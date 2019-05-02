
import java.util.*;

public class Gasolina extends Veiculo{
    
    private double velocidadeMedia;
    private double consumo;
    private double autonomia;
    private double preco;
    
    // Construtores
    
    public Gasolina(){
        super();
        this.velocidadeMedia = 0;
        this.consumo = 0;
        this.autonomia = 0;
        this.preco = 0;
    }
    
    public Gasolina (Gasolina g){
        super(g);
        this.velocidadeMedia = g.getVelocidadeMedia();
        this.consumo = g.getConsumo();
        this.autonomia = g.getAutonomia();
        this.preco = g.getPreco();
    }
    
    public Gasolina (TipoVeiculo tipoVeiculo, int nifProprietario, String matricula, boolean disponivel, Ponto localizacao,
                     double classificacao, Collection<Integer> classificacoes, double velocidadeMedia, double consumo, double autonomia, 
                     double preco){
        super(tipoVeiculo, nifProprietario, matricula, disponivel, localizacao, classificacao, classificacoes);
        this.velocidadeMedia = velocidadeMedia;
        this.consumo = consumo;
        this.autonomia = autonomia;
        this.preco = preco;
    }
    
    // Gets
    
    public double getVelocidadeMedia(){
        return this.velocidadeMedia;
    }
    
    public double getConsumo(){
        return this.consumo;
    }
    
    public double getAutonomia(){
        return this.autonomia;
    }
    
    public double getPreco(){
        return this.preco;
    }
    
    // Sets
    
    public void setVelocidadeMedia (double newVelocidadeMedia){
        this.velocidadeMedia = newVelocidadeMedia;
    }
    
    public void setConsumo (double newConsumo){
        this.consumo = newConsumo;
    }
    
    public void setAutonomia (double newAutonomia){
        this.autonomia = newAutonomia;
    }
    
    public void setPreco (double newPreco){
        this.preco = newPreco;
    }
    
    // Clone
    
    public Gasolina clone(){
        return new Gasolina(this);
    }
    
    // Equals
    
    public boolean equals (Object o){
        if (this == o)
          return true;
          
        if((o == null) || (this.getClass() != o.getClass()))
          return false;
          
        Gasolina g = (Gasolina) o;
        return (super.equals(g) && this.velocidadeMedia == g.getVelocidadeMedia() && this.consumo == g.getConsumo() &&
                this.autonomia == g.getAutonomia() && this.preco == g.getPreco());
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Velocidade Média: " + this.velocidadeMedia + " Km/h;\n");
        sb.append("Consumo: " + this.consumo + " L/Km;\n");
        sb.append("Autonomia: " + this.autonomia + " %;\n");
        sb.append("Preco: " + this.preco + " €/Km;\n");
        return sb.toString();
    }
    
}