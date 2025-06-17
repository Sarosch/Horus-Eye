package CJP.Server.Suporte;


import java.sql.*;

public class Databank {
    private static String url_db;
    private static Statement stmt;
    private static Connection conn;
    private static String TableName;
    private static Statement db = null;
    private static String OnStart = String.format("CREATE TABLE IF NOT EXISTS %n( id INTEGER PRIMARY KEY AUTOINCREMENT);",TableName);

    public Databank(String dbName, String TableName){
        this.url_db = dbName;
        this.TableName = TableName;
    }
    public void SetOnStart(String command){
            OnStart = String.format("CREATE TABLE IF NOT EXISTS %s ( id INTEGER PRIMARY KEY AUTO_INCREMENT, %s);", TableName, command);
    }
    public void Start(){
        try{
            conn = DriverManager.getConnection(url_db);
            stmt = conn.createStatement();
            db = stmt;
            db.setQueryTimeout(0);
            db.execute(OnStart);
        }catch (SQLException e){
            System.out.println("SQL Start Fail");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void Start(String User, String Password){
        try{
            conn = DriverManager.getConnection(url_db,User,Password);
            stmt = conn.createStatement();
            db = stmt;
            db.execute(OnStart);
        }catch (SQLException e){
            System.out.println("SQL Start Fail");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
    public void InsertIn(String Column, String value){
        if(db != null){
            String exe = String.format("INSERT INTO %s(%s) VALUES(%s);",TableName,Column,value);
            try {
                db.execute(exe);
            } catch (SQLException e) {
                System.out.println("SQL Insert Fail");
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }
    public void AddColumn(String ColumnSetting){
        if(db != null){
            String exe = String.format("ALTER TABLE %s ADD COLUMN %s;",TableName,ColumnSetting);
            try {
                db.execute(exe);
            } catch (SQLException e) {
                System.out.println("SQL AddColumn Fail");
                System.out.println(e.getMessage());
                System.exit(1);
            }

        }
    }
    public String Read(String SearchKey, String Column){
        String data = null;
        if(db != null) {
            String exe = String.format("SELECT %s FROM %s WHERE %s;", Column, TableName, SearchKey);
            try {
                ResultSet result = db.executeQuery(exe);
                while(result.next()){
                    data = result.getString(Column);
                }
            } catch (SQLException e) {
                System.out.println("SQL Read Fail");
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
        return data;
    }
    public boolean ValueExists(String SearchKey, String Column){
        try{
            ResultSet result = db.executeQuery(String.format("SELECT %s FROM %s WHERE %s;", Column, TableName, SearchKey));
            if(result.next()){
                System.out.println(result.getString(1));
                return true;
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public void EditIn(String SearchKey, String Column, String Value){
        if(db != null){
            String exe = String.format("UPDATE %s SET %s=%s WHERE %s;",TableName,Column,Value,SearchKey);
            try {
                db.execute(exe);
            } catch (SQLException e) {
                System.out.println("SQL EditIn Fail");
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
    }
    public Statement getdb(){
        return db;
    }

}
