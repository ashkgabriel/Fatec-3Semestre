

public class Pilha {
	
	String[] pilha;
	int qtdItens;

	public Pilha (int tamanhoDaPilha) {
		pilha = new String[tamanhoDaPilha];
		qtdItens = 0;
	}
	
	boolean estaVazia() {
		
		return (qtdItens == 0);

	}

	void empilhar(String elemento) {
		if (!estaCheia()) {
			pilha[qtdItens] = elemento;
			qtdItens++;			
		}
	
	}

	String desempilha() {
		if (!estaVazia()) {
			qtdItens--;
			return pilha[qtdItens];			
		}
		throw new ExceptionPilha("Pilha está vazia.");
		
	}

	Object topo() {
		
		return pilha[qtdItens-1];
	}

	boolean estaCheia() {
		// TODO Auto-generated method stub
		return (qtdItens > 0);
	}
}