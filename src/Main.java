import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        // pruebas
        GrafoDirigido<String> grafo_d = new GrafoDirigido<String>();

        CSVReader reader = new CSVReader("./dataset4.csv");
        reader.cargarGrafo(grafo_d);

        // ver arcos
        // Iterator<Arco<String>> it = grafo_d.obtenerArcos();
        // // //Iterator<Arco<String>> it = grafo_d.obtenerGenerosAfines("periodismo");
        // while (it.hasNext()) {
        //     Arco arco = it.next();
        //     arco.getDatos();
        // }

        // comparar tiempos de ejecución
        //System.out.println(grafo_d.obtenerGenerosAfinesV2("historia", 5));
        System.out.println(grafo_d.obtenerGenerosAfines("historia", 5));

        // System.out.println(grafo_d.obtenerArco("periodismo", "viajes"));

        // Iterator<Arco<String>> it = grafo_d.obtenerArcos("periodismo");
        // while (it.hasNext()) {
        // Arco arco = it.next();
        // arco.getDatos();
        // }

        // // ver generos del hashmap
        // Iterator<String> it2 = grafo_d.obtenerVertices();
        // while (it2.hasNext()) {
        // String genero = it2.next();
        // System.out.println(genero);
        // }

    }

}
