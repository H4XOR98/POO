package MyLogic.ClassesBase;

import MyLogic.Exceptions.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
public class Aluguer{
    
    // Variáveis de Instância
    
    private TipoVeiculo tipoVeiculo;
    private int nif;
    private Ponto destino;
    private TipoCombustivel tipoCombustivel;
    private PreferenciaAluguer preferencia;
    private Veiculo veiculo;
    private EstadoAluguer estadoAluguer;
    private double custo;
    private double distancia;
    private double duracao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Metereologia meteo;
    private Trafego trafego;

    private static int id = 0;
    
    // Construtores
    
    public Aluguer() {
        this.tipoVeiculo = TipoVeiculo.Carro;
        this.nif = 0;
        this.destino = new Ponto();
        this.tipoCombustivel = TipoCombustivel.Gasolina;
        this.preferencia = PreferenciaAluguer.MaisPerto;
        this.veiculo = new Veiculo();
        this.estadoAluguer = EstadoAluguer.Espera;
        this.custo = 0;
        this.distancia = 0;
        this.duracao = 0;
        this.dataInicio = LocalDateTime.now();
        this.dataFim = LocalDateTime.now();
        this.meteo = Metereologia.getRandom();
        this.trafego = Trafego.getRandom();
        id++;
    }

    public Aluguer(TipoVeiculo tipoVeiculo, int nif, Ponto destino, TipoCombustivel tipoCombustivel, PreferenciaAluguer preferencia) {
        this.tipoVeiculo = tipoVeiculo;
        this.nif = nif;
        this.destino = destino;
        this.tipoCombustivel = tipoCombustivel;
        this.preferencia = preferencia;
        this.veiculo = new Veiculo();
        this.estadoAluguer = EstadoAluguer.Espera;
        this.custo = 0;
        this.distancia = 0;
        this.duracao = 0;
        this.dataInicio = LocalDateTime.now();
        this.dataFim = LocalDateTime.now();
        this.meteo = Metereologia.getRandom();
        this.trafego = Trafego.getRandom();
        id++;
    }

    public Aluguer(Aluguer a){
        this.tipoVeiculo = a.getTipoVeiculo();
        this.nif = a.getNif();
        this.destino = a.getDestino();
        this.tipoCombustivel = a.getTipoCombustivel();
        this.preferencia = a.getPreferencia();
        this.veiculo = a.getVeiculo();
        this.estadoAluguer = a.getEstadoAluguer();
        this.custo = a.getCusto();
        this.distancia = a.getDistancia();
        this.duracao = a.getDuracao();
        this.dataInicio = a.getDataInicio();
        this.dataFim = a.getDataFim();
        this.meteo = a.getMeteorologia();
        this.trafego = a.getTrafego();
    }

    // Gets
    
    public TipoVeiculo getTipoVeiculo() {
        return this.tipoVeiculo;
    }
    
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
    
    public LocalDateTime getDataInicio(){
        return this.dataInicio;
    }
    
    public LocalDateTime getDataFim(){
        return this.dataFim;
    }
    
    public Metereologia getMeteorologia() {
        return this.meteo;
    }
    
    public Trafego getTrafego() {
        return this.trafego;
    }
    
    // Sets

    
    public void setTipoVeiculo(TipoVeiculo tipoveiculo){
        this.tipoVeiculo = tipoVeiculo;
    }
    
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
    
    
    //Get de classe
    
