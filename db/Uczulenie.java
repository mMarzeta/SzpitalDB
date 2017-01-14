package db;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by maciejmarzeta on 08.01.2017.
 */
public class Uczulenie extends Table_abstract {
    private int uczulenie_id;
    private String nazwa;
    private int lek_ref;

    public Uczulenie() {
        this.class_name = "Uczulenie";
        this.create();
    }

    public Uczulenie(int uczulenie_id, String nazwa){
        this();
        this.uczulenie_id = uczulenie_id;
        this.nazwa = nazwa;
        this.lek_ref = 0;
    }

    public Uczulenie(int uczulenie_id, String nazwa, int lek_id){
        this(uczulenie_id, nazwa);
        this.lek_ref = lek_id;
    }

    protected void create() {
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            String sql;
            sql = "CREATE TABLE Uczulenie " +
                    "(uczulenie_id   INT PRIMARY KEY NOT NULL," +
                    "nazwa          VARCHAR NOT NULL," +
                    "lek_ref         INT REFERENCES lek(lek_id));";
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
                res += rs.getInt("uczulenie_id") + " ";
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

    //moze byc problem bo lek nie musi miec uczulenia
    public String generate_insert_query(){
        return "(" + this.uczulenie_id + ", '" + this.nazwa +"', " + this.lek_ref +")";
    }

}
