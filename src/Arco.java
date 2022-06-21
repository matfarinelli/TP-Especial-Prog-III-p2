/*
 * La clase arco representa un arco del grafo. Contiene un vertice origen, un vertice destino y una valor.
 * Nota: Para poder exponer los arcos fuera del grafo y que nadie los modifique se hizo esta clase inmutable
 * (Inmutable: una vez creado el arco no es posible cambiarle los valores).
 */
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
		this.valor = 1; // cuando se crea el arco es por se hizo ya una busqueda que vincula esos generos
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

	public String getDatos() {
		return "O: " + getVerticeOrigen() + " - D: " + getVerticeDestino() + " - Valor: " + this.getValor();
	}

}