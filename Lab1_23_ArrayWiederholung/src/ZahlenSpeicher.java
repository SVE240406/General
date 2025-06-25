import java.util.Random;
import java.util.Scanner;

public class ZahlenSpeicher {
    private static Scanner scn = new Scanner(System.in);
    private final int[] zahlen;

    public ZahlenSpeicher(int n) {
        if (n >= 20 && n <= 50)
            zahlen = new int[n];
        else {
            System.err.println("Arraygröße nicht erlaubt!!!");
            zahlen = new int[25];
        }
        for (int i = 0; i < zahlen.length; i++) {
            boolean isContained;
            Random rand = new Random();
            do {
                isContained = false;
                zahlen[i] = rand.nextInt(0, 101);
                for (int j = 0; j < i; j++) {
                    if (zahlen[j] == zahlen[i]) {
                        isContained = true;
                        break;
                    }
                }
            } while (isContained);
        }
    }

    public ZahlenSpeicher() {
        zahlen = new int[10];
        for (int i = 0; i < zahlen.length; i++)
            zahlen[i] = zahlVonConsole();
        scn.close();
    }

    private int zahlVonConsole() {
        while (scn.hasNext()) {
            if(scn.hasNextInt()){
                int i = scn.nextInt();
                if(i >= 1 && i <= 50)
                    return i;
                System.err.println("Unerlaubte Zahl!!!");
            }else {
                System.out.println("Keine Zahl angegeben!!!");
                scn.next();
            }
        }
        return -1;
    }

    public void dreheZahl(){
        for (int i = 0, j = zahlen.length-1; i < j; i++, j--) {
            int temp = zahlen[i];
            zahlen[i] = zahlen[j];
            zahlen[j] = temp;
        }
    }

    public void verschiebeElement(int position){
        if(position < 0 || position >= zahlen.length){
            System.err.println("Unmögliche Position!!!");
            return;
        }
        for (int i = position; i < zahlen.length; i++) {
            if(zahlen[i] <= zahlen[i+1])
                break;
            int temp = zahlen[i];
            zahlen[i] = zahlen[i+1];
            zahlen[i+1] = temp;
        }
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
        System.out.println("Initialisieren von Zahlenspeicher1 mit 19:");
        ZahlenSpeicher z1 = new ZahlenSpeicher(19);
        System.out.println(z1);
        System.out.println("Umdrehen des Zahlenspeichers1:");
        z1.dreheZahl();
        System.out.println(z1);
        System.out.println("Verschieben des Elements 33 in Zahlenspeicher1:");
        z1.verschiebeElement(33);
        System.out.println(z1);

        System.out.println("Initialisieren von Zahlenspeicher2 mit Consolenzahlen:");
        ZahlenSpeicher z2 = new ZahlenSpeicher();
        System.out.println(z2);
        System.out.println("Umdrehen des Zahlenspeichers2:");
        z2.dreheZahl();
        System.out.println(z2);
        System.out.println("Verschieben des Elements 4 in Zahlenspeicher2:");
        z2.verschiebeElement(4);
        System.out.println(z2);
    }
}
