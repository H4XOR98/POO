package Gestores;

import ClassesBase.*;
import Exceptions.*;
import java.util.*;
/**
 * Escreva a descrição da classe GestorUtilizador aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
public class GestorUtilizadores {
    private Map<Integer,Utilizador> utilizadores;


    public GestorUtilizadores() {
        this.utilizadores = new HashMap<>();
    }

    public GestorUtilizadores(GestorUtilizadores gUtilizadores) {
        gUtilizadores.getUtilizadores();
    }

    public Map<Integer,Utilizador> getUtilizadores() {
        Map<Integer,Utilizador> aux = new HashMap<>();
        for (Utilizador u : this.utilizadores.values()) {
            aux.put(u.getNif(),u.clone());
        }
        return aux;
    }

    public void setUtilizadores(Map<Integer,Utilizador> utilizadores) {
        this.utilizadores = new HashMap<>();
        for (Utilizador u : utilizadores.values()) {
            this.utilizadores.put(u.getNif(),u.clone());
        }
    }


    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        GestorUtilizadores gUtilizadores = (GestorUtilizadores)o;
        
        return this.utilizadores.equals(gUtilizadores.getUtilizadores()); 
    }
    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-------------- Utilizadores --------------\n");
        for(Utilizador u: this.utilizadores.values()){
            sb.append(u.toString());
        }
        return sb.toString();
    }
    
    
    public GestorUtilizadores clone(){
        return new GestorUtilizadores(this);
    }
    
    //-------------
    
    public Notificacao insereUtilizador(Utilizador u) throws UtilizadorJaExisteException{
        if(this.utilizadores.containsKey(u.getNif())){
            throw new UtilizadorJaExisteException(u.toString());
        }
        this.utilizadores.put(u.getNif(),u.clone());
        Notificacao n = new Notificacao(u.getNif(),"Boas Vindas","\nBem vindo à UMCarroJá!");
        return n;
    }
    
    
    public void removeUtilizador(int nif) throws UtilizadorNaoExisteException{
        if(!this.utilizadores.containsKey(nif)){
            throw new UtilizadorNaoExisteException("Não existe nenhum utilizador com o nif " + nif + " no sistema!");
        }
        this.utilizadores.remove(nif);
    }
    
    public void libertaUtilizadores(){
        this.utilizadores.clear();
    }
    
    
    public Utilizador getUtilizador(int nif) throws UtilizadorNaoExisteException{
        if(!this.utilizadores.containsKey(nif)){
            throw new UtilizadorNaoExisteException("O nif: " + nif + " não existe no sistema!\n");
        }
        return this.utilizadores.get(nif).clone();
    }
    
    public List<Cliente> getClientes(){
        List<Cliente> clientes = new ArrayList<>();
        for(Utilizador u : this.utilizadores.values()){
            if(u.getClass().getSimpleName().equals("Cliente")){
                Cliente c = (Cliente)u;
                clientes.add(c.clone());
            }
        }
        return clientes;
    }
    
    public List<Proprietario> getProprietarios(){
        List<Proprietario> proprietarios = new ArrayList<>();
        for(Utilizador u : this.utilizadores.values()){
            if(u.getClass().getSimpleName().equals("Proprietario")){
                Proprietario p = (Proprietario)u;
                proprietarios.add(p.clone());
            }
        }
        return proprietarios;
    }
    
    public List<Utilizador> procuraUtilizadores(List<Integer> nifs) throws UtilizadorNaoExisteException{
        if(nifs.isEmpty()){
            throw new UtilizadorNaoExisteException("Não existem nif's a listar!\n");
        }
        List<Utilizador> utilitarios = new ArrayList<>();
        for(int n : nifs){
            utilitarios.add(getUtilizador(n).clone());
        }   
        return utilitarios;
    }
}
