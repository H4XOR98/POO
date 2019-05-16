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
    
    Comparator<Aluguer> compKms = (a,b) -> { if (a.getDistancia() == b.getDistancia()) return 0;
                                             if (a.getDistancia() >  b.getDistancia()) return 1;
                                             else return -1;
                                           };
    
    Comparator<Integer> compNumVezes = (a,b) -> a - b;
    
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
    
    public void adicionaAluguer(Aluguer a) throws AluguerJaExisteException{
        if(this.alugueres.contains(a)){
            throw new AluguerJaExisteException("O aluguer já exite!\n");
        }
        this.alugueres.add(a.clone());
    }
    
    // Liberta os Veículos
    
    public void libertaGestorAlugueres(){
        this.alugueres.clear();
    }
    
    
    /*
    public Aluguer confirmaAluguer(int id,int nif, EstadoAluguer estadoAluguer){
        Iterator<Aluguer> it = this.alugueres.iterator();
        Aluguer a = null;
        boolean enc = false;
        while(it.hasNext() && !enc){
            a = it.next();
            if(a.getId() == id){
                enc = true;
            }
        }
        if(a.getVeiculo().getNif != nif){
            throw new
        }
        
    }
    */
    
    
    //-----------------------------------------
    
    
    public List<Aluguer> historicoCliente(int nif){
        List<Aluguer> aux = new ArrayList<>();
        for(Aluguer a : this.alugueres){
            if(a.getNif() == nif){
                aux.add(a.clone());
            }
        }
        return aux;
    }
    
    
    public List<Aluguer> historicoProprietario(String matricula){
        List<Aluguer> aux = new ArrayList<>();
        for(Aluguer a : this.alugueres){
            if(a.getVeiculo().getMatricula().equals(matricula)){
                aux.add(a.clone());
            }
        }
        return aux;
    }
    
    
    public double totalFaturadoVeiculo(int nif){
        double totalFaturado = 0;
        for(Aluguer a : this.alugueres){
            if(a.getVeiculo().getNif() == nif){
                totalFaturado += a.getCusto();
            }
        }
        return totalFaturado;
    }
    
    
    public List<Integer> topDezClientesKms() throws AluguerNaoExisteException {
        if(this.alugueres.isEmpty()){
            throw new AluguerNaoExisteException ("Ups! Não existem alugueres!");
        }
        Set<Aluguer> top = new TreeSet<>(compKms);
        for(Aluguer a : this.alugueres){
            top.add(a);
        }
        List<Integer> resultado = new ArrayList<>();
        Iterator<Aluguer> it = top.iterator();
        Aluguer a = null;
        int i = 0;
        while(it.hasNext() && i<9){
            a = it.next();
            if(!resultado.contains(a.getNif())){
                resultado.add(a.getNif());
                i++;
            }
        }
        return resultado;
    }
}
