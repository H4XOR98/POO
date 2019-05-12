package ClassesBase;

import Exceptions.*;
import Exceptions.*;

public class Aluguer{
    
    // Variáveis de Instância
    private int nif;
    private Ponto destino;
    private TipoCombustivel tipoCombustivel;
    private PreferenciaAluguer preferencia;
    private Veiculo veiculo;
    private double custo;
    private double distancia;
    private double duracao;
    private Metereologia meteo;
    private Trafego trafego;


    public Aluguer() {
        this.nif = 0;
        this.destino = new Ponto();
        this.tipoCombustivel = TipoCombustivel.Gasolina;
        this.preferencia = PreferenciaAluguer.MaisPerto;
        this.veiculo = new Veiculo();
        this.custo = 0;
        this.distancia = 0;
        this.duracao = 0;
        this.meteo = Metereologia.getRandom();
        this.trafego = Trafego.getRandom();
    }


    public Aluguer(int nif, Ponto destino, TipoCombustivel tipoCombustivel, PreferenciaAluguer preferencia) {
        this.nif = nif;
        this.destino = destino;
        this.tipoCombustivel = tipoCombustivel;
        this.preferencia = preferencia;
        this.veiculo = new Veiculo();
        this.custo = 0;
        this.distancia = 0;
        this.duracao = 0;
        this.meteo = Metereologia.getRandom();
        this.trafego = Trafego.getRandom();
    }

    public Aluguer(Aluguer a){
        this.nif = a.getNif();
        this.destino = a.getDestino();
        this.tipoCombustivel = a.getTipoCombustivel();
        this.preferencia = a.getPreferencia();
        this.veiculo = a.getVeiculo();
        this.custo = a.getCusto();
        this.distancia = a.getDistancia();
        this.duracao = a.getDuracao();
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
    
    public double getCusto() {
        return this.custo;
    }
    
    public double getDistancia() {
        return this.distancia;
    }
    
    public double getDuracao() {
        return this.duracao;
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

    public void setCusto(double custo) {
        this.custo = custo;
    }
    
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
    
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    
    
    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("............ALUGUER............\n");
        sb.append("Destino: \n\t" + this.destino.toString() + ";\n");
        sb.append("Nif: " + this.nif + ";\n");
        sb.append("Tipo de Combustivel: " + this.tipoCombustivel + ";\n");
        sb.append("Preferencia: " + this.preferencia + ";\n");
        sb.append("Custo: " + this.custo + ";\n");
        sb.append("Distancia: " + this.distancia + ";\n");
        sb.append("Duracao: " + this.duracao + ";\n");
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
                this.preferencia == a.getPreferencia() && this.custo == a.getCusto() && this.distancia == a.getDistancia() &&
                this.duracao == a.getDuracao() && this.meteo.equals(a.getMeteorologia()) && this.trafego.equals(a.getTrafego()));
    }
   
    
    public Aluguer clone(){
        return new Aluguer(this);
    }   
    
    //------------------------------------
    
    public double custoViagem(Veiculo veiculo, double duracao){
        return duracao * veiculo.getPreco();
    }
    
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
    
    
    
    public double iniciaAluguer(Cliente cliente, Veiculo veiculo) throws AutonomiaVeiculoInsuficienteException{
        this.distancia = veiculo.getLocalizacao().distancia(this.destino);
        if(this.distancia > veiculo.getAutonomiaAtual()){
            throw new AutonomiaVeiculoInsuficienteException("Ups! A autonomia é insuficiente.");
        }
        double tempoViagem = tempoViagem(cliente,veiculo);
        cliente.setLocalizacao(veiculo.getLocalizacao());
        return tempoViagem;
    }
    
    
    public void alugaVeiculo (Cliente cliente, Veiculo veiculo,Proprietario proprietario, double avaliacaoCliente,double avaliacaoVeiculo,
                              double avaliacaoProprietario) throws AutonomiaVeiculoInsuficienteException, AvaliacaoInvalidaException{
        double tempoViagem = iniciaAluguer(cliente,veiculo);
        double custoEstimado = custoViagem(veiculo,tempoViagem);
        this.duracao = tempoRealViagem(cliente,veiculo);
        this.custo =  custoViagem(veiculo,this.duracao);
        veiculo.diminuirAutonomiaAtual(this.distancia);
        veiculo.novaAvaliacao(avaliacaoVeiculo);
        cliente.novaAvaliacao(avaliacaoCliente);
        proprietario.novaAvaliacao(avaliacaoProprietario);
    }
   
    
    
}