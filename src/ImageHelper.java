


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * @author kappe
 */

// This is a helper class, to manage the images of the dice.
// It helps me keep the Controller class down in size
// and manages all the image setting for me
public class ImageHelper {

    private int[] vals;
    ImageView[] imvArray;
    Dice[] dices;

    public ImageHelper(int[] vals, ImageView[] imvArray, Dice[] dices) {
        this.vals = vals;
        this.imvArray = imvArray;
        this.dices = dices;
    }

    // Sets images of the first row
    // and blanks out the images of the second and third row
    public void row1() {
        for (int i = 0; i < 3; i++) {
            dices[i].setPath("imgs/" + vals[i] + ".png");
        }

        blankRow2();
        blankRow3();

    }

    // Sets images of the second row
    // and blanks out the images of the third
    public void row2() {
        for (int i = 2; i < 6; i++) {
            dices[i].setPath("imgs/" + vals[i] + ".png");
        }

        blankRow3();

    }

    // Sets images of the third row
    public void row3() {
        for (int i = 5; i < 9; i++) {
            dices[i].setPath("imgs/" + vals[i] + ".png");
        }
    }

    // Blanks out the second row
    public void blankRow2() {
        for (int i = 3; i < 6; i++) {
            dices[i].setPath("imgs/blank.png");
        }

    }

    // Blanks out the third row
    public void blankRow3() {
        {
            for (int i = 3; i < 6; i++) {
                dices[i + 3].setPath("imgs/blank.png");
            }

        }
    }
}
