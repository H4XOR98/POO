
import java.util.*;

public class Veiculos{
    
    // Variáveis de Instância
    
    private Collection<Veiculo> veiculos;
    
    
    // Construtores
    
    public Veiculos(){
        this.veiculos = new HashSet<>();
    }
    
    public Veiculos(Veiculos veiculos){
        this.veiculos = veiculos.getVeiculos();
    }
    
    public Veiculos(Collection<Veiculo> veiculos){
        setVeiculos(veiculos);
    }
    
    // Gets
    
    public Collection<Veiculo> getVeiculos(){
        Collection<Veiculo> aux = new HashSet<>();
        for(Veiculo v : this.veiculos){
            aux.add(v.clone());
        }
        return aux;
    }
    
    // Sets
    
    public void setVeiculos(Collection<Veiculo> veiculos){
        this.veiculos = new HashSet<>();
        for(Veiculo v : veiculos){
            this.veiculos.add(v.clone());
        }
    }
    
    // Clone
    
    public Veiculos clone(){
        return new Veiculos(this);
    }
    
    // Equals
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        Veiculos v = (Veiculos)o;
        return this.veiculos.equals(v.getVeiculos());
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Veiculo v : this.veiculos){
            sb.append(v.toString());
        }
        return sb.toString();
    }
    
    // Adiciona um veículo
    
    public void addVeiculo(Veiculo v){
        if(!this.veiculos.contains(v)){
            this.veiculos.add(v.clone());
        }
    }
    
    // Remove um veículo
    
    public void removeVeiculo(Veiculo v){
        if(this.veiculos.contains(v)){
            this.veiculos.remove(v);
        }
    }
    
    // Liberta os Veículos
    
    public void libertaVeiculos(){
        this.veiculos.clear();
    }
    
}