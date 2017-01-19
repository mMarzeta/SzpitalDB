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
public class Zabieg extends Table_abstract {
    private int zabieg_id;
    private String nazwa;
    private int oddzial_ref;

    private IntegerProperty zabieg_id_p;
    private StringProperty nazwa_p;
    private IntegerProperty oddzial_ref_p;

    private int prev_zabieg_id;

    private static boolean isCreated;
    static {
        isCreated = false;
    }

    public Zabieg(){}

    public Zabieg(int zabieg_id, String nazwa, int oddzial_ref) {
        this.class_name = "Zabieg";
        this.zabieg_id = zabieg_id;
        this.nazwa = nazwa;
        this.oddzial_ref = oddzial_ref;

        this.zabieg_id_p = new SimpleIntegerProperty(zabieg_id);
        this.nazwa_p = new SimpleStringProperty(nazwa);
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
            sql = "CREATE TABLE Zabieg " +
                    "(zabieg_id   INT PRIMARY KEY NOT NULL," +
                    "nazwa         VARCHAR NOT NULL," +
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

    public void update(){
        Statement stmt = null;
        try {
            this.zabieg_id_p = new SimpleIntegerProperty(zabieg_id);
            this.nazwa_p = new SimpleStringProperty(nazwa);
            this.oddzial_ref_p = new SimpleIntegerProperty(oddzial_ref);

            stmt = this.conn.createStatement();
            String sql;
            sql = "UPDATE Zabieg SET "
                    + "zabieg_id = " + this.zabieg_id + ", "
                    + "nazwa = '" + this.nazwa + "', "
                    + "oddzial_ref = " + this.oddzial_ref
                    + " WHERE zabieg_id = " + this.prev_zabieg_id
                    + ";";
            stmt.executeUpdate(sql);
            System.out.println("UPDATE zabieg");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String generate_insert_query(){
        return "(" + this.zabieg_id + ", '"
                + this.nazwa + "', "
                + this.oddzial_ref + ")";
    }

    public int getZabieg_id() {
        return zabieg_id;
    }

    public void setZabieg_id(int zabieg_id) {
        this.zabieg_id = zabieg_id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getOddzial_ref() {
        return oddzial_ref;
    }

    public void setOddzial_ref(int oddzial_ref) {
        this.oddzial_ref = oddzial_ref;
    }

    public int getZabieg_id_p() {
        return zabieg_id_p.get();
    }

    public IntegerProperty zabieg_id_pProperty() {
        return zabieg_id_p;
    }

    public void setZabieg_id_p(int zabieg_id_p) {
        this.zabieg_id_p.set(zabieg_id_p);
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

    public int getOddzial_ref_p() {
        return oddzial_ref_p.get();
    }

    public IntegerProperty oddzial_ref_pProperty() {
        return oddzial_ref_p;
    }

    public void setOddzial_ref_p(int oddzial_ref_p) {
        this.oddzial_ref_p.set(oddzial_ref_p);
    }

    public int getPrev_zabieg_id() {
        return prev_zabieg_id;
    }

    public void setPrev_zabieg_id(int prev_zabieg_id) {
        this.prev_zabieg_id = prev_zabieg_id;
    }
}
