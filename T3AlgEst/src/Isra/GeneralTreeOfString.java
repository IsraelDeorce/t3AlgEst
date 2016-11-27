package Isra;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.plaf.synth.SynthSeparatorUI;


public class GeneralTreeOfString {	
	
	private Node root;
	private int count;	
	
	protected static final class Node{
		
		public Node father;
		public String element;
		public LinkedListOfNodes subtrees;
		
		public Node(String element){
			
			this.father = null;
			this.element = element;
			subtrees = new LinkedListOfNodes();
		}
		
		public void addSubtree(Node n){
			n.father = this;
			subtrees.add(n);					
		}		
		
		public boolean removeSubtree(Node n){
			n.father = null;
			return subtrees.remove(n);
		}
		
		public Node getSubtree(int i) {
			if(i<0 || i>=subtrees.size())				
				throw new RuntimeException("Index inv�lido");
			return subtrees.get(i);				
		}
		
		public int getSubtreeSize(){
			return subtrees.size();
		}
		@Override
		public String toString() {
			return "Node [father=" + father + ", element=" + element + ", subtrees=" + subtrees + "]";
		}	
	}
	
	public GeneralTreeOfString(){
		this.root = null;
		this.count = 0;
	}
	public GeneralTreeOfString(String element){
		this.root = new Node(element);
		this.count = 1;
	}
	
	//Retorna referencia de um nodo que possua determinado elemento a partir de uma arvore/subarvore
	private Node searchNodeRef(String element, Node target){
		Node n = null;
		if(target != null){
			if(element.equals(target.element))
				n = target;
			else{
				int i=0;
				Node aux = null;
				while(aux==null && i<target.getSubtreeSize()){
					aux = searchNodeRef(element, target.getSubtree(i));
					i++;
				}
				n=aux;
			}
		}
		return n;	
	}
	
	// insere o elemento e como	filho de father
	public boolean add(String element, String father){
		Node n = new Node(element);
		if(father==null){//se for nulo, � para adicionar na raiz
			if(root!=null){	
				root.father = n;
				n.addSubtree(root);
			}
			root = n;			
		}
		//se father n�o � nulo, � para adicionar como subtree dele
		else{
			Node aux = searchNodeRef(father, root);
			if(aux==null)
				return false;
			aux.addSubtree(n);
			n.father = aux;		
		}
		count++;
		return true;		
	}
	
	// retorna o elemento armazenado na raiz
	public String getRoot(){
		if(root==null)
			throw new RuntimeException("Ra�z nula");			
		return root.element;
	}
	
	//altera o elemento armazenado na raiz
	public void setRoot(String e){
		if(root==null)
			add(e, null);
		root.element = e;						
	}
	
	//retorna o pai do elemento e
	public String getParent(String e){
		Node n = searchNodeRef(e, root);
		if(n==null)
			throw new RuntimeException("Elemento n�o encontrado!");
		return n.father.element;
	}
	
	// remove o elemento e e seus filhos
	public boolean removeBranch(String e){
		Node n = searchNodeRef(e, root);	
		if(n==null)
			 return false;
		count = count - n.getSubtreeSize();
		Node father = n.father;
		father.removeSubtree(n);
		return true;
	}
	
	//retorna true se a �rvore cont�m o	elemento
	public boolean contains(String e){
		if(searchNodeRef(e,root)!=null)
			return true;
		return false;
	}
	
	// retorna true se o elemento est� armazenado em um nodo interno
	public boolean isInternal(String e){
		Node n = searchNodeRef(e, root);
		if(n!=null){
			return n.getSubtreeSize()>0;
		}
		return false;
	}
	
	// retorna true se o elemento est� armazenado em um nodo externo
	public boolean isExternal(String e){
		Node n = searchNodeRef(e, root);
		if(n!=null){
			return n.getSubtreeSize()==0;
		}
		return false;
	}
	
	//retorna true se o elemento e est�	armazenado na raiz
	public boolean isRoot(String e){
		if(e!=null){
			return root.element == e;
		}
		return false;
	}
	
