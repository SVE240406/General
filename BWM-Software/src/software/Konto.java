package software;

import java.util.ArrayList;

import static main.Constants.KONTO_NUM_FORMAT;

public class Konto {
    private int kontoNum;
    public KontoTyp kontoTyp;
    private String name;
    private ArrayList<Buchung> buchungen = new ArrayList<>();

    public Konto(int kontoNum, String name, KontoTyp kontoTyp, float ab, boolean soll) {
        this.kontoNum = kontoNum;
        this.kontoTyp = kontoTyp;
        this.name = name;
        if(soll)
            buchungen.add(new Buchung(null, new Konto(0, "AB", kontoTyp.GRAU), ab, 0));
        else
            buchungen.add(new Buchung(null, new Konto(0, "AB", kontoTyp.GRAU), 0, ab));
    }
    public Konto(int kontoNum, String name, KontoTyp kontoTyp) {
        this.kontoNum = kontoNum;
        this.kontoTyp = kontoTyp;
        this.name = name;
    }

    public void addBuchung(Buchung buchung) {
        buchungen.add(buchung);
    }

    public KontoTyp getKontoTyp() {
        return kontoTyp;
    }
    public Buchung getBuchung(int index){
        return buchungen.get(index);
    }
    public int getKontoNum() {
        return kontoNum;
    }
    public int numOfBuchungen(){
        return buchungen.size();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if(kontoNum != 0)
            sb.append(KONTO_NUM_FORMAT.format(kontoNum)).append(" ");
        sb.append(name);
        return sb.toString();
    }

    public int compareTo(Konto konto) {
        return Integer.compare(kontoNum, konto.getKontoNum());
    }
}
