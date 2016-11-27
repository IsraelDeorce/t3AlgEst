package Isra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class App {
	
	private static GeneralTreeOfString arvore;
	private static LinkedListOfString lista;
	private static LinkedListOfString livro;
	private static LinkedListOfString livroPronto;
	private static LinkedListOfString sumario;
	
	public static void main(String[] args) throws IOException{
		
		arvore = new GeneralTreeOfString();
		lista = new LinkedListOfString();
		livroPronto = new LinkedListOfString();
		
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
		sumario = gerarSumario();
		if(sumario!=null){
			System.out.println("ok\n");
			//printaSumario(sumario);
		}
		else
			throw new RuntimeException("Erro ao gerar o sumário");
		
		geraPaginas();
		System.out.println(livroPronto);
	}
	
	
		
		public static void geraDocumento() throws UnsupportedEncodingException, FileNotFoundException, IOException{
			try (Writer writer = new BufferedWriter(new OutputStreamWriter(
		       new FileOutputStream("livro_prod.txt"), "utf-8"))) {
					writer.write("--------------------------------------------");
					for(int i=1; i<=umaPagina; i++){
						writer.write("\n"+i);
						if(i==7)
							writer.write("               "+livro.get(0)+"               ");
						if(i==15)
							writer.write("\n-------------------------------------- Capa");
					}				
						for(int i=1; i<=livro.size(); i++){											
							int capitulo = 0, secao = 0, subsecao = 0, paragrafo = 0;
							String linha = "", tipo = "", numeracao = "";
							for(int j=0; j<=umaPagina;j++){							
								linha = livro.get(j);
								tipo = linha.substring(0, 2);
								switch(tipo){
								case "C ":
									capitulo++;
									secao = 0;
									subsecao = 0;
									paragrafo = 0;
									numeracao = String.valueOf(capitulo) + ". ";	
									writer.write("\n"+j+"  "+linha.replace(tipo, numeracao));								
									break;
								case "S ":
									secao++;
									subsecao = 0;
									paragrafo = 0;
									numeracao = String.valueOf(capitulo) + "."
												+ String.valueOf(secao) + ". ";	
									writer.write("\n"+j+"  "+linha.replace(tipo, numeracao));
									break;
								case "SS":
									subsecao++;
									paragrafo = 0;
									numeracao = String.valueOf(capitulo) + "."
											+ String.valueOf(secao) + "."
											+ String.valueOf(subsecao) + ".";	
									writer.write("\n"+j+"  "+linha.replace(tipo, numeracao));								
									break;
								case "P ":
									paragrafo++;								
									writer.write("\n"+j+"  "+"Lorem Ipsum "+paragrafo);
								default:
									break;
								}			
							}
							writer.write("\n-------------------------------------- pg."+i);						
						}			
				}

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
				livroPronto.add(texto.replace(tipo, ordem));				
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

	public static void geraPaginas(){
		if(livro==null) return;
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
				sumario.add(texto.replace(tipo, ordem) + "........ " + pagina);
				livroPronto.add(linha + " " + texto.replace(tipo, ordem));
				linha++;
				break;
			
			case "S ":
				secao++;
				subsecao = 0;
				ordem = String.valueOf(capitulo) + "."
							+ String.valueOf(secao) + ". ";
				if(linha<15)
					linha++;
				else{
					linha=1;
					pagina++;
				}
				sumario.add("\t" + texto.replace(tipo, ordem) + "........ " + pagina);
				livroPronto.add(linha + " " + texto.replace(tipo, ordem));
				break;
			
			case "SS":
				subsecao++;
				ordem = String.valueOf(capitulo) + "."
						+ String.valueOf(secao) + "."
						+ String.valueOf(subsecao) + ".";
				if(linha<15)
					linha++;
				else{ 
					linha=1;
					pagina++;
				}
				sumario.add("\t\t" + texto.replace(tipo, ordem) + "........ " + pagina);
				livroPronto.add(linha + " " + texto.replace(tipo, ordem));
				break;
			
			case "P ":
				int tamanho = Integer.parseInt(texto.replaceAll("P ",""));				
				for(int j = 1; j <tamanho; j++){
					livroPronto.add(linha + " Lorem Ipsum " + j);
					if(linha<15)
						linha++;
					else{ 
						linha=1;
						pagina++;
					}
				}
				break;
			}
		}
	}
}
