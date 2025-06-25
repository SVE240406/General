import java.math.BigDecimal;

public class Firma {
    private String name;
    private Person[] persons;
    private int posMitarbeiter = 0;

    public Firma(String name, int anzahlMitarbeiter) {
        if(name != null)
            this.name = name;
        else {
            //...
        }

        if(anzahlMitarbeiter >= 20 && anzahlMitarbeiter <= 100)
            persons = new Person[anzahlMitarbeiter];
        else {
            //...
        }
    }

    public boolean addMitarbeiter(Person mitarbeiter) {
        if(mitarbeiter != null && posMitarbeiter < persons.length){
            persons[posMitarbeiter] = mitarbeiter;
            posMitarbeiter++;
            return true;
        }
        return false;
    }

    public Person[] getMitarbeiter(int minAge){
        int anzahl = 0;
        for (int i = 0; i < posMitarbeiter; i++) {
            if(persons[i] != null && persons[i].getAlter() >= minAge)
                anzahl++;
        }
        Person[] ret = new Person[anzahl];
        anzahl = 0;
        for (int i = 0; i < posMitarbeiter; i++) {
            if(persons[i] != null && persons[i].getAlter() >= minAge) {
                ret[anzahl] = persons[i];
                anzahl++;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Firma firma = new Firma("FIRMA4U", 20);
        Person p1 = new Person("Tom","Guy", 33);
        Person p2 = new Person("Tim","Guy", 32);
        System.out.println("Mitarbeiter wurde " + ((firma.addMitarbeiter(p1))?"":"nicht ") + "erfolgreich aufgenommen");
        System.out.println("Mitarbeiter wurde " + ((firma.addMitarbeiter(p2))?"":"nicht ") + "erfolgreich aufgenommen");

        Person[] mitarbeiter = firma.getMitarbeiter(33);
        if(mitarbeiter != null)
            for(Person p : mitarbeiter)
                System.out.println(p);

        int i = 101;
        BigDecimal zaehler = BigDecimal.valueOf(i);
        zaehler = zaehler.add(BigDecimal.TEN);
        zaehler = zaehler.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
