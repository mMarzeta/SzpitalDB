package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import db.Lek;

/**
 * Created by maciejmarzeta on 15.01.2017.
 */
public class LekEditController extends AbstractEditController{
    @FXML
    private TextField lek_id_textField;
    @FXML
    private TextField nazwa_textField;

    private Lek lek;

    public void setLek(Lek l){
        this.lek = l;

        this.lek_id_textField.setText(Integer.toString(l.getLek_id()));
        this.nazwa_textField.setText(l.getNazwa());
    }

    @FXML
    private void handleOk(){
        if(isInputValid()){
            lek.setPrev_lek_id(lek.getLek_id());

            lek.setLek_id(Integer.parseInt(lek_id_textField.getText()));
            lek.setNazwa(nazwa_textField.getText());

            lek.update();

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
