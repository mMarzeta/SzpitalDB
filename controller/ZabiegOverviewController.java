package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import db.Zabieg;
/**
 * Created by maciejmarzeta on 16.01.2017.
 */
public class ZabiegOverviewController {

    @FXML
    private TableView<Zabieg> zabieg_table;
    @FXML
    private TableColumn<Zabieg, Integer> zabieg_id_table;
    @FXML
    private TableColumn<Zabieg, String> zabieg_nazwa_table;

    @FXML
    private Label zabieg_id_label;
    @FXML
    private Label nazwa_label;
    @FXML
    private Label oddzial_ref_label;

    private Main mainApp;


    public ZabiegOverviewController(){

    }

    public void showZabiegDetails(Zabieg zabieg){
        System.out.println("showLekDetails LekOverwievController");
        if(zabieg!=null){
            this.zabieg_id_label.setText(Integer.toString(zabieg.getZabieg_id()));
            this.nazwa_label.setText(zabieg.getNazwa());;
            this.oddzial_ref_label.setText(Integer.toString(zabieg.getOddzial_ref()));
        }
        else{
            this.zabieg_id_label.setText("");
            this.nazwa_label.setText("");
            this.oddzial_ref_label.setText("");
        }
    }

    @FXML
    private void initialize(){
        System.out.println("initialize LekOvierwiecController");

        zabieg_id_table.setCellValueFactory(
                cellData -> cellData.getValue().zabieg_id_pProperty().asObject()
        );
        zabieg_nazwa_table.setCellValueFactory(
                cellData -> cellData.getValue().nazwa_pProperty()
        );
        showZabiegDetails(null);

        zabieg_table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showZabiegDetails(newValue)
        );
    }

    @FXML
    private void handleDeleteButton(){
        System.out.println("handleDeleteButton LekOverwievController");

        int selectedIndex = zabieg_table.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            mainApp.getZabiegData().get(selectedIndex).delete(Integer.toString(
                    mainApp.getZabiegData().get(selectedIndex).getZabieg_id()));
            zabieg_table.getItems().remove(selectedIndex);
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Zabieg SeIected");
            alert.setContentText("Please select a Lek in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditButton(){

        Zabieg selected = zabieg_table.getSelectionModel().getSelectedItem();
        if(selected != null){
            boolean onClicked = mainApp.showZabiegEditDialog(selected);
            if(onClicked){
                showZabiegDetails(selected);
            }
        }
        else{
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Zabieg Selected");
            alert.setContentText("Please select a Lek in the table.");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleOkButton(){

        Zabieg tmp = new Zabieg();
        boolean okClicked = mainApp.showZabiegEditDialog(tmp);
        if (okClicked) {
            mainApp.getZabiegData().add(new Zabieg(
                    tmp.getZabieg_id(),tmp.getNazwa(), tmp.getOddzial_ref()));
        }

    }

    public void setMainApp(Main mainApp){
        this.mainApp = mainApp;

        zabieg_table.setItems(mainApp.getZabiegData());
    }

}
