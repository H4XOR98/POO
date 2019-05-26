package MyLogic.ClassesBase;

import MyLogic.Exceptions.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.Serializable;
/**
 * Escreva a descrição da classe Proprietario aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Proprietario extends Utilizador implements Serializable
{
    //variaveis de instancia
    private double classificacao;
    private List<Double> classificacoes;
    
    
    /**
     * Construtores da classe Proprietario.
     * Declaracao dos construtores por omissao (vazio),
     * parametrizado e de copia.
     */
  
    /**
     * Construtor por omissao de Proprietario.
     */
    public Proprietario(){
        super();
        this.classificacao = 0;
        this.classificacoes = new ArrayList<>();
    }
    
    /**
     * Construtor parametrizado de Proprietario.
     * Aceita como parametros o email, o nome, a password,
     * a morada e a data de nascimento de um Proprietario.
     */
    public Proprietario(String email, String password, String nome, int nif, String morada){
        super(email, password, nome, nif, morada);
        this.classificacao = 100.0;
        this.classificacoes = new ArrayList<>();
    }
    
    
    public Proprietario(Proprietario proprietario){
        super(proprietario);
        this.classificacao = proprietario.getClassificacao();
        this.classificacoes = proprietario.getClassificacoes();
    }

    // Gets
    
    public double getClassificacao(){
        return this.classificacao;
    }
    
    public List<Double> getClassificacoes(){
        List<Double> aux = new ArrayList<Double>();
        for (double i : this.classificacoes){
            aux.add(i);
        }
        return aux;
    }
    
    // Sets
    
    public void setClassificacao (double classificacao){
        this.classificacao = classificacao;
    }
    
    public void setClassificacoes (List<Double> newClassificacoes){
        this.classificacoes = new ArrayList<Double>();
        for(double i : newClassificacoes){
            this.classificacoes.add(i);
        }
    }
    
    // Equals
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        
        Proprietario p = (Proprietario)o;
        
        return super.equals(p);
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("----------- Proprietario -----------\n");
        sb.append(super.toString());
        sb.append("Classificacao:   " + this.classificacao + ";\n");
        return sb.toString();
    }
    
    // Clone
    
    public Proprietario clone(){
        return new Proprietario(this);
    }
    
    
    //-----------------------------------------------------
    
    public void novaAvaliacao(double avaliacao) throws AvaliacaoInvalidaException{
        if(avaliacao < 0 || avaliacao > 100){
            throw new AvaliacaoInvalidaException("");
        }
        this.classificacao = 0;
        this.classificacoes.add(avaliacao);
        for(double n : this.classificacoes){
            this.classificacao += n;
        }
        this.classificacao /=  this.classificacoes.size();
    }
    
    public void abasteceVeiculo(Veiculo veiculo) throws VeiculoNaoPertenceException{
        if(veiculo.getNif() != this.getNif()){
            throw new VeiculoNaoPertenceException(veiculo.getMatricula());
        }
        veiculo.abastecerVeiculo();
    }
    
    public void alteraPrecoVeiculo(Veiculo veiculo, double preco) throws VeiculoNaoPertenceException{
        if(veiculo.getNif() != this.getNif()){
            throw new VeiculoNaoPertenceException(veiculo.getMatricula());
        }
        veiculo.setPreco(preco);
    }
    
}
