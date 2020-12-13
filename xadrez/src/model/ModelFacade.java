package model;

import java.awt.Color;
import java.lang.Math.*;

public class ModelFacade {
	
	private static Peca pecaClicked;
	private static Peca peaoPromo;
	
	public static void startGame() {
		Peca.PecaFactory();
	}
	
	public static void desenhaTabuleiro() {
		Tabuleiro.desenhaTabuleiro();
	}
	
	//assume x y valido e (vez > 0 -> branco, vez < 0 -> preto)
	public static int [][] movDisp(int x, int y, int vez) {
		Peca p = Tabuleiro.getPecaIn(x, y);
		if (p instanceof Rei){
			return movDispXeque(x, y, vez);
		}
		
		if (p == null) {
			//System.out.println("Invalido: Nenhuma peca ai!\n");
			return null;
		}
		PecaCor cor = p.getCor();
		if (cor == PecaCor.Branco && vez <= 0) {
			//System.out.println("Invalido: Vez do Preto!\n");
			return null;
		}
		else if (cor == PecaCor.Preto && vez > 0) {
			//System.out.println("Invalido: Vez do Branco!\n");
			return null;
		}
		pecaClicked = p;
		return p.movimentosDisponiveis();
	}
	
	//assume x y valido e (vez > 0 -> branco, vez < 0 -> preto)
	public static int [][] movDispXeque(int x, int y, int vez) {
		Peca p = Tabuleiro.getPecaIn(x, y);
		if (p == null) {
			return null;
		}
		PecaCor cor = p.getCor();
		if (cor == PecaCor.Branco && vez <= 0) {
			return null;
		}
		else if (cor == PecaCor.Preto && vez > 0) {
			return null;
		}
		pecaClicked = p;
		int [][] movDisp = p.movimentosDisponiveis();
		int[][] movDispRemov = new int[movDisp.length][2];
		int index = 0;
		
		for (int [] pos: movDisp) {
			if (!simulateMov(pos, p, vez)) {
				movDispRemov[index] = pos.clone();
				index++;
			}
		}
		
		return reduzArray(movDispRemov, index);
	}
	
	private static Boolean simulateMov(int [] pos, Peca peca, int vez) {
		int xPeca = peca.x;
		int yPeca = peca.y;
		
		Peca pecaComida = peca.simulaMovimento(pos[0], pos[1]);
		Boolean xeque = verificaXeque(vez);
		peca.simulaMovimento(xPeca, yPeca);
		if (pecaComida != null)
			pecaComida.simulaMovimento(pecaComida.x, pecaComida.y);
	
		return xeque;
	}
	
	//funcao generica que cria um novo array do tamanho desejado e copia o conteudo de um antigo array
	public static int [][] reduzArray(int[][] oldArray, int qnt){
		int [][] newArray = new int[qnt][2];
		for (int i = 0; i < qnt; i++) {
			newArray[i] = oldArray[i].clone();
		}
		oldArray = null;
		return newArray;
	}
	
	//assume pontos validos
	public static int movRealiza(int toX, int toY, int vez) {
		if (pecaClicked == null){
			System.out.println("pecaClicked (temporario) é nulo");
			return codificaPeca(null);
		}
		
		Peca p = Tabuleiro.getPecaIn(toX, toY);
		if (pecaClicked != null && p != null)
		{
			if (pecaClicked.cor == p.cor) {
				if (Math.abs(pecaClicked.x - p.x) == 3) {
					realizaRoqueCurto(vez); 
				}
				else {
					realizaRoqueLongo(vez);
				}
				pecaClicked = null;
                return 0;
			}
		}
		
		Peca pecaComida = pecaClicked.realizaMovimento(toX, toY);
		pecaClicked = null;
		return codificaPeca(pecaComida);
	}

	
	public static Boolean isOutOfBounds(int x, int y) {
		return Tabuleiro.isOutOfBounds(x, y);
	}
	
