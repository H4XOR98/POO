package ClassesBase;

import Exceptions.*;
import java.util.*;

public class Veiculo{
    
    // Variáveis de Instância
    
    private TipoVeiculo tipoVeiculo;
    private TipoCombustivel tipoCombustivel;
    private String marca;
    private String matricula;
    private int nif;
    private int velocidadeMedia;
    private double preco; // por Km
    private double consumo; // por Km
    private double autonomiaMax;
    private Ponto localizacao;
    private double autonomiaAtual;
    private double classificacao;
    private Collection<Double> classificacoes;
    
    // Construtores
    
    public Veiculo(){
        this.tipoVeiculo = TipoVeiculo.Carro;
        this.tipoCombustivel = TipoCombustivel.Gasolina;
        this.marca = "n/a";
        this.matricula = "n/a";
        this.nif = 0;
        this.velocidadeMedia = 0;
        this.preco = 0.0;
        this.consumo = 0.0;
        this.autonomiaMax = 0.0;
        this.localizacao = new Ponto();
        this.autonomiaAtual = 0.0;
        this.classificacao = 100.0;
        this.classificacoes = new ArrayList<>();
    }
    
    public Veiculo (TipoVeiculo tipoVeiculo,TipoCombustivel tipoCombustivel,String marca, String matricula, int nif, int velocidadeMedia, double preco, double consumo, 
                    double autonomiaMax, Ponto localizacao){
        this.tipoVeiculo = tipoVeiculo;
        this.tipoCombustivel = tipoCombustivel;
        this.marca = marca;
        this.matricula = matricula;
        this.nif = nif;
        this.velocidadeMedia = velocidadeMedia;
        this.preco = preco;
        this.consumo = consumo;
        this.autonomiaMax = autonomiaMax;
        setLocalizacao(localizacao);
        this.autonomiaAtual = autonomiaMax;
        this.classificacao = 100.0;
        this.classificacoes = new ArrayList<>();
    }
    
    public Veiculo (Veiculo veiculo){
        this.tipoVeiculo = veiculo.getTipoVeiculo();
        this.tipoCombustivel = veiculo.getTipoCombustivel();
        this.marca = veiculo.getMarca();
        this.matricula = veiculo.getMatricula();
        this.nif = veiculo.getNif();
        this.velocidadeMedia = veiculo.getVelocidadeMedia();
        this.preco = veiculo.getPreco();
        this.consumo = veiculo.getConsumo();
        this.autonomiaMax = veiculo.getAutonomiaMax();
        this.localizacao = veiculo.getLocalizacao();
        this.autonomiaAtual = veiculo.getAutonomiaAtual();
        this.classificacao = veiculo.getClassificacao();
        this.classificacoes = veiculo.getClassificacoes();
    }
    
    
    // Gets
    
    public TipoVeiculo getTipoVeiculo(){
        return this.tipoVeiculo;
    }
    
    public TipoCombustivel getTipoCombustivel(){
        return this.tipoCombustivel;
    }
    
    public String getMarca(){
        return this.marca;
    }
    
    public String getMatricula(){
        return this.matricula;
    }
    
    public int getNif(){
        return this.nif;
    }
    
    public int getVelocidadeMedia(){
        return this.velocidadeMedia;
    }
    
    public double getPreco(){
        return this.preco;
    }
    
    public double getConsumo(){
        return this.consumo;
    }
    
    public double getAutonomiaMax(){
        return this.autonomiaMax;
    }
    
    public Ponto getLocalizacao(){
        return this.localizacao;
    }
    
    public double getAutonomiaAtual(){ 
        return this.autonomiaAtual;
    }
    
    public double getClassificacao(){
        return this.classificacao;
    }
    
    public Collection<Double> getClassificacoes(){
        Collection<Double> aux = new ArrayList<>();
        for(double i : this.classificacoes){
            aux.add(i);
        }
        return aux;
    }
    
    // Sets
    
    public void setTipoVeiculo(TipoVeiculo newTipoVeiculo){
        this.tipoVeiculo = newTipoVeiculo;
    }
    
