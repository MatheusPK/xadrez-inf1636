package model;

public class Rei extends Peca{
	protected Rei(PecaCor cor) {
		super(cor);
	}

	@Override
	public int[][] movimentosDisponiveis(int x, int y) {
		Peca[][] tab = Tabuleiro.getGameMatrix();
		int [][] movimentos = new int[63][2];
		int movCount = 0;
		
		movCount = percorre(x, y, 1, 0, 1, tab, movimentos, movCount);
		movCount = percorre(x, y, -1, 0, 1, tab, movimentos, movCount);
		movCount = percorre(x, y, 0, 1, 1, tab, movimentos, movCount);
		movCount = percorre(x, y, 0, -1, 1, tab, movimentos, movCount);
		
		movCount = percorre(x, y, 1, 1, 1, tab, movimentos, movCount);
		movCount = percorre(x, y, -1, 1, 1, tab, movimentos, movCount);
		movCount = percorre(x, y, -1, -1, 1, tab, movimentos, movCount);
		movCount = percorre(x, y, 1, -1, 1, tab, movimentos, movCount);
		
		System.out.println(movCount);
		
		return reduzArray(movimentos, movCount);
	}
}
