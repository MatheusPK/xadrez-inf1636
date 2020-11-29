package controller;

import model.*;
import view.*;
import view.Observable;
import view.Observer;

import java.util.*;


public class Controller implements Observable{
	
	static private Scanner s = new Scanner(System.in);
	static private int rodada = 0;
	static public int [][] codeTab = new int[8][8]; //mudar pra private?
	static private int[][] movDisp;
	static private List<Observer> observerList=new ArrayList<Observer>();
	
	
	public static void main(String[] args) {

		
		ModelFacade.startGame();
		
//		MainView v = new MainView();
//        v.setTitle("Xadrez - INF1636");
//        v.setVisible(true);
		View.startView();
		addObserver(View.tabView);
		
		while (true) {
			rodada++;
			realizaRodada();
		}
	}
	
	private static void realizaRodada() {
		
		ModelFacade.codificaTabuleiro(codeTab);
		//view (passa codeTab)
		ModelFacade.desenhaTabuleiro();
		
		movDisp = null;
		
		for(Observer o: observerList)
			o.notify(getGambiarra());
		
		//View.atualizaMovDisp();
		
		int xPeca, yPeca, xDest, yDest, iPeca;
		
		do {
			do {
				//view
				System.out.printf("Escolha a peca a movimentar: (x y)\n");
				xPeca = s.nextInt();
				yPeca = s.nextInt();
	
			}while(ModelFacade.isOutOfBounds(xPeca, yPeca)); //certifica que o ponto passado � valido 
		
			movDisp = ModelFacade.movDisp(xPeca,yPeca, defineVez());
			
		}while (movDisp == null); //certifica que escolheu uma peca valida (considerando a cor e a vez)
		
		for(Observer o: observerList)
			o.notify(getGambiarra());
		
		//view
		System.out.printf("Movimentos disponiveis:\n");
		if (movDisp.length == 0) { //vazio
			System.out.printf("Nenhum movimento disponivel!\n");
			//mudar controle (voltar na funcao)
			realizaRodada();
			return;
		}
		else {
			for (int [] pos : movDisp) {
				System.out.printf("(%d, %d)\n", pos[0], pos[1]);
			}
		}
		
		//view
		do {
			System.out.printf("Escolha pra qual casa movimentar: (x y)\n");
			xDest = s.nextInt();
			yDest = s.nextInt();
		}while(ModelFacade.isOutOfBounds(xDest, yDest) || !inDisp(movDisp, xDest, yDest)); //certifica ponto valido e pertencente aos mov disp
		
		
		iPeca = ModelFacade.movRealiza(xPeca,yPeca, xDest, yDest);
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

	public void addObserver(Observer o) {
		observerList.add(o);
	}
	
	public void removeObserver(Observer o) {
		observerList.remove(o);
	}
	
	public static Object getGambiarra() {
		Object dados[] = new Object[2];
		dados[0] = codeTab;
		dados[1] = movDisp;
		return dados;
	}
	
	public Object get() {
		return null;
	}
	
}
