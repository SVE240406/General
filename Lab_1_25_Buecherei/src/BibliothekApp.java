import java.util.ArrayList;

public class BibliothekApp {
    public static void main(String[] args) {
        Autor[] autor = initAutor();
        Buch[] buecher = initBuecher(autor);

        print("George Orwell", buecher);

        buecher = sortBuecher(buecher);

        print("George Orwell", buecher);
    }

    private static Autor[] initAutor() {
        Autor[] autor = new Autor[2];
        autor[0] = new Autor("J.R.R. Tolkien", "1892");
        autor[1] = new Autor("George Orwell", "1903");
        return autor;
    }

    private static Buch[] initBuecher(Autor[] autor) {
        Buch[] buecher = new Buch[5];

        buecher[0] = new Buch("Der Hobbit: an unexpected Journey", 1937, autor[0]);
        buecher[1] = new Buch("Der Herr der Ringe", 1954, autor[0]);
        buecher[2] = new Buch("Das Silmarillon", 1977, autor[0]);
        buecher[3] = new Buch("1984", 1949, autor[1]);
        buecher[4] = new Buch("Farm der Tiere", 1945, autor[1]);

        return buecher;
    }

    private static void print(String name, Buch[] buecher) {
        for(Buch buch : buecher)
            if(buch.getAutor().getName().equals(name))
                System.out.println(buch);
    }

    private static Buch[] getBuecher(int erscheinungsjahr, Buch[] buecher) {
        ArrayList<Buch> results = new ArrayList<Buch>();
        for(Buch buch : buecher)
            if(buch.getErscheinungsjahr() == erscheinungsjahr)
                results.add(buch);
        return results.toArray(new Buch[results.size()]);
    }

    private static Buch[] sortBuecher(Buch[] buecher) {
        for(int i = 0; i < buecher.length; i++){
            Buch temp = buecher[i];
            int index = i;
            for(int j = i+1; j < buecher.length; j++){
                if(buecher[j].getErscheinungsjahr() < temp.getErscheinungsjahr()) {
                    temp = buecher[j];
                    index = j;
                }
            }
            buecher[index] = buecher[i];
            buecher[i] = temp;
        }
        return buecher;
    }
}
