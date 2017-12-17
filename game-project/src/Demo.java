import javax.swing.*;
import java.awt.*;

/**
 * Created by Family on 08-Oct-17.
 */
public class Demo {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;


    public static void main(String[] args) {
        Game game = new Game();
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        JFrame frame = new JFrame(Game.NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(game, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        game.start();
    }

}
