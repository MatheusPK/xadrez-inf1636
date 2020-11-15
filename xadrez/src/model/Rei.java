package model;

class Rei extends Peca{
	protected Rei(PecaCor cor) {
		super(cor);
	}
	
	protected Rei(PecaCor cor, int x , int y, PecaTipo t) {
		super(cor, x, y, t);
	}
	
	

	@Override
	public int[][] movimentosDisponiveis() {
		int [][] movimentos = new int[63][2];
		int movCount = 0;
		
		movCount = percorre(1, 0, 1, movimentos, movCount);
		movCount = percorre(-1, 0, 1, movimentos, movCount);
		movCount = percorre(0, 1, 1, movimentos, movCount);
		movCount = percorre(0, -1, 1, movimentos, movCount);
		
		movCount = percorre(1, 1, 1, movimentos, movCount);
		movCount = percorre(-1, 1, 1, movimentos, movCount);
		movCount = percorre(-1, -1, 1, movimentos, movCount);
		movCount = percorre(1, -1, 1, movimentos, movCount);
		
		System.out.println(movCount);
		
		return reduzArray(movimentos, movCount);
	}
}
