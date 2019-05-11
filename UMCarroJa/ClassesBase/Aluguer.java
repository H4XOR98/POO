package ClassesBase;

import Exceptions.*;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;

public class Aluguer{
    
    // Variáveis de Instância
    private int nifCliente;
    private Ponto destino;
    private TipoCombustivel tipoCombustivel;
    private PreferenciaAluguer preferencia;


    public Aluguer() {
        
    }


    public Aluguer(Ponto destino, Cliente cliente, Veiculo veiculo, double numKms, double precoViagem) {
        
    }

    public Aluguer(Aluguer aluguer){
        
    }


    //getters

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

    public void setPrecoViagem(double precoViagem) {
        this.precoViagem = precoViagem;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("............ALUGUER............\n");
        sb.append("Destino: \n\t" + this.destino.toString());
        sb.append(this.cliente.toString());
        sb.append(this.veiculo.toString());
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
        
        return (this.destino.equals(a.getDestino()) && this.cliente.equals(a.getCliente()) &&  this.veiculo.equals(a.getVeiculo()) && this.numKms == a.getNumKms());
    }
    
    
    public Aluguer clone(){
        return new Aluguer(this);
    }   
    
}
