package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import db.Uczulenie;
/**
 * Created by maciejmarzeta on 16.01.2017.
 */
public class UczulenieOverviewController {

    @FXML
    private TableView<Uczulenie> uczulenie_table;
    @FXML
    private TableColumn<Uczulenie, Integer> uczulenie_id_table;
    @FXML
    private TableColumn<Uczulenie, String> uczulenie_nazwa_table;

    @FXML
    private Label uczulenie_id_label;
    @FXML
    private Label nazwa_label;
    @FXML
    private Label lek_ref_label;

    private Main mainApp;

    public UczulenieOverviewController() {
    }

    public void showUczulenieDetails(Uczulenie uczulenie){
        if(uczulenie!=null){
            this.uczulenie_id_label.setText(Integer.toString(uczulenie.getUczulenie_id()));
            this.nazwa_label.setText(uczulenie.getNazwa());;
            this.lek_ref_label.setText(Integer.toString(uczulenie.getLek_ref()));
        }
        else{
            this.uczulenie_id_label.setText("");
            this.nazwa_label.setText("");
            this.lek_ref_label.setText("");
        }
    }

    @FXML
    private void initialize(){
        uczulenie_id_table.setCellValueFactory(
                cellData -> cellData.getValue().uczulenie_id_pProperty().asObject()
        );
        uczulenie_nazwa_table.setCellValueFactory(
                cellData -> cellData.getValue().nazwa_pProperty()
        );
        showUczulenieDetails(null);

        uczulenie_table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showUczulenieDetails(newValue)
        );
    }

    @FXML
    private void handleDeleteButton(){
        int selectedIndex = uczulenie_table.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            mainApp.getUczulenieData().get(selectedIndex).delete(Integer.toString(
                    mainApp.getUczulenieData().get(selectedIndex).getUczulenie_id()));
            uczulenie_table.getItems().remove(selectedIndex);
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No uczulenie SeIected");
            alert.setContentText("Please select a choroba in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditButton(){
        Uczulenie selected = uczulenie_table.getSelectionModel().getSelectedItem();
        if(selected != null){
            boolean onClicked = mainApp.showUczulenieEditDialog(selected);
            if(onClicked){
                showUczulenieDetails(selected);
            }
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No uczulenie Selected");
            alert.setContentText("Please select a choroba in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleOkButton(){
        Uczulenie tmp = new Uczulenie();
        boolean okClicked = mainApp.showUczulenieEditDialog(tmp);
        if (okClicked) {
            mainApp.getUczulenieData().add(new Uczulenie(tmp.getUczulenie_id(),
                    tmp.getNazwa(), tmp.getLek_ref()));
        }
    }

    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;

        uczulenie_table.setItems(mainApp.getUczulenieData());
    }
}
