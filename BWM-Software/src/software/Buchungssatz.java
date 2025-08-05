package software;

import java.util.GregorianCalendar;

import static main.Constants.*;

public class Buchungssatz {
    GregorianCalendar date;
    Konto kontoS, kontoH;
    float betrag;

    public Buchungssatz(GregorianCalendar date, Konto kontoS, Konto kontoH, float betrag) {
        this.date = date;
        this.kontoS = kontoS;
        this.kontoH = kontoH;
        this.betrag = betrag;
    }

    public void createBuchungen(){
        kontoS.addBuchung(new Buchung(date, kontoH, betrag, 0));
        kontoH.addBuchung(new Buchung(date, kontoS, 0, betrag));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DATE_FORMAT.setCalendar(date);
        sb.append(DATE_FORMAT.format(date.getTime()));
        sb.append(" ").append(kontoS);
        sb.append(" / ").append(kontoH);
        sb.append(" ").append(DECIMAL_FORMAT.format(betrag));
        return sb.toString();
    }
}
