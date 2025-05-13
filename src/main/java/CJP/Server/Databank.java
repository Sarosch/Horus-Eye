package CJP.Server;


import java.sql.*;

public class Databank {
    private static String url_db;
    private static String TableName;
    private static Statement db = null;
    private static String OnStart = String.format("CREATE TABLE IF NOT EXISTS %n( id INTEGER PRIMARY KEY AUTOINCREMENT);",TableName);

    public Databank(String dbName, String TableName){
        this.url_db = dbName;
        this.TableName = TableName;
    }
    public static void SetOnStart(String command){
        OnStart = String.format("CREATE TABLE IF NOT EXISTS %s"+
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, %a);",TableName,command);
    }
    public static void Start(){
        try(Connection conn = DriverManager.getConnection(url_db);
            Statement stmt = conn.createStatement()){
            db = stmt;
            db.execute(OnStart);
        }catch (SQLException e){
            System.out.println("SQL Start Fail");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
    public static void InsertIn(String Column, String value){
        if(db != null){
            String exe = String.format("INSERT INTO %t(%c) VALUES(%v);",TableName,Column,value);
            try {
                db.execute(exe);
            } catch (SQLException e) {
                System.out.println("SQL Insert Fail");
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }
    public static void AddColumn(String ColumnSetting){
        if(db != null){
            String exe = String.format("ALTER TABLE %t ADD COLUMN %c;",TableName,ColumnSetting);
            try {
                db.execute(exe);
            } catch (SQLException e) {
                System.out.println("SQL AddColumn Fail");
                System.out.println(e.getMessage());
                System.exit(1);
            }

        }
    }
    public static String Read(String SearchKey, String Column){
        String data = null;
        if(db != null) {
            String exe = String.format("SELECT %c FROM %t WHERE %k;", Column, TableName, SearchKey);
            try {
                ResultSet result = db.executeQuery(exe);
                while(result.next()){
                    data = result.getString(SearchKey);
                }
            } catch (SQLException e) {
                System.out.println("SQL Read Fail");
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
        return data;
    }
    public static void EditIn(String SearchKey, String Column, String Value){
        if(db != null){
            String exe = String.format("UPDATE %t SET %c=%v WHERE %k;",TableName,Column,Value,SearchKey);
            try {
                db.execute(exe);
            } catch (SQLException e) {
                System.out.println("SQL EditIn Fail");
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }
    public static Statement getdb(){
        return db;
    }

}
