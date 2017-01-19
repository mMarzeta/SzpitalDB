package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import db.Szpital;
/**
 * Created by maciejmarzeta on 16.01.2017.
 */
public class SzpitalOverviewController {
    @FXML
    private TableView<Szpital> szpital_table;
    @FXML
    private TableColumn<Szpital, Integer> szpital_id_table;
    @FXML
    private TableColumn<Szpital, String> szpital_nazwa_table;

    @FXML
    private Label szpital_id_label;
    @FXML
    private Label nazwa_label;
    @FXML
    private Label adres_ref_label;
    @FXML
    private Label oddzial_ref_label;

    private Main mainApp;


    public SzpitalOverviewController(){

    }

    public void showSzpitalDetails(Szpital szpital){
        System.out.println("showLekDetails LekOverwievController");
        if(szpital!=null){
            this.szpital_id_label.setText(Integer.toString(szpital.getSzpital_id()));
            this.nazwa_label.setText(szpital.getNazwa());;
            this.adres_ref_label.setText(Integer.toString(szpital.getAdres_ref()));
            this.oddzial_ref_label.setText(Integer.toString(szpital.getOddzial_ref()));
        }
        else{
            this.szpital_id_label.setText("");
            this.nazwa_label.setText("");
            this.adres_ref_label.setText("");
            this.oddzial_ref_label.setText("");
        }
    }

    @FXML
    private void initialize(){

        szpital_id_table.setCellValueFactory(
                cellData -> cellData.getValue().szpital_id_pProperty().asObject()
        );
        szpital_nazwa_table.setCellValueFactory(
                cellData -> cellData.getValue().nazwa_pProperty()
        );
        showSzpitalDetails(null);

        szpital_table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSzpitalDetails(newValue)
        );
    }

    @FXML
    private void handleDeleteButton(){

        int selectedIndex = szpital_table.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            mainApp.getSzpitalData().get(selectedIndex).delete(Integer.toString(
                    mainApp.getSzpitalData().get(selectedIndex).getSzpital_id()));
            szpital_table.getItems().remove(selectedIndex);
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Szpital SeIected");
            alert.setContentText("Please select a Lek in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditButton(){

        Szpital selected = szpital_table.getSelectionModel().getSelectedItem();
        if(selected != null){
            boolean onClicked = mainApp.showSzpitalEditDialog(selected);
            if(onClicked){
                showSzpitalDetails(selected);
            }
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Szpital Selected");
            alert.setContentText("Please select a Lek in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleOkButton(){

        Szpital tmp = new Szpital();
        boolean okClicked = mainApp.showSzpitalEditDialog(tmp);
        if (okClicked) {
            mainApp.getSzpitalData().add(new Szpital(
                    tmp.getSzpital_id(),tmp.getNazwa(), tmp.getAdres_ref(),
                    tmp.getOddzial_ref()));
        }

    }

    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;

        szpital_table.setItems(mainApp.getSzpitalData());
    }

}

