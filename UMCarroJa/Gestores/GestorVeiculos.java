package Gestores;

import Exceptions.*;
import ClassesBase.*;
import java.util.*;

public class GestorVeiculos{
    
    // Variáveis de Instância
    
    private Map<Integer,Set<Veiculo>> gestor;
    
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
        this.gestor = new HashMap<Integer,Set<Veiculo>>();   
    }
    
    public GestorVeiculos (GestorVeiculos gv){
        this.gestor = gv.getGestor();
    }
    
    // Gets
    
    public Map<Integer,Set<Veiculo>> getGestor(){
        Map<Integer,Set<Veiculo>> aux = new HashMap<>();
        this.gestor.forEach((k,v) -> {
            Set<Veiculo> veiculos = new HashSet<>();
            for (Veiculo veiculo : v){
                veiculos.add(veiculo.clone());
            }
            aux.put(k, veiculos);
        });
        return aux;
    }
    
    // Sets
    
    public void setGestor (Map<Integer,Set<Veiculo>> newGestor){
        this.gestor = new HashMap<>();
        newGestor.forEach((k,v) -> {
            Set<Veiculo> veiculos = new HashSet<>();
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
        this.gestor.forEach((k,v) -> {
            sb.append("NIF: " + k.toString() + "\n");
            for (Veiculo veiculo : v) sb.append(veiculo.toString());
        });
        return sb.toString();
    }
    
    // Adiciona um Veiculo
    
    public void insereVeiculo (Veiculo v) throws UtilizadorNaoExisteException{
        if (!this.gestor.containsKey(v.getNif())) throw new UtilizadorNaoExisteException ("Proprietário não registado.\n");
        if (this.gestor.get(v.getNif()).isEmpty()){
            Set<Veiculo> veiculos = new HashSet<>();
            this.gestor.put(v.getNif(), veiculos);
        }
        this.gestor.get(v.getNif()).add(v.clone());
    }
    
    // Atualiza um Veiculo
    
    public void updateVeiculo (Veiculo veiculo){
        Set<Veiculo> veiculos = this.gestor.get(veiculo.getNif());
        for (Veiculo v : veiculos){
            if (v.getMatricula().equals(veiculo.getMatricula())) v = veiculo.clone();
        }
    }
    
    // Liberta o GestorVeiculos
    
    public void libertaGestor(){
        this.gestor.clear();
    }
    
    // Devolver o Veiculo mais barato
    
    public Veiculo veiculoMaisBarato (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel) 
    throws VeiculoNaoExisteException{
        if (this.gestor.isEmpty()) throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        TreeSet<Veiculo> maisBarato = new TreeSet<>(comparaPrecos);
        this.gestor.forEach((k,v) -> { 
            for (Veiculo veiculo : v){
                if (veiculo.getTipoVeiculo().equals(tipoVeiculo) && veiculo.getTipoCombustivel().equals(tipoCombustivel)){
                maisBarato.add(veiculo.clone());
                }
            }
        });
        return maisBarato.first();
    }
    
    // Devolver o Veiculo mais perto
  
    public Veiculo veiculoMaisPerto (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, Ponto localizacao) 
    throws VeiculoNaoExisteException{
        if (this.gestor.isEmpty()) throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        TreeSet<Veiculo> maisPerto = new TreeSet<>(comparaLocalizacao);
        this.gestor.forEach((k,v) -> { 
            for (Veiculo veiculo : v){
                if (veiculo.getTipoVeiculo().equals(tipoVeiculo) && veiculo.getTipoCombustivel().equals(tipoCombustivel)){
                    maisPerto.add(veiculo.clone());
                }
            }            
        });
        double distanciaMenor = Double.MAX_VALUE;
        Veiculo vei = null;
        for (Veiculo veiculo : maisPerto){
            if (veiculo.getLocalizacao().distancia(localizacao) < distanciaMenor){
                vei = veiculo;
                distanciaMenor = veiculo.getLocalizacao().distancia(localizacao);
            }
        }
        return vei.clone();
    }
        
    // Devolver o Veiculo mais barato dentro de uma distância
    
    public Veiculo veiculoMaisPertoBarato (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, Ponto localizacao, double distanciaMax) 
    throws VeiculoNaoExisteException{
        if (this.gestor.isEmpty()) throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        TreeSet<Veiculo> maisBarato = new TreeSet<>(comparaPrecos);
        this.gestor.forEach((k,v) -> { 
            for (Veiculo veiculo : v){
                if (veiculo.getTipoVeiculo().equals(tipoVeiculo) && veiculo.getTipoCombustivel().equals(tipoCombustivel)){
                    maisBarato.add(veiculo.clone());
                }
            }
        });
        for (Veiculo veiculo : maisBarato){
            if (localizacao.distancia(veiculo.getLocalizacao()) <= distanciaMax) return veiculo.clone();
        }
        throw new VeiculoNaoExisteException ("Ups! Não existe nenhum veículo a " + distanciaMax + " Kms de si.\n");
    }
    
    // Devolver um Veículo expecífico
    
    public Veiculo veiculoEspecifico (String matricula) 
    throws VeiculoNaoExisteException{
        if (this.gestor.isEmpty()) throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        TreeSet<Veiculo> veiculos = new TreeSet<>();
        this.gestor.forEach((k,v) -> { 
            for (Veiculo veiculo : v){
                if (veiculo.getMatricula().equals(matricula)) veiculos.add(veiculo.clone());
            }
        });
        if (!veiculos.isEmpty()) return veiculos.first().clone();
        throw new VeiculoNaoExisteException ("Ups! Não existe nenhum veículo com a matrícula " + matricula + ".\n");
    }
    
    // Devolver um Veiculo com uma autonomia expecífica
    
    public Veiculo veiculoAutonomia (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, Ponto localizacao, double autonomia) 
    throws VeiculoNaoExisteException{
        if (this.gestor.isEmpty()) throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        TreeSet<Veiculo> veiculos = new TreeSet<>();
        this.gestor.forEach((k,v) -> {
            for (Veiculo veiculo : v){
                if (veiculo.getTipoVeiculo().equals(tipoVeiculo) && veiculo.getTipoCombustivel().equals(tipoCombustivel) && 
                    veiculo.getAutonomiaAtual() == autonomia){ 
                        veiculos.add(veiculo.clone());
                }
            }
        });
        double distanciaMenor = Double.MAX_VALUE;
        Veiculo vei = null;
        for (Veiculo veiculo : veiculos){
            if (veiculo.getLocalizacao().distancia(localizacao) < distanciaMenor){
                vei = veiculo;
                distanciaMenor = veiculo.getLocalizacao().distancia(localizacao);
            }
        }
        if (!veiculos.isEmpty()) return vei.clone();
        throw new VeiculoNaoExisteException ("Ups! Não existe nenhum veículo com a autonomia atual de " + autonomia + ".\n");
    }
                
}
