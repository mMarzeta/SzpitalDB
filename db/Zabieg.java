package db;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by maciejmarzeta on 09.01.2017.
 */
public class Zabieg extends Table_abstract {
    private int zabieg_id;
    private String nazwa;
    private int oddzial_ref;

    public Zabieg(int zabieg_id, String nazwa, int oddzial_ref) {
        this.class_name = "Zabieg";
        this.zabieg_id = zabieg_id;
        this.nazwa = nazwa;
        this.oddzial_ref = oddzial_ref;
        this.create();
    }

    protected void create() {
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            String sql;
            sql = "CREATE TABLE Zabieg " +
                    "(zabieg_id   INT PRIMARY KEY NOT NULL," +
                    "nazwa         VARCHAR," +
                    "oddzial_ref     INT REFERENCES oddzial(oddzial_id));";

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
                res += rs.getInt("zabieg_id") + " ";
                res += rs.getString("nazwa") + " ";
                res += rs.getInt("oddzial_ref") + " ";
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
        return "(" + this.zabieg_id + ", '"
                + this.nazwa + "', "
                + this.oddzial_ref + ")";
    }
}
