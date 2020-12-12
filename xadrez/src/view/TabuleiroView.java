package view;

import model.*; //mudar
import controller.*;//mudar

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.*;



public class TabuleiroView extends JPanel implements MouseListener, Observer, ActionListener{
	
	//Tabuleiro t = new Tabuleiro();
	int nImagens = 12; 
	double xIni = 0, yIni = 0, xOffSet, yOffSet, imgHeight, imgWidth;
	private int[][] codeTab;
	private int[][] movDisp;
	
	Observable obs;
	Image img;
	Map<PecaTipo, Image> imgPecas = new HashMap<PecaTipo, Image>();
	
	private PecaTipo [] decodeArrayBranco =  {PecaTipo.torreB, PecaTipo.cavaloB, PecaTipo.bispoB, PecaTipo.reiB, PecaTipo.rainhaB, PecaTipo.peaoB};
	private PecaTipo [] decodeArrayPreto =  {PecaTipo.torreP, PecaTipo.cavaloP, PecaTipo.bispoP, PecaTipo.reiP, PecaTipo.rainhaP, PecaTipo.peaoP};


	int height, width;
	private Boolean popUpActive = false;
	
	
	public TabuleiroView(int height, int width) {
		this.setSize(width, height);
		this.height = height;
		this.width = width;
		this.xOffSet = this.width/8;
		this.yOffSet = this.height/8;
		this.imgHeight = yOffSet - 10;
		this.imgWidth = xOffSet - 10;
		
		Peca.PecaFactory();
		loadImages();
		addMouseListener(this);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		Rectangle2D rt;
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++) {

				if(((i + j) % 2) != 0)
					g2d.setColor(Color.black);
				else 
					g2d.setColor(Color.white);
				
				if(movDisp != null) {
					for (int [] pos : movDisp) {
						if(pos[0] == i && pos[1] == j)
							g2d.setColor(Color.pink);
					}
				}
	
				rt=new Rectangle2D.Double(xIni + (i*xOffSet), yIni + (yOffSet*(7-j)), xOffSet, yOffSet);
				g2d.fill(rt);
				
				Image img = decodePeca(i, j);
				
				if(img != null) {
					g2d.drawImage(img, (int) (xIni + (i*xOffSet) + (xOffSet/2 - imgHeight/2) ),
							(int) (yIni + (yOffSet*(7-j)) + (yOffSet/2 - imgWidth/2)), (int) imgWidth, (int) imgHeight, this);
				}
				//System.out.println("x : " + xIni + (j*xOffSet) + " y: " + this.yIni + (yOffSet*i));
			}
		}
		
		
		
	}
	
	private void loadImages() {
		
		for(PecaTipo tipoPeca : PecaTipo.values()) {
			try {
				 Image img = ImageIO.read(new File("src/view/assets/Pecas_2/" + tipoPeca.name() + ".png"));
				 img.getScaledInstance((int)xOffSet, (int)yOffSet, Image.SCALE_DEFAULT);
				 imgPecas.put(tipoPeca, img);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Image decodePeca(int i, int j) {
		//int cod = codeTab[i][j];
		int cod = codeTab[i][j];
		
		//System.out.println(cod);
		if (cod == 0) {
			return null;
		}
		
		if (cod > 0) {
			return imgPecas.get(decodeArrayBranco[cod-1]);
		}
		cod *= -1;
		return imgPecas.get(decodeArrayPreto[cod-1]);
	}
	
	
	public void notify(Observable o) {
		obs = o;
		Object[] dados = (Object []) obs.get();
		codeTab = (int [][]) dados[0];
		movDisp = (int [][]) dados[1];
		
		repaint();
		
	}
	

	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (popUpActive)
			return;
	
		int x = e.getX();
		int y = e.getY();
		
		ControllerFacade.getController().verificaClick(x, y, xOffSet, yOffSet);
	}
	
	public void popUpPromo() {
        
        JFrame frame = new JFrame("Promoção Peão!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Toolkit toolkit = frame.getToolkit();
        JPopupMenu pm = new JPopupMenu();
        
        JMenuItem rainhaPromo = new JMenuItem("rainha");
        JMenuItem bispoPromo = new JMenuItem("bispo");
        JMenuItem torrePromo = new JMenuItem("torre");
        JMenuItem cavaloPromo = new JMenuItem("cavalo");
        
        rainhaPromo.setName("5");
        bispoPromo.setName("3");
        torrePromo.setName("1");
        cavaloPromo.setName("2");
        
        rainhaPromo.addActionListener(this);
        bispoPromo.addActionListener(this);
        torrePromo.addActionListener(this);
        cavaloPromo.addActionListener(this);
 
        
        //System.out.println(rainhaPromo.getName());

        pm.add(rainhaPromo);
        pm.add(bispoPromo);
        pm.add(torrePromo);
        pm.add(cavaloPromo);
        pm.setVisible(true);
        
        frame.getContentPane().add(pm);
        frame.setSize(250, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        popUpActive = true;
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JMenuItem aux = (JMenuItem) e.getSource();
		//System.out.println(aux.getName());
		int iPeca = Integer.parseInt(aux.getName());
		
		
	}

}