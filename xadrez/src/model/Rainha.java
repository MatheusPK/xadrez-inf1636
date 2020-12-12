package model;

class Rainha extends Peca{
	
	protected Rainha(PecaCor cor) {
		super(cor);
	}
	
	protected Rainha(PecaCor cor, int x, int y) {
		super(cor, x, y);
	}

	@Override
	public int[][] movimentosDisponiveis() {
		int [][] movimentos = new int[63][2];
		int movCount = 0;
		
		movCount = percorre(1, 0, movimentos, movCount);
		movCount = percorre(-1, 0, movimentos, movCount);
		movCount = percorre(0, 1, movimentos, movCount);
		movCount = percorre(0, -1, movimentos, movCount);
		
		movCount = percorre(1, 1, movimentos, movCount);
		movCount = percorre(-1, 1, movimentos, movCount);
		movCount = percorre(-1, -1, movimentos, movCount);
		movCount = percorre(1, -1, movimentos, movCount);
		
		return ModelFacade.reduzArray(movimentos, movCount);
	}
}
