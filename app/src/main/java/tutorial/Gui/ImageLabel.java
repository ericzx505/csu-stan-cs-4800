/*taken from https://stackoverflow.com/questions/9342233/zoom-in-and-out-of-images-in-java as a way to zoom in on the image
 * This code allows one to zoom in on an image like we need to do with the map we are creating
 */
package tutorial.Gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImageLabel extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener{
    private final BufferedImage image;
    private double zoomFactor = 1;
    private double prevZoomFactor = 1;
    private boolean zoomer;
    private boolean dragger;
    private boolean released;
    private double xOffset = 0;
    private double yOffset = 0;
    private int xDiff;
    private int yDiff;
    private Point startPoint;

    public ImageLabel(BufferedImage image){
        this.image = image;
        initComponent();
    }

    private void initComponent() {
        addMouseWheelListener(this);
        System.out.println("added 1!");
        addMouseMotionListener(this);
        System.out.println("added 2!");
        addMouseListener(this);
        System.out.println("added 3!");
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;

        if (released == false && zoomer == true){
            AffineTransform at = new AffineTransform();

            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zoomDiv = zoomFactor / prevZoomFactor;

            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

            at.translate(xOffset, yOffset);
            at.scale(zoomFactor, zoomFactor);
            prevZoomFactor = zoomFactor;
            g2.transform(at);
            zoomer = false;
        }
        else {
            if (zoomer) {
            AffineTransform at = new AffineTransform();

            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zoomDiv = zoomFactor / prevZoomFactor;

            xOffset = (zoomDiv) * (xOffset) + (1 - zoomDiv) * xRel;
            yOffset = (zoomDiv) * (yOffset) + (1 - zoomDiv) * yRel;

            at.translate(xOffset, yOffset);
            at.scale(zoomFactor, zoomFactor);
            prevZoomFactor = zoomFactor;
            g2.transform(at);
            zoomer = false;
        }

        if (dragger) {
            AffineTransform at = new AffineTransform();
            at.translate(xOffset + xDiff, yOffset + yDiff);
            at.scale(zoomFactor, zoomFactor);
            g2.transform(at);

            if (released) {
                xOffset += xDiff;
                yOffset += yDiff;
                dragger = false;
            }

        }
    }

        // All drawings go here
        
        g2.drawImage(image, 0, 0, this);

    }
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoomer = true;

        //Zoom in
        if (e.getWheelRotation() < 0) {
            this.zoomFactor *= 1.1;
            repaint();
            //ImageIcon zoomedImage = getScaledImage(imageIcon, newHeight, newWidth);
            //originalLabel.setIcon(zoomedImage);
            //System.out.println(zoomedImage.getIconWidth());
            //System.out.println(zoomedImage.getIconHeight());

        }
        //Zoom out
        if (e.getWheelRotation() > 0) {
            this.zoomFactor /= 1.1;
            repaint();
            //ImageIcon zoomedImage = getScaledImage(imageIcon, newHeight, newWidth);
            //originalLabel.setIcon(zoomedImage);
            //System.out.println(zoomedImage.getIconWidth());
            //System.out.println(zoomedImage.getIconHeight());
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
        released = false;
        this.startPoint = MouseInfo.getPointerInfo().getLocation();
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        released = true;
        repaint();
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        Point curPoint = e.getLocationOnScreen();
        xDiff = curPoint.x - startPoint.x;
        yDiff = curPoint.y - startPoint.y;

        dragger = true;
        repaint();
    }
}

