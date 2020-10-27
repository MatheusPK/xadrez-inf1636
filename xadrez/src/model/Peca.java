package model;

import java.util.HashMap;
import java.util.Map;


enum PecaCor { 
	Preto, Branco;
}

public abstract class Peca {
	private PecaCor cor; 
//	private int x;
//	private int y;
	protected Peca(PecaCor cor) {
		this.cor = cor;
	}
	public PecaCor getCor() {
		return cor;
	}
	
	public abstract int[][] movimentosDisponiveis(int x, int y);

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
}