	//transoforma o tipo da peca (classe e cor) em um inteiro correspondente
	private static int codificaPeca(Peca p) {
		int i;
		if (p == null) {
			return 0;
		}
		else if (p instanceof Torre) {
			i = 1;
		}
		else if (p instanceof Cavalo) {
			i = 2;
		}
		else if (p instanceof Bispo) {
			i = 3;
		}
		else if (p instanceof Rei) {
			i = 4;
		}
		else if (p instanceof Rainha) {
			i = 5;
		}
		else if (p instanceof Peao) {
			i = 6;
		}
		else {
			return 0;
		}
		
		if (p.getCor() == PecaCor.Preto) {
			i *= -1;
		}
		
		return i;
	}
	
	//retorna tabuleiro codificado
	public static void codificaTabuleiro(int [][] codeTab) {
		Peca [][] tab = Tabuleiro.getGameMatrix();
		//int [][] codeTab = new int[8][8];
		
		for(int i = 0; i < 8; i ++) {
			for(int j = 0; j < 8; j ++) {
				codeTab[i][j] = codificaPeca(tab[i][j]);
			}
		}
		//return codeTab;
	}
	
	//retorna se posicao é um dos elementos do vetor de mov disp
	public static Boolean isPosInMov(int [][] mov, int x, int y) {
		for (int [] pos : mov) {
			if (pos[0] == x && pos[1] == y) {
				return true;
			}
		}
		return false;
	}
	
	//retorna se o rei da cor (1 ou -1) ta em xeque
	public static Boolean verificaXeque(int cor) {
		if (cor == 1) {
			return Tabuleiro.isXeque(PecaCor.Branco);
		}
		return Tabuleiro.isXeque(PecaCor.Preto);
	}
	
	//retorna se o rei da cor (1 ou -1) ta em xequeMate
		public static Boolean verificaXequeMate(int cor) {
			if (cor == 1) {
				return Tabuleiro.isXequeMate(PecaCor.Branco);
			}
			return Tabuleiro.isXequeMate(PecaCor.Preto);
		}
	
	//verifica se a peca a ser movida e peao e esta num local de promocao
	public static Boolean verificaPromocao(int pecaX, int pecaY) {
        Peca p = Tabuleiro.getPecaIn(pecaX, pecaY);
        if ((pecaY == 0 || pecaY == 7) && p instanceof Peao) {
        	peaoPromo = p;
        	return true;
        }
        return false;
    }
    
	//muda peca no tabuleiro de acordo com a peca escolhida no popup
    public static void realizaPromocao(int pecaNova) {
    	
        Peca[][] tab = Tabuleiro.getGameMatrix();
        
        if (peaoPromo == null) {
        	System.out.println("peaoPromo (temporario) é nulo");
			return;
        }
        
        PecaCor cor = peaoPromo.getCor();
        int pecaX = peaoPromo.x;
        int pecaY = peaoPromo.y;
       
        if (pecaNova == 1) {
            tab[pecaX][pecaY] = new Torre(cor, pecaX, pecaY);
        }
        else if (pecaNova == 2) {
            tab[pecaX][pecaY] = new Cavalo(cor, pecaX, pecaY);
        }
        else if (pecaNova == 3) {
            tab[pecaX][pecaY] = new Bispo(cor, pecaX, pecaY);
        }
        else if (pecaNova == 5) {
            tab[pecaX][pecaY] = new Rainha(cor, pecaX, pecaY);
        }
        
        peaoPromo = null;     
    }
    
