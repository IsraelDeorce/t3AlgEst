package general;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class App {
	
	private static GeneralTreeOfString arvore;
	private static String livro;
	private static String capitulo;
	private static String secao;
	private static String subsecao;
	private static String paragrafo;
	
	public static void main(String[] args) throws IOException{
		
		arvore = new GeneralTreeOfString();
		boolean livroUnico = false;
		
			
		//===================================================================================
		//Gera os 100 nomes do txt e armazena em um vetor para ser utilizado no gerador de clientes			
		BufferedReader in = new BufferedReader(new FileReader("livro.txt")); 			
		
		for (int i=0; in.ready(); i++) 
		{ 			
			String linha = in.readLine();				
			
			switch(linha.substring(0, 2))
			{
			case "L ":				
				if(livroUnico)
					throw new RuntimeException("s� pode haver um livro");
				livroUnico = true;
				livro = linha;
				arvore.add("linha", null);				
				break;
			case "C ":
				capitulo = linha;
				emCapitulo(linha);				
				break;
			case "S ":
				secao = linha;
				emSecao(linha);				
				break;
			case "SS":
				subsecao = linha;
				emSubsecao(linha);
				break;
			case "P ":
				paragrafo = linha;
				emParagrafo(linha);				
				break;
			default:
				throw new RuntimeException("Erro de leitura do tipo de linha!");				
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
	
	public static void emCapitulo(String tipo){
		System.out.println(capitulo+" est� funcionando");
						
		
	}
	public static void emSecao(String tipo){
		System.out.println(secao+" est� funcionando");
		
	}
	public static void emSubsecao(String tipo){
		System.out.println(subsecao+" est� funcionando");
		
	}
	public static void emParagrafo(String tipo){
		System.out.println(paragrafo+" est� funcionando");
		
	}
}
