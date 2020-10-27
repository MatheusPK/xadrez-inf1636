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
	
}
