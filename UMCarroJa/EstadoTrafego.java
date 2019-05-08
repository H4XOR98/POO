
/**
 * Enumeration class EstadoTrafego - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum EstadoTrafego
{
    Fluido(1.05),Congestinado(1.6);
    
    private double percentagem;
    
    private EstadoTrafego(double percentagem) {
        this.percentagem = percentagem;
    }
    
    public double getPercentagem(){
        return this.percentagem;
    }
    
    public static EstadoTrafego getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
