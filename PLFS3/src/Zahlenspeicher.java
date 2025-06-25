import java.util.Random;
import java.util.Scanner;

public class Zahlenspeicher {
    private final Scanner scn = new Scanner(System.in);
    private final int[] zahlen;

    public Zahlenspeicher(int n) {
        if (n >= 20 && n <= 50)
            zahlen = new int[n];
        else
            zahlen = new int[25];

        for (int i = 0; i < zahlen.length; i++) {
            Random rand = new Random();
            zahlen[i] = rand.nextInt(0, 101);
            for (int j = 0; j < i; j++) {
                if (zahlen[j] == zahlen[i]) {
                    i--;
                    break;
                }
            }
        }
    }

    public Zahlenspeicher() {
        zahlen = new int[10];
        for (int i = 0; i < zahlen.length; i++)
            zahlen[i] = zahlVonConsole();
        scn.close();
    }

    private int zahlVonConsole() {
        while (scn.hasNext()) {
            if (scn.hasNextInt()) {
                int i = scn.nextInt();
                if (i >= 1 && i <= 50)
                    return i;
            } else
                scn.next();
        }
        return -1;
    }

    public void dreheZahlen() {
        for (int i = 0, j = zahlen.length - 1; i < zahlen.length / 2; i++, j--) {
            int temp = zahlen[i];
            zahlen[i] = zahlen[j];
            zahlen[j] = temp;
        }
    }

    public int verschiebeElement(int pos){
        int count = 0;
        if(pos >= 0 && pos < zahlen.length){
            for (int i = pos; i < zahlen.length; i++) {
                if(zahlen[i] > zahlen[i+1]){
                    int temp = zahlen[i];
                    zahlen[i] = zahlen[i+1];
                    zahlen[i+1] = temp;
                    count++;
                }else
                    break;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Anzahl der Elemente -> ").append(zahlen.length).append("\n");
        for (int i = 0; i < zahlen.length; i++)
            sb.append("Element: ").append(i).append("; Wert: ").append(zahlen[i]).append("\n");
        return sb.toString();
    }

    public static void main(String[] args) {
        Zahlenspeicher z = new Zahlenspeicher();
        System.out.println("Element wurde um " + z.verschiebeElement(0) + " verschoben");
        System.out.println(z);
    }
}