package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import db.Lek;

/**
 * Created by maciejmarzeta on 15.01.2017.
 */
public class LekOverviewController {

    @FXML
    private TableView<Lek> lek_table;
    @FXML
    private TableColumn<Lek, Integer> lek_id_table;
    @FXML
    private TableColumn<Lek, String> lek_nazwa_table;

    @FXML
    private Label lek_id_label;
    @FXML
    private Label nazwa_label;

    private Main mainApp;

    public LekOverviewController() {
    }

    public void showLekDetails(Lek lek){
        System.out.println("showLekDetails LekOverwievController");
        if(lek!=null){
            this.lek_id_label.setText(Integer.toString(lek.getLek_id()));
            this.nazwa_label.setText(lek.getNazwa());;
        }
        else{
            this.lek_id_label.setText("");
            this.lek_nazwa_table.setText("");
        }
    }

    @FXML
    private void initialize(){
        System.out.println("initialize LekOvierwiecController");

        lek_id_table.setCellValueFactory(
                cellData -> cellData.getValue().lek_id_pProperty().asObject()
        );
        lek_nazwa_table.setCellValueFactory(
                cellData -> cellData.getValue().nazwa_pProperty()
        );
        showLekDetails(null);

        lek_table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showLekDetails(newValue)
        );
    }

    @FXML
    private void handleDeleteButton(){
        System.out.println("handleDeleteButton LekOverwievController");

        int selectedIndex = lek_table.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            mainApp.getLekData().get(selectedIndex).delete(Integer.toString(
                    mainApp.getLekData().get(selectedIndex).getLek_id()));
            lek_table.getItems().remove(selectedIndex);
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Lek SeIected");
            alert.setContentText("Please select a Lek in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditButton(){
        System.out.println("handleEditButton LekOvierwiecController");

        Lek selectedLek = lek_table.getSelectionModel().getSelectedItem();
        if(selectedLek != null){
            boolean onClicked = mainApp.showLekEditDialog(selectedLek);
            if(onClicked){
                showLekDetails(selectedLek);
            }
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Lek Selected");
            alert.setContentText("Please select a Lek in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleOkButton(){
        System.out.println("handleOkButton AdresOvierwiecController");

        Lek tempLek = new Lek();
        boolean okClicked = mainApp.showLekEditDialog(tempLek);
        if (okClicked) {
            mainApp.getLekData().add(new Lek(tempLek.getLek_id(),tempLek.getNazwa()));
        }

    }

    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;

        lek_table.setItems(mainApp.getLekData());
    }
}
