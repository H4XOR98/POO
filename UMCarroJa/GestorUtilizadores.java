import java.util.*;
/**
 * Escreva a descrição da classe GestorUtilizador aqui.
 *
 * @author (seu nome)
 * @version (número de versão ou data)
 */
public class GestorUtilizadores {
    private Collection<Cliente> clientes;
    private Collection<Proprietario> proprietarios;


    public GestorUtilizadores() {
        this.clientes = new HashSet<>();
        this.proprietarios = new HashSet<>();
    }


    public GestorUtilizadores(Collection<Cliente> clientes, Collection<Proprietario> proprietarios) {
        setClientes(clientes);
        setProprietarios(proprietarios);
    }

    public GestorUtilizadores(GestorUtilizadores gUtilizadores) {
        gUtilizadores.getClientes();
        gUtilizadores.getProprietarios();
    }

    public Collection<Cliente> getClientes() {
        Collection<Cliente> aux = new HashSet<>();
        for (Cliente cliente : this.clientes) {
            aux.add(cliente.clone());
        }
        return aux;
    }

    public Collection<Proprietario> getProprietarios() {
        Collection<Proprietario> aux = new HashSet<>();
        for (Proprietario proprietario : this.proprietarios) {
            aux.add(proprietario.clone());
        }
        return aux;
    }

    public void setClientes(Collection<Cliente> clientes) {
        this.clientes = new HashSet<>();
        for (Cliente cliente : clientes) {
            this.clientes.add(cliente.clone());
        }
    }

    public void setProprietarios(Collection<Proprietario> proprietarios) {
        this.proprietarios = new HashSet<>();
        for (Proprietario proprietario : proprietarios)  {
            this.proprietarios.add(proprietario.clone());
        }
    }

    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        GestorUtilizadores gUtilizadores = (GestorUtilizadores)o;
        
        return this.clientes.equals(gUtilizadores.getClientes()) && this.proprietarios.equals(gUtilizadores.getProprietarios());
    }
    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("--------------Clientes--------------\n");
        for(Cliente cliente: this.clientes){
            sb.append(cliente.toString());
        }
        sb.append("--------------Proprietarios--------------\n");
        for(Proprietario proprietario: this.proprietarios){
            sb.append(proprietario.toString());
        }
        return sb.toString();
    }
    
    
    public GestorUtilizadores clone(){
        return new GestorUtilizadores(this);
    }
}
