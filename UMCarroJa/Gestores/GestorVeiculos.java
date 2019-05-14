package Gestores;

import Exceptions.*;
import ClassesBase.*;
import java.util.*;
import java.lang.*;

public class GestorVeiculos{
    
    // Variáveis de Instância
    
    private Map<String,Veiculo> veiculos;
    
    // Comparators
    
    Comparator<Veiculo> comparaPrecos = (a,b) -> { if (a.getPreco() == b.getPreco()) return 0;
                                                   if (a.getPreco() >  b.getPreco()) return 1;
                                                   else return -1;
                                                 };
                                                                                              
    // Construtores
    
    public GestorVeiculos(){
        this.veiculos = new HashMap<String,Veiculo>();   
    }
    
    public GestorVeiculos (GestorVeiculos gv){
        this.veiculos = gv.getVeiculos();
    }
    
    // Gets
    
    public Map<String,Veiculo> getVeiculos() {
        Map<String,Veiculo> aux = new HashMap<>();
        this.veiculos.forEach((k,v) -> aux.put(k,v.clone()));
        return aux;
    }
    
    // Sets
    
    public void setVeiculos (Map<String,Veiculo> veiculos) {
        this.veiculos = new HashMap<>();
        veiculos.forEach((k,v) -> this.veiculos.put(k,v.clone()));
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
        return this.veiculos.equals(gv.getVeiculos());
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("---------- Gestor Veículos ----------\n");
        for (Veiculo veiculo : this.veiculos.values()) {
            sb.append("\n" + veiculo.toString());
        }
        return sb.toString();
    }
   
    // Adiciona um Veiculo
    
    public void insereVeiculo (Veiculo v) 
    throws UtilizadorNaoExisteException, VeiculoJaExisteException{
        
        if (this.veiculos.containsValue(v)){
            throw new VeiculoJaExisteException ("O veiculo com a matricula " + v.getMatricula() + " já se encontra registado no sistema!\n");
        }
        this.veiculos.put(v.getMatricula(),v.clone());
    }
    
    // Liberta o GestorVeiculos
    
    public void libertaGestorVeiculos(){
        this.veiculos.clear();
    }
    
    // Devolver um veículo conforme a matrícula
    
    public Veiculo getVeiculo(String matricula) throws VeiculoNaoExisteException{
        if (this.veiculos.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }
        if (this.veiculos.containsKey(matricula)){
            throw new VeiculoNaoExisteException ("Ups! Esse veículo não existe no sistema.\n");
        }
        return this.veiculos.get(matricula).clone();
    }
    
    // Devolver o Veiculo mais barato
    
    public List<Veiculo> veiculoMaisBarato (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, Ponto destino) 
    throws VeiculoNaoExisteException{
        
        if (this.veiculos.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Gestor de veículos vazio.\n");
        }
        
        List<Veiculo> resultado = new ArrayList<>();
        TreeSet<Veiculo> maisBarato = new TreeSet<>(comparaPrecos);
        
        this.veiculos.forEach((k,v) -> {    
            if (v.getTipoVeiculo().equals(tipoVeiculo) && v.getTipoCombustivel().equals(tipoCombustivel) &&
                v.autonomiaBaixa() == false && v.getAutonomiaAtual() >= v.getLocalizacao().distancia(destino)){
                    maisBarato.add(v.clone());
            }
        });
        
        if (maisBarato.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Nenhum veículo disponível para a realização da viagem!\n");
        }
                
        for (Veiculo veiculo : maisBarato){
            if (veiculo.getPreco() == maisBarato.first().getPreco()) resultado.add(veiculo);
        }
        return resultado;
    }
        
    // Devolver o Veiculo mais perto
  
