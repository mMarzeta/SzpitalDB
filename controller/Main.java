package controller;

import java.io.IOException;

import db.*;

import javafx.scene.control.MenuBar;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.stage.Modality;
//test
//try again

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane anchorPane;
    private MenuBar menuBar;

    private ObservableList<Adres> adresData = FXCollections.observableArrayList();
    private ObservableList<Lek> lekData = FXCollections.observableArrayList();
    private ObservableList<Lekarz> lekarzData = FXCollections.observableArrayList();
    private ObservableList<Choroba> chorobaData = FXCollections.observableArrayList();
    private ObservableList<Oddzial> oddzialData = FXCollections.observableArrayList();
    private ObservableList<Szpital> szpitalData = FXCollections.observableArrayList();
    private ObservableList<Pacjent> pacjentData = FXCollections.observableArrayList();
    private ObservableList<Wizyta> wizytaData = FXCollections.observableArrayList();
    private ObservableList<Zabieg> zabiegData = FXCollections.observableArrayList();
    private ObservableList<Uczulenie> uczulenieData = FXCollections.observableArrayList();


    public Main(){
        adresData.add(new Adres(1, "Małopolska", "Kraków",
                "Czanrowiejska", "Polska", "24-123"));
        adresData.add(new Adres(2, "Mazowieckie", "Warszawa",
                "Wiejska", "Polska", "55-111"));
        adresData.add(new Adres(3, "Lubelskie", "Lublin",
                "Diamentowa", "Polska", "24-222"));

        lekData.add(new Lek(1, "Aspiryna"));
        lekData.add(new Lek(2, "Gripex"));

        Plec plec = new Plec(1, "Kobieta");
        plec.insert(plec.generate_insert_query(2, "Mezczyzna"));
        plec.insert(plec.generate_insert_query());

        lekarzData.add(new Lekarz(1, "dr.Anna", "House",
                "123456789", 1, 1));
        lekarzData.add(new Lekarz(2, "lek.med Maciej", "Lekarski",
                "123123123", 3, 2));

        chorobaData.add(new Choroba(1, "Katar"));
        chorobaData.add(new Choroba(2, "Jaskra"));

        oddzialData.add(new Oddzial(1, "Pediatria", 1));
        oddzialData.add(new Oddzial(2, "Neonatologia", 2));

        uczulenieData.add(new Uczulenie(1, "Orzechy", 1));

        zabiegData.add(new Zabieg(1, "Wyrostek", 1));

        szpitalData.add(new Szpital(1, "Szpital Dziecięcy", 1, 1));

        wizytaData.add(new Wizyta(1, "2017-01-01", 1, 1, 1, 1));

        pacjentData.add(new Pacjent(1, "Maciej", "Marzeta",
                "1994-09-30", "123123123", 2, 1, 1, 1 ));
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println("start Main");

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Szpital DB");

        initRootLayout();
        showMenuOverview();
        //showAdresOverview();
        //showAdresEditDialog();

    }

    public void initRootLayout() {
        System.out.println("initRootLayout Main");

        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMenuOverview(){
        System.out.println("MenuOverview Main");

        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/RootLayout.fxml"));
            rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            MenuController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStatystykiOverview(){
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/Statystyki.fxml"));
            AnchorPane personOverview = loader.load();

            rootLayout.setCenter(personOverview);
         } catch (IOException e) {
                e.printStackTrace();
            }
        }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showAdresOverview() {
        System.out.println("showAdresOverwiev Main");

        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/Adres.fxml"));
            AnchorPane personOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            AdresOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLekOverview() {
        System.out.println("showLekOverwiew Main");

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/Lek.fxml"));
            AnchorPane personOverview = loader.load();

            rootLayout.setCenter(personOverview);

            LekOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLekarzOverview() {
        System.out.println("showLekarzOverwiew Main");

        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/Lekarz.fxml"));
            AnchorPane personOverview = loader.load();

            rootLayout.setCenter(personOverview);

            LekarzOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showChorobaOverview() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/Choroba.fxml"));
            AnchorPane personOverview = loader.load();

            rootLayout.setCenter(personOverview);

            ChorobaOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showOddzialOverview() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/Oddzial.fxml"));
            AnchorPane personOverview = loader.load();

            rootLayout.setCenter(personOverview);

            OddzialOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showUczulenieOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/Uczulenie.fxml"));
            AnchorPane personOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            UczulenieOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSzpitalOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/Szpital.fxml"));
            AnchorPane personOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            SzpitalOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showZabiegOverview(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/Zabieg.fxml"));
            AnchorPane personOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            ZabiegOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showWizytaOverview(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/Wizyta.fxml"));
            AnchorPane personOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            WizytaOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPacjentOverview(){
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/Pacjent.fxml"));
            AnchorPane personOverview = loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            PacjentOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showAdresEditDialog(Adres ad){
        System.out.println("showAdresEditDialog Main");

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/view/AdresEditDialog.fxml")));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Adres");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            AdresEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAdres(ad);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showLekEditDialog(Lek lek){
        System.out.println("showLekEditDialog Main");

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/view/LekEditDialog.fxml")));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Lek");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            LekEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLek(lek);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showLekarzEditDialog(Lekarz lek){
        System.out.println("showLekarzEditDialog Main");

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/view/LekarzEditDialog.fxml")));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Lekarz");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            LekarzEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setLekarz(lek);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showChorobaieEditDialog(Choroba choroba){
        System.out.println("showLekarzEditDialog Main");

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/view/ChorobaEditDialog.fxml")));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Lekarz");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ChorobaEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setchoroba(choroba);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showOddzialEditDialog(Oddzial oddzial){
        System.out.println("showLekarzEditDialog Main");

        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/view/OddzialEditDialog.fxml")));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Oddzial");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            OddzialEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setOddzial(oddzial);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showUczulenieEditDialog(Uczulenie uczulenie){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/view/UczulenieEditDialog.fxml")));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Uczulenie");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            UczulenieEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setUczulenie(uczulenie);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean showZabiegEditDialog(Zabieg zabieg){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/view/ZabiegEditDialog.fxml")));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Zabieg");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            ZabiegEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setZabieg(zabieg);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showSzpitalEditDialog(Szpital szpital){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/view/SzpitalEditDialog.fxml")));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Szpital");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            SzpitalEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSzpital(szpital);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showWizytaEditDialog(Wizyta wizyta){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/view/WizytaEditDialog.fxml")));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Wizyta");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            WizytaEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setWizyta(wizyta);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showPacjentEditDialog(Pacjent pacjent){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader((Main.class.getResource("/view/PacjentEditDialog.fxml")));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Pacjent");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PacjentEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPacjent(pacjent);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        System.out.println("getPrimaryStage Main");

        return primaryStage;
    }

    public static void main(String[] args) {
        System.out.println("main Main");

        launch(args);
    }

    public ObservableList<Adres> getAdresData(){
        System.out.println("getAdresData Main");
        return this.adresData;
    }

    public ObservableList<Lek> getLekData(){
        System.out.println("getLekData Main");
        return this.lekData;
    }

    public ObservableList<Lekarz> getLekarzData() {
        System.out.println("getLekarzData Main");
        return lekarzData;
    }

    public ObservableList<Choroba> getChorobaData() {
        return chorobaData;
    }

    public ObservableList<Oddzial> getOddzialData() {
        return oddzialData;
    }

    public ObservableList<Szpital> getSzpitalData() {
        return szpitalData;
    }

    public ObservableList<Pacjent> getPacjentData() {
        return pacjentData;
    }

    public ObservableList<Wizyta> getWizytaData() {
        return wizytaData;
    }

    public ObservableList<Zabieg> getZabiegData() {
        return zabiegData;
    }

    public ObservableList<Uczulenie> getUczulenieData() {
        return uczulenieData;
    }
}
