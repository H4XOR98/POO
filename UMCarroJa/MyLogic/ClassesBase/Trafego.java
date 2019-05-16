package MyLogic.ClassesBase;


/**
 * Enumeration class EstadoTrafego - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum Trafego
{
    Fluido(1.05),Congestinado(1.6);
    
    private double percentagem;
    
    private Trafego(double percentagem) {
        this.percentagem = percentagem;
    }
    
    public double getPercentagem(){
        return this.percentagem;
    }
    
    public static Trafego getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
