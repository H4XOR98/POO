package Gestores;

import Exceptions.*;
import ClassesBase.*;
import java.util.*;
import java.lang.*;

public class GestorVeiculos{
    
    // Variáveis de Instância
    
    private Set<Veiculo> gestor;
    
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
        this.gestor = new Set<Veiculo>();   
    }
    
    public GestorVeiculos (GestorVeiculos gv){
        this.gestor = gv.getGestor();
    }
    
    // Gets
    
    public Set<Veiculo> getGestor(){
        Set<Veiculo> veiculos = new HashSet<>();
        for (Veiculo veiculo : this.gestor){
            veiculos.add(veiculo.clone());
        }
        return veiculos;
    }
    
    // Sets
    
    public void setGestor (Set<Veiculo> newGestor){
        this.gestor = new HashSet<>();
        for (Veiculo veiculo : newGestor){
            this.gestor.add(veiculo.clone());
        }
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
        for (Veiculo veiculo : this.gestor) {
            sb.append("\n" + veiculo.toString());
        }
        return sb.toString();
    }
    
    
    
    // Adiciona um Veiculo
    
    public void insereVeiculo (Veiculo v) throws UtilizadorNaoExisteException,VeiculoJaExisteException{
        if(this.gestor.contains(v)){
            throw new VeiculoJaExisteException("O veiculo com a matricula " + v.getMatricula() + " ja se encontra registado no sistema!\n");
        }
        this.gestor.add(v.clone());
    }
    
    // Atualiza um Veiculo
    
    public void updateVeiculo (Veiculo veiculo){
        for (Veiculo v : this.gestor){
            if (v.equals(veiculo)){
                v = veiculo.clone();
            }
        }
    }
    
    // Liberta o GestorVeiculos
    
    public void libertaGestor(){
        this.gestor.clear();
    }
    
    // Devolver o Veiculo mais barato
    
    public List<Veiculo> veiculoMaisBarato (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, double distancia) 
    throws VeiculoNaoExisteException{
        
        if (this.gestor.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Gestor de veículos vazio.\n");
        }
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
                if (veiculo.getPreco() == maisBarato.first().getPreco()) resultado.add(veiculo);
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
        
        Cliente cliente = (Cliente) u;
        
        List<Veiculo> maisPerto = new ArrayList<>();
        
        this.gestor.forEach((k,v) -> { 
            for (Veiculo veiculo : v){
                if (veiculo.getTipoVeiculo().equals(a.getTipoVeiculo()) && veiculo.getTipoCombustivel().equals(a.getTipoCombustivel()) &&
                    veiculo.autonomiaBaixa() == false && veiculo.getAutonomiaAtual() >= veiculo.getLocalizacao().distancia(a.getDestino())){
                        maisPerto.add(veiculo.clone());
                }
            }            
        });
        
        maisPerto.sort((Veiculo v1,Veiculo v2) -> {if(v1.getLocalizacao().distancia(cliente.getLocalizacao()) > v2.getLocalizacao().distancia(cliente.getLocalizacao())) return 1;
                                                   if(v1.getLocalizacao().distancia(cliente.getLocalizacao()) == v2.getLocalizacao().distancia(cliente.getLocalizacao())) return 0;
                                                   else return -1;
                                                  });
                                                  
        if (maisPerto.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Nenhum veículo disponível para a realização da viagem!\n");
        }
        return maisPerto;
    }
    
        
    // Devolver o Veiculo mais barato dentro de uma distância
    
    public List<Veiculo> veiculoMaisPertoBarato (Aluguer a, Utilizador u, double distanciaMax) 
    throws VeiculoNaoExisteException, UtilizadorNaoExisteException {
        if(!u.getClass().getSimpleName().equals("Cliente")){
            throw new UtilizadorNaoExisteException("O nif " + u.getNif() + " não está registado como um Cliente.\n");
        }
        if (this.gestor.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }
        
        Cliente c = (Cliente)u;
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
        
        if (maisBarato.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Não existe nenhum veículo disponível a " + distanciaMax + " Kms de si.\n");
        }
        
        for (Veiculo veiculo : maisBarato){
            if (c.getLocalizacao().distancia(veiculo.getLocalizacao()) <= distanciaMax) {
                resultado.add(veiculo.clone());
            }
        }
        
        if (resultado.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Não existe nenhum veículo disponível a " + distanciaMax + " Kms de si.\n");
        }
        
        return resultado;
    }
    
    // Devolver um Veículo expecífico
    
    public void veiculoEspecifico (Aluguer a, String matricula) 
    throws VeiculoNaoExisteException{
        if (this.gestor.isEmpty()) {
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }
        
        boolean enc = false;
        
        for(Collection<Veiculo> veiculos : this.gestor.values()){
            for(Veiculo veiculo : veiculos){
                if (veiculo.autonomiaBaixa() == false && veiculo.getAutonomiaAtual() >= veiculo.getLocalizacao().distancia(a.getDestino())){
                    a.setVeiculo(veiculo);
                    enc = true;
                }
            }
        }
        
        if(enc == false){
            throw new VeiculoNaoExisteException ("Ups! Esse veículo não tem autonomia suficiente.\n");
        }
    }
    
    // Devolver um Veiculo com uma autonomia expecífica
    
    public List<Veiculo> veiculoAutonomia (Aluguer a, Utilizador u, double autonomia) 
    throws VeiculoNaoExisteException,UtilizadorNaoExisteException {
        if(!u.getClass().getSimpleName().equals("Cliente")){
            throw new UtilizadorNaoExisteException ("O nif " + u.getNif() + " não está registado como um Cliente.\n");
        }
        if (this.gestor.isEmpty()) {
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }
        
        Cliente c = (Cliente) u;
        List<Veiculo> resultado = new ArrayList<>();
        
        for(Collection<Veiculo> veiculos : this.gestor.values()){
            for (Veiculo veiculo : veiculos){
                if (veiculo.getTipoVeiculo().equals(a.getTipoVeiculo()) && veiculo.getTipoCombustivel().equals(a.getTipoCombustivel()) && 
                    veiculo.getAutonomiaAtual() == autonomia && veiculo.autonomiaBaixa() == false){ 
                        resultado.add(veiculo.clone());
                }
            }
        }
        
        if (resultado.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Não existe nenhum veículo com a autonomia atual de " + autonomia + " que possa ser alugado.\n");
        }
        return resultado;
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