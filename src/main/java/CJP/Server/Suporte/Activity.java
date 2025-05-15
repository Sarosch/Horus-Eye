package CJP.Server.Suporte;

import javax.swing.*;

public interface Activity {
    default JPanel Layout(){
        return null;
    };
}
