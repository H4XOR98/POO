
/**
 * Escreva a descrição da classe UtilizadorExistenteException aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class UtilizadorExistenteException extends Exception
{
    public UtilizadorExistenteException(Utilizador u){
        super(u.toString());
    }
}
