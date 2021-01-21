
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author kappe
 */
public class Dice {

    public int value;
    public String path = "imgs/blank.png";
    public ImageView imv;

    public Dice(int value, ImageView i) {
        this.value = value;
        this.imv = i;
    }

    public int getValue() {
        return value;
    }

    public void setPath(String path) {
        this.path = path;
        try {
            imv.setImage(new Image(path));
        } catch (Exception e) {

        }
    }
}
