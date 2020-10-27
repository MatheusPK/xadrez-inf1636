package model;

public class Torre extends Peca {
	
	protected Torre(PecaCor cor) {
		super(cor);
	}

	@Override
	public int[][] movimentosDisponiveis(int x, int y) {
		Peca[][] tab = Tabuleiro.getGameMatrix();
		int [][] movimentos = new int[63][2];
		int movCount = 0;
		
//		for(int i = x + 1; i < 8; i++) { // movimentos a direita da peca
//			if(tab[i][y] != null) {
//				if(tab[i][y].getCor() != this.getCor()) {
//					movimentos[movCount][0] = i;
//					movimentos[movCount][1] = y;
//				}
//				break;
//			} 
//			else {
//				movimentos[movCount][0] = i;
//				movimentos[movCount][1] = y;
//			}
//		}
		
		
		movCount = percorre(x, y, 1, 0, tab, movimentos, movCount);
		movCount = percorre(x, y, -1, 0, tab, movimentos, movCount);
		movCount = percorre(x, y, 0, 1, tab, movimentos, movCount);
		movCount = percorre(x, y, 0, -1, tab, movimentos, movCount);
		
		System.out.println(movCount);
		
		int [][] newMovimentos = new int[movCount][2];
		for (int i = 0; i < movCount; i++) {
			newMovimentos[i] = movimentos[i].clone();
		}
		
		return newMovimentos;
	}
	
}
