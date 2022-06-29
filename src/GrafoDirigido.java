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

	// devuelve arcos de un vertice especifico pidiendole a cada genero (vertice), sus arcos
	@Override
	public Iterator<Arco<T>> obtenerArcos(String origen) {
		return this.vertices.get(origen).getArcos();
	}

	public ArrayList<Arco<T>> obtenerArcosArray(String origen) {
		ArrayList<Arco<T>> arcos = new ArrayList<>();

		Iterator<Arco<T>> it = this.obtenerArcos(origen);
		while (it.hasNext()) {
			Arco arco = it.next();
			arcos.add(arco);
		}

		return arcos;
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

	public ArrayList<String> obtenerMayorSecuenciaGeneros(String generoOrigen) {

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
		if (generosCandidatos.contains(generoOrigen)) {

			while (!generosCandidatos.isEmpty() && generoOrigen != null) {
				// visitados.add(generoOrigen);
				solucion.add(generoOrigen);

				generosCandidatos.remove(generoOrigen);

				// para ver el recorrido entre arco y arco
				System.out.println("Lista solucion:" + solucion.toString() + " " + solucion.size());
				System.out.println("Suma de pesos: " + sumaArcos);

				// si devuelve null, se acaba el ciclo
				Arco arcoAdyMayor = this.seleccionarArcoMayorPeso(generoOrigen, generosCandidatos);

				sumaArcos += arcoAdyMayor.getValor();

				generoOrigen = arcoAdyMayor.getVerticeDestino();

			}
		}

		return solucion;
	}

	public Arco seleccionarArcoMayorPeso(String generoOrigen, ArrayList<String> generosCandidatos) {
		Iterator<Arco<T>> it = this.obtenerArcos(generoOrigen);
		Arco arcoMayor = new Arco(null, null, 0); // al estar los arcos ordenados, puede devolver el mayor aunque haya
		// sido recorrido por otro genero anteriormente

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
	// public void cicloGenerosAfines(String origen_destino, Grafo solucion) {
	public GrafoDirigido<T> cicloGenerosAfines(String origen_destino) {
		GrafoDirigido<T> solucion = new GrafoDirigido<T>();
		HashSet<String> visitados = new HashSet<>(); // me permite que no se repitan los generos visitados

		solucion.agregarVertice(origen_destino);

		String actual = "Inicio";
		this.backtracking(origen_destino, actual, solucion, visitados);

		return solucion;
	}

	private void backtracking(String origen_destino, String actual, GrafoDirigido<T> solucion, HashSet<String> visitados) {

		if (actual.equals(origen_destino)) { // situación corte, hay un ciclo
			return;
		} else {

			// situación inicial, para evitar el if de corte
			if (actual.equals("Inicio")) {
				actual = origen_destino;
			}

			ArrayList<Arco<T>> arcosGenero = this.obtenerArcosArray(actual);
			ArrayList<String> generosAdyacentes = new ArrayList<>();

			for (Arco arco : arcosGenero) {
				// si no visite los arcos de ese genero actual u origen
				if (!visitados.contains(arco.getVerticeDestino())) {
					generosAdyacentes.add(arco.getVerticeDestino());
				}
				// System.out.println(arco.getVerticeOrigen() + " " + arco.getVerticeDestino() +
				// " " + arco.getValor());
			}

			for (String generoAdy : generosAdyacentes) {
				solucion.agregarVertice(generoAdy);
				solucion.agregarArco(actual, generoAdy);
				visitados.add(generoAdy);

				System.out.println("genero actual " + actual + "- adyacente : " + generoAdy);
				// System.out.println(visitados.toString());

				this.backtracking(origen_destino, generoAdy, solucion, visitados);

				solucion.borrarVertice(generoAdy);
				solucion.borrarArco(actual, generoAdy);
				visitados.remove(generoAdy);
			}
			return;
		}

	}

}
