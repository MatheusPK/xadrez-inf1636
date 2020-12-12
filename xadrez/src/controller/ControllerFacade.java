package controller;

import model.*;
import view.*;
import view.Observable;
import view.Observer;

import java.util.*;


public class ControllerFacade implements Observable{
	
	
	static private ControllerFacade c = null;

	private int rodada = 0;
	private Boolean isPecaClicked = false;
	
	private int [][] codeTab = new int[8][8];
	private int[][] movDisp;
	
	private List<Observer> observerList=new ArrayList<Observer>();
	
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
		
		if (!isPecaClicked) {
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
	    
	    isPecaClicked = true;
		
		for(Observer o: observerList)
			o.notify(this);
	}
	
	public void selecionaMov(int xPeca, int yPeca)
	{
	
		int iPeca;
			
		if (ModelFacade.isOutOfBounds(xPeca, yPeca) || !ModelFacade.isPosInMov(movDisp, xPeca, yPeca)){
			System.out.printf("Movimento Inválido!\n");
			return;
		} 
				
		iPeca = ModelFacade.movRealiza(xPeca, yPeca);//retorna iPeca pra view
		
		isPecaClicked = false;
	    
	    if (ModelFacade.verificaCheck(defineVez()*-1)) {
			System.out.println("REI EM CHEQUE");
		}
	    
	    if(ModelFacade.verificaPromocao(xPeca, yPeca)) {
	    	ModelFacade.codificaTabuleiro(codeTab);
			ModelFacade.desenhaTabuleiro();
			movDisp = null;
			for(Observer o: observerList) {
				o.notify(this);
			}
            ViewFacade.popUpPromo();
            return;
        }
	    proxRodada();
	}
	
	public void selecionaPromocao(int iPeca) {
		ModelFacade.realizaPromocao(iPeca);
		proxRodada();
	}
	
}
