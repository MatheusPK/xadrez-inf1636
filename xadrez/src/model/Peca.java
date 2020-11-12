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
	//(apenas para facilitar acesso, o que define sua real posicao é seu ij da matrix do tabuleiro)
	protected int x;
	protected int y;
	
	//construtor
	protected Peca(PecaCor cor) {
		this.cor = cor;
	}
	public PecaCor getCor() {
		return cor;
	}
	
	//metodos abstratos
	public abstract int[][] movimentosDisponiveis(int x, int y);

	//peca factory
	public static void PecaFactory() {
		Peca[][] tab = Tabuleiro.getGameMatrix();
		
		// Pecas Brancas
		
		tab[0][0] = new Torre(PecaCor.Branco);
		tab[1][0] = new Cavalo(PecaCor.Branco);	
		tab[2][0] = new Bispo(PecaCor.Branco);
		tab[3][0] = new Rei(PecaCor.Branco);
		tab[4][0] = new Rainha(PecaCor.Branco);
		tab[5][0] = new Bispo(PecaCor.Branco);
		tab[6][0] = new Cavalo(PecaCor.Branco);
		tab[7][0] = new Torre(PecaCor.Branco);	
		for(int i = 0; i < 8; i++) {
			tab[i][1] = new Peao(PecaCor.Branco);
		}
		
		// Pecas Pretas
		
		tab[0][7] = new Torre(PecaCor.Preto);
		tab[1][7] = new Cavalo(PecaCor.Preto);	
		tab[2][7] = new Bispo(PecaCor.Preto);
		tab[3][7] = new Rei(PecaCor.Preto);
		tab[4][7] = new Rainha(PecaCor.Preto);
		tab[5][7] = new Bispo(PecaCor.Preto);
		tab[6][7] = new Cavalo(PecaCor.Preto);	
		tab[7][7] = new Torre(PecaCor.Preto);
		
		for(int i = 0; i < 8; i++) {
			tab[i][6] = new Peao(PecaCor.Preto);
		}
		
	}
	
	//peca analisa movimentos possiveis na direcao (dx, dy) e adiciona em mov
	//recebe qnt de elementos em mov e retorna a nova qnt
	protected int percorre(int x, int y, int dx, int dy, Peca[][] tab, int[][]mov, int count) {
		int c = count;
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
	protected int percorre(int x, int y, int dx, int dy, int limit, Peca[][] tab, int[][]mov, int count) {
		
		int c = count;
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
}
