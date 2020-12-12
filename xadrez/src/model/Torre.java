package model;

class Torre extends Peca {
	
	protected Boolean hasMoved = false;
	
	protected Torre(PecaCor cor) {
		super(cor);
	}
	
	protected Torre(PecaCor cor, int x, int y) {
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
		
		return ModelFacade.reduzArray(movimentos, movCount);
	}
	
	public Peca realizaMovimento(int x, int y) {
        Peca p = super.realizaMovimento(x, y);
        this.hasMoved = true;
        return p;
        
    }  
	
}
