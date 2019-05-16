package ClassesBase;



/**
 * Escreva a descrição da classe Notificacao aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Notificacao {
    private int destinatario;
    private String assunto;
    private String conteudo;

    public Notificacao() {
        this.destinatario = 0;
        this.assunto = "n/a";
        this.conteudo = "n/a";
    }

    
    public Notificacao(int destinatario, String assunto, String conteudo) {
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.conteudo = conteudo;
    }


    public Notificacao(Notificacao notificacao) {
        this.destinatario = notificacao.getDestinatario();
        this.assunto = notificacao.getAssunto();
        this.conteudo = notificacao.getConteudo();
    }

    

    public int getDestinatario() {
        return this.destinatario;
    }
    
    public String getAssunto() {
        return this.assunto;
    }

    public String getConteudo() {
        return this.conteudo;
    }


    public void setDestinatario(int destinatario) {
        this.destinatario = destinatario;
    }
    
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    
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
        sb.append("Conteudo: " + this.conteudo + ";\n");
        return sb.toString();
    }
    
    
    public Notificacao clone(){
        return new Notificacao(this);
    }
}
