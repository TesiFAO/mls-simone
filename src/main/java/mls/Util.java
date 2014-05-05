package mls;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Simone Murzilli
 */

public class Util {

    /** Variabili statiche usate nel test Chi-Quadro **/
    public static double Z25 = -0.674;
    public static double Z75 = 0.674;
    public static double Z99 = 2.326;
    public static double Z10 = -1.282;
    public static double Z90 = 1.282;

    /**
     * @param a  a
     * @param x0 Seed
     * @param m  m=2^b
     * @return Una sequenza pseudo-casuale
     * <p/>
     * Generatore congruente moltiplicativo che genera una sequenza di valori pseudo-casuali
     */
    public static List<Integer> generatoreCongruenteMoltiplicativo(int a, int x0, int m) {
        List<Integer> l = new ArrayList<Integer>();
        while (!l.contains(x0)) {
            l.add(x0);
            x0 = (a * x0) % m;
        }
        return l;
    }

    /**
     * @param a  a
     * @param x0 Seed
     * @param m m=2^b
     * @return Una sequenza pseudo-casuale con valori che sono compresi tra 0 ed 1
     * <p/>
     * Genera una sequenza di valori pseudo-casuali
     * compresi tra 0 ed 1
     * tramite generatore congruente moltiplicativo
     */
    public static List<Double> generaRns(int a, int x0, int m) {
        //double m = Math.pow(2.0, b);
        List<Double> l1 = new ArrayList<Double>();
        List<Double> l2 = new ArrayList<Double>();
        double next = x0;
        while (!l1.contains(next)) {
            l1.add(next);
            next = (a * next) % m;
        }
        for (Double d : l1)
            l2.add(d / m);
        return l2;
    }

    /**
     *
     * MODIFICATA
     *
     * @param q
     * @param modulo
     * @param values Numero di valori da generare per la sequenza
     * @return Una lista di valori interi
     * <p/>
     * Genera una sequenza valutando a=q*(mod m) (quivalente a a=m*t+q)
     */
    public static List<Integer> generaAs(int q, int modulo, int values) {
        List<Integer> l = new ArrayList<Integer>();
        for (int t = 0; t < values; t++)
            l.add(modulo * t + q);
        return l;
    }

    /**
     *
     * MODIFICATA
     *
     * @param m  valore di m
     * @return   Lista contente valori dispari minori di m=2^b
     * <p/>
     * Genera una sequenza di numeri dispari che sono inferiori di m=2^b
     */
    public static List<Integer> generaXs(int m) {
        List<Integer> l = new ArrayList<Integer>();
        for (int i = 1; i < m; i += 2) l.add(i);
        return l;
    }


    public static List<Double> generaRange(int a, int x0, int m, int min, int max) {
        List<Double> rns = generaRns(a, x0, m);
        List<Double> l = new ArrayList<Double>();
        for(Double rn : rns)
            l.add(min + rn * (max - min));
        return l;
    }


    public static List<Double> generateExponential(int a, int x0, int m, int avg) {
        //System.out.println("generateExponential: " + a + " | "+ x0 + " | "+  m + " | "+ avg );
        List<Double> l = new ArrayList<Double>();
        List<Double> rns = generaRns(a, x0, m);
        //System.out.println("generateRn: " + printR(rns));
        double lambda = 1.0 / avg;
        for(Double rn : rns) {
            l.add((-1.0 / lambda) * Math.log(rn));
        }
        return l;
    }

    public static void print_int(List<Integer> l) {
        for (int i = 0 ; i < l.size() ; i++) {
            System.out.print(l.get(i));
            if (i < l.size() - 1)
                System.out.print(", ");
        }
        System.out.println();
    }

    public static void print_double(List<Double> l) {
        for (int i = 0 ; i < l.size() ; i++) {
            System.out.print(l.get(i));
            if (i < l.size() - 1)
                System.out.print(", ");
        }
        System.out.println();
    }

    public static double calcolaV(List<Double> yss, double n, double ps) {
        double v = 0.0;
        double nps = n * ps;
        for (int i = 0 ; i < yss.size() ; i++) {
            v += Math.pow(yss.get(i) - nps, 2) / nps;
        }
        System.out.println("nps: " + nps);
        System.out.println("v: " + v);
        System.out.println();
        return v;
    }

    public static double calcolaVseriale(List<Double> yss, double n, double ps) {
        double v = 0.0;
        double nps = (n / 4096.0) / 2;
        nps = (43000.0 / 2.0) / 4096.0;
//        nps = 164;
        double v1 = 0;
        for (int i = 0 ; i < yss.size() ; i++) {
            System.out.println("categoria " + i + " contiene " + yss.get(i) + " -> " + (Math.pow(yss.get(i) - nps, 2) / nps));
            v += Math.pow(yss.get(i) - nps, 2) / nps;
            if  ( yss.get(i) <= 0)
                v1 += Math.pow(yss.get(i) - nps, 2) / nps;
        }
        System.out.println("nps: " + nps);
        System.out.println("v: " + v);
        System.out.println("v1: " + v1);
        System.out.println();
        return v;
    }

    public static double calcolaChiQuadro(double df, double za) {
        double a = 1.0;
        double b = 2.0 / (9.0 * df);
        double c = za * Math.sqrt(2.0 / (9.0 * df));
        double abc = a - b + c;
        double cube = Math.pow(abc, 3);
//        System.out.println(df * cube);
        return (df * cube);
    }
}
