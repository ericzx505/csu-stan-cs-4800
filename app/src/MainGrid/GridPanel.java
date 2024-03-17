package main;

import java.awt.Dimension;
import javax.swing.JPanel;

public class GridPanel extends JPanel implements Runnable{
    // Set Screen Settings

    final int originalTileSize = 16; // 16x16 tile standard size of the map
    final int scale = 3; // this is a multiplier of the original size 16 x 3 = 48

    final int tileSize = originalTileSize * scale;// Here we multiply scale and tile size
    final int maxScreenCol = 16; // Max column on screen
    final int maxScreenRow = 12; // Max Row on screen 16x12
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels total
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels total of the screen (This numbers can be changed any
                                                      // time)

    Thread gridThread;// Thread so program can do more than one thing at the time

    // Constructor of Grid panel
    public GridPanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));// setting panel size
       // this.setBackground(Color.black);
        this.setDoubleBuffered(true);// better rendering performance
    }

    public void startGridThread(){

        gridThread = new Thread(this);// passing gridpanel to this constructor
    }

    public void run(){ // when grid thread is called run method. Also will create the loop which will be the core of the grid

        while(gridThread != null){

            //system.out.println("The loop is running!");
        }
    }

}