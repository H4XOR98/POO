package Gestores;

import ClassesBase.*;
import java.util.*;
/**
 * Escreva a descrição da classe Notificacoes aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Notificacoes
{
    private Collection<Notificacao> notificacoes;
    
    
    // Construtores
    
    public Notificacoes(){
        this.notificacoes = new ArrayList<>();
    }
    
    public Notificacoes(Notificacoes notificacoes){
        this.notificacoes = notificacoes.getNotificacoes();
    }
    
    public Notificacoes(Collection<Notificacao> notificacoes){
        setNotificacoes(notificacoes);
    }
    
    // Gets
    
    public Collection<Notificacao> getNotificacoes(){
        Collection<Notificacao> aux = new ArrayList<>();
        this.notificacoes.forEach((n -> aux.add(n.clone())));
        return aux;
    }
    
    // Sets
    
    public void setNotificacoes(Collection<Notificacao> notificacoes){
        this.notificacoes = new ArrayList<>();
        notificacoes.forEach((n -> this.notificacoes.add(n.clone())));
    }
    
    
    // Equals
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        Notificacoes n = (Notificacoes)o;
        return this.notificacoes.equals(n.getNotificacoes());
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Notificacao n : this.notificacoes){
            sb.append(n.toString());
        }
        return sb.toString();
    }
    
    // Clone
    
    public Notificacoes clone(){
        return new Notificacoes(this);
    }
    
    // Adiciona uma notificacao
    public void addNotificacao(Notificacao n){
        this.notificacoes.add(n.clone());
    }
    
    // Remove uma notificacao
    public void removeNotificacao(Notificacao n){
        this.notificacoes.remove(n);
    }
    
    // Liberta as Notificacoes
    public void libertaNotificacoes(){
        this.notificacoes.clear();
    }
    
    
    public int quantosElementos(){
        return this.notificacoes.size();
    }
    
}
