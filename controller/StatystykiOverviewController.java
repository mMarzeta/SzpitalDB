package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import db.Statystyki;
/**
 * Created by maciejmarzeta on 16.01.2017.
 */
public class StatystykiOverviewController {
    @FXML
    private Label count_odzial;
    @FXML
    private Label count_lekarze;
    @FXML
    private Label count_pacjent;

    public StatystykiOverviewController(){
    }

    @FXML
    private void initialize(){
        Statystyki tmp = new Statystyki();
        this.count_odzial.setText(Integer.toString(
                tmp.agregacja("oddzial_id", "oddzial")));
        this.count_lekarze.setText(Integer.toString(
                tmp.agregacja("lekarz_id", "lekarz")));
        this.count_pacjent.setText(Integer.toString(
                tmp.agregacja("pacjent_id", "pacjent")));
    }
}
