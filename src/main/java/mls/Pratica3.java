package mls;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.SortedMap;

/**
 * Created by vortex on 5/6/14.
 */
public class Pratica3 {

    private int a;
    private int x0;
    private int m;
    private int min;
    private int max;
    private double avg;
    private int k;
    private int[] xos;

    public Pratica3(int a, int x0, int m) {
        this.setA(a);
        this.setX0(x0);
        this.setM(m);
    }


    public Pratica3(int a, int x0, int m, int min, int max) {
        this.setA(a);
        this.setX0(x0);
        this.setM(m);
        this.setMin(min);
        this.setMax(max);
    }

    public Pratica3(int a, int m, double avg, int k, int[] xos) {
        this.setA(a);
        this.setM(m);
        this.setAvg(avg);
        this.setK(k);
        this.setXos(xos);
    }

    public Pratica3(int a, int x0, int m, double avg) {
        this.setA(a);
        this.setX0(x0);
        this.setM(m);
        this.setAvg(avg);
    }

    public List<Double> generaRn() {
        List<Double> l = Util.generaRns(this.getA(), this.getX0(), this.getM());
        System.out.println("--Rn--");
        //System.out.println(l);
        Util.calcolaStatistiche(l, 10.0);
        //Util.calcolaStatistiche(l, 10.0);
        return l;
    }

    public List<Double> generaRange() {
        List<Double> l = Util.generaRange(this.getA(), this.getX0(), this.getM(), this.getMin(), this.getMax());
        System.out.println("--Range--");
        Util.calcolaStatistiche(l, 10.0);
        return l;
    }

    public List<Double> generaExponential() {
        System.out.println("--Exponential--");
        List<Double> l = Util.generaExponential(this.getA(), this.getX0(), this.getM(), this.getAvg());
        Util.calcolaStatistiche(l, 25.0);
        return l;
    }


    // public List<Double> generateKErl(int a, int x0, int m, int avg, int k) {
    public List<Double> generaKErl() {
        List<Double> l = Util.generaKErl(this.getA(), this.getM(), this.getK(), this.getAvg(), this.getXos());
        System.out.println("--K-Erlangiana--");
        Util.calcolaStatistiche(l, 20.0);
        return l;
    }


    public static void main(String args[]) {
        /*int a = 3;
        int b = 12;
        int m = (int) Math.pow(2, b);
        int x0 = 1;
        int min = 60;
        int max = 80;
        double avg = 30.0;
        int k = 3;*/

        int a = 3;
        int b = 12;
        int m = (int) Math.pow(2, b);
        int x0 = 1;
        int min = 60;
        int max = 80;
        double avg = 30.0;
        int k = 3;

        //List<Double> rn = new Pratica3(a, x0, m, min, max).generaRn();
        //System.out.println();

        //List<Double> range = new Pratica3(a, x0, m, min, max).generaRange();
        //System.out.println();

        // List<Double> sequenceExp = new Pratica3(a, x0, m, avg).generaExponential();
        //System.out.println();

        int[] xos = new int[]{5,9,67};
        List<Double> erl = new Pratica3(a, m, avg, k, xos).generaKErl();
        System.out.println();
    }


    /**
     *
     * Rn
     * occ:http://jsfiddle.net/v0rtex83/AFn5k/2/
     * fr: http://jsfiddle.net/v0rtex83/x2pZ2/3/
     * dp: http://jsfiddle.net/v0rtex83/kaka2/4/
     * cum: http://jsfiddle.net/v0rtex83/zw4GK/2/
     *
     * Range
     * http://jsfiddle.net/v0rtex83/R5XCq/4/
     * http://jsfiddle.net/v0rtex83/QfXMd/3/
     * http://jsfiddle.net/v0rtex83/8JbGU/2/
     * http://jsfiddle.net/v0rtex83/9sZhv/2/
     *
     *
     * Esponenziale:
     * http://jsfiddle.net/v0rtex83/2ZNEK/1/
     * http://jsfiddle.net/v0rtex83/Z4dJV/1/
     * http://jsfiddle.net/v0rtex83/29XSC/1/
     * http://jsfiddle.net/v0rtex83/kEB6W/1/
     *
     * Erlangiana:
     * http://jsfiddle.net/v0rtex83/a2hAe/2/
     * http://jsfiddle.net/v0rtex83/8dAfc/1s/
     * http://jsfiddle.net/v0rtex83/WDEqw/1/
     * http://jsfiddle.net/v0rtex83/H33NW/1/
     *
     */

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getX0() {
        return x0;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int[] getXos() {
        return xos;
    }

    public void setXos(int[] xos) {
        this.xos = xos;
    }


}
