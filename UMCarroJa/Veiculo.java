
import java.util.*;

public abstract class Veiculo{
    
    // Variáveis de Instância
    
    private TipoVeiculo tipoVeiculo;
    private int nifProprietario;
    private String matricula;
    private boolean disponivel;
    private Ponto localizacao;
    private double classificacao;
    private Collection<Integer> classificacoes;
    
    // Construtores
    
    public Veiculo(){
        this.tipoVeiculo = null;
        this.nifProprietario = 0;
        this.matricula = " ";
        this.disponivel = false;
        this.localizacao = new Ponto();
        this.classificacao = 0;
        this.classificacoes = new HashSet<>();
    }
    
    public Veiculo (TipoVeiculo tipoVeiculo,int nifProprietario, String matricula, boolean disponivel, Ponto localizacao, 
                    double classificacao, Collection<Integer> classificacoes){
        this.tipoVeiculo = tipoVeiculo;
        this.nifProprietario = nifProprietario;
        this.matricula = matricula;
        this.disponivel = disponivel;
        this.localizacao = localizacao;
        this.classificacao = classificacao;
        setClassificacoes(classificacoes);
    }
    
    public Veiculo (Veiculo veiculo){
        this.tipoVeiculo = veiculo.getTipoVeiculo();
        this.nifProprietario = veiculo.getNifProprietario();
        this.matricula = veiculo.getMatricula();
        this.disponivel = veiculo.getDisponivel();
        this.localizacao = veiculo.getLocalizacao();
        this.classificacao = veiculo.getClassificacao();
        this.classificacoes = veiculo.getClassificacoes();
    }
    
    // Gets
    
    public TipoVeiculo getTipoVeiculo(){
        return this.tipoVeiculo;
    }
    
    public int getNifProprietario(){
        return this.nifProprietario;
    }
    
    public String getMatricula(){
        return this.matricula;
    }
    
    public boolean getDisponivel(){
        return this.disponivel;
    }
    
    public Ponto getLocalizacao(){
        return this.localizacao;
    }
    
    public double getClassificacao(){
        return this.classificacao;
    }
    
    public Collection<Integer> getClassificacoes(){
        Collection<Integer> aux = new HashSet<>();
        for(Integer i : this.classificacoes){
            aux.add(i);
        }
        return aux;
    }
    
    // Sets
    
    public void setTipoVeiculo(TipoVeiculo newTipoVeiculo){
        this.tipoVeiculo = newTipoVeiculo;
    }
    
    public void setNifProprietario(int nifProprietario){
        this.nifProprietario = nifProprietario;
    }

    public void setMatricula (String newMatricula){
        this.matricula = newMatricula;
    }
    
    public void setDisponivel (boolean newDisponivel){
        this.disponivel = newDisponivel;
    }
    
    public void setLocalizacao (Ponto newLocalizacao){
        this.localizacao = newLocalizacao;
    }
    
    public void setClassificacao (double newClassificacao){
        this.classificacao = newClassificacao;
    }

    public void setClassificacoes(Collection<Integer> classificacoes){
        this.classificacoes = new HashSet<>();
        for(Integer i : classificacoes){
            this.classificacoes.add(i);
        }
    }

    // Clone
    
    public abstract Veiculo clone();
        
    // Equals
    
    public boolean equals (Object o){
        if (this == o) {
            return true;
        }
        if((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }
        Veiculo v = (Veiculo) o;
        return (this.tipoVeiculo.equals(v.getTipoVeiculo()) && this.nifProprietario == v.getNifProprietario() &&
                this.matricula.equals(v.getMatricula()) && this.disponivel == v.getDisponivel() && 
                this.localizacao.equals(v.getLocalizacao()) && this.classificacao == v.getClassificacao() &&
                this.classificacoes.equals(v.getClassificacoes()));
    }
    
    // toString

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Tipo Veículo: " + this.tipoVeiculo + "\n");
        sb.append("NIF Proprietário: " + this.nifProprietario + "\n");
        sb.append("Matricula: " + this.matricula + "\n");
        sb.append("Disponível: " + this.disponivel + "\n");
        sb.append(this.localizacao.toString() + "\n");
        sb.append("Classificação: " + this.classificacao + "\n");
        return sb.toString();
    }
    
}
