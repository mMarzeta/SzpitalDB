package db;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

    private IntegerProperty pacjent_id_p;
    private StringProperty imie_p;
    private StringProperty nazwisko_p;
    private StringProperty numer_telefonu_p;
    private IntegerProperty plec_ref_p;
    private IntegerProperty uczulenie_ref_p;
    private IntegerProperty adres_ref_p;
    private IntegerProperty wizyta_ref_p;

    private int prev_pacjent_id;

    private static boolean isCreated;
    static {
        isCreated = false;
    }

    public Pacjent(){
        this.class_name = "Pacjent";
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

        this.pacjent_id_p = new SimpleIntegerProperty(pacjent_id);
        this.imie_p = new SimpleStringProperty(imie);
        this.nazwisko_p = new SimpleStringProperty(nazwisko);
        this.numer_telefonu_p = new SimpleStringProperty(numer_telefonu);
        this.plec_ref_p = new SimpleIntegerProperty(plec_ref);
        this.uczulenie_ref_p = new SimpleIntegerProperty(uczulenie_ref);
        this.adres_ref_p = new SimpleIntegerProperty(adres_ref);
        this.wizyta_ref_p = new SimpleIntegerProperty(wizyta_ref);

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

    public void update(){
        Statement stmt = null;
        try {
            this.pacjent_id_p = new SimpleIntegerProperty(pacjent_id);
            this.imie_p = new SimpleStringProperty(imie);
            this.nazwisko_p = new SimpleStringProperty(nazwisko);
            this.numer_telefonu_p = new SimpleStringProperty(numer_telefonu);
            this.plec_ref_p = new SimpleIntegerProperty(plec_ref);
            this.uczulenie_ref_p = new SimpleIntegerProperty(uczulenie_ref);
            this.adres_ref_p = new SimpleIntegerProperty(adres_ref);
            this.wizyta_ref_p = new SimpleIntegerProperty(wizyta_ref);

            stmt = this.conn.createStatement();
            String sql;
            sql = "UPDATE Pacjent SET "
                    + "pacjent_id = " + this.pacjent_id + ", "
                    + "imie = '" + this.imie + "', "
                    + "nazwisko = '" + this.nazwisko + "', "
                    + "numer_telefonu = '" + this.numer_telefonu+ "', "
                    + "plec_ref = " + this.plec_ref+ ", "
                    + "uczulenie_ref = " + this.uczulenie_ref + ", "
                    + "adres_ref = " + this.adres_ref + ", "
                    + "wizyta_ref = " + this.wizyta_ref + " "
                    + " WHERE pacjent_id = " + this.prev_pacjent_id
                    + ";";
            stmt.executeUpdate(sql);
            System.out.println("UPDATE pacjent");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String generate_insert_query(){
        return "(" + this.pacjent_id + ", '" + this.imie +"', '" + this.nazwisko
                + "', '" + this.data_urodzenia + "', '" + this.numer_telefonu
                + "', " + this.plec_ref + ", " + this.uczulenie_ref + ", " + this.adres_ref
                + ", " + wizyta_ref + ")";
    }

    public int getPacjent_id() {
        return pacjent_id;
    }

    public void setPacjent_id(int pacjent_id) {
        this.pacjent_id = pacjent_id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getData_urodzenia() {
        return data_urodzenia;
    }

    public void setData_urodzenia(String data_urodzenia) {
        this.data_urodzenia = data_urodzenia;
    }

    public String getNumer_telefonu() {
        return numer_telefonu;
    }

    public void setNumer_telefonu(String numer_telefonu) {
        this.numer_telefonu = numer_telefonu;
    }

    public int getPlec_ref() {
        return plec_ref;
    }

    public void setPlec_ref(int plec_ref) {
        this.plec_ref = plec_ref;
    }

    public int getUczulenie_ref() {
        return uczulenie_ref;
    }

    public void setUczulenie_ref(int uczulenie_ref) {
        this.uczulenie_ref = uczulenie_ref;
    }

    public int getAdres_ref() {
        return adres_ref;
    }

    public void setAdres_ref(int adres_ref) {
        this.adres_ref = adres_ref;
    }

    public int getWizyta_ref() {
        return wizyta_ref;
    }

    public void setWizyta_ref(int wizyta_ref) {
        this.wizyta_ref = wizyta_ref;
    }

    public int getPacjent_id_p() {
        return pacjent_id_p.get();
    }

    public IntegerProperty pacjent_id_pProperty() {
        return pacjent_id_p;
    }

    public void setPacjent_id_p(int pacjent_id_p) {
        this.pacjent_id_p.set(pacjent_id_p);
    }

    public String getImie_p() {
        return imie_p.get();
    }

    public StringProperty imie_pProperty() {
        return imie_p;
    }

    public void setImie_p(String imie_p) {
        this.imie_p.set(imie_p);
    }

    public String getNazwisko_p() {
        return nazwisko_p.get();
    }

    public StringProperty nazwisko_pProperty() {
        return nazwisko_p;
    }

    public void setNazwisko_p(String nazwisko_p) {
        this.nazwisko_p.set(nazwisko_p);
    }

    public String getNumer_telefonu_p() {
        return numer_telefonu_p.get();
    }

    public StringProperty numer_telefonu_pProperty() {
        return numer_telefonu_p;
    }

    public void setNumer_telefonu_p(String numer_telefonu_p) {
        this.numer_telefonu_p.set(numer_telefonu_p);
    }

    public int getPlec_ref_p() {
        return plec_ref_p.get();
    }

    public IntegerProperty plec_ref_pProperty() {
        return plec_ref_p;
    }

    public void setPlec_ref_p(int plec_ref_p) {
        this.plec_ref_p.set(plec_ref_p);
    }

    public int getUczulenie_ref_p() {
        return uczulenie_ref_p.get();
    }

    public IntegerProperty uczulenie_ref_pProperty() {
        return uczulenie_ref_p;
    }

    public void setUczulenie_ref_p(int uczulenie_ref_p) {
        this.uczulenie_ref_p.set(uczulenie_ref_p);
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

    public int getWizyta_ref_p() {
        return wizyta_ref_p.get();
    }

    public IntegerProperty wizyta_ref_pProperty() {
        return wizyta_ref_p;
    }

    public void setWizyta_ref_p(int wizyta_ref_p) {
        this.wizyta_ref_p.set(wizyta_ref_p);
    }

    public int getPrev_pacjent_id() {
        return prev_pacjent_id;
    }

    public void setPrev_pacjent_id(int prev_pacjent_id) {
        this.prev_pacjent_id = prev_pacjent_id;
    }
}
