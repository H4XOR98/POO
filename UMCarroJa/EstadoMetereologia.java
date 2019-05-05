
/**
 * Enumeration class EstadoMetereologia - write a description of the enum class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public enum EstadoMetereologia
{
    NEVE(0,1.8,"Neve"), CHUVA(1,1.3,"Chuva"), NEVOEIRO(2,1.4,"Nevoeiro"), VENTO(3,1.2,"Vento"), NUBLADO(4,1.1,"Nublado"), SOL(5,1.0,"Sol");
    
    private int id;
    private double percentagem;
    private String estado;
    
    private EstadoMetereologia(int id,double percentagem,String estado) {
        this.id = id;
        this.percentagem = percentagem;
        this.estado = estado;
    }
    
    public int getId(){
        return this.id;
    }
    
    public double getPercentagem(){
        return this.percentagem;
    }
    
    public String getEstado(){
        return this.estado;
    }
}

