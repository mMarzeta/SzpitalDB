package db;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by maciejmarzeta on 09.01.2017.
 */
public class Szpital extends Table_abstract{
    private int szpital_id;
    private String nazwa;
    private int adres_ref;
    private int oddzial_ref;

    public Szpital(int szpital_id, String nazwa, int adres_ref, int oddzial_ref) {
        this.class_name = "Szpital";
        this.szpital_id = szpital_id;
        this.nazwa = nazwa;
        this.adres_ref = adres_ref;
        this.oddzial_ref = oddzial_ref;
        this.create();
    }

    protected void create() {
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            String sql;
            sql = "CREATE TABLE Szpital " +
                    "(szpital_id   INT PRIMARY KEY NOT NULL," +
                    "nazwa          VARCHAR NOT NULL," +
                    "adres_ref         INT REFERENCES adres(adres_id)," +
                    "oddzial_ref         INT REFERENCES oddzial(oddzial_id));";
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
                res += rs.getInt("szpital_id") + " ";
                res += rs.getString("nazwa") + " ";
                res += rs.getInt("adres_ref") + " ";
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
        return "(" + this.szpital_id + ", '"
                + this.nazwa + "', "
                + this.adres_ref + ", "
                + this.oddzial_ref + ")";
    }
}
