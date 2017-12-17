package lessons.tasks.tank;


        import javax.swing.*;
        import java.awt.*;
        import java.util.Arrays;

public class T5_CleanBattleField extends JPanel
{

    final boolean COLORED_MODE = false;
    final boolean IS_GRID = true;

    final int BF_WIDTH = 576;
    final int BF_HEIGHT = 576;

    // 1 - top, 2 - right, 3 - down, 4 - left
    int tankDirection = 1;

    int tankX = 128;
    int tankY = 512;

    int bulletX = -100;
    int bulletY = -100;

    int tankSpeed = 10;
    int bulletSpeed = 5;

    final String BRICK = "B";
    final String BLANK = " ";

    String[][] battleField = {
            {"B", "B", "B", "B", "B", "B", "B", "B", "B"},
            {" ", " ", " ", " ", " ", " ", " ", " ", "B"},
            {"B", "B", "B", " ", "B", " ", "B", "B", "B"},
            {"B", "B", "B", "B", " ", " ", "B", "B", "B"},
            {"B", "B", "B", " ", "B", " ", "B", "B", "B"},
            {"B", " ", " ", " ", "B", "B", " ", "B", "B"},
            {"B", "B", " ", " ", " ", " ", " ", "B", "B"},
            {"B", " ", " ", "B", "B", "B", " ", " ", "B"},
            {"B", " ", " ", "B", "B", "B", " ", " ", "B"}
    };

    void runTheGame() throws Exception
    {
        printCurrentBattleField();

        clean();
    }

    /**
     *
     *  When method called tank should destroy all the objects on battle field in less then 30 seconds.
     *
     */
    void clean()
    {
        int goalX = tankX;
        int goalY = tankY;
        int direction = 0;

        while (fieldCleared()) {
            while (checkFilledCellsInRow()) {
                turn(4);
                fire();
                turn(2);
                fire();
            }
            move(1);

            while (checkFilledCellsInColumn()) {
                turn(1);
                fire();
                turn(3);
                fire();
            }
        }

    }

    /**
     * Return true when filled cell exists in the row with tank.
     */
    boolean checkFilledCellsInRow(){
        int[] XY = getQuadrant(tankX, tankY);
//        int x = XY[0];
        int y = XY[1];
        int i = 0;

        String[] battleRow = battleField[y];

        for (String cell : battleRow){
            if (cell.equals("B")){
              i++;
            }
        }
        if(i == 0){
            return false;
        }
        return true;

    }

    boolean checkFilledCellsInColumn(){
        int[] XY = getQuadrant(tankX, tankY);
        int x = XY[0];
//        int y = XY[1];
        int counter = 0;


        for (int i = 0; i < battleField.length; i++)
        {
            if (battleField[i][x].equals("B")){
                counter++;
            }
        }

        if(counter == 0){
            return false;
        }
        return true;

    }

    boolean fieldCleared(){
        int counter = 0;
        for (String[] arr : battleField){
            for (String str : arr){
                if(str.equals("B")){
                    counter++;
                }
            }
        }
        if(counter == 0)
        {
            return false;
        }
        return true;
    }


    /**
     *
     * When called tank should produce new bullet.
     * This bullet should smoothly move to the opposite side.
     *
     * Bullet should be destroyed when rich the opposite side.
     *
     * When the bullet shoot something method would clean appropriate quadrant and destroy the bullet.
     */
    void fire()
    {
        bulletX = tankX + 25;
        bulletY = tankY + 25;

        while (isItemInFields(bulletX, bulletY))
        {
            if(checkAndProcessInterception(bulletX, bulletY)){
                break;
            }
            moveBullet(tankDirection);
        }

        battleField[bulletY/64][bulletX/64] = " ";
        bulletX = -100;



    }

    void moveBullet(int direction){
        int sleepTime = 4;

        sleep(sleepTime);
        bulletX += getDirectionX(getDirections(direction));
        bulletY += getDirectionY(getDirections(direction));
        repaint();

    }

    boolean isItemInFields(int x, int y)
    {
        if (x < 0 || (x > 576 - 14) || y < 0 || (y > 576 - 14))
        {
            return false;
        }
        return true;
    }

    /**
     *
     * Should return true if bullet located in non-empty quadrant.
     *
     */
    boolean checkAndProcessInterception(int pixelsX, int pixelsY)
    {
        int x = (pixelsY / 64);
        int y = (pixelsX / 64);

        if(battleField[x][y] == "B")
        {
            return true;
        }

        return false;
    }


    private void printCurrentBattleField()
    {
        for (String[] row : battleField)
        {
            System.out.println(Arrays.toString(row));
        }
    }

