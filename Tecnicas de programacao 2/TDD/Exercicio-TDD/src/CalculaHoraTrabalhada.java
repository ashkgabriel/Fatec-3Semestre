
public class CalculaHoraTrabalhada {
	
	float totalHoras = 0f;

	public float calculaHT(float horaInicio, float horaFinal) {
		totalHoras = horaFinal - horaInicio;
		
		return totalHoras; 
	}
	
	public float calculaAlmoco(float horaInicio, float horaFinal) {
		return (calculaHT(horaInicio, horaFinal) > 6) ? 1.5f : 0f;		
	}

	public float calculadoraHoraExtra(float horaInicio, float horaFinal) {

		float horaExtra = 0f;
		float horaExtraN = 0f;
		float multiplicador = 0f;
		float multiplicadorN = 0f;
		
		if (horaInicio <= 18f && horaInicio >= 8f && horaFinal > 18f ){
			multiplicador = 1.5f;
			horaExtra = (horaFinal - 18f);
			
			return calculaHT(horaInicio, 18f) + horaExtra * multiplicador + calculaAlmoco(horaInicio, horaFinal);
		} 
		
		else if (horaInicio >= 6f && horaInicio < 8f && horaFinal > 8f) {
			multiplicador = 1.5f;
			horaExtra = (8f - horaInicio);
			
			
			return calculaHT(8f, horaFinal) + horaExtra * multiplicador + calculaAlmoco(horaInicio, horaFinal);
		} 
		
		else if (horaInicio >= 8f && horaFinal <= 18f) {
			multiplicador = 1f;
			horaExtra = 0;
			
			return calculaHT(horaInicio, horaFinal) + horaExtra * multiplicador + calculaAlmoco(horaInicio, horaFinal);
		} 
		
		else if (horaInicio <= 22f && horaFinal > 22f) {
			multiplicadorN = 2f;
			multiplicador = 1.5f;
			horaExtraN = (horaFinal - 22f);
			horaExtra = (horaInicio < 18) ? (18f - horaInicio) : 0f;
			
			return calculaHT(horaInicio, horaFinal) + horaExtra * multiplicador + horaExtraN * multiplicadorN + calculaAlmoco(horaInicio, horaFinal);
		}
		
		else return 2f;
	}
	
	
}
