package Isra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
		
		if(lerArquivo("livro.txt"))
			System.out.println("Carregando arquivo livro.txt... ok");
		else
			System.out.println("Erro ao carregar arquivo livro.txt");
		arvore.montaArvore(lista);		
		
		LinkedListOfString lista1 = arvore.positionsPre();
		LinkedListOfString lista2 = arvore.positionsPos();
		LinkedListOfString lista3 = arvore.positionsWidth();
		ArrayList<String> lista4 = arvore.positions();
		
		System.out.println("Posições Pré:");
		System.out.println(lista1.toString());
		System.out.println("Posições Pós:");
		System.out.println(lista2.toString());
		System.out.println("Posições Width:");
		System.out.println(lista3.toString());	
		System.out.println("Posições:");
		System.out.println(lista4.toString());
		
		/*		Gerando a árvore... ok
				Capitulos...: 2
				Seções......:
				6
				Subseções...: 2
				Parágrafos..: 16
				Gerando o sumário... ok
				Imprimindo o livro para o arquivo livro_prod.txt... ok.
			*/
	
		
	}
	/* 1. Guardar a estrutura do livro em uma árvore; */
	public static boolean lerArquivo(String arquivo) throws FileNotFoundException, IOException{
		
		try(BufferedReader in = new BufferedReader(new FileReader(arquivo))){ 				
			while(in.ready())  			
				lista.add(in.readLine());
			return true;
		}
	}
	
	/* 2. Gerar o sumário do livro, indicando cada capítulo, cada seção e subseção com os respectivos 
	números de página (assumindo que cada página suporte 15 linhas de texto); */
	
	public void gerarSumario(GeneralTreeOfString arvore){
	
	
	}
	
	
	/* 3. “Imprimir” (gravar) o livro em um arquivo texto de saída, observando o formato a seguir.*/
	
	/*
	
		
		
	*/
	
		
		
		/*
	}
	public static void adiciona(String linha){
		switch(linha.substring(0, 2))
		{
		case "L ":				
			//if(livroUnico)
			//	throw new RuntimeException("só pode haver um livro");
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
		System.out.println(capitulo+" está funcionando");
		arvore.add(tipo, livro);		
	}
	
	public static void emSecao(String tipo){
		System.out.println(secao+" está funcionando");
		
	}
	public static void emSubsecao(String tipo){
		System.out.println(subsecao+" está funcionando");
		
	}
	public static void emParagrafo(String tipo){
		System.out.println(paragrafo+" está funcionando");
		
	}
	*/
	
	
}
