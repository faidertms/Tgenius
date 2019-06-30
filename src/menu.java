import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;


public class menu {		
	// LOCALIZAÇAO DOS RECTS MENU e PARA MENU RESTART (Usado apenas para localizacao)
	protected Rectangle2D Classic = new Rectangle2D.Double(300, 150, 150, 50);
	protected Rectangle2D Invert = new Rectangle2D.Double(300, 210, 150, 50);
	protected Rectangle2D fantasma = new Rectangle2D.Double(300, 270, 150, 50);
	protected Rectangle2D crazy = new Rectangle2D.Double(300, 330, 150, 50);
	protected Rectangle2D sair = new Rectangle2D.Double(300, 390, 150, 50);
		
	// Renderiza o MENU
	public void render(Graphics g){
		Graphics2D g2 = (Graphics2D) g; // cria um graphics 2D
		
		g2.setColor(Color.white); // Cria os rects 3D com a COR BRANCA
		g2.draw3DRect(300, 150, 150, 50,false);
		g2.draw3DRect(300, 210, 150, 50,false);
		g2.draw3DRect(300, 270, 150, 50,false);
		g2.draw3DRect(300, 330, 150, 50,false);
		g2.draw3DRect(300, 390, 150, 50,false);
		
		
		Font font = new Font ("arial",Font.ROMAN_BASELINE,20); // Carrega a fonte
		g.setFont(font);// seleciona a fonte
		g.setColor(Color.white);// seleciona a cor
		g.drawString("TGENIUS™", 310,100); 
		// Renderiza os nomes dos modos
		g.drawString("Modo Classico", 310,180);
		g.drawString("Modo Espelhado", 302,240);
		g.drawString("Modo Fantasma", 305,300);
		g.drawString("Modo Loucura", 310,360);
		g.drawString("Quit", 353,420);
	}
	
	// Renderiza o MENU RESTART
	public void renderRestart(Graphics g){
		Graphics2D g2 = (Graphics2D) g;// cria um graphics 2D
		
		g2.setColor(Color.white); // Cria os rects 3D com a COR BRANCA
		g2.draw3DRect(300, 210, 150, 50,false);
		g2.draw3DRect(300, 270, 150, 50,false);
		
		Font font = new Font ("arial",Font.ROMAN_BASELINE,20); // Carrega a fonte
		g.setFont(font);// seleciona a fonte
		g.setColor(Color.white);// seleciona a cor
		g.drawString("TGENIUS™", 330,100);
		// Renderiza os nomes dos quadrado
		g.drawString("Reiniciar", 340,240);
		g.drawString("Quit", 360,300);
		
	}
	}
