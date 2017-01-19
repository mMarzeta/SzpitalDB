package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import db.Uczulenie;
/**
 * Created by maciejmarzeta on 16.01.2017.
 */
public class UczulenieEditController extends AbstractEditController{

    @FXML
    private TextField uczulenie_id_textField;
    @FXML
    private TextField nazwa_textField;
    @FXML
    private  TextField lek_ref_textField;

    private Uczulenie uczulenie;


    /**
     * Sets the person to be edited in the dialog.
     *
     * @param uczulenie
     */
    public void setUczulenie(Uczulenie uczulenie) {

        this.uczulenie = uczulenie;

        this.uczulenie_id_textField.setText(Integer.toString(uczulenie.getUczulenie_id()));
        this.nazwa_textField.setText(uczulenie.getNazwa());
        this.lek_ref_textField.setText(Integer.toString(uczulenie.getLek_ref()));
    }
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            //aby udalo sie select
            uczulenie.setPrev_uczulenie_id(uczulenie.getUczulenie_id());

            uczulenie.setUczulenie_id(Integer.parseInt(uczulenie_id_textField.getText()));
            uczulenie.setNazwa(nazwa_textField.getText());
            uczulenie.setLek_ref(Integer.parseInt(lek_ref_textField.getText()));

            uczulenie.update();

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
