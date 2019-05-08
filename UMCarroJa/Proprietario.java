import java.util.*;
import java.time.LocalDate;
/**
 * Escreva a descrição da classe Proprietario aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Proprietario extends Utilizador
{
    //variaveis de instancia
    private double classificacao;
    private Collection <Integer> classificacoes;
    private Veiculos meusVeiculos;
    
    
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
        this.classificacoes = new HashSet<>();
    }
    
    /**
     * Construtor parametrizado de Proprietario.
     * Aceita como parametros o email, o nome, a password,
     * a morada e a data de nascimento de um Proprietario.
     */
    public Proprietario(String email, String password, String nome, int nif, String morada, LocalDate dataNascimento, 
                        double classificacao, Collection<Integer> classificacoes){
        super(email, password, nome, nif, morada, dataNascimento);
        this.classificacao = classificacao;
        setClassificacoes(classificacoes);
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
    
    public Collection<Integer> getClassificacoes(){
        Collection<Integer> aux = new HashSet<Integer>();
        for (Integer i : this.classificacoes){
            aux.add(i);
        }
        return aux;
    }
    
    // Sets
    
    public void setClassificacao (double classificacao){
        this.classificacao = classificacao;
    }
    
    public void setClassificacoes (Collection<Integer> newClassificacoes){
        this.classificacoes = new HashSet<Integer>();
        for(Integer i : newClassificacoes){
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
        
        return super.equals(p) && this.classificacao == p.getClassificacao() && this.classificacoes.equals(p.getClassificacoes());
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Classificacao:   " + this.classificacao + ";\n");
        return sb.toString();
    }
    
    // Clone
    
    public Proprietario clone(){
        return new Proprietario(this);
    }
    
    
    //-----------------------------------------------------
    
    
}
