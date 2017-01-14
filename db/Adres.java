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
public class Adres extends Table_abstract {
    private int adres_id;
    private String wojewodztwo;
    private String miejscowosc;
    private String ulica;
    private String kraj;
    private String kod_pocztowy;

    private IntegerProperty adres_id_p;
    private StringProperty  wojewodztwo_p;
    private StringProperty  miejscowosc_p;
    private StringProperty  ulica_p;
    private StringProperty  kraj_p;
    private StringProperty  kod_pocztowy_p;

    private int prev_adres_id;

    private static boolean isCreated;

    static {
        isCreated = false;
    }

    public Adres(){};

    public Adres(int adres_id, String wojewodztwo, String miejscowosc,
                 String ulica, String kraj, String kod_pocztowy) {
        this.class_name = "Adres";
        this.adres_id = adres_id;
        this.wojewodztwo = wojewodztwo;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.kraj = kraj;
        this.kod_pocztowy = kod_pocztowy;

        this.adres_id_p = new SimpleIntegerProperty(this.adres_id);
        this.wojewodztwo_p = new SimpleStringProperty(this.wojewodztwo);
        this.miejscowosc_p = new SimpleStringProperty(this.miejscowosc);
        this.ulica_p = new SimpleStringProperty(this.ulica);
        this.kraj_p = new SimpleStringProperty(this.kraj);
        this.kod_pocztowy_p = new SimpleStringProperty(this.kod_pocztowy);

        if (this.isCreated == false){
            this.create();
        }
        this.isCreated = true;
        this.insert(this.generate_insert_query());
    }

    protected void create(){
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            String sql;
            sql = "CREATE TABLE Adres " +
                    "(adres_id   INT PRIMARY KEY NOT NULL," +
                    "wojewodztwo          VARCHAR NOT NULL," +
                    "miejscowosc          VARCHAR NOT NULL," +
                    "ulica          VARCHAR NOT NULL," +
                    "kraj          VARCHAR NOT NULL," +
                    "kod_pocztowy          VARCHAR NOT NULL);";
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
                res += rs.getInt("adres_id") + " ";
                res += rs.getString("wojewodztwo") + " ";
                res += rs.getString("miejscowosc") + " ";
                res += rs.getString("ulica") + " ";
                res += rs.getString("kraj") + " ";
                res += rs.getString("kod_pocztowy") + " ";
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
            this.adres_id_p = new SimpleIntegerProperty(this.adres_id);
            this.wojewodztwo_p = new SimpleStringProperty(this.wojewodztwo);
            this.miejscowosc_p = new SimpleStringProperty(this.miejscowosc);
            this.ulica_p = new SimpleStringProperty(this.ulica);
            this.kraj_p = new SimpleStringProperty(this.kraj);
            this.kod_pocztowy_p = new SimpleStringProperty(this.kod_pocztowy);
            stmt = this.conn.createStatement();
            String sql;
            sql = "UPDATE Adres SET "
                    + "adres_id = " + this.adres_id + ", "
                    + "wojewodztwo = '" + this.wojewodztwo + "', "
                    + "miejscowosc = '" + this.miejscowosc + "', "
                    + "ulica = '" + this.ulica + "', "
                    + "kraj = '" + this.kraj + "', "
                    + "kod_pocztowy = '" + this.kod_pocztowy
                    + "' WHERE adres_id = " + this.prev_adres_id
                    + ";";
            stmt.executeUpdate(sql);
            System.out.println("UPDATE");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String generate_insert_query() {
        return "(" + this.adres_id + ", '"
                + this.wojewodztwo + "', '"
                + this.miejscowosc + "', '"
                + this.ulica + "', '"
                + this.kraj + "', '"
                + this.kod_pocztowy +"')";
    }

    public int getAdres_id() {
        return adres_id;
    }

    public String getWojewodztwo() {
        return wojewodztwo;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public String getUlica() {
        return ulica;
    }

    public void setAdres_id(int adres_id) {
        this.adres_id = adres_id;
    }

    public void setWojewodztwo(String wojewodztwo) {
        this.wojewodztwo = wojewodztwo;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public void setKraj(String kraj) {
        this.kraj = kraj;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getKraj() {
        return kraj;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public int getPrev_adres_id() {
        return prev_adres_id;
    }

    public void setPrev_adres_id(int prev_adres_id) {
        this.prev_adres_id = prev_adres_id;
    }

    public int getAdres_id_p() {
        return adres_id_p.get();
    }

    public IntegerProperty adres_id_pProperty() {
        return adres_id_p;
    }

    public StringProperty wojewodztwo_pProperty() {
        return wojewodztwo_p;
    }

    public StringProperty miejscowosc_pProperty() {
        return miejscowosc_p;
    }

    public StringProperty ulica_pProperty() {
        return ulica_p;
    }

    public StringProperty kraj_pProperty() {
        return kraj_p;
    }

    public StringProperty kod_pocztowy_pProperty() {
        return kod_pocztowy_p;
    }
}
