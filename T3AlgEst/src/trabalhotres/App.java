package trabalhotres;

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
	private static LinkedListOfString sumario;
	
	public static void main(String[] args) throws IOException{
		
		arvore = new GeneralTreeOfString();
		lista = new LinkedListOfString();
		sumario = new LinkedListOfString();
		
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
		
		/*================================================================================*/
		System.out.print("Gerando o Sumário e gravando o Livro...");		
		if(geraDocumento())
			System.out.print("ok\n");
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
	/* 3. “Imprimir” (gravar) o livro em um arquivo texto de saída, observando o formato a seguir.*/	
	public static boolean geraDocumento() throws UnsupportedEncodingException, FileNotFoundException, IOException{	
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(				
	       new FileOutputStream("livro_prod.txt"), "utf-8"))) {
				writer.write("--------------------------------------------");
				for(int i=1; i<=15; i++){
					writer.write("\n"+i);
					if(i==7)
						writer.write("               "+livro.get(0)+"               ");
					if(i==15)
						writer.write("\n-------------------------------------- Capa");
				}	
				
				int capitulo = 0, secao = 0, subsecao = 0;
				String linha = "", tipo = "", numeracao = "";				
				int pag = 1;	
				
				for(int i=0, j=0; i<lista.size(); i++){								
							linha = livro.get(i);							
							tipo = linha.substring(0, 2);
							switch(tipo){
							case "C ":
								j++;								
								capitulo++;
								secao = 0;
								subsecao = 0;
								numeracao = String.valueOf(capitulo) + ". ";									
								writer.write("\n"+j+"  "+linha.replace(tipo, numeracao));
								String pontinhosC = linha.replace(tipo, numeracao);
								while(pontinhosC.length()<40){
									pontinhosC = pontinhosC + "."	;								
								}								
								sumario.add(pontinhosC + pag);
								if(j>=15){
									j=0;									
									writer.write("\n-------------------------------------- pg."+pag);
									pag++;
								}								
								break;						
							case "S ":
								j++;
								secao++;
								subsecao = 0;
								numeracao = String.valueOf(capitulo) + "."
											+ String.valueOf(secao) + ". ";	
								writer.write("\n"+j+"  "+linha.replace(tipo, numeracao));
								String pontinhosS = linha.replace(tipo, numeracao);
								while(pontinhosS.length()<40){
									pontinhosS = pontinhosS + "."	;								
								}
								sumario.add(pontinhosS + pag);
								if(j>=15){
									j=0;									
									writer.write("\n-------------------------------------- pg."+pag);	
									pag++;
								}
								break;						
							case "SS":
								j++;
								subsecao++;
								numeracao = String.valueOf(capitulo) + "."
										+ String.valueOf(secao) + "."
										+ String.valueOf(subsecao) + ".";	
								writer.write("\n"+j+"  "+linha.replace(tipo, numeracao));
								String pontinhosSS = linha.replace(tipo, numeracao);
								while(pontinhosSS.length()<40){
									pontinhosSS = pontinhosSS + "."	;								
								}								
								sumario.add(pontinhosSS + pag);
								if(j>=15){
									j=0;									
									writer.write("\n-------------------------------------- pg."+pag);	
									pag++;
								}
								break;						
							case "P ":
								for(int k=0; k < Integer.parseInt(livro.get(i).substring(2, livro.get(i).length()));){
									j++;
									k++;										
									writer.write("\n"+j+"  "+"Lorem Ipsum "+k);
									if(j>=15){
										j=0;										
										writer.write("\n-------------------------------------- pg."+pag);
										pag++;
									}
								}									
							default:
								break;
							}														
				}				
				writer.write("\n-------------------------------------- pg."+pag);
				writer.write("\nSUMARIO");
				for(int i=0; i<sumario.size();i++){					
					writer.write("\n"+sumario.get(i));				
				}
			}	
		return true;
	}	
	
	public static void printaSumario(){
		if(sumario.isEmpty())
			return;
		for(int i = 0; i<sumario.size(); i++){
			System.out.println(sumario.get(i));			
		}
	}	
	
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
			*/
		}
}
