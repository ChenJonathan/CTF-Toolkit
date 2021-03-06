import format.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by bird on 5/8/2016.
 */
public class ReformatController implements Initializable {
    @FXML
    private TextArea inputArea;
    @FXML
    private TextArea outputArea;
    @FXML
    private ChoiceBox<Format> from;
    @FXML
    private ChoiceBox<Format> to;
    @FXML
    private Button convert;

    /**
     * Initialize values
     * @param fxmlFileLocation File location
     * @param resources Resources
     */
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        List<Format> subClasses = Arrays.asList(new Binary(), new Hex(), new Base64(), new Ascii(), new Morse());
        from.setItems(FXCollections.observableArrayList(subClasses));
        from.getSelectionModel().selectFirst();
        to.setItems(FXCollections.observableArrayList(subClasses));
        to.getSelectionModel().selectFirst();

        convert.setOnAction(event -> {
            conversion();
        });
    }

    private void conversion() {
        if (inputArea.getText().equals("")) {
            return;
        }
        String s = inputArea.getText();
        s = from.getValue().decode(s);
        s = to.getValue().encode(s);
        outputArea.setText(s);
    }
}
