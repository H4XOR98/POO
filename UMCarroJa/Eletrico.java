
import java.util.*;

public class Eletrico extends Veiculo{
    
    // Variáveis de Instância
    
    private double velocidadeMedia;
    private double autonomia;
    private double consumo;
    
    // Construtores
    
    public Eletrico(){
        super();
        this.velocidadeMedia = 0;
        this.autonomia = 0;
        this.consumo = 0;
    }
    
    public Eletrico (Eletrico e){
        super(e);
        this.velocidadeMedia = e.getVelocidadeMedia();
        this.autonomia = e.getAutonomia();
        this.consumo = e.getConsumo();
    }
    
    public Eletrico (TipoVeiculo tipoVeiculo, int nifProprietario, String matricula, boolean disponivel, Ponto localizacao, double preco,
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
    
    public Eletrico clone(){
        return new Eletrico(this);
    }
    
    // Equals
    
    public boolean equals (Object o){
        if (this == o)
          return true;
          
        if((o == null) || (this.getClass() != o.getClass()))
          return false;
          
        Eletrico e = (Eletrico) o;
        return super.equals(e) && this.velocidadeMedia == e.getVelocidadeMedia() && this.autonomia == e.getAutonomia() &&
               this.consumo == e.getConsumo();
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