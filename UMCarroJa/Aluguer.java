import java.time.LocalDateTime;
/**
 * Escreva a descrição da classe GestorAluguer aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Aluguer
{
    private EstadoAluguer estado;
    private Ponto destino;
    private Cliente cliente;
    private Veiculo veiculo;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    
    
    public Aluguer(){
        this.estado = null;
        this.destino = new Ponto();
        this.cliente = new Cliente();
        this.veiculo = null;
        this.dataInicio = LocalDateTime.now();
        this.dataFim = LocalDateTime.now();
    }
    
    public Aluguer(EstadoAluguer estado,Ponto destino,Cliente cliente,Veiculo veiculo){
        this.estado = null;
        this.destino = new Ponto();
        this.cliente = new Cliente();
        this.veiculo = null;
        this.dataInicio = LocalDateTime.now();
        this.dataFim = LocalDateTime.now();
    }
}
