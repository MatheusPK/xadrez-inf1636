package model;

class Rei extends Peca{
	
//	int [][] impossibleMovs = new int[64][2];
//	int indexImpMovs = 0;
	
	protected Boolean hasMoved = false;
	
	protected Rei(PecaCor cor) {
		super(cor);
	}
	
	protected Rei(PecaCor cor, int x , int y) {
		super(cor, x, y);
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
		
		return ModelFacade.reduzArray(movimentos, movCount);
	}
	
	public Peca realizaMovimento(int x, int y) {
        Peca p = super.realizaMovimento(x, y);
        this.hasMoved = true;
        return p;
    }      
	
//	public void addImpossibleMov(int x, int y) {
//		
//		Boolean repetido = false;
//		
//		for (int [] pos : impossibleMovs) {
//			if (pos[0] == x && pos[1] == y) {
//				repetido = true;
//			}
//		}
//		
//		if (!repetido) {
//			impossibleMovs[indexImpMovs][0] = x;
//			impossibleMovs[indexImpMovs][1] = y;
//			
//			indexImpMovs++;
//		}
//	}
//	
//	public void clearImpossibleMovs() {
//		impossibleMovs = new int[64][2];
//		indexImpMovs = 0;
//	}
//	
//	
//	protected int percorre(int dx, int dy, int limit, int[][]mov, int count) {
//		
//		int c = count;
//		Peca[][] tab = Tabuleiro.getGameMatrix();
//		
//		int x = this.x;
//		int y = this.y;
//		
//		int vezes = 1;
//		
//		if (limit <= 0) return c;
//		
//		x += dx;
//		y += dy;
//		while (!Tabuleiro.isOutOfBounds(x, y) && vezes <= limit) {
//			Peca p = tab[x][y];
//			if (p == null){
//				if (!ModelFacade.isPosInMov(impossibleMovs, x, y)) {
//					mov[c] = new int[]{x, y}; 
//					c++;
//				}
//			}
//			else {
//				if (this.cor != p.cor) {
//					if (!ModelFacade.isPosInMov(impossibleMovs, x, y)) {
//						mov[c] = new int[]{x, y}; 
//						c++;
//					}
//				}
//				break;
//			}
//			x += dx;
//			y += dy;
//			vezes++;
//		}
//		
//		return c;
//	}
	
}


