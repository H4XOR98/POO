import java.util.*;
/**
 * Escreva a descrição da classe Veiculos aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Veiculos
{
    private Map<Integer,Collection<Veiculo>> veiculos;
    //private Collection<Veiculo> veiculos;
    
    public Veiculos(){
        this.veiculos = new HashMap<>();
    }
    
    public Veiculos(Map<Integer,Collection<Veiculo>> veiculos){
        setVeiculos(veiculos);
    }
    
    public Veiculos(Veiculos veiculos){
        this.veiculos = veiculos.getVeiculos();
    }
    
    
    public Collection<Veiculo> getV(){
        Collection<Veiculo> aux = new HashSet<>();
        this.veiculos.forEach((v -> aux.add(v.clone())));
        return aux;
    }
    
    public void setV(Collection<Veiculo> veiculos){
        this.veiculos = new HashSet<>();
        veiculos.forEach((v -> this.veiculos.add(v.clone())));
    }
    
   
    public Map<Integer,Collection<Veiculo>> getVeiculos(){
        Map<Integer,Collection<Veiculo>> newVeiculos = new HashMap<>();
        Collection<Veiculo> aux = new ArrayList<>();
        this.veiculos.forEach((k,v) -> newVeiculos.put(k, aux.add(v.getV())));
        return newVeiculos;
    }
   
    /*
    public Collection<Veiculo> getVeiculos(){
        Map<Integer,Collection<Veiculo>> aux = new HashMap<>();
        for(Integer nif : this.veiculos.keySet()){
            for(Veiculo v : this.veiculos.get(nif)){
                aux.put(nif,v.clone());
            }
        }
        return aux;
    }*/
    
    
}
