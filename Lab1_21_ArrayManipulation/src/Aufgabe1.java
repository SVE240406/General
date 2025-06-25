import java.util.Scanner;

public class Aufgabe1 {
    public static Scanner scn = new Scanner(System.in);

    private static final double[] werte = new double[10];

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            while(scn.hasNext()){
                if(scn.hasNextDouble()){
                    werte[i] = scn.nextDouble();
                    break;
                }else
                    scn.next();
            }
        }
        scn.close();

        System.out.printf("Sum %04.2f\n", sum());
        System.out.printf("Minimum: %04.2f, Maximum: %04.2f\n", min(), max());
        System.out.printf("Mittelwerte: %04.2f\n", average());
        System.out.printf("Anzahl der positiven werte: %d\n\n", anzahlPositiv());
    }

    public static double sum(){
        double sum = 0;
        for (double d : werte)
            sum += d;
        return sum;
    }

    public static double min(){
        double min = Double.MAX_VALUE;
        for(double d : werte)
            min = Math.min(min, d);
        return min;
    }

    public static double max(){
        double max = Double.MIN_VALUE;
        for(double d : werte)
            max = Math.max(max, d);
        return max;
    }

    public static double average(){
        return sum() / werte.length;
    }

    public static int anzahlPositiv(){
        int anz = 0;
        for(double d : werte)
            if(d > 0) anz++;
        return anz;
    }
}
