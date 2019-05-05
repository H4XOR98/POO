
import java.time.LocalDateTime;

public class Aluguer{
    
    // Variáveis de Instância
    private EstadoAluguer estado;
    private Ponto destino;
    private Cliente cliente;
    private Veiculo veiculo;
    private double numKms;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private double precoViagem;
    private Metereologia meteo;


    public Aluguer() {
        this.estado = EstadoAluguer.Espera;
        this.destino = new Ponto();
        this.cliente = new Cliente();
        this.veiculo = null;
        this.numKms = 0;
        this.dataInicio = LocalDateTime.now();
        this.dataFim = LocalDateTime.now();
        this.precoViagem = 0;
    }


    public Aluguer(EstadoAluguer estado, Ponto destino, Cliente cliente, Veiculo veiculo, double numKms, LocalDateTime dataInicio, LocalDateTime dataFim) {
        this.estado = estado;
        this.destino = destino;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.numKms = numKms;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Aluguer(Aluguer aluguer){
        this.estado = aluguer.getEstado();
        this.destino = aluguer.getDestino();
        this.cliente = aluguer.getCliente();
        this.veiculo = aluguer.getVeiculo();
        this.numKms = aluguer.getNumKms();
        this.dataInicio = aluguer.getDataInicio();
        this.dataFim = aluguer.getDataFim();
    }


    //getters


    public EstadoAluguer getEstado() {
        return this.estado;
    }

    public Ponto getDestino() {
        return this.destino.clone();
    }

    public Cliente getCliente() {
        return this.cliente.clone();
    }

    public Veiculo getVeiculo() {
        return this.veiculo.clone();
    }

    public double getNumKms() {
        return this.numKms;
    }

    public LocalDateTime getDataInicio() {
        return this.dataInicio;
    }

    public LocalDateTime getDataFim() {
        return this.dataFim;
    }

    //setters


    public void setEstado(EstadoAluguer estado) {
        this.estado = estado;
    }

    public void setDestino(Ponto destino) {
        this.destino = destino.clone();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente.clone();
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo.clone();
    }

    public void setNumKms(double numKms) {
        this.numKms = numKms;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("............ALUGUER............\n");
        sb.append("Estado: " + this.estado +";\n");
        sb.append("Destino: \n\t" + this.destino.toString());
        sb.append(this.cliente.toString());
        sb.append(this.veiculo.toString());
        sb.append("Numero de Kms: " + this.numKms + ";\n");
        sb.append("Data:\n");
        sb.append("\tInicio " + this.dataInicio.toString() + ";\n");
        sb.append("\tFim " + this.dataFim.toString() + ";\n");
        return sb.toString();
    }
    
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        Aluguer a = (Aluguer)o;
        
        return (this.estado.equals(a.getEstado()) && this.destino.equals(a.getDestino()) && this.cliente.equals(a.getCliente()) && 
                this.veiculo.equals(a.getVeiculo()) && this.numKms == a.getNumKms() && this.dataInicio.equals(a.getDataInicio()) &&
                this.dataFim.equals(a.getDataFim()));
    }
    
    
    public Aluguer clone(){
        return new Aluguer(this);
    }   
}
