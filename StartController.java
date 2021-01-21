import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kappe
 */
public class StartController implements Initializable {

    @FXML
    private Button btnConfirm;
    @FXML
    private Slider dices;

    private boolean multi;
    @FXML
    private Label fehler;
    @FXML
    private RadioButton select;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void confirm(ActionEvent event) throws IOException {
        try {
            System.out.println(multi);
            Controller.numDices = (int) dices.getValue();
            Controller.multi = multi;

            Parent root;
            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Stage stage = new Stage();
            Stage old = (Stage) select.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.show();
            old.close();

        } catch (Exception e) {
            fehler.setText("Select all fields pls");
        }
    }

    @FXML
    private void mutlti(ActionEvent event) {
        multi = select.isSelected();
    }
}
