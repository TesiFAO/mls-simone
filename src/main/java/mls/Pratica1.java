package mls;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Simone Murzilli
 */

public class Pratica1 {

    private int a;
    private int b;
    private int m;

    public Pratica1(int a, int b) {
        this.setA(a);
        this.setB(b);
        this.setM((int) Math.pow(2, b));
    }

    public List<List<Integer>> generatoreSequenze() {
        List<Integer> xos = generaXs();
        List<Integer> lastSequence = new ArrayList<Integer>();
        List<List<Integer>> sequences = new ArrayList<List<Integer>>();
        for (int xo: xos) {
            List<Integer> xs = Util.generatoreCongruenteMoltiplicativo(this.getA(), xo, this.getM());
            if (!xs.equals(lastSequence)) {
                sequences.add(xs);
                lastSequence = xs;
            }
        }
        return sequences;
    }

    private List<Integer> generaXs() {
        return Util.generaXs(getM());
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getM() {
        return m;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public static void main(String args[]) {
        int a = 3;
        int b = 9;
        Pratica1 p = new Pratica1(a, b);
        List<List<Integer>> sequences = p.generatoreSequenze();
        //System.out.println(domain);
        for (List<Integer> l : sequences) {
            System.out.println("[X0:" + l.get(0) + "]" + l);
            /*if ( (int) Math.pow(2, b - 2) != l.size()) {
                System.out.println("Error");
            }*/
        }
    }
}
