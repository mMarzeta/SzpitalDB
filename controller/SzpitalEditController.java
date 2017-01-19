package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import db.Szpital;
/**
 * Created by maciejmarzeta on 16.01.2017.
 */
public class SzpitalEditController extends AbstractEditController {
    @FXML
    private TextField szpital_id_textField;
    @FXML
    private TextField nazwa_textField;
    @FXML
    private TextField adres_ref_textField;
    @FXML
    private TextField oddzial_ref_textField;

    private Szpital szpital;

    public void setSzpital(Szpital szpital) {

        this.szpital = szpital;

        this.szpital_id_textField.setText(Integer.toString(szpital.getSzpital_id()));
        this.nazwa_textField.setText(szpital.getNazwa());
        this.adres_ref_textField.setText(Integer.toString(szpital.getAdres_ref()));
        this.oddzial_ref_textField.setText(Integer.toString(szpital.getOddzial_ref()));
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            //aby udalo sie select
            szpital.setPrev_szpita_id(szpital.getSzpital_id());

            szpital.setSzpital_id(Integer.parseInt(szpital_id_textField.getText()));
            szpital.setNazwa(nazwa_textField.getText());
            szpital.setAdres_ref(Integer.parseInt(adres_ref_textField.getText()));
            szpital.setOddzial_ref(Integer.parseInt(oddzial_ref_textField.getText()));

            szpital.update();

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
