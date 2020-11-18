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



public class TabuleiroView extends JPanel implements MouseListener {
	
	Tabuleiro t = new Tabuleiro();
	int nImagens = 12; 
	double xIni = 0, yIni = 0, xOffSet, yOffSet, imgHeight, imgWidth;
	Image img;
	Map<PecaTipo, Image> imgPecas = new HashMap<PecaTipo, Image>();
	
	private PecaTipo [] decodeArrayBranco =  {PecaTipo.torreB, PecaTipo.cavaloB, PecaTipo.bispoB, PecaTipo.reiB, PecaTipo.rainhaB, PecaTipo.peaoB};
	private PecaTipo [] decodeArrayPreto =  {PecaTipo.torreP, PecaTipo.cavaloP, PecaTipo.bispoP, PecaTipo.reiP, PecaTipo.rainhaP, PecaTipo.peaoP};


	int height, width;
	
	
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
				rt=new Rectangle2D.Double(xIni + (j*xOffSet), yIni + (yOffSet*i), xOffSet, yOffSet);
				g2d.fill(rt);
				
				Image img = decodePeca(i, j);
				
				if(img != null) {
					g2d.drawImage(img, (int) (xIni + (j*xOffSet) + (xOffSet/2 - imgHeight/2) ), (int) (yIni + (yOffSet*i) + (yOffSet/2 - imgWidth/2)), (int) imgWidth, (int) imgHeight, this);
				}
				System.out.println("x : " + xIni + (j*xOffSet) + " y: " + this.yIni + (yOffSet*i));
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
		int cod = Controller.codeTab[i][j];
		if (cod == 0) {
			return null;
		}
		if (cod > 0) {
			return imgPecas.get(decodeArrayBranco[cod]);
		}
		cod *= -1;
		return imgPecas.get(decodeArrayPreto[cod]);
	}
	

	public void mouseEntered(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("oi");
		
	}
}