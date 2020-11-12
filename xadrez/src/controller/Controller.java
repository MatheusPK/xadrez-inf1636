package controller;

import model.*;

public class Controller {
	public static void main(String[] args) {

		
//		Peca.PecaFactory();
//		
//		Tabuleiro.desenhaTabuleiro();
//		
//		Peca p = Tabuleiro.getPecaIn(1, 1);
//		int [][] mov = p.movimentosDisponiveis();
//		for (int [] pos : mov) {
//			System.out.printf("(%d, %d)\n", pos[0], pos[1]);
//		}
		
		Model.startGame();
		Model.desenhaTabuleiro();
		int [][] mov = Model.movDisp(1, 1);
		Model.movRealiza(1, 1, 1, 2);
		Model.desenhaTabuleiro();
		
	}
}
