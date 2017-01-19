package db;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

    private IntegerProperty szpital_id_p;
    private StringProperty nazwa_p;
    private IntegerProperty adres_ref_p;
    private IntegerProperty oddzial_ref_p;

    private int prev_szpita_id;

    private static boolean isCreated;
    static {
        isCreated = false;
    }

    public Szpital(){};

    public Szpital(int szpital_id, String nazwa, int adres_ref, int oddzial_ref) {
        this.class_name = "Szpital";
        this.szpital_id = szpital_id;
        this.nazwa = nazwa;
        this.adres_ref = adres_ref;
        this.oddzial_ref = oddzial_ref;

        this.szpital_id_p = new SimpleIntegerProperty(szpital_id);
        this.nazwa_p = new SimpleStringProperty(nazwa);
        this.adres_ref_p = new SimpleIntegerProperty(adres_ref);
        this.oddzial_ref_p = new SimpleIntegerProperty(oddzial_ref);

        if (this.isCreated == false){
            this.create();
        }
        this.isCreated = true;
        this.insert(this.generate_insert_query());
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

    public void update(){
        Statement stmt = null;
        try {
            this.szpital_id_p = new SimpleIntegerProperty(szpital_id);
            this.nazwa_p = new SimpleStringProperty(nazwa);
            this.adres_ref_p = new SimpleIntegerProperty(adres_ref);
            this.oddzial_ref_p = new SimpleIntegerProperty(oddzial_ref);

            stmt = this.conn.createStatement();
            String sql;
            sql = "UPDATE Szpital SET "
                    + "szpital_id = " + this.szpital_id + ", "
                    + "nazwa = '" + this.nazwa + "', "
                    + "adres_ref = " + this.adres_ref +", "
                    + "oddzial_ref = " + this.oddzial_ref
                    + " WHERE szpital_id = " + this.prev_szpita_id
                    + ";";
            stmt.executeUpdate(sql);
            System.out.println("UPDATE zabieg");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String generate_insert_query(){
        return "(" + this.szpital_id + ", '"
                + this.nazwa + "', "
                + this.adres_ref + ", "
                + this.oddzial_ref + ")";
    }

    public int getSzpital_id() {
        return szpital_id;
    }

    public void setSzpital_id(int szpital_id) {
        this.szpital_id = szpital_id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getAdres_ref() {
        return adres_ref;
    }

    public void setAdres_ref(int adres_ref) {
        this.adres_ref = adres_ref;
    }

    public int getOddzial_ref() {
        return oddzial_ref;
    }

    public void setOddzial_ref(int oddzial_ref) {
        this.oddzial_ref = oddzial_ref;
    }

    public int getSzpital_id_p() {
        return szpital_id_p.get();
    }

    public IntegerProperty szpital_id_pProperty() {
        return szpital_id_p;
    }

    public void setSzpital_id_p(int szpital_id_p) {
        this.szpital_id_p.set(szpital_id_p);
    }

    public String getNazwa_p() {
        return nazwa_p.get();
    }

    public StringProperty nazwa_pProperty() {
        return nazwa_p;
    }

    public void setNazwa_p(String nazwa_p) {
        this.nazwa_p.set(nazwa_p);
    }

    public int getAdres_ref_p() {
        return adres_ref_p.get();
    }

    public IntegerProperty adres_ref_pProperty() {
        return adres_ref_p;
    }

    public void setAdres_ref_p(int adres_ref_p) {
        this.adres_ref_p.set(adres_ref_p);
    }

    public int getOddzial_ref_p() {
        return oddzial_ref_p.get();
    }

    public IntegerProperty oddzial_ref_pProperty() {
        return oddzial_ref_p;
    }

    public void setOddzial_ref_p(int oddzial_ref_p) {
        this.oddzial_ref_p.set(oddzial_ref_p);
    }

    public void setPrev_szpita_id(int prev_szpita_id) {
        this.prev_szpita_id = prev_szpita_id;
    }
}
