package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import db.Lekarz;
/**
 * Created by maciejmarzeta on 15.01.2017.
 */
public class LekarzEditController extends  AbstractEditController{
    @FXML
    private TextField lekarz_id_textField;
    @FXML
    private TextField imie_textField;
    @FXML
    private TextField nazwisko_textField;
    @FXML
    private TextField numer_telefonu_textField;
    @FXML
    private TextField adres_ref_textField;
    @FXML
    private TextField plec_ref_textField;

    private Lekarz lekarz;

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param lekarz
     */
    public void setLekarz(Lekarz lekarz) {
        System.out.println("setAdres AdresEditController");

        this.lekarz = lekarz;

        this.lekarz_id_textField.setText(Integer.toString(lekarz.getLekarz_id()));
        this.imie_textField.setText(lekarz.getImie());
        this.nazwisko_textField.setText(lekarz.getNazwisko());
        this.numer_telefonu_textField.setText(lekarz.getNumer_telefonu());
        this.adres_ref_textField.setText(Integer.toString(lekarz.getAdres_ref()));
        this.plec_ref_textField.setText(Integer.toString(lekarz.getPlec_ref()));

//        lekarz.update();
//
//        okClicked = true;
//        dialogStage.close();
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            //aby udalo sie select
            lekarz.setPrev_lekarz_id(lekarz.getLekarz_id());

            lekarz.setLekarz_id(Integer.parseInt(lekarz_id_textField.getText()));
            lekarz.setImie(imie_textField.getText());
            lekarz.setNazwisko(nazwisko_textField.getText());
            lekarz.setNumer_telefonu(numer_telefonu_textField.getText());
            lekarz.setAdres_ref(Integer.parseInt(adres_ref_textField.getText()));
            lekarz.setPlec_ref(Integer.parseInt(plec_ref_textField.getText()));

            lekarz.update();

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

