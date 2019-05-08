import java.util.*;
/**
 * Escreva a descrição da classe GestorNotificacoes aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class GestorNotificacoes
{
    private Map<Integer,Notificacoes> caixaNotificacoes;//int - nif

    
    public GestorNotificacoes(){
        this.caixaNotificacoes = new HashMap<>();
    }

    public GestorNotificacoes(Map<Integer,Notificacoes> caixaNotificacoes) {
        setCaixaNotificacoes(caixaNotificacoes);
    }

    public GestorNotificacoes(GestorNotificacoes gestorNotificacoes){
        this.caixaNotificacoes = gestorNotificacoes.getCaixaNotificacoes();
    }

    
    public Map<Integer,Notificacoes> getCaixaNotificacoes(){
        Map<Integer,Notificacoes> aux = new HashMap<Integer,Notificacoes>();
        this.caixaNotificacoes.forEach((k,n) -> aux.put(k,n.clone()));
        return aux;
    }
    
    public void setCaixaNotificacoes(Map<Integer,Notificacoes> caixaNotificacoes){
        this.caixaNotificacoes = new HashMap<Integer,Notificacoes>();
        caixaNotificacoes.forEach((k,n) -> this.caixaNotificacoes.put(k,n.clone()));
    }
    
    
    public boolean temNotificacoes(int nif){
        return this.caixaNotificacoes.containsKey(nif);
    }
    
    public int quantasNotificacoes(int nif){
        return this.caixaNotificacoes.get(nif).quantosElementos();
    }
    
    public Notificacoes gestorUtilizador(int nif){
        return this.caixaNotificacoes.get(nif);
    }
    
    public void apagaNotificacoes(int nif){
        this.caixaNotificacoes.remove(this.caixaNotificacoes.get(nif));
    }
}
