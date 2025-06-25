import java.util.Scanner;

public class Klausurauswertung {
    public static Scanner scn = new Scanner(System.in);

    private static int[] schuelernoten;

    public static void main(String[] args) {
        schuelernotenZuweisen();
        notenwerteBerechnen();
        drawDiagram();
    }

    private static void schuelernotenZuweisen(){
        System.out.print("Bitte geben sie die Anzahl der Schüler an: ");
        while (scn.hasNext()){
            if (scn.hasNextInt()){
                int a = scn.nextInt();
                if (a > 0)
                    schuelernoten = new int[a];
                break;
            }
            else
                scn.next();
        }
        for (int i = 0; i < schuelernoten.length; i++) {
            System.out.printf("Bitte geben Sie die Note des Schülers %d ein: ", i + 1);
            while (scn.hasNext()){
                if (scn.hasNextInt()){
                    int a = scn.nextInt();
                    if(a > 0 && a <=5) {
                        schuelernoten[i] = a;
                        break;
                    }
                }
                else
                    scn.next();
            }
        }
    }

    private static void notenwerteBerechnen(){
        System.out.println("Maximum: " + max() + "\nMinimum: " + min() + "\nMittelwert: " + average() + "\nAnteil negativer Noten: " + anteilNegativ() + "%\n");
    }

    private static void drawDiagram(){
        int[] noten = new int[5];
        for(int i : schuelernoten)
            noten[i-1]++;
        for (int i = 0; i < noten.length; i++) {
            System.out.print((i+1)+" | ");
            for (int j = 0; j < noten[i]; j++)
                System.out.print("*");
            System.out.println();
        }
    }


    private static int sum(){
        int sum = 0;
        for (int d : schuelernoten)
            sum += d;
        return sum;
    }

    private static int max(){
        int max = Integer.MIN_VALUE;
        for(int i : schuelernoten)
            max = Math.max(max, i);
        return max;
    }

    private static int min(){
        int min = Integer.MAX_VALUE;
        for(int i : schuelernoten)
            min = Math.min(min, i);
        return min;
    }

    private static int average(){
        return sum() / schuelernoten.length;
    }

    private static double anteilNegativ(){
        int anzahlNeg = 0;
        double anteilNeg;
        for(int i : schuelernoten)
            if(i == 5) anzahlNeg++;
        anteilNeg = (double) anzahlNeg/ schuelernoten.length*100;
        return anteilNeg;
    }
}
