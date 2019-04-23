import java.util.*;
import java.time.LocalDateTime;
/**
 * Escreva a descrição da classe Proprietario aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Proprietario extends Utilizador
{
    //variaveis de instancia
    private double classificacao;
    private Collection<String> historicoAlugueres;
    
    /**
     * Construtores da classe Proprietario.
     * Declaracao dos construtores por omissao (vazio),
     * parametrizado e de copia.
     */
  
    /**
     * Construtor por omissao de Proprietario.
     */
    public Proprietario(){
        super();
        this.classificacao = 0;
        this.historicoAlugueres = new HashSet<>();
    }
    
    /**
     * Construtor parametrizado de Proprietario.
     * Aceita como parametros o email, o nome, a password,
     * a morada e a data de nascimento de um Proprietario.
     */
    public Proprietario(String email, String nome, String password, String morada, LocalDateTime dataNascimento, double classificacao, Collection<String> historicoAlugueres){
        super(email, nome, password, morada, dataNascimento);
        this.classificacao = classificacao;
        setHistoricoAlugueres(historicoAlugueres);
    }
    
    public Proprietario(Proprietario proprietario){
        super(proprietario);
        this.classificacao = proprietario.getClassificacao();
        this.historicoAlugueres = proprietario.getHistoricoAlugueres();
    }


}
