/**
 * Ricerca
 */
public class Ricerca {

    public int valore;
    public boolean trovato;
    public boolean proc1,proc2;
    public Ricerca(int valore,boolean trovato) {
        this.valore=valore;
        this.trovato=trovato;
        proc1=false;
        proc2=false;
    }
    @Override
    public String toString() {
        return "Ricerca [valore=" + valore + ", trovato=" + trovato + "]";
    }
    
}