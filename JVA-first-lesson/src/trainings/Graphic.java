package trainings;

/**
 * Created by Family on 24-Sep-17.
 */
public class Graphic {
    public static void main(String[] args) {

        String usual = "      ";
        String symbol = "ХОРОШО";
        int i = 0;
        int j;
        int columnSize = 10;
        int rowSize = 10;

        for (j = 0; j <= rowSize; j++) {
            for (i = 0; i <= columnSize; i++) {
                if (i == j){
                    System.out.print(symbol);
                }else {
                    System.out.print(usual);
                }
            }
            System.out.println("");
        }
//        System.out.println(i);
//        System.out.println(j);

        for (j = rowSize; j >= 0; j--) {
            for (i = 0; i != columnSize; i++) {
                if (i == j){
                    System.out.print(symbol);
                }else {
                    System.out.print(usual);
                }
            }
            System.out.println("");
        }

    }
}
