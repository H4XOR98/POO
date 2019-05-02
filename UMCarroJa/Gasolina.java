
import java.util.*;

public class Gasolina extends Veiculo{
    
    // Variáveis de Instância
    
    private double velocidadeMedia;
    private double autonomia;
    private double consumo;
    
    // Construtores
    
    public Gasolina(){
        super();
        this.velocidadeMedia = 0;
        this.autonomia = 0;
        this.consumo = 0;
    }
    
    public Gasolina (Gasolina g){
        super(g);
        this.velocidadeMedia = g.getVelocidadeMedia();
        this.autonomia = g.getAutonomia();
        this.consumo = g.getConsumo();
    }
    
    public Gasolina (TipoVeiculo tipoVeiculo, int nifProprietario, String matricula, boolean disponivel, Ponto localizacao, double preco,
                     double classificacao, Collection<Integer> classificacoes, double velocidadeMedia, double autonomia, double consumo){
        super(tipoVeiculo, nifProprietario, matricula, disponivel, localizacao, preco, classificacao, classificacoes);
        this.velocidadeMedia = velocidadeMedia;
        this.autonomia = autonomia;
        this.consumo = consumo;
    }
    
    // Gets
    
    public double getVelocidadeMedia(){
        return this.velocidadeMedia;
    }
    
    public double getAutonomia(){
        return this.autonomia;
    }
      
    public double getConsumo(){
        return this.consumo;
    }
    
    // Sets
    
    public void setVelocidadeMedia (double newVelocidadeMedia){
        this.velocidadeMedia = newVelocidadeMedia;
    }
    
    public void setAutonomia (double newAutonomia){
        this.autonomia = newAutonomia;
    }
    
    public void setConsumo (double newConsumo){
        this.consumo = newConsumo;
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
        return super.equals(g) && this.velocidadeMedia == g.getVelocidadeMedia() && this.autonomia == g.getAutonomia() &&
               this.consumo == g.getConsumo();
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Velocidade Média: " + this.velocidadeMedia + " Km/h\n");
        sb.append("Autonomia: " + this.autonomia + " %\n");
        sb.append("Consumo: " + this.consumo + " %/Km\n");
        return sb.toString();
    }
    
}