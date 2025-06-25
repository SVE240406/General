public class Aufgabe7 {
    private int[] arr = {4,2,10,3,-5,0,17};

    public Aufgabe7() {
        print();
        swap(arr, 3, 5);
        print();
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void print() {
        for(int i : arr)
            System.out.printf("%d ", i);
        System.out.println();
    }

    public static void main(String[] args) {
        new Aufgabe7();
    }
}
