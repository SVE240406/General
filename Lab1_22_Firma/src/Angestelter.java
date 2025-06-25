public class Angestelter {
    private String name;
    private String abteilung;
    private float gehalt;

    public Angestelter(String name, String abteilung, float gehalt) {
        if(name != null && name.contains(" "))
            this.name = name;
        else{
            this.name = "Mustermann Max";
            System.err.println("Ungültiger Name, der Name ist jetzt " + this.name);
        }

        if(abteilung != null)
            this.abteilung = abteilung;
        else{
            this.abteilung = "Sales";
            System.err.println("Ungültige Abteilung, die Abteilung ist jetzt " + this.abteilung);
        }

        if(gehalt > 0)
            this.gehalt = gehalt;
        else{
            this.gehalt = 60000;
            System.err.println("Gehalt muss größer als 0 sein, das Gehalt ist jetzt " + this.gehalt);
        }
    }

    public String getName() {
        return name;
    }
    public String getAbteilung() {
        return abteilung;
    }
    public float getGehalt() {
        return gehalt;
    }

    public boolean gleicheAbteilung(Angestelter ang){
        if(ang != null)
            return ang.getAbteilung().equals(this.getAbteilung());
        return false;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        String[] namen = this.getName().split(" ");
        sb.append("Vorname:");
        for (int i = 0; i < namen.length-1; i++)
            sb.append(" ").append(namen[i]);
        sb.append("; Nachname: ").append(namen[namen.length-1]).append("; Abteilug: ").append(getAbteilung()).append("; Gehalt: ").append(Firma.df.format(getGehalt()));
        return sb.toString();
    }
}
