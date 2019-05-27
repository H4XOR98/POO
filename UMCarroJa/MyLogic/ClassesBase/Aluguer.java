package MyLogic.ClassesBase;

import MyLogic.Exceptions.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.io.Serializable;

public class Aluguer implements Serializable{
    
    // Variáveis de Instância
    
    private int id;
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

    private static int identidade = 0;
    
    // Construtores
    
    public Aluguer() {
        identidade++;
        this.id = identidade;
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
    }

    public Aluguer(TipoVeiculo tipoVeiculo, int nif, Ponto destino, TipoCombustivel tipoCombustivel, PreferenciaAluguer preferencia) {
        identidade++;
        this.id = identidade;
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
    }

    public Aluguer(Aluguer a){
        this.id = a.getId();
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
    
    public int getId(){
        return this.id;
    }
    
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
    
    // Get de classe
    
    public static int getIdentidade() {
        return identidade;
    }
    
    // toString
    
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.##");
        StringBuilder sb = new StringBuilder();
        sb.append("............ALUGUER............\n");
        sb.append("ID: " + this.id + ";\n");
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
    
    // Equals
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        Aluguer a = (Aluguer)o;
        /*return this.nif == a.getNif() && this.destino.equals(a.getDestino()) && this.tipoCombustivel.equals(a.getTipoCombustivel()) && 
               this.preferencia.equals(a.getPreferencia()) && this.tipoVeiculo.equals(a.getTipoVeiculo()) &&  this.veiculo.equals(a.getVeiculo()) &&
               this.estadoAluguer.equals(a.getEstadoAluguer()) && this.custo == a.getCusto() && this.distancia == a.getDistancia() && 
               this.duracao == a.getDuracao() && this.meteo.equals(a.getMeteorologia()) && this.trafego.equals(a.getTrafego());*/
        return this.id == a.getId();
    }
   
    // Equals
    
    public Aluguer clone(){
        return new Aluguer(this);
    }   
    
    // ------------------------------------ Métodos ------------------------------------
    
    // Converte a duração para horas
    
    public double converteEmHoras(double duracao){
        int horas = (int)Math.floor(duracao);
        double minutos = (duracao - horas) * 0.6;
        return (horas + minutos);
    }
    
    // Tempo do cliente ao veículo
    
    private double tempoClienteVeiculo(Cliente cliente){
        double distancia = cliente.getLocalizacao().distancia(this.veiculo.getLocalizacao());
        return converteEmHoras(distancia / 4);
    }
    
    // Distância do veículo ao destino
    
    private void distanciaVeiculoDestino(){
        this.distancia = this.veiculo.getLocalizacao().distancia(this.destino);
    }
    
    // Tempo real de viagem
    
    private void tempoViagemReal (Cliente cliente){
        distanciaVeiculoDestino();
        this.duracao = this.distancia/this.veiculo.getVelocidadeMedia();
        this.duracao *= 2 - (cliente.getDestreza()/100);
        this.duracao *= 2 - (this.veiculo.getClassificacao()/100);
        this.duracao *= this.meteo.getPercentagem();
        this.duracao *= this.trafego.getPercentagem();
        this.duracao = converteEmHoras(this.duracao);
        int horas = (int)Math.floor(this.duracao);
        int minutos = (int)((this.duracao - horas) * 100);
        this.dataFim = this.dataInicio.plusHours((long)horas);
        this.dataFim = this.dataFim.plusMinutes((long)minutos);
    }
    
    // Custo de viagem
    
    private void custoViagem(){
        this.custo = this.distancia * this.veiculo.getPreco();
    }
    
    // Efetua uma viagem lida do ficheiro
    
    public void efetuaViagemFile (Cliente cliente) throws AluguerNaoExisteException{
        if(!estadoAluguer.equals(EstadoAluguer.Aceite)){
            throw new AluguerNaoExisteException("Este aluguer ainda não foi aceite!\n");
        }
        this.tempoViagemReal(cliente);
        this.estadoAluguer = EstadoAluguer.Terminado;
    }
    
    // Cliente
    
