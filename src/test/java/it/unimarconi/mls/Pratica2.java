package it.unimarconi.mls;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Guido Barbaglia
 */
public class Pratica2 extends TestCase {

    public void testGenerateRn() {
        Integer a = 3;
        Integer m = (int) Math.pow(2, 12);
        Integer x0 = 1;
        System.out.println("a: " + a);
        System.out.println("m: " + m);
        System.out.println("x0: " + x0);
        List<Double> l = generateRn(a, x0, m);
        for (Double d : l) {
            assertTrue(d <= 1);
            System.out.println(d);
        }
    }

    public void testGenerateRange() {
        Integer a = 3;
        Integer m = (int) Math.pow(2, 12);
        Integer x0 = 1;
        Integer min = 60;
        Integer max = 80;
        List<Double> l = generateRange(a, x0, m, min, max);
        for (Double d : l) {
            assertTrue(d >= min && d <= max);
            System.out.println(d);
        }
    }

    public void testGenerateExponential() {
        Integer a = 3;
        //Integer m = (int) Math.pow(2, 12);
        Integer m = (int) Math.pow(2, 9);
        System.out.println(m);
        Integer x0 = 1;
        Integer avg = 30;
        List<Double> l = generateExponential(a, x0, m, avg);
        double sum = 0.0;
        System.out.println(l.size());
        for (Double d : l) {
            sum += d;
        }
        System.out.println("Sum: "+ sum);
        System.out.println("Media: "+ (sum / l.size()));
        printR(l);
    }

    public void testGenerateKErl() {
        Integer a = 3;
        //Integer m = (int) Math.pow(2, 12);
        Integer m = (int) Math.pow(2, 9);
        Integer x0 = 1;
        Integer avg = 30;
        Integer k = 3;
        List<Double> l = generateKErl(a, x0, m, avg, k);
        double sum = 0.0;
        for (Double d : l) {
            sum += d;
        }
        double mean = (sum / l.size());

        double sumsq = 0;
        for (Double d : l) {
            sumsq = sumsq + ((d-mean) * (d-mean));
        }
        double variance = (float) sumsq /  l.size();
        double sd = Math.sqrt(variance);
        System.out.println("Sum: "+ sum);
        System.out.println("Media: "+ mean);
        System.out.println("Varianza: "+ variance);
        System.out.println("Standard deviation: "+ sd);

        printR(l);
    }


    public void testGenerateHyperexponential() {
        List<Double> l = generateHyperexponential(5, 1, 212, 20, 0.38);
        printR(l);
    }

    /* generate between 0 and 1 */
    public static List<Double> generateRn(int a, int x0, int m) {
        //System.out.println("generateRn: "  + a + " | "+ x0 + " | "+  m );
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

    public List<Double> generateRange(int a, int x0, int m, int min, int max) {
        List<Double> rns = generateRn(a, x0, m);
        List<Double> l = new ArrayList<Double>();
        for(Double rn : rns)
            l.add(min + rn * (max - min));
        return l;
    }

    public List<Double> generateKErl(int a, int x0, int m, int avg, int k) {

        List<Double> result = new ArrayList<Double>();
        List<Double> X = new ArrayList<Double>();
        List<Double> l = new ArrayList<Double>();
        List<List<Double>> exps = new ArrayList<List<Double>>();

        int[] xos = new int[]{5,9,67};
        for(int i=0; i < k; i++) {
           // System.out.println("i: " + i);
            //List<Double> exp = generateExponential(a, xos[i], m, avg/k);
            List<Double> exp = generateRn(a, xos[i], m);
            //Collections.sort(exp);
            exps.add(exp);
            double sum = 0.0;
            for (Double d : exp) {
                sum += d;
            }
            System.out.println("Media exp: "+ (sum / exp.size()));
            printR(exp);
        }

        List<Double> X_SUM = new ArrayList<Double>();
        List<Double> X_PROD = new ArrayList<Double>();
        double p = 1.0;
        double avgk = -avg / k;
        for (int i = 0 ; i < exps.get(0).size() ; i++) {
            Double prod = exps.get(0).get(i) * exps.get(1).get(i) * exps.get(2).get(i);
            //System.out.println("generateRn: " + prod + " | " + exps.get(0).get(i) + " | "+  exps.get(1).get(i) + " | "+exps.get(2).get(i) );
            X_PROD.add((avgk) * Math.log(prod));

            Double sumlog = Math.log(exps.get(0).get(i)) + Math.log(exps.get(1).get(i)) + Math.log(exps.get(2).get(i));
            //System.out.println("generateRn: " + sumlog + " | " + exps.get(0).get(i) + " | "+  exps.get(1).get(i) + " | "+exps.get(2).get(i) );
            X_SUM.add((avgk) * sumlog);
        }
        //printR(X);
        System.out.println("print output");
        printR(X_SUM);
        printR(X_PROD);
        erlangfdit(X_PROD);
        return X_SUM;
    }

    public List<Double> erlangfdit(List<Double> t) {
        List<Double> l = new ArrayList<Double>();
        double k = 3.0;
        double mu = 1.0;
        Collections.sort(t);
        for (int i = 0 ; i < t.size() ; i++) {
            double uno = Math.pow(k * mu, k);
            double due = Math.exp(-k * mu * t.get(i)) / 2;
            double tre = Math.pow(t.get(i), 2);
            System.out.println(uno + " | " + due + " | " + tre);
            l.add( uno * due * tre );
        }
        System.out.println("erlangfdit: " +  printR(l));

        return l;
    }

    public List<Double> generateExponential(int a, int x0, int m, int avg) {
        //System.out.println("generateExponential: " + a + " | "+ x0 + " | "+  m + " | "+ avg );
        List<Double> l = new ArrayList<Double>();
        List<Double> rns = generateRn(a, x0, m);
        //System.out.println("generateRn: " + printR(rns));
        double lambda = 1.0 / avg;
        for(Double rn : rns) {
            l.add((-1 / lambda) * Math.log(rn));
        }
        return l;
    }

    public List<Double> generateHyperexponential(int a, int x0, int m, int avg, double p) {
        List<Double> zs = new ArrayList<Double>();
        List<Double> rns = generateRn(a, x0, m);
        List<Double> Xs = generateExponential(a, x0, m, 1);
        for (int i = 0 ; i < rns.size() ; i++) {
            if (rns.get(i) <= p) {
                zs.add(Xs.get(i) * (avg / (2 * p)));
            } else {
                zs.add(Xs.get(i) * (avg / (2 * (1 - p))));
            }
        }
        return zs;
    }


    private String printR(List<Double> l) {
        //Collections.sort(l);
        String s = "l = c(";
        for (int i = 0 ; i < l.size() ; i++) {
            s += l.get(i);
            if (i < l.size() - 1)
                s += ",";
        }
        s += "); summary(l); var(l);";
        System.out.println(s);
        return s;
    }

}