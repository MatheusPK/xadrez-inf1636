package model;

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
	
	//assume pontos validos
	public static int movRealiza(int toX, int toY) {
		if (pecaClicked == null){
			System.out.println("pecaClicked (temporario) é nulo");
			return codificaPeca(null);
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
	
	//retorna se o rei da cor (1 ou -1) ta em cheque
	public static Boolean verificaCheck(int cor) {
		if (cor == 1) {
			return Tabuleiro.isCheck(PecaCor.Branco);
		}
		return Tabuleiro.isCheck(PecaCor.Preto);
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
}
