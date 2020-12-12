package view;

public class ViewFacade {
	
	public static MainView mainView;
	public static TabuleiroView tabView;
	
	static public void startView() {
		int DEFAULT_WIDTH = 800;
	    int DEFAULT_HEIGHT = 800;
		tabView = new TabuleiroView(DEFAULT_HEIGHT, DEFAULT_WIDTH);
		mainView = new MainView(tabView, DEFAULT_HEIGHT, DEFAULT_WIDTH);
		mainView.setTitle("Xadrez - INF1636");
		mainView.setVisible(true);
	}
	
	static public void popUpPromo() {
        tabView.popUpPromo();     
    }
}
