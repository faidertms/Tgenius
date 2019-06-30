import java.util.Random;

import javax.swing.JOptionPane;
// lista encadeada com metodos funcionais para o jogo
class No{
	protected int valor;
	protected No prox;
    protected Random gerador = new Random();

    public No(int v){
        valor = v;
        prox = null;
    }
    public No(){
    	valor=gerador.nextInt(4)+1;
    }
}

class sequencia{
    No primeiro,ultimo;
    int totalNos;

    public sequencia(){
        primeiro = ultimo = null;
        totalNos = 0;
    }

    public int getTotalNos(){ 
        return totalNos;
    }

    public boolean checkIfListaVazia(){
        if (getTotalNos() == 0){
            return true;
       }
       return false;
    }
    public boolean checkIfListaCheia(){
        if (getTotalNos() == 32){// não deixa o numero de nó passar de 32(TOTAL DE JOGADAS)
            return true;
       }
       return false;
    }

    public void inserirNoInicio(No n) {
        if ( checkIfListaVazia() ){
            primeiro = ultimo = n;
        }
        else{
            n.prox = primeiro;
            primeiro = n;
        }
        totalNos++;
    }
    public void inserirNoFim(No n){ 
    	if(checkIfListaCheia()){ 
    		JOptionPane.showMessageDialog(null," Passou do Limite");
    	}else{
    	
        if ( checkIfListaVazia() ){
            primeiro = ultimo = n;
        }
        else{
            ultimo.prox = n;
            ultimo = n;
       }
       totalNos++;
    }
    }

    public void excluirNo(No n){
        No noAtual;
        No noAnterior;
        noAtual = noAnterior = primeiro;
        int contador = 1;

        if (!checkIfListaVazia()){
            while (contador <= getTotalNos() &&
                     noAtual.valor != n.valor){
                noAnterior = noAtual;
                noAtual = noAtual.prox;
                contador++;
            } 

        if(noAtual.valor == n.valor){
            if ( getTotalNos() == 1){
                primeiro = ultimo = null;
           }
           else{
               if (noAtual == primeiro){
                   primeiro = noAtual.prox;
               }
               else{
                   noAnterior.prox = noAtual.prox;
               }
           }
           totalNos--;
        }
    }
}
    public int retornar(int n){
    	No temp = primeiro;
    	int contador = 1;
    	if ( checkIfListaVazia() == false ){
    		while (contador < n){
    			temp = temp.prox;
    			contador++;
        }
    	}
    		return temp.valor;
    
    }
    
    public void randomizarLouco(int n){ // n é numero da jogada, caso a jogada seja 3 ele ira randomizar 3 valores , so é util no modo Locura onde a objeto é zerado a cada rodada
    	int t= 0;
    	while(t<=n){
    		inserirNoFim(new No());
    		t++;
    	}
    }
    
    public void exibirLista(){ // apenas para exibir a lista com o valor da cor e a jogada
    	No temp = primeiro;
    	int contador = 1;
    	if ( checkIfListaVazia() == false ){
    		while (contador <= getTotalNos()){
    			System.out.println(temp.valor +"           "+ contador);
    			temp = temp.prox;
    			contador++;
        }
    }
 }
}
