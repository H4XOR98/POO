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
    }
    
    /**
     * Construtor parametrizado de Proprietario.
     * Aceita como parametros o email, o nome, a password,
     * a morada e a data de nascimento de um Proprietario.
     */
    public Proprietario(String email, String password, String nome, int nif, String morada, LocalDate dataNascimento, double classificacao){
        super(email, password, nome, nif, morada, dataNascimento);
        this.classificacao = classificacao;
    }
    
    
    public Proprietario(Proprietario proprietario){
        super(proprietario);
        this.classificacao = proprietario.getClassificacao();
    }

    
    public double getClassificacao(){
        return this.classificacao;
    }
    
    public void getClassificacao(double classificacao){
        this.classificacao = classificacao;
    }
    
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        
        Proprietario p = (Proprietario)o;
        
        return super.equals(p) && this.classificacao == p.getClassificacao();
    }
    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Classificacao:   " + this.classificacao + ";\n");
        return sb.toString();
    }
    
    
    public Proprietario clone(){
        return new Proprietario(this);
    }
}
