package model;

class Bispo extends Peca{
	protected Bispo(PecaCor cor) {
		super(cor);
	}
	
	protected Bispo(PecaCor cor, int x, int y) {
		super(cor, x, y);
	}

	@Override
	public int[][] movimentosDisponiveis() {
		int [][] movimentos = new int[63][2];
		int movCount = 0;
		
		movCount = percorre(1, 1, movimentos, movCount);
		movCount = percorre(-1, 1, movimentos, movCount);
		movCount = percorre(-1, -1, movimentos, movCount);
		movCount = percorre(1, -1, movimentos, movCount);
		// oiiee
		
		System.out.println(movCount);
		
		return reduzArray(movimentos, movCount);
	}
}
