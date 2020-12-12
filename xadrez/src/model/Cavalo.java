package model;

class Cavalo extends Peca{
	protected Cavalo(PecaCor cor) {
		super(cor);
	}
	
	protected Cavalo(PecaCor cor, int x, int y) {
		super(cor, x, y);
	}
	
	@Override
	public int[][] movimentosDisponiveis() {
		int [][] movimentos = new int[63][2];
		int movCount = 0;
		
		movCount = percorre(1, 2, 1, movimentos, movCount);
		movCount = percorre(2, 1, 1, movimentos, movCount);
		
		movCount = percorre(-1, 2, 1, movimentos, movCount);
		movCount = percorre(-2, 1, 1, movimentos, movCount);
		
		movCount = percorre(-1, -2, 1, movimentos, movCount);
		movCount = percorre(-2, -1, 1, movimentos, movCount);
		
		movCount = percorre(1, -2, 1, movimentos, movCount);
		movCount = percorre(2, -1, 1, movimentos, movCount);
		
		return reduzArray(movimentos, movCount);
	}
}
