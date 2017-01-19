package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import db.Wizyta;
/**
 * Created by maciejmarzeta on 16.01.2017.
 */
public class WizytaEditController extends AbstractEditController{
    @FXML
    private TextField wizyta_id_textField;
    @FXML
    private TextField data_wizyty_textField;
    @FXML
    private TextField lek_ref_textField;
    @FXML
    private TextField choroba_ref_textField;
    @FXML
    private TextField zabieg_ref_textField;
    @FXML
    private TextField lekarz_ref_textField;

    private Wizyta wizyta;


    public void setWizyta(Wizyta wizyta) {

        this.wizyta = wizyta;

        this.wizyta_id_textField.setText(Integer.toString(wizyta.getWizyta_id()));
        this.data_wizyty_textField.setText(wizyta.getData_wizyty());
        this.lek_ref_textField.setText(Integer.toString(wizyta.getLekarz_ref()));
        this.choroba_ref_textField.setText(Integer.toString(wizyta.getChoroba_ref()));
        this.zabieg_ref_textField.setText(Integer.toString(wizyta.getZabieg_ref()));
        this.lekarz_ref_textField.setText(Integer.toString(wizyta.getLekarz_ref()));
    }


    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            //aby udalo sie select
            wizyta.setPrev_wizyta_id(wizyta.getWizyta_id());

            wizyta.setWizyta_id(Integer.parseInt(wizyta_id_textField.getText()));
            wizyta.setData_wizyty(data_wizyty_textField.getText());
            wizyta.setLek_ref(Integer.parseInt(lek_ref_textField.getText()));
            wizyta.setChoroba_ref(Integer.parseInt(choroba_ref_textField.getText()));
            wizyta.setZabieg_ref(Integer.parseInt(zabieg_ref_textField.getText()));
            wizyta.setLekarz_ref(Integer.parseInt(lekarz_ref_textField.getText()));

            wizyta.update();

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
