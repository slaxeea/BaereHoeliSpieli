

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

/**
 *
 * @author kappe
 */
public class Controller implements Initializable {

    // Variables used later 
    Random rnd = new Random();
    public int[] vals = new int[15];
    public Dice[] dices = new Dice[15];
    boolean wins = false;
    int correct = 0;
    int tries = 0;
    int bäre;
    int höhle;
    String guessBären;
    String guessHöhlen;
    ImageView[] imvArray = new ImageView[15];

    // Every image view of a dice
    @FXML
    public ImageView dice_1;
    @FXML
    public ImageView dice_3;
    @FXML
    public ImageView dice_5;
    @FXML
    public ImageView dice_2;
    @FXML
    public ImageView dice_4;
    @FXML
    public ImageView dice_6;
    @FXML
    public ImageView dice_7;
    @FXML
    public ImageView dice_8;
    @FXML
    public ImageView dice_9;

    // all the fields on the fxml 
    @FXML
    private TextField anzBären;
    @FXML
    private TextField anzHöhlen;
    @FXML
    private Label fehler;
    @FXML
    private Label valBären;
    @FXML
    private Label valHöhlen;
    @FXML
    private Label label;
    @FXML
    private ImageView baer;
    @FXML
    private ImageView igang;
    @FXML
    private Button roll;
    @FXML
    private Button check;

    ImageHelper imagehelper;
    @FXML
    private Label points_1;
    @FXML
    private Label points_2;
    @FXML
    private Label dran;

