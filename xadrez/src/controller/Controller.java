package controller;

import model.*;
import java.util.Scanner;

public class Controller {
	
	static private Scanner s = new Scanner(System.in);
	static private int rodada = 0;
	static public int [][] codeTab = new int[8][8]; //mudar pra private?
	
	
	public static void main(String[] args) {

		
		Model.startGame();
		
		while (true) {
			rodada++;
			realizaRodada();
		}
	}
	
	private static void realizaRodada() {
		
		Model.codificaTabuleiro(codeTab);
		//view (passa codeTab)
		Model.desenhaTabuleiro();
		
		int [][] mov;
		int xPeca, yPeca, xDest, yDest, iPeca;
		
		do {
			do {
				//view
				System.out.printf("Escolha a peca a movimentar: (x y)\n");
				xPeca = s.nextInt();
				yPeca = s.nextInt();
	
			}while(Model.isOutOfBounds(xPeca, yPeca)); //certifica que o ponto passado é valido 
		
			mov = Model.movDisp(xPeca,yPeca, defineVez());
			
		}while (mov == null); //certifica que escolheu uma peca valida (considerando a cor e a vez)
		
		//view
		System.out.printf("Movimentos disponiveis:\n");
		if (mov.length == 0) { //vazio
			System.out.printf("Nenhum movimento disponivel!\n");
			//mudar controle (voltar na funcao)
			realizaRodada();
			return;
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
	
	private static int defineVez() {
		int vez = 1;
		if (rodada % 2 == 0) {
			vez = -1;
		}
		return vez;
	}
	
	private static Boolean inDisp(int [][] mov, int x, int y) {
		for (int [] pos : mov) {
			if (pos[0] == x && pos[1] == y) {
				return true;
			}
		}
		
		return false;
	}
}
