import model.*;

public class Main {
	public static void main(String[] args) {
//		System.out.println("sem factory");
//		Tabuleiro.printaTabuleiro();
//		System.out.println("com factory");
//		Peca.PecaFactory();
//		Tabuleiro.printaTabuleiro();
		
		Peca.PecaFactory();
		
		Peca p = Tabuleiro.getPecaIn(0, 0);
		int [][] mov = p.movimentosDisponiveis(0, 0);
		for (int [] pos : mov) {
			System.out.printf("(%d, %d)\n", pos[0], pos[1]);
		}
		
		
		
	}
}
