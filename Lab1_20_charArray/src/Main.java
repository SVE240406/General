public static void main(String[] args) {
    System.out.println("Insanzieren von CharArray");
    char[] chars = {'\u0000', 'H', 'e', 'l', 'l', 'o', '\u0000', ' ', '\u0000', 'W', 'o', 'r', 'l', 'd', '!', '\u0000', '\u0000'};
    CharArray charArray = new CharArray(chars);
    charArray.ausgeben();

    System.out.println("\nEntfernen von ' ': ");
    charArray.entferneZeichen(' ');
    charArray.ausgeben();

    System.out.println("\numdrehen von CharArray");
    charArray.reverseZeile();
    charArray.ausgeben();

    System.out.println("\nErste Stelle von \"l\" im CharArray: " + charArray.ersterIndexVon('l'));
    System.out.println("Erste Stelle von \"l\" im CharArray bei 4 beginnend: " + charArray.ersterIndexVon('l', 3));

    System.out.println("\nletzte Stelle von \"l\" im CharArray: " + charArray.letzterIndexVon('l'));

    charArray.reverseZeile();
    System.out.println();
    char[] ident1 = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    System.out.println("'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h' ist ident mit CharArray: " + charArray.istIdentMit(ident1));
    char[] ident2 = {'H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd', '!'};
    System.out.println("'H', 'e', 'l', 'l', 'o', 'W', 'o', 'r', 'l', 'd', '!' ist ident mit CharArray: " + charArray.istIdentMit(ident2));

    System.out.println();
    char[] contains1 = {'a', 'b', 'c'};
    System.out.println(charArray.istTeilVon(contains1));
    char[] contains2 = {'H', 'e', 'l', 'l', 'o'};
    System.out.println(charArray.istTeilVon(contains2));

    System.out.print("\nSortieren von CharArray: ");
    charArray.sort();
    charArray.ausgeben();
}