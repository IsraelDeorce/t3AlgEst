package Isra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class App {
	
	private static GeneralTreeOfString arvore;
	private static String livro;
	private static String capitulo;
	private static String secao;
	private static String subsecao;
	private static String paragrafo;
	private static ArrayList<String> lista;
	private static int pos = 0;
	
	public static void main(String[] args) throws IOException{
		
		arvore = new GeneralTreeOfString();
		lista = new ArrayList<>();
		//boolean livroUnico = false;
		
		
			
		//===================================================================================
		//Gera os 100 nomes do txt e armazena em um vetor para ser utilizado no gerador de clientes			
		BufferedReader in = new BufferedReader(new FileReader("livro.txt")); 				
		for (int i=0; in.ready(); i++) 		{ 			
			lista.add(in.readLine());							
		}						
		in.close();	//fecha o arquivo	
		
		
		arvore.montaArvore(lista);
		
		LinkedListOfString lista1 = arvore.positionsPre();
		LinkedListOfString lista2 = arvore.positionsPos();
		LinkedListOfString lista3 = arvore.positionsWidth();
		ArrayList<String >lista4 = arvore.positions();
		
		System.out.println("Posi��es Pr�:");
		System.out.println(lista1.toString());
		System.out.println("Posi��es P�s:");
		System.out.println(lista2.toString());
		System.out.println("Posi��es Width:");
		System.out.println(lista3.toString());	
		System.out.println("Posi��es:");
		System.out.println(lista4.toString());
	
		/*
	}
	public static void adiciona(String linha){
		switch(linha.substring(0, 2))
		{
		case "L ":				
			//if(livroUnico)
			//	throw new RuntimeException("s� pode haver um livro");
			//livroUnico = true;
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
	
	public static void preenche() throws IOException{
		BufferedReader in = new BufferedReader(new FileReader("livro.txt")); 	
		lista = new ArrayList<>();
		for (int i=0; in.ready(); i++) 
		{ 			
			lista.add(in.readLine());								
		}
		in.close();
		recursao(lista.get(0));
		
	}

	public static void recursao(String linha){
		
		if(linha.substring(0, 2)=="C "){
		//	arvore.add(linha, );			
		}		
		
		
	}
	
	public static void emCapitulo(String tipo){
		System.out.println(capitulo+" est� funcionando");
		arvore.add(tipo, livro);		
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
	*/
	}
	
}
