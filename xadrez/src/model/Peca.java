package model;

import java.util.HashMap;
import java.util.Map;

//enum com possiveis valores para cor da peca
enum PecaCor { 
	Preto, Branco;
}




public abstract class Peca {
	//cor da peca
	protected PecaCor cor; 
	
	//posicao x y da peca 
	//(apenas para facilitar acesso interno, o que define sua real posicao � seu ij da matrix do tabuleiro)
	protected int x = 0;
	protected int y = 0;
	protected PecaTipo tipo;
	
	//construtor
	protected Peca(PecaCor cor) {
		this.cor = cor;
	}
	
	//construtor2
	protected Peca(PecaCor cor, int x, int y, PecaTipo t) {
		this.cor = cor;
		this.x = x;
		this.y = y;
		this.tipo = t;
	}
	public PecaCor getCor() {
		return cor;
	}
	
	public PecaTipo getPecaTipo() {
		return tipo;
	}
	
	protected void atualizaPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//metodos abstratos
	public abstract int[][] movimentosDisponiveis();

	//peca factory
	public static void PecaFactory() {
		Peca[][] tab = Tabuleiro.getGameMatrix();
		
		// Pecas Brancas
		
		tab[0][0] = new Torre(PecaCor.Branco, 0, 0, PecaTipo.torreB);
		tab[1][0] = new Cavalo(PecaCor.Branco, 1, 0, PecaTipo.cavaloB);	
		tab[2][0] = new Bispo(PecaCor.Branco, 2, 0, PecaTipo.bispoB);
		tab[3][0] = new Rei(PecaCor.Branco, 3, 0, PecaTipo.reiB);
		tab[4][0] = new Rainha(PecaCor.Branco, 4, 0, PecaTipo.rainhaB);
		tab[5][0] = new Bispo(PecaCor.Branco, 5, 0, PecaTipo.bispoB);
		tab[6][0] = new Cavalo(PecaCor.Branco, 6, 0, PecaTipo.cavaloB);
		tab[7][0] = new Torre(PecaCor.Branco, 7, 0, PecaTipo.torreB);	
		for(int i = 0; i < 8; i++) {
			tab[i][1] = new Peao(PecaCor.Branco, i, 1, PecaTipo.peaoB);
		}
		
		// Pecas Pretas
		
		tab[0][7] = new Torre(PecaCor.Preto, 0, 7, PecaTipo.torreP);
		tab[1][7] = new Cavalo(PecaCor.Preto, 1, 7, PecaTipo.cavaloP);	
		tab[2][7] = new Bispo(PecaCor.Preto, 2, 7, PecaTipo.bispoP);
		tab[3][7] = new Rei(PecaCor.Preto, 3, 7, PecaTipo.reiP);
		tab[4][7] = new Rainha(PecaCor.Preto, 4, 7, PecaTipo.rainhaP);
		tab[5][7] = new Bispo(PecaCor.Preto, 5, 7, PecaTipo.bispoP);
		tab[6][7] = new Cavalo(PecaCor.Preto, 6, 7, PecaTipo.cavaloP);	
		tab[7][7] = new Torre(PecaCor.Preto, 7, 7, PecaTipo.torreP);
		
		for(int i = 0; i < 8; i++) {
			tab[i][6] = new Peao(PecaCor.Preto, i, 6, PecaTipo.peaoP);
		}
		
	}
	
	//peca analisa movimentos possiveis na direcao (dx, dy) e adiciona em mov
	//recebe qnt de elementos em mov e retorna a nova qnt
	protected int percorre(int dx, int dy, int[][]mov, int count) {
		int c = count;
		Peca[][] tab = Tabuleiro.getGameMatrix();
		
		int x = this.x;
		int y = this.y;
		
		x += dx;
		y += dy;
		while (!Tabuleiro.isOutOfBounds(x, y)) {
			Peca p = tab[x][y];
			if (p == null){
				mov[c] = new int[]{x, y}; 
				c++;
			}
			else {
				if (this.cor != p.cor) {
					mov[c] = new int[]{x, y}; 
					c++;
				}
				break;
			}
			x += dx;
			y += dy;
		}
		
		return c;
	}
	
	//peca analisa movimentos possiveis na direcao (dx, dy) com um limite > 0 de passos e adiciona em mov
	//recebe qnt de elementos em mov e retorna a nova qnt
	protected int percorre(int dx, int dy, int limit, int[][]mov, int count) {
		
		int c = count;
		Peca[][] tab = Tabuleiro.getGameMatrix();
		
		int x = this.x;
		int y = this.y;
		
		int vezes = 1;
		
		if (limit <= 0) return c;
		
		x += dx;
		y += dy;
		while (!Tabuleiro.isOutOfBounds(x, y) && vezes <= limit) {
			Peca p = tab[x][y];
			if (p == null){
				mov[c] = new int[]{x, y}; 
				c++;
			}
			else {
				if (this.cor != p.cor) {
					mov[c] = new int[]{x, y}; 
					c++;
				}
				break;
			}
			x += dx;
			y += dy;
			vezes++;
		}
		
		return c;
	}
	
	//funcao generica que cria um novo array do tamanho desejado e copia o conteudo de um antigo array
	protected int [][] reduzArray(int[][] oldArray, int qnt){
		int [][] newArray = new int[qnt][2];
		for (int i = 0; i < qnt; i++) {
			newArray[i] = oldArray[i].clone();
		}
		oldArray = null;
		return newArray;
	}
	
	//considerando que o movimento � valido,
	//move peca no tabuleiro e atualiza seu valor x, y
	//retorna peca comida ou null (caso a casa esteja vazia)
	public Peca realizaMovimento(int x, int y) {
		Peca[][] tab = Tabuleiro.getGameMatrix();
		Peca p = tab[x][y];
		
		tab[this.x][this.y] = null;
		tab[x][y] = this;
		
		this.atualizaPos(x, y);
		
		return p;
	}
}
