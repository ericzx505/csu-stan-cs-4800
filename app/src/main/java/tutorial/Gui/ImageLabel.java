/*taken from https://stackoverflow.com/questions/9342233/zoom-in-and-out-of-images-in-java as a way to zoom in on the image
 * This code allows one to zoom in on an image like we need to do with the map we are creating
 */
package tutorial.Gui;

import javax.swing.*;
import java.awt.*;

public class ImageLabel extends JLabel{
    Image image;
    int width, height;

    public void paint(Graphics g) {
        int x, y;
        //this is to center the image
        x = (this.getWidth() - width) < 0 ? 0 : (this.getWidth() - width);
        y = (this.getHeight() - width) < 0 ? 0 : (this.getHeight() - width);

        g.drawImage(image, x, y, width, height, null);
    }

    public void setDimensions(int width, int height) {
        this.height = height;
        this.width = width;

        image = image.getScaledInstance(width, height, Image.SCALE_FAST);
        Container parent = this.getParent();
        if (parent != null) {
            parent.repaint();
        }
        this.repaint();
    }
}

