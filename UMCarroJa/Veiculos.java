import java.util.*;
/**
 * Escreva a descrição da classe Veiculos aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Veiculos
{
    /*
    private Collection<Veiculo> veiculos;
    
    public Veiculos(){
        this.veiculos = new HashSet<>();
    }
    
    public Veiculos(Collection<Veiculo> veiculos){
        setVeiculos(veiculos);
    }
    
    public Veiculos(Veiculos veiculos){
        this.veiculos = veiculos.getVeiculos();
    }
    
    
    public Collection<Veiculo> getVeiculos(){
        Collection<Veiculo> aux = new HashSet<>();
        this.veiculos.forEach((v -> aux.add(v.clone())));
        return aux;
    }
    
    public void setVeiculos(Collection<Veiculo> veiculos){
        this.veiculos = new HashSet<>();
        veiculos.forEach((v -> this.veiculos.add(v.clone())));
    }
    
    
    public void addVeiculo(Veiculo v){
        this.veiculos.add(v.clone());
    }
    
    public void removeVeiculo(Veiculo v){
        this.veiculos.remove(v);
    }
    
    public void libertaVeiculos(){
        this.veiculos.clear();
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Veiculo v : this.veiculos){
            sb.append(v.toString());
        }
        return sb.toString();
    }
    
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
    
    public Veiculos clone(){
        return new Veiculos(this);
    }
    */
}
