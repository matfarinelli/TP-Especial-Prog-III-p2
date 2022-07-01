import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

// a los fines del práctico representa cada Género
public class Vertice<T> {

    private String id;
    private HashMap<String, Arco<T>> arcos;

    public Vertice(String id) {
        this.id = id;
        this.arcos = new HashMap<String, Arco<T>>();
    }

    public String getId() {
        return this.id;
    }

    // protected void agregarArco(String destino, int etiqueta) {
    // Arco<T> arco = new Arco<T>(this.getId(), destino, etiqueta);
    // this.arcos.put(destino, arco);
    // }

    // version sin etiqueta - uso actual
    protected void agregarArco(String destino) {
        Arco<T> arco = new Arco<T>(this.getId(), destino);
        this.arcos.put(destino, arco);
    }

    protected void borrarArco(String destino) {
        this.arcos.remove(destino);
    }

    // todos los arcos del genero
    protected Iterator<Arco<T>> getArcos() {
        Iterator<Arco<T>> ItArcos = this.arcos.values().iterator();
        return ItArcos;
    }

    protected ArrayList<Arco<T>> getArcosArray() {
        ArrayList<Arco<T>> arcos = new ArrayList<>();

        Iterator<Arco<T>> it = this.getArcos();
        while (it.hasNext()) {
            Arco arco = it.next();
            arcos.add(arco);
        }
        return arcos;

    }

    // arco específico a un destino
    protected Arco<T> obtenerArco(String destino) {

        Iterator<Arco<T>> itArcos = getArcos();

        while (itArcos.hasNext()) {
            Arco<T> actual = itArcos.next();

            if (actual.getVerticeDestino().equals(destino)) {
                return actual;
            }
        }
        return null;
    }

    protected boolean existeArco(String destino) {
        return this.arcos.containsKey(destino);
    }

    // el destino de los arcos
    protected Iterator<String> obtenerAdyacentes() {
		ArrayList<String> listaAdyacentes = new ArrayList<String>();
		Iterator<Arco<T>> itArcos = this.getArcos();

		while (itArcos.hasNext()) {
			listaAdyacentes.add(itArcos.next().getVerticeDestino());
		}
		Iterator<String> itAdyacentes = listaAdyacentes.iterator();
		return itAdyacentes;
	}

}
