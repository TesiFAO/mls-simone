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
    private double d;
    private int prove;

    public Pratica4(int a, int x0, int m, double d, int prove) {
       this.setA(a);
       this.setX0(x0);
       this.setM(m);
       this.setD(d);
       this.setProve(prove);
    }

    public boolean[] uniformita() {

        boolean[] out = new boolean[this.getProve()];

        List<Double> rns = Util.generaRns(this.getA(), this.getX0(), this.getM());

        double min = Util.calcolaChiQuadro(64.0, Util.Z25);
        double max = Util.calcolaChiQuadro(64.0, Util.Z75);

        List<Double> ds = new ArrayList<Double>();
        for (Double rn : rns)
            ds.add(Math.floor(this.getD() * rn));

        int chunks = this.getProve();
        List<List<Double>> testSet = new ArrayList<List<Double>>();
        double size = ds.size() / this.getProve();
        for (int i = 0 ; i < chunks ; i++) {
            List<Double> chunk = new ArrayList<Double>();
            for (int j = i * (int)size ; j < (i + 1) * (int)size ; j++) {
                chunk.add(ds.get(j));
            }
            testSet.add(chunk);
        }

        for (int k = 0 ; k < chunks ; k++) {
            Collections.sort(testSet.get(k));
            LinkedHashMap<Double, Double> freqs = new LinkedHashMap<Double, Double>();
            for (Double tmp : testSet.get(k)) {
                try {
                    freqs.put(tmp, 1.0 + freqs.get(tmp));
                } catch (Exception e) {
                    if (tmp <= this.getD())
                        freqs.put(tmp, 1.0);
                }
            }
            List<Double> yss = new ArrayList<Double>();
            for (Double pippo : freqs.values())
                yss.add(pippo);
            double v = Util.calcolaV(yss, size, 0.015625);
            if (v > min && v < max) {
                out[k] = true;
            } else {
                out[k] = false;
            }
        }

        return out;
    }

    public static void main(String args[]) {
        int a = 65;
        int x0 = 3;
        int b = 19;
        double d = 64.0;
        int prove = 3;
        int m = (int) Math.pow(2.0, b);
        Pratica4 u = new Pratica4(a, x0, m, d, prove);
        boolean[] proveUniformita = u.uniformita();
        for(boolean tests : proveUniformita)
            System.out.println(tests);

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
}
