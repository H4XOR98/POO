
import java.util.*;

public class Veiculos{
    
    // Variáveis de Instância
    
    private Collection<Veiculo> veiculos;
    
    //Comparator<Veiculo> comparadorPreco = (a,b) -> (a.getPreco().compareTo(b.getPreco()));
    
    //Comparator<Veiculo> comparadorMatriculas = (a,b) -> (a.getMatricula().compareTo(b.getMatricula()));
    
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
        this.veiculos.forEach((v -> aux.add(v.clone())));
        return aux;
    }
    
    // Sets
    
    public void setVeiculos(Collection<Veiculo> veiculos){
        this.veiculos = new HashSet<>();
        veiculos.forEach((v -> this.veiculos.add(v.clone())));
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
        this.veiculos.add(v.clone());
    }
    
    // Remove um veículo
    
    public void removeVeiculo(Veiculo v){
        this.veiculos.remove(v);
    }
    
    // Liberta os Veículos
    
    public void libertaVeiculos(){
        this.veiculos.clear();
    }
    
}