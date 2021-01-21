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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kappe
 */
public class WinController implements Initializable {

    @FXML
    private Button btnReplay;
    @FXML
    private Label who;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Controller c = new Controller();
        String msg="";
        int sp1 = c.getPoints_spieler1();
        int sp2 = c.getPoints_spieler2();
        if(sp1 >sp2){
            msg= "Yay, Player 1 wins!!!";
        } else if(sp1 < sp2){            
            msg= "Yay, Player 2 wins!!!";
        } else msg ="Yay, you win!!!";
        who.setText(msg);
    }    

    @FXML
    private void replay(ActionEvent event) throws IOException {
        Parent root;
                root = FXMLLoader.load(getClass().getResource("Start.fxml"));
                Stage stage = new Stage();
                Stage old= (Stage) btnReplay.getScene().getWindow();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                
                stage.show();
                old.close();
    }
    
}
