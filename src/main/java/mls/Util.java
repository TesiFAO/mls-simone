package mls;

import java.util.*;


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

    public static String printR(List<Double> l, boolean sort) {
        if ( sort ) Collections.sort(l);
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

    public static List<Integer> generaValoriCorollarioA(int b, int x0) {
        List<Integer> l = new ArrayList<Integer>();
        int size = (int) Math.pow(2, (b-3)) -1;
        if ( x0 == 1 % 16 || x0 == 3 % 16 || x0 == 9 % 16 || x0 == 11 % 16) {
            for (int v = 0; v <= size; v++) {
                l.add((8 * v) + 1);
                l.add((8 * v) + 3);
            }
        }
        if ( x0 == 5 % 16 || x0 == 7 % 16 || x0 == 13 % 16 || x0 == 15 % 16) {
            for (int v = 0; v <= size; v++) {
                l.add((8 * v) + 5);
                l.add((8 * v) + 7);
            }
        }
        return l;

    }



    public static List<Double> generaRange(int a, int x0, int m, int min, int max) {
        List<Double> rns = generaRns(a, x0, m);
        List<Double> l = new ArrayList<Double>();
        for(Double rn : rns)
            l.add(min + rn * (max - min));
        return l;
    }


    public static List<Double> generaExponential(int a, int x0, int m, double avg) {
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

    public static List<Double> generaKErl(int a, int m, int k, double avg, int[] xos ) {
        //int[] xos = new int[]{5,9,67};
        List<List<Double>> exps = new ArrayList<List<Double>>();
        for(int i=0; i < k; i++) {
            List<Double> exp = Util.generaRns(a, xos[i], m);
            exps.add(exp);
            //System.out.println("Media exp: "+ calcolaMedia(exp));
        }
        List<Double> X_SUM = new ArrayList<Double>();
        List<Double> X_PROD = new ArrayList<Double>();
        double p = 1.0;
        double avgk = -avg / k;
        for (int i = 0 ; i < exps.get(0).size() ; i++) {
            //Double prod = exps.get(0).get(i) * exps.get(1).get(i) * exps.get(2).get(i);
            //X_PROD.add((avgk) * Math.log(prod));
            Double sumlog = Math.log(exps.get(0).get(i)) + Math.log(exps.get(1).get(i)) + Math.log(exps.get(2).get(i));
            X_SUM.add((avgk) * sumlog);
        }
        return X_SUM;
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

    public static double calcolaMedia(List<Double> l) {
        double sum = 0.0;
        for (Double d : l) {
            sum += d;
        }
        double mean = (sum / l.size());
        return mean;
    }

    public static double calcolaVarianza(List<Double> l, double media) {
        double sumsq = 0;
        for (Double d : l) {
            sumsq = sumsq + ((d-media) * (d-media));
        }
        return (float) sumsq / l.size();
    }

    public static double calcolaStandardDeviation(double varianze) {
        return Math.sqrt(varianze);
    }

    public static double calcolaV(List<Double> yss, double n, double ps) {
        double v = 0.0;
        double nps = n * ps;
//        nps = 164;
        for (int i = 0 ; i < yss.size() ; i++) {
//            System.out.println("categoria " + i + " contiene " + yss.get(i) + " -> " + (Math.pow(yss.get(i) - nps, 2) / nps));
            v += Math.pow(yss.get(i) - nps, 2) / nps;
        }
//        System.out.println("nps: " + nps);
//        System.out.println("v: " + v);
//        System.out.println();
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

    /**
     *
     * @param sequenza sequenza da passa
     * @param step step dei vari intervalli
     * @return
     */
    public static SortedMap<Double, Integer> numeroOsservazioni(List<Double> sequenza, double step, double min, double max) {
        SortedMap<Double, Integer> osservazioni = new TreeMap();
        for(Double v : sequenza) {
            for(double range=min; range <= max; range+=step) {
                double interval = range + step;
                // check if it's >= 1.0 use floor
                if ( step >= 1.0)
                    interval =  Math.floor(range + step);

                if ( v > range && v <= (interval) ) {
                    int count = 1;
                    if ( osservazioni.containsKey(interval) ) {
                        count = osservazioni.get(interval) + 1;
                    }
                    osservazioni.put(interval, count);
                    break;
                }
            }
        }
        return osservazioni;
    }

    public static SortedMap<Double, Double> frequezaRelativa(SortedMap<Double, Integer> osservazioni, double size) {
        SortedMap<Double, Double> frequenzaRelativa = new TreeMap();
        for(Double key: osservazioni.keySet()) {
            frequenzaRelativa.put(key, osservazioni.get(key) / size);
        }
        return frequenzaRelativa;
    }

    public static SortedMap<Double, Double> densitaProbabilita(SortedMap<Double, Double> frequenzaRelativa) {
        SortedMap<Double, Double> densitaProbabilita = new TreeMap();
        for(Double key: frequenzaRelativa.keySet()) {
            densitaProbabilita.put(key, frequenzaRelativa.get(key) / frequenzaRelativa.size());
        }
        return densitaProbabilita;
    }


    public static List<Double> distribuzioneCumulativa(SortedMap<Double, Double> frequenzaRelativa) {
        List<Double> cumulativa = new ArrayList<Double>();
        double sum = 0;
        for(Double key: frequenzaRelativa.keySet()) {
            sum =+ sum + frequenzaRelativa.get(key);
            cumulativa.add(sum);
        }
        return cumulativa;
    }

    public static void calcolaStatistiche(List<Double> l, double step) {
        double min = Collections.min(l);
        double max = Collections.max(l);
        SortedMap<Double, Integer> numeroOccorrenze = Util.numeroOsservazioni(l, step, min, max);
        System.out.println("OCCORENZE: " + numeroOccorrenze);
        printMap(numeroOccorrenze);
        SortedMap<Double, Double> frequezaRelativa = Util.frequezaRelativa(numeroOccorrenze, l.size());
        System.out.println("FREQUENZA RELATIVA: " + frequezaRelativa);
        printMap(frequezaRelativa);
        SortedMap<Double, Double> densitaProbabilita = Util.densitaProbabilita(frequezaRelativa);
        System.out.println("DENSITA PROBABILITA': " + densitaProbabilita);
        printMap(densitaProbabilita);
        List<Double> cumulativa = Util.distribuzioneCumulativa(frequezaRelativa);
        System.out.println("CUMULATIVA: " + cumulativa);
        double media =  Util.calcolaMedia(l);
        double varianza =  Util.calcolaVarianza(l, media);
        System.out.println("MEDIA: " + media);
        System.out.println("VARIANZA: " + varianza);
    }

    private static void printMap(Map map) {
        int count = 0;
        for(Object key: map.keySet()) {
            count++;
            System.out.print(map.get(key));
            if ( count < map.size()) {
                System.out.print(",");
            }
        }
        System.out.println();
    }

}
