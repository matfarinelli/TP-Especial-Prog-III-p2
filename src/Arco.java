/*
 * La clase arco representa un arco del grafo. Contiene un vertice origen, un vertice destino y una valor.
 * Nota: Para poder exponer los arcos fuera del grafo y que nadie los modifique se hizo esta clase inmutable
 * (Inmutable: una vez creado el arco no es posible cambiarle los valores).
 */

//public class Arco<T> implements Comparable<Arco<T>> {
public class Arco<T> {
	private String verticeOrigen;
	private String verticeDestino;
	private int valor;

	public Arco(String verticeOrigen, String verticeDestino, int valor) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.valor = valor;
	}

	// uso actual
	public Arco(String verticeOrigen, String verticeDestino) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.valor = 1; // cuando se crea el arco es por se hizo ya una busqueda que vincula esos
						// generos
	}

	public String getVerticeOrigen() {
		return verticeOrigen;
	}

	public String getVerticeDestino() {
		return verticeDestino;
	}

	public int getValor() {
		return valor;
	}

	// al repetir busqueda
	public void incrementarValor() {
		valor++;
	}

	// public String getDatos() {
	// return "O: " + getVerticeOrigen() + " - D: " + getVerticeDestino() + " -
	// Valor: " + this.getValor();
	// }

	// a -> b [label = 2];
	public void getDatos() {
		System.out.print(this.getVerticeOrigen() + " -> " + this.getVerticeDestino());
		System.out.print("[ " + " label = " + this.getValor() + " ];");
		System.out.println();
	}

	@Override
	public boolean equals(Object o) {
		try {
			Arco aux = (Arco) o;
			return (this.getVerticeOrigen().toString().equals(aux.getVerticeOrigen().toString())
					&& this.getVerticeDestino().toString().equals(aux.getVerticeDestino().toString()));
		} catch (Exception e) {
			return false;
		}
	}

	// @Override
	// public int compareTo(Arco<T> o) {
	// 	// return this.getValor() - o.getValor();
	// 	if (this.getValor() > o.getValor()) {
	// 		return 1;
	// 	} else if (this.getValor() < o.getValor()) {
	// 		return -1;
	// 	} else
	// 		return 0;
	// }

}
