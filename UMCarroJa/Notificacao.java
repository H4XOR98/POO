

/**
 * Escreva a descrição da classe Notificacao aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Notificacao {
    private int remetente;
    private int destinatario;
    private String assunto;
    private String conteudo;
    private boolean lida;

    private static int id = 0;

    public Notificacao() {
        this.remetente = 0;
        this.destinatario = 0;
        this.assunto = "n/a";
        this.conteudo = "n/a";
        this.lida = false;
        id++;
    }


    public Notificacao(int remetente, int destinatario, String assunto, String conteudo, boolean lida) {
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.conteudo = conteudo;
        this.lida = lida;
        id++;
    }


    public Notificacao(Notificacao notificacao) {
        this.remetente = notificacao.getRemetente();
        this.destinatario = notificacao.getDestinatario();
        this.assunto = notificacao.getAssunto();
        this.conteudo = notificacao.getConteudo();
        this.lida = notificacao.getLida();
    }

    public int getRemetente() {
        return this.remetente;
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

    public boolean getLida() {
        return this.lida;
    }


    public void setRemetente(int remetente) {
        this.remetente = remetente;
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

    public void setLida(boolean lida) {
        this.lida = lida;
    }



    
    public static int getId() {
        return id;
    }
    
    
    
    
    public boolean equals (Object o){
        if (this == o) {
            return true;
        }
        if((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }
        Notificacao n = (Notificacao) o;
        return this.remetente == n.getRemetente() && this.destinatario == n.getDestinatario() && this.assunto.equals(n.getAssunto()) && 
                this.conteudo.equals(n.getConteudo()) && this.lida == n.getLida();
    }
    
    // toString

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("-----------------NOTIFICACAO-----------------\n");
        sb.append("ID: " + id + ";\n");
        sb.append("Remetente: " + this.remetente + ";\n");
        sb.append("Destinatario: " + this.destinatario + ";\n");
        sb.append("Assunto: " + this.assunto + ";\n");
        sb.append("Conteudo: " + this.conteudo + ";\n");
        sb.append("Lida? " + this.lida + ".\n");
        return sb.toString();
    }
    
    
    public Notificacao clone(){
        return new Notificacao(this);
    }
}
