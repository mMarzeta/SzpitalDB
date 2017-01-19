package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import db.Adres;

public class AdresEditController extends AbstractEditController{

    @FXML
    private TextField adres_id_textField;
    @FXML
    private TextField wojewodztwo_textField;
    @FXML
    private TextField miejscowosc_textField;
    @FXML
    private TextField ulica_textField;
    @FXML
    private TextField kraj_textField;
    @FXML
    private TextField kod_pocztowy_textField;

    private Adres adres;

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param ad
     */
    public void setAdres(Adres ad) {
        System.out.println("setAdres AdresEditController");

        this.adres = ad;

        this.adres_id_textField.setText(Integer.toString(ad.getAdres_id()));
        this.wojewodztwo_textField.setText(ad.getWojewodztwo());
        this.miejscowosc_textField.setText(ad.getMiejscowosc());
        this.ulica_textField.setText(ad.getUlica());
        this.kraj_textField.setText(ad.getKraj());
        this.kod_pocztowy_textField.setText(ad.getKod_pocztowy());
    }


    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        System.out.println("handleOk AdresEditController");
        if (isInputValid()) {
            //aby udalo sie select
            adres.setPrev_adres_id(adres.getAdres_id());

            adres.setAdres_id(Integer.parseInt(adres_id_textField.getText()));
            adres.setWojewodztwo(wojewodztwo_textField.getText());
            adres.setMiejscowosc(miejscowosc_textField.getText());
            adres.setUlica(ulica_textField.getText());
            adres.setKraj(kraj_textField.getText());
            adres.setKod_pocztowy(kod_pocztowy_textField.getText());

            adres.update();

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        return true;
    }
}