import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        // pruebas
        GrafoDirigido<String> grafo_d = new GrafoDirigido<String>();

        CSVReader reader = new CSVReader("./dataset1.csv");
        reader.cargarGrafo(grafo_d);

        // servicio 1

        // System.out.println(grafo_d.obtenerGenerosAfines("viajes", 3));

        // servicio 2

        // System.out.println(grafo_d.obtenerMayorSecuenciaGeneros("humor"));

        // servicio 3

        GrafoDirigido grafoSolucion = new GrafoDirigido<>();

        grafoSolucion = grafo_d.cicloGenerosAfines("viajes");

        // System.out.println(grafo_d.cantidadArcos());

        // System.out.println(grafo_d.existeArco("viajes", "marketing"));
        // System.out.println(grafo_d.existeArco("historia", "terror"));

        // ver arcos

        // Iterator<Arco> it = grafo_d.obtenerTodoslosArcos();
        // // Iterator<Arco<String>> it = grafo_d.obtenerGenerosAfines("periodismo");
        // while (it.hasNext()) {
        // Arco arco = it.next();
        // arco.getDatos();
        // }

        // ver generos

        // Iterator<String> itGeneros = grafo_d.obtenerVertices();
        // while (itGeneros.hasNext()) {
        // String genero = itGeneros.next();
        // System.out.println(genero.toString());
        // }

    }

}
