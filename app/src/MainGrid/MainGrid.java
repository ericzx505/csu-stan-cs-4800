package MainGrid;

public class MainGrid {

    public static void main(String[] args) {

        JFrame window = new JFrame("grid demo");// Creation of the window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// Window will close when 'X' is clicked
        window.setResizable(false); // window size can't be changed
        window.setTitle("Stan State Map"); // window Title

        GridPanel gridPanel = new GamePanel(); // Calling Grid Panel
        window.add(gridPanel);

        window.pack(); // So we can see the grid.

        window.setLocationRelativeTo(null);// Window will appear at the center of the screen
        window.setVisible(true);

        gridPanel.startGridThread();
    }
}
