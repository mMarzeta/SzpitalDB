package db;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by maciejmarzeta on 08.01.2017.
 */
public class Lek extends Table_abstract {
    private int lek_id;
    private String nazwa;

    public Lek() {
        this.class_name = "Lek";
        this.create();
    }

    public Lek(int lek_id, String nazwa){
        this();
        this.lek_id = lek_id;
        this.nazwa = nazwa;
    }

    protected void create() {
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            String sql;
            sql = "CREATE TABLE Lek " +
                    "(lek_id   INT PRIMARY KEY NOT NULL," +
                    "nazwa          VARCHAR NOT NULL)";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String select() {
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            this.conn.setAutoCommit(false);
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.class_name);
            String res = " ";
            while (rs.next()) {
                res += rs.getInt("lek_id") + " ";
                res += rs.getString("nazwa") + " ";
                res += "\n";
            }
            rs.close();
            stmt.close();
            this.conn.setAutoCommit(true);
            System.out.println(res + "DODANO");
            return res;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    //moze byc problem bo uczulenie nie musi miec leku
    public String generate_insert_query(){
        return "(" + this.lek_id + ", '" + this.nazwa +"')";
    }


}
