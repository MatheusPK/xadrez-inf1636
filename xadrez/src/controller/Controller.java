package controller;

import model.*;
import java.util.Scanner;

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
		
		Scanner s = new Scanner(System.in);
		Model.startGame();
		
		
		while (true) {
			Model.desenhaTabuleiro();
			System.out.printf("Escolha a peca a movimentar: (x y)\n");
			int xPeca = s.nextInt();
			int yPeca = s.nextInt();
			
			int [][] mov = Model.movDisp(xPeca,yPeca);
			
			System.out.printf("Movimentos disponiveis:\n");
			for (int [] pos : mov) {
				System.out.printf("(%d, %d)\n", pos[0], pos[1]);
			}
			
			System.out.printf("Escolha pra qual casa movimentar: (x y)\n");
			int xDest = s.nextInt();
			int yDest = s.nextInt();
			
			
			Model.movRealiza(xPeca,yPeca, xDest, yDest);
		}
	}
}
