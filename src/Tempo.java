// classe tempo
public class Tempo {
	
	private long tempo;
	private long tempo1;
	private long tempo2;
	private long tempo3;
	
	public Tempo(){
		tempo=tempo1=tempo2=tempo3=0;
	}
	
	public void setTempo1(){
		tempo1 = System.currentTimeMillis(); // guarda o tempo no qual foi chamado
		
	}
	public void setTempo2(){
		tempo2 = System.currentTimeMillis(); // guarda o tempo no qual foi chamado
		
	}
	public void setTempo3(){
		tempo3 = System.currentTimeMillis(); // guarda o tempo no qual foi chamado
		
	}
	public void calcularTempo(){
		tempo=((System.currentTimeMillis()-(tempo1+tempo2+tempo3))/1000); // Nessa condição temp é 0
		
	}
	public long getTempo(){ // retorna tempo
		return tempo;
	}
	public boolean Tempolimite(){  // metodo para analisar se o jogador passou de 5 seg
		if(tempo>5){
			return true;
		}
		return false;
	}
	public void resetar(){  // reinicia os tempos
		tempo=tempo1=tempo2=tempo3=0;
	}
}
