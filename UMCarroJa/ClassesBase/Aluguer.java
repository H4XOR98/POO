package ClassesBase;

import Exceptions.*;
import java.time.temporal.ChronoUnit;
import java.time.LocalDateTime;

public class Aluguer{
    
    // Variáveis de Instância
    private int nif;
    private Ponto destino;
    private TipoCombustivel tipoCombustivel;
    private PreferenciaAluguer preferencia;


    public Aluguer() {
        this.nif = 0;
        this.destino = new Ponto();
        this.tipoCombustivel = TipoCombustivel.Gasolina;
        this.preferencia = PreferenciaAluguer.MaisPerto;
    }


    public Aluguer(int nif, Ponto destino, TipoCombustivel tipoCombustivel, PreferenciaAluguer preferencia) {
        this.nif = nif;
        this.destino = destino;
        this.tipoCombustivel = tipoCombustivel;
        this.preferencia = preferencia;
    }

    public Aluguer(Aluguer a){
        this.nif = a.getNif();
        this.destino = a.getDestino();
        this.tipoCombustivel = a.getTipoCombustivel();
        this.preferencia = a.getPreferencia();
    }


    //getters

    public int getNif() {
        return this.nif;
    }
    
    public Ponto getDestino() {
        return this.destino;
    }

    public TipoCombustivel getTipoCombustivel() {
        return this.tipoCombustivel;
    }

    public PreferenciaAluguer getPreferencia() {
        return this.preferencia;
    }

    //setters

    public void setNif(int nif) {
        this.nif = nif;
    }

    public void setDestino(Ponto destino) {
        this.destino = destino;
    }

    public void setTipoCombustivel(TipoCombustivel tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public void setPreferencia(PreferenciaAluguer preferencia) {
        this.preferencia = preferencia;
    }

    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("............ALUGUER............\n");
        sb.append("Destino: \n\t" + this.destino.toString() + ";\n");
        sb.append("Nif: " + this.nif + ";\n");
        sb.append("Tipo de Combustivel: " + this.tipoCombustivel + ";\n");
        sb.append("Preferencia: " + this.preferencia + "\n");
        return sb.toString();
    }
    
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        Aluguer a = (Aluguer)o;
        
        return (this.destino.equals(a.getDestino()) && this.nif==a.getNif() && this.tipoCombustivel.equals(a.getTipoCombustivel()) &&
                this.preferencia == a.getPreferencia());
    }
    
    
    public Aluguer clone(){
        return new Aluguer(this);
    }   
    
    
    
    public Veiculo determinaVeiculo (PreferenciaAluguer preferencia){
        Veiculo vei = new Veiculo();
        return vei;
    }
}