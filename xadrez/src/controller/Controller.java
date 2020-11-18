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
			
			int [][] mov;
			int xPeca, yPeca, xDest, yDest, iPeca;
			
			do {
				//view
				System.out.printf("Escolha a peca a movimentar: (x y)\n");
				xPeca = s.nextInt();
				yPeca = s.nextInt();
	
			}while(Model.isOutOfBounds(xPeca, yPeca)); //certifica que o ponto passado é valido 
			
			do {
				mov = Model.movDisp(xPeca,yPeca);
			}while (mov == null); //certifica que escolheu uma peca valida (ainda nao considera a cor)
			
			//view
			System.out.printf("Movimentos disponiveis:\n");
			if (mov.length == 0) { //vazio
				System.out.printf("Nenhum movimento disponivel!\n");
				//mudar controle (voltar na funcao)
			}
			else {
				for (int [] pos : mov) {
					System.out.printf("(%d, %d)\n", pos[0], pos[1]);
				}
			}
			
			//view
			do {
				System.out.printf("Escolha pra qual casa movimentar: (x y)\n");
				xDest = s.nextInt();
				yDest = s.nextInt();
			}while(Model.isOutOfBounds(xDest, yDest) || !inDisp(mov, xDest, yDest)); //certifica ponto valido e pertencente aos mov disp
			
			
			iPeca = Model.movRealiza(xPeca,yPeca, xDest, yDest);
			//retorna iPeca pra view
		}
	}
	
	public static Boolean inDisp(int [][] mov, int x, int y) {
		for (int [] pos : mov) {
			if (pos[0] == x && pos[1] == y) {
				return true;
			}
		}
		
		return false;
	}
}
