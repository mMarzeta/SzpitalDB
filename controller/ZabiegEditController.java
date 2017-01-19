package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import db.Zabieg;
/**
 * Created by maciejmarzeta on 16.01.2017.
 */
public class ZabiegEditController extends AbstractEditController{
    @FXML
    private TextField zabieg_id_textField;
    @FXML
    private TextField nazwa_textField;
    @FXML
    private  TextField oddzial_ref_textField;

    private Zabieg zabieg;


    public void setZabieg(Zabieg zabieg) {

        this.zabieg = zabieg;

        this.zabieg_id_textField.setText(Integer.toString(zabieg.getZabieg_id()));
        this.nazwa_textField.setText(zabieg.getNazwa());
        this.oddzial_ref_textField.setText(Integer.toString(zabieg.getOddzial_ref()));
    }


    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            //aby udalo sie select
            zabieg.setPrev_zabieg_id(zabieg.getZabieg_id());

            zabieg.setZabieg_id(Integer.parseInt(zabieg_id_textField.getText()));
            zabieg.setNazwa(nazwa_textField.getText());
            zabieg.setOddzial_ref(Integer.parseInt(oddzial_ref_textField.getText()));

//            zabieg.setZabieg_id_p(Integer.parseInt(zabieg_id_textField.getText()));
//            zabieg.setNazwa_p(nazwa_textField.getText());
//            zabieg.setOddzial_ref_p(Integer.parseInt(oddzial_ref_textField.getText()));

            zabieg.update();

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
