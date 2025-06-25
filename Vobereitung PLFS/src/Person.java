public class Person {
    private String vorname;
    private String nachname;
    private int alter;

    public Person(String vorname, String nachname, int alter) {
        setVorname(vorname);
        setNachname(nachname);
        setAlter(alter);
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        if(vorname != null)
            this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        if(nachname != null)
            this.nachname = nachname;
    }

    public int getAlter() {
        return alter;
    }

    public void setAlter(int alter) {
        if(alter > 18 && alter < 65)
            this.alter = alter;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("vorname='").append(vorname).append('\'');
        sb.append(", nachname='").append(nachname).append('\'');
        sb.append(", alter=").append(alter);
        sb.append('}');
        return sb.toString();
    }
}
