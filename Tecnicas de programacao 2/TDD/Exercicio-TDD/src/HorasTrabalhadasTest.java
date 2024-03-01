import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HorasTrabalhadasTest {

	@Test
	public void calculaHorasNormais() {
		CalculaHoraTrabalhada journey = new CalculaHoraTrabalhada();
		float resposta = journey.calculaHT(13f, 18f);
		
		assertEquals(5f,resposta);
	}
	
	@Test
	public void calculaAdicionalAlmoco() {
		CalculaHoraTrabalhada journey = new CalculaHoraTrabalhada();
		float resposta = journey.calculadoraHoraExtra(8f, 18f);
		
		assertEquals(11.5f,resposta);
	}
	
	@Test
	public void calculaHoraExtraTarde() {
		CalculaHoraTrabalhada journey = new CalculaHoraTrabalhada();
		float horaExtraDinheiro = journey.calculadoraHoraExtra(14f, 19f);
		
		assertEquals(5.5f, horaExtraDinheiro);
	}

	@Test
	public void calculaHoraExtraManha() {
		CalculaHoraTrabalhada journey = new CalculaHoraTrabalhada();
		float horaExtraDinheiro = journey.calculadoraHoraExtra(6f, 12f);
		
		assertEquals(7f, horaExtraDinheiro);
	}
	
	@Test
	public void calculaHEAlmoco() {
		CalculaHoraTrabalhada journey = new CalculaHoraTrabalhada();
		float horaExtraDinheiro = journey.calculadoraHoraExtra(6f, 13f);
		
		assertEquals(9.5f, horaExtraDinheiro);
	}
	
	@Test
	public void calculaAdicionalNoturno() {
		CalculaHoraTrabalhada journey = new CalculaHoraTrabalhada();
		float horaExtraDinheiro = journey.calculadoraHoraExtra(17f, 23f);
		
		assertEquals(9f, horaExtraDinheiro);
	}
}
