package sample;

import java.io.IOException;

import db.Adres;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import view.AdresEditController;
import view.AdresOverviewController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.collections.FXCollections;
import javafx.stage.Modality;
import view.MenuController;

public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private AnchorPane anchorPane;
    private MenuBar menuBar;

    private ObservableList<Adres> adresData = FXCollections.observableArrayList();


    public Main(){
        adresData.add(new Adres(1, "kij", "krakow",
                "ulica", "polska", "24-222"));
        adresData.add(new Adres(2, "ci", "krakow",
                "ulica", "polska", "24-222"));
        adresData.add(new Adres(3, "w", "krakow",
                "ulica", "polska", "24-222"));
        adresData.add(new Adres(4, "oko", "krakow",
                "ulica", "polska", "24-222"));
        //adresData.get(0).drop();
    }

    public ObservableList<Adres> getAdresData(){
        System.out.println("getAdresData Main");

        return this.adresData;
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
}
