import java.util.Random;

public class Test {
    public static Random rand = new Random();

    public Test(){
        Firma firma = new Firma(generateString());
        String[] abteilungen = {generateString(), generateString(), generateString()};
        for(int i = 0; i < rand.nextInt(5, 10); i++)
            firma.einfuegen(generateString(), abteilungen[rand.nextInt(0, abteilungen.length)], rand.nextFloat(0, 10000));

        firma.ausgeben();

        testLoeschenAngestellter(firma, firma.getAngestellten(0));

        testInAbteilungMit(firma, 0);

        testGetAngestellte(firma, 'A');

        testGetMeisstverdiener(firma);

        testAlphabetischSortieren(firma);

        testNachGehaltSortieren(firma);
    }

    public static void testLoeschenAngestellter(Firma firma, Angestelter angestelter){
        System.out.println("\nLöschen des Angestellten\n" + angestelter + ":");
        System.out.println(firma.loeschen(angestelter.getName()));
    }

    public static void testInAbteilungMit(Firma firma, int pos){
        System.out.println("\nAlle, die in einer Abteilung mit dem angestellten an Position " + pos + " sind:");
        firma.inAbteilungMit(firma.getAngestellten(pos));
    }

    public static void testGetAngestellte(Firma firma, char c){
        System.out.println("\nAlle, deren Name mit \"" + c + "\" beginnt:");
        if(firma.getAngestellte(c) != null)
            for(Angestelter ang : firma.getAngestellte(c))
                System.out.println(ang);
        else
            System.out.println("Kein Angestellter beginnt mit \"" + c + "\"");
    }

    public static void testGetMeisstverdiener(Firma firma){
        System.out.println("\nMeißtverdienene:");
        firma.meistVerdiener();
    }

    public static void testAlphabetischSortieren(Firma firma){
        System.out.println("\nAlphabetisch sortieren:");
        firma.sortAlphabetisch();
        firma.ausgeben();
    }

    public static void testNachGehaltSortieren(Firma firma){
        System.out.println("\nNach Gehalt sortieren:");
        firma.sortGehalt();
        firma.ausgeben();
    }

    public static String generateString(){
        int charCounter = 0;
        boolean afterSpace = true;
        String vocals = "aeiou";
        String consonants = "bcdfghjklmnpqrstvwxyz ";
        StringBuilder sb = new StringBuilder();
        char currentChar;
        if(rand.nextBoolean()) {
            currentChar = consonants.charAt(rand.nextInt(consonants.length() - 1));
            charCounter = 1;
        }else
            currentChar = vocals.charAt(rand.nextInt(vocals.length()));
        do{
            if(currentChar == ' '){
                if(sb.toString().contains(" ") && rand.nextInt(3) == 2)
                    break;
                sb.append(currentChar);
                charCounter = 0;
                if(rand.nextBoolean()) {
                    currentChar = consonants.charAt(rand.nextInt(consonants.length() - 1));
                    charCounter = 1;
                }else
                    currentChar = vocals.charAt(rand.nextInt(vocals.length()));
                afterSpace = true;
            }
            if(afterSpace){
                sb.append(Character.toUpperCase(currentChar));
                charCounter++;
                afterSpace = false;
            }else {
                sb.append(currentChar);
                charCounter++;
            }
            if(charCounter % 3 == 0)
                currentChar = vocals.charAt(rand.nextInt(vocals.length()));
            else
                currentChar = consonants.charAt(rand.nextInt(consonants.length()));
        }while (charCounter < 8 || !sb.toString().contains(" "));
        return sb.toString();
    }
}
