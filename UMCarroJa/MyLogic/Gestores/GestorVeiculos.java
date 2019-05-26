package MyLogic.Gestores;

import MyLogic.Exceptions.*;
import MyLogic.ClassesBase.*;
import java.util.*;
import java.lang.*;
import java.io.Serializable;
import java.io.PrintWriter;
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
    
    public void insereVeiculo (Veiculo v) throws VeiculoJaExisteException{
        if (this.veiculos.containsValue(v)){
            throw new VeiculoJaExisteException("" + v.getMatricula());
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
    
    public Veiculo getVeiculo(String matricula) throws GestorVazioException, VeiculoNaoExisteException{
        if (this.veiculos.isEmpty()){
            throw new GestorVazioException("");
        }
        if (!this.veiculos.containsKey(matricula)){
            throw new VeiculoNaoExisteException(matricula);
        }
        return this.veiculos.get(matricula).clone();
    }
    
    // Devolver o veículo mais barato
    
    public List<Veiculo> veiculoMaisBarato (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, Ponto destino) 
    throws GestorVazioException, VeiculoNaoEncontradoException{
        
        if (this.veiculos.isEmpty()){
            throw new GestorVazioException("");
        }
        
        List<Veiculo> resultado = new ArrayList<>();
        List<Veiculo> maisBarato = new ArrayList<>();
        
        this.veiculos.forEach((k,v) -> {    
            if (v.getTipoVeiculo().equals(tipoVeiculo) && v.getTipoCombustivel().equals(tipoCombustivel) &&
                v.autonomiaBaixa() == false && v.autonomiaSuficiente(destino)){
                    maisBarato.add(v);
            }
        });
        
        if (maisBarato.isEmpty()){
            throw new VeiculoNaoEncontradoException(".");
        }
        
        maisBarato.sort(comparaPrecos);
        double precoMenor = maisBarato.get(0).getPreco();
        
        for (Veiculo veiculo : maisBarato){
            if (veiculo.getPreco() <= precoMenor) resultado.add(veiculo.clone());
        }
        return resultado;
    }
        
    // Devolver o veículo mais perto
  
    public List<Veiculo> veiculoMaisPerto (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, Ponto localizacao, Ponto destino) 
    throws GestorVazioException, VeiculoNaoEncontradoException{
        
        if (this.veiculos.isEmpty()) {
            throw new GestorVazioException("");
        }
        
        List<Veiculo> resultado = new ArrayList<>();
        List<Veiculo> maisPerto = new ArrayList<>();
        
        this.veiculos.forEach((k,v) -> {
            if (v.getTipoVeiculo().equals(tipoVeiculo) && v.getTipoCombustivel().equals(tipoCombustivel) && 
                v.autonomiaBaixa() == false && v.autonomiaSuficiente(destino)){
                    maisPerto.add(v.clone());
            }          
        });
        
        if (maisPerto.isEmpty()){ 
            throw new VeiculoNaoEncontradoException(".");
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
    
    // Devolver o veículo mais barato dentro de uma distância
    
    public List<Veiculo> veiculoMaisPertoBarato (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, Ponto localizacao, Ponto destino, 
    double distanciaMax) throws GestorVazioException, VeiculoNaoEncontradoException{
        
        if (this.veiculos.isEmpty()){
            throw new GestorVazioException("");
        }
        
        List<Veiculo> resultado = new ArrayList<>();
        List<Veiculo> maisBarato = new ArrayList<>();
        
        this.veiculos.forEach((k,v) -> {
            if (v.getTipoVeiculo().equals(tipoVeiculo) && v.getTipoCombustivel().equals(tipoCombustivel) && 
                v.autonomiaBaixa() == false && v.autonomiaSuficiente(destino) && v.getLocalizacao().distancia(localizacao) <= distanciaMax){
                    maisBarato.add(v.clone());
            } 
        });
        
        if (maisBarato.isEmpty()){
            throw new VeiculoNaoEncontradoException(" a " + distanciaMax + " Km's de si.");
        }
        
        maisBarato.sort(comparaPrecos);
        double precoMenor = maisBarato.get(0).getPreco(); 
        
        for (Veiculo veiculo : maisBarato){
            if (veiculo.getPreco() <= precoMenor) resultado.add(veiculo.clone());
        }
        return resultado;
    }
    
    // Devolver um veículo expecífico
    
    public Veiculo veiculoEspecifico (String matricula, Ponto destino) 
    throws GestorVazioException, VeiculoNaoExisteException, VeiculoNaoEncontradoException{
        
        if (this.veiculos.isEmpty()) {
            throw new GestorVazioException("");
        }
        if(!this.veiculos.containsKey(matricula)){
            throw new VeiculoNaoExisteException(matricula);
        }
        
        Veiculo veiculo = this.veiculos.get(matricula);
        
        if(veiculo.getAutonomiaAtual() < veiculo.getLocalizacao().distancia(destino)){
            throw new VeiculoNaoEncontradoException(" com a matricula " + veiculo.getMatricula() + ".");
        }
        return veiculo.clone();
    }
    
    // Devolver um veículo com uma autonomia expecífica
    
    public List<Veiculo> veiculoAutonomia (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, double autonomiaAtual) 
    throws GestorVazioException, VeiculoNaoEncontradoException{
        
        if (this.veiculos.isEmpty()) {
            throw new GestorVazioException("");
        }

        List<Veiculo> resultado = new ArrayList<>();

        for(Veiculo veiculo : this.veiculos.values()){
            if (veiculo.getTipoVeiculo().equals(tipoVeiculo) && veiculo.getTipoCombustivel().equals(tipoCombustivel) && 
                veiculo.getAutonomiaAtual() == autonomiaAtual && veiculo.autonomiaBaixa() == false){ 
                    resultado.add(veiculo.clone());
            }
        }

        if (resultado.isEmpty()){
            throw new VeiculoNaoEncontradoException(" com a autonomia atual de " + autonomiaAtual);
        }
        return resultado;
    }
    
    // Devolver o veículo mais barato (ler do ficheiro)

    public List<Veiculo> veiculoMaisBaratoFile (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, Ponto destino) 
    throws GestorVazioException, VeiculoNaoEncontradoException{

        if (this.veiculos.isEmpty()){
            throw new GestorVazioException("Ups! Gestor de veículos vazio.");
        }

        List<Veiculo> resultado = new ArrayList<>();
        List<Veiculo> maisBarato = new ArrayList<>();

        this.veiculos.forEach((k,v) -> {
            if (v.getTipoVeiculo().equals(tipoVeiculo) && v.getTipoCombustivel().equals(tipoCombustivel)){
                    maisBarato.add(v.clone());
            }
        });

        if (maisBarato.isEmpty()){
            throw new VeiculoNaoEncontradoException(".");
        }

        maisBarato.sort(comparaPrecos);
        double precoMenor = maisBarato.get(0).getPreco();

        for (Veiculo veiculo : maisBarato){
            if (veiculo.getPreco() <= precoMenor) resultado.add(veiculo.clone());
        }
        return resultado;
    }
    
    // Devolver o veículo mais perto (ler do ficheiro)

    public List<Veiculo> veiculoMaisPertoFile (TipoVeiculo tipoVeiculo, TipoCombustivel tipoCombustivel, Ponto localizacao, Ponto destino) 
    throws GestorVazioException, VeiculoNaoEncontradoException{

        if (this.veiculos.isEmpty()) {
            throw new GestorVazioException("");
        }

        List<Veiculo> resultado = new ArrayList<>();
        List<Veiculo> maisPerto = new ArrayList<>();

        this.veiculos.forEach((k,v) -> {
            if (v.getTipoVeiculo().equals(tipoVeiculo) && v.getTipoCombustivel().equals(tipoCombustivel)){
                    maisPerto.add(v.clone());
            }
        });

        if (maisPerto.isEmpty()){ 
            throw new VeiculoNaoEncontradoException(".");
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
    
    // Retornar os veículos de um proprietário 
    
    public List<String> redacaoVeiculosProprietario (int nif) throws GestorVazioException{
        if (this.veiculos.isEmpty()){
            throw new GestorVazioException("");
        }
        List<String> resultado = new ArrayList<>();
        for(Veiculo v : this.veiculos.values()){
            if(v.getNif() == nif){
                resultado.add(v.toString());
            }
        }
        return resultado;
    }
    
    // Escrever para um ficheiro
    
    public void writeFile() throws IOException{
        PrintWriter file = new PrintWriter("GestorVeiculos.txt");
        file.println(" -- GestorVeiculos -- ");
        
        for (Veiculo v : this.veiculos.values()){
            
            String tv = "";
            String tc = "";
            
            if (v.getTipoVeiculo().equals(TipoVeiculo.Carro)) tv = "Carro";
            if (v.getTipoCombustivel().equals(TipoCombustivel.Gasolina)) tc = "Gasolina";
            if (v.getTipoCombustivel().equals(TipoCombustivel.Electrico)) tc = "Electrico";
            if (v.getTipoCombustivel().equals(TipoCombustivel.Hibrido)) tc = "Hibrido";
            
            file.println("Veiculo:" + tv + "," + tc + "," + v.getMarca() + "," + v.getMatricula() + "," + v.getNif() + "," + 
                         v.getVelocidadeMedia() + "," + v.getPreco() + "," + v.getConsumo() + "," + v.getAutonomiaMax() + "," +
                         v.getLocalizacao().getX() + "," + v.getLocalizacao().getY() + "," + v.getAutonomiaAtual() + "," + v.getClassificacao());
            
            String classificar = "Classificar:";
            for (Double classificacao : v.getClassificacoes()){
                classificar = classificar + classificacao + ",";
            }
            file.println(classificar);
        }
        file.flush();
        file.close();
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