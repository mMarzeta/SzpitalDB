package db;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by maciejmarzeta on 16.01.2017.
 */
public class Statystyki extends Table_abstract {
    protected void create(){};
    public String select(){return " ";};

    public int agregacja(String q1, String q2){
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            this.conn.setAutoCommit(false);
            ResultSet rs = stmt.executeQuery("SELECT COUNT( " + q1 + ") AS total FROM " + q2 + ";");
            int res = 0;
            while(rs.next()) {
                res = rs.getInt("total");
            }
            rs.close();
            stmt.close();
            this.conn.setAutoCommit(true);
            return res;
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return 0;
    }
}
