package model;

public class Peao extends Peca{
	
	private Boolean hasMoved = false;
	
	protected Peao(PecaCor cor) {
		super(cor);
	}

	@Override
	public int[][] movimentosDisponiveis(int x, int y) {
		Peca[][] tab = Tabuleiro.getGameMatrix();
		int [][] movimentos = new int[63][2];
		int movCount = 0;
		
		
	
		if (!hasMoved) {
			movCount = percorre(x, y, 0, 1, 2, tab, movimentos, movCount);
		}
		else {
			movCount = percorre(x, y, 0, 1, 1, tab, movimentos, movCount);
		}
		
		movCount = movLateral(x, y, tab, movimentos, movCount);
		
		System.out.println(movCount);
		
		return reduzArray(movimentos, movCount);

	}
	
	protected int percorre(int x, int y, int dx, int dy, int limit, Peca[][] tab, int[][]mov, int count) {
		
		int c = count;
		int vezes = 1;
		
		if (limit <= 0) return c;
		
		int signal = 1;
		if (this.getCor() == PecaCor.Preto) {
			signal = -1;
		}
		
		x += dx;
		y += dy*signal;
		while (!Tabuleiro.isOutOfBounds(x, y) && vezes <= limit) {
			Peca p = tab[x][y];
			if (p == null){
				mov[c] = new int[]{x, y}; 
				c++;
			}
			else {
//				if (this.cor != p.cor) {
//					mov[c] = new int[]{x, y}; 
//					c++;
//				}
				break;
			}
			x += dx;
			y += dy*signal;
			vezes++;
		}
		
		return c;
	}
	
	private int movLateral(int x, int y, Peca[][] tab, int[][]mov, int count) {
		int c = count;
		
		int signal = 1;
		if (this.getCor() == PecaCor.Preto) {
			signal = -1;
		}
		
		int x1 = x + 1;
		int y1 = y + signal;
		
		int x2 = x - 1;
		int y2 = y + signal;
		
		if (!Tabuleiro.isOutOfBounds(x1, y1)){
			Peca p = tab[x1][y1];
			if (p != null && p.getCor() != this.getCor()) {
				mov[c] = new int[]{x1, y1}; 
				c++;
			}
		}
		
		if (!Tabuleiro.isOutOfBounds(x2, y2)){
			Peca p = tab[x2][y2];
			if (p != null && p.getCor() != this.getCor()) {
				mov[c] = new int[]{x2, y2}; 
				c++;
			}
		}
		
		
		return c;
	}
}
