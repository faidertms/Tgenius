 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;  
import javax.swing.JOptionPane;

import java.awt.event.MouseListener;
import java.io.IOException;
      
@SuppressWarnings("serial")
public class janela extends JFrame implements ActionListener, MouseListener{
	
	// Objetos das classes
	private menu Menu;
	private Rects rects;
	private Tempo tempo;
	private sequencia Sequencia = new sequencia();
	private sequencia Sequencia2 = new sequencia();
	private Player player1 = new Player();
	private Player player2= new Player();;
	private String Click="";
	
	
	
	// variaveis de estado
	protected static STATE state = STATE.MENU;
	protected static PC pc = PC.jogando;
	protected static MODO modo = null;
	protected static Pllayer player = Pllayer.player1;;
	
	protected enum Pllayer{
		player1,
		player2
	};
	protected enum STATE{
		MENU,
		GAME,
		RESTART
	};
	protected enum PC{
		jogando,
		livre
	};
	protected enum MODO{
		Classic,
		Espelhado,
		Fantasma,
		Locura,
	};
	//--------------------
	
	
	
	
	private int n=0; // é a variavel que guarda o valor do quadrado clicado e compara
	private int a; // variavel que pecorre a sequencia para retorna o valor do quadrado e fazendo piscar
	private boolean click; // variavel de gatilho, quando o quadrado é clicado valor passa a ser true e ativa o metodo verificar
	private boolean conclusao = false; // variavel de gatilho , quando a jogada é completa  ele passa a ser true e ativa o metodo randomizar
	private int jogadaPC=0,jogadaPC2=0; // variaveis do PC, que guarda a rodada do Computador
	
	
	public janela(){ // construtor quando chamado cria uma janela jFrame
				
			    super("TGenius"); // nome da janela
	            this.setSize(800, 600); // tamanho da janela
	            this.setResizable(false);  // se pode ser aumentado
	            this.setLocationRelativeTo(null); // coloca a janela no centro
	            this.setIgnoreRepaint(true); // ignora o repaint
	            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // fechar a janela
	}

	public void rodar(){ //inicializa todos os metodos
		setVisible(true); // ativa a visibilidade do jogo
    	gameLoop(); // Ativa o loop do jogo
	}
	
