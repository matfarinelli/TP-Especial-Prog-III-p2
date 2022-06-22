import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {

    String csvFile = "";
    String line = "";
    String cvsSplitBy = ",";

    public CSVReader(String csvFile) {
        this.csvFile = csvFile;
    }

    public void cargarGrafo(Grafo grafo) {

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            // br.readLine();

            int contador_linea = 0;

            while ((line = br.readLine()) != null) {

                if (contador_linea > 0) {
                    // fraccionado del csv
                    String[] generos = line.split(cvsSplitBy);

                    // para recorrer arreglo de la linea de busqueda
                    int i = 0;
                    while (i < generos.length) {

                        // para omitir primera linea - titulos de csv
                        if (generos[0].equals("Generos")) {
                            continue;
                        }
                        // al ser un hash map , no repite generos
                        grafo.agregarVertice(generos[i]);

                        if (i + 1 < generos.length) {

                            if (grafo.existeArco(generos[i], generos[i + 1])) {
                                grafo.obtenerArco(generos[i], generos[i + 1]).incrementarValor();
                            } else {
                                grafo.agregarVertice(generos[i + 1]);
                                grafo.agregarArco(generos[i], generos[i + 1]);
                            }
                        }

                        i++;

                    }

                }

                contador_linea++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
