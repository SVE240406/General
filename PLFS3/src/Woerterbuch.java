import java.util.Arrays;

public class Woerterbuch {
    private String[] woerter;
    private int aktpos = 0;

    public Woerterbuch(int n) {
        if(n >= 5 && n <= 20)
            woerter = new String[n];
        else
            woerter = new String[10];
        Arrays.fill(woerter, "");
    }

    public boolean addWord(String word) {
        if(word != null && !word.isEmpty()){
            if(aktpos != woerter.length) {
                woerter[aktpos] = word;
                aktpos++;
                return true;
            }
        }
        return false;
    }

    public boolean delWord(int pos) {
        if(pos >= 0 && pos < aktpos) {
            woerter[pos] = "";
            fuellLuecke(pos);
            aktpos--;
        }
        return false;
    }

    private void fuellLuecke(int pos) {
        for (int i = pos; i < aktpos-1; i++)
            woerter[i] = woerter[i+1];
        woerter[aktpos-1] = "";
    }

    public boolean containsWord(String subword) {
        for (int i = 0; i < aktpos; i++) {
            if (woerter[i].equalsIgnoreCase(subword))
                return true;
        }
        return false;
    }

    public int startWihWord(String text){
        int count = 0;
        if(text != null && !text.isEmpty()) {
            for (int i = 0; i < aktpos; i++)
                if (woerter[i].startsWith(text))
                    count++;
        }
        return count;
    }

    @Override
    public String toString() {
        return "Woerterbuch{" +
                "woerter=" + Arrays.toString(woerter) +
                '}';
    }

    public static void main(String[] args) {
        Woerterbuch w = new Woerterbuch(5);
        System.out.println(w);
        if(w.addWord("Hans"))
            System.out.println("Hans wurde eingetragen");
        else
            System.out.println("Wörterbuch ist voll");
        System.out.println(w);

        System.out.println(w.delWord(200));
    }
}
