import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TreeMap;

public class GrafoDirigido<T> implements Grafo<T> {

	private HashMap<String, Vertice<T>> vertices;

	public GrafoDirigido() {
		this.vertices = new HashMap<String, Vertice<T>>();
	}

	@Override
	public void agregarVertice(String verticeId) {
		if (!this.vertices.containsKey(verticeId)) {
			this.vertices.put(verticeId, new Vertice<T>(verticeId));
		}
	}

	@Override
	public void borrarVertice(String verticeId) {
		if (this.vertices.containsKey(verticeId)) {

			for (String vertices : this.vertices.keySet()) {
				if (existeArco(vertices, verticeId)) {
					borrarArco(vertices, verticeId);
				}
			}

			this.vertices.remove(verticeId);
		}
	}

	@Override
	public void agregarArco(String origen, String destino, int etiqueta) {
		if ((this.vertices.containsKey(origen) && (this.vertices.containsKey(destino)))) {
			if (!existeArco(origen, destino)) {
				this.vertices.get(origen).agregarArco(destino, etiqueta);
			}
		}
	}

	@Override
	public void agregarArco(String origen, String destino) {
		if ((this.vertices.containsKey(origen) && (this.vertices.containsKey(destino)))) {
			if (!existeArco(origen, destino)) {
				this.vertices.get(origen).agregarArco(destino);
			}
		}
	}

	@Override
	public void borrarArco(String origen, String destino) {
		if (existeArco(origen, destino)) {
			this.vertices.get(origen).borrarArco(destino);
		}
	}

	@Override
	public boolean contieneVertice(String verticeId) {
		return (this.vertices.containsKey(verticeId));
	}

	@Override
	public boolean existeArco(String origen, String destino) {
		Iterator<Arco<T>> itArcos = obtenerArcos(origen);

		while (itArcos.hasNext()) {
			Arco<T> actual = itArcos.next();

			if (actual.getVerticeDestino().equals(destino)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Arco<T> obtenerArco(String origen, String destino) {
		Iterator<Arco<T>> itArcos = obtenerArcos(origen);

		while (itArcos.hasNext()) {
			Arco<T> actual = itArcos.next();

			if (actual.getVerticeDestino().equals(destino)) {
				return actual;
			}
		}
		return null;
	}

	@Override
	public int cantidadVertices() {
		return this.vertices.size();
	}

	@Override
	public int cantidadArcos() {
		Iterator<Arco<T>> itArcos = this.obtenerArcos();
		int cantidadArcos = 0;

		while (itArcos.hasNext()) {
			if (itArcos.next() != null) {
				cantidadArcos++;
			}

			// itArcos.next();
			// cantidadArcos++;
		}

		return cantidadArcos;

	}

	@Override
	public Iterator<String> obtenerVertices() {
		// keySet devuelve las claves
		Iterator<String> itVertices = this.vertices.keySet().iterator();
		return itVertices;
	}

	@Override
	public Iterator<String> obtenerAdyacentes(String origen) {
		LinkedList<String> listaAdyacentes = new LinkedList<String>();
		Iterator<Arco<T>> itArcos = this.obtenerArcos(origen);

		while (itArcos.hasNext()) {
			listaAdyacentes.add(itArcos.next().getVerticeDestino());
		}
		Iterator<String> itAdyacentes = listaAdyacentes.iterator();
		return itAdyacentes;
	}

	// devuelve arcos de todos los vertices del grafo
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		LinkedList<Arco<T>> listaArcos = new LinkedList<Arco<T>>();

		for (String id : this.vertices.keySet()) {
			Iterator<Arco<T>> itArcos = this.obtenerArcos(id);
			while (itArcos.hasNext()) {
				listaArcos.add(itArcos.next());
			}
		}

		Iterator<Arco<T>> itArcosTotales = listaArcos.iterator();
		return itArcosTotales;
	}

	// devuelve arcos de un vertice especifico
	@Override
	public Iterator<Arco<T>> obtenerArcos(String origen) {
		return this.vertices.get(origen).getArcos();
	}

	public ArrayList<String> obtenerGenerosAfinesV2(String generoBuscado, int n) {

		ArrayList<Arco> arcos = new ArrayList<>();

		Iterator<Arco<T>> it = this.obtenerArcos(generoBuscado);

		while (it.hasNext()) {
			Arco arco = it.next();
			arcos.add(arco);
		}

		Collections.sort(arcos, new Comparator<Arco>() {
			@Override
			public int compare(Arco arco1, Arco arco2) {
				if (arco1.getValor() < arco2.getValor()) {
					return 1;
				} else if (arco1.getValor() > arco2.getValor()) {
					return -1;
				} else
					return 1;
			}
		});

		ArrayList<String> generosAfines = new ArrayList<>();

		int lenght = 0;

		if (n < arcos.size()) {
			lenght = n;
		} else {
			lenght = arcos.size();
		}

		for (int i = 0; i < lenght; i++) {
			generosAfines.add(arcos.get(i).getVerticeDestino());
		}

		return generosAfines;

	}

}