    public List<Veiculo> veiculoMaisPerto (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, Ponto localizacao, Ponto destino) 
    throws VeiculoNaoExisteException, UtilizadorNaoExisteException{
        
        if (this.veiculos.isEmpty()) {
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }
        
        List<Veiculo> resultado = new ArrayList<>();
        List<Veiculo> maisPerto = new ArrayList<>();
        
        this.veiculos.forEach((k,v) -> {
            if (v.getTipoVeiculo().equals(tipoVeiculo) && v.getTipoCombustivel().equals(tipoCombustivel) && 
                v.autonomiaBaixa() == false && v.getAutonomiaAtual() >= v.getLocalizacao().distancia(destino)){
                    maisPerto.add(v.clone());
            }          
        });
        
        if (maisPerto.isEmpty()){ 
            throw new VeiculoNaoExisteException ("Ups! Nenhum veículo disponível para a realização da viagem!\n");
        }
        
        maisPerto.sort((Veiculo v1,Veiculo v2) -> {
            if (v1.getLocalizacao().distancia(localizacao) >  v2.getLocalizacao().distancia(localizacao)) return 1;
            if (v1.getLocalizacao().distancia(localizacao) == v2.getLocalizacao().distancia(localizacao)) return 0;
            else return -1;
        });
        
        for (Veiculo veiculo : maisPerto){
            if (veiculo.getLocalizacao().distancia(destino) == maisPerto.get(0).getLocalizacao().distancia(destino)){
                resultado.add(veiculo.clone());
            }
        }
        return resultado;
    }
    
    // Devolver o Veiculo mais barato dentro de uma distância
    
    public List<Veiculo> veiculoMaisPertoBarato (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, Ponto localizacao, Ponto destino, 
    double distanciaMax) throws VeiculoNaoExisteException, UtilizadorNaoExisteException{
        
        if (this.veiculos.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }
        
        List<Veiculo> resultado = new ArrayList<>();
        TreeSet<Veiculo> maisBarato = new TreeSet<>(comparaPrecos);
        
        this.veiculos.forEach((k,v) -> {
            if (v.getTipoVeiculo().equals(tipoVeiculo) && v.getTipoCombustivel().equals(tipoCombustivel) && 
                v.autonomiaBaixa() == false && v.getAutonomiaAtual() >= v.getLocalizacao().distancia(destino) && 
                v.getLocalizacao().distancia(localizacao) <= distanciaMax){
                    maisBarato.add(v.clone());
            } 
        });
        
        if (maisBarato.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Não existe nenhum veículo disponível a " + distanciaMax + " Km's de si.\n");
        }
        
        for (Veiculo veiculo : maisBarato){
            if (veiculo.getPreco() == maisBarato.first().getPreco()) resultado.add(veiculo.clone());
        }
        return resultado;
    }
    
    // Devolver um Veículo expecífico
    
    public Veiculo veiculoEspecifico (String matricula, Ponto destino) 
    throws VeiculoNaoExisteException{
        
        if (this.veiculos.isEmpty()) {
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }
        if(!this.veiculos.containsKey(matricula)){
            throw new VeiculoNaoExisteException ("Ups! Esse veículo não existe no sistema.\n");
        }
        
        Veiculo veiculo = this.veiculos.get(matricula);
        
        if(veiculo.getAutonomiaAtual() < veiculo.getLocalizacao().distancia(destino)){
            throw new VeiculoNaoExisteException ("Ups! O veiculo com a matricula " + veiculo.getMatricula() + 
                                                 " nao tem autonomia para que possa ser alugado.\n");
        }
        return veiculo.clone();
    }
    
    // Devolver um Veiculo com uma autonomia expecífica
    
    public List<Veiculo> veiculoAutonomia (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, double autonomiaAtual) 
    throws VeiculoNaoExisteException, UtilizadorNaoExisteException{
        
        if (this.veiculos.isEmpty()) {
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }

        List<Veiculo> resultado = new ArrayList<>();

        for(Veiculo veiculo : this.veiculos.values()){
            if (veiculo.getTipoVeiculo().equals(tipoVeiculo) && veiculo.getTipoCombustivel().equals(tipoCombustivel) && 
                veiculo.getAutonomiaAtual() == autonomiaAtual && veiculo.autonomiaBaixa() == false){ 
                    resultado.add(veiculo.clone());
            }
        }

        if (resultado.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Não existe nenhum veículo com a autonomia atual de " + autonomiaAtual +
                                                 " que possa ser alugado.\n");
        }
        return resultado;
    }
    
}