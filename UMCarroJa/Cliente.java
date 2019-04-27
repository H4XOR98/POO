import java.util.*;
import java.time.LocalDate;
/**
 * Escreva a descrição da classe Cliente aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Cliente extends Utilizador
{
    //variaveis de instancia
    private Ponto localizacao;
    
    /**
     * Construtores da classe Ponto.
     * Declaracao dos construtores por omissao (vazio),
     * parametrizado e de copia.
     */
  
    /**
     * Construtor por omissao de Ponto.
     */
    public Cliente(){
        super();
        this.localizacao = new Ponto();
    }
    
    public Cliente(String email, String password, String nome, int nif, String morada, LocalDate dataNascimento, Ponto localizacao){
        super(email, password, nome, nif, morada, dataNascimento);
        setLocalizacao(localizacao);
    }
    
    public Cliente(Cliente c){
        super(c);
        this.localizacao = c.getLocalizacao();
    }
    
    
    public Ponto getLocalizacao(){
        return this.localizacao.clone();
    }
    
    
    public void setLocalizacao(Ponto localizacao){
        this.localizacao = localizacao.clone();
    }
    
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        
        Cliente c = (Cliente)o;
        
        return super.equals(c) && this.localizacao == c.getLocalizacao();
    }
    
    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(this.localizacao.toString());
        return sb.toString();
    }
    
    
    public Cliente clone(){
        return new Cliente(this);
    }
}
