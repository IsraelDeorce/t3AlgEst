package general;

import java.util.ArrayList;

public class GeneralTreeOfString {	
	
	private Node root;
	private int count;	
	
	protected static final class Node{
		
		public Node father;
		public String element;
		public int kind;
		public LinkedListOfNodes subtrees;
		
		public Node(String element){
			
			this.father = null;
			this.element = element;
			subtrees = new LinkedListOfNodes();
			switch(element.substring(0,2)){
			case "L ":
				kind = 1;
				break;
			case "C ":
				kind = 2;
				break;
			case "S ":
				kind = 3;
				break;
			case "SS":
				kind = 4;
				break;
			case "P ":
				kind = 5;
				break;
			}	
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
				throw new RuntimeException("Index inválido");
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
	public boolean add(Node father, Node novo){
		return add(father.element, novo.element);
	}
	
	// insere o elemento e como	filho de father
	public boolean add(String father, String element){
		Node n = new Node(element);
		if(father==null){//se for nulo, é para adicionar na raiz
			if(root!=null){	
				root.father = n;
				n.addSubtree(root);
			}
			root = n;			
		}
		//se father não é nulo, é para adicionar como subtree dele
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
			throw new RuntimeException("Raíz nula");			
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
			throw new RuntimeException("Elemento não encontrado!");
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
	
	//retorna true se a árvore contém o	elemento
	public boolean contains(String e){
		if(searchNodeRef(e,root)!=null)
			return true;
		return false;
	}
	
	// retorna true se o elemento está armazenado em um nodo interno
	public boolean isInternal(String e){
		Node n = searchNodeRef(e, root);
		if(n!=null){
			return n.getSubtreeSize()>0;
		}
		return false;
	}
	
	// retorna true se o elemento está armazenado em um nodo externo
	public boolean isExternal(String e){
		Node n = searchNodeRef(e, root);
		if(n!=null){
			return n.getSubtreeSize()==0;
		}
		return false;
	}
	
	//retorna true se o elemento e está	armazenado na raiz
	public boolean isRoot(String e){
		if(e!=null){
			return root.element == e;
		}
		return false;
	}
	
	//retorna true se a árvore está vazia
	public boolean isEmpty(){
		return count==0;
	}
	
	//retorna o número de elementos armazenados na árvore
	public int size(){
		return count;
	}
	
	//remove todos os elementos da árvore
	public void clear(){
		root = null;
		count = 0;
	}
	
	//retorna uma lista com todos os elementos da árvore na ordem pré-fixada
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
	
	//retorna uma lista com todos os elementos da árvore na ordem pos-fixada
	public LinkedListOfString positionsPos(){
		LinkedListOfString lista = new LinkedListOfString();
		positionsPosAux(root, lista);
		return lista;		
	}
	public void positionsPosAux(Node n, LinkedListOfString lista){
		if(n!=null){			
			for(int i=0; i<n.getSubtreeSize();i++){
				positionsPosAux(n.getSubtree(i),lista);			}
			lista.add(n.element);
		}
	}
	
	// retorna uma lista com todos os elementos da árvore com um caminhamento em largura
	public LinkedListOfString positionsWidth(){
		LinkedListOfString lista = new LinkedListOfString();
		if(root!=null){
			LinkedQueueOfNodes fila = new LinkedQueueOfNodes();
			Node aux = root;
			fila.enqueue(aux);
			while(!fila.isEmpty())
				aux=fila.dequeue();
				lista.add(aux.element);
				for(int i=0; i<aux.getSubtreeSize(); i++)
					fila.enqueue(aux.getSubtree(i));			
		}
		return lista;
	}
	
	public void plantar(ArrayList<String> linhas){
		
		plantar(root, linhas, 1);		
	}
	
	private void plantar(Node father, ArrayList<String> linhas, int pos){
		while(pos<linhas.size()){
			Node novo = new Node(linhas.get(pos));
			while(!(novo.kind<father.kind))
				father = father.father;
			this.add(father, novo);
			pos++;
			if(novo.kind!=5)
				father=novo;
			plantar(father, linhas, pos);
		}
	}
	
	@Override
	public String toString() {
		return "GeneralTreeOfInteger [root=" + root + ", count=" + count + "]";
	}
		
}