    public static int getId() {
        return id;
    }
    
    
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##");
        StringBuilder sb = new StringBuilder();
        sb.append("............ALUGUER............\n");
        sb.append("ID: " + id + ";\n");
        sb.append("Destino: \n\t" + this.destino.toString() + ";\n");
        sb.append("Nif Cliente: " + this.nif + ";\n");
        sb.append("Tipo de Combustivel: " + this.tipoCombustivel + ";\n");
        sb.append("Preferencia: " + this.preferencia + ";\n");
        sb.append("Tipo de Veiculo: " + this.tipoVeiculo + ";\n");
        sb.append(this.veiculo.toString());
        sb.append("Estado Aluguer: " + this.estadoAluguer + ";\n");
        sb.append("Custo: " + df.format(this.custo) + "€;\n");
        sb.append("Distancia: " +  df.format(this.distancia) + " km;\n");
        sb.append("Datas : \n");
        sb.append("\tInicio: " + this.dataInicio + ";\n");
        sb.append("\tFim: " + this.dataFim.toString() + ";\n");
        sb.append("Duracao: " +  df.format(this.duracao) + " horas;\n");
        sb.append("Metereologia: " + this.meteo + ";\n");
        sb.append("Trafego: " + this.trafego.toString() + ";\n");
        sb.append("...............................\n");
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
    
    private double converteEmHoras(double duracao){
        int horas = (int)Math.floor(duracao);
        double minutos = (duracao - horas) * 0.6;
        return (horas + minutos);
    }
    
    private double tempoClienteVeiculo(Cliente cliente){
        double distancia = cliente.getLocalizacao().distancia(this.veiculo.getLocalizacao());
        return converteEmHoras(distancia / 4);
    }
    
    
    private void distanciaVeiculoDestino(Cliente cliente){
        this.distancia = this.veiculo.getLocalizacao().distancia(this.destino);
    }
    
    private void tempoViagemReal (Cliente cliente){
        distanciaVeiculoDestino(cliente);
        this.duracao = this.distancia/this.veiculo.getVelocidadeMedia();
        this.duracao *= 2 - (cliente.getDestreza()/100);
        this.duracao *= 100 - (this.veiculo.getClassificacao()/100);
        this.duracao *= this.meteo.getPercentagem();
        this.duracao *= this.trafego.getPercentagem();
        this.dataInicio = LocalDateTime.now();
        this.duracao = converteEmHoras(this.duracao);
        this.dataFim = LocalDateTime.now().plusMinutes((long)this.duracao * 60);
    }
    
    private void custoViagem(){
        this.custo = this.distancia * this.veiculo.getPreco();
    }
    
    
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
    
    //Cliente
    public Notificacao pedidoAluguer(Cliente cliente){
        DecimalFormat df = new DecimalFormat("#.##");
        double duracao = tempoClienteVeiculo(cliente);
        Notificacao n = new Notificacao(this.veiculo.getNif(), "Pedido de Aluguer", "\nAluguer numero : " +  id + ";\n" + "O cliente com o nif " + cliente.getNif() +
                                                               " pretende alugar o veiculo com a matricula " + this.veiculo.getMatricula() + 
                                                               ".\nO cliente demora cerca de " +  df.format(duracao) + " a chegar ao veiculo.\n");
        return n;
    }
    
    //Cliente
    public Notificacao efetuaViagem(Cliente cliente) throws AluguerNaoExisteException{
        if(!estadoAluguer.equals(EstadoAluguer.Aceite)){
            throw new AluguerNaoExisteException("Este aluguer ainda não foi aceite!\n");
        }
        Notificacao n = null;
        this.tempoViagemReal(cliente);
        this.veiculo.diminuirAutonomiaAtual(this.distancia);
        this.estadoAluguer = EstadoAluguer.Terminado;
        if(this.veiculo.autonomiaBaixa()){
            n = new Notificacao(this.veiculo.getNif(),"Abastecer veiuculo","\nO veiculo com a matricula " + this.veiculo.getMatricula() + 
                                            " deve ser abastecido!\n");
        }
        return n;
    }
    
    //proprietario
    public void registaCusto() throws AluguerNaoExisteException{
        if(!estadoAluguer.equals(EstadoAluguer.Terminado)){
            throw new AluguerNaoExisteException("Este aluguer não foi terminado!\n");
        }
        custoViagem();
    }
}