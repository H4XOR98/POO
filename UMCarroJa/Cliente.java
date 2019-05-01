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
    private double destreza;
    private Collection<Integer> classificacoes;
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
        this.destreza = 100;
        this.classificacoes = new HashSet<>();
    }


    public Cliente(String email, String password, String nome, int nif, String morada, LocalDate dataNascimento, Ponto localizacao, double destreza, Collection<Integer> classificacoes){
        super(email, password, nome, nif, morada, dataNascimento);
        setLocalizacao(localizacao);
        this.destreza = destreza;
        setClassificacoes(classificacoes);
    }
    
    public Cliente(Cliente cliente){
        super(cliente);
        this.localizacao = cliente.getLocalizacao();
        this.destreza = cliente.getDestreza();
        this.classificacoes = cliente.getClassificacoes();
    }
    
    
    public Ponto getLocalizacao(){
        return this.localizacao.clone();
    }

    public double getDestreza() {
        return this.destreza;
    }

    public Collection<Integer> getClassificacoes() {
        Collection<Integer>  aux = new HashSet<>();
        for(int i : this.classificacoes){
            aux.add(i);
        }
        return aux;
    }

    public void setLocalizacao(Ponto localizacao){
        this.localizacao = localizacao.clone();
    }

    public void setDestreza(double destreza) {
        this.destreza = destreza;
    }

    public void setClassificacoes(Collection<Integer> classificacoes) {
        this.classificacoes = new HashSet<>();
        for(int i : classificacoes){
            this.classificacoes.add(i);
        }
    }

    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }

        Cliente cliente = (Cliente)o;

        return super.equals(cliente) && this.localizacao == cliente.getLocalizacao() && this.destreza == cliente.getDestreza() && this.classificacoes.equals(cliente.getClassificacoes());
    }



    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(this.localizacao.toString());
        sb.append("Destreza: " + this.destreza + ";\n");
        return sb.toString();
    }


    public Cliente clone(){
        return new Cliente(this);
    }
}
