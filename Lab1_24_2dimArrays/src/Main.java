public class Main {
    public static void main(String[] args) {
        Muster m = new Muster(20,20);
        m.quadrate(5, 6);
        m.ausgeben();
        m.loesche(3,false);
        m.ausgeben();
    }
}