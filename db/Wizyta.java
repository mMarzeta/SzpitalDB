package db;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by maciejmarzeta on 08.01.2017.
 */
public class Wizyta extends Table_abstract{
    private int wizyta_id;
    private String data_wizyty;
    //private int pacjent_ref;
    private int lek_ref;
    private int choroba_ref;
    private int zabieg_ref;
    private int lekarz_ref;

    public Wizyta() {
        this.class_name = "Wizyta";
        this.create();
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

    //moze byc problem bo uczulenie nie musi miec leku
    public String generate_insert_query(){
        return "(" + this.wizyta_id + ", '" + this.data_wizyty +"', "
                + this.lek_ref + ", " + this.choroba_ref + ", " + this.zabieg_ref + ", "
                + this.lekarz_ref + ")";
    }
}
