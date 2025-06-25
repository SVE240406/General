import java.util.Random;
import java.util.Scanner;

public class Aufgabe2 {
    public static Scanner scn = new Scanner(System.in);
    public static Random rand = new Random();

    private static int anz;
    private static int[] tütchen;

    public static void main(String[] args) {
        while (scn.hasNext()){
            if (scn.hasNextInt()){
                anz = scn.nextInt();
                scn.close();
                break;
            }else
                scn.next();
        }
        tütchen = new int[anz];
        fülleTütchen();
        System.out.println(testeTütchen());
    }

    private static void fülleTütchen(){
        for (int i = 0; i < anz; i++) {
            tütchen[i] = rand.nextInt(10, 16);
            if(tütchen[i] > 14)
                tütchen[i] = 12;
        }
    }

    private static int testeTütchen(){
        int voll = 0;
        for(int i : tütchen)
            if(i == 12) voll++;
        return voll;
    }
}
