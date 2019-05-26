package MyLogic.Gestores;

import MyLogic.ClassesBase.*;
import java.util.*;
import java.text.DecimalFormat;
import MyLogic.Exceptions.*;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.lang.ClassNotFoundException;

public class GestorAlugueres implements Serializable{
    
    // Variáveis de Instância
    
    private Map<Integer, Aluguer> alugueres;
    
    // Comparators
    
    Comparator<Aluguer> compKms = (a,b) -> { if (a.getDistancia() == b.getDistancia()) return 0;
                                             if (a.getDistancia() >  b.getDistancia()) return 1;
                                             else return -1;
                                           };
    
    Comparator<Integer> compNumVezes = (a,b) -> a - b;
    
    // Construtores
    
    public GestorAlugueres(){
        this.alugueres = new HashMap<>();
    }
    
    public GestorAlugueres(GestorAlugueres gestorAlugueres){
        this.alugueres = gestorAlugueres.getAlugueres();
    }
    
    // Gets
    
    public Map<Integer, Aluguer> getAlugueres(){
        Map<Integer,Aluguer> aux = new HashMap<>();
        this.alugueres.forEach((k,v) -> aux.put(k,v.clone()));
        return aux;
    }
    
    // Sets
    
    public void setAlugueres (Map<Integer,Aluguer> alugueres){
        this.alugueres = new HashMap<>();
        alugueres.forEach((k,v) -> this.alugueres.put(k,v.clone()));
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
        return this.alugueres.equals(a.getAlugueres());
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("----------- Gestor Alugueres -----------\n");
        for(Aluguer a : this.alugueres.values()){
            sb.append(a.toString());
        }
        return sb.toString();
    }
    
    // Adiciona um Aluguer
    
    public void insereAluguer(Aluguer a) throws AluguerJaExisteException{
        if(this.alugueres.containsKey(a.getId())){
            throw new AluguerJaExisteException("");
        }
        this.alugueres.put(a.getId(), a.clone());
    }
    
    public void atualizaAluguer(Aluguer a){
        this.alugueres.replace(a.getId(), a.clone());
    }
    
    public void removeAluguer(Aluguer a){
        this.alugueres.remove(a.getId());
    }
    
    // Liberta os Veículos
    
    public void libertaGestorAlugueres(){
        this.alugueres.clear();
    }
    
    
    public Aluguer getAluguer(int id) throws AluguerNaoExisteException{
        if (!this.alugueres.containsKey(id)){
            throw new AluguerNaoExisteException("");
        }
        return this.alugueres.get(id);
    }
    
    
    //-----------------------------------------
    
    
    public List<String> historicoCliente (int nif){
        List<String> aux = new ArrayList<>();
        this.alugueres.forEach((k,v) -> {
            if (v.getNif() == nif) aux.add(v.toString());
        });
        return aux;
    }
    
    public List<String> historicoClienteEntreDatas (int nif, LocalDateTime inicio, LocalDateTime fim){
        List<String> aux = new ArrayList<>();
        this.alugueres.forEach((k,v) -> {
            if(v.getNif() == nif && ((v.getDataInicio().isAfter(inicio) && v.getDataInicio().isBefore(fim)) || 
                                     (v.getDataInicio().isEqual(inicio) && v.getDataInicio().isEqual(fim)))){
                aux.add(v.toString());
            }
        });
        return aux;
    }
    
    
    public List<String> historicoProprietario(int nif){
        List<String> aux = new ArrayList<>();
        this.alugueres.forEach((k,v) -> {
            if(v.getVeiculo().getNif() == nif){
                aux.add(v.toString());
            }
        });
        return aux;
    }
    
    
    public List<String> historicoProprietarioEntreDatas (int nif, LocalDateTime inicio, LocalDateTime fim){
        List<String> aux = new ArrayList<>();
        this.alugueres.forEach((k,v) -> {
            if(v.getVeiculo().getNif() == nif && ((v.getDataInicio().isAfter(inicio) && v.getDataInicio().isBefore(fim)) || 
                                                  (v.getDataInicio().isEqual(inicio) && v.getDataInicio().isEqual(fim)))){
                aux.add(v.toString());
            }
        });
        return aux;
    }
    
    public double totalFaturadoVeiculo (String matricula, LocalDateTime inicio, LocalDateTime fim){
        double totalFaturado = 0;
        for(Aluguer a : this.alugueres.values()){
            if(a.getVeiculo().getMatricula().equals(matricula) && ((a.getDataInicio().isAfter(inicio) && a.getDataInicio().isBefore(fim)) || 
                                     (a.getDataInicio().isEqual(inicio) && a.getDataInicio().isEqual(fim)))){
                totalFaturado += a.getCusto();
            }
        }
        
        DecimalFormat df = new DecimalFormat("#.##");      
        totalFaturado = Double.valueOf(df.format(totalFaturado));
        return totalFaturado;
    }
    
    
    
    public List<Integer> topDezClientesKms() throws AlugueresNaoExistemException {
        if(this.alugueres.isEmpty()){
            throw new AlugueresNaoExistemException("");
        }
        Set<Aluguer> top = new TreeSet<>(compKms);
        for(Aluguer a : this.alugueres.values()){
            top.add(a);
        }
        List<Integer> resultado = new ArrayList<>();
        Iterator<Aluguer> it = top.iterator();
        Aluguer a = null;
        int i = 0;
        while(it.hasNext() && i<10){
            a = it.next();
            if(!resultado.contains(a.getNif())){
                resultado.add(a.getNif());
                i++;
            }
        }
        return resultado;
    }
    
    
    public List<Integer> topDezClientesVezes() throws AlugueresNaoExistemException {
        if(this.alugueres.isEmpty()){
            throw new AlugueresNaoExistemException("");
        }
        Map<Integer,Integer> top = new TreeMap(compNumVezes);
        for(Aluguer a : this.alugueres.values()){
            int nif = a.getNif();
            if(top.containsKey(nif)){
                int n = top.get(nif);
                top.put(nif,n++);
            }else{
                top.put(nif,1);
            }
        }
        List<Integer> resultado = new ArrayList();
        Iterator<Integer> it = top.keySet().iterator();
        int x = 0, i = 0;
        while(it.hasNext() && i < 10){
            x = it.next();
            resultado.add(x);
            i++;
        }
        return resultado;
    }
    
    // Guardar Estado para um ficheiro
    
    public void saveStatus() throws IOException{
        FileOutputStream fos = new FileOutputStream("GestorAlugueres_Status.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(this.alugueres);
        fos.close();
        oos.close();
    }
    
    // Carregar Estado de um ficheiro
    
    public GestorAlugueres loadStatus() throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fis = new FileInputStream("GestorAlugueres_Status.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        GestorAlugueres ga = new GestorAlugueres();
        ga.alugueres = (HashMap<Integer,Aluguer>) ois.readObject();
        fis.close();
        ois.close();
        return ga;
    }
    
}
