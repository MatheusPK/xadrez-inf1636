package controller;

import model.*;
import view.*;
import view.Observable;
import view.Observer;

import java.io.FileWriter;
import java.util.*;

import javax.swing.JFileChooser;



public class ControllerFacade implements Observable{
	
	
	static private ControllerFacade c = null;

	private int rodada = 0;
	private Boolean isPecaClicked = false;
	private int isXeque = 0; //1 --> branco/ -1 --> preto
	private int isXequeMate = 0; //1 --> branco/ -1 --> preto
	
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
		Object dados[] = new Object[3];
		dados[0] = codeTab;
		dados[1] = movDisp;
		dados[2] = isXequeMate;
		return dados;
	}
	
	private void atualizaView() {
		ModelFacade.codificaTabuleiro(codeTab);
		//ModelFacade.desenhaTabuleiro();
		movDisp = null;
		
		for(Observer o: observerList) {
			o.notify(this);
		}
	}
	
	private void proxRodada() {
		
		rodada++;
		atualizaView();
		
		isXeque = 0;
		if (ModelFacade.verificaXeque(defineVez())) {
			isXeque = defineVez();
		}
		
		isXequeMate = 0;
		if (isXeque != 0) {
			if (ModelFacade.verificaXequeMate(defineVez())) {
				isXequeMate = defineVez();
			}
		}
		
		if (isXequeMate != 0) {
			for(Observer o: observerList)
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
		xPeca = 7 - xPeca;
		
		
		if (isPecaClicked) {
			selecionaMov(xPeca, yPeca);
		}
		verificaMovDisp(xPeca, yPeca);
	}
	
	private void verificaMovDisp(int xPeca, int yPeca) {
		
		if (ModelFacade.isOutOfBounds(xPeca, yPeca)){
			//System.out.println("Clique inválido");
			return;
		}
		
		if (isXeque == defineVez()){
			//System.out.println("XEQUE - controller");
			movDisp = ModelFacade.movDispXeque(xPeca, yPeca, defineVez());
		}
		else {
			movDisp = ModelFacade.movDisp(xPeca,yPeca, defineVez());
		}
		
		
		movDisp = ModelFacade.verificaRoqueCurto(xPeca, yPeca, defineVez(), movDisp);
		movDisp = ModelFacade.verificaRoqueLongo(xPeca, yPeca, defineVez(), movDisp);
	    
	    
	    if (movDisp == null){
	    	atualizaView();
	    	return;
		} 
	    
	    isPecaClicked = true;
		
		for(Observer o: observerList)
			o.notify(this);
	}
	
	private void selecionaMov(int xPeca, int yPeca)
	{
	
		int iPeca;
		
		if (movDisp == null) {
			return;
		}
		if (ModelFacade.isOutOfBounds(xPeca, yPeca) || !ModelFacade.isPosInMov(movDisp, xPeca, yPeca)){
			//System.out.printf("Movimento Inválido!\n");
			return;
		} 
				
		iPeca = ModelFacade.movRealiza(xPeca, yPeca, defineVez());//retorna iPeca pra view
		
		isPecaClicked = false;
	    
	    if(ModelFacade.verificaPromocao(xPeca, yPeca)) {
	    	atualizaView();
            ViewFacade.popUpPromo();
            return;
        }
	    proxRodada();
	}
	
	public void selecionaPromocao(int iPeca) {
		ModelFacade.realizaPromocao(iPeca);
		proxRodada();
	}
	
	public void salvaJogo() {
        ModelFacade.codificaTabuleiro(codeTab);
        String s = "";
        s = s.concat(Integer.toString(defineVez()));
        for(int i = 0; i < 8; i++) {
            for(int j = 0;j < 8; j++) {
                s = s.concat(" " + Integer.toString(codeTab[i][j]));
            }
        }
        
        JFileChooser chooser  = new JFileChooser();
        int retval = chooser.showSaveDialog(null);
        try{
            FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
            fw.write(s);
            fw.flush();
            fw.close();
        } catch(Exception IOException) {} finally {}
        System.out.println(s);
    }
	
}
