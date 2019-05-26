package MyLogic.ClassesBase;

import MyLogic.Exceptions.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.Serializable;
/**
 * Escreva a descrição da classe Cliente aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Cliente extends Utilizador implements Serializable
{
    //variaveis de instancia
    private Ponto localizacao;
    private double destreza;
    private List<Double> classificacoes;
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
        this.destreza = 100.0;
        this.classificacoes = new ArrayList<>();
    }


    public Cliente(String email, String password, String nome, int nif, String morada, Ponto localizacao){
        super(email, password, nome, nif, morada);
        setLocalizacao(localizacao);
        this.destreza = 100.0;
        this.classificacoes = new ArrayList<>();
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

    public List<Double> getClassificacoes() {
        List<Double> aux = new ArrayList<>();
        for(double i : this.classificacoes){
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

    public void setClassificacoes(List<Double> classificacoes) {
        this.classificacoes = new ArrayList<>();
        for(double i : classificacoes){
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

        return super.equals(cliente);
    }



    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("----------- Cliente -----------\n");
        sb.append(super.toString());
        sb.append(this.localizacao.toString() + ";\n");
        sb.append("Destreza: " + this.destreza + ";\n");
        return sb.toString();
    }


    public Cliente clone(){
        return new Cliente(this);
    }
    
    //-------------
    public void novaAvaliacao(double avaliacao) throws AvaliacaoInvalidaException{
        if(avaliacao < 0 || avaliacao > 100){
            throw new AvaliacaoInvalidaException("");
        }
        this.destreza = 0;
        this.classificacoes.add(avaliacao);
        for(double n : this.classificacoes){
            this.destreza += n;
        }
        this.destreza /=  this.classificacoes.size();
    }
}
