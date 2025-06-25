import java.util.Arrays;
import java.util.Random;

public class Muster {
    private static Random rand = new Random();
    private char[][] zeichnung;

    public Muster() {
        zeichnung = new char[10][10];
        init();
    }

    public Muster(int rows, int cols) {
        zeichnung = new char[rows][cols];
        init();
    }

    public void init(){
        for(char[] row : zeichnung)
            Arrays.fill(row, ' ');
    }

    public void ausgeben() {
        StringBuilder sb=new StringBuilder();
        for (char[] chars : zeichnung) {
            for (char aChar : chars)
                sb.append(aChar).append(' ');
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public void quadrate(int len, int anz){
        for (int a = 0; a < anz; a++) {
            int x = rand.nextInt(zeichnung.length - len);
            int y = rand.nextInt(zeichnung[x].length - len);

            for (int i = x; i < x + len; i++) {
                for (int j = y; j < y + len; j++) {
                    if (zeichnung[i][j] == ' ')
                        zeichnung[i][j] = 'A';
                    else
                        zeichnung[i][j] = (char) (zeichnung[i][j] + 1);
                }
            }
        }
    }

    public void plusZeichnen(int len, int anz, char zeichen){
        if(len%2==0)
            len++;
        for (int a = 0; a < anz; a++) {
            int x = rand.nextInt(zeichnung.length - len);
            int y=rand.nextInt(zeichnung[x].length - len);

            for (int i = x; i < x + len; i++)
                zeichnung[i][y+len/2] = zeichen;
            for (int i = y; i < y + len; i++)
                zeichnung[x+len/2][i] = zeichen;
        }
    }

    public void malZeichnen(int len, int anz, char zeichen){
        if(len%2==0)
            len++;
        for (int a = 0; a < anz; a++) {
            int x = rand.nextInt(zeichnung.length - len);
            int y=rand.nextInt(zeichnung[x].length - len);

            for (int i=x, j=y; i<x+len&&j<y+len; i++, j++)
                zeichnung[i][j] = zeichen;
            for (int i=x, j=y+len-1; i<x+len&&j>=y; i++, j--)
                zeichnung[i][j] = zeichen;
        }
    }

    public void invert(){
        for (int i = 0; i < zeichnung.length; i++) {
            for (int j = 0; j < zeichnung[i].length; j++) {
                if(zeichnung[i][j] == ' ')
                    zeichnung[i][j] = 'o';
                else
                    zeichnung[i][j] = ' ';
            }
        }
    }

    public void spiegeln(){
        for (int i = 0; i < zeichnung.length; i++) {
            for (int j = 0; j < zeichnung[i].length/2; j++) {
                char temp = zeichnung[i][j];
                zeichnung[i][j] = zeichnung[i][zeichnung[i].length-j-1];
                zeichnung[i][zeichnung[i].length-j-1] = temp;
            }
        }
    }

    public void loesche(int element, boolean spalte){
        if(spalte){
            for (int i = 0; i < zeichnung.length; i++)
                for (int j = element; j < zeichnung[i].length-1; j++)
                    zeichnung[i][j] = zeichnung[i][j+1];
            Arrays.fill(zeichnung[zeichnung.length-1], ' ');
        }else{
            for (int i = element; i < zeichnung.length-1; i++)
                zeichnung[i] = zeichnung[i+1];
            Arrays.fill(zeichnung[zeichnung.length-1], ' ');
        }
    }

    public void verschiebe(boolean rechts){
        char[][] arr = new char[zeichnung.length][zeichnung[0].length];
        if(rechts){
            for (int i = 0; i < zeichnung.length; i++) {
                int k = zeichnung[i].length-1;
                for (int j = zeichnung[i].length - 1; j >= 0; j--) {
                    if (zeichnung[i][j] != ' ') {
                        arr[i][k] = zeichnung[i][j];
                        k--;
                    }
                }
                for (int j = k; j >= 0; j--)
                    arr[i][j] = ' ';
            }
        }else{
            for (int i = 0; i < zeichnung.length; i++) {
                int k = 0;
                for (int j = 0; j < zeichnung[i].length; j++) {
                    if (zeichnung[i][j] != ' ') {
                        arr[i][k] = zeichnung[i][j];
                        k++;
                    }
                }
                for (int j = k; j < arr[i].length; j++)
                    arr[i][j] = ' ';
            }
        }
        zeichnung = arr;
    }
}
