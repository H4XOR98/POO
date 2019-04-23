import java.util.*;
import java.time.LocalDateTime;
/**
 * Escreva a descrição da classe Cliente aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Cliente extends Utilizador
{
    //variaveis de instancia
    private Ponto localizacao;
    private Collection<String> historicoAlugueres;
    
    /**
     * Construtores da classe Ponto.
     * Declaracao dos construtores por omissao (vazio),
     * parametrizado e de copia.
     */
  
    /**
     * Construtor por omissao de Ponto.
     */
    public Cliente(){
        super();
        this.localizacao = new Ponto();
        this.historicoAlugueres = new HashSet<>();
    }
    
    public Cliente(String email, String nome, String password, String morada, LocalDateTime dataNascimento){
        super(email, nome, password, morada, dataNascimento);
        this.localizacao = new Ponto();
        this.historicoAlugueres = new HashSet<>();
    }
}
