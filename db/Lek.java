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
public class Lek extends Table_abstract {
    private int lek_id;
    private String nazwa;

    private IntegerProperty lek_id_p;
    private StringProperty nazwa_p;

    private int prev_lek_id;

    private static boolean isCreated;

    static {
        isCreated = false;
    }


    public Lek() {
        this.class_name = "Lek";
    }

    public Lek(int lek_id, String nazwa){
        this();
        this.lek_id = lek_id;
        this.nazwa = nazwa;

        this.lek_id_p = new SimpleIntegerProperty(lek_id);
        this.nazwa_p = new SimpleStringProperty(nazwa);

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
            sql = "CREATE TABLE Lek " +
                    "(lek_id   INT PRIMARY KEY NOT NULL," +
                    "nazwa          VARCHAR NOT NULL)";
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
                res += rs.getInt("lek_id") + " ";
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
            this.lek_id_p = new SimpleIntegerProperty(this.lek_id);
            this.nazwa_p = new SimpleStringProperty(this.nazwa);
            stmt = this.conn.createStatement();
            String sql;
            sql = "UPDATE Lek SET "
                    + "lek_id = " + this.lek_id + ", "
                    + "nazwa = '" + this.nazwa + "' "
                    + " WHERE lek_id = " + this.prev_lek_id
                    + ";";
            stmt.executeUpdate(sql);
            System.out.println("UPDATE lek");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    //moze byc problem bo uczulenie nie musi miec leku
    public String generate_insert_query(){
        return "(" + this.lek_id + ", '" + this.nazwa +"')";
    }

    public int getLek_id() {
        return lek_id;
    }

    public void setLek_id(int lek_id) {
        this.lek_id = lek_id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getLek_id_p() {
        return lek_id_p.get();
    }

    public IntegerProperty lek_id_pProperty() {
        return lek_id_p;
    }

    public void setLek_id_p(int lek_id_p) {
        this.lek_id_p.set(lek_id_p);
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

    public int getPrev_lek_id() {
        return prev_lek_id;
    }

    public void setPrev_lek_id(int prev_lek_id) {
        this.prev_lek_id = prev_lek_id;
    }
}
