package controller;

import javafx.fxml.FXML;

import javafx.scene.control.MenuItem;

/**
 * Created by maciejmarzeta on 14.01.2017.
 */
public class MenuController {

    @FXML
    private MenuItem adres_menuItem;

    private Main mainApp;

    @FXML
    private void initialize(){
        System.out.println("MEnuController Init");
    }

    @FXML
    private void handleMenuAdresButton(){
        System.out.println("MenuButton MenuController");
        mainApp.showAdresOverview();
    }

    @FXML
    private void handleMenuLek(){
        System.out.println("LEkButton MenuController");

    }

    @FXML
    private void handleCloseButton(){
        mainApp.getAdresData().get(0).drop();
    }

    public void setMainApp(Main mainApp) {
        System.out.println("setMainApp MenuController");
        this.mainApp = mainApp;
        System.out.println("przypisuje)");
    }

}
