package model;

public class ModelFacade {
	
	public static void startGame() {
		Peca.PecaFactory();
	}
	
	public static void desenhaTabuleiro() {
		Tabuleiro.desenhaTabuleiro();
	}
	
	//assume x y valido e (vez > 0 -> branco, vez < 0 -> preto)
	public static int [][] movDisp(int x, int y, int vez) {
		Peca p = Tabuleiro.getPecaIn(x, y);
		if (p == null) {
			System.out.println("Invalido: Nenhuma peca ai!\n");
			return null;
		}
		PecaCor cor = p.getCor();
		if (cor == PecaCor.Branco && vez <= 0) {
			System.out.println("Invalido: Vez do Preto!\n");
			return null;
		}
		else if (cor == PecaCor.Preto && vez > 0) {
			System.out.println("Invalido: Vez do Branco!\n");
			return null;
		}
		return p.movimentosDisponiveis();
	}
	
	//assume pontos validos
	public static int movRealiza(int fromX, int fromY, int toX, int toY) {
		Peca p = Tabuleiro.getPecaIn(fromX, fromY);
		Peca p2 = p.realizaMovimento(toX, toY);
		return codificaPeca(p2);
	}
	
	public static Boolean isOutOfBounds(int x, int y) {
		return Tabuleiro.isOutOfBounds(x, y);
	}
	
	//transoforma o tipo da peca (classe e cor) em um inteiro correspondente
	private static int codificaPeca(Peca p) {
		int i;
		if (p == null) {
			return 0;
		}
		else if (p instanceof Torre) {
			i = 1;
		}
		else if (p instanceof Cavalo) {
			i = 2;
		}
		else if (p instanceof Bispo) {
			i = 3;
		}
		else if (p instanceof Rei) {
			i = 4;
		}
		else if (p instanceof Rainha) {
			i = 5;
		}
		else if (p instanceof Peao) {
			i = 6;
		}
		else {
			return 0;
		}
		
		if (p.getCor() == PecaCor.Preto) {
			i *= -1;
		}
		
		return i;
	}
	
	//retorna tabuleiro codificado
	public static void codificaTabuleiro(int [][] codeTab) {
		Peca [][] tab = Tabuleiro.getGameMatrix();
		//int [][] codeTab = new int[8][8];
		
		for(int i = 0; i < 8; i ++) {
			for(int j = 0; j < 8; j ++) {
				codeTab[i][j] = codificaPeca(tab[i][j]);
			}
		}
		//return codeTab;
	}
	
	//retorna se posicao é um dos elementos do vetor de mov disp
	public static Boolean isPosInDisp(int [][] mov, int x, int y) {
		for (int [] pos : mov) {
			if (pos[0] == x && pos[1] == y) {
				return true;
			}
		}
		return false;
	}
	
	//retorna se o rei da cor (1 ou -1) ta em cheque
	public static Boolean verificaCheck(int cor) {
		if (cor == 1) {
			return Tabuleiro.isCheck(PecaCor.Branco);
		}
		return Tabuleiro.isCheck(PecaCor.Preto);
	}
}
