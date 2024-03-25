/*
 * Shoutout to https://github.com/Thanasis1101/Zoomable-Java-Panel/blob/master/src/zoomable/panel/MainFrame.java for providing the foundation for zooming in.
 * Code is mostly the same say for a few modifications to fit our particular project.
 */
package tutorial.Gui;

import tutorial.Gui.ImageLabel;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Gui extends JFrame{
    ImageLabel paintableMap;
    String imagePath = getFilePath();

    public Gui(){
        initComponents();
    }
    private String getFilePath(){
        String tempPath = null;
        try {
            tempPath = new File("src/main/java/tutorial/resources/test_image.png").getCanonicalPath();
        } catch (IOException e) {
            System.out.print("The file 'resources/test_image.png' is not found, quiting now");
        }
        System.out.println(tempPath);
        return tempPath;
    }
    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        setTitle("CSU Stan Map");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();

        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            
            paintableMap = new ImageLabel(image);
            paintableMap.setBounds(50, 50, width - 100, height - 240);
            paintableMap.setBorder(BorderFactory.createLineBorder(Color.black));
            this.add(paintableMap);
            paintableMap.setVisible(true);

        } catch (IOException ex) {
            System.out.print("The file 'resources/test_image.png' is not found, quiting now");
        }

    }

}



