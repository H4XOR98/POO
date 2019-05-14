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
    private double duracao;
    private Metereologia meteo;
    private Trafego trafego;

    // Construtores
    
    public Aluguer() {
        this.nif = 0;
        this.destino = new Ponto();
        this.tipoCombustivel = TipoCombustivel.Gasolina;
        this.preferencia = PreferenciaAluguer.MaisPerto;
        this.veiculo = new Veiculo();
        this.estadoAluguer = EstadoAluguer.Espera;
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
        this.estadoAluguer = EstadoAluguer.Espera;
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

    // Gets

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
    
    public double getDuracao() {
        return this.duracao;
    }
    
    public Metereologia getMeteorologia() {
        return this.meteo;
    }
    
    public Trafego getTrafego() {
        return this.trafego;
    }
    
    // Sets

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
    
    public void setDuracao(int duracao) {
        this.duracao = duracao;
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
        sb.append("Duracao Real: " + this.duracao + " horas;\n");
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
               this.duracao == a.getDuracao() && this.meteo.equals(a.getMeteorologia()) && this.trafego.equals(a.getTrafego()); 
    }
   
    
    public Aluguer clone(){
        return new Aluguer(this);
    }   
    
    //------------------------------------
    
    private double converteEmHoras(double duracao) throws DuracaoNegativaException{
        if(duracao < 0){
            throw new DuracaoNegativaException("Ups! Duracao negativa não é aceite pelo Sistema.\n");
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
        return distancia * this.veiculo.getPreco();
    }
    
    //--- Proprietario, passar na notificacao
    public double tempoClienteVeiculo(Cliente cliente) throws DuracaoNegativaException{
        double tempo = cliente.getLocalizacao().distancia(this.veiculo.getLocalizacao());
        tempo /= 4;
        tempo = converteEmHoras(tempo);
        return tempo;
    }
    
    ////-----------------------------------------------------------------------------------------
    
    //--Cliente
    private void tempoViagemReal (Cliente cliente) throws DuracaoNegativaException{
        this.duracaoReal = cliente.getLocalizacao().distancia(this.veiculo.getLocalizacao());
        this.duracaoReal /= 4;
        this.duracaoReal *= (100 - cliente.getDestreza())/100;
        this.duracaoReal *= (100 - this.veiculo.getClassificacao())/100;
        this.duracaoReal *= this.meteo.getPercentagem();
        this.duracaoReal *= this.trafego.getPercentagem();
        this.duracaoReal = converteEmHoras(this.duracaoReal);
    }
    
    public void distanciaViagem(Cliente cliente){
        this.distancia = this.veiculo.getLocalizacao().distancia(cliente.getLocalizacao());
    }
    
    private void tempoRealViagem(Cliente cliente) throws DuracaoNegativaException{
        this.duracaoReal = this.duracaoEstimada;
        this.duracaoReal *= (100 - cliente.getDestreza())/100;
        this.duracaoReal *= (100 - this.veiculo.getClassificacao())/100;
        this.duracaoReal *= this.meteo.getPercentagem();
        this.duracaoReal *= this.trafego.getPercentagem();
        this.duracaoReal = converteEmHoras(this.duracaoReal);
    }*/
    
    
    /*private void iniciaAluguer(Cliente cliente) throws VeiculoNaoExisteException, DuracaoNegativaException{
        this.distancia = this.veiculo.getLocalizacao().distancia(this.destino);
        if(this.distancia > this.veiculo.getAutonomiaAtual()){
            throw new VeiculoNaoExisteException("Ups! A autonomia é insuficiente.");
        }
        tempoViagem(cliente);
        cliente.setLocalizacao(this.veiculo.getLocalizacao());
    }*/
    
    /*private void terminaAluguer(Cliente cliente, Proprietario proprietario, double avaliacaoCliente,double avaliacaoVeiculo,
                              double avaliacaoProprietario) throws VeiculoNaoExisteException, AvaliacaoInvalidaException, 
                              DuracaoNegativaException{
        tempoRealViagem(cliente);
        custoViagem();
        veiculo.diminuirAutonomiaAtual(this.distancia);
        veiculo.novaAvaliacao(avaliacaoVeiculo);
        cliente.novaAvaliacao(avaliacaoCliente);
        proprietario.novaAvaliacao(avaliacaoProprietario);  
    }*/
    
    /*public void alugaVeiculo (Cliente cliente, Veiculo veiculo,Proprietario proprietario, double avaliacaoCliente,double avaliacaoVeiculo,
                              double avaliacaoProprietario) throws VeiculoNaoExisteException, AvaliacaoInvalidaException, 
                              DuracaoNegativaException{
        this.veiculo = veiculo;
        iniciaAluguer(cliente);
        terminaAluguer(cliente,proprietario,avaliacaoCliente,avaliacaoVeiculo,avaliacaoProprietario);
        this.veiculo = this.veiculo.clone();
    }*/
    
    /*
     * Criar Instancia de Aluguer(Construtor parametrizado);
     * Buscar o cliente ao GestorUtilizadores;(getUtilizador(a.getNif())
     * Buscar o veiculo ao GestorVeiculos
     *      -retorna o custo Estimado do aluguer para ser possivel exibir ao cliente / IO;
     * Iniciar aluguer;
     * Enviar o pedido de aluguer ao Proprietaro(Tempo ClienteVeiculo)
     * Esperar Confirmacao do Proprietario
     * 
     * CONFIRMA:
     *      *Cliente:
     *          A viagem e realizada;
     *          Atualizar a autonomiaAtual do Veiculo;
     *          Calculo do tempo real da viagem;
     *          AvaliacaoDo Veiculo e do Proprietario
     *      *Proprietario:
     *          Registar o custo da Viagem
     *          Avaliacao do Cliente   
     *          
     * RECUSAR:
     *      Cliente: Envia notificacao;
     *  
     * Colocar no GestorAlugueres .|. NAO PODE SER ALTERADO
     */
    //Inicia Alguer 
    
    
    
}