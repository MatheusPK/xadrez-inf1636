package controller;

import model.*;
import view.*;
import view.Observable;
import view.Observer;

import java.util.*;


public class ControllerFacade implements Observable{
	
	
	static private ControllerFacade c = null;
	private Scanner s = new Scanner(System.in);
	private int rodada = 0;
	private int [][] codeTab = new int[8][8];
	private int[][] movDisp;
	private List<Observer> observerList=new ArrayList<Observer>();
	private int clickedPecaX = -1;
	private int clickedPecaY = -1;
	
	
	
	private ControllerFacade() {
		ModelFacade.startGame();
		ViewFacade.startView();
		addObserver(ViewFacade.tabView);
	}
	
	public static ControllerFacade getController() {
		if(c == null) {
			c = new ControllerFacade();
		}
		return c;
	} 
	
	public void startController() {
		proxRodada();
	}

	public void addObserver(Observer o) {
		observerList.add(o);
	}
	
	public void removeObserver(Observer o) {
		observerList.remove(o);
	}
	
	public Object get() {
		Object dados[] = new Object[2];
		dados[0] = codeTab;
		dados[1] = movDisp;
		return dados;
	}
	
	private void proxRodada() {
		
		rodada++;
		ModelFacade.codificaTabuleiro(codeTab);
		ModelFacade.desenhaTabuleiro();
		movDisp = null;
		
		for(Observer o: observerList) {
			o.notify(this);
		}
	}
	
	//branco -> 1 preto -> -1
	private int defineVez() {
		int vez = 1;
		if (rodada % 2 == 0) {
			vez = -1;
		}
		return vez;
	}
	
	public void verificaClick(int x, int y, double xOffSet, double yOffSet) {
		
		int xPeca = (int) (x/xOffSet);
		int yPeca = (int) (y/yOffSet);
		
		yPeca = 7 - yPeca;
		
		if (clickedPecaX == -1 && clickedPecaY == -1) {
			verificaMovDisp(xPeca, yPeca);
		}
		else {
			selecionaMov(xPeca, yPeca);
		}
	}
	
	private void verificaMovDisp(int xPeca, int yPeca) {
		
		if (ModelFacade.isOutOfBounds(xPeca, yPeca)){
			System.out.println("Clique inválido");
			return;
		}
		
	    movDisp = ModelFacade.movDisp(xPeca,yPeca, defineVez());
	    
	    if (movDisp == null){
	    	System.out.println("Clique inválido");
	    	return;
		} 
	 
	    clickedPecaX = xPeca;
	    clickedPecaY = yPeca;
		
		for(Observer o: observerList)
			o.notify(this);
	}
	
	public void selecionaMov(int xPeca, int yPeca)
	{
	
		int iPeca;
			
		if (ModelFacade.isOutOfBounds(xPeca, yPeca) || !ModelFacade.isPosInDisp(movDisp, xPeca, yPeca)){
			System.out.printf("Movimento Inválido!\n");
			clickedPecaX = -1;
		    clickedPecaY = -1;
			return;
		} 
				
		iPeca = ModelFacade.movRealiza(clickedPecaX,clickedPecaY, xPeca, yPeca);//retorna iPeca pra view
		
		clickedPecaX = -1;
	    clickedPecaY = -1;
	    
	    if (ModelFacade.verificaCheck(defineVez()*-1)) {
			System.out.println("REI EM CHEQUE");
		}
	    proxRodada();
	}
	
}
