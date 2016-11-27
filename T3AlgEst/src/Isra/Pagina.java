package Isra;

public class Pagina {
	LinkedListOfString linhas;
	
	public Pagina(){
		linhas = new LinkedListOfString();
	}
	
	public void geraPaginas(LinkedListOfString livro){
		if(livro==null) return;
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
				sumario.add(texto.replace(tipo, ordem) + "........ " + pagina);
				livroPronto.add(linha + texto.replace(tipo, ordem));
				linha++;
				break;
			case "S ":
				secao++;
				subsecao = 0;
				ordem = String.valueOf(capitulo) + "."
							+ String.valueOf(secao) + ". ";
				sumario.add(texto.replace(tipo, ordem) + pagina);
				livroPronto.add(linha + texto.replace(tipo, ordem));
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
	
}
