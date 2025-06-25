public class Aufgabe8 {
    private int[] arr = {3,4,1,9,-5,4};
    private int arrLength = arr.length;

    public Aufgabe8() {
        print();
        arr = delElement(arr, 9);
        print();
        arr = delElement(arr, 4);
        print();
    }

    public int[] delElement(int[] a, int w) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if(a[i] == w) {
                count++;
                for (int j = i; j < a.length-count; j++)
                    a[j] = a[j+count];
                a[a.length-1] = 0;
                arrLength--;
            }
        }
        return a;
    }

    public void print() {
        for (int i = 0; i < arrLength; i++)
            System.out.printf("%d ", arr[i]);
        System.out.println();
    }

    public static void main(String[] args) {
        new Aufgabe8();
    }
}
