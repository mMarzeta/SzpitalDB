package db;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Ref;

/**
 * Created by maciejmarzeta on 07.01.2017.
 */
public class Pacjent extends Table_abstract{
    private int pacjent_id;
    private String imie;
    private String nazwisko;
    private String data_urodzenia;
    private String numer_telefonu;
    private int plec_ref;
    private int uczulenie_ref;
    private int adres_ref;
    private int wizyta_ref;

    public Pacjent(){
        this.class_name = "Pacjent";
        this.create();
    }

    public Pacjent(int pacjent_id, String imie, String nazwisko, String data_urodzenia,
                   String numer_telefonu, int plec_ref, int uczulenie_ref, int adres_ref,
                   int wizyta_ref){
        this();
        this.pacjent_id = pacjent_id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data_urodzenia = data_urodzenia;
        this.numer_telefonu = numer_telefonu;
        this.plec_ref = plec_ref;
        this.uczulenie_ref = uczulenie_ref;
        this.adres_ref = adres_ref;
        this.wizyta_ref = wizyta_ref;
    }

    protected void create() {
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            String sql;
            sql = "CREATE TABLE Pacjent " +
                    "(pacjent_id            INT PRIMARY KEY NOT NULL," +
                    "imie                   VARCHAR NOT NULL," +
                    "nazwisko               VARCHAR NOT NULL," +
                    "data_urodzenia         VARCHAR NOT NULL," +
                    "numer_telefonu         VARCHAR NOT NULL," +
                    "plec_ref               INT REFERENCES plec(plec_id)," +
                    "uczulenie_ref          INT REFERENCES uczulenie(uczulenie_id)," +
                    "adres_ref              INT REFERENCES adres(adres_id)," +
                    "wizyta_ref             INT REFERENCES wizyta(wizyta_id));";

            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }


    public String select(){
        Statement stmt = null;
        try {
            this.conn.setAutoCommit(false);
            stmt = this.conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM " + this.class_name);
            String res = " ";
            ResultSet foreignKeys = conn.getMetaData().getImportedKeys(conn.getCatalog(), null, "Plec");
            while (rs.next()) {
                res += rs.getInt("pacjent_id") + " ";
                res += rs.getString("imie") + " ";
                res += rs.getString("nazwisko") + " ";
                res += rs.getString("data_urodzenia") + " ";
                res += rs.getString("numer_telefonu") + " ";
                res += rs.getInt("plec_ref") + " ";
                res += rs.getInt("uczulenie_ref") + " ";
                res += rs.getInt("adres_ref") + " ";
                res += rs.getInt("wizyta_ref") + " ";
                res += "\n";
            }
            rs.close();
            stmt.close();
            this.conn.setAutoCommit(true);
            return res;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;
    }

    public String generate_insert_query(){
        return "(" + this.pacjent_id + ", '" + this.imie +"', '" + this.nazwisko
                + "', '" + this.data_urodzenia + "', '" + this.numer_telefonu
                + "', " + this.plec_ref + ", " + this.uczulenie_ref + ", " + this.adres_ref
                + ", " + wizyta_ref + ")";
    }
}
