package Isra;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class App {
	
	private static GeneralTreeOfString arvore;
	private static LinkedListOfString lista;
	private static LinkedListOfString livro;
	
	public static void main(String[] args) throws IOException{
		
		arvore = new GeneralTreeOfString();
		lista = new LinkedListOfString();
		
		/*================================================================================*/
		System.out.print("Carregando arquivo livro.txt... ");
		if(lerArquivo("livro.txt"))
			System.out.print("ok\n");
		else{
			System.out.println("Erro ao carregar arquivo livro.txt");
			return;
		}
		
		/*================================================================================*/
		System.out.print("Gerando a árvore... ");
		if(arvore.montaArvore(lista)){
			System.out.print("ok\n");
			livro = arvore.positionsPre();
			printaQuantidade();	
		}
		else
			throw new RuntimeException("Erro ao gerar a árvore");
		System.out.print("Gerando o Sumário...");		
		LinkedListOfString sumario = gerarSumario();
		if(sumario!=null){
			System.out.println("ok\n");
			printaSumario(sumario);
		}
		else
			throw new RuntimeException("Erro ao gerar o sumário");		

	}
	
		public void escondeComents(){	
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
	
	public static LinkedListOfString gerarSumario(){		
		if(livro==null) return null;
		LinkedListOfString sumario = new LinkedListOfString();
		LinkedListOfString livroPronto = new LinkedListOfString();
		int capitulo = 0, secao = 0, subsecao = 0, pagina = 0, linha = 1;
		String texto = "", tipo = "", ordem = "";
		for(int i = 0; i<livro.size();i++){
			texto = livro.get(i);
			tipo = texto.substring(0, 2);
			switch(tipo){
			case "C ":
				capitulo++;
				secao = 0;
				subsecao = 0;
				ordem = String.valueOf(capitulo) + ". ";
				pagina++;
				linha=1;
				livroPronto.add("texto.replace(tipo, ordem));				
				break;
			case "S ":
				secao++;
				subsecao = 0;
				ordem = String.valueOf(capitulo) + "."
							+ String.valueOf(secao) + ". ";
				sumario.add(texto.replace(tipo, ordem));
				break;
			case "SS":
				subsecao++;
				ordem = String.valueOf(capitulo) + "."
						+ String.valueOf(secao) + "."
						+ String.valueOf(subsecao) + ".";
			sumario.add(texto.replace(tipo, ordem));
				break;
			default:
				break;
			}			
		}
		return sumario;		
	}
	
	public static void printaSumario(LinkedListOfString sumario){
		for(int i = 0; i<sumario.size(); i++){
			System.out.println(sumario.get(i));			
		}
	}
	
	
	/* 3. “Imprimir” (gravar) o livro em um arquivo texto de saída, observando o formato a seguir.*/
	
	
	/*Printa na tela a quantidade de capitulos, secoes, subsecoes e paragrafos presentes no livro*/
	public static void printaQuantidade(){
		int capitulos = 0, secoes = 0, subsecoes = 0, paragrafos = 0;
		for(int i = 0; i<livro.size();i++){
			switch(livro.get(i).substring(0, 2)){
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

	public static void geraNumeracao(LinkedListOfString sumario){
		if(livro==null) return;
		int pagina = 0, linha = 0;
		
		
		
	}
}