    int[] getQuadrant(int x, int y)
    {
        return new int[] {x / 64, y / 64};
    }



    void move(int direction)
    {
        int sleepTime = 5;
        int pixelsInQuadrant = 64;

        turn(direction);

        for (int i = 0; i < pixelsInQuadrant; i++)
        {
            if (isBorderAchieved())
            {
                break;
            }

            sleep(sleepTime);
            tankX += getDirectionX(getDirections(direction));
            tankY += getDirectionY(getDirections(direction));
            repaint();
        }

    }


    int[] getDirections(int direction)
    {
        int[] tempArray = {-1, 1, 1, -1};
        String str = "1234";
        int position = str.indexOf(direction + "");
        int[] array = {0, 0, 0, 0};

        array[position] = tempArray[position];

        int directionX = array[1] + array[3];
        int directionY = array[0] + array[2];

        int[] valuesXY = {directionX, directionY};

        return valuesXY;

    }

    int getDirectionX(int[] array)
    {
        return array[0];
    }

    int getDirectionY(int[] array)
    {
        return array[1];
    }

    boolean isBorderAchieved()
    {
        if (tankDirection == 1 && tankY < 0)
        {
            System.out.println("1");
            return true;
        }
        else if(tankDirection == 2 && tankX > 576 - 64)
        {
            System.out.println("2");
            return true;
        }
        else if (tankDirection == 3 && tankY > 576 - 64)
        {
            System.out.println(3);
            return true;
        }
        else if (tankDirection == 4 && tankX < 0)
        {
            System.out.println("4");
            return true;
        }

        return false;

//        int x = (pixelsY / 64);
//        int y = (pixelsX / 64);
//
//        if(battleField[x][y] == "B")
//        {
//            return true;
//        }
    }



    void turn(int direction)
    {
        tankDirection = direction;
        repaint();
    }

    // Magic bellow. Do not worry about this now, you will understand everything in this course.
    // Please concentrate on your tasks only.


    public static void main(String[] args) throws Exception
    {
        T5_CleanBattleField bf = new T5_CleanBattleField();
        bf.runTheGame();
    }

    public T5_CleanBattleField() throws Exception
    {
        JFrame frame = new JFrame("YOUR TANK SHOULD FIRE!!!");
        frame.setLocation(750, 150);
        frame.setMinimumSize(new Dimension(BF_WIDTH, BF_HEIGHT + 22));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
    }

    void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (Exception ignore)
        {
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        paintBF(g);

        paintBorders(g);

        g.setColor(new Color(255, 0, 0));
        g.fillRect(tankX, tankY, 64, 64);

        g.setColor(new Color(0, 255, 0));
        if (tankDirection == 1)
        {
            g.fillRect(tankX + 20, tankY, 24, 34);
        }
        else if (tankDirection == 3)
        {
            g.fillRect(tankX + 20, tankY + 30, 24, 34);
        }
        else if (tankDirection == 4)
        {
            g.fillRect(tankX, tankY + 20, 34, 24);
        }
        else
        {
            g.fillRect(tankX + 30, tankY + 20, 34, 24);
        }

        g.setColor(new Color(255, 255, 0));
        g.fillRect(bulletX, bulletY, 14, 14);
    }

    private void paintBorders(Graphics g)
    {
        for (int j = 0; j < battleField.length; j++)
        {
            for (int k = 0; k < battleField.length; k++)
            {
                if (battleField[j][k].equals("B"))
                {
                    String coordinates = getQuadrantXY(j + 1, k + 1);
                    int separator = coordinates.indexOf("_");
                    int y = Integer.parseInt(coordinates.substring(0, separator));
                    int x = Integer.parseInt(coordinates.substring(separator + 1));
                    g.setColor(new Color(0, 0, 255));
                    g.fillRect(x, y, 64, 64);

                    if (IS_GRID)
                    {
                        g.setColor(new Color(0, 0, 0));
                        g.drawRect(x, y, 64, 64);
                    }
                }
            }
        }
    }

    private void paintBF(Graphics g)
    {
        super.paintComponent(g);

        int i = 0;
        Color cc;
        for (int v = 0; v < 9; v++)
        {
            for (int h = 0; h < 9; h++)
            {
                if (COLORED_MODE)
                {
                    if (i % 2 == 0)
                    {
                        cc = new Color(252, 241, 177);
                    }
                    else
                    {
                        cc = new Color(233, 243, 255);
                    }
                }
                else
                {
                    cc = new Color(180, 180, 180);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h * 64, v * 64, 64, 64);
            }
        }
    }

    private String getQuadrantXY(int v, int h)
    {
        return (v - 1) * 64 + "_" + (h - 1) * 64;
    }

}
