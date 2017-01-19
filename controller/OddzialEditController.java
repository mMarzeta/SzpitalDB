package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import db.Oddzial;
/**
 * Created by maciejmarzeta on 16.01.2017.
 */
public class OddzialEditController extends AbstractEditController{

    @FXML
    private TextField oddzial_id_textField;
    @FXML
    private TextField nazwa_textField;
    @FXML
    private  TextField lekarz_ref_textField;

    private Oddzial oddzial;

    public void setOddzial(Oddzial oddzial) {

        this.oddzial = oddzial;

        this.oddzial_id_textField.setText(Integer.toString(oddzial.getOddzial_id()));
        this.nazwa_textField.setText(oddzial.getNazwa());
        this.lekarz_ref_textField.setText(Integer.toString(oddzial.getLekarz_ref()));
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            //aby udalo sie select
            oddzial.setPrev_oddzial_id(oddzial.getOddzial_id());

            oddzial.setOddzial_id(Integer.parseInt(oddzial_id_textField.getText()));
            oddzial.setNazwa(nazwa_textField.getText());
            oddzial.setLekarz_ref(Integer.parseInt(lekarz_ref_textField.getText()));

            oddzial.update();

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
