package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import db.Pacjent;
/**
 * Created by maciejmarzeta on 16.01.2017.
 */
public class PacjentEditController extends AbstractEditController{
    @FXML
    private TextField pacjent_id_textField;
    @FXML
    private TextField imie_textField;
    @FXML
    private TextField nazwisko_textField;
    @FXML
    private TextField data_urodzenia_textField;
    @FXML
    private TextField numer_telefonu_textField;
    @FXML
    private TextField plec_ref_textField;
    @FXML
    private TextField uczulenie_ref_textField;
    @FXML
    private TextField adres_ref_textField;
    @FXML
    private TextField wizyta_ref_textField;

    private Pacjent pacjent;

    public void setPacjent(Pacjent pacjent){
        this.pacjent = pacjent;

        this.pacjent_id_textField.setText(Integer.toString(pacjent.getPacjent_id()));
        this.imie_textField.setText(pacjent.getImie());
        this.nazwisko_textField.setText(pacjent.getNazwisko());
        this.data_urodzenia_textField.setText(pacjent.getData_urodzenia());
        this.numer_telefonu_textField.setText(pacjent.getNumer_telefonu());
        this.plec_ref_textField.setText(Integer.toString(pacjent.getPlec_ref()));
        this.uczulenie_ref_textField.setText(Integer.toString(pacjent.getUczulenie_ref()));
        this.adres_ref_textField.setText(Integer.toString(pacjent.getAdres_ref()));
        this.wizyta_ref_textField.setText(Integer.toString(pacjent.getWizyta_ref()));
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            //aby udalo sie select
            pacjent.setPrev_pacjent_id(pacjent.getPacjent_id());

            pacjent.setPacjent_id(Integer.parseInt(pacjent_id_textField.getText()));
            pacjent.setImie(imie_textField.getText());
            pacjent.setNazwisko(nazwisko_textField.getText());
            pacjent.setData_urodzenia(data_urodzenia_textField.getText());
            pacjent.setNumer_telefonu(numer_telefonu_textField.getText());
            pacjent.setPlec_ref(Integer.parseInt(plec_ref_textField.getText()));
            pacjent.setUczulenie_ref(Integer.parseInt(uczulenie_ref_textField.getText()));
            pacjent.setAdres_ref(Integer.parseInt(adres_ref_textField.getText()));
            pacjent.setWizyta_ref(Integer.parseInt(wizyta_ref_textField.getText()));

            pacjent.update();

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