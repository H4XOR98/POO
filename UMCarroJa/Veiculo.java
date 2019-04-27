
import java.util.*;

public abstract class Veiculo{
    
    private String info;
    private boolean disponivel;
    private Ponto localizacao;
    private double classificacao;
    
    // Construtores
    
    public Veiculo(){
        this.info = " ";
        this.disponivel = false;
        this.localizacao = new Ponto();
        this.classificacao = 0;
    }
    
    public Veiculo (Veiculo v){
        this.info = v.getInfo();
        this.disponivel = v.getDisponivel();
        this.localizacao = v.getLocalizacao();
        this.classificacao = v.getClassificacao();
    }
    
    public Veiculo (String info, boolean disponivel, Ponto localizacao, double classificacao){
        this.info = info;
        this.disponivel = disponivel;
        this.localizacao = localizacao;
        this.classificacao = classificacao;
    }
    
    // Gets
    
    public String getInfo(){
        return this.info;
    }
    
    public boolean getDisponivel(){
        return this.disponivel;
    }
    
    public Ponto getLocalizacao(){
        return this.localizacao;
    }
    
    public double getClassificacao(){
        return this.classificacao;
    }
           
    // Sets
    
    public void setInfo (String newInfo){
        this.info = newInfo;
    }
    
    public void setDisponivel (boolean newDisponivel){
        this.disponivel = newDisponivel;
    }
    
    public void setLocalizacao (Ponto newLocalizacao){
        this.localizacao = newLocalizacao;
    }
    
    public void setClassificacao (double newClassificacao){
        this.classificacao = newClassificacao;
    }
    
    // Clone
    
    public abstract Veiculo clone();
        
    // Equals
    
    public boolean equals (Object o){
        if (this == o)
          return true;
          
        if((o == null) || (this.getClass() != o.getClass()))
          return false;
          
        Veiculo v = (Veiculo) o;
        return (this.info.equals(v.getInfo()) && this.disponivel == v.getDisponivel() && this.localizacao.equals(v.getLocalizacao()) &&
                this.classificacao == v.getClassificacao());
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Info: " + this.info);
        sb.append("Disponível: " + this.disponivel);
        sb.append("Localização: " + this.localizacao.toString());
        sb.append("Classificação: " + this.classificacao);
        return sb.toString();
    }
    
}
