import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class Rects extends JFrame {
	
	
	// LOCALIZAÇAO DOS RECTS DO JOGO
	protected Rectangle2D razul  = new Rectangle2D.Double(400, 180, 200, 100);
	protected Rectangle2D rverm = new Rectangle2D.Double(400, 360, 200, 100);
	protected Rectangle2D rverde = new Rectangle2D.Double(200, 180, 200, 100);
	protected Rectangle2D ramara = new Rectangle2D.Double(200, 360, 200, 100);
	
	
	String pontuacao; // String que vai salvar a pontuacao
	String pontuacao2;// String que vai salvar a pontuacao
	Som som = new Som(); // cria objeto SOM
	int V=0,v=0,A=0,a=0; // Variaveis de controle para o modo Fanstasma.

	// Pinta os quadrado com as cores primarias; Renderiza os Nomes,quando houver o click, pontuacao , tempo e round
	public void paint(Graphics2D g,String nome,String nome1,String click,int po,int po2,long tempo,int Prodada, int Prodada2) throws InterruptedException 
    {  
		//Antialiasing
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		//-----------VERDE-----------------------
        g.setColor(new Color(0,205,102));  
        g.fill(rverde); 
		
        //-----------AMARELO----------------------
        g.setColor(new Color(255,246,143));  
        g.fill(ramara);
       
        //-----------VERMELHO---------------------
        g.setColor(new Color(238,99,99));  
        g.fill(rverm);
        //-------------AZUL----------------------

        g.setColor(new Color(202,225,255));  
        g.fill(razul);
        //--------------------------------------
        
     
        //-------------------------------------------------
        
        String pont ="Pontuação: " + Integer.toString(po); // Transforma o inteiro Pontuacao em String
        String pont2 ="Pontuação: " +  Integer.toString(po2);// Transforma o inteiro Pontuacao em String
        String round= "Round: "+ Integer.toString(Prodada);
        String round2= "Round: "+ Integer.toString(Prodada2);
        
        
		Font font = new Font ("arial",Font.ROMAN_BASELINE,20);// Cria a FONTE
		g.setFont(font); // carrega a fonte no graphics
		
		
		if(janela.player == janela.Pllayer.player1){ // caso jogador 1 esteja jogando
			g.setColor(Color.ORANGE); // carrega a cor laranja
			g.drawString(nome, 10,20);// Renderiza o nome do player 1 na tela
			}else{
				g.setColor(Color.white); // Caso não esteja jogando e nome fica com cor Branca
				g.drawString(nome, 10,20);// Renderiza o nome do player1 na tela
			}
		if(janela.player == janela.Pllayer.player2){ // Mesma coisa, so que para o player 2
				g.setColor(Color.ORANGE);
				g.drawString(nome1, 600,20);
			}else{
				g.setColor(Color.white);
				g.drawString(nome1, 600,20);
			}
		
		//-------Click--------------
		g.setColor(Color.CYAN); // carrega a cor
		g.drawString(click, 350, 100); // renderiza Click
		
		//-------Pontuacao e Round--------------
		Font font1 = new Font ("arial",Font.ROMAN_BASELINE,10); // cria uma nova fonte
		g.setFont(font1); // carrega a fonte no graphics
		g.setColor(Color.white); // carrega a cor Branca
		g.drawString(pont, 10,40); // Renderiza a pontuacao 
		g.drawString(pont2, 600,40); // Renderiza a pontuacao 
		g.drawString(round, 10,60); // Renderiza o round
		g.drawString(round2, 600,60); // Renderiza o round 
		//-----------------tempo--------
		if(janela.pc == janela.PC.livre){ // Caso pc não esteja jogando
			String Tempo ="Tempo: " + tempo; // transforma long em string
			g.setFont(font); // carrega a fonte
			g.setColor(Color.white); // carrega a cor
			g.drawString(Tempo, 330,50); // Renderiza TEMPO
			
		}
		
        g.dispose();// limpa
    }  
	public void piscar(int n,Graphics2D g) throws InterruptedException, LineUnavailableException, UnsupportedAudioFileException, IOException{ // Metodo piscar , tem como parametro o numero da cor , grafico2D
		// carrega o antialiasing
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// -----------VERIFICA AS CORES-------------
		if(n==1){ 
				g.setColor(new Color(238,59,59));// carrega a cor
	        	g.fill(rverm);
	        	som.carregar("music/verm.wav");
	        	som.tocar();
			}
		
		if(n==2){  
			// Verifica se é modo fantasma, para contabilizar as ocorrencias.
			if((janela.modo==janela.MODO.Fantasma)){
				// Verifica qual jogador esta jogando, para incrementar as variaveis certas 
				if(janela.player == janela.Pllayer.player1){
					A++;
					if(A % 2 == 0){ // caso seja par não pisca o rect,apenas toca o som
						som.carregar("music/amara.wav"); // Carrega o som
						som.tocar(); // Toca o som
					}else{
						g.setColor(new Color(238,238,0)); 
						g.fill(ramara);
						som.carregar("music/amara.wav");
						som.tocar();
					}
				}else if(janela.player == janela.Pllayer.player2){ // mesma coisa
					V++;
					if(V % 2 == 0){
						som.carregar("music/amara.wav");
						som.tocar();
						}else{
							g.setColor(new Color(238,238,0)); 
							g.fill(ramara);
							som.carregar("music/amara.wav");
							som.tocar();
						}
				}
			}else{// caso não esteja no modo fantasma, carrega e piscar o Rect , Toca o som
				g.setColor(new Color(238,238,0)); 
				g.fill(ramara);
				som.carregar("music/amara.wav");
				som.tocar();
    	}
		}

		if(n==3){ // mesma coisa , como é um valor impar não participa do modo fantasma
				g.setColor(new Color(46,139,87));  
				g.fill(rverde);
				som.carregar("music/verde.wav");
				som.tocar();
			}	
		
		if(n==4){ // mesma coisa
			if((janela.modo==janela.MODO.Fantasma)){
				if(janela.player == janela.Pllayer.player1){
					a++;
					if(a % 2 == 0){
						som.carregar("music/azul.wav");
						som.tocar();
					}else{
						g.setColor(new Color(72,118,255)); 
						g.fill(razul);
						som.carregar("music/azul.wav");
				    	som.tocar();
					}
				}else if(janela.player == janela.Pllayer.player2){
					v++;
					if(v % 2 == 0){
						som.carregar("music/azul.wav");
						som.tocar();
						}else{
							g.setColor(new Color(72,118,255)); 
							g.fill(razul);
							som.carregar("music/azul.wav");
					    	som.tocar();
						}
				}
	}else{
		g.setColor(new Color(72,118,255)); 
		g.fill(razul);
		som.carregar("music/azul.wav");
    	som.tocar();
    	}
	}
}
	public void rectrestart(){ // reinicia as variaveis utilizada
		V=0;v=0;A=0;a=0;
	}
}
