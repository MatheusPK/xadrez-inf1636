package view;
import java.awt.*;
import javax.swing.*;

public class MainView extends JFrame{
    public final int DEFAULT_WIDTH = 800;
    public final int DEFAULT_HEIGHT = 800;
    

    public MainView(TabuleiroView tv) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
        setBounds(screenWidth/2 - DEFAULT_WIDTH/2, screenHeight/2 - DEFAULT_HEIGHT/2, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        tv = new TabuleiroView(DEFAULT_HEIGHT, DEFAULT_WIDTH);
        getContentPane().add(tv);
    }
}