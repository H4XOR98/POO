
public class Hibrido extends Veiculo{
    
    private double velocidadeMedia;
    private double consumoG;
    private double consumoE;
    private double autonomiaG;
    private double autonomiaE;
    private double precoG;
    private double precoE;
    
    // Construtores
    
    public Hibrido(){
        this.velocidadeMedia = 0;
        this.consumoG = 0;
        this.consumoE = 0;
        this.autonomiaG = 0;
        this.autonomiaE = 0;
        this.precoG = 0;
        this.precoE = 0;
    }
    
    public Hibrido (Hibrido h){
        this.velocidadeMedia = h.getVelocidadeMedia();
        this.consumoG = h.getConsumoG();
        this.consumoE = h.getConsumoE();
        this.autonomiaG = h.getAutonomiaG();
        this.autonomiaE = h.getAutonomiaE();
        this.precoG = h.getPrecoG();
        this.precoE = h.getPrecoE();
    }
    
    public Hibrido (double velocidadeMedia, double consumoG, double consumoE, double autonomiaG, double autonomiaE, double precoG, double precoE){
        this.velocidadeMedia = velocidadeMedia;
        this.consumoG = consumoG;
        this.consumoE = consumoE;
        this.autonomiaG = autonomiaG;
        this.autonomiaE = autonomiaE;
        this.precoG = precoG;
        this.precoE = precoE;
    }
    
    // Gets
    
    public double getVelocidadeMedia(){
        return this.velocidadeMedia;
    }
    
    public double getConsumoG(){
        return this.consumoG;
    }
    
    public double getConsumoE(){
        return this.consumoE;
    }
    
    public double getAutonomiaG(){
        return this.autonomiaG;
    }
    
    public double getAutonomiaE(){
        return this.autonomiaE;
    }
    
    public double getPrecoG(){
        return this.precoG;
    }
    
    public double getPrecoE(){
        return this.precoE;
    }
    
    // Sets
    
    public void setVelocidadeMedia (double newVelocidadeMedia){
        this.velocidadeMedia = newVelocidadeMedia;
    }
    
    public void setConsumoG (double newConsumoG){
        this.consumoG = newConsumoG;
    }
    
    public void setConsumoE (double newConsumoE){
        this.consumoE = newConsumoE;
    }
    
    public void setAutonomiaG (double newAutonomiaG){
        this.autonomiaG = newAutonomiaG;
    }
    
    public void setAutonomiaE (double newAutonomiaE){
        this.autonomiaE = newAutonomiaE;
    }
    
    public void setPrecoG (double newPrecoG){
        this.precoG = newPrecoG;
    }
    
    public void setPrecoE (double newPrecoE){
        this.precoE = newPrecoE;
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
        return (super.equals(h) && this.velocidadeMedia == h.getVelocidadeMedia() && this.consumoG == h.getConsumoG() &&
                this.consumoE == h.getConsumoE() && this.autonomiaG == h.getAutonomiaG() && this.autonomiaE == h.getAutonomiaE() &&
                this.precoG == h.getPrecoG() && this.precoE == h.getPrecoE());
    }
    
    // toString
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Velocidade Média: " + this.velocidadeMedia + " Km/h\n");
        sb.append("Gasolina\n");
        sb.append("  Consumo: " + this.consumoG + " L/Km\n");
        sb.append("  Autonomia: " + this.autonomiaG + " %\n");
        sb.append("  Preco: " + this.precoG + " €/Km\n");
        sb.append("Eletricidade\n");
        sb.append("  Consumo: " + this.consumoE + " W/Km\n");
        sb.append("  Autonomia: " + this.autonomiaE + " %\n");
        sb.append("  Preco: " + this.precoE + " €/Km\n");
        return sb.toString();
    }
    
}

