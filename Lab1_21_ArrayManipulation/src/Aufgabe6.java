import java.util.Random;

public class Aufgabe6 {
    public static Random rand = new Random();

    private static int[] arr = new int[10];

    public static void main(String[] args) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = rand.nextInt(1, 101);

        System.out.println(searchIndex(arr, 10));
    }

    public static int searchIndex(int[] a, int value) {
        for (int i : a)
            if (i == value)
                return i;
        return -1;
    }
}
