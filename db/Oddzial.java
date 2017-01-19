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
public class Oddzial extends Table_abstract{
    private int oddzial_id;
    private String nazwa;
    private int lekarz_ref;

    private IntegerProperty oddzial_id_p;
    private StringProperty nazwa_p;
    private IntegerProperty lekarz_ref_p;

    private int prev_oddzial_id;

    private static boolean isCreated;
    static{
        isCreated = false;
    }

    public Oddzial(){}

    public Oddzial(int oddzial_id, String nazwa, int lekarz_ref) {
        this.class_name = "Oddzial";
        this.oddzial_id = oddzial_id;
        this.nazwa = nazwa;
        this.lekarz_ref = lekarz_ref;

        this.oddzial_id_p = new SimpleIntegerProperty(oddzial_id);
        this.nazwa_p = new SimpleStringProperty(nazwa);
        this.lekarz_ref_p = new SimpleIntegerProperty(lekarz_ref);

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

    public void update(){
        Statement stmt = null;
        try {
            this.oddzial_id_p = new SimpleIntegerProperty(this.oddzial_id);
            this.nazwa_p = new SimpleStringProperty(this.nazwa);
            this.lekarz_ref_p = new SimpleIntegerProperty(this.lekarz_ref);
            stmt = this.conn.createStatement();
            String sql;
            sql = "UPDATE Oddzial SET "
                    + "oddzial_id = " + this.oddzial_id + ", "
                    + "nazwa = '" + this.nazwa + "', "
                    + "lekarz_ref = " + this.lekarz_ref
                    + " WHERE oddzial_id = " + this.prev_oddzial_id
                    + ";";
            stmt.executeUpdate(sql);
            System.out.println("UPDATE oddzial");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String generate_insert_query(){
        return "(" + this.oddzial_id + ", '"
                + this.nazwa + "',"
                + this.lekarz_ref + ")";
    }

    public int getOddzial_id() {
        return oddzial_id;
    }

    public void setOddzial_id(int oddzial_id) {
        this.oddzial_id = oddzial_id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getLekarz_ref() {
        return lekarz_ref;
    }

    public void setLekarz_ref(int lekarz_ref) {
        this.lekarz_ref = lekarz_ref;
    }

    public int getOddzial_id_p() {
        return oddzial_id_p.get();
    }

    public IntegerProperty oddzial_id_pProperty() {
        return oddzial_id_p;
    }

    public void setOddzial_id_p(int oddzial_id_p) {
        this.oddzial_id_p.set(oddzial_id_p);
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

    public int getLekarz_ref_p() {
        return lekarz_ref_p.get();
    }

    public IntegerProperty lekarz_ref_pProperty() {
        return lekarz_ref_p;
    }

    public void setLekarz_ref_p(int lekarz_ref_p) {
        this.lekarz_ref_p.set(lekarz_ref_p);
    }

    public int getPrev_oddzial_id() {
        return prev_oddzial_id;
    }

    public void setPrev_oddzial_id(int prev_oddzial_id) {
        this.prev_oddzial_id = prev_oddzial_id;
    }
}
