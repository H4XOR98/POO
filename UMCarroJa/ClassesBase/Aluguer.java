package ClassesBase;

import Exceptions.*;
import java.time.temporal.ChronoUnit;
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
    private Trafego trafego;


    public Aluguer() {
        this.estado = EstadoAluguer.Espera;
        this.destino = new Ponto();
        this.cliente = new Cliente();
        this.veiculo = null;
        this.numKms = 0;
        this.dataInicio = LocalDateTime.now();
        this.dataFim = LocalDateTime.now();
        this.precoViagem = 0;
        this.meteo = Metereologia.getRandom();
        this.trafego = Trafego.getRandom();
    }


    public Aluguer(EstadoAluguer estado, Ponto destino, Cliente cliente, Veiculo veiculo, double numKms, LocalDateTime dataInicio, LocalDateTime dataFim, double precoViagem) {
        this.estado = estado;
        this.destino = destino;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.numKms = numKms;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.precoViagem = precoViagem;
        this.meteo = Metereologia.getRandom();
        this.trafego = Trafego.getRandom();
    }

    public Aluguer(Aluguer aluguer){
        this.estado = aluguer.getEstado();
        this.destino = aluguer.getDestino();
        this.cliente = aluguer.getCliente();
        this.veiculo = aluguer.getVeiculo();
        this.numKms = aluguer.getNumKms();
        this.dataInicio = aluguer.getDataInicio();
        this.dataFim = aluguer.getDataFim();
        this.precoViagem = aluguer.getPrecoViagem();
        this.meteo = aluguer.getMetereologia();
        this.trafego = aluguer.getTrafego();
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
    
    public double getPrecoViagem() {
        return this.precoViagem;
    }
    
    public Metereologia getMetereologia(){
        return this.meteo;
    }
    
    public Trafego getTrafego(){
        return this.trafego;
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

    public void setPrecoViagem(double precoViagem) {
        this.precoViagem = precoViagem;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("............ALUGUER............\n");
        sb.append("Estado: " + this.estado +";\n");
        sb.append("Destino: \n\t" + this.destino.toString());
        sb.append(this.cliente.toString());
        sb.append(this.veiculo.toString());
        sb.append("Data:\n");
        sb.append("\tInicio " + this.dataInicio.toString() + ";\n");
        sb.append("\tFim " + this.dataFim.toString() + ";\n");
        sb.append("Numero de Kms: " + this.numKms + ";\n");
        sb.append("Estado:\n");
        sb.append("\tMetereologia: " + this.meteo + ";\n");
        sb.append("\tTrafego: " + this.trafego + ";\n");
        sb.append("A pagar " + this.precoViagem + "€.\n");
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
    
    
    
    
    
    public double tempoRealViagem(){
        double duracao = ChronoUnit.MINUTES.between(this.dataInicio, this.dataFim);
        duracao *= (100 - this.cliente.getDestreza())/100;
        duracao *= (100 - this.veiculo.getClassificacao())/100;
        duracao *= this.meteo.getPercentagem();
        duracao *= this.trafego.getPercentagem();
        return duracao;
    }
    
    
    
    
}
