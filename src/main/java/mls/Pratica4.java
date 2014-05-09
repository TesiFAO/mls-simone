package mls;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by vortex on 5/6/14.
 */
public class Pratica4 {

    private int a;
    private int x0;
    private int m;
    private int b;
    private double d;
    private int prove;

    public Pratica4(int a, int x0, int b, double d, int prove) {
        this.setA(a);
        this.setX0(x0);
        this.setM((int) Math.pow(2.0, b));
        this.setD(d);
        this.setProve(prove);
        this.setB(b);
    }

    public void testUniformita() {
        System.out.println("--Il Test di Uniformita' dato [a=" + this.getA() + "]" + "[x0=" + this.getX0() + "]" + "[b=" + this.getB() + "]"+ "[d=" + this.getD() + "]"+ "[prove=" + this.getProve()+ "]");

        List<List<Integer>> sequenze = Util.creaSequenze(this.getD(), this.getA(), this.getX0(), this.getM(), this.getProve());

        int successi = 0;
        for (List<Integer> sottoSequenza : sequenze ) {

            List<Double> l = Util.calcolaFrequenze(sottoSequenza);

            double v = Util.calcolaV(l, sottoSequenza.size(), 1 / this.getD());
            if( Util.controllaV(v, this.getD()-1)) {
                successi++;
            }
         }

        System.out.println("Risulta Accettabile " + successi + " volte su " +  sequenze.size() + "\n");
    }



    public void testSeriale() {
        System.out.println("--Il Test Seriale dato [a=" + this.getA() + "]" + "[x0=" + this.getX0() + "]" + "[b=" + this.getB() + "]"+ "[d=" + this.getD() + "]"+ "[prove=" + this.getProve()+ "]");

        List<List<Integer>> sequenze = Util.creaSequenze(this.getD(), this.getA(), this.getX0(), this.getM(), this.getProve());
        int dd = (int) Math.pow(this.getD(), 2);

        int successi = 0;
        for (List<Integer> sequenza : sequenze ) {
            int[][] matrix1 = new int[(int) this.getD()][(int) this.getD()];
            int[][] matrix2 = new int[(int) this.getD()][(int) this.getD()];

            for (int i = 0; i < sequenza.size() - 1; i+=2) {
                matrix1[sequenza.get(i)][sequenza.get(i + 1)] = matrix1[sequenza.get(i)][sequenza.get(i + 1)] + 1;
            }
            for (int i = 1; i < sequenza.size() - 2; i+=2) {
                matrix2[sequenza.get(i)][sequenza.get(i + 1)] = matrix1[sequenza.get(i)][sequenza.get(i + 1)] + 1;
            }

            double v1 = Util.calcolaVSeriale(matrix1, (double) sequenza.size()/2, (double) 1/dd);
            double v2 = Util.calcolaVSeriale(matrix2, (double) sequenza.size()/2, (double) 1/dd);

            if ( Util.controllaV(v1, dd-1) )  {
                successi++;
            }
            if ( Util.controllaV(v2, dd-1) ) {
                successi++;
            }
        }

        System.out.println("Risulta Accettabile " + successi + " volte su " +  sequenze.size() * 2 );
    }


    public static void main(String args[]) {
        int b = 19;
        int a = 79;
        int x0 = 1;
        int d = 64;
        int prove = 3;
        Pratica4 u1 = new Pratica4(a, x0, b, d, prove);
        u1.testUniformita();

        b = 19;
        a = 15;
        x0 = 1;
        d = 64;
        prove = 3;
        Pratica4 u2 = new Pratica4(a, x0, b, d, prove);
        u2.testUniformita();



        b = 19;
        a = 3;
        x0 = 11;
        d = 64;
        prove = 3;
        Pratica4 s = new Pratica4(a, x0, b, d, prove);
        s.testSeriale();
    }




    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getX0() {
        return x0;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public int getProve() {
        return prove;
    }

    public void setProve(int prove) {
        this.prove = prove;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
