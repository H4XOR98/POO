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
    
    public Notificacoes todasNotificacoes(int nif){
        return this.caixaNotificacoes.get(nif);
    }
    
    public void apagaTodasNotificacoes(int nif){
        this.caixaNotificacoes.remove(this.caixaNotificacoes.get(nif));
    }
    
    public void enviaNotificacao(Notificacao n){
        int dest = n.getDestinatario();
        this.caixaNotificacoes.get(dest).addNotificacao(n);
    }
    
    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("----------------- GESTOR NOTIFICACOES -----------------\n");
        this.caixaNotificacoes.forEach((k,v) -> sb.append("Nif: " + k + "\n" + v.toString()));
        return sb.toString();
    }
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        GestorNotificacoes gestorNotificacoes = (GestorNotificacoes)o;
        
        return this.caixaNotificacoes.equals(gestorNotificacoes.getCaixaNotificacoes());
    }
    
    public GestorNotificacoes clone(){
        return new GestorNotificacoes(this);
    }
}
