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
public class Choroba extends Table_abstract {
    private int choroba_id;
    private String nazwa;

    private IntegerProperty choroba_id_p;
    private StringProperty nazwa_p;

    private int prev_choroba_id;

    private static boolean isCreated;
    static {
        isCreated = false;
    }

    public Choroba(){
        this.class_name = "Choroba";
    }

    public Choroba(int choroba_id, String nazwa) {
        this();
        this.choroba_id = choroba_id;
        this.nazwa = nazwa;

        this.choroba_id_p = new SimpleIntegerProperty(choroba_id);
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
            sql = "CREATE TABLE Choroba " +
                    "(choroba_id   INT PRIMARY KEY NOT NULL," +
                    "nazwa          VARCHAR NOT NULL);";

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
                res += rs.getInt("choroba_id") + " ";
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
            this.choroba_id_p = new SimpleIntegerProperty(this.choroba_id);
            this.nazwa_p = new SimpleStringProperty(this.nazwa);
            stmt = this.conn.createStatement();
            String sql;
            sql = "UPDATE Choroba SET "
                    + "choroba_id = " + this.choroba_id + ", "
                    + "nazwa = '" + this.nazwa + "' "
                    + " WHERE choroba_id = " + this.prev_choroba_id
                    + ";";
            stmt.executeUpdate(sql);
            System.out.println("UPDATE choroba");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String generate_insert_query(){
        return "(" + this.choroba_id + ",' " + this.nazwa +"')";
    }

    public int getChoroba_id() {
        return choroba_id;
    }

    public void setChoroba_id(int choroba_id) {
        this.choroba_id = choroba_id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getChoroba_id_p() {
        return choroba_id_p.get();
    }

    public IntegerProperty choroba_id_pProperty() {
        return choroba_id_p;
    }

    public void setChoroba_id_p(int choroba_id_p) {
        this.choroba_id_p.set(choroba_id_p);
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

    public int getPrev_choroba_id() {
        return prev_choroba_id;
    }

    public void setPrev_choroba_id(int prev_choroba_id) {
        this.prev_choroba_id = prev_choroba_id;
    }
}
