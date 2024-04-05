
package Controller;

import Model.DAO.*;
import Model.Estacionamento.*;
import static Model.Estacionamento.MetricaCalculoEnum.*;
import View.TelaPrincipal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Controlador {
    
    private List<ContaVeiculo> listaVeiculos;
    private Thread t0,t1;
    private PersistenciaDados DAO;
    
    public Controlador(){
        listaVeiculos=new ArrayList<ContaVeiculo>();  
        DAO=new PersistenciaDados();
    }
    
    public void addContaVeiculo(String nome, String placa, TipoVeiculoEnum tipo)throws Exception{
        listaVeiculos.add(new ContaVeiculo(Calendar.getInstance().getTimeInMillis() - (1000*60*60*2), new Veiculo(nome, placa, tipo)));
    }
    
    public String[][] listaVeiculosCadastrados() throws Exception{
        
        String[][] aux=new String[listaVeiculos.size()][6];
        ContaVeiculo conta;
        Date dataAux;
        for(int i=0;i<listaVeiculos.size();i++){
            conta=(ContaVeiculo) listaVeiculos.get(i);
            aux[i][0]=conta.getVeiculo().getNome();
            aux[i][1]=conta.getVeiculo().getPlaca();
            aux[i][2]=conta.getVeiculo().getTipo().toString();
            dataAux=new Date(conta.getInicio());
            aux[i][3]= dataAux.toString();
            dataAux=new Date(conta.getFim());
            aux[i][4]= dataAux.toString();
            aux[i][5]=conta.getStatus().toString();
        }
        return aux;
        
    }
    
    public void finalizarConta(String placaVeiculo,MetricaCalculoEnum metrica) throws Exception{
        //Finaliza a conta, utilizando a metrica de calculo recebida como paramentro.
        // Se a metrica for AUTOMATICO, o sistema deverá verificar a opção mais barata e utiliza-la
        
        
        // Altera o status para fechado e salva o registro.
        for (int i = 0; i < listaVeiculos.size(); i++) {
            if (placaVeiculo.equals(listaVeiculos.get(i).getVeiculo().getPlaca())) {
                listaVeiculos.get(i).setStatus(StatusConta.FECHADO);
            }
        }    
        //Se valor da conta for zero retorna um erro.
        
        //Se não for possivel registra no BD, salve um backup local da listaVeiculos;
        //Utilize o objeto DAO
       
        
        
        
    }
    
    public long calculaPermanencia(String placa) {
        
        long permanencia = 0;
        
        for (int i = 0; i < listaVeiculos.size(); i++) {
            if (placa.equals(listaVeiculos.get(i).getVeiculo().getPlaca())) {
                permanencia = (Calendar.getInstance().getTimeInMillis() - listaVeiculos.get(i).getInicio());
            }
        }
        return permanencia;
    }
    
    public String calculaValorEstacionamento(MetricaCalculoEnum metrica, String placa) {
    String resultado = null;
    
    for (int i = 0; i < listaVeiculos.size(); i++) {
        if (placa.equals(listaVeiculos.get(i).getVeiculo().getPlaca())) {
            double valorCalculado;
            switch (metrica) {
                case UM_QUARTO_HORA:
                     // Criar uma instância de Calculo15Minutos
                    Calculo15Minutos calculo15Minutos = new Calculo15Minutos();
                    
                    // Chamar o método calcular na instância criada
                    valorCalculado = calculo15Minutos.calcular(calculaPermanencia(placa), listaVeiculos.get(i).getVeiculo());
                    
                    resultado = String.valueOf(valorCalculado); // Converter o resultado double para String
                    break;
                    
                case DIARIA:
                    CalculoDiaria calculoDiaria = new CalculoDiaria();
                    
                    valorCalculado = calculoDiaria.calcular(calculaPermanencia(placa), listaVeiculos.get(i).getVeiculo());
                    
                    resultado = String.valueOf(valorCalculado);
                    break;
                case HORA:
                    CalculoHorista calculoHorista = new CalculoHorista();
                    
                    valorCalculado = calculoHorista.calcular(calculaPermanencia(placa), listaVeiculos.get(i).getVeiculo());
                    
                    resultado = String.valueOf(valorCalculado);
                    break;
                case AUTOMATICO:
                    resultado = calculaAutomatico(placa);
                    break;
                default:
                    // Caso padrão, talvez lançar uma exceção ou lidar de outra forma com métricas desconhecidas
                    break;
            }
        }
    }
    return resultado;
    }
    
    private String calculaAutomatico(String placa){
        long permanencia = calculaPermanencia(placa);
        
        if (permanencia < (60 * 60 * 1000)) {
            return calculaValorEstacionamento(UM_QUARTO_HORA, placa);
        } else if (permanencia >= (60 * 60 * 1000) && permanencia < (12 * 60 * 60 * 1000)) {
            return calculaValorEstacionamento(HORA, placa);
        } else return calculaValorEstacionamento(DIARIA, placa);
        
    }
    
    public void salvar(String caminho)throws IOException {

        Serializador.gravar(caminho, listaVeiculos);
    }      

    public void ler(String caminho) throws ClassNotFoundException,IOException{
        listaVeiculos = (List<ContaVeiculo>)Serializador.ler(caminho);
    }
}    
