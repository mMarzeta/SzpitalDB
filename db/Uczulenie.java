package db;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by maciejmarzeta on 08.01.2017.
 */
public class Uczulenie extends Table_abstract {
    private int uczulenie_id;
    private String nazwa;
    private int lek_ref;

    private IntegerProperty uczulenie_id_p;
    private StringProperty nazwa_p;
    private IntegerProperty lek_ref_p;

    private int prev_uczulenie_id;

    private static boolean isCreated;

    static {
        isCreated = false;
    }

    public Uczulenie() {
        this.class_name = "Uczulenie";
        //this.create();
    }

    public Uczulenie(int uczulenie_id, String nazwa, int lek_id){
        this();
        this.uczulenie_id = uczulenie_id;
        this.nazwa = nazwa;
        this.lek_ref = lek_id;

        this.uczulenie_id_p = new SimpleIntegerProperty(uczulenie_id);
        this.nazwa_p = new SimpleStringProperty(nazwa);
        this.lek_ref_p = new SimpleIntegerProperty(lek_id);

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
            sql = "CREATE TABLE Uczulenie " +
                    "(uczulenie_id   INT PRIMARY KEY NOT NULL," +
                    "nazwa          VARCHAR NOT NULL," +
                    "lek_ref         INT REFERENCES lek(lek_id));";
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
                res += rs.getInt("uczulenie_id") + " ";
                res += rs.getString("nazwa") + " ";
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
            this.uczulenie_id_p = new SimpleIntegerProperty(this.uczulenie_id);
            this.nazwa_p = new SimpleStringProperty(this.nazwa);
            this.lek_ref_p = new SimpleIntegerProperty(this.lek_ref);
            stmt = this.conn.createStatement();
            String sql;
            sql = "UPDATE Uczulenie SET "
                    + "uczulenie_id = " + this.uczulenie_id + ", "
                    + "nazwa = '" + this.nazwa + "', "
                    + "lek_ref = " + this.lek_ref
                    + " WHERE uczulenie_id = " + this.prev_uczulenie_id
                    + ";";
            stmt.executeUpdate(sql);
            System.out.println("UPDATE lek");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    //moze byc problem bo lek nie musi miec uczulenia
    public String generate_insert_query(){
        return "(" + this.uczulenie_id + ", '" + this.nazwa +"', " + this.lek_ref +")";
    }

    public int getUczulenie_id() {
        return uczulenie_id;
    }

    public void setUczulenie_id(int uczulenie_id) {
        this.uczulenie_id = uczulenie_id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getLek_ref() {
        return lek_ref;
    }

    public void setLek_ref(int lek_ref) {
        this.lek_ref = lek_ref;
    }

    public int getUczulenie_id_p() {
        return uczulenie_id_p.get();
    }

    public IntegerProperty uczulenie_id_pProperty() {
        return uczulenie_id_p;
    }

    public void setUczulenie_id_p(int uczulenie_id_p) {
        this.uczulenie_id_p.set(uczulenie_id_p);
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

    public int getLek_ref_p() {
        return lek_ref_p.get();
    }

    public IntegerProperty lek_ref_pProperty() {
        return lek_ref_p;
    }

    public void setLek_ref_p(int lek_ref_p) {
        this.lek_ref_p.set(lek_ref_p);
    }

    public int getPrev_uczulenie_id() {
        return prev_uczulenie_id;
    }

    public void setPrev_uczulenie_id(int prev_uczulenie_id) {
        this.prev_uczulenie_id = prev_uczulenie_id;
    }
}
