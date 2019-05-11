package Gestores;

import Exceptions.*;
import ClassesBase.*;
import java.util.*;

public class GestorVeiculos{
    
    // Variáveis de Instância
    
    private Map<Integer,Collection<Veiculo>> gestor;
    
    Comparator<Ponto> comparaLocalizacao = (a,b) -> { if (a.distancia(b) == 0) return 0;
                                                      if (a.distancia(b) >  0) return 1;
                                                      else return -1;
                                                    };
    
    
    Comparator<Veiculo> comparaPrecos = (a,b) -> { if (a.getPreco() == b.getPreco()) return 0;
                                                   if (a.getPreco() >  b.getPreco()) return 1;
                                                   else return -1;
                                                 };
               
                                                 
                                                 
    // Construtores
    
    public GestorVeiculos(){
        this.gestor = new HashMap<>();   
    }
    
    public GestorVeiculos (Map<Integer,Collection<Veiculo>> gestor){
        setGestor(gestor);
    }
    
    public GestorVeiculos (GestorVeiculos gv){
        this.gestor = gv.getGestor();
    }
    
    // Gets
    
    public Map<Integer,Collection<Veiculo>> getGestor(){
        Map<Integer,Collection<Veiculo>> aux = new HashMap<Integer,Collection<Veiculo>>();
        this.gestor.forEach((k,v) -> {
            Collection<Veiculo> veiculos = new HashSet<>();
            for (Veiculo veiculo : v){
                veiculos.add(veiculo.clone());
            }
            aux.put(k, veiculos);
        });
        return aux;
    }
    
    // Sets
    
    public void setGestor (Map<Integer,Collection<Veiculo>> newGestor){
        this.gestor = new HashMap<Integer,Collection<Veiculo>>();
        newGestor.forEach((k,v) -> {
            Collection<Veiculo> veiculos = new HashSet<>();
            for (Veiculo veiculo : v){
                veiculos.add(veiculo.clone());
            }
            this.gestor.put(k, veiculos);
        });
    }
    
    // Clone
    
    public GestorVeiculos clone(){
        return new GestorVeiculos(this);
    }
    
    // Equals
    
    public boolean equals (Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        
        GestorVeiculos gv = (GestorVeiculos)o;
        
        return this.gestor.equals(gv.getGestor());
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("---------- Gestor Veículos ----------\n");
        this.gestor.forEach((k,v) -> sb.append(v.toString()));
        return sb.toString();
    }
    
    // Adiciona um veículo
    
    public void insereVeiculo (Veiculo v){
        if (this.gestor.get(v.getNif()).isEmpty()){
            Collection<Veiculo> veiculos = new HashSet<>();
            this.gestor.put(v.getNif(), veiculos);
        }
        this.gestor.get(v.getNif()).add(v.clone());
    }
    
    // remove um veículo
    
    public void removeVeiculo (Veiculo v){
        Collection<Veiculo> aux = this.gestor.get(v.getNif());
        aux.remove(v);
    }
    
    // Liberta o Gestor de Veículos
    
    public void libertaGestor(){
        this.gestor.clear();
    }
    
    // Devolver o Veiculo mais barato
    
    public Veiculo determinaVeiculoMaisBarato(){
        TreeSet<Veiculo> maisBarato = new TreeSet<>(comparaPrecos);
        this.gestor.forEach((k,v) -> { for (Veiculo veiculo : v) maisBarato.add(veiculo.clone()); });
        return maisBarato.first();
    }
    
    // Devolver o Veiculo mais perto
  
    public Veiculo determinaVeiculoMaisPerto (Ponto localizacao){
        TreeSet<Veiculo> maisPerto = new TreeSet<>();
        this.gestor.forEach((k,v) -> { for (Veiculo veiculo : v) maisPerto.add(veiculo.clone()); });
        return maisPerto.first(); 
    }
        
    // Devolver o Veiculo mais barato dentro de uma distância
    
    public Veiculo determinaVeiculoMaisBaratoPerto (Ponto localizacao, double distanciaMax) throws VeiculoNaoEncontradoException {
        TreeSet<Veiculo> maisBarato = new TreeSet<>(comparaPrecos);
        this.gestor.forEach((k,v) -> { for (Veiculo veiculo : v) maisBarato.add(veiculo.clone()); });
        for (Veiculo veiculo : maisBarato){
            if (localizacao.distancia(veiculo.getLocalizacao()) <= distanciaMax) return veiculo;
        }
        throw new VeiculoNaoEncontradoException("" + distanciaMax);
    }
   
}