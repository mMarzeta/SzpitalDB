package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import db.Choroba;
/**
 * Created by maciejmarzeta on 15.01.2017.
 */
public class ChorobaEditController extends AbstractEditController{

    @FXML
    private TextField choroba_id_textField;
    @FXML
    private TextField nazwa_textField;

    private Choroba choroba;

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param choroba
     */
    public void setchoroba(Choroba choroba) {
        System.out.println("setchoroba chorobaEditController");

        this.choroba = choroba;

        this.choroba_id_textField.setText(Integer.toString(choroba.getChoroba_id()));
        this.nazwa_textField.setText(choroba.getNazwa());
    }


    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        System.out.println("handleOk chorobaEditController");
        if (isInputValid()) {
            //aby udalo sie select
            choroba.setPrev_choroba_id(choroba.getChoroba_id());

            choroba.setChoroba_id(Integer.parseInt(choroba_id_textField.getText()));
            choroba.setNazwa(nazwa_textField.getText());

            choroba.update();

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

