package model;

public class Tabuleiro {
	private static Peca[][] gameMatrix = new Peca[8][8];

	public static Peca[][] getGameMatrix() {
		return gameMatrix;
	}
	
	public static void printaTabuleiro() {
		Peca[][] tab = Tabuleiro.getGameMatrix();
		for(int i = 0; i < 8; i ++) {
			for(int j = 0; j < 8; j ++) {
				System.out.println("j: " + i + " i: " + j + " " + tab[j][i]);
			}
		}
	}
	
	public static Boolean isOutOfBounds(int x, int y) {
		return !(x >= 0 && x <= 7 && y >= 0 && y <= 7);
	}
	
	public static Peca getPecaIn(int x, int y) {
		return gameMatrix[x][y];
	}
	
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
	
	
	
	
}