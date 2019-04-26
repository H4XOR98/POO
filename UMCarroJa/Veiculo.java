
import java.util.*;

public class Veiculo{
    
    private String info;
    private String tipo;
    private Ponto localizacao;
    private double classificacao;
    private double preco;
    private double velocidadeMedia;
    private double consumo;
    private double autonomia;
    private ArrayList <String> historico;
    
    // Construtores
    
    public Veiculo(){
        this.info = " ";
        this.tipo = " ";
        this.localizacao = new Ponto();
        this.classificacao = 0;
        this.preco = 0;
        this.velocidadeMedia = 0;
        this.consumo = 0;
        this.autonomia = 0;
        this.historico = new ArrayList<String>();
    }
    
    public Veiculo (Veiculo v){
        this.info = v.getInfo();
        this.tipo = v.getTipo();
        this.localizacao = v.getLocalizacao();
        this.classificacao = v.getClassificacao();
        this.preco = v.getPreco();
        this.velocidadeMedia = v.getVelocidadeMedia();
        this.consumo = v.getConsumo();
        this.autonomia = v.getAutonomia();
        this.historico = v.getHistorico();
    }
    
    public Veiculo (String info, String tipo, Ponto localizacao, double classificacao, double preco, double velocidadeMedia, 
                     double consumo, double autonomia, ArrayList<String> historico){
        this.info = info;
        this.tipo = tipo;
        this.localizacao = localizacao;
        this.classificacao = classificacao;
        this.preco = preco;
        this.velocidadeMedia = velocidadeMedia;
        this.consumo = consumo;
        this.autonomia = autonomia;
        this.historico = historico;
    }
    
    // Gets
    
    public String getInfo(){
        return this.info;
    }
    
    public String getTipo(){
        return this.tipo;
    }
    
    public Ponto getLocalizacao(){
        return this.localizacao;
    }
    
    public double getClassificacao(){
        return this.classificacao;
    }
    
    public double getPreco(){
        return this.preco;
    }
    
    public double getVelocidadeMedia(){
        return this.velocidadeMedia;
    }
    
    public double getConsumo(){
        return this.consumo;
    }
    
    public double getAutonomia(){
        return this.autonomia;
    }
    
    public ArrayList<String> getHistorico(){
        return this.historico;
    }
        
    // Sets
    
    public void setInfo (String newInfo){
        this.info = newInfo;
    }
    
    public void setTipo (String newTipo){
        this.tipo = newTipo;
    }
    
    public void setLocalizacao (Ponto newLocalizacao){
        this.localizacao = newLocalizacao;
    }
    
    public void setClassificacao (double newClassificacao){
        this.classificacao = newClassificacao;
    }
    
    public void setPreco (double newPreco){
        this.preco = newPreco;
    }
    
    public void setVelocidadeMedia (double newVelocidadeMedia){
        this.velocidadeMedia = newVelocidadeMedia;
    }
    
    public void setConsumo (double newConsumo){
        this.consumo = newConsumo;
    }
    
    public void setAutonomia (double newAutonomia){
        this.autonomia = newAutonomia;
    }
    
    public void setHistorico (ArrayList<String> newHistorico){
        this.historico = newHistorico;
    }
    
    // Clone
    
    public Veiculo clone(){
        return new Veiculo(this);
    }
        
    // Equals
    
    public boolean equals (Object o){
        if (this == o)
          return true;
          
        if((o == null) || (this.getClass() != o.getClass()))
          return false;
          
        Veiculo v = (Veiculo) o;
        return (this.info.equals(v.getInfo()) && this.tipo.equals(v.getTipo()) && this.localizacao.equals(v.getLocalizacao()) &&
                this.classificacao == v.getClassificacao() && this.preco == v.getPreco() && this.velocidadeMedia == v.getVelocidadeMedia() &&
                this.consumo == v.getConsumo() && this.autonomia == v.getAutonomia() && this.historico.equals(v.getHistorico()));
    }
    
    // toString
    
    public String toString(){
        return "Info: " + this.info + "\n" +
               "Tipo:"  + this.tipo + "\n" +
               "Localizacao:" + this.localizacao + "\n";
    }
    
    
    
    
    
    
   
    
    
    
}
