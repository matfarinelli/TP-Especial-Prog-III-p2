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
                    ArrayList<String> test = new ArrayList<>();

                    // recorrido de cada genero del libro
                    for (String generoBuscado : generos) {

                        // para omitir primera linea - titulos de csv
                        if (generoBuscado.equals("Generos")) {
                            continue;
                        }

                        if (!test.contains(generoBuscado)) {
                            test.add(generoBuscado);
                        }

                        System.out.println(test);
                    }

                    System.out.println(" ");

                }

                contador_linea++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