    public static int [][] verificaRoqueCurto(int xPeca, int yPeca, int vez, int [][] movDisp) {
        
        if(movDisp == null)
            return movDisp;
    
        Peca p = Tabuleiro.getPecaIn(xPeca, yPeca);
        if (!(p instanceof Rei || p instanceof Torre)) 
            return movDisp;
        
         System.out.println("Roque Curto!");
        
        
        Peca[][] tab = Tabuleiro.getGameMatrix();
        int yRoque = (vez == 1) ? 0 : 7;
        
        Peca torrePerto = tab[0][yRoque];
        Peca rei = tab[3][yRoque];
        
        Boolean shouldMov = true;
        
        if (!(torrePerto instanceof Torre) || ((Torre) torrePerto).hasMoved)
            return movDisp;
        
        System.out.println("Roque Curto!");
        if (!(rei instanceof Rei) || ((Rei) rei).hasMoved) 
            return movDisp;
        
        System.out.println("Roque Curto!");
            
        //verificar se o rei nao esta em cheque
        for(int i = rei.x - 1; i > 0; i--) { 
            System.out.printf("teste roque curto x %d / y% d", i, yRoque);
            if(tab[i][yRoque] != null) { //verificar se o rei nao fica em cheque nas casas entre o rei e a torre
                 shouldMov = false;
            }
        }
        System.out.println("acabei o teste");
        int [][] newMovDisp = new int [movDisp.length + 1][2];
        int index = 0;
        
        if (shouldMov) {
     
            System.out.println("Roque Curto!");
            for (int [] pos: movDisp) {
                newMovDisp[index] = pos.clone();
                index++;
            }
            
            if (p instanceof Rei) {
                newMovDisp[index][0] = 0;
                newMovDisp[index][1] = yRoque;
            }
            else {
                newMovDisp[index][0] = 3;
                newMovDisp[index][1] = yRoque;
            }
        }
        
        movDisp = null;
        return newMovDisp;
    }
    
    public static void realizaRoqueCurto(int vez) {
         Peca[][] tab = Tabuleiro.getGameMatrix();
         int yRoque = (vez == 1) ? 0 : 7;
         
         Peca torrePerto = tab[0][yRoque];
         Peca rei = tab[3][yRoque];
         
         tab[0][yRoque] = null;
         tab[3][yRoque] = null;
        
        tab[2][yRoque] = torrePerto;
        tab[1][yRoque] = rei;
        
        torrePerto.atualizaPos(2, yRoque);
        rei.atualizaPos(1, yRoque);
    }
    
    public static int[][] verificaRoqueLongo(int xPeca, int yPeca, int vez, int [][] movDisp) {
        
        Peca p = Tabuleiro.getPecaIn(xPeca, yPeca);
    	if (!(p instanceof Rei || p instanceof Torre)) 
    		return movDisp;
    	
    	 //System.out.println("Roque Longo!");
    	
        Peca[][] tab = Tabuleiro.getGameMatrix();
        int yRoque = (vez == 1) ? 0 : 7;
        
        Peca torrePerto = tab[0][yRoque];
        Peca rei = tab[3][yRoque];
        
        Boolean shouldMov = true;
        
        if (!(torrePerto instanceof Torre && !((Torre) torrePerto).hasMoved) || !(rei instanceof Rei && !((Rei) rei).hasMoved))
        	return movDisp;
        	
        //System.out.println("Roque Longo!");
	    //verificar se o rei nao esta em cheque
        for(int i = rei.x - 1; i > 0; i--) { 
	        if(tab[i][yRoque] != null) { //verificar se o rei nao fica em cheque nas casas entre o rei e a torre
	             shouldMov = false;
	        }
	    }
        
        int [][] newMovDisp = new int [movDisp.length + 1][2];
    	int index = 0;
	    
	    if (shouldMov) {
	 
	    	//System.out.println("Roque Longo!");
	    	for (int [] pos: movDisp) {
	    		newMovDisp[index] = pos.clone();
	    		index++;
	    	}
	    	
	    	if (p instanceof Rei) {
	    		newMovDisp[index][0] = 0;
	    		newMovDisp[index][1] = yRoque;
	    	}
	    	else {
	    		newMovDisp[index][0] = 3;
	    		newMovDisp[index][1] = yRoque;
	    	}
	    }
	    
	    movDisp = null;
	    return newMovDisp;
    }
    
    public static void realizaRoqueLongo(int vez) {
    	Peca[][] tab = Tabuleiro.getGameMatrix();
    	int yRoque = (vez == 1) ? 0 : 7;
        
        Peca torreLonge = tab[0][yRoque];
        Peca rei = tab[3][yRoque];
        
        tab[3][yRoque] = null;
        tab[2][yRoque] = null;
        
        tab[3][yRoque] = torreLonge;
        tab[2][yRoque] = rei;
        
        torreLonge.atualizaPos(3, yRoque);
        rei.atualizaPos(2, yRoque);
    }
}