	//retorna true se a �rvore est� vazia
	public boolean isEmpty(){
		return count==0;
	}
	
	//retorna o n�mero de elementos armazenados na �rvore
	public int size(){
		return count;
	}
	
	//remove todos os elementos da �rvore
	public void clear(){
		root = null;
		count = 0;
	}
	
	//retorna uma lista com todos os elementos da �rvore na ordem pr�-fixada
	public LinkedListOfString positionsPre(){
		LinkedListOfString lista = new LinkedListOfString();
		positionsPreAux(root, lista);
		return lista;		
	}
	public void positionsPreAux(Node n, LinkedListOfString lista){
		if(n!=null){
			lista.add(n.element);
			for(int i=0; i<n.getSubtreeSize();i++){
				positionsPreAux(n.getSubtree(i),lista);
			}			
		}
	}
	
	//retorna uma lista com todos os elementos da �rvore na ordem pos-fixada
	public LinkedListOfString positionsPos(){
		LinkedListOfString lista = new LinkedListOfString();
		positionsPosAux(root, lista);
		return lista;		
	}
	public void positionsPosAux(Node n, LinkedListOfString lista){
		if(n!=null){			
			for(int i=0; i<n.getSubtreeSize();i++){
				positionsPosAux(n.getSubtree(i),lista);
			}
			lista.add(n.element);
		}
	}
	
	// retorna uma lista com todos os elementos da �rvore com um caminhamento em largura
	public LinkedListOfString positionsWidth(){
		LinkedListOfString lista = new LinkedListOfString();
		if(root!=null){
			LinkedQueueOfNodes fila = new LinkedQueueOfNodes();
			Node aux = root;
			fila.enqueue(aux);
			while(!fila.isEmpty()){
				aux=fila.dequeue();
				lista.add(aux.element);
				for(int i=0; i<aux.getSubtreeSize(); i++)
					fila.enqueue(aux.getSubtree(i));
			}
		}
		return lista;
	}
	
	public void montaArvore(ArrayList<String> livro){
		if(livro.isEmpty()) return;
		if(livro.size()==1) this.root = new Node(livro.get(0));		
		else{		
			Node pai = new Node(livro.get(0));
			Node filho = new Node(livro.get(1));
			this.root = pai;	
			if(pai.element.substring(0, 2).equals("L ")){			
				montaArvoreAux(pai,filho,2,livro);
			}			
		}
		
	}
	private void montaArvoreAux(Node pai, Node filho, int pos, ArrayList<String> livro){
		if(pos>=livro.size()) return;
		
		
		if(filho.element.substring(0, 2).equals("C ")){			
			this.root.addSubtree(filho);
			montaArvoreAux(filho,new Node(livro.get(pos)),pos+1, livro);
		}		
		if(filho.element.substring(0, 2).equals("S ")){
			pai.addSubtree(filho);
			montaArvoreAux(filho,new Node(livro.get(pos)),pos+1, livro);
		}		
		if(filho.element.substring(0, 2).equals("SS")){			
			pai.addSubtree(filho);			
			montaArvoreAux(filho,new Node(livro.get(pos)),pos+1, livro);
			
		}		
		if(filho.element.substring(0, 2).equals("P ")){
			pai.addSubtree(filho);
			montaArvoreAux(filho.father,new Node(livro.get(pos)),pos+1, livro);
		}		
	}
	
	public ArrayList<String> positions(){
		ArrayList<String> lista = new ArrayList<>();
		if(root != null){
			Queue<Node> fila = new LinkedList<>();
			Node aux = root;
			fila.offer(aux);
			while(!fila.isEmpty()) {
				aux = fila.poll();
				lista.add(aux.element);
				for(int i=0; i<aux.getSubtreeSize(); i++){
					fila.offer(aux.getSubtree(i));
				}
			}
		}
		return lista;
	}
	
	

	@Override
	public String toString() {
		return "GeneralTreeOfInteger [root=" + root + ", count=" + count + "]";
	}
		
}
