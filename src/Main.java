import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        // pruebas
        GrafoDirigido<String> grafo_d = new GrafoDirigido<String>();

        CSVReader reader = new CSVReader("./dataset2.csv");
        reader.cargarGrafo(grafo_d);

        // ver arcos
        // Iterator<Arco<String>> it = grafo_d.obtenerArcos();
        // Iterator<Arco<String>> it = grafo_d.obtenerGenerosAfines("periodismo");
        // while (it.hasNext()) {
        //     Arco arco = it.next();
        //     arco.getDatos();
        // }

        System.out.println(grafo_d.obtenerGenerosAfines("periodismo", 2));

        // System.out.println(grafo_d.obtenerArco("periodismo", "viajes"));

        // Iterator <Arco <String>> it = grafo_d.obtenerArcos("periodismo");
        // while (it.hasNext()) {
        // Arco arco = it.next();
        // arco.incrementarValor();
        // System.out.println(arco.getDatos());
        // }

        // ver generos del hashmap
        // Iterator<String> it2 = grafo_d.obtenerVertices();
        // while (it2.hasNext()){
        // String genero = it2.next();
        // System.out.println(genero);
        // }

    }

}
