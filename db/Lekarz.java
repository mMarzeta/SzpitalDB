package db;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

    private IntegerProperty lekarz_id_p;
    private StringProperty imie_p;
    private StringProperty nazwisko_p;
    private StringProperty numer_telefonu_p;
    private IntegerProperty adres_ref_p;
    private IntegerProperty plec_ref_p;

    private int prev_lekarz_id;

    private static boolean isCreated;
    static {
        isCreated = false;
    }

    public Lekarz(){
        this.class_name = "Lekarz";
        //this.create();
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

        this.lekarz_id_p = new SimpleIntegerProperty(lekarz_id);
        this.imie_p = new SimpleStringProperty(imie);
        this.nazwisko_p = new SimpleStringProperty(nazwisko);
        this.numer_telefonu_p = new SimpleStringProperty(numer_telefonu);
        this.adres_ref_p = new SimpleIntegerProperty(adres_ref);
        this.plec_ref_p = new SimpleIntegerProperty(plec_ref);

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

    public void update(){
        Statement stmt = null;
        try {
            this.lekarz_id_p = new SimpleIntegerProperty(lekarz_id);
            this.imie_p = new SimpleStringProperty(imie);
            this.nazwisko_p = new SimpleStringProperty(nazwisko);
            this.numer_telefonu_p = new SimpleStringProperty(numer_telefonu);
            this.adres_ref_p = new SimpleIntegerProperty(adres_ref);
            this.plec_ref_p = new SimpleIntegerProperty(plec_ref);
            stmt = this.conn.createStatement();
            String sql;
            sql = "UPDATE Lekarz SET "
                    + "lekarz_id = " + this.lekarz_id + ", "
                    + "imie = '" + this.imie + "', "
                    + "nazwisko = '" + this.nazwisko + "', "
                    + "numer_telefonu = '" + this.numer_telefonu + "', "
                    + "adres_ref = " + this.adres_ref + ","
                    + "plec_ref = " + this.plec_ref
                    + " WHERE lekarz_id = " + this.prev_lekarz_id
                    + ";";
            stmt.executeUpdate(sql);
            System.out.println("UPDATE lekarz");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String generate_insert_query(){
        return "(" + this.lekarz_id + ", '"
                + this.imie +"', '"
                + this.nazwisko + "', '"
                + this.numer_telefonu + "', "
                + this.adres_ref + ", "
                + this.plec_ref + ")";
    }

    public int getLekarz_id() {
        return lekarz_id;
    }

    public void setLekarz_id(int lekarz_id) {
        this.lekarz_id = lekarz_id;
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

    public String getNumer_telefonu() {
        return numer_telefonu;
    }

    public void setNumer_telefonu(String numer_telefonu) {
        this.numer_telefonu = numer_telefonu;
    }

    public int getAdres_ref() {
        return adres_ref;
    }

    public void setAdres_ref(int adres_ref) {
        this.adres_ref = adres_ref;
    }

    public int getPlec_ref() {
        return plec_ref;
    }

    public void setPlec_ref(int plec_ref) {
        this.plec_ref = plec_ref;
    }

    public int getLekarz_id_p() {
        return lekarz_id_p.get();
    }

    public IntegerProperty lekarz_id_pProperty() {
        return lekarz_id_p;
    }

    public void setLekarz_id_p(int lekarz_id_p) {
        this.lekarz_id_p.set(lekarz_id_p);
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

    public int getAdres_ref_p() {
        return adres_ref_p.get();
    }

    public IntegerProperty adres_ref_pProperty() {
        return adres_ref_p;
    }

    public void setAdres_ref_p(int adres_ref_p) {
        this.adres_ref_p.set(adres_ref_p);
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

    public int getPrev_lekarz_id() {
        return prev_lekarz_id;
    }

    public void setPrev_lekarz_id(int prev_lekarz_id) {
        this.prev_lekarz_id = prev_lekarz_id;
    }

    public static boolean isIsCreated() {
        return isCreated;
    }

    public static void setIsCreated(boolean isCreated) {
        Lekarz.isCreated = isCreated;
    }
}