    public Notificacao pedidoAluguer(Cliente cliente){
        DecimalFormat df = new DecimalFormat("#.##");
        double duracao = tempoClienteVeiculo(cliente);
        this.estadoAluguer = EstadoAluguer.Espera;
        Notificacao n = new Notificacao(this.veiculo.getNif(), "Pedido de Aluguer", "\nAluguer numero : " +  this.id + ";\n" + "O cliente com o nif " + cliente.getNif() +
                                                               " pretende alugar o veiculo com a matricula " + this.veiculo.getMatricula() + 
                                                               ".\nO cliente demora cerca de " +  df.format(duracao) + " a chegar ao veiculo.\n");
        return n;
    }
    
    public Notificacao efetuaViagem(Cliente cliente) throws AluguerNaoExisteException{
        if(!estadoAluguer.equals(EstadoAluguer.Aceite)){
            throw new AluguerNaoExisteException("Este aluguer ainda não foi aceite!\n");
        }
        Notificacao n = null;
        this.tempoViagemReal(cliente);
        this.veiculo.diminuirAutonomiaAtual(this.distancia);
        this.estadoAluguer = EstadoAluguer.Terminado;
        cliente.setLocalizacao(this.destino);
        this.veiculo.setLocalizacao(this.destino);
        if(this.veiculo.autonomiaBaixa()){
            n = new Notificacao(this.veiculo.getNif(),"Abastecer veiuculo","\nO veiculo com a matricula " + this.veiculo.getMatricula() + 
                                            " deve ser abastecido!\n");
        }
        return n;
    }
    
    public Notificacao terminaViagemCliente (Cliente cliente){
        Notificacao n = new Notificacao(this.veiculo.getNif(), "Fim de Aluguer", "\nAluguer numero : " +  this.id + ";\n" + "O cliente com o nif " + 
                                                                cliente.getNif() + " terminou a viagem.");
        return n;
    }

    // Proprietário
    
    public void registaCusto() throws AluguerNaoExisteException{
        if(!estadoAluguer.equals(EstadoAluguer.Terminado)){
            throw new AluguerNaoExisteException("Este aluguer não foi terminado!\n");
        }
        this.custoViagem();
    }
    
    // Aceita Aluguer
    
    public Notificacao aceitaAluguer() throws AluguerNaoEmEsperaException{
        if(!this.estadoAluguer.equals(EstadoAluguer.Espera)){
            throw new AluguerNaoEmEsperaException("");
        }
        this.setEstadoAluguer(EstadoAluguer.Aceite);
        Notificacao n = new Notificacao (this.nif,"Aluguer Aceite","O seu pedido de aluguer com o id " + this.id + 
                            " foi aceite.\nO veiculo com a matricula "+ this.veiculo.getMatricula() + " está à sua espera.\n\nBoa Viagem.");
        return n;
    }
    
    // Rejeita Aluguer
    
    public Notificacao rejeitaAluguer() throws AluguerNaoEmEsperaException{
        if(!this.estadoAluguer.equals(EstadoAluguer.Espera)){
            throw new AluguerNaoEmEsperaException("");
        }
        this.setEstadoAluguer(EstadoAluguer.Rejeitado);
        Notificacao n = new Notificacao (this.nif,"Aluguer Rejeitado","O seu pedido de aluguer com o id " + this.id + 
                                         " foi rejeitado.\n\nPedimos desculpa pelo incómodo.");
        return n;
    }
    
    // Avaliar
    
    public Proprietario avaliacoesCliente(Proprietario proprietario, double notaVeiculo, double notaProprietario) 
    throws AluguerNaoExisteException, AvaliacaoInvalidaException{
        if(!estadoAluguer.equals(EstadoAluguer.Terminado)){
            throw new AluguerNaoExisteException("Este aluguer não foi terminado!\n");
        }
        proprietario.novaAvaliacao(notaProprietario);
        this.veiculo.novaAvaliacao(notaVeiculo);
        return proprietario;
    }
    
    public Cliente avaliacaoProprietario (Cliente cliente, double notaCliente) 
    throws AluguerNaoExisteException, AvaliacaoInvalidaException{
        if(!estadoAluguer.equals(EstadoAluguer.Terminado)){
            throw new AluguerNaoExisteException("Este aluguer não foi terminado!\n");
        }
        cliente.novaAvaliacao(notaCliente);
        return cliente;
    }
}