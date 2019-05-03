
import java.util.*;

public class Hibrido extends Veiculo{

    // Variáveis de Instância
    
    private double velocidadeMedia;
    private double autonomiaG;
    private double autonomiaE;
    private double consumoG;
    private double consumoE;
  
    // Construtores
    
    public Hibrido(){
        super();
        this.velocidadeMedia = 0;
        this.autonomiaG = 0;
        this.autonomiaE = 0;
        this.consumoG = 0;
        this.consumoE = 0;
    }
    
    public Hibrido (Hibrido h){
        super(h);
        this.velocidadeMedia = h.getVelocidadeMedia();
        this.autonomiaG = h.getAutonomiaG();
        this.autonomiaE = h.getAutonomiaE();
        this.consumoG = h.getConsumoG();
        this.consumoE = h.getConsumoE();
    }
    
    public Hibrido (TipoVeiculo tipoVeiculo,int nifProprietario, String matricula, boolean disponivel, Ponto localizacao, double preco,
                    double numTotalKms,double classificacao, Collection<Integer> classificacoes, double velocidadeMedia, double autonomiaG, double autonomiaE,
                    double consumoG, double consumoE){
        super(tipoVeiculo, nifProprietario, matricula, disponivel, localizacao, preco,numTotalKms, classificacao, classificacoes);
        this.velocidadeMedia = velocidadeMedia;
        this.autonomiaG = autonomiaG;
        this.autonomiaE = autonomiaE;
        this.consumoG = consumoG;
        this.consumoE = consumoE;
    }
    
    // Gets
    
    public double getVelocidadeMedia(){
        return this.velocidadeMedia;
    }
    
    public double getAutonomiaG(){
        return this.autonomiaG;
    }
    
    public double getAutonomiaE(){
        return this.autonomiaE;
    }

    public double getConsumoG(){
        return this.consumoG;
    }
    
    public double getConsumoE(){
        return this.consumoE;
    }
          
    // Sets
    
    public void setVelocidadeMedia (double newVelocidadeMedia){
        this.velocidadeMedia = newVelocidadeMedia;
    }
    
    public void setAutonomiaG (double newAutonomiaG){
        this.autonomiaG = newAutonomiaG;
    }
    
    public void setAutonomiaE (double newAutonomiaE){
        this.autonomiaE = newAutonomiaE;
    }
    
    public void setConsumoG (double newConsumoG){
        this.consumoG = newConsumoG;
    }
    
    public void setConsumoE (double newConsumoE){
        this.consumoE = newConsumoE;
    }
    
    // Clone
    
    public Hibrido clone(){
        return new Hibrido(this);
    }
    
    // Equals
    
    public boolean equals (Object o){
        if (this == o)
          return true;
          
        if((o == null) || (this.getClass() != o.getClass()))
          return false;
          
        Hibrido h = (Hibrido) o;
        return super.equals(h) && this.velocidadeMedia == h.getVelocidadeMedia() && this.autonomiaG == h.getAutonomiaG() && 
               this.autonomiaE == h.getAutonomiaE() && this.consumoG == h.getConsumoG() && this.consumoE == h.getConsumoE();
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Velocidade Média: " + this.velocidadeMedia + " Km/h\n");
        sb.append("Gasolina\n");
        sb.append("\tAutonomia: " + this.autonomiaG + " %\n");
        sb.append("\tConsumo: " + this.consumoG + " %/Km\n");
        sb.append("Eletricidade\n");
        sb.append("\tAutonomia: " + this.autonomiaE + " %\n");
        sb.append("\tConsumo: " + this.consumoE + " W/Km\n");
        return sb.toString();
    }
    
    
    public void abastecerVeiculo(){
        this.autonomiaG = 100.0;
        this.autonomiaE = 100.0;
    }
    
    public double quantidadeCombustivel(){
        return ((this.autonomiaE+autonomiaG) * 100) / 200;
    }
}