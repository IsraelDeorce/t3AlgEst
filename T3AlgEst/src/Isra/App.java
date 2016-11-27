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
	private static LinkedListOfString lista;
	private static int pos = 0;
	
	public static void main(String[] args) throws IOException{
		
		arvore = new GeneralTreeOfString();
		lista = new LinkedListOfString();
		//boolean livroUnico = false;
		
		if(lerArquivo("livro.txt"))
			System.out.println("Carregando arquivo livro.txt... ok");
		else
			System.out.println("Erro ao carregar arquivo livro.txt");
		
		if(arvore.montaArvore(lista)){
			System.out.println("Gerando a árvore... ok");
			int capitulos = 0, secoes = 0, subsecoes = 0, paragrafos = 0;
			for(int i = 0; i<lista.size();i++){
				switch(lista.get(i).substring(0, 2)){
					case "C ":
						capitulos++;
						break;
					case "S ":
						secoes++;
						break;
					case "SS":
						subsecoes++;
						break;
					case "P ":
						paragrafos++;
						break;
				}
			}		
			
		System.out.println("Capitulos...: " + capitulos
				+ "\nSeções......: " + secoes
				+ "\nSubseções...: " + subsecoes
				+ "\nParágrafos..: " + paragrafos);			
		}
		else
			System.out.println("Erro ao gerar a árvore");
		System.out.println("Sumário");
		LinkedListOfString sumario = gerarSumario(arvore.positionsPre());
		for(int i = 0; i<sumario.size(); i++){
			System.out.println(sumario.get(i));			
		}
	
			
		/*
		LinkedListOfString lista1 = arvore.positionsPre();
		LinkedListOfString lista2 = arvore.positionsPos();
		LinkedListOfString lista3 = arvore.positionsWidth();
		LinkedListOfString lista4 = arvore.positions();
		
		System.out.println("Posições Pré:");
		System.out.println(lista1.toString());
		System.out.println("Posições Pós:");
		System.out.println(lista2.toString());
		System.out.println("Posições Width:");
		System.out.println(lista3.toString());	
		System.out.println("Posições:");
		System.out.println(lista4.toString());
		
							
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
	
	public static LinkedListOfString gerarSumario(LinkedListOfString lista){
		LinkedListOfString sumario = new LinkedListOfString();
		int capitulo = 0, secao = 0, subsecao = 0;
		String linha = "", tipo = "", numeracao = "";
		for(int i = 0; i<lista.size();i++){
			linha = lista.get(i);
			tipo = linha.substring(0, 2);
			switch(tipo){
			case "C ":
				capitulo++;
				secao = 0;
				subsecao = 0;
				numeracao = String.valueOf(capitulo) + ". ";
				sumario.add(linha.replace(tipo, numeracao)); 
				break;
			case "S ":
				secao++;
				subsecao = 0;
				numeracao = String.valueOf(capitulo) + "."
							+ String.valueOf(secao) + ". ";
				sumario.add(linha.replace(tipo, numeracao));
				break;
			case "SS":
				subsecao++;
				numeracao = String.valueOf(capitulo) + "."
						+ String.valueOf(secao) + "."
						+ String.valueOf(subsecao) + ".";
			sumario.add(linha.replace(tipo, numeracao));
				break;
			default:
				break;
			}			
		}
		return sumario;
			
		
	
	
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
