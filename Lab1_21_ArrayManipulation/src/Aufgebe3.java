import java.util.Random;

public class Aufgebe3 {
    public static Random rand = new Random();

    private static final int[] werte = new int[1000];
    private static final int[] anzWerte = new int[12];

    public static void main(String[] args) {
        for (int i = 0; i < werte.length; i++)
            werte[i] = rand.nextInt(1, 13);
        for (int i : werte)
            anzWerte[i-1]++;
        for (int i = 1; i <= anzWerte.length; i++)
            System.out.printf("%02dV: %03d\n", i, anzWerte[i-1]);
    }
}
