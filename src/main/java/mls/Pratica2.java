package mls;

import java.util.List;

/**
 * @author Simone Murzilli
 */

public class Pratica2 {

    private int a;
    private int b;
    private int m;
    private int avg;

    public Pratica2() {

    }

    public List<Double> generaRange(int a, int x0, int m, int min, int max) {
        return Util.generaRange(a, x0, m, min, max);
    }


    public static void main(String args[]) {
        int a = 3;
        int b = 12;
        int m = (int) Math.pow(2, 12);
        int x0 = 1;
        int min = 60;
        int max = 80;
        int avg = 30;
        Pratica2 p = new Pratica2();

        List<Double> sequence = p.generaRange(a, x0, m, min, max);

        System.out.println("[X0:" + sequence.get(0) + "]" + sequence);
    }

}
