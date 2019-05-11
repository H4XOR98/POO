package ClassesBase;

import Exceptions.*;
import Exceptions.*;

public class Aluguer{
    
    // Variáveis de Instância
    private int nif;
    private Ponto destino;
    private TipoCombustivel tipoCombustivel;
    private PreferenciaAluguer preferencia;
    private Metereologia meteo;
    private Trafego trafego;


    public Aluguer() {
        this.nif = 0;
        this.destino = new Ponto();
        this.tipoCombustivel = TipoCombustivel.Gasolina;
        this.preferencia = PreferenciaAluguer.MaisPerto;
        this.meteo = Metereologia.getRandom();
        this.trafego = Trafego.getRandom();
    }


    public Aluguer(int nif, Ponto destino, TipoCombustivel tipoCombustivel, PreferenciaAluguer preferencia) {
        this.nif = nif;
        this.destino = destino;
        this.tipoCombustivel = tipoCombustivel;
        this.preferencia = preferencia;
        this.meteo = Metereologia.getRandom();
        this.trafego = Trafego.getRandom();
    }

    public Aluguer(Aluguer a){
        this.nif = a.getNif();
        this.destino = a.getDestino();
        this.tipoCombustivel = a.getTipoCombustivel();
        this.preferencia = a.getPreferencia();
        this.meteo = a.getMeteorologia();
        this.trafego = a.getTrafego();
    }


    //getters

    public int getNif() {
        return this.nif;
    }
    
    public Ponto getDestino() {
        return this.destino;
    }

    public TipoCombustivel getTipoCombustivel() {
        return this.tipoCombustivel;
    }

    public PreferenciaAluguer getPreferencia() {
        return this.preferencia;
    }
    
    public Metereologia getMeteorologia() {
        return this.meteo;
    }
    
    public Trafego getTrafego() {
        return this.trafego;
    }
    
    
    
    //setters

    public void setNif(int nif) {
        this.nif = nif;
    }

    public void setDestino(Ponto destino) {
        this.destino = destino;
    }

    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public void setPreferencia(PreferenciaAluguer preferencia) {
        this.preferencia = preferencia;
    }

    public void setMeteorologia(Metereologia meteo) {
        this.meteo = meteo;
    }
    
    public void setTrafego(Trafego trafego) {
        this.trafego = trafego;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("............ALUGUER............\n");
        sb.append("Destino: \n\t" + this.destino.toString() + ";\n");
        sb.append("Nif: " + this.nif + ";\n");
        sb.append("Tipo de Combustivel: " + this.tipoCombustivel + ";\n");
        sb.append("Preferencia: " + this.preferencia + ";\n");
        sb.append("Metereologia: " + this.meteo + ";\n");
        sb.append("Trafego: " + this.trafego + ";\n");
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
        
        return (this.destino.equals(a.getDestino()) && this.nif==a.getNif() && this.tipoCombustivel.equals(a.getTipoCombustivel()) &&
                this.preferencia == a.getPreferencia());
    }
   
    
    public Aluguer clone(){
        return new Aluguer(this);
    }   
    
    /*
    public Veiculo determinaVeiculo(){
        if (this.preferencia.equals(PreferenciaAluguer.MaisBarato)){
            
        }
    }
    */
   
   
    public double tempoViagem(Cliente cliente,Veiculo veiculo){
        double distancia = cliente.getLocalizacao().distancia(veiculo.getLocalizacao());
        double duracao = distancia / 4;
        distancia = this.destino.distancia(veiculo.getLocalizacao());
        duracao += distancia * veiculo.getVelocidadeMedia();
        return duracao;
    }
    
    
    public double tempoRealViagem(Cliente cliente,Veiculo veiculo){
        double duracao = tempoViagem(cliente,veiculo);
        duracao *= (100 - cliente.getDestreza())/100;
	duracao *= (100 - veiculo.getClassificacao())/100;
        duracao *= this.meteo.getPercentagem();
	duracao *= this.trafego.getPercentagem();
        return duracao;
    }
}