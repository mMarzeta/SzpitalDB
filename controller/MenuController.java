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
    private void handleMenuAdres(){
        mainApp.showAdresOverview();
    }

    @FXML
    private void handleMenuLek(){
        mainApp.showLekOverview();
    }

    @FXML
    private void handleMenuLekarz(){
        mainApp.showLekarzOverview();
    }

    @FXML
    private void handleMenuChoroba(){
        mainApp.showChorobaOverview();
    }

    @FXML
    private void handleMenuReportStatystyki(){
        mainApp.showStatystykiOverview();
    }

    @FXML
    private void handleMenuOddzial(){
        mainApp.showOddzialOverview();
    }

    @FXML
    private void handleMenuUczulenie(){
        mainApp.showUczulenieOverview();
    }

    @FXML
    private void handleMenuZabieg(){
        mainApp.showZabiegOverview();
    }

    @FXML
    private void handleMenuSzpital(){
        mainApp.showSzpitalOverview();
    }

    @FXML
    private void handleMenuWizyta(){
        mainApp.showWizytaOverview();
    }

    @FXML
    private void handleMenuPacjent(){
        mainApp.showPacjentOverview();
    }

    @FXML
    private void handleCloseButton(){
        mainApp.getLekarzData().get(0).drop();
        mainApp.getLekData().get(0).drop();
        mainApp.getAdresData().get(0).drop();
        mainApp.getChorobaData().get(0).drop();
        mainApp.getOddzialData().get(0).drop();
        mainApp.getUczulenieData().get(0).drop();
        mainApp.getZabiegData().get(0).drop();
        mainApp.getWizytaData().get(0).drop();
        mainApp.getSzpitalData().get(0).drop();
        mainApp.getPacjentData().get(0).drop();
    }

    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;
    }

}
