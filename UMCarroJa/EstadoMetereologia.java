
/**
 * Enumeration class EstadoMetereologia - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum EstadoMetereologia
{
    Neve(1.8), Chuva(1.3), Nevoeiro(1.4), Vento(1.2), Nublado(1.1), Sol(1.0);
    
    private double percentagem;
    
    private EstadoMetereologia(double percentagem) {
        this.percentagem = percentagem;
    }
    
    public double getPercentagem(){
        return this.percentagem;
    }
    
    public static EstadoMetereologia getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}