    public void setTipoCombustivel(TipoCombustivel newTipoCombustivel){
        this.tipoCombustivel = newTipoCombustivel;
    }
    
    public void setMarca (String newMarca){
        this.marca = newMarca;
    }
    
    public void setMatricula (String newMatricula){
        this.matricula = newMatricula;
    }
   
    public void setNif(int newNif){
        this.nif = newNif;
    }
    
    public void setVelocidadeMedia(int newVelocidadeMedia){
        this.velocidadeMedia = newVelocidadeMedia;
    }
    
    public void setPreco(double newPreco){
        this.preco = newPreco;
    }
    
    public void setConsumo(double newConsumo){
        this.consumo = newConsumo;
    }
    
    public void setAutonomiaMax(double newAutonomiaMax){
        this.autonomiaMax = newAutonomiaMax;
    }

    public void setLocalizacao (Ponto newLocalizacao){
        this.localizacao = newLocalizacao.clone();
    }
    
    public void setAutonomiaAtual(double newAutonomiaAtual){ 
        this.autonomiaAtual = newAutonomiaAtual;
    }
    
    public void setClassificacao (int newClassificacao){
        this.classificacao = newClassificacao;
    }
    
    public void setClassificacoes(Collection<Double> classificacoes){
        this.classificacoes = new ArrayList<>();
        for(double i : classificacoes){
            this.classificacoes.add(i);
        }
    }

    // Clone
    
    public Veiculo clone(){
        return new Veiculo(this);
    }
        
    // Equals
    
    public boolean equals (Object o){
        if (this == o) {
            return true;
        }
        if((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }
        Veiculo v = (Veiculo) o;
        return (this.tipoVeiculo.equals(v.getTipoVeiculo()) && this.tipoCombustivel.equals(v.getTipoCombustivel()) && this.marca.equals(v.getMarca()) && 
                this.matricula.equals(v.getMatricula()) && this.nif == v.getNif() && this.velocidadeMedia == v.getVelocidadeMedia() && this.preco == v.getPreco() &&
                this.consumo == v.getConsumo() && this.autonomiaMax == v.getAutonomiaMax() && this.localizacao.equals(v.getLocalizacao()) && 
                this.autonomiaAtual == v.getAutonomiaAtual() && this.classificacao == v.getClassificacao() && this.classificacoes.equals(v.getClassificacoes()));
    }
    
    // toString

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("------------ " + this.tipoVeiculo + " \n");
        sb.append("Tipo Combustivel: " + this.tipoCombustivel + ";\n");
        sb.append("Marca: " + this.marca + ";\n");
        sb.append("Matricula: " + this.matricula + ";\n");
        sb.append("NIF Proprietário: " + this.nif + ";\n");
        sb.append("Velocidade Media: " + this.velocidadeMedia + " km/h;\n");
        sb.append("Preco: " + this.preco + " €/km;\n");
        sb.append("Consumo: " + this.consumo + " L/km;\n");
        sb.append("Autonomia Maxima: " + this.autonomiaMax + " L;\n");
        sb.append(this.localizacao.toString() + "\n");
        sb.append("Autonomia Atual: " + this.autonomiaAtual + " L;\n");
        sb.append("Classificação: " + this.classificacao + ".\n");
        return sb.toString();
    }
    
    
    //Metodos
    public void abastecerVeiculo(){
        setAutonomiaAtual(this.autonomiaMax);
    }
    
    public double quantidadeCombustivel(){
        return this.autonomiaAtual;
    }
    
    public void alteraPrecoKm(double preco){
        setPreco(preco);
    }
    
    public void novaAvaliacao(double avaliacao) throws AvaliacaoInvalidaException{
        if(avaliacao < 0 || avaliacao > 100){
            throw new AvaliacaoInvalidaException("" + avaliacao);
        }
        this.classificacao = 0;
        this.classificacoes.add(avaliacao);
        for(double n : this.classificacoes){
            this.classificacao += n;
        }
        this.classificacao /=  this.classificacoes.size();
    }
}
