import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        // pruebas
        GrafoDirigido<String> grafo_d = new GrafoDirigido<String>();

        CSVReader reader = new CSVReader("./dataset1.csv");
        reader.cargarGrafo(grafo_d);

        // ver arcos
        // Iterator<Arco<String>> it = grafo_d.obtenerArcos("historia");
        // Iterator<Arco<String>> it = grafo_d.obtenerGenerosAfines("periodismo");
        // while (it.hasNext()) {
        // Arco arco = it.next();
        // arco.getDatos();
        // }

        // System.out.println(grafo_d.obtenerArco("periodismo", "viajes"));

        /*
         * ArrayList<String> generosCandidatos = new ArrayList<>();
         * Iterator<String> it2 = grafo_d.obtenerVertices();
         * while (it2.hasNext()) {
         * String genero = it2.next();
         * generosCandidatos.add(genero);
         * }
         */

        // System.out.println(generosCandidatos);

        /*
         * Iterator<Arco<String>> it = grafo_d.obtenerArcos();
         * while (it.hasNext()) {
         * Arco arco = it.next();
         * arco.getDatos();
         * }
         */

        // ArrayList<String> generosTotales = new ArrayList<>();
        // Iterator<String> it2 = grafo_d.obtenerVertices();
        // while (it2.hasNext()) {
        // String genero = it2.next();
        // generosTotales.add(genero);
        // }

        // for (String genero : generosTotales) {
        // System.out.println("Generos afines a " + genero + " " +
        // grafo_d.obtenerGenerosAfines(genero, 3));
        // }

        // ver! genera null pointer
        // grafo_d.secuenciaGeneros("poesía");

        // grafo_d.secuenciaGeneros("policial");

        // grafo_d.obtenerGenerosCiclo("poesía");

        GrafoDirigido grafoSolucion = new GrafoDirigido<>();

        grafoSolucion.cicloGenerosAfines("poesía", grafoSolucion);

        ArrayList<String> generosTotales = new ArrayList<>();
        Iterator<String> it2 = grafo_d.obtenerVertices();
        while (it2.hasNext()) {
            String genero = it2.next();
            generosTotales.add(genero);
        }

        System.out.println(generosTotales);

    }

}
