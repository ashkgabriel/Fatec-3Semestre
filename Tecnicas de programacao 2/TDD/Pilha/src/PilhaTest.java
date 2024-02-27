import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PilhaTest {
	
	Pilha p;
	
	@BeforeEach
	public void inicializarPilha () {
		p = new Pilha(10);	
	}

//	Verificar se uma pilha está vazia
	@Test
	public void verificaSePilhaVazia () {

		assertTrue(p.estaVazia());
	}
	
	@Test
	public void empilharUmElemento () {
		
		assertTrue(p.estaVazia());
		p.empilhar("Elemento 1");
		assertFalse(p.estaVazia());
		assertFalse(p.estaCheia());
	}
	
	@Test
	public void empilharUmDesempilhaUmElemento () {
		
		assertTrue(p.estaVazia());
		p.empilhar("Elemento 1");
		assertFalse(p.estaVazia());
		String retorno = p.desempilha();
		assertTrue(p.estaVazia());
	}
	
	@Test
	public void verificaTopo () {
		
		assertTrue(p.estaVazia());
		p.empilhar("Elemento 1");
		assertFalse(p.estaVazia());
		assertTrue(p.estaVazia());
		p.empilhar("Elemento 2");
		assertFalse(p.estaVazia());
		String retorno = p.desempilha();
		
		assertEquals("Elemento 1", p.topo());
	}
	
	@Test
	public void verificaPilhaCheia () {
		assertFalse(p.estaCheia());
		for (int i = 0; i < 10; i++) {
			// Adiciona 10 elementos
			p.empilhar("Elemento " + i + 1);
		}
		assertTrue (p.estaCheia());
	}
	
	@Test
	public void empilhaDezDesempilhaOnze() {
		assertFalse(p.estaCheia());
		for (int i = 0; i < 10; i++) {
			// Adiciona 10 elementos
			p.empilhar("Elemento " + i + 1);
		}
		assertTrue(p.estaCheia());
		assertFalse(p.estaCheia());
		for (int i = 0; i < 11; i++) {
			// Adiciona 10 elementos
			p.desempilha();
		}
		assertTrue(p.estaVazia());
	}
}
