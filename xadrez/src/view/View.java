package view;

public class View {
	
	private static MainView mainView;
	private static TabuleiroView tabView;
	
	static public void startView(){
		mainView = new MainView(tabView);
		mainView.setTitle("Xadrez - INF1636");
		mainView.setVisible(true);
	}
	
    static public void atualizaMovDisp() {
		tabView.atualizaMovDisp();
	}
	
}
