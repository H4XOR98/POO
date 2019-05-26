package MyLogic.Gestores;

import MyLogic.ClassesBase.*;
import MyLogic.Exceptions.*;
import java.util.*;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;

public class GestorUtilizadores implements Serializable{
    
    // Variáveis de Instância
    
    private Map<Integer,Utilizador> utilizadores;

    // Construtores
    
    public GestorUtilizadores() {
        this.utilizadores = new HashMap<>();
    }

    public GestorUtilizadores(GestorUtilizadores gUtilizadores) {
        gUtilizadores.getUtilizadores();
    }

    public Map<Integer,Utilizador> getUtilizadores() {
        Map<Integer,Utilizador> aux = new HashMap<>();
        for (Utilizador u : this.utilizadores.values()) {
            aux.put(u.getNif(),u.clone());
        }
        return aux;
    }

    // Sets
    
    public void setUtilizadores(Map<Integer,Utilizador> utilizadores) {
        this.utilizadores = new HashMap<>();
        for (Utilizador u : utilizadores.values()) {
            this.utilizadores.put(u.getNif(),u.clone());
        }
    }

    // Equals
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        GestorUtilizadores gUtilizadores = (GestorUtilizadores)o;
        
        return this.utilizadores.equals(gUtilizadores.getUtilizadores()); 
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-------------- Utilizadores --------------\n");
        for(Utilizador u: this.utilizadores.values()){
            sb.append(u.toString());
        }
        return sb.toString();
    }
    
    // Clone
    
    public GestorUtilizadores clone(){
        return new GestorUtilizadores(this);
    }
    
    //-------------
    
    public Notificacao insereUtilizador(Utilizador u) throws UtilizadorJaExisteException{
        if(this.utilizadores.containsKey(u.getNif())){
            throw new UtilizadorJaExisteException("" + u.getNif());
        }
        this.utilizadores.put(u.getNif(),u.clone());
        Notificacao n = new Notificacao(u.getNif(),"Boas Vindas","\nBem vindo à UMCarroJá!");
        return n;
    }
    
    public void atualizaUtilizador(Utilizador u){
        this.utilizadores.replace(u.getNif(),u.clone());
    }
    
    public void removeUtilizador(int nif) throws UtilizadorNaoExisteException{
        if(!this.utilizadores.containsKey(nif)){
            throw new UtilizadorNaoExisteException("" + nif);
        }
        this.utilizadores.remove(nif);
    }
    
    public void libertaUtilizadores(){
        this.utilizadores.clear();
    }
    
    public boolean emailRegistado(String email){
        for(Utilizador u : this.utilizadores.values()){
            if(u.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }
    
    public Utilizador loginUtilizador(String email, String password) throws UtilizadorNaoRegistadoException{
        Utilizador aux = null;
        for(Utilizador u : this.utilizadores.values()){
            if(email.equals(u.getEmail()) && password.equals(u.getPassword())){
                aux = u.clone();
            }
        }
        if(aux == null){
            throw new UtilizadorNaoRegistadoException("");
        }
        return aux;
    }
    
    public Utilizador getUtilizador(int nif) throws UtilizadorNaoExisteException{
        if(!this.utilizadores.containsKey(nif)){
            throw new UtilizadorNaoExisteException("" + nif);
        }
        return this.utilizadores.get(nif).clone();
    }
    
    public List<Cliente> getClientes(){
        List<Cliente> clientes = new ArrayList<>();
        for(Utilizador u : this.utilizadores.values()){
            if(u.getClass().getSimpleName().equals("Cliente")){
                Cliente c = (Cliente)u;
                clientes.add(c.clone());
            }
        }
        return clientes;
    }
    
    public List<Proprietario> getProprietarios(){
        List<Proprietario> proprietarios = new ArrayList<>();
        for(Utilizador u : this.utilizadores.values()){
            if(u.getClass().getSimpleName().equals("Proprietario")){
                Proprietario p = (Proprietario)u;
                proprietarios.add(p.clone());
            }
        }
        return proprietarios;
    }
    
    public List<String> procuraUtilizadores(List<Integer> nifs) throws UtilizadoresNaoExistemException, UtilizadorNaoExisteException{
        if(nifs.isEmpty()){
            throw new UtilizadoresNaoExistemException("");
        }
        List<String> utilitarios = new ArrayList<>();
        for(int n : nifs){
            utilitarios.add(getUtilizador(n).toString());
        }   
        return utilitarios;
    }
    
    // Guardar Estado para um ficheiro
    
    public void saveStatus() throws IOException{
        FileOutputStream fos = new FileOutputStream("GestorUtilizadores_Status.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.utilizadores);
        fos.close();
        oos.close();
    }
    
    // Carregar Estado de um ficheiro
    
    public GestorUtilizadores loadStatus() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("GestorUtilizadores_Status.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        GestorUtilizadores gu = new GestorUtilizadores();
        gu.utilizadores = (Map<Integer,Utilizador>) ois.readObject();
        fis.close();
        ois.close();
        return gu;
    }

}
