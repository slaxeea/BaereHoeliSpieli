/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kappe
 */
public class ScenarioController implements Initializable {

    @FXML
    private Button ja1;
    @FXML
    private Button nein;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    Parent root;

    @FXML
    private void ja(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        Stage stage = new Stage();
        Stage old = (Stage) ja1.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
        old.close();
    }

    @FXML
    private void nein(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("RIP.fxml"));
        Stage stage = new Stage();
        Stage old = (Stage) ja1.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
        old.close();
    }
}
