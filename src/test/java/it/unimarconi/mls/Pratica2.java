package it.unimarconi.mls;

import com.sun.deploy.util.ArrayUtil;
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
        Integer m = (int) Math.pow(2, 4);
        System.out.println(m);
        Integer x0 = 1;
        Integer avg = 30;
        List<Double> l = generateExponential(a, x0, m, avg);
        double sum = 0.0;
        double s2 = 0.0;
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
        Integer m = (int) Math.pow(2, 6);
        Integer x0 = 1;
        Integer avg = 30;
        Integer k = 3;
        List<Double> l = generateKErl(a, x0, m, avg, k);
        double sum = 0.0;
        for (Double d : l) {
            sum += d;
        }
        System.out.println("Sum: "+ sum);
        System.out.println("Media: "+ (sum / l.size()));
        printR(l);
    }


    public void testGenerateHyperexponential() {
        List<Double> l = generateHyperexponential(5, 1, 212, 20, 0.38);
        printR(l);
    }

    /* generate between 0 and 1 */
    public static List<Double> generateRn(int a, int x0, int m) {
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

    public List<Double> generateExponential2(int a, int x0, int m, int avg) {
        List<Double> l = new ArrayList<Double>();
        List<Double> rns = generateRn(a, x0, m);
        double lambda = 1.0 / avg;
        for(Double rn : rns)
            l.add(-avg * Math.log(rn));
        return l;
    }

    public List<Double> generateKErl(int a, int x0, int m, int avg, int k) {
        double p = 1.0;
        List<Double> result = new ArrayList<Double>();
        List<Double> X = new ArrayList<Double>();
        List<Double> l = new ArrayList<Double>();
        List<List<Double>> exps = new ArrayList<List<Double>>();
        for(int kindex=0; kindex < k; kindex++) {
            List<Double> exp = generateExponential(a, x0, m, avg/k);
            exps.add(exp);
            printR(exp);
        }
        // sommo
        //for (int i =0; i < exps.get(0).size(); i++){
        //    X.add(exps.get(0).get(i) + exps.get(1).get(i) + exps.get(2).get(i));
        //}
        // p = 1
        List<Double> ps = new ArrayList<Double>();
        for (int i = 0 ; i < exps.size() ; i++) {
            for ( int j=0; j < exps.get(i).size(); j++) {
                p = p * exps.get(i).get(j);
                System.out.println(p);
                ps.add(p);
                if (p == 0) {
                    System.out.println("0");
                    return null;
                } else {
                    double ts = -(avg / k);
                    result.add(ts * Math.log(p));
                }
            }
        }
        printR(result);


        return result;
    }

    public List<Double> generateExponential(int a, int x0, int m, int avg) {
        List<Double> l = new ArrayList<Double>();
        List<Double> rns = generateRn(a, x0, m);
        double lambda = 1.0 / avg;
        for(Double rn : rns)
            l.add((-1 / lambda) * Math.log(rn));
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

    private void printR(List<Double> l) {
//        Collections.sort(l);
        String s = "l = c(";
        for (int i = 0 ; i < l.size() ; i++) {
            s += l.get(i);
            if (i < l.size() - 1)
                s += ",";
        }
        s += "); summary(l); var(l);";
        System.out.println(s);
    }

}