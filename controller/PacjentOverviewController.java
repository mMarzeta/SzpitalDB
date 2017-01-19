package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import db.Pacjent;
/**
 * Created by maciejmarzeta on 16.01.2017.
 */
public class PacjentOverviewController {
    @FXML
    private TableView<Pacjent> pacjent_table;
    @FXML
    private TableColumn<Pacjent, Integer> pacjent_id_table;
    @FXML
    private TableColumn<Pacjent, String> pacjent_nazwisko_table;

    @FXML
    private Label pacjent_id_Label;
    @FXML
    private Label imie_Label;
    @FXML
    private Label nazwisko_Label;
    @FXML
    private Label data_urodzenia_Label;
    @FXML
    private Label numer_telefonu_Label;
    @FXML
    private Label plec_ref_Label;
    @FXML
    private Label uczulenie_ref_Label;
    @FXML
    private Label adres_ref_Label;
    @FXML
    private Label wizyta_ref_Label;

    private Main mainApp;

    public PacjentOverviewController() {

    }

    public void showPacjentDetails(Pacjent pacjent){
        if(pacjent!=null){
            this.pacjent_id_Label.setText(Integer.toString(pacjent.getPacjent_id()));
            this.imie_Label.setText(pacjent.getImie());
            this.nazwisko_Label.setText(pacjent.getNazwisko());
            this.data_urodzenia_Label.setText(pacjent.getData_urodzenia());
            this.numer_telefonu_Label.setText(pacjent.getNumer_telefonu());
            this.plec_ref_Label.setText(Integer.toString(pacjent.getPlec_ref()));
            this.uczulenie_ref_Label.setText(Integer.toString(pacjent.getUczulenie_ref()));
            this.adres_ref_Label.setText(Integer.toString(pacjent.getAdres_ref()));
            this.wizyta_ref_Label.setText(Integer.toString(pacjent.getWizyta_ref()));
        }
        else{
            this.pacjent_id_Label.setText("");
            this.imie_Label.setText("");
            this.nazwisko_Label.setText("");
            this.data_urodzenia_Label.setText("");
            this.numer_telefonu_Label.setText("");
            this.plec_ref_Label.setText("");
            this.uczulenie_ref_Label.setText("");
            this.adres_ref_Label.setText("");
            this.wizyta_ref_Label.setText("");
        }
    }

    @FXML
    private void initialize(){
        pacjent_id_table.setCellValueFactory(
                cellData -> cellData.getValue().pacjent_id_pProperty().asObject()
        );
        pacjent_nazwisko_table.setCellValueFactory(
                cellData -> cellData.getValue().nazwisko_pProperty()
        );
        showPacjentDetails(null);

        pacjent_table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPacjentDetails(newValue)
        );
    }

    @FXML
    private void handleDeleteButton(){
        System.out.println("handleDeleteButton LekOverwievController");

        int selectedIndex = pacjent_table.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            mainApp.getPacjentData().get(selectedIndex).delete(Integer.toString(
                    mainApp.getPacjentData().get(selectedIndex).getPacjent_id()));
            pacjent_table.getItems().remove(selectedIndex);
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Pacjent SeIected");
            alert.setContentText("Please select a Lek in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditButton(){

        Pacjent selected = pacjent_table.getSelectionModel().getSelectedItem();
        if(selected != null){
            boolean onClicked = mainApp.showPacjentEditDialog(selected);
            if(onClicked){
                showPacjentDetails(selected);
            }
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Pacjent Selected");
            alert.setContentText("Please select a Lek in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleOkButton(){

        Pacjent tmp = new Pacjent();
        boolean okClicked = mainApp.showPacjentEditDialog(tmp);
        if (okClicked) {
            mainApp.getPacjentData().add(new Pacjent(
                    tmp.getPacjent_id(),tmp.getImie(), tmp.getNazwisko(),
                    tmp.getData_urodzenia(), tmp.getNumer_telefonu(),
                    tmp.getPlec_ref(), tmp.getUczulenie_ref(),
                    tmp.getAdres_ref(), tmp.getWizyta_ref()));
        }

    }

    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;

        pacjent_table.setItems(mainApp.getPacjentData());
    }

}
