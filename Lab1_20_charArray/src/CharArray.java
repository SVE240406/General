public class CharArray {
    private char[] satz;
    private int anz;

    public CharArray(char[] satz) {
        if(satz != null && satz.length != 0) {
            this.satz = satz;
            anz = this.satz.length;
            while(true){
                if(String.valueOf(this.satz).contains("\u0000")) {
                    entferneZeichen('\u0000');
                    char[] shortened = new char[this.satz.length-1];
                    System.arraycopy(this.satz, 0, shortened, 0, shortened.length);
                    this.satz = shortened;
                }else
                    break;
            }
        }
    }

    public void entferneZeichen(char c){
        for(int i = 0; i < anz; i++){
            if(satz[i] == c){
                for(int j = i; j < satz.length; j++){
                    if(j == satz.length-1 || j >= anz){
                        satz[j] = '\u0000';
                        anz--;
                        break;
                    }
                    satz[j] = satz[j+1];
                }
                return;
            }
        }
    }

    public void ausgeben(){
        for(int i = 0; i < anz; i++)
            System.out.print(satz[i]);
        System.out.println();
    }

    public void reverseZeile(){
        for (int i = 0; i < anz/2; i++) {
            char temp = satz[i];
            satz[i] = satz[anz-i-1];
            satz[anz-i-1] = temp;
        }
    }

    public boolean istIdentMit(char[] satz){
        if(satz != null && satz.length == anz){
            for(int i = 0; i < anz; i++){
                if(satz[i] == '\u0000'||satz[i] != this.satz[i])
                    return false;
            }
        }
        else
            return false;
        return true;
    }

    public String istTeilVon(char[] satzTeil){
        String s = String.valueOf(satzTeil).trim();
        boolean contains = String.valueOf(satz).contains(s);

        StringBuilder str = new StringBuilder();
        str.append("'").append(s).append("'").append(" ist ");
        if(!contains)
            str.append("nicht ");
        str.append("in ").append(String.valueOf(satz).trim()).append(" vorhanden");
        return str.toString();
    }

    public int ersterIndexVon(char c){
        return ersterIndexVon(c, 0);
    }

    public int ersterIndexVon(char c, int abIndex){
        for(int i = abIndex; i < anz; i++){
            if(satz[i] == c)
                return i;
        }
        return -1;
    }

    public int letzterIndexVon(char c){
        for(int i = anz-1; i >= 0; i--){
            if(satz[i] == c)
                return i;
        }
        return -1;
    }

    public void sort(){
        for (int i = anz-1; i >= 0; i--) {
            char biggestChar = '\u0000';
            int indexOfBiggestChar = -1;
            for (int j = 0; j <= i; j++) {
                if (satz[j] > biggestChar) {
                    biggestChar = satz[j];
                    indexOfBiggestChar = j;
                }
            }
            satz[indexOfBiggestChar] = satz[i];
            satz[i] = biggestChar;
        }
    }
}