import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

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
	public void agregarArco(String origen_destino, String destino, int etiqueta) {
		if ((this.vertices.containsKey(origen_destino) && (this.vertices.containsKey(destino)))) {
			if (!existeArco(origen_destino, destino)) {
				this.vertices.get(origen_destino).agregarArco(destino, etiqueta);
			}
		}
	}

	@Override
	public void agregarArco(String origen_destino, String destino) {
		if ((this.vertices.containsKey(origen_destino) && (this.vertices.containsKey(destino)))) {
			if (!existeArco(origen_destino, destino)) {
				this.vertices.get(origen_destino).agregarArco(destino);
			}
		}
	}

	@Override
	public void borrarArco(String origen_destino, String destino) {
		if (existeArco(origen_destino, destino)) {
			this.vertices.get(origen_destino).borrarArco(destino);
		}
	}

	@Override
	public boolean contieneVertice(String verticeId) {
		return (this.vertices.containsKey(verticeId));
	}

	@Override
	public boolean existeArco(String origen_destino, String destino) {
		Iterator<Arco<T>> itArcos = obtenerArcos(origen_destino);

		while (itArcos.hasNext()) {
			Arco<T> actual = itArcos.next();

			if (actual.getVerticeDestino().equals(destino)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Arco<T> obtenerArco(String origen_destino, String destino) {
		Iterator<Arco<T>> itArcos = obtenerArcos(origen_destino);

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
	public Iterator<String> obtenerAdyacentes(String origen_destino) {
		LinkedList<String> listaAdyacentes = new LinkedList<String>();
		Iterator<Arco<T>> itArcos = this.obtenerArcos(origen_destino);

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
	public Iterator<Arco<T>> obtenerArcos(String origen_destino) {
		return this.vertices.get(origen_destino).getArcos();
	}

	public ArrayList<String> obtenerGenerosAfines(String generoBuscado, int n) {

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

	// servicio 2

	public ArrayList<String> secuenciaGeneros(String generoorigen_destino) {

		ArrayList<String> solucion = new ArrayList<>();
		ArrayList<String> generosCandidatos = new ArrayList<>(); // candidatos
		// // HashMap<String, Boolean> visitados = new HashMap<>();
		// ArrayList<String> visitados = new ArrayList<>();
		int sumaArcos = 0;

		Iterator<String> it = this.obtenerVertices(); // todos los generos
		while (it.hasNext()) {
			String genero = it.next();
			generosCandidatos.add(genero);
		}

		// System.out.println(generosCandidatos);

		// situación que evitar NUll point exception - por si el genero no existe
		if (generosCandidatos.contains(generoorigen_destino)) {

			while (!generosCandidatos.isEmpty() && generoorigen_destino != null) {
				// visitados.add(generoorigen_destino);
				solucion.add(generoorigen_destino);

				generosCandidatos.remove(generoorigen_destino);

				// para ver el recorrido entre arco y arco
				System.out.println("Lista solucion:" + solucion.toString() + " " + solucion.size());
				System.out.println("Suma de pesos: " + sumaArcos);

				// si devuelve null, se acaba el ciclo
				Arco arcoAdyMayor = this.seleccionarArcoMayorPeso(generoorigen_destino, generosCandidatos);

				sumaArcos += arcoAdyMayor.getValor();

				generoorigen_destino = arcoAdyMayor.getVerticeDestino();

			}
		}

		return solucion;
	}

	public Arco seleccionarArcoMayorPeso(String generoorigen_destino, ArrayList<String> generosCandidatos) {
		Iterator<Arco<T>> it = this.obtenerArcos(generoorigen_destino);
		Arco arcoMayor = new Arco(null, null, 0);

		while (it.hasNext()) {
			Arco arco = it.next();

			if (arco.getValor() > arcoMayor.getValor() && generosCandidatos.contains(arco.getVerticeDestino())) {
				arcoMayor = arco;
			}
		}

		// System.out.println(arcoMayor.getValor() + " " +
		// arcoMayor.getVerticeDestino());

		return arcoMayor;
	}

	// servicio 3
	// por backtracking

	public void cicloGenerosAfines(String origen_destino, Grafo solucion) {
	//public Grafo cicloGenerosAfines(String origen_destino) {
		//GrafoDirigido solucion = new GrafoDirigido();
		HashSet<String> visitados = new HashSet<>(); // me permite que no se repitan los generos visitados
		solucion.agregarVertice(origen_destino);
		String actual = origen_destino;
		this.Backtracking(origen_destino, actual, solucion, visitados);

		//return solucion;
	}

	private void Backtracking(String origen_destino, String actual, Grafo solucion, HashSet<String> visitados) {
		if (actual == origen_destino) { // situación corte, hay un ciclo
			return;
		} else {
			// del genero actual, debo obtener todos sus adyacentes
			Iterator<String> itGeneroAdy = this.obtenerAdyacentes(actual);

			while (itGeneroAdy.hasNext()) {
				String generoAdy = itGeneroAdy.next();
				solucion.agregarVertice(generoAdy);
				visitados.add(generoAdy);

				this.Backtracking(origen_destino, actual, solucion, visitados);

				solucion.borrarVertice(actual);
				visitados.remove(actual);
			}

		}
	}

}
