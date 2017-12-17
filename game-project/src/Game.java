import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Created by Family on 08-Oct-17.
 */
public class Game extends Canvas implements Runnable
{
    private boolean running;
    public static String NAME = "First Game";


    public void start(){
        running = true;
        new Thread(this).start();
    }


    @Override
    public void run() {

    }

    public void initiate(){

    }

    public void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(2);
            requestFocus();

        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.cyan);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.dispose();
        bs.show();

    }

    public void update(long delta){

    }

}
