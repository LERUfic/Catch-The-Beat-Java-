import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
/**
 *
 * @author My Name is Methos
 */
public class buah {
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Image image;

    public buah(int x, int y) {
        ImageIcon ii = new ImageIcon(this.getClass().getResource("banana.png"));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        this.x = x;
        this.y = y;
    }


    public void move() {
        if (y > 400) {
            y = 0;
        }
        y += 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Image getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}  
