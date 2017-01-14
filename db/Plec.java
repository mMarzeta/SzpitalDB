package db;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by maciejmarzeta on 07.01.2017.
 */
public class Plec extends Table_abstract {
    private int plec_id;
    private String nazwa;

    public Plec() {
        this.class_name = "Plec";
        this.create();
    }

    public Plec(int plec_id, String nazwa){
        this();
        this.plec_id = plec_id;
        this.nazwa = nazwa;
    }

    protected void create() {
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            String sql;
            sql = "CREATE TABLE Plec " +
                    "(plec_id   INT PRIMARY KEY NOT NULL," +
                    "nazwa       VARCHAR NOT NULL);";
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
                res += rs.getInt("plec_id") + " ";
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

    public String generate_insert_query(){
        return "(" + this.plec_id + ", '" + this.nazwa +"')";
    }

    public String generate_insert_query(int plec_id, String nazwa){
        return "(" + plec_id + ", '" + nazwa +"')";
    }

}