import model.*;

public class Main {
	public static void main(String[] args) {
		System.out.println("sem factory");
		Tabuleiro.printaTabuleiro();
		System.out.println("com factory");
		Peca.PecaFactory();
		Tabuleiro.printaTabuleiro();
	}
}
