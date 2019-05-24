package MyLogic.Gestores;

import MyLogic.ClassesBase.*;
import java.util.*;
import MyLogic.Exceptions.*;
import java.time.LocalDateTime;
import java.io.Serializable;
/**
 * Escreva a descrição da classe GestorAlugueres aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class GestorAlugueres implements Serializable{
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
        sb.append("----------- Gestor Alugueres -----------\n");
        for(Aluguer a : this.alugueres){
            sb.append(a.toString());
        }
        return sb.toString();
    }
    
    // Adiciona um Aluguer
    
    public void insereAluguer(Aluguer a) throws AluguerJaExisteException{
        if(this.alugueres.contains(a)){
            throw new AluguerJaExisteException("O aluguer já exite!\n");
        }
        this.alugueres.add(a.clone());
    }
    
    // Liberta os Veículos
    
    public void libertaGestorAlugueres(){
        this.alugueres.clear();
    }
    
    
    public Aluguer getAluguer(int id) throws AluguerNaoExisteException{
        for(Aluguer a : this.alugueres){
            if(a.getId() == id){
                return a.clone();
            }
        }
        throw new AluguerNaoExisteException("Aluguer nao existe!");
    }
    
    
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
    
    public List<Aluguer> historicoClienteEntreDatas(int nif, LocalDateTime inicio, LocalDateTime fim){
        List<Aluguer> aux = new ArrayList<>();
        for(Aluguer a : this.alugueres){
            if(a.getNif() == nif && ((a.getDataInicio().isAfter(inicio) && a.getDataInicio().isBefore(fim)) || 
                                     (a.getDataInicio().isEqual(inicio) && a.getDataInicio().isEqual(fim)))){
                aux.add(a.clone());
            }
        }
        return aux;
    }
    
    
    public List<Aluguer> historicoProprietario(int nif){
        List<Aluguer> aux = new ArrayList<>();
        for(Aluguer a : this.alugueres){
            if(a.getVeiculo().getNif() == nif){
                aux.add(a.clone());
            }
        }
        return aux;
    }
    
    
    public List<Aluguer> historicoProprietarioEntreDatas (int nif, LocalDateTime inicio, LocalDateTime fim){
        List<Aluguer> aux = new ArrayList<>();
        for(Aluguer a : this.alugueres){
            if(a.getVeiculo().getNif() == nif && ((a.getDataInicio().isAfter(inicio) && a.getDataInicio().isBefore(fim)) || 
                                     (a.getDataInicio().isEqual(inicio) && a.getDataInicio().isEqual(fim)))){
                aux.add(a.clone());
            }{
                aux.add(a.clone());
            }
        }
        return aux;
    }
    
    public double totalFaturadoVeiculo(String matricula, LocalDateTime inicio, LocalDateTime fim){
        double totalFaturado = 0;
        for(Aluguer a : this.alugueres){
            if(a.getVeiculo().getMatricula().equals(matricula) && ((a.getDataInicio().isAfter(inicio) && a.getDataInicio().isBefore(fim)) || 
                                     (a.getDataInicio().isEqual(inicio) && a.getDataInicio().isEqual(fim)))){
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
        while(it.hasNext() && i<10){
            a = it.next();
            if(!resultado.contains(a.getNif())){
                resultado.add(a.getNif());
                i++;
            }
        }
        return resultado;
    }
    
    
    public List<Integer> topDezClientesVezes() throws AluguerNaoExisteException {
        if(this.alugueres.isEmpty()){
            throw new AluguerNaoExisteException ("Ups! Não existem alugueres!");
        }
        Map<Integer,Integer> top = new TreeMap(compNumVezes);
        for(Aluguer a : this.alugueres){
            int nif = a.getNif();
            if(top.containsKey(nif)){
                int n = top.get(nif);
                top.put(nif,n++);
            }else{
                top.put(nif,1);
            }
        }
        List<Integer> resultado = new ArrayList();
        Iterator<Integer> it = top.keySet().iterator();
        int x = 0, i = 0;
        while(it.hasNext() && i < 10){
            x = it.next();
            resultado.add(x);
            i++;
        }
        return resultado;
    }
}
