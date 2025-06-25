public class Buch {
    private String titel;
    private int erscheinungsjahr;
    private Autor autor;

    public Buch(String titel, int erscheinungsjahr, Autor autor) {
        setAutor(autor);
        setTitel(titel);
        setErscheinungsjahr(erscheinungsjahr);
    }

    public String getTitel() {
        return titel;
    }
    public void setTitel(String titel) {
        if(titel != null && !titel.equals(""))
            this.titel = titel;
        else{
            this.titel = "The Hobbit";
            System.err.println("Kein gültiger Titel übergeben! Der Titel ist jetzt: \"" + this.titel + "\".");
        }
    }
    public int getErscheinungsjahr() {
        return erscheinungsjahr;
    }
    public void setErscheinungsjahr(int erscheinungsjahr) {
        if(erscheinungsjahr > Integer.parseInt(autor.getGeburtsjahr()))
            this.erscheinungsjahr = erscheinungsjahr;
    }
    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        if(autor != null)
            this.autor = autor;
        else {
            this.autor = new Autor("J.R.R. Tolkien", "1892");
            System.out.println("Kein Autor übergeben! Autor ist jetzt: \"" + this.autor + "\".");
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Titel: ");
        sb.append(titel).append(", ");
        sb.append("Jahr: ").append(erscheinungsjahr);
        sb.append(", ").append(autor);
        return sb.toString();
    }
}
