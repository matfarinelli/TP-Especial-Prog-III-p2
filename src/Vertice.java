import java.util.HashMap;
import java.util.Iterator;

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

    protected void agregarArco(String destino, int etiqueta) {
        Arco<T> arco = new Arco<T>(this.getId(), destino, etiqueta);
        this.arcos.put(destino, arco);
    }
    // version sin etiqueta - uso actual
    protected void agregarArco(String destino) {
        Arco<T> arco = new Arco<T>(this.getId(), destino);
        this.arcos.put(destino, arco);
    }

    protected void borrarArco(String destino) {
        this.arcos.remove(destino);
    }

    protected Iterator<Arco<T>> getArcos() {
        Iterator<Arco<T>> ItArcos = this.arcos.values().iterator();
        return ItArcos;
    }

}
