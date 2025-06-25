import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    Scanner scn = new Scanner(System.in);
    private ArrayList<String> user;
    private ArrayList<String> passwords;

    public Main() {
        user.add("Admin");
        passwords.add("passwort");
    }

    public boolean addUser(String username, String passwort) {
        for(String s : user)
            if(s.equals(username))
                return false;
        user.add(username);
        passwords.add(passwort);
        return true;
    }

    public static void main(String[] args) {

    }
}