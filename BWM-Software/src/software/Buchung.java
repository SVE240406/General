package software;

import java.util.GregorianCalendar;

import static main.Constants.*;

public class Buchung {
    private GregorianCalendar date;
    private Konto gegenk;
    private float soll, haben;

    public Buchung(GregorianCalendar date, Konto gegenk, float soll, float haben) {
        this.date = date;
        this.gegenk = gegenk;
        this.soll = soll;
        this.haben = haben;
    }

    public GregorianCalendar getDate() {
        return date;
    }
    public String getDateString() {
        if(date == null) return "";
        DATE_FORMAT.setCalendar(date);
        return DATE_FORMAT.format(date.getTime());
    }
    public Konto getGegenk() {
        return gegenk;
    }
    public float getSoll(){
        return soll;
    }
    public String getSollS() {
        return getPrice(soll);
    }
    public float getHaben() {
        return haben;
    }
    public String getHabenS() {
        return getPrice(haben);
    }
    public String getPrice(float sh){
        String ret = DECIMAL_FORMAT.format(sh);
        return (ret.equals("0,00€"))?"":ret;
    }
}
