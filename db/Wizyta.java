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
public class Wizyta extends Table_abstract{
    private int wizyta_id;
    private String data_wizyty;
    private int lek_ref;
    private int choroba_ref;
    private int zabieg_ref;
    private int lekarz_ref;

    private IntegerProperty wizyta_id_p;
    private StringProperty data_wizyty_p;
    private IntegerProperty lek_ref_p;
    private IntegerProperty choroba_ref_p;
    private IntegerProperty zabieg_ref_p;
    private IntegerProperty lekarz_ref_p;

    private int prev_wizyta_id;

    private static boolean isCreated;
    static {
        isCreated = false;
    }

    public Wizyta() {
        this.class_name = "Wizyta";
        //this.create();
    }

    public Wizyta(int wizyta_id, String data_wizyty, int lek_ref,
               int choroba_ref, int zabieg_ref, int lekarz_ref){
        this();
        this.wizyta_id = wizyta_id;
        this.data_wizyty = data_wizyty;
        //this.pacjent_ref = pacjent_ref;
        this.lek_ref = lek_ref;
        this.choroba_ref = choroba_ref;
        this.zabieg_ref = zabieg_ref;
        this.lekarz_ref = lekarz_ref;

        this.wizyta_id_p = new SimpleIntegerProperty(wizyta_id);
        this.data_wizyty_p = new SimpleStringProperty(data_wizyty);
        this.lek_ref_p = new SimpleIntegerProperty(lek_ref);
        this.choroba_ref_p = new SimpleIntegerProperty(choroba_ref);
        this.zabieg_ref_p = new SimpleIntegerProperty(zabieg_ref);
        this.lek_ref_p = new SimpleIntegerProperty(lek_ref);

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
            sql = "CREATE TABLE Wizyta " +
                    "(wizyta_id   INT PRIMARY KEY NOT NULL," +
                    "data_wizyty          VARCHAR NOT NULL," + //albo varchar
                    "lek_ref         INT REFERENCES lek(lek_id)," +
                    "choroba_ref         INT REFERENCES choroba(choroba_id)," +
                    "zabieg_ref         INT REFERENCES zabieg(zabieg_id)," +
                    "lekarz_ref         INT REFERENCES lekarz(lekarz_id));";

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
                res += rs.getInt("wizyta_id") + " ";
                res += rs.getString("data_wizyty") + " ";
                res += rs.getInt("lek_ref") + " ";
                res += rs.getInt("choroba_ref") + " ";
                res += rs.getInt("zabieg_ref") + " ";
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
            this.wizyta_id_p = new SimpleIntegerProperty(wizyta_id);
            this.data_wizyty_p = new SimpleStringProperty(data_wizyty);
            this.lek_ref_p = new SimpleIntegerProperty(lek_ref);
            this.choroba_ref_p = new SimpleIntegerProperty(choroba_ref);
            this.zabieg_ref_p = new SimpleIntegerProperty(zabieg_ref);
            this.lekarz_ref_p = new SimpleIntegerProperty(lekarz_ref);

            stmt = this.conn.createStatement();
            String sql;
            sql = "UPDATE Wizyta SET "
                    + "wizyta_id = " + this.wizyta_id + ", "
                    + "data_wizyty = '" + this.data_wizyty + "', "
                    + "lek_ref = " + this.lek_ref + ", "
                    + "choroba_ref = " + this.choroba_ref+ ", "
                    + "zabieg_ref = " + this.zabieg_ref+ ", "
                    + "lekarz_ref = " + this.lekarz_ref
                    + " WHERE wizyta_id = " + this.prev_wizyta_id
                    + ";";
            stmt.executeUpdate(sql);
            System.out.println("UPDATE wizyta");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    //moze byc problem bo uczulenie nie musi miec leku
    public String generate_insert_query(){
        return "(" + this.wizyta_id + ", '" + this.data_wizyty +"', "
                + this.lek_ref + ", " + this.choroba_ref + ", " + this.zabieg_ref + ", "
                + this.lekarz_ref + ")";
    }

    public int getWizyta_id() {
        return wizyta_id;
    }

    public void setWizyta_id(int wizyta_id) {
        this.wizyta_id = wizyta_id;
    }

    public String getData_wizyty() {
        return data_wizyty;
    }

    public void setData_wizyty(String data_wizyty) {
        this.data_wizyty = data_wizyty;
    }

    public int getLek_ref() {
        return lek_ref;
    }

    public void setLek_ref(int lek_ref) {
        this.lek_ref = lek_ref;
    }

    public int getChoroba_ref() {
        return choroba_ref;
    }

    public void setChoroba_ref(int choroba_ref) {
        this.choroba_ref = choroba_ref;
    }

    public int getZabieg_ref() {
        return zabieg_ref;
    }

    public void setZabieg_ref(int zabieg_ref) {
        this.zabieg_ref = zabieg_ref;
    }

    public int getLekarz_ref() {
        return lekarz_ref;
    }

    public void setLekarz_ref(int lekarz_ref) {
        this.lekarz_ref = lekarz_ref;
    }

    public int getWizyta_id_p() {
        return wizyta_id_p.get();
    }

    public IntegerProperty wizyta_id_pProperty() {
        return wizyta_id_p;
    }

    public void setWizyta_id_p(int wizyta_id_p) {
        this.wizyta_id_p.set(wizyta_id_p);
    }

    public String getData_wizyty_p() {
        return data_wizyty_p.get();
    }

    public StringProperty data_wizyty_pProperty() {
        return data_wizyty_p;
    }

    public void setData_wizyty_p(String data_wizyty_p) {
        this.data_wizyty_p.set(data_wizyty_p);
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

    public int getChoroba_ref_p() {
        return choroba_ref_p.get();
    }

    public IntegerProperty choroba_ref_pProperty() {
        return choroba_ref_p;
    }

    public void setChoroba_ref_p(int choroba_ref_p) {
        this.choroba_ref_p.set(choroba_ref_p);
    }

    public int getZabieg_ref_p() {
        return zabieg_ref_p.get();
    }

    public IntegerProperty zabieg_ref_pProperty() {
        return zabieg_ref_p;
    }

    public void setZabieg_ref_p(int zabieg_ref_p) {
        this.zabieg_ref_p.set(zabieg_ref_p);
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

    public int getPrev_wizyta_id() {
        return prev_wizyta_id;
    }

    public void setPrev_wizyta_id(int prev_wizyta_id) {
        this.prev_wizyta_id = prev_wizyta_id;
    }
}