    // variables for multi player
    private int spieler = 1;
    private int points_spieler1 = 0;
    private int points_spieler2 = 0;
    @FXML
    private Label spielerDran;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Make Multiplayer stuff invisible if playing alone
        if (!multi) {
            points_1.setVisible(false);
            points_2.setVisible(false);
            dran.setVisible(false);
            spielerDran.setVisible(false);
        }
        // roll them dices!
        würfle();

    }

    public static int numDices = 6;
    public static boolean multi = false;

    // Change the Images of all the Dices
    // This uses a Helper Class, which does it for me
    public void setImgs() {
        switch (numDices) {
            case 3:
                imagehelper.row1();
                break;
            case 6:
                imagehelper.row1();
                imagehelper.row2();
                break;
            case 9:
                imagehelper.row1();
                imagehelper.row2();
                imagehelper.row3();
                break;
        }
    }

    @FXML
    // On click for new dice
    public void roll(ActionEvent event) {
        initializeArr();
        if (multi) {
            würfleMulti();
        } else {
            würfle();
        }
    }

    // Because it doesn't work without
    public void initializeArr() {
        imvArray[0] = dice_1;
        imvArray[1] = dice_2;
        imvArray[2] = dice_3;
        imvArray[3] = dice_4;
        imvArray[4] = dice_5;
        imvArray[5] = dice_6;
        imvArray[6] = dice_7;
        imvArray[7] = dice_8;
        imvArray[8] = dice_9;
    }

    // roll the dices
    private void würfle() {
        initializeArr();
        imagehelper = new ImageHelper(vals, imvArray, dices);

        resetText();

        for (int i = 0; i < 9; i++) {
            try {
                int rand = rnd.nextInt(6);
                rand++;
                vals[i] = rand;
                dices[i] = new Dice(rand, imvArray[i]);
            } catch (Exception e) {
            }

            calcVals();
        }
        // print out the solutions because im lazy and cant be bothered to solve it everytime
        System.out.println(String.valueOf(höhle) + " " + String.valueOf(bäre));

        setImgs();
    }

    // Rolling dices but for Multiplayer
    private void würfleMulti() {
        if (points_spieler1 > 5 || points_spieler2 > 5) {
            winScene();

        } else {
            würfle();
            if (spieler == 1) {
                spieler = 2;
            } else {
                spieler = 1;
            }
        }
        dran.setText(String.valueOf(spieler));
    }

    @FXML
    // Onclick action for the check button
    public void check(ActionEvent event) {
        if (multi) {
            checkMulti();
        } else {
            checkSingle();
        }
    }

    // Checks it the input is correct, in Multiplayer
    private void checkMulti() {
        fehler.setText("");
        try {
            guessHöhlen = anzHöhlen.getText();
            guessBären = anzBären.getText();
            guessHöhlen.replaceAll("\\s+", "");
            guessBären.replaceAll("\\s+", "");

            if (Integer.valueOf(guessHöhlen) == höhle && Integer.valueOf(guessBären) != bäre) {
                valHöhlen.setText("Richtig");
                valBären.setText("Falsch");
            } else if (Integer.valueOf(guessHöhlen) != höhle && Integer.valueOf(guessBären) == bäre) {
                valHöhlen.setText("Falsch");
                valBären.setText("Richtig");
            } else if (Integer.valueOf(guessHöhlen) == höhle && Integer.valueOf(guessBären) == bäre) {
                valBären.setText("Richtig");
                valHöhlen.setText("Richtig");
                if (spieler == 1) {
                    points_spieler1++;
                } else {
                    points_spieler2++;
                }
                points_1.setText(String.valueOf("Punkte Spieler 1: " + points_spieler1));
                points_2.setText(String.valueOf("Punkte Spieler 2: " + points_spieler2));
            } else {
                valBären.setText("Falsch");
                valHöhlen.setText("Falsch");
            }
            würfleMulti();
        } catch (Exception e) {
            fehler.setText("Bisch du dummmm");
        }
    }

    // Checks it the input is correct, in Singleplayer
    private void checkSingle() {
        fehler.setText("");
        if (correct > 5) {
            winScene();
        } else if (tries > 5) {
            try {
                ripScene();
            } catch (IOException ex) {
            }
        } else {
            try {
                guessHöhlen = anzHöhlen.getText();
                guessBären = anzBären.getText();
                guessHöhlen.replaceAll("\\s+", "");
                guessBären.replaceAll("\\s+", "");

                if (Integer.valueOf(guessHöhlen) == höhle && Integer.valueOf(guessBären) != bäre) {
                    valHöhlen.setText("Richtig");
                    valBären.setText("Falsch");
                } else if (Integer.valueOf(guessHöhlen) != höhle && Integer.valueOf(guessBären) == bäre) {
                    valHöhlen.setText("Falsch");
                    valBären.setText("Richtig");
                } else if (Integer.valueOf(guessHöhlen) == höhle && Integer.valueOf(guessBären) == bäre) {
                    valBären.setText("Richtig");
                    valHöhlen.setText("Richtig");
                    correct++;
                    würfleMulti();
                } else {
                    valBären.setText("Falsch");
                    valHöhlen.setText("Falsch");
                }
            } catch (Exception e) {
                fehler.setText("Bisch du dummmm");
            }
            tries++;
        }
    }

    // Deploys the Win Screnn thing if a (or the) player won
    private void winScene() {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("Win.fxml"));
            Stage stage = new Stage();
            Stage old = (Stage) fehler.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.show();
            old.close();
        } catch (IOException ex) {
        }
    }

    // Resets the text fields
    private void resetText() {
        fehler.setText("");
        anzBären.setText("");
        anzHöhlen.setText("");
        valBären.setText("");
        valHöhlen.setText("");
    }
    // calculates the Values of the Bears and Caves
    private void calcVals() {
        höhle = 0;
        bäre = 0;
        for (int i = 0; i < numDices; i++) {
            int temp = vals[i];
            if (temp % 2 != 0) {
                höhle++;
                bäre = bäre + (7 - temp);
            }
        }
    }

    // Getters for the Win .fxml file
    public int getPoints_spieler1() {
        return points_spieler1;
    }

    public int getPoints_spieler2() {
        return points_spieler2;
    }

    private void ripScene() throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("RIP.fxml"));
        Stage stage = new Stage();
        Stage old = (Stage) fehler.getScene().getWindow();
        Scene scene = new Scene(root);

        stage.setScene(scene);

        stage.show();
        old.close();
    }
}