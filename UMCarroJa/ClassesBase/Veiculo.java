package ClassesBase;

import Exceptions.*;
import java.util.*;

public abstract class Veiculo{
    
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
        this.classificacao = 0.0;
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
        this.classificacao = 0.0;
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
        this.autonomiaAtual = veiculo.getAutonomiaMax;
        this.classificacao = veiculo.getAutonomia();
        this.classificacoes = veiculo.getClassificacoes();
    }
    
    
    // Gets
    
    public TipoVeiculo getTipoVeiculo(){
        return this.tipoVeiculo;
    }
    
    public TipoVeiculo getTipoCombustivel(){
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
    
    public Ponto getLocalizacao(){
        return this.localizacao;
    }
    
    public double getPreco(){
        return this.preco;
    }
    
    public double getNumTotalKms(){
        return this.numTotalKms;
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
    
    public void setMarca (String newMarca){
        this.marca = newMarca;
    }
    
    public void setMatricula (String newMatricula){
        this.matricula = newMatricula;
    }
    
    public void setNif(int newNif){
        this.nif = newNif;
    }

    public void setLocalizacao (Ponto newLocalizacao){
        this.localizacao = newLocalizacao.clone();
    }
    
    public void setPreco (double newPreco){
        this.preco = newPreco;
    }
    
    public void setNumTotalKms (double newnumTotalKms){
        this.numTotalKms = newnumTotalKms;
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
    
    public abstract Veiculo clone();
        
    // Equals
    
    public boolean equals (Object o){
        if (this == o) {
            return true;
        }
        if((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }
        Veiculo v = (Veiculo) o;
        return (this.tipoVeiculo.equals(v.getTipoVeiculo()) && this.marca.equals(v.getMarca()) && this.matricula.equals(v.getMatricula()) &&
                this.nif == v.getNif() && this.localizacao.equals(v.getLocalizacao()) && this.preco == v.getPreco() && this.numTotalKms == v.getNumTotalKms() && 
                this.classificacao == v.getClassificacao() && this.classificacoes.equals(v.getClassificacoes()));
    }
    
    // toString

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo Veículo: " + this.tipoVeiculo + "\n");
        sb.append("Marca: " + this.marca + "\n");
        sb.append("Matricula: " + this.matricula + "\n");
        sb.append("NIF Proprietário: " + this.nif + "\n");
        sb.append(this.localizacao.toString() + "\n");
        sb.append("Preco: " + this.getPreco() + "€/Km\n"); 
        sb.append("Numero Total de Kms " + this.numTotalKms + "\n");
        sb.append("Classificação: " + this.classificacao + "\n");
        return sb.toString();
    }
    
    
    //Metodos
    public abstract void abastecerVeiculo();
    
    public abstract double quantidadeCombustivel();
    
    public void alteraPrecoKm(double preco){
        setPreco(preco);
    }
    
    public void novaAvaliacao(double avaliacao) throws AvaliacaoInvalidaException{
        if(avaliacao < 0 || avaliacao > 100){
            throw new AvaliacaoInvalidaException("" + avaliacao);
        }
        if(this.classificacoes.isEmpty()){
            this.classificacoes.add(this.classificacao);
        }
        this.classificacao = 0;
        this.classificacoes.add(avaliacao);
        for(double n : this.classificacoes){
            this.classificacao += n;
        }
        this.classificacao /=  this.classificacoes.size();
    }
}
