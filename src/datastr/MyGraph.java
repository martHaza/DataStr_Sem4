package datastr;

public class MyGraph <Ttype> {

	private Ttype[] vertices;
	private int[][] edges;
	private final int DEFAULT_SIZE = 10;
	private int size = DEFAULT_SIZE;
	private int counter = 0;
	
	public MyGraph()
	{
		vertices = (Ttype[])new Object[size];
		edges = new int[size][size];
	}
	
	public MyGraph(int inputSize) {
		if(inputSize > 0) {
			size = inputSize;
		}
		vertices = (Ttype[])new Object[size];
		edges = new int[size][size];
	}
	public boolean isFull()
	{
		return (counter == size);	
	}
	
	public boolean isEmpty() {
		return (counter == 0);
	}
	
	public int howManyElements() {
		return counter;
	}
	
	
	private void resize()
	{
		size = (counter < 100) ? size * 2 : (int)(size * 1.5);
		Ttype[] newVertices = (Ttype[])new Object[size];
		int[][] newEdges = new int[size][size];
		
		for(int i = 0; i < counter; i++) {
			newVertices[i] = vertices[i];
		}
		
		for(int i = 0; i < counter; i++) {
			for(int j = 0; j < counter; j++) {
				newEdges[i][j] = edges[i][j];
			}
		}
		
		vertices = newVertices;
		edges = newEdges;
		System.gc();
		
	}
	//addVertice
	public void addVertice(Ttype element) throws Exception {
		if(element == null) {
			throw new Exception("Padotais elements nevar būt bez references!");
		}
		
		if(doesVerticeExist(element)) {
			throw new Exception("Tāda virsotne jau eksistē!");
		}
		
		vertices[counter] = element;
		counter++;
	}
	
	private boolean doesVerticeExist(Ttype element) {
		for(int i = 0; i < counter; i++) {
			if(vertices[i].equals(element)) {
				return true;
			} 
		} 
		
		return false;
	}
	//addEdge
	public void addEdge(Ttype elementFrom, Ttype elementTo, int weight) throws Exception {
		if(elementFrom == null || elementTo == null || weight <= 0) {
			throw new Exception("Ievades parametrs nav atbilstošs!");
		}
		
		if(doesVerticeExist(elementFrom)) {
			throw new Exception("Virsotne, no kuras vēlas veidot saiti, neeksistē grafā!");
		}
		
		if(doesVerticeExist(elementTo)) {
			throw new Exception("Virsotne, uz kuru vēlas veidot saiti, neeksistē grafā!");
		}
		// vai ceļš jau eksistē
		int indexOfElementFrom = getIndexOfVertice(elementFrom);
		int indexOfElementTo = getIndexOfVertice(elementTo);
		int possibleExistingEdge = edges[indexOfElementFrom][indexOfElementTo];
		
		if(possibleExistingEdge > 0) {
			throw new Exception("Savienojums starp " + elementFrom + " un" + elementTo + " jau eksistē!");
		}
		
		edges[indexOfElementFrom][indexOfElementTo] = weight;
		
	}
	
	private int getIndexOfVertice(Ttype element) {
		for(int i = 0; i < counter; i++) {
			if(vertices[i].equals(element)) {
				return i;
			} 
		} 
		
		return -1;
	}
	//print
	
	
	
	public void makeEmpty() {
		if(!isEmpty()) {
			size = DEFAULT_SIZE;
			counter = 0;
			vertices = (Ttype[])new Object[size];
			edges = new int[size][size];
			System.gc();
		}
	}
	
}
