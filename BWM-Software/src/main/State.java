package main;

public enum State {
    MENU, N_KONTO, BUCHUNG, K_ABSCHLIESSEN, XLSX;
    public static State currentState = MENU;
}
