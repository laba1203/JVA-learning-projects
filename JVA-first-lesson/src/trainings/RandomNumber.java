package trainings;

/**
 * Created by Family on 25-Sep-17.
 */
public class RandomNumber {
    public static void main(String[] args) {
        int i = 0;
        while (i<30) {
            i++;
            int random = getRandomNumber();
            System.out.println(random);
            if(random < 1 && random > 4){
                System.out.println("incorrect number: " + random );
            }
        }

    }

    static int getRandomNumber(){

        String numberString = System.currentTimeMillis() + "";
        int i = Integer.parseInt(numberString.substring(numberString.length() - 1));

        return (i % 4) + 1;

    }
}
