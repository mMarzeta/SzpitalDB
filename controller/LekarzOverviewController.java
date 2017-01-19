package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import db.Lekarz;

/**
 * Created by maciejmarzeta on 15.01.2017.
 */
public class LekarzOverviewController {
    @FXML
    private TableView<Lekarz> lekarz_table;
    @FXML
    private TableColumn<Lekarz, Integer> lekarz_id_table;
    @FXML
    private TableColumn<Lekarz, String> nazwisko_table;

    @FXML
    private Label lekarz_id_label;
    @FXML
    private Label imie_label;
    @FXML
    private Label nazwisko_label;
    @FXML
    private Label numer_telefonu_label;
    @FXML
    private Label adres_ref_label;
    @FXML
    private Label plec_ref_label;


    private Main mainApp;

    public LekarzOverviewController() {
    }

    public void showLekarzDetails(Lekarz l){
        System.out.println("showLekarzDetails LekarzvierwiecController");
        if (l != null) {
            this.lekarz_id_label.setText((Integer.toString(l.getLekarz_id())));
            this.imie_label.setText(l.getImie());
            this.nazwisko_label.setText(l.getNazwisko());
            this.numer_telefonu_label.setText(l.getNumer_telefonu());
            this.adres_ref_label.setText(Integer.toString(l.getAdres_ref()));
            this.plec_ref_label.setText(Integer.toString(l.getPlec_ref()));
        }
        else{
            this.lekarz_id_label.setText("");
            this.imie_label.setText("");
            this.nazwisko_label.setText("");
            this.numer_telefonu_label.setText("");
            this.adres_ref_label.setText("");
            this.adres_ref_label.setText("");
            this.plec_ref_label.setText("");
        }
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        System.out.println("initialize LekarzOvierwiecController");

        // Initialize the person table with the two columns.
        lekarz_id_table.setCellValueFactory(
                cellData -> cellData.getValue().lekarz_id_pProperty().asObject());
        nazwisko_table.setCellValueFactory(
                cellData -> cellData.getValue().nazwisko_pProperty());

        //clear
        showLekarzDetails(null);

        lekarz_table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showLekarzDetails(newValue)
        );
    }

    @FXML
    private void handleDeleteButton(){
        System.out.println("handleDeleteButton LekarzOverview");

        int selectedIndex = lekarz_table.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            mainApp.getLekData().get(selectedIndex).delete(Integer.toString(
                    mainApp.getLekarzData().get(selectedIndex).getLekarz_id()));

            lekarz_table.getItems().remove(selectedIndex);
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Adres SeIected");
            alert.setContentText("Please select a adres in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditButton(){
        System.out.println("handleEditButton LekarzOvierwiecController");

        Lekarz selectedLekarz = lekarz_table.getSelectionModel().getSelectedItem();
        if(selectedLekarz != null){
            boolean okClicked = mainApp.showLekarzEditDialog(selectedLekarz);
            //mainApp.getAdresData().add(null);
            if(okClicked){
                showLekarzDetails(selectedLekarz);
            }
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Adres Selected");
            alert.setContentText("Please select a adres in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleOkButton() {
        System.out.println("handleOkButton AdresOvierwiecController");

        Lekarz tempLekarz = new Lekarz();
        boolean okClicked = mainApp.showLekarzEditDialog(tempLekarz);
        if (okClicked) {
            mainApp.getLekarzData().add(new Lekarz(tempLekarz.getLekarz_id(),
                    tempLekarz.getImie(), tempLekarz.getNazwisko(), tempLekarz.getNumer_telefonu(),
                    tempLekarz.getAdres_ref(), tempLekarz.getPlec_ref()));
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        System.out.println("setMainApp LekarzOvierwiecController");

        this.mainApp = mainApp;

        // Add observable list data to the table
        lekarz_table.setItems(mainApp.getLekarzData());
    }

}
