import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import timer.Timer;

public class GrafoDirigido<T> implements Grafo<T> {

	private HashMap<String, Vertice<T>> vertices;

	public GrafoDirigido() {
		this.vertices = new HashMap<String, Vertice<T>>();
	}

	@Override
	public void agregarVertice(String verticeId) {
		if (!this.vertices.containsKey(verticeId)) { // O(1)
			this.vertices.put(verticeId, new Vertice<T>(verticeId));
		}
	}

	@Override
	public void borrarVertice(String verticeId) {
		if (this.vertices.containsKey(verticeId)) { // O(1)

			for (String vertices : this.vertices.keySet()) {
				if (existeArco(vertices, verticeId)) { // O(1)
					borrarArco(vertices, verticeId);// O(1)
				}
			}

			this.vertices.remove(verticeId); // O(1)
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
		return vertices.get(origen).existeArco(destino); // O(1)
	}

	@Override
	public Arco<T> obtenerArco(String origen, String destino) {
		return this.vertices.get(origen).obtenerArco(destino);
	}

	@Override
	public int cantidadVertices() {
		return this.vertices.size();
	}

	@Override
	public int cantidadArcos() {
		Iterator<Arco> itArcos = this.obtenerTodoslosArcos();
		int cantidadArcos = 0;

		while (itArcos.hasNext()) {
			if (itArcos.next() != null) {
				cantidadArcos++;
			}
		}

		return cantidadArcos;

	}

	@Override
	public Iterator<String> obtenerVertices() {
		// keySet devuelve las claves
		Iterator<String> itVertices = this.vertices.keySet().iterator();
		return itVertices;
	}

	// @Override
	public Iterator<String> obtenerAdyacentes(String origen) {
		Iterator<String> listaAdyacentes = this.vertices.get(origen).obtenerAdyacentes();
		return listaAdyacentes;
	}

	// obtener todos los arcos del grafo
	public Iterator<Arco> obtenerTodoslosArcos() {
		ArrayList<Arco> listaArcos = new ArrayList<Arco>();

		for (String genero : this.vertices.keySet()) {
			Iterator<Arco<T>> itArcos = this.obtenerArcos(genero);
			while (itArcos.hasNext()) {
				listaArcos.add(itArcos.next());
			}
		}

		return listaArcos.iterator();

	}

	// devuelve arcos de un vertice especifico pidiendole a cada genero (vertice),
	// sus arcos
	@Override
	public Iterator<Arco<T>> obtenerArcos(String origen) {
		return this.vertices.get(origen).getArcos();
	}

	public ArrayList<Arco<T>> obtenerArcosArray(String origen) {
		ArrayList<Arco<T>> arcos = this.vertices.get(origen).getArcosArray();

		return arcos;
	}

	/*
	 * SERVICIO N?? 1
	 * 
	 */

	public ArrayList<String> obtenerGenerosAfines(String generoBuscado, int n) {
		ArrayList<Arco> arcos = new ArrayList<>();

		System.out.println("Tiempo: ");
		Timer timer = new Timer();

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

		System.out.println(timer.stop());
		return generosAfines;

	}

	/*
	 * SERVICIO N?? 2
	 * 
	 */

	public ArrayList<String> obtenerMayorSecuenciaGeneros(String generoOrigen) {

		ArrayList<String> solucion = new ArrayList<>();
		ArrayList<String> generosCandidatos = new ArrayList<>(); // candidatos

		int sumaArcos = 0;

		Timer timer = new Timer();
		System.out.println("Tiempo: ");
		timer.start();

		Iterator<String> it = this.obtenerVertices(); // todos los generos
		while (it.hasNext()) {
			String genero = it.next();
			generosCandidatos.add(genero);
		}

		// situaci??n que evita NUll point exception - por si el genero no existe
		if (generosCandidatos.contains(generoOrigen)) {

			while (!generosCandidatos.isEmpty() && generoOrigen != null) {
				// visitados.add(generoOrigen);
				solucion.add(generoOrigen);

				generosCandidatos.remove(generoOrigen);

				// para ver el recorrido entre arco y arco
				// System.out.println("Lista solucion:" + solucion.toString() + " " +
				// solucion.size());
				// System.out.println("Suma de pesos: " + sumaArcos);

				// si devuelve null, se acaba el ciclo
				Arco arcoAdyMayor = this.seleccionarArcoMayorPeso(generoOrigen, generosCandidatos);

				sumaArcos += arcoAdyMayor.getValor();

				generoOrigen = arcoAdyMayor.getVerticeDestino();

			}
		}

		System.out.println(timer.stop());
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

		// System.out.println("Origen " + generoOrigen + " / arco mayor: " +
		// arcoMayor.getVerticeDestino() + " = " + arcoMayor.getValor());

		return arcoMayor;
	}

	/*
	 * SERVICIO N?? 3
	 * 
	 */

	public GrafoDirigido<T> cicloGenerosAfines(String origen_destino) {
		GrafoDirigido<T> solucion = new GrafoDirigido<T>();
		HashSet<String> visitados = new HashSet<>(); // me permite que no se repitan los generos visitados

		solucion.agregarVertice(origen_destino);

		String actual = "Inicio";

		this.backtracking(origen_destino, actual, solucion, visitados);

		return solucion;
	}

	private void backtracking(String origen_destino, String actual, GrafoDirigido<T> solucion,
			HashSet<String> visitados) {

		if (actual.equals(origen_destino)) { // situaci??n corte, hay un ciclo
			// System.out.println("CICLO ENCONTRADO! por " + actual);
			// // System.out.println("Generos visitados: " + visitados);
			System.out.println("-");

			// solo para control! - borrar!
			Iterator<Arco> it = solucion.obtenerTodoslosArcos();
			while (it.hasNext()) {
				Arco arco = it.next();
				arco.getDatos();
			}
			return;

		} else {
			// situaci??n inicial, para evitar el if de corte
			if (actual.equals("Inicio")) {
				actual = origen_destino;
			}
			// Obtengo array de arcos del generoRecibido
			ArrayList<Arco<T>> arcosGenero = this.obtenerArcosArray(actual);

			// chequeo solo de revisar arcos no visitados
			for (Arco arco : arcosGenero) {
				// si no visite los arcos de ese genero actual u origen
				if (!visitados.contains(arco.getVerticeDestino())) {

					visitados.add(arco.getVerticeDestino());
					solucion.agregarVertice(arco.getVerticeDestino());
					solucion.agregarArco(actual, arco.getVerticeDestino());

					this.backtracking(origen_destino, arco.getVerticeDestino(), solucion, visitados);

					solucion.borrarVertice(arco.getVerticeDestino());
					solucion.borrarArco(actual, arco.getVerticeDestino());
					visitados.remove(arco.getVerticeDestino());
				}
			}
			return;
		}
	}
}
