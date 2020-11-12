package model;

public class Model {
	
	public static void startGame() {
		Peca.PecaFactory();
	}
	
	public static void desenhaTabuleiro() {
		Tabuleiro.desenhaTabuleiro();
	}
	
	public static int [][] movDisp(int x, int y) {
		Peca p = Tabuleiro.getPecaIn(x, y);
		return p.movimentosDisponiveis();
	}
	
	public static Peca movRealiza(int fromX, int fromY, int toX, int toY) {
		Peca p = Tabuleiro.getPecaIn(fromX, fromY);
		return p.realizaMovimento(toX, toY);
	}

}
