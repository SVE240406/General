public class Aufgabe9 {
    private int[] arr = {3,4,1,9,-5,4};

    public Aufgabe9() {
        print();
        arr = sumUp(arr);
        print();
    }

    public int[] sumUp(int[] a){
        for(int i = 0; i<a.length; i++){
            if(i != 0){
                a[i] = a[i] + a[i-1];
            }
        }
        return a;
    }

    public void print() {
        for (int i : arr)
            System.out.printf("%3d", i);
        System.out.println();
    }

    public static void main(String[] args) {
        new Aufgabe9();
    }
}
