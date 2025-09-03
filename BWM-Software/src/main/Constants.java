package main;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Constants {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.");
    public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,##0.00€");
    public static final DecimalFormat KONTO_NUM_FORMAT = new DecimalFormat("0000");

    public static GregorianCalendar getCalendar(String datum) {
        String[] dateParts = datum.split("\\.");
        return new GregorianCalendar(2025, Integer.parseInt(dateParts[1])-1, Integer.parseInt(dateParts[0]));
    }
}
