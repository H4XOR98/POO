
package Gestores;

import Exceptions.*;
import ClassesBase.*;
import java.util.*;

public class GestorVeiculos{
    
    // Variáveis de Instância
    
    private Map<Integer,Collection<Veiculo>> gestor;
    
    // Comparators
    
    
    Comparator<Veiculo> comparaLocalizacao = (a,b) -> { if (a.getLocalizacao().distancia(b.getLocalizacao()) == 0) return 0;
                                                        if (a.getLocalizacao().distancia(b.getLocalizacao()) >  0) return 1;
                                                        else return -1;
                                                      };
    
    
    Comparator<Veiculo> comparaPrecos = (a,b) -> { if (a.getPreco() == b.getPreco()) return 0;
                                                   if (a.getPreco() >  b.getPreco()) return 1;
                                                   else return -1;
                                                 };
    
                                                 
    Comparator<Veiculo> comparaAutonomias = (a,b) -> { if (a.getAutonomiaAtual() == b.getAutonomiaAtual()) return 0;
                                                       if (a.getAutonomiaAtual() >  b.getAutonomiaAtual()) return 1;
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
    
    public void libertaGestorVeiculos(){
        this.gestor.forEach((k,v) -> v.clear());
        this.gestor.clear();
    }
    
    // Devolver o Veiculo mais barato
    
    public Veiculo determinaVeiculoMaisBarato(TipoCombustivel tipoCombustivel) throws VeiculoNaoExisteException{
        if (this.gestor.isEmpty()) throw new VeiculoNaoExisteException("Ups! Gestor de Veiculos Vazio.\n");
        TreeSet<Veiculo> maisBarato = new TreeSet<>(comparaPrecos);
        this.gestor.forEach((k,v) -> { for (Veiculo veiculo : v) maisBarato.add(veiculo.clone()); });
        for(Veiculo veiculo : maisBarato){
            if(tipoCombustivel.equals(veiculo.getTipoCombustivel())){
                return veiculo.clone();
            }
        }
        throw new VeiculoNaoExisteException("Ups! Não existe nenhum veículo a " + tipoCombustivel + ".\n");
    }
    
    // Devolver o Veiculo mais perto
  
    public Veiculo determinaVeiculoMaisPerto (Ponto localizacao,TipoCombustivel tipoCombustivel) throws VeiculoNaoExisteException{
        if (this.gestor.isEmpty()) throw new VeiculoNaoExisteException("Ups! Gestor de Veiculos Vazio.\n");
        TreeSet<Veiculo> maisPerto = new TreeSet<>(comparaLocalizacao);
        this.gestor.forEach((k,v) -> { for (Veiculo veiculo : v) maisPerto.add(veiculo.clone()); });
        double distanciaMenor = Double.MAX_VALUE;
        Veiculo vei = null;
        for (Veiculo veiculo : maisPerto){
            if (veiculo.getLocalizacao().distancia(localizacao) < distanciaMenor && tipoCombustivel.equals(veiculo.getTipoCombustivel())){
                vei = veiculo;
                distanciaMenor = veiculo.getLocalizacao().distancia(localizacao);
            }
        }
        if(vei == null){
            throw new VeiculoNaoExisteException("Ups! Não existe nenhum veículo a " + tipoCombustivel + ".\n");
        }
        return vei.clone();
    }
        
    // Devolver o Veiculo mais barato dentro de uma distância -> Erro
    
    public Veiculo determinaVeiculoMaisBaratoPerto (Ponto localizacao, double distanciaMax, TipoCombustivel tipoCombustivel) throws VeiculoNaoExisteException {
        if (this.gestor.isEmpty()) throw new VeiculoNaoExisteException("Ups! Gestor de Veiculos Vazio.\n");
        TreeSet<Veiculo> maisBarato = new TreeSet<>(comparaPrecos);
        this.gestor.forEach((k,v) -> { for (Veiculo veiculo : v) maisBarato.add(veiculo.clone()); });
        for (Veiculo veiculo : maisBarato){
            if (localizacao.distancia(veiculo.getLocalizacao()) <= distanciaMax && tipoCombustivel.equals(veiculo.getTipoCombustivel())){
                return veiculo.clone();
            }
        }
        throw new VeiculoNaoExisteException("Ups! Não existe nenhum veículo a " + distanciaMax + " Kms de si e que seja a " + tipoCombustivel + ".\n");
    }
    
    // Devolver um Veículo expecífico
    
    public Veiculo devolveVeiculoEspecifico (String matricula) throws VeiculoNaoExisteException{
        if (this.gestor.isEmpty()) throw new VeiculoNaoExisteException("Ups! Gestor de Veiculos Vazio.\n");
        TreeSet<Veiculo> veiculos = new TreeSet<>();
        this.gestor.forEach((k,v) -> { 
            for (Veiculo veiculo : v){
                if (veiculo.getMatricula().equals(matricula)) veiculos.add(veiculo.clone());
            }
        });
        if (!veiculos.isEmpty()) return veiculos.first().clone();
        throw new VeiculoNaoExisteException("Ups! Não existe nenhum veículo com a matrícula " + matricula + ".\n");
    }
    
    // Devolver um Veiculo com uma autonomia expecífica
    
    public Veiculo devolveVeiculoAutonomia (Ponto localizacao, double autonomia, TipoCombustivel tipoCombustivel) throws VeiculoNaoExisteException{
        if (this.gestor.isEmpty()) throw new VeiculoNaoExisteException("Ups! Gestor de Veiculos Vazio.\n");
        TreeSet<Veiculo> veiculos = new TreeSet<>();
        this.gestor.forEach((k,v) -> {
            for (Veiculo veiculo : v){
                if (veiculo.getAutonomiaAtual() == autonomia) veiculos.add(veiculo.clone());
            }
        });
        double distanciaMenor = Double.MAX_VALUE;
        Veiculo vei = null;
        for (Veiculo veiculo : veiculos){
            if (veiculo.getLocalizacao().distancia(localizacao) < distanciaMenor && tipoCombustivel.equals(veiculo.getTipoCombustivel())){
                vei = veiculo;
                distanciaMenor = veiculo.getLocalizacao().distancia(localizacao);
            }
        }
        if (!veiculos.isEmpty()) return vei.clone();
        throw new VeiculoNaoExisteException("Ups! Não existe nenhum veículo com a autonomia atual de " + autonomia + ".\n");
    }
                
}
