package db;

import javafx.scene.control.Tab;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by maciejmarzeta on 09.01.2017.
 */
public class Lekarz  extends Table_abstract{
    private int lekarz_id;
    private String imie;
    private String nazwisko;
    private String numer_telefonu;
    private int adres_ref;
    private int plec_ref;

    public Lekarz(){
        this.class_name = "Lekarz";
        this.create();
    }

    public Lekarz(int lekarz_id, String imie, String nazwisko,
                  String numer_telefonu, int adres_ref, int plec_ref) {
        this();
        this.lekarz_id = lekarz_id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numer_telefonu = numer_telefonu;
        this.adres_ref = adres_ref;
        this.plec_ref = plec_ref;
    }

    protected void create(){
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            String sql;
            sql = "CREATE TABLE Lekarz " +
                    "(lekarz_id   INT PRIMARY KEY NOT NULL," +
                    "imie          VARCHAR NOT NULL," + //albo varchar
                    "nazwisko         VARCHAR ," +
                    "numer_telefonu         VARCHAR ," +
                    "adres_ref         INT REFERENCES adres(adres_id)," +
                    "plec_ref         INT REFERENCES plec(plec_id));";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String select(){
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            this.conn.setAutoCommit(false);
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.class_name);
            String res = " ";
            while (rs.next()) {
                res += rs.getInt("lekarz_id") + " ";
                res += rs.getString("imie") + " ";
                res += rs.getString("nazwisko") + " ";
                res += rs.getString("numer_telefonu") + " ";
                res += rs.getInt("adres_ref") + " ";
                res += rs.getInt("plec_ref") + " ";
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
        return "(" + this.lekarz_id + ", '"
                + this.imie +"', '"
                + this.nazwisko + "', '"
                + this.numer_telefonu + "', "
                + this.adres_ref + ", "
                + this.plec_ref + ")";
    }

}
