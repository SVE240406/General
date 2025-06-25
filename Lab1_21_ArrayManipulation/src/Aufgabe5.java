import java.util.Random;

public class Aufgabe5 {
    public static Random rand = new Random();

    private static final int[] feld1=new int[20];
    private static int[] feld2=new int[20];
    private static int[] feld3=new int[20];

    public static void main(String[] args) {
        fuelleFeld1();
        fuelleFeld2();
        fuelleFeld3();

        print();
    }

    public static void fuelleFeld1() {
        for (int i = 0; i < feld1.length; i++)
            feld1[i] = rand.nextInt(Integer.MAX_VALUE/2)*2;
    }

    public static void fuelleFeld2() {
        for (int i = 0; i < feld2.length; i++)
            feld2[i] = rand.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        feld2 = sort(feld2, false);
    }

    public static void fuelleFeld3() {
        for (int i = 0; i < feld3.length; i++)
            feld3[i] = rand.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        feld3 = sort(feld3, true);
    }


    private static int[] sort(int[] arr, boolean StoL){
        int current = ((StoL)?Integer.MAX_VALUE:Integer.MIN_VALUE);
        int currentPos = 0;
        for (int i = arr.length-1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if(StoL) {
                    if (arr[j] < current) {
                        current = arr[j];
                        currentPos = j;
                    }
                }else{
                    if (arr[j] > current) {
                        current = arr[j];
                        currentPos = j;
                    }
                }
            }
            arr[currentPos] = arr[i];
            arr[i] = current;
            current = ((StoL)?Integer.MAX_VALUE:Integer.MIN_VALUE);
        }
        return arr;
    }


    public static void print() {
        System.out.println("Feld1: ");
        for(int i : feld1)
            System.out.printf("% 11d\n", i);
        System.out.println("\nFeld2: ");
        for(int i : feld2)
            System.out.printf("% 11d\n", i);
        System.out.println("\nFeld3: ");
        for(int i : feld3)
            System.out.printf("% 11d\n", i);
    }
}