	private void gameLoop() // game loop
	   {
		
		  boolean rodando = true; // variavel para ativar o jogo
		  // variaveis de controle de FPS
	      long lastime = System.nanoTime();
	      final double amountofthick = 60.0;
	      double ns = 1000000000 / amountofthick;
	      double delta = 0 ;
	      int frames = 0;
	      int updates = 0;
	      long timer = System.currentTimeMillis();
	      
	      //instanciar os objetos
	      Menu= new menu();
	      rects = new Rects();
	      tempo=new Tempo();
	      
	      try // aqui começa o jogo
	        {
	      buffer(); // chama o metodo buffer
	      
	      while (rodando) 
	      {
	    	  //------------FPS-----------------------
	    	 long now = System.nanoTime();
	         delta += (now- lastime)/ns;
	         lastime= now;
	         if(delta>=1){
	        	 updates++;
	        	 delta--;
	       }
	         //------------- Metodos do jogo-------------
	        if (state == STATE.GAME){ // apenas é chamado quando o jogo sai do menu
            randomizar();
            verificar();
            }
	        render();
	        piscar();
	        paintScreen();
	        //----------------------FPS---------------
	         frames++;
	         if (System.currentTimeMillis() - timer>1000){
	        	 timer +=1000;
	        	 System.out.println(updates + "ticks, Fps"+ frames);
	        	 updates=0;
	        	 frames=0;
	         }
	      }
	            
	      }
	      catch (Exception e)
	        {   
	    	// se der algo errado no loop do jogo, é encerrado e exibe o erro
	    	  	rodando = false; 
	            System.exit(0);
	            throw new RuntimeException("Exception during game loop", e);
	        }
	  

	   }
	public void randomizar() throws InterruptedException{ // metodo randomizar , gera a sequencia do Computador
		if(conclusao){ // variavel gatilho
			if(player==Pllayer.player1){ // caso o player 1 esteja jogando
				if(modo == MODO.Locura){ // modo  de jogo
					Sequencia= new sequencia();// Instancia novamente uma sequencia
					Sequencia.randomizarLouco(jogadaPC);// Coloca Diversos valores randomicos no fim da sequencia, o total de randomizaçao depende do round
					}else{// qualquer outro modo
						Sequencia.inserirNoFim(new No()); // Coloca um valor randomico no fim da sequencia
					}
		
					Thread.sleep(5); // coloca uns thread para dormir para controlar o incremento
					jogadaPC++; // Aumenta o numero de round cada vez que o metodo é ativo
					click = false; // click muda para false
		
				//Player 1
					player1.resetrodada(); // reseta a 0
					player1.addrodada();	// coloca 1 na jogada pra sinalizar que é a primeira jogada(não round)
					if(modo == MODO.Espelhado){// modo  de jogo
						player1.inversor(jogadaPC); // quando o jogo é espelhado, chama o metodo inversor do player para mudar o meio de pecorrer a sequencia
						}
				}
			
			if(player==Pllayer.player2){// caso Player 2
				if(modo == MODO.Locura){// modo  de jogo
					Sequencia2= new sequencia(); // Instancia novamente uma sequencia
					Sequencia2.randomizarLouco(jogadaPC2); // Coloca Diversos valores randomicos no fim da sequencia, o total de randomizaçao depende do round
					}else{ // qualquer outro modo
						Sequencia2.inserirNoFim(new No()); // Coloca um valor randomico no fim da sequencia
					}
		
					Thread.sleep(5); // coloca uns thread para dormir para controlar o incremento
					jogadaPC2++; // Aumenta o numero de round cada vez que o metodo é ativo
					click = false; // click muda para false
		
				
					player2.resetrodada(); // reseta a 0
					player2.addrodada();	// coloca 1 na jogada pra sinalizar que é a primeira jogada(não round)
					if(modo == MODO.Espelhado){// modo  de jogo
						player2.inversor(jogadaPC2); // quando o jogo é espelhado, chama o metodo inversor do player para mudar o meio de pecorrer a sequencia
						}
				}
			a=0; // reinicia a variavel utilizada para pecorrer a sequencia e retornar os valores fazendo as animaçoes piscar do jogo
			pc = PC.jogando; // PC começa a jogar, para piscar os rects
			tempo.setTempo2(); // ATUALIZAR O TEMPO
			tempo.calcularTempo(); // Calcular o tempo
			}
		  
			conclusao=false; // desativa o gatilho
			
		}

