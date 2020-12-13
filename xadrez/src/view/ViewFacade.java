package view;

public class ViewFacade {
	
	public static MainView mainView;
	public static TabuleiroView tabView;
	
	static public void startView() {
		int DEFAULT_WIDTH = 800;
	    int DEFAULT_HEIGHT = 800;
		tabView = new TabuleiroView(DEFAULT_HEIGHT - 32, DEFAULT_WIDTH - 15);
		mainView = new MainView(tabView, DEFAULT_HEIGHT, DEFAULT_WIDTH);
		mainView.setTitle("Xadrez - INF1636");
		mainView.setVisible(true);
	}
	
	static public void popUpPromo() {
        tabView.popUpPromo();     
    }
	
	static public void tvAddObserver(Observer o) {
		tabView.addObserver(o);
	}
}
