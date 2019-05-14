package Gestores;

import Exceptions.*;
import ClassesBase.*;
import java.util.*;
import java.lang.*;

public class GestorVeiculos{
    
    // Variáveis de Instância
    
    private Map<Integer,Set<Veiculo>> gestor;
    
    // Comparators
    
    
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
            for (Veiculo veiculo : v) sb.append("\n" + veiculo.toString());
        });
        return sb.toString();
    }
    
    // Adiciona um Proprietário
    
    public void insereProprietario (Utilizador u) throws UtilizadorNaoExisteException,UtilizadorJaExisteException{
        if(!u.getClass().getSimpleName().equals("Proprietario")){
            throw new UtilizadorNaoExisteException("Este utilizador não é proprietário.\n");
        }
        if (this.gestor.containsKey(u.getNif())){
            throw new UtilizadorJaExisteException ("Proprietário já registado.\n");
        }
        Set<Veiculo> veiculos = new HashSet<>();
        this.gestor.put(u.getNif(),veiculos);
    }
    
    // Adiciona um Veiculo
    
    public void insereVeiculo (Veiculo v) throws UtilizadorNaoExisteException,VeiculoJaExisteException{
        if(!this.gestor.containsKey(v.getNif())){
            throw new UtilizadorNaoExisteException("O veiculo com a matricula " + v.getMatricula()+ " não tem o seu Proprietario registado no sistema!\n");
        }
        Set<Veiculo> veiculos = this.gestor.get(v.getNif());
        if(veiculos.contains(v)){
            throw new VeiculoJaExisteException("O veiculo com a matricula " + v.getMatricula() + " ja se encontra registado no sistema!\n");
        }
        veiculos.add(v.clone());
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
    
    public List<Veiculo> veiculoMaisBarato (Aluguer a) 
    throws VeiculoNaoExisteException{
        
        if (this.gestor.isEmpty()) throw new VeiculoNaoExisteException ("Ups! Gestor de veículos vazio.\n");
        List<Veiculo> resultado = new ArrayList<>();
        TreeSet<Veiculo> maisBarato = new TreeSet<>(comparaPrecos);
        
        this.gestor.forEach((k,v) -> { 
            for (Veiculo veiculo : v){
                if (veiculo.getTipoVeiculo().equals(a.getTipoVeiculo()) && veiculo.getTipoCombustivel().equals(a.getTipoCombustivel()) &&
                    veiculo.autonomiaBaixa() == false && veiculo.getAutonomiaAtual() >= veiculo.getLocalizacao().distancia(a.getDestino())){ 
                        maisBarato.add(veiculo.clone());
                }
            }
        });
        
        
        if (!maisBarato.isEmpty()){
            for (Veiculo veiculo : maisBarato){
                if (veiculo.getPreco() == maisBarato.first().getPreco()) resultado.add(veiculo.clone());
            }
            return resultado;
        }
        
        throw new VeiculoNaoExisteException ("Ups! Nenhum veículo disponível para a realização da viagem!\n");
    }
    
    // Devolver o Veiculo mais perto
  
    public List<Veiculo> veiculoMaisPerto (Aluguer a, Utilizador u) 
    throws VeiculoNaoExisteException, UtilizadorNaoExisteException{
        if(!u.getClass().getSimpleName().equals("Cliente")){
            throw new UtilizadorNaoExisteException ("O nif " + u.getNif() + " não está registado como um Cliente.\n");
        }
        if (this.gestor.isEmpty()) {
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }
        
        Cliente c = (Cliente) u;
        List<Veiculo> resultado = new ArrayList<>();
        TreeSet<Veiculo> maisPerto = new TreeSet<>();
        double distanciaMenor = Double.MAX_VALUE;
        
        this.gestor.forEach((k,v) -> { 
            for (Veiculo veiculo : v){
                if (veiculo.getTipoVeiculo().equals(a.getTipoVeiculo()) && veiculo.getTipoCombustivel().equals(a.getTipoCombustivel()) &&
                    veiculo.autonomiaBaixa() == false && veiculo.getAutonomiaAtual() >= veiculo.getLocalizacao().distancia(a.getDestino())){
                        maisPerto.add(veiculo.clone());
                }
            }            
        });
        
        
        if (!maisPerto.isEmpty()){
            for (Veiculo veiculo : maisPerto){
                if (veiculo.getLocalizacao().distancia(c.getLocalizacao()) < distanciaMenor){
                    resultado.clear();
                    resultado.add(veiculo.clone());
                    distanciaMenor = veiculo.getLocalizacao().distancia(c.getLocalizacao());
                }
                if (veiculo.getLocalizacao().distancia(c.getLocalizacao()) == distanciaMenor) resultado.add(veiculo.clone());
            }
            return resultado;
        }
        throw new VeiculoNaoExisteException ("Ups! Nenhum veículo disponível para a realização da viagem!\n");
    }
        
    // Devolver o Veiculo mais barato dentro de uma distância
    
    public List<Veiculo> veiculoMaisPertoBarato (Aluguer a, Utilizador u, double distanciaMax) 
    throws VeiculoNaoExisteException, UtilizadorNaoExisteException {
        if(!u.getClass().getSimpleName().equals("Cliente")){
            throw new UtilizadorNaoExisteException("O nif " + u.getNif() + " não está registado como um Cliente.\n");
        }
        if (this.gestor.isEmpty()) throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        
        Cliente c = (Cliente) u;
        List<Veiculo> resultado = new ArrayList<>();
        TreeSet<Veiculo> maisBarato = new TreeSet<>(comparaPrecos);
        
        this.gestor.forEach((k,v) -> { 
            for (Veiculo veiculo : v){
                if (veiculo.getTipoVeiculo().equals(a.getTipoVeiculo()) && veiculo.getTipoCombustivel().equals(a.getTipoCombustivel()) &&
                    veiculo.autonomiaBaixa() == false && veiculo.getAutonomiaAtual() >= veiculo.getLocalizacao().distancia(a.getDestino())){ 
                        maisBarato.add(veiculo.clone());
                }
            }
        });
        
        if (!maisBarato.isEmpty()){
            for (Veiculo veiculo : maisBarato){
                if (c.getLocalizacao().distancia(veiculo.getLocalizacao()) <= distanciaMax) resultado.add(veiculo.clone());
            }
            return resultado;
        }
        throw new VeiculoNaoExisteException ("Ups! Não existe nenhum veículo disponível a " + distanciaMax + " Kms de si.\n");
    }
    
    // Devolver um Veículo expecífico
    
    public void veiculoEspecifico (Aluguer a, String matricula) 
    throws VeiculoNaoExisteException{
        if (this.gestor.isEmpty()) {
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }
        
        List<Veiculo> veiculos = new ArrayList<>(); 
        
        this.gestor.forEach((k,v) -> { 
            for (Veiculo veiculo : v){
                if (veiculo.getMatricula().equals(matricula)) veiculos.add(veiculo);
            }
        });
        
        if (!veiculos.isEmpty()){
            if (veiculos.get(0).autonomiaBaixa() == false && 
                veiculos.get(0).getAutonomiaAtual() >= veiculos.get(0).getLocalizacao().distancia(a.getDestino())){
                    a.setVeiculo(veiculos.get(0).clone());
            }
            else throw new VeiculoNaoExisteException ("Ups! Esse veículo não tem autonomia suficiente.\n");
        }
        
        throw new VeiculoNaoExisteException ("Ups! Não existe nenhum veículo com a matrícula " + matricula + ".\n");
    }
    
    // Devolver um Veiculo com uma autonomia expecífica
    
    public List<Veiculo> veiculoAutonomia (Aluguer a, Utilizador u, double autonomia) 
    throws VeiculoNaoExisteException,UtilizadorNaoExisteException {
        
        if(!u.getClass().getSimpleName().equals("Cliente")){
            throw new UtilizadorNaoExisteException ("O nif " + u.getNif() + " não está registado como um Cliente.\n");
        }
        if (this.gestor.isEmpty()) throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        
        Cliente c = (Cliente) u;
        List<Veiculo> resultado = new ArrayList<>();
        
        this.gestor.forEach((k,v) -> {
            for (Veiculo veiculo : v){
                if (veiculo.getTipoVeiculo().equals(a.getTipoVeiculo()) && veiculo.getTipoCombustivel().equals(a.getTipoCombustivel()) && 
                    veiculo.getAutonomiaAtual() == autonomia && veiculo.autonomiaBaixa() == false){ 
                        resultado.add(veiculo.clone());
                }
            }
        });
        
        if (!resultado.isEmpty()) return resultado;
             
        throw new VeiculoNaoExisteException ("Ups! Não existe nenhum veículo com a autonomia atual de " + autonomia + " que possa ser alugado.\n");
    }
    
    public void getVeiculo(String matricula, Aluguer a) throws VeiculoNaoExisteException{
        if (this.gestor.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }
        List<Veiculo> veiculos = new ArrayList<>(); 
        
        this.gestor.forEach((k,v) -> { 
            for (Veiculo veiculo : v){
                if (veiculo.getMatricula().equals(matricula)) veiculos.add(veiculo);
            }
        });
        
        if (veiculos.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Esse veículo não tem autonomia suficiente.\n");
        }
        a.setVeiculo(veiculos.get(0).clone());
    }
}