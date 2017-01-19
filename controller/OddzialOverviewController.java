package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import db.Oddzial;
/**
 * Created by maciejmarzeta on 16.01.2017.
 */
public class OddzialOverviewController {

    @FXML
    private TableView<Oddzial> oddzial_table;
    @FXML
    private TableColumn<Oddzial, Integer> oddzial_id_table;
    @FXML
    private TableColumn<Oddzial, String> oddzial_nazwa_table;

    @FXML
    private Label oddzial_id_label;
    @FXML
    private Label nazwa_label;
    @FXML
    private Label lekarz_ref_label;

    private Main mainApp;

    public OddzialOverviewController() {
    }

    public void showOddzialDetails(Oddzial oddzial){
        if(oddzial!=null){
            this.oddzial_id_label.setText(Integer.toString(oddzial.getOddzial_id()));
            this.nazwa_label.setText(oddzial.getNazwa());;
            this.lekarz_ref_label.setText(Integer.toString(oddzial.getLekarz_ref()));
        }
        else{
            this.oddzial_id_label.setText("");
            this.nazwa_label.setText("");
            this.lekarz_ref_label.setText("");
        }
    }

    @FXML
    private void initialize(){
        oddzial_id_table.setCellValueFactory(
                cellData -> cellData.getValue().oddzial_id_pProperty().asObject()
        );
        oddzial_nazwa_table.setCellValueFactory(
                cellData -> cellData.getValue().nazwa_pProperty()
        );
        showOddzialDetails(null);

        oddzial_table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showOddzialDetails(newValue)
        );
    }

    @FXML
    private void handleDeleteButton(){
        int selectedIndex = oddzial_table.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            mainApp.getOddzialData().get(selectedIndex).delete(Integer.toString(
                    mainApp.getOddzialData().get(selectedIndex).getOddzial_id()));
            oddzial_table.getItems().remove(selectedIndex);
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No choroba SeIected");
            alert.setContentText("Please select a choroba in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditButton(){
        Oddzial selectedoddzial = oddzial_table.getSelectionModel().getSelectedItem();
        if(selectedoddzial != null){
            boolean onClicked = mainApp.showOddzialEditDialog(selectedoddzial);
            if(onClicked){
                showOddzialDetails(selectedoddzial);
            }
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No choroba Selected");
            alert.setContentText("Please select a choroba in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleOkButton(){
        Oddzial tmp = new Oddzial();
        boolean okClicked = mainApp.showOddzialEditDialog(tmp);
        if (okClicked) {
            mainApp.getOddzialData().add(new Oddzial(tmp.getOddzial_id(),
                    tmp.getNazwa(), tmp.getLekarz_ref()));
        }
    }

    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;

        oddzial_table.setItems(mainApp.getOddzialData());
    }
}