	public void verificar(){ // verifica
		// verifica se os jogadores passaram de 31 rodada
		if(jogadaPC==31 && jogadaPC2==31){
			JOptionPane.showMessageDialog(null," Empate ");
			click=false;
			state = STATE.RESTART; // ativa o menu de restart
		}
		
		// Verifica se o tempo passou de 5 segundo 
		if(tempo.Tempolimite()){
			JOptionPane.showMessageDialog(null,"Tempo Esgotado "+ player );
			click=false;
			state = STATE.RESTART; // ativa o menu de restart
		}
		
		// PLAYER 1
		if(player==Pllayer.player1){
			if(modo==MODO.Classic || modo==MODO.Locura || modo==MODO.Fantasma){ // verifica o modo de jogo
				if(click){ // quando click é ativo
					if(n==Sequencia.retornar(player1.getrodada())){ // verifica o valor armazenado na variavel que representa o quadrado e verifica na sequencia se acertou
						if(player1.getrodada()==jogadaPC){// caso acerte todos os valores da rodada
							conclusao=true; // ativa a randomização
							player=Pllayer.player2; // começa a jogada do player 2
						}
						player1.pontucao(); // incrementa a pontucao a cada acerto
						player1.addrodada();// incrementa a rodada a cada acerto (não é o round)
						click=false; // desativa o click
				
					}else{
						JOptionPane.showMessageDialog(null,player+ " Você errou !! "); // caso erre, chama o menu restart
						click=false;
						state = STATE.RESTART;
			}
		}
		}
		
		if(modo == MODO.Espelhado){// verifica o modo de jogo
			if(click){// quando click é ativo
				if(n==Sequencia.retornar(player1.getEspelhado())){// verifica o valor armazenado na variavel que representa o quadrado e verifica na sequencia se acertou, nesse caso ele vem decrescente
					// caso acerte todos os valores da rodada
					if(player1.getrodada()==player1.getEspelhado()){
						conclusao=true;// ativa a randomização
						player=Pllayer.player2;// começa a jogada do player 2
					}
					player1.pontucao();// incrementa a pontucao a cada acerto
					player1.addrodadaE();// decrementa a rodada a cada acerto (não é o round)
					click=false;// desativa o click
					
				}else{// caso erre, chama o menu restart
					JOptionPane.showMessageDialog(null,player+ " Você errou !!");
					click=false;
					state = STATE.RESTART;
				}
			}
		}
	}
		// PLAYER 2 MESMA COISA DO PLAYER 1 SO MUDA AS VARIAVEIS
		if(player==Pllayer.player2){
			if(modo==MODO.Classic || modo==MODO.Locura || modo==MODO.Fantasma){
				if(click){
					if(n==Sequencia2.retornar(player2.getrodada())){
						if(player2.getrodada()==jogadaPC2){
							conclusao=true;
							player=Pllayer.player1;
						}
						player2.pontucao();
						player2.addrodada();
						click=false;
				
						System.out.println("deu certo");
					}else{
						JOptionPane.showMessageDialog(null,player+ " Você errou !!");
						click=false;
						state = STATE.RESTART;
			}
		}
		}
		
		if(modo == MODO.Espelhado){
			if(click){
				if(n==Sequencia2.retornar(player2.getEspelhado())){
					
					if(player2.getrodada()==player2.getEspelhado()){
						conclusao=true;
						player=Pllayer.player1;
					}
					player2.pontucao();
					player2.addrodadaE();
					click=false;
					
					System.out.println("deu certo ");
				}else{
					JOptionPane.showMessageDialog(null,player+ " Você errou !!");
					click=false; 
					state = STATE.RESTART;
				}
			}
		}
		}
	}
	public void buffer(){
		createBufferStrategy(3); // cria um buffer para controlar o paint
	}
	
	
	// metodo piscar
	public void piscar() throws InterruptedException, LineUnavailableException, UnsupportedAudioFileException, IOException{
		Graphics g = getBufferStrategy().getDrawGraphics(); // cria um graphics
		Graphics g2 = g.create(getInsets().right,  getInsets().top,  getWidth() - getInsets().left, getHeight() - getInsets().bottom);// cria um graphics
		
		if (state == STATE.GAME){ // caso esteja no jogo
			Thread.sleep(500);// controlar a entrada do game
			if(pc == PC.jogando){ // caso PC esteja jogando
					if(player == Pllayer.player1){// caso a sequencia seja do player 1
							rects.piscar(Sequencia.retornar(a+1), (Graphics2D) g2); // atraves da variavel a, ele pecorre a sequencia e retorna o valor do quadrado a ser ativado
							a++; // incrementa
					}else{ // sequencia do player 2
							
							rects.piscar(Sequencia2.retornar(a+1), (Graphics2D) g2);// atraves da variavel a, ele pecorre a sequencia e retorna o valor do quadrado a ser ativado
							a++;// incrementa
					}
			tempo.setTempo2();// ATUALIZAR O TEMPO
			}
		}
			Thread.sleep(300);// tempo do quadrado
			
			if(a==jogadaPC){ // caso o valor de 'a' seja a rodada do pc
			pc=PC.livre; // PC para de jogar, para que o jogador possa jogar
			tempo.calcularTempo();// Calcular o tempo
			}
		}

