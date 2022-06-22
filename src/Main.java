import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        // pruebas
        GrafoDirigido<String> grafo_d = new GrafoDirigido<String>();

        // grafo_d.agregarVertice("Suspenso");
        // grafo_d.agregarVertice("Acción");
        // grafo_d.agregarVertice("Terror");
        // grafo_d.agregarVertice("Thriller");
        // grafo_d.agregarVertice("Comedia");
        // grafo_d.agregarVertice("Satira");
        // grafo_d.agregarVertice("Romantica");
        // grafo_d.agregarVertice("Aventura");

        // grafo_d.agregarArco("Suspenso", "Acción");
        // grafo_d.agregarArco("Acción", "Thriller");
        // grafo_d.agregarArco("Accion", "Aventura");
        // grafo_d.agregarArco("Comedia", "Romantica");
        // grafo_d.agregarArco("Suspenso", "Thriller");
        // grafo_d.agregarArco("Comedia", "Aventura");
        // grafo_d.agregarArco("Comedia", "Satira");

        CSVReader reader = new CSVReader("./dataset1.csv");
        reader.cargarGrafo(grafo_d);

        // ver arcos
        // Iterator<Arco<String>> it = grafo_d.obtenerArcos();
        // while (it.hasNext()) {
        // Arco arco = it.next();
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
