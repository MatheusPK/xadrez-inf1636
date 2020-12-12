package view;
import java.awt.*;
import javax.swing.*;

public class MainView extends JFrame{

    public MainView(TabuleiroView tv, int DEFAULT_HEIGHT, int DEFAULT_WIDTH) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize=tk.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
        setBounds(screenWidth/2 - DEFAULT_WIDTH/2, screenHeight/2 - DEFAULT_HEIGHT/2, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(tv);
    }
    
    
}