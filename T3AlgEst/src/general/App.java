package general;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
	
	public static void main(String[] args) throws IOException{
		
		Arvore arvore = new Arvore();
		boolean livroUnico = false;
		
			
		//===================================================================================
		//Gera os 100 nomes do txt e armazena em um vetor para ser utilizado no gerador de clientes			
		BufferedReader in = new BufferedReader(new FileReader("livro.txt")); 
		for (int i=0; in.ready(); i++) { 			
			String linha = in.readLine();
			switch(linha.charAt(0)){
				case "L":
					if(livroUnico)
						throw new RuntimeException("s� pode haver um livro");
					livroUnico = true;
					arvore
				
			
			
			
			
			}		
			
			
			
			
		}
		in.close();	//fecha o arquivo	
		//===================================================================================			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/* TESTES QUE FUNCIONAM (precisa dos TADS Integer e n�o String:
		 * 
		GeneralTreeOfString arvore = new  GeneralTreeOfString();
		arvore.add(15, null);
		arvore.add(23, 15);		
		arvore.add(12, 15);	
		arvore.add(8, 15);	
		arvore.add(5, 23);	
		arvore.add(18, 23);	
		arvore.add(17, 12);	
		arvore.add(7, 8);	
		arvore.add(25, 8);	
		arvore.add(19, 8);
		arvore.add(6, 8);
		
		LinkedListOfInteger lista1 = arvore.positionsPre();
		LinkedListOfInteger lista2 = arvore.positionsPos();
		LinkedListOfInteger lista3 = arvore.positionsWidth();
		
		System.out.println("Posi��es Pr�:");
		System.out.println(lista1.toString());
		System.out.println("Posi��es P�s:");
		System.out.println(lista2.toString());
		System.out.println("Posi��es Width:");
		System.out.println(lista3.toString());	
		*/
	}	
}
