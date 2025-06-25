import java.util.Arrays;

public class WoerterBuch {
    private String[] woerter;
    private int aktpos = 0;

    public WoerterBuch(int n) {
        if (n >= 5 && n <= 20)
            woerter = new String[n];
        else
            woerter = new String[10];
        Arrays.fill(woerter, "");
    }

    public boolean addWord(String word) {
        if (word != null && !word.isEmpty()) {
            if (aktpos < woerter.length) {
                aktpos++;
                woerter[aktpos] = word;
                return true;
            }
        }
        System.out.println("Dieses Wort kann nicht agefügt werden!");
        return false;
    }

    public boolean delWord(int pos) {
        if (pos >= 0 && pos < aktpos) {
            woerter[pos] = "";
            fuelleLuwcke(pos);
            return true;
        }
        System.out.println("Ungültige Position!!!");
        return false;
    }

    private void fuelleLuwcke(int pos) {
        for (int i = pos; i < aktpos; i++)
            woerter[i] = woerter[i + 1];
        aktpos--;
        woerter[aktpos] = "";
    }

    public boolean containsWord(String subword) {
        if (subword != null && !subword.isEmpty()) {
            for (int i = 0; i < aktpos; i++) {
                if (woerter[i].contains(subword))
                    return true;
            }
            return false;
        }
        System.out.println("Unerlaubtes Wort!!!");
        return false;
    }

    public int startsWithWord(String text) {
        int count = 0;
        if (text != null && !text.isEmpty()) {
            for (int i = 0; i < aktpos; i++) {
                if (woerter[i].startsWith(text))
                    count++;
            }
        } else
            System.err.println("Ungültiger Text!!!");
        ;
        return count;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("\nWörterbuch:");
        for(int i = 0; i < aktpos; i++)
            sb.append("\n").append(woerter[i]);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Konstruktor testen:");
        WoerterBuch wb = new WoerterBuch(22);
        System.out.println(wb);

        System.out.println("\nWort anhängen testen:");
        System.out.println("Wort POS anhängen: " + wb.addWord("POS"));
        System.out.println("Wort ist anhängen: " + wb.addWord("ist"));
        System.out.println("Wort super anhängen: " + wb.addWord("super"));
        System.out.println("null anhängen: " + wb.addWord(null));
        System.out.println(wb);

        System.out.println("\nWort löschen testen:");
        System.out.println("Wort 4 loeschen: " + wb.delWord(4));
        System.out.println("Wort 2 loeschen: " + wb.delWord(2));
        System.out.println(wb);

        System.out.println("\nStertet mit Wort testen");
        System.out.println("Stertet mit sup: " + wb.startsWithWord("sup"));

        System.out.println("\nContains wort testen: ");
        System.out.println("contains POS: " + wb.containsWord("POS"));
    }
}
