package mls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Simone Murzilli
 */

public class Pratica1 {

    private int a;
    private int b;
    private int m;
    private int x0;

    public Pratica1(int a, int b, int x0) {
        this.setA(a);
        this.setB(b);
        this.setM((int) Math.pow(2, b));
        this.setX0(x0);
    }

    private List<Integer> generaSequenza() {

        // generazione sequenza
        List<Integer> l = Util.generatoreCongruenteMoltiplicativo(this.getA(), this.getX0(), this.getM());

        // print della sequenza generata
        System.out.println(stampaSequenza(l));

        // sort della sequenza
        Collections.sort(l);
        return l;
    }

    private  List<Integer> ge() {
         return null;
    }

    private String stampaSequenza(List<Integer> sequence) {
        return "[a= " + this.getA() + "]" + "[x= " + this.getX0() + "]" + "[b= " + this.getB() + "] "  + sequence;
    }

    private List<Integer> generaXs() { return Util.generaXs(getM()); }

    public int getA() { return a; }

    public void setA(int a) { this.a = a; }

    public void setM(int m) { this.m = m; }

    public int getM() { return m; }

    public int getB() { return b; }

    public void setB(int b) { this.b = b; }

    public int getX0() { return x0; }

    public void setX0(int x0) { this.x0 = x0; }

    public static void main(String args[]) {
        int b = 9;

        /** generazione delle due sequenze del corollario **/
        List<Integer> corollarioA1 = Util.generaValoriCorollarioA(b, 1);
        List<Integer> corollarioA2 = Util.generaValoriCorollarioA(b, 15);

        /** Lista delle sequenze con differenti a e x0 **/
        List<List<Integer>> sequenze = new ArrayList<List<Integer>>();
        sequenze.add(new Pratica1(3, b, 1).generaSequenza());
        sequenze.add(new Pratica1(11, b, 9).generaSequenza());
        sequenze.add(new Pratica1(19, b, 13).generaSequenza());
        sequenze.add(new Pratica1(27, b, 15).generaSequenza());
        sequenze.add(new Pratica1(11, b, 233).generaSequenza());
        sequenze.add(new Pratica1(19, b, 427).generaSequenza());

        for(List<Integer> l : sequenze) {
            if (l.equals(corollarioA1) || l.equals(corollarioA2)) {
                System.out.println("la sequenza contenuta nel corollario");
            }
            else {
                System.out.println("Error: la sequenza contenuta nel corollario");
            }
        }
    }
}
