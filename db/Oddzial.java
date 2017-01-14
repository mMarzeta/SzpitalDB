package db;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by maciejmarzeta on 09.01.2017.
 */
public class Oddzial extends Table_abstract{
    private int oddzial_id;
    private String nazwa;
    private int lekarz_ref;

    public Oddzial(int oddzial_id, String nazwa, int lekarz_ref) {
        this.class_name = "Oddzial";
        this.oddzial_id = oddzial_id;
        this.nazwa = nazwa;
        this.lekarz_ref = lekarz_ref;
        this.create();
    }

    protected void create() {
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            String sql;
            sql = "CREATE TABLE Oddzial " +
                    "(oddzial_id   INT PRIMARY KEY NOT NULL," +
                    "nazwa          VARCHAR NOT NULL," +
                    "lekarz_ref     INT REFERENCES lekarz(lekarz_id));";
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
                res += rs.getInt("oddzial_id") + " ";
                res += rs.getString("nazwa") + " ";
                res += rs.getInt("lekarz_ref") + " ";
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
        return "(" + this.oddzial_id + ", '"
                + this.nazwa + "',"
                + this.lekarz_ref + ")";
    }

}