	public void paintScreen() { // pinta a tela
		if (!getBufferStrategy().contentsLost())
			getBufferStrategy().show();
	}

	public void render()throws InterruptedException { // renderiza os quadrado estatico , HUD , TEMPO e MENU
		
		Graphics g = getBufferStrategy().getDrawGraphics();
		Graphics g2 = g.create(getInsets().right,  getInsets().top,  getWidth() - getInsets().left, getHeight() - getInsets().bottom);

		
		g2.setColor(Color.LIGHT_GRAY);		// a cor da janela
		g2.fillRect(0, 0, getWidth(), getHeight()); // cria um rect com tamanho da janela e pinta com a cor escolhida
		
		
	
		if (state == STATE.GAME){// caso esteja no game
		
		rects.paint((Graphics2D) g2,player1.GetNome(),player2.GetNome(),Click,player1.getpont(),player2.getpont(),tempo.getTempo(),jogadaPC,jogadaPC2); // renderizaz a tela com os objetos
		
		if(Click=="Clicou"){ // caso click esteja "ativo" ele apaga para que não apareca na tela
			Click="";
		}
		this.addMouseListener(this); // adiciona evento do mouse
		
		}else if(state == STATE.MENU){ // caso esteja no menu
			
			g2.setColor(Color.DARK_GRAY);		// seleciona a cor do menu
			g2.fillRect(0, 0, getWidth(), getHeight()); // cria um rect com tamanho da janela e pinta com a cor escolhida
			Menu.render(g); // renderiza o menu
			this.addMouseListener(this); // adiciona evento do mouse
		}else if(state == STATE.RESTART){// caso esteja no menu RESTART
			
			g2.setColor(Color.DARK_GRAY);	// seleciona a cor do menu	
			g2.fillRect(0, 0, getWidth(), getHeight());// cria um rect com tamanho da janela e pinta com a cor escolhida
			Menu.renderRestart(g);// renderiza o menu RESTART
			this.addMouseListener(this);// adiciona evento do mouse
		}
		
		g.dispose(); //LIMPA
		g2.dispose();// LIMPA
		}
	
		
	
	
	
// EVENTOS DO MOUSE
@Override
	public void mousePressed(MouseEvent e) {
	// EVENTOS DO MOUSE QUANDO O JOGO ESTá NO MENU
		if((state == STATE.MENU)){
			if ((e.getButton() == 1) && Menu.Classic.contains(e.getX(), e.getY() )) { // verifica se existe um evento de mouse nesse quadrado
				player1.setnome(1);// PERGUNTA O NOME DO PLAYER
				player2.setnome(2);// PERGUNTA O NOME DO PLAYER
				modo = MODO.Classic;// ativar o modo de jogo
				state = STATE.GAME;// iniciar o game
				conclusao = true;// ativar randomização
			}
			
			if ((e.getButton() == 1) && Menu.fantasma.contains(e.getX(), e.getY() )) {// verifica se existe um evento de mouse nesse quadrado
				player1.setnome(1);// PERGUNTA O NOME DO PLAYER
				player2.setnome(2);
				modo = MODO.Fantasma; // ativar o modo de jogo
				state = STATE.GAME;// iniciar o game
				conclusao = true;// ativar randomização
			}
			
			if ((e.getButton() == 1) && Menu.Invert.contains(e.getX(), e.getY() )) {// verifica se existe um evento de mouse nesse quadrado
				player1.setnome(1);// PERGUNTA O NOME DO PLAYER
				player2.setnome(2);// PERGUNTA O NOME DO PLAYER
				modo = MODO.Espelhado;// ativar o modo de jogo
				state = STATE.GAME;// iniciar o game
				conclusao = true;// ativar randomização
			}
			
			
			if ((e.getButton() == 1) && Menu.crazy.contains(e.getX(), e.getY() )) {// verifica se existe um evento de mouse nesse quadrado
				player1.setnome(1);// PERGUNTA O NOME DO PLAYER
				player2.setnome(2);// PERGUNTA O NOME DO PLAYER
				modo = MODO.Locura; // ativar o modo de jogo
				state = STATE.GAME; // iniciar o game
				conclusao = true;// ativar randomização
			}
			
			
			if ((e.getButton() == 1) && Menu.sair.contains(e.getX(), e.getY() )) {// verifica se existe um evento de mouse nesse quadrado
				System.exit(0); // sair
			}
	}
			
		// EVENTOS DO MOUSE QUANDO O JOGO ESTá NO MENU RESTART
			if((state == STATE.RESTART)){
				if ((e.getButton() == 1) && Menu.Invert.contains(e.getX(), e.getY() )) { // Aqui para o restart apesar do rect ser do inverter as posiçao são as mesma
					// REINICIANDO TODAS AS VARIAVEIS
					tempo.setTempo2();// ATUALIZAR O TEMPO
					Sequencia = new sequencia();
					Sequencia2 = new sequencia();
					player1.restart();
					player2.restart();
					jogadaPC2=0;
					jogadaPC=0;
					conclusao = true;
					rects.rectrestart();
					System.out.println("________________________________________");
					Click="";
					player=Pllayer.player1;
					state = STATE.GAME;
				}
				if ((e.getButton() == 1) && Menu.fantasma.contains(e.getX(), e.getY() )) { // Aqui para o restart apesar do rect ser do locura as posiçao são as mesma
					System.exit(0);
				}
			}
		
			
			
	// EVENTOS DO MOUSE QUANDO O JOGO ESTá ATIVO E QUANDO PC NÃO ESTA JOGANDO		
	if((state == STATE.GAME)&&(pc == PC.livre)){ //Esperando o menu desaparecer e o PC parar de JOGAR
	   if ((e.getButton() == 1) && rects.razul.contains(e.getX(), e.getY() )) {// verifica se existe um evento de mouse nesse quadrado
		   	 n=4; // valor do quadrado azul
			 click=true;	// ativa o gatilho "o usuario clicou"	
			 Click="Clicou";// avisa que clicou
			 tempo.setTempo2();// ATUALIZAR O TEMPO
			 }
	   if ((e.getButton() == 1) && rects.rverde.contains(e.getX(), e.getY()) ) {// verifica se existe um evento de mouse nesse quadrado
			 n=3; // valor do quadrado verde
			 click=true;// ativa o gatilho "o usuario clicou"
			 Click="Clicou";// avisa que clicou
			 tempo.setTempo2();// ATUALIZAR O TEMPO
		     }
	   if ((e.getButton() == 1) && rects.ramara.contains(e.getX(), e.getY()) ) {// verifica se existe um evento de mouse nesse quadrado
			 n=2; //valor do quadrado amarelo
			 click=true;// ativa o gatilho "o usuario clicou"
			 Click="Clicou";// avisa que clicou
			 tempo.setTempo2();// ATUALIZAR O TEMPO
		     }
	   if ((e.getButton() == 1) && rects.rverm.contains(e.getX(), e.getY()) ) {// verifica se existe um evento de mouse nesse quadrado
			 n=1;// valor do quadrado vermelho
			 click=true; // ativa o gatilho "o usuario clicou"
			 Click="Clicou"; // avisa que clicou
			 tempo.setTempo2(); // ATUALIZAR O TEMPO
		    }
	}
}

// EVENTOS NÂO USADOS , que precisa ser criados
@Override
public void mouseClicked(MouseEvent e) {
}
@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub

}
@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	
}
}