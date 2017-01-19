package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by maciejmarzeta on 07.01.2017.
 */
public abstract class Table_abstract {
    protected Connection conn;
    protected String class_name;


    public Table_abstract() {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/szpital",
                    "maciejmarzeta", "123");
            System.out.println("Database opened succesfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void disconnect(){
        try{
            this.conn.close();
            System.out.println("Baza zostala zamknieta pomyslnie");
        }
        catch(Exception e){
            System.out.println("Nie udalo sie zamknac bazy");
        }
    }

    public void drop(){
        Statement stmt = null;
        try{
            stmt = this.conn.createStatement();
            this.conn.setAutoCommit(false);
            String sql = "DROP TABLE " + this.class_name + " CASCADE";
            stmt.executeUpdate(sql);
            this.conn.commit();
            stmt.close();
            this.conn.setAutoCommit(true);
            System.out.println(this.class_name + " dropped succesfully");
        }
        catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    abstract protected void create();

    abstract public String select();

    public void insert(String query){
        Statement stmt = null;
        try{
            stmt = this.conn.createStatement();
            this.conn.setAutoCommit(false);
            String sql = "INSERT INTO " + this.class_name + " VALUES" + query + ";";
            stmt.executeUpdate(sql);
            stmt.close();
            this.conn.commit();
            this.conn.setAutoCommit(true);
            System.out.println(query + " zostalo dodane do " + this.class_name);
        }
         catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void delete(String query){
        Statement stmt = null;
        try{
            stmt = this.conn.createStatement();
            this.conn.setAutoCommit(false);
            String sql = "DELETE FROM " + this.class_name + " WHERE "
                        +this.class_name + "_id = " + query +";";
            stmt.executeUpdate(sql);
            stmt.close();
            this.conn.commit();
            this.conn.setAutoCommit(true);
            System.out.println("USUNIETO REKORD");
        }
        catch(Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String toString(){
        return this.class_name;
    }
}
