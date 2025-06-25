public class Autor {
    private String name;
    private String geburtsjahr;

    public Autor(String name, String geburtsjahr) {
        setName(name);
        setGeburtsjahr(geburtsjahr);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if(name != null)
            this.name = name;
        else{
            this.name = "J.R.R. Tolkien";
            System.err.println("Kein Name übergeben!! Name ist jetzt J.R.R. Tolkien");
        }
    }
    public String getGeburtsjahr() {
        return geburtsjahr;
    }
    public void setGeburtsjahr(String geburtsjahr) {
        if(geburtsjahr != null) {
            boolean valid = true;
            char[] chars = geburtsjahr.toCharArray();
            for(char c : chars)
                if (!Character.isDigit(c))
                    valid = false;
            if(valid) {
                this.geburtsjahr = geburtsjahr;
                return;
            }
        }
        this.geburtsjahr = "1892";
        System.err.println("Kein gültiges Geburtsjahr übergeben!!! Geburtsjahr ist jetzt 1892");
    }

    @Override
    public String toString() {
        return "Autor: " + getName() + " (" + getGeburtsjahr() + ")";
    }
}
