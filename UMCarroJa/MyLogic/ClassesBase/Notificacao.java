package MyLogic.ClassesBase;

import java.time.LocalDateTime;
import java.io.Serializable;
/**
 * Escreva a descrição da classe Notificacao aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Notificacao implements Serializable{
    
    // Variáveis de Instância
    
    private int destinatario;
    private LocalDateTime data;
    private String assunto;
    private String conteudo;
    
    // Construtores

    public Notificacao() {
        this.destinatario = 0;
        this.data = LocalDateTime.now();
        this.assunto = "n/a";
        this.conteudo = "n/a";
    }

    public Notificacao(int destinatario, String assunto, String conteudo) {
        this.destinatario = destinatario;
        this.data = LocalDateTime.now();
        this.assunto = assunto;
        this.conteudo = conteudo;
    }

    public Notificacao(Notificacao notificacao) {
        this.destinatario = notificacao.getDestinatario();
        this.data = notificacao.getData();
        this.assunto = notificacao.getAssunto();
        this.conteudo = notificacao.getConteudo();
    }

    // Gets

    public int getDestinatario() {
        return this.destinatario;
    }
    
    public LocalDateTime getData(){
        return this.data;
    }
    
    public String getAssunto() {
        return this.assunto;
    }

    public String getConteudo() {
        return this.conteudo;
    }

    // Sets
    
    public void setDestinatario(int destinatario) {
        this.destinatario = destinatario;
    }
    
    public void setData(LocalDateTime data){
        this.data = data;
    }
    
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    // Equals
    
    public boolean equals (Object o){
        if (this == o) {
            return true;
        }
        if((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }
        Notificacao n = (Notificacao) o;
        return this.destinatario == n.getDestinatario() && this.assunto.equals(n.getAssunto()) && this.conteudo.equals(n.getConteudo());
    }
    
    // toString

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------NOTIFICACAO-----------------\n");
        sb.append("Destinatario: " + this.destinatario + ";\n");
        sb.append("Assunto: " + this.assunto + ";\n");
        sb.append("Conteudo: " + this.conteudo);
        return sb.toString();
    }
    
    // Clone
    
    public Notificacao clone(){
        return new Notificacao(this);
    }
}
