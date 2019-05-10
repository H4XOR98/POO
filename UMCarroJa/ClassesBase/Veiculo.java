package ClassesBase;

import Exceptions.*;
import java.util.*;

public abstract class Veiculo{
    
    // Variáveis de Instância
    
    private TipoVeiculo tipoVeiculo;
    private String marca;
    private String matricula;
    private int nif;
    private Ponto localizacao;
    private double preco;
    private double numTotalKms;
    private double classificacao;
    private Collection<Double> classificacoes;
    
    // Construtores
    
    public Veiculo(){
        this.tipoVeiculo = TipoVeiculo.Carro;
        this.marca = "n/a";
        this.matricula = " ";
        this.nif = 0;
        this.localizacao = new Ponto();
        this.preco = 0;
        this.numTotalKms = 0;
        this.classificacao = 0;
        this.classificacoes = new ArrayList<>();
    }
    
    public Veiculo (TipoVeiculo tipoVeiculo, String marca, String matricula, int nif, Ponto localizacao, double preco, double numTotalKms, double classificacao, 
                    Collection<Double> classificacoes){
        this.tipoVeiculo = tipoVeiculo;
        this.marca= marca;
        this.matricula = matricula;
        this.nif = nif;
        setLocalizacao(localizacao);
        this.preco = preco;
        this.numTotalKms = numTotalKms;
        this.classificacao = classificacao;
        setClassificacoes(classificacoes);
    }
    
    public Veiculo (Veiculo veiculo){
        this.tipoVeiculo = veiculo.getTipoVeiculo();
        this.marca = veiculo.getMarca();
        this.matricula = veiculo.getMatricula();
        this.nif = veiculo.getNif();
        this.localizacao = veiculo.getLocalizacao();
        this.preco = veiculo.getPreco();
        this.numTotalKms = veiculo.getNumTotalKms();
        this.classificacao = veiculo.getClassificacao();
        this.classificacoes = veiculo.getClassificacoes();
    }
    
    
    // Gets
    
    public TipoVeiculo getTipoVeiculo(){
        return this.tipoVeiculo;
    }
    
    public String getMarca(){
        return this.matricula;
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
