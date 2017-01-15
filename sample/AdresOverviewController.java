package view; /**
 * Created by maciejmarzeta on 11.01.2017.
 */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import sample.Main;
import db.Adres;

public class AdresOverviewController {

    @FXML
    private TableView<Adres> adres_table;
    @FXML
    private TableColumn<Adres, Integer> adres_id_table;
    @FXML
    private TableColumn<Adres, String> wojewodztwo_table;

    @FXML
    private Label adres_id_label;
    @FXML
    private Label wojewodztwo_label;
    @FXML
    private Label miejscowosc_label;
    @FXML
    private Label ulica_label;
    @FXML
    private Label kraj_label;
    @FXML
    private Label kod_pocztowy_label;

    // Reference to the main application.
    private Main mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public AdresOverviewController() {
    }

    public void showAdresDetails(Adres ad){
        System.out.println("showAdresDetails AdresOvierwiecController");
        if (ad != null){
            this.adres_id_label.setText((Integer.toString(ad.getAdres_id())));
            this.wojewodztwo_label.setText(ad.getWojewodztwo());
            this.miejscowosc_label.setText(ad.getMiejscowosc());
            this.ulica_label.setText(ad.getUlica());
            this.kraj_label.setText(ad.getKraj());
            this.kod_pocztowy_label.setText(ad.getKod_pocztowy());
//            adres_id_table.setCellValueFactory(
//                    cellData -> cellData.getValue().adres_id_pProperty().asObject());
        }
        else{
            this.adres_id_label.setText("");
            this.wojewodztwo_label.setText("");
            this.miejscowosc_label.setText("");
            this.ulica_label.setText("");
            this.kraj_label.setText("");
            this.kod_pocztowy_label.setText("");
        }
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        System.out.println("initialize AdresOvierwiecController");

        // Initialize the person table with the two columns.
        adres_id_table.setCellValueFactory(
                cellData -> cellData.getValue().adres_id_pProperty().asObject());
        wojewodztwo_table.setCellValueFactory(
                cellData -> cellData.getValue().wojewodztwo_pProperty());

        //clear
        showAdresDetails(null);

        adres_table.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showAdresDetails(newValue)
        );
    }

    @FXML
    private void handleDeleteButton(){
        System.out.println("handleDeleteButton AdresOvierwiecController");

        int selectedIndex = adres_table.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            mainApp.getAdresData().get(selectedIndex).delete(Integer.toString(
                    mainApp.getAdresData().get(selectedIndex).getAdres_id()));

            adres_table.getItems().remove(selectedIndex);
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
        System.out.println("handleEditButton AdresOvierwiecController");

        Adres selectedAdres = adres_table.getSelectionModel().getSelectedItem();
        if(selectedAdres != null){
            boolean okClicked = mainApp.showAdresEditDialog(selectedAdres);
            //mainApp.getAdresData().add(null);
            if(okClicked){
                showAdresDetails(selectedAdres);
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
        //initialize();
    }


    //nowa osoba
    @FXML
    private void handleOkButton(){
        System.out.println("handleOkButton AdresOvierwiecController");

        Adres tempAdres = new Adres();
        boolean okClicked = mainApp.showAdresEditDialog(tempAdres);
        if (okClicked) {
            mainApp.getAdresData().add(new Adres(tempAdres.getAdres_id(),
                    tempAdres.getWojewodztwo(), tempAdres.getMiejscowosc(),
                    tempAdres.getUlica(), tempAdres.getKraj(), tempAdres.getKod_pocztowy()));
        }

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        System.out.println("setMainApp AdresOvierwiecController");

        this.mainApp = mainApp;

        // Add observable list data to the table
        adres_table.setItems(mainApp.getAdresData());
    }
}

