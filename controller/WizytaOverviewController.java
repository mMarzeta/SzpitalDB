package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import db.Wizyta;
/**
 * Created by maciejmarzeta on 16.01.2017.
 */
public class WizytaOverviewController {

    @FXML
    private TableView<Wizyta> wizyta_table;
    @FXML
    private TableColumn<Wizyta, Integer> wizyta_id_table;
    @FXML
    private TableColumn<Wizyta, String> wizyta_data;

    @FXML
    private Label wizyta_id_label;
    @FXML
    private Label data_wizyty_label;
    @FXML
    private Label lek_ref_label;
    @FXML
    private Label choroba_ref_label;
    @FXML
    private Label zabieg_ref_label;
    @FXML
    private Label lekarz_ref_label;

    private Main mainApp;

    public WizytaOverviewController(){

    }

    public void showWizytaDetails(Wizyta wizyta){
        if(wizyta!=null){
            this.wizyta_id_label.setText(Integer.toString(wizyta.getWizyta_id()));
            this.data_wizyty_label.setText(wizyta.getData_wizyty());;
            this.lek_ref_label.setText(Integer.toString(wizyta.getLek_ref()));
            this.choroba_ref_label.setText(Integer.toString(wizyta.getChoroba_ref()));
            this.zabieg_ref_label.setText(Integer.toString(wizyta.getZabieg_ref()));
            this.lekarz_ref_label.setText(Integer.toString(wizyta.getLekarz_ref()));

        }
        else{
            this.wizyta_id_label.setText("");
            this.data_wizyty_label.setText("");
            this.lek_ref_label.setText("");
            this.choroba_ref_label.setText("");
            this.zabieg_ref_label.setText("");
            this.lek_ref_label.setText("");
        }
    }

    @FXML
    private void initialize(){

        wizyta_id_table.setCellValueFactory(
                cellData -> cellData.getValue().wizyta_id_pProperty().asObject()
        );
        wizyta_data.setCellValueFactory(
                cellData -> cellData.getValue().data_wizyty_pProperty()
        );
        showWizytaDetails(null);

        wizyta_table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showWizytaDetails(newValue)
        );
    }

    @FXML
    private void handleDeleteButton(){
        System.out.println("handleDeleteButton LekOverwievController");

        int selectedIndex = wizyta_table.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            mainApp.getWizytaData().get(selectedIndex).delete(Integer.toString(
                    mainApp.getWizytaData().get(selectedIndex).getWizyta_id()));
            wizyta_table.getItems().remove(selectedIndex);
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Wizyta SeIected");
            alert.setContentText("Please select a Lek in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditButton(){

        Wizyta selected = wizyta_table.getSelectionModel().getSelectedItem();
        if(selected != null){
            boolean onClicked = mainApp.showWizytaEditDialog(selected);
            if(onClicked){
                showWizytaDetails(selected);
            }
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Wizyta Selected");
            alert.setContentText("Please select a Lek in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleOkButton(){

        Wizyta tmp = new Wizyta();
        boolean okClicked = mainApp.showWizytaEditDialog(tmp);
        if (okClicked) {
            mainApp.getWizytaData().add(new Wizyta(
                    tmp.getWizyta_id(),tmp.getData_wizyty(), tmp.getLek_ref(),
                    tmp.getChoroba_ref(), tmp.getZabieg_ref(), tmp.getLekarz_ref()));
        }

    }

    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;

        wizyta_table.setItems(mainApp.getWizytaData());
    }
}
