package it.unimarconi.mls;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author simone murzilli
 */
public class Pratica1Junit extends TestCase {

    public void testGenerate() {
        List<List<Integer>> out = generate();
        System.out.println("Ci sono "+ out.size() + " liste differenti.");
        for (List<Integer> l : out) {
            System.out.print("[X0 = " + l.get(0) + "] [" + l.size() +"]: ");
            for (int j = 0; j < l.size(); j++) {
                System.out.print(l.get(j));
                if (j < l.size() - 1)
                    System.out.print(", ");
            }
            System.out.println();
        }

        int b = 6;
        int vsize = (int) Math.pow(2, (b-3)) -1;
        System.out.println("vsize: " + vsize);
        for(int v=0; v <= vsize; v++ ) {
            System.out.println((8 * v ) + 1);
            System.out.println((8 * v ) + 3);
        }

        System.out.println("---------------------");
        for(int v=0; v <= vsize; v++ ) {
            System.out.println((8 * v ) + 5);
            System.out.println((8 * v ) + 7);
        }

    }

    public List<List<Integer>> generate() {

        Integer b = 5;
        Integer m = (int) Math.pow(2, b);
        //Integer x0 = 1;
        Integer a = GeneratoreMoltiplicativo.generateA(8, 3, 110).get(0);
        List<Integer> as = GeneratoreMoltiplicativo.generateA(8, 3, 110);


        //List<Integer> as = new ArrayList<Integer>();
       // as.add(3);
       // as.add(5);

        /*List<Integer> memory = null;
        List<List<Integer>> out = new ArrayList<List<Integer>>();
        Integer differentLists = 1;
        for (Integer a : as ) {
            System.out.println("a: " + a);
            List<Integer> l = GeneratoreMoltiplicativo.generate(a, x0, m);
            //System.out.println("l: " + l);

            if (memory == null) {
                memory = l;
                out.add(l);
            }
            if(!l.equals(memory)) {
                differentLists++;
                out.add(l);
            } else if (l.equals(memory) && differentLists > 1) {
               break;
            }
        } */


        List<Integer> xs = GeneratoreMoltiplicativo.generateX(m);
        List<Integer> memory = null;
        List<List<Integer>> out = new ArrayList<List<Integer>>();
        Integer differentLists = 1;
        for (int i = 0 ; i < xs.size() ; i++) {
            Integer x0 = xs.get(i);
            List<Integer> l = GeneratoreMoltiplicativo.generate(a, x0, m);
            if (memory == null) {
                memory = l;
                out.add(l);
            }
            if(!l.equals(memory)) {
                differentLists++;
                out.add(l);
            } else if (l.equals(memory) && differentLists > 1) {
                break;
            }
        }
        return out;
    }

    /**
     * @param m Pendenza retta
     * @param q Intercetta retta
     * @values  Numero di valori da generare per la sequenza
     * @return  Lista di valori interi
     *
     * Genera una sequenza valutando a = q mod m che equivale ad a = m * t + q
     */
    public static List<Integer> generateA(Integer m, Integer q, Integer values) {
        List<Integer> l = new ArrayList<Integer>();
        for (int i = 0 ; i < values ; i++)
            l.add(i * m + q);
        return l;
    }

    /**
     * @param m Modulo
     * @return  Lista di valori dispari minori del modulo <code>m</code> dato
     *
     * Generazione di sequenza di numeri dispari minori del modulo dato
     */
    public static List<Integer> generateX(Integer m) {
        List<Integer> l = new ArrayList<Integer>();
        for (int i = 1 ; i < m ; i += 2)
            l.add(i);
        return l;
    }

}