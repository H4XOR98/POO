import java.util.*;
/**
 * Escreva a descrição da classe GestorAlugueres aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class GestorAlugueres
{
    private Collection<Aluguer> alugueres;
    
    
    public GestorAlugueres(){
        this.alugueres = new HashSet<>();
    }
    
    public GestorAlugueres(GestorAlugueres alugueres){
        this.alugueres = alugueres.getGestorAlugueres();
    }
    
    public GestorAlugueres(Collection<Aluguer> alugueres){
        setGestorAlugueres(alugueres);
    }
    
    // Gets
    
    public Collection<Aluguer> getGestorAlugueres(){
        Collection<Aluguer> aux = new HashSet<>();
        for(Aluguer a : this.alugueres){
            aux.add(a.clone());
        }
        return aux;
    }
    
    // Sets
    
    public void setGestorAlugueres(Collection<Aluguer> alugueres){
        this.alugueres = new HashSet<>();
        for(Aluguer a : alugueres){
            this.alugueres.add(a.clone());
        }
    }
    
    // Clone
    
    public GestorAlugueres clone(){
        return new GestorAlugueres(this);
    }
    
    // Equals
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        GestorAlugueres a = (GestorAlugueres)o;
        return this.alugueres.equals(a.getGestorAlugueres());
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Aluguer a : this.alugueres){
            sb.append(a.toString());
        }
        return sb.toString();
    }
    
    // Adiciona um veículo
    
    public void addAluguer(Aluguer a){
        if(!this.alugueres.contains(a)){
            this.alugueres.add(a.clone());
        }
    }
    
    // Remove um veículo
    
    public void removeAluguer(Aluguer a){
        if(this.alugueres.contains(a)){
            this.alugueres.remove(a);
        }
    }
    
    // Liberta os Veículos
    
    public void libertaGestorAlugueres(){
        this.alugueres.clear();
    }
    
    
    public Collection<Aluguer> historicoCliente(Cliente cliente){
        Collection<Aluguer> aux = new HashSet<>();
        for(Aluguer a : this.alugueres){
            if(a.getCliente().equals(cliente)){
                aux.add(a);//não necessita de clone, pois é uma "visao"
            }
        }
        return aux;
    }
    
    
    public Collection<Aluguer> historicoVeiculo(Veiculo veiculo){
        Collection<Aluguer> aux = new HashSet<>();
        for(Aluguer a : this.alugueres){
            if(a.getVeiculo().equals(veiculo)){
                aux.add(a);//não necessita de clone, pois é uma "visao"
            }
        }
        return aux;
    }
    
    
    public double totalFaturadoProprietario(int nif){
        double totalFaturado = 0;
        for(Aluguer a : this.alugueres){
            if(a.getVeiculo().getNif() == nif){
                totalFaturado += a.getPrecoViagem();
            }
        }
        return totalFaturado;
    }
}
