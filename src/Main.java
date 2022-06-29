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
        // Iterator<String> itGeneros = grafo_d.obtenerVertices();
        // Iterator<Arco> it = grafo_d.obtenerTodoslosArcos();
        // Iterator<Arco<String>> it = grafo_d.obtenerGenerosAfines("periodismo");
        // while (it.hasNext()) {
        //     Arco arco = it.next();
        //     arco.getDatos();
        // }

        // servicio 1

        // System.out.println(grafo_d.obtenerGenerosAfines("deportes", 6));

        // servicio 2

        // grafo_d.obtenerMayorSecuenciaGeneros("deportes");

        // servicio 3

        GrafoDirigido grafoSolucion = new GrafoDirigido<>();

        grafoSolucion = grafo_d.cicloGenerosAfines("viajes");

        // System.out.println(grafo_d.cantidadArcos());

    }

}
