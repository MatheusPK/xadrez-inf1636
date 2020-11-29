package view;

public class View {
	
	public static MainView mainView;
	public static TabuleiroView tabView;
	
	static public void startView(){
		mainView = new MainView(tabView);
		mainView.setTitle("Xadrez - INF1636");
		mainView.setVisible(true);
	}
	
    static public void atualizaMovDisp() {
		tabView.atualizaMovDisp();
	}
	
}
