package Gestores;

import ClassesBase.*;
import java.util.*;
import Exceptions.*;
/**
 * Escreva a descrição da classe GestorAlugueres aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class GestorAlugueres
{
    private Set<Aluguer> alugueres;
    
    
    public GestorAlugueres(){
        this.alugueres = new HashSet<>();
    }
    
    public GestorAlugueres(GestorAlugueres gestorAlugueres){
        this.alugueres = gestorAlugueres.getAlugueres();
    }
    
    // Gets
    
    public Set<Aluguer> getAlugueres(){
        Set<Aluguer> aux = new HashSet<>();
        for(Aluguer a : this.alugueres){
            aux.add(a.clone());
        }
        return aux;
    }
    
    // Sets
    
    public void setAlugueres (Set<Aluguer> alugueres){
        this.alugueres = new HashSet<>();
        for(Aluguer a : alugueres){
            this.alugueres.add(a.clone());
        }
    }
    
    // Clone
    
    public GestorAlugueres clone(){
        return new GestorAlugueres(this);
    }
    
    // Equals
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        GestorAlugueres a = (GestorAlugueres)o;
        return this.alugueres.equals(a.getAlugueres());
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Aluguer a : this.alugueres){
            sb.append("----------- Gestor Alugueres -----------\n");
            sb.append(a.toString());
        }
        return sb.toString();
    }
    
    // Adiciona um Aluguer
    
    public void addAluguer(Aluguer a) throws AluguerJaExisteException{
        if(this.alugueres.contains(a)){
            throw new AluguerJaExisteException("O aluguer já exite!\n");
        }
        this.alugueres.add(a.clone());
    }
    
    // Liberta os Veículos
    
    public void libertaGestorAlugueres(){
        this.alugueres.clear();
    }
    
    
    public List<Aluguer> historicoCliente(int nif){
        List<Aluguer> aux = new ArrayList<>();
        for(Aluguer a : this.alugueres){
            if(a.getNif() == nif){
                aux.add(a.clone());
            }
        }
        return aux;
    }
    
    
    public Collection<Aluguer> historicoVeiculo(Veiculo veiculo){
        Collection<Aluguer> aux = new HashSet<>();
        for(Aluguer a : this.alugueres){
            if(a.getVeiculo().equals(veiculo)){
                aux.add(a.clone());//não necessita de clone, pois é uma "visao"
            }
        }
        return aux;
    }
    
    
    public double totalFaturadoProprietario(int nif){
        double totalFaturado = 0;
        for(Aluguer a : this.alugueres){
            if(a.getVeiculo().getNif() == nif){
                totalFaturado += a.getCusto();
            }
        }
        return totalFaturado;
    }
}
