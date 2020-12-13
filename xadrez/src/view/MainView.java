package view;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainView extends JFrame{
	
	private JPanel menuInicial;
	private JPanel tv;

    public MainView(TabuleiroView tv, int DEFAULT_HEIGHT, int DEFAULT_WIDTH) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize=tk.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        setBounds(screenWidth/2 - DEFAULT_WIDTH/2, screenHeight/2 - DEFAULT_HEIGHT/2, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel f=new JPanel();  
        f.setSize(screenSize);
        JButton novoJogo = new JButton("Novo Jogo");
        JButton carregarJogo = new JButton("Carregar Jogo"); 
        
        novoJogo.setBounds(325, 340, 150, 30);
        f.add(novoJogo);  
        
        carregarJogo.setBounds(325,400,150,30);  
        f.add(carregarJogo);
        
        novoJogo.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
                getContentPane().remove(f);
                repaint(); //provavelmente causa o bug do double click
                getContentPane().add(tv); 
                tv.isFinished = false;
            }  
        });  
        
        carregarJogo.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){ 
            	getContentPane().remove(f);
            	getContentPane().add(tv); 
                tv.escolheArquivo();
                tv.isFinished = false;
            }  
        }); 
        f.setSize(400,400);  
        f.setLayout(null);
        f.setVisible(true);
        add(f);
        
        menuInicial = f;
        this.tv = tv;
        //getContentPane().add(f);
        //getContentPane().add(tv);
    }
    
    public void voltaMenuInicial() {
    	getContentPane().remove(tv);
    	getContentPane().add(menuInicial); 
    	repaint();
    }
    
    
    
}