package model;

public class Tabuleiro {
	private static Peca[][] gameMatrix = new Peca[8][8];

	//retorna referencia para a matriz estatica
	public static Peca[][] getGameMatrix() {
		return gameMatrix;
	}
	
	//funcao para debugar matrix tabuleiro
	public static void printaTabuleiro() {
		Peca[][] tab = Tabuleiro.getGameMatrix();
		for(int i = 0; i < 8; i ++) {
			for(int j = 0; j < 8; j ++) {
				System.out.println("j: " + i + " i: " + j + " " + tab[j][i]);
			}
		}
	}
	
	//retorna se posicao ï¿½ fora do tabuleiro
	public static Boolean isOutOfBounds(int x, int y) {
		return !(x >= 0 && x <= 7 && y >= 0 && y <= 7);
	}
	
	//mesmo que gameMatrix[x][[y]
	public static Peca getPecaIn(int x, int y) {
		if (isOutOfBounds(x, y)) {
			System.out.println("Posicao Invalida!");
			return null;
		}
		return gameMatrix[x][y];
	}
	
	public static Boolean hasPeca(int x, int y) {
		Peca p = gameMatrix[x][y];
		return (p == null) ? false: true;
	}
	
	//funcao temporario para imprimir tabuleiro e pecas em interface caracter
	public static void desenhaTabuleiro() {
		for(int i = 7; i >= 0; i --) {
			System.out.printf("%d---", i);
			for(int j = 0; j < 8; j ++) {
				Peca p = gameMatrix[j][i];
				String c = "";
				if (p == null) {
					c = "x";
				}
				else if (p instanceof Torre) {
					c = "t";
				}
				else if (p instanceof Cavalo) {
					c = "c";
				}
				else if (p instanceof Bispo) {
					c = "b";
				}
				else if (p instanceof Rei) {
					c = "r";
				}
				else if (p instanceof Rainha) {
					c = "q";
				}
				else if (p instanceof Peao) {
					c = "p";
				}
				
				if (p != null && p.getCor() == PecaCor.Branco) {
					c = c.toUpperCase();
				}
				
				System.out.printf("%s ", c);
			}
			System.out.println();
		}
		
		System.out.printf("    ");
		for(int j = 0; j < 8; j ++) {
			System.out.printf("| ");
		}
		System.out.println();
		System.out.printf("    ");
		for(int j = 0; j < 8; j ++) {
			System.out.printf("%d ", j);
		}
		System.out.println();
	
	}
	
	public static Boolean isXeque(PecaCor cor) {
		
		Rei rei = null;
		Boolean check = false;
		
		for (Peca pecaArray[] : gameMatrix) {
			for (Peca peca : pecaArray) {
				if (peca != null && peca.cor == cor && peca instanceof Rei) {
					rei = (Rei) peca;
				}
			}
		}
		
		if (rei == null){
			System.out.println("Erro: Rei não encontrado!");
			return false;
		}
		
		//rei.clearImpossibleMovs();
	
		for (Peca pecaArray[] : gameMatrix) {
			for (Peca peca: pecaArray) {
				if (peca != null && peca.cor != cor) {
					int [][] movDisp = peca.movimentosDisponiveis();
//					if (ModelFacade.isPosInDisp(movDisp, rei.x, rei.y)) {
//						check = true;
//					}
					
					for (int [] pos : movDisp) {
						if (pos[0] == rei.x && pos[1] == rei.y) {
							check = true;
						}
						//rei.addImpossibleMov(pos[0], pos[1]);
					}
					
				}
			}
		}
		
//		if (rei.movimentosDisponiveis().length == 0) {
//			check = true;
//		}
		
		return check;
	}
	
	
}
