package MyLogic.Gestores;

import MyLogic.Exceptions.*;
import MyLogic.ClassesBase.*;
import java.util.*;
import java.lang.*;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;

public class GestorVeiculos implements Serializable{
    
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
   
    // Adiciona um veículo
    
    public void insereVeiculo (Veiculo v) 
    throws VeiculoJaExisteException{
        
        if (this.veiculos.containsValue(v)){
            throw new VeiculoJaExisteException ("O veiculo com a matricula " + v.getMatricula() + " já se encontra registado no sistema!\n");
        }
        this.veiculos.put(v.getMatricula(),v.clone());
    }
    
    public void atualizaVeiculo(Veiculo v){
        this.veiculos.replace(v.getMatricula(),v.clone());
    }
    
    // Liberta o gestor de veículos
    
    public void libertaGestorVeiculos(){
        this.veiculos.clear();
    }
    
    // Devolver um veículo conforme a matrícula
    
    public Veiculo getVeiculo(String matricula) throws VeiculoNaoExisteException{
        if (this.veiculos.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }
        if (!this.veiculos.containsKey(matricula)){
            throw new VeiculoNaoExisteException ("Ups! Esse veículo não existe no sistema.\n");
        }
        return this.veiculos.get(matricula).clone();
    }
    
    // Devolver o veículo mais barato
    
    public List<String> veiculoMaisBarato (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, Ponto destino) 
    throws VeiculoNaoExisteException{
        
        if (this.veiculos.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Gestor de veículos vazio.\n");
        }
        
        List<String> resultado = new ArrayList<>();
        List<Veiculo> maisBarato = new ArrayList<>();
        
        this.veiculos.forEach((k,v) -> {    
            if (v.getTipoVeiculo().equals(tipoVeiculo) && v.getTipoCombustivel().equals(tipoCombustivel) &&
                v.autonomiaBaixa() == false && v.autonomiaSuficiente(destino)){
                    maisBarato.add(v.clone());
            }
        });
        
        if (maisBarato.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Nenhum veículo disponível para a realização da viagem!\n");
        }
        
        maisBarato.sort(comparaPrecos);
        double precoMenor = maisBarato.get(0).getPreco();
        
        for (Veiculo veiculo : maisBarato){
            if (veiculo.getPreco() <= precoMenor) resultado.add(veiculo.toString());
        }
        return resultado;
    }
        
    // Devolver o veículo mais perto
  
    public List<String> veiculoMaisPerto (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, Ponto localizacao, Ponto destino) 
    throws VeiculoNaoExisteException{
        
        if (this.veiculos.isEmpty()) {
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }
        
        List<String> resultado = new ArrayList<>();
        List<Veiculo> maisPerto = new ArrayList<>();
        
        this.veiculos.forEach((k,v) -> {
            if (v.getTipoVeiculo().equals(tipoVeiculo) && v.getTipoCombustivel().equals(tipoCombustivel) && 
                v.autonomiaBaixa() == false && v.autonomiaSuficiente(destino)){
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
                resultado.add(veiculo.toString());
            }
        }
        return resultado;
    }
    
    // Devolver o veículo mais barato dentro de uma distância
    
    public List<String> veiculoMaisPertoBarato (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, Ponto localizacao, Ponto destino, 
    double distanciaMax) throws VeiculoNaoExisteException{
        
        if (this.veiculos.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }
        
        List<String> resultado = new ArrayList<>();
        List<Veiculo> maisBarato = new ArrayList<>();
        
        this.veiculos.forEach((k,v) -> {
            if (v.getTipoVeiculo().equals(tipoVeiculo) && v.getTipoCombustivel().equals(tipoCombustivel) && 
                v.autonomiaBaixa() == false && v.autonomiaSuficiente(destino) && v.getLocalizacao().distancia(localizacao) <= distanciaMax){
                    maisBarato.add(v.clone());
            } 
        });
        
        if (maisBarato.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Não existe nenhum veículo disponível a " + distanciaMax + " Km's de si.\n");
        }
        
        maisBarato.sort(comparaPrecos);
        double precoMenor = maisBarato.get(0).getPreco(); 
        
        for (Veiculo veiculo : maisBarato){
            if (veiculo.getPreco() <= precoMenor) resultado.add(veiculo.toString());
        }
        return resultado;
    }
    
    // Devolver um veículo expecífico
    
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
    
    // Devolver um veículo com uma autonomia expecífica
    
    public List<String> veiculoAutonomia (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, double autonomiaAtual) 
    throws VeiculoNaoExisteException{
        
        if (this.veiculos.isEmpty()) {
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }

        List<String> resultado = new ArrayList<>();

        for(Veiculo veiculo : this.veiculos.values()){
            if (veiculo.getTipoVeiculo().equals(tipoVeiculo) && veiculo.getTipoCombustivel().equals(tipoCombustivel) && 
                veiculo.getAutonomiaAtual() == autonomiaAtual && veiculo.autonomiaBaixa() == false){ 
                    resultado.add(veiculo.toString());
            }
        }

        if (resultado.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Não existe nenhum veículo com a autonomia atual de " + autonomiaAtual +
                                                 " que possa ser alugado.\n");
        }
        return resultado;
    }
    
    // Retornar os veículos de um proprietário 
    
    public List<String> redacaoVeiculosProprietario (int nif) throws VeiculoNaoExisteException{
        if (this.veiculos.isEmpty()){
            throw new VeiculoNaoExisteException ("Ups! Gestor de Veiculos Vazio.\n");
        }
        List<String> resultado = new ArrayList<>();
        for(Veiculo v : this.veiculos.values()){
            if(v.getNif() == nif){
                resultado.add(v.toString());
            }
        }
        return resultado;
    }
    
    // Guardar Estado para um ficheiro
    
    public void saveStatus() throws IOException{
        FileOutputStream fos = new FileOutputStream("GestorVeiculos_Status.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.veiculos);
        fos.close();
        oos.close();
    }
    
    // Carregar Estado de um ficheiro
    
    public GestorVeiculos loadStatus() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("GestorVeiculos_Status.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        GestorVeiculos gv = new GestorVeiculos();
        gv.veiculos = (Map<String,Veiculo>) ois.readObject();
        fis.close();
        ois.close();
        return gv;
    }

}