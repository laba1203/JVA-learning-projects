package lessons.tasks;


public class BirdsShop
{

    static int bTypes = 1; //bird Types
    static int bData = 3; // values of each bird Type: 0 - price; 1 - countInShop; 2 - soledCount

    //    static String [][] data = new String[bTypes][bData];
    static String [] names = new String[bTypes];
    static int[][] values = new int[bTypes][bData];


    public static void main(String[] args) {
        addNewBirdType("Chicken", 30);
        addNewBirdType("Eagle", 55);
        addBirds("Eagle", 32);
        addBirds("Chicken", 5);

        printAllData();

        sellBird("Chicken", 1);
        sellBird("Eagle", 5);
        System.out.println("\r");

        printAllData();

        System.out.println("\r\r");
        System.out.println("Sold Count: " + getSoldCountOfAllTypes());

    }



    static void addBirds (String birdType, int birdsCount)
    {
        int[] birdValues = getBirdValuesByType(birdType);
        birdValues[1] += birdsCount;
    }

    static void sellBird(String birdType, int birdCount)
    {
        int[] birdValues = getBirdValuesByType(birdType);
        birdValues[1] -= birdCount;
        birdValues[2] += birdCount;
    }

    static int getSoldCountOfAllTypes()
    {
        int count = 0;
        for (int[] arr : values)
        {
            count += arr[2];
        }
        return count;
    }


    static int getSoldCountForBirdType(String birdType)
    {
        return values[getBirdIndex(birdType)][2];
    }

    static void addNewBirdType(String birdType, int price)
    {
        int[][] tempIntArray = values;
        String [] tempNamesArray = names;

        values = new int [bTypes + 1] [4];
        names = new String[bTypes + 1];

        for (int i = 0; i < tempNamesArray.length; i++)
        {
            names[i] = tempNamesArray[i];
            values[i] = tempIntArray[i];
        }
        names[bTypes - 1] = birdType;
        values[bTypes - 1][0] = price;
        values[bTypes - 1][1] = 0;
        values[bTypes - 1][2] = 0;

        bTypes++;

    }


    static void printAllData()
    {

        System.out.print("\n  Data table(New approach)" + "\nName, Price, Count, SoldCount");
        for(int i = 0; i < names.length; i++){
            System.out.print("\n" + names[i]);
            for (int j = 0; j < values[i].length; j++){
                System.out.print("| " + values[i][j]);
            }
        }
    }


    static int[] getBirdValuesByType(String birdType)
    {
        return values[getBirdIndex(birdType)];
    }


    static int getBirdIndex(String birdType)
    {
        int index = -1;

        for (int i = 0; i < names.length; i++)
        {
            if (birdType.equals(names[i]))
            {
                index = i;
            }
        }
        return index;
    }
}

