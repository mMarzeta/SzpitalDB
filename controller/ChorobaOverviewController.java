package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import db.Choroba;
/**
 * Created by maciejmarzeta on 15.01.2017.
 */
public class ChorobaOverviewController {

    @FXML
    private TableView<Choroba> choroba_table;
    @FXML
    private TableColumn<Choroba, Integer> choroba_id_table;
    @FXML
    private TableColumn<Choroba, String> choroba_nazwa_table;

    @FXML
    private Label choroba_id_label;
    @FXML
    private Label nazwa_label;

    private Main mainApp;

    public ChorobaOverviewController() {
    }

    public void showChorobaDetails(Choroba choroba){
        if(choroba!=null){
            this.choroba_id_label.setText(Integer.toString(choroba.getChoroba_id()));
            this.nazwa_label.setText(choroba.getNazwa());;
        }
        else{
            this.choroba_id_label.setText("");
            this.choroba_nazwa_table.setText("");
        }
    }

    @FXML
    private void initialize(){
        System.out.println("initialize chorobaOvierwiecController");

        choroba_id_table.setCellValueFactory(
                cellData -> cellData.getValue().choroba_id_pProperty().asObject()
        );
        choroba_nazwa_table.setCellValueFactory(
                cellData -> cellData.getValue().nazwa_pProperty()
        );
        showChorobaDetails(null);

        choroba_table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showChorobaDetails(newValue)
        );
    }

    @FXML
    private void handleDeleteButton(){
        System.out.println("handleDeleteButton chorobaOverwievController");

        int selectedIndex = choroba_table.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            mainApp.getChorobaData().get(selectedIndex).delete(Integer.toString(
                    mainApp.getChorobaData().get(selectedIndex).getChoroba_id()));
            choroba_table.getItems().remove(selectedIndex);
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
        System.out.println("handleEditButton chorobaOvierwiecController");

        Choroba selectedchoroba = choroba_table.getSelectionModel().getSelectedItem();
        if(selectedchoroba != null){
            boolean onClicked = mainApp.showChorobaieEditDialog(selectedchoroba);
            if(onClicked){
                showChorobaDetails(selectedchoroba);
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
        System.out.println("handleOkButton AdresOvierwiecController");

        Choroba tempchoroba = new Choroba();
        boolean okClicked = mainApp.showChorobaieEditDialog(tempchoroba);
        if (okClicked) {
            mainApp.getChorobaData().add(new Choroba(tempchoroba.getChoroba_id(),
                    tempchoroba.getNazwa()));
        }

    }

    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;

        choroba_table.setItems(mainApp.getChorobaData());
    }
}
