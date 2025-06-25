import java.text.DecimalFormat;

public class Firma {
    public static final DecimalFormat df = new DecimalFormat("###,##0.00");

    private String name;
    private Angestelter[] angestelte = new Angestelter[10];
    private int anz = 0;

    public Firma(String name) {
        if (name != null)
            this.name = name;
    }

    public Angestelter einfuegen(String name, String abteilung, float gehalt) {
        for (Angestelter ang : angestelte)
            if (ang != null && ang.getName().equals(name))
                return null;
        if (anz != 10) {
            angestelte[anz] = new Angestelter(name, abteilung, gehalt);
            anz++;
            return angestelte[anz];
        }
        return null;
    }

    private int getPosAgestellter(String name) {
        for (int i = 0; i < anz; i++)
            if (angestelte[i].getName().equals(name))
                return i;
        return -1;
    }

    public Angestelter loeschen(String name) {
        Angestelter gefeuerter;
        int i = getPosAgestellter(name);
        if (i == -1){
            System.err.println("Diesen angestellten gibt es nicht!!");
            return null;
        }
        gefeuerter = angestelte[i];
        for (int j = i; j < anz; j++)
            angestelte[j] = angestelte[j + 1];
        angestelte[anz] = null;
        anz--;
        return gefeuerter;
    }

    public void ausgeben() {
        System.out.println("Firmenname: " + name);
        for (int i = 0; i < anz; i++)
            System.out.printf("Angestellter %02d: %s\n", i, angestelte[i]);
    }

    public Angestelter getAngestellten(int pos) {
        return angestelte[pos];
    }

    public void inAbteilungMit(Angestelter ang) {
        for (Angestelter ang1 : angestelte) {
            if (ang1 == null)
                return;
            if (ang1.gleicheAbteilung(ang))
                System.out.println(ang1);
        }
    }

    public Angestelter[] getAngestellte(char c) {
        Angestelter[] angestellte1 = null;
        for (Angestelter ang : angestelte) {
            if (ang == null)
                return angestellte1;
            String[] names = ang.getName().split(" ");
            if (names[names.length - 1].startsWith(String.valueOf(c))) {
                if (angestellte1 == null)
                    angestellte1 = new Angestelter[]{ang};
                else {
                    Angestelter[] angestellte2 = new Angestelter[angestellte1.length + 1];
                    for (int i = 0; i < angestellte1.length; i++)
                        angestellte2[i] = angestellte1[i];
                    angestellte2[angestellte1.length] = ang;
                    angestellte1 = angestellte2;
                }
            }
        }
        return angestellte1;
    }

    public void meistVerdiener() {
        if (anz == 0) {
            System.err.println("Keine Angestellten!");
            return;
        }
        Angestelter[] meistVerdiner = {angestelte[0]};
        for (int i = 1; i < anz; i++) {
            if (angestelte[i].getGehalt() > meistVerdiner[0].getGehalt())
                meistVerdiner = new Angestelter[]{angestelte[i]};
            else if (angestelte[i].getGehalt() == meistVerdiner[0].getGehalt()) {
                Angestelter[] meistVerdiner2 = new Angestelter[meistVerdiner.length + 1];
                for (int j = 0; j < meistVerdiner.length; j++)
                    meistVerdiner2[j] = meistVerdiner[j];
                meistVerdiner2[meistVerdiner.length] = angestelte[i];
                meistVerdiner = meistVerdiner2;
            }
        }
        System.out.print("Meißtverdienender Angestellter: ");
        for (Angestelter ang : meistVerdiner)
            System.out.println(ang);
    }

    public void sortAlphabetisch() {
        int currentPos;
        for (int i = anz - 1; i >= 0; i--) {
            Angestelter hoechsterAlphabet = angestelte[i];
            currentPos = i;
            for (int j = i - 1; j >= 0; j--) {
                if (hoechsterAlphabet.getAbteilung().compareToIgnoreCase(angestelte[j].getAbteilung()) == 0) {
                    if (hoechsterAlphabet.getName().compareToIgnoreCase(angestelte[j].getName()) < 0) {
                        hoechsterAlphabet = angestelte[j];
                        currentPos = j;
                    }
                } else if (hoechsterAlphabet.getAbteilung().compareToIgnoreCase(angestelte[j].getAbteilung()) < 0) {
                    hoechsterAlphabet = angestelte[j];
                    currentPos = j;
                }
            }
            angestelte[currentPos] = angestelte[i];
            angestelte[i] = hoechsterAlphabet;
        }
    }

    public void sortGehalt() {
        for (int i = anz - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (angestelte[j].getGehalt() > angestelte[j + 1].getGehalt()) {
                    Angestelter temp = angestelte[j];
                    angestelte[j] = angestelte[j + 1];
                    angestelte[j + 1] = temp;
                }
            }
        }
    }
}
