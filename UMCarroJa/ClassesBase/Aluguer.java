package ClassesBase;

import Exceptions.*;
import java.text.DecimalFormat;

public class Aluguer{
    
    // Variáveis de Instância
    private int nif;
    private Ponto destino;
    private TipoCombustivel tipoCombustivel;
    private PreferenciaAluguer preferencia;
    private TipoVeiculo tipoVeiculo;
    private Veiculo veiculo;
    private EstadoAluguer estadoAluguer;
    private double custo;
    private double distancia;
    private double duracaoReal;
    private Metereologia meteo;
    private Trafego trafego;


    public Aluguer() {
        this.nif = 0;
        this.destino = new Ponto();
        this.tipoCombustivel = TipoCombustivel.Gasolina;
        this.preferencia = PreferenciaAluguer.MaisPerto;
        this.veiculo = new Veiculo();
        this.estadoAluguer = EstadoAluguer.Espera;
        this.custo = 0;
        this.distancia = 0;
        this.duracaoReal = 0;
        this.meteo = Metereologia.getRandom();
        this.trafego = Trafego.getRandom();
    }


    public Aluguer(int nif, Ponto destino, TipoCombustivel tipoCombustivel, PreferenciaAluguer preferencia) {
        this.nif = nif;
        this.destino = destino;
        this.tipoCombustivel = tipoCombustivel;
        this.preferencia = preferencia;
        this.veiculo = new Veiculo();
        this.estadoAluguer = EstadoAluguer.Espera;
        this.custo = 0;
        this.distancia = 0;
        this.duracaoReal = 0;
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
        this.duracaoReal = a.getDuracaoReal();
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
    
    public TipoVeiculo getTipoVeiculo() {
        return this.tipoVeiculo;
    }
    
    public Veiculo getVeiculo() {
        return this.veiculo;
    }
    
    public EstadoAluguer getEstadoAluguer() {
        return this.estadoAluguer;
    }
    
    public double getCusto() {
        return this.custo;
    }
    
    public double getDistancia() {
        return this.distancia;
    }
    
    public double getDuracaoReal() {
        return this.duracaoReal;
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
    
    public void setTipoVeiculo(TipoVeiculo tipoveiculo){
        this.tipoVeiculo = tipoVeiculo;
    }
    
    public void setVeiculo(Veiculo veiculo){
        this.veiculo = veiculo;
    }

    public void setEstadoAluguer(EstadoAluguer estadoAluguer) {
        this.estadoAluguer = estadoAluguer;
    }
    
    public void setCusto(double custo) {
        this.custo = custo;
    }
    
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
    
    public void setDuracaoReal(int duracaoReal) {
        this.duracaoReal = duracaoReal;
    }
    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("............ALUGUER............\n");
        sb.append("Destino: \n\t" + this.destino.toString() + ";\n");
        sb.append("Nif Cliente: " + this.nif + ";\n");
        sb.append("Tipo de Combustivel: " + this.tipoCombustivel + ";\n");
        sb.append("Preferencia: " + this.preferencia + ";\n");
        sb.append("Tipo de Veiculo: " + this.tipoVeiculo + ";\n");
        sb.append(this.veiculo.toString());
        sb.append("Estado Aluguer: " + this.estadoAluguer + ";\n");
        sb.append("Custo: " + this.custo + "€;\n");
        sb.append("Distancia: " + this.distancia + " km;\n");
        sb.append("Duracao Real: " + this.duracaoReal + " horas;\n");
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
        return this.nif == a.getNif() && this.destino.equals(a.getDestino()) && this.tipoCombustivel.equals(a.getTipoCombustivel()) && 
               this.preferencia.equals(a.getPreferencia()) && this.tipoVeiculo.equals(a.getTipoVeiculo()) &&  this.veiculo.equals(a.getVeiculo()) &&
               this.estadoAluguer.equals(a.getEstadoAluguer()) && this.custo == a.getCusto() && this.distancia == a.getDistancia() && 
               this.duracaoReal == a.getDuracaoReal() && this.meteo.equals(a.getMeteorologia()) && this.trafego.equals(a.getTrafego()); 
    }
   
    
    public Aluguer clone(){
        return new Aluguer(this);
    }   
    
    //------------------------------------
    
    private double converteEmHoras(double duracao) throws DuracaoNegativaException{
        if(duracao < 0){
            throw new DuracaoNegativaException("Ups! Duracao negativa não é aceite pelo sistema.\n");
        }
        int horas = (int)Math.floor(duracao);
        double minutos = (duracao - horas) * 0.6;
        double tempo = horas + minutos;
        DecimalFormat df = new DecimalFormat("#.##");      
        tempo = Double.valueOf(df.format(tempo));
        return tempo;
    }
    
    
    
    /*
    private void custoViagem(){
        this.custo = this.distancia * this.veiculo.getPreco();
    }
    
    
    public double tempoClienteVeiculo(Cliente cliente){
        double tempo = cliente.getLocalizacao().distancia(this.veiculo.getLocalizacao());
        tempo /= 4;
        tempo = converteEmHoras(tempo);
        return tempo;
    }
    
    private void tempoViagem(Cliente cliente) throws DuracaoNegativaException{
        this.distancia = cliente.getLocalizacao().distancia(this.veiculo.getLocalizacao());
        this.duracaoEstimada = distancia / 4;
        this.distancia = this.destino.distancia(this.veiculo.getLocalizacao());
        this.duracaoEstimada += this.distancia * this.veiculo.getVelocidadeMedia();
        this.duracaoEstimada = converteEmHoras(this.duracaoEstimada);
    }
    
    private void tempoRealViagem(Cliente cliente) throws DuracaoNegativaException{
        this.duracaoReal = this.duracaoEstimada;
        this.duracaoReal *= (100 - cliente.getDestreza())/100;
        this.duracaoReal *= (100 - this.veiculo.getClassificacao())/100;
        this.duracaoReal *= this.meteo.getPercentagem();
        this.duracaoReal *= this.trafego.getPercentagem();
        this.duracaoReal = converteEmHoras(this.duracaoReal);
    }
    
    
    private void iniciaAluguer(Cliente cliente) throws VeiculoNaoExisteException, DuracaoNegativaException{
        this.distancia = this.veiculo.getLocalizacao().distancia(this.destino);
        if(this.distancia > this.veiculo.getAutonomiaAtual()){
            throw new VeiculoNaoExisteException("Ups! A autonomia é insuficiente.");
        }
        tempoViagem(cliente);
        cliente.setLocalizacao(this.veiculo.getLocalizacao());
    }
    
    private void terminaAluguer(Cliente cliente, Proprietario proprietario, double avaliacaoCliente,double avaliacaoVeiculo,
                              double avaliacaoProprietario) throws VeiculoNaoExisteException, AvaliacaoInvalidaException, 
                              DuracaoNegativaException{
        tempoRealViagem(cliente);
        custoViagem();
        veiculo.diminuirAutonomiaAtual(this.distancia);
        veiculo.novaAvaliacao(avaliacaoVeiculo);
        cliente.novaAvaliacao(avaliacaoCliente);
        proprietario.novaAvaliacao(avaliacaoProprietario);  
    }
    
    public void alugaVeiculo (Cliente cliente) throws VeiculoNaoExisteException, AvaliacaoInvalidaException, DuracaoNegativaException{
        iniciaAluguer(cliente);
        terminaAluguer(cliente,proprietario,avaliacaoCliente,avaliacaoVeiculo,avaliacaoProprietario);
        this.veiculo = this.veiculo.clone();
    }*/
    
    
    public void iniciaAluguer(){
        
    }
}