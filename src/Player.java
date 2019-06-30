import javax.swing.JOptionPane;


public class Player {
	private String nome = null;
	private int Pontuacao;// criar pontuacao apartir da rodada 1 rodada igual 30 ponto String pont ="Pontuação: " + Integer.toString(Pontuacao);
	private int rodada=0;
	private int rodadaE=0; // exclusivo para o modo espelhado, pois ele so faz diminuir
	
	public Player(){
		nome=null;
		Pontuacao=0;
				
	}
	public void restart(){ // reinicia as variaveis utilizada
		Pontuacao=0;
		rodada=0;
		rodadaE=0;
	}
	public void pontucao(){ // incrementa a pontuacao
		Pontuacao+=5;
	}
	public int getpont(){ // retorna pontuacao
		return Pontuacao;
	}
	public void inversor(int z) { // simples trabalho de receber o numero da rodada PC
		rodadaE=z;
	}
	public void addrodadaE() { // e diminuir pra que a sequencia seja decrescente
		rodadaE--;
	}
	public int getEspelhado() { // simples trabalho de receber o numero da rodada PC e diminuir pra que a sequencia seja decrescente
		return rodadaE;
	}
	
	//MODO CLASSICO , LOCURA e FANTASMA
	
	public void resetrodada() { // reseta a rodada para que inicie do 0 , não é utilizada no restart
		rodada=0;
	}
	public void addrodada() {// incrementa a rodada
		rodada++;
	}
	
	public int getrodada(){ // retorna a rodada
		return rodada;
	}
	
	
	
	// PEGAR OS NOMES DOS PLAYERS
	public String GetNome(){
		return nome;
	}
	public	void setnome(int n){ // adicionar o nome do jogador , o parametro é apenas para dizer qual é o player
		while (nome == null || nome.equals("")) { // fica em loop ate o nome existir
		      nome = JOptionPane.showInputDialog("Nome do Player "+n);
		      if (nome == null || nome.equals("")) { // caso não coloque o nome, uma janela aparece dizendo que não foi escrito
			        JOptionPane.showMessageDialog(null,"Você não respondeu a pergunta.");
			      }
		    }
	}
}
