import java.time.LocalDate;
/**
 * Escreva a descrição da classe Utilizador aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public abstract class Utilizador
{
    //variaveis de instancia
    private String email;
    private String password;
    private String nome;
    private int nif;
    private String morada;
    private LocalDate dataNascimento;
    
    
    public Utilizador(){
        this.email = "n/a";
        this.password = "n/a";
        this.nome = "n/a";
        this.nif = 0;
        this.morada = "n/a";
        this.dataNascimento = LocalDate.now();
    }
    
    
    public Utilizador(String email, String password, String nome, int nif, String morada, LocalDate dataNascimento){
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.nif = nif;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
    }
    
    public Utilizador(Utilizador utiliza){
        this.email = utiliza.getEmail();
        this.password = utiliza.getPassword();
        this.nome = utiliza.getNome();
        this.nif = utiliza.getNif();
        this.morada = utiliza.getMorada();
        this.dataNascimento = utiliza.getDataNascimento();
    }


    /**
     * metodos de instancia
     */

    /**
     * Devolve o email.
     * @return email.
     */
    public String getEmail() {
        return this.email;
    }

    
    /**
     * Devolve a password.
     * @return password.
     */
    public String getPassword() {
        return this.password;
    }
    
    
    /**
     * Devolve o Nome.
     * @return nome.
     */
    public String getNome() {
        return this.nome;
    }

    
    /**
     * Devolve o Numero de idenificacao fiscal.
     * @return nif.
     */
    public int getNif(){
        return this.nif;
    }
    
    
    /**
     * Devolve a morada.
     * @return morada.
     */
    public String getMorada() {
        return this.morada;
    }

    /**
     * Devolve a data de nascimento.
     * @return dataNascimento.
     */
    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    /**
     * Atualiza o email.
     *
     * @param novo email.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Atualiza a password.
     *
     * @param novo password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Atualiza o nome.
     *
     * @param novo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Atualiza o Numero de idenificacao fiscal.
     *
     * @param novo nif.
     */
    public void setNif(int nif) {
        this.nif = nif;
    }
    
    /**
     * Atualiza a morada.
     *
     * @param nova morada.
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * Atualiza a data de nascimento.
     *
     * @param nova dataNascimento.
     */
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        
        Utilizador u = (Utilizador)o;
        
        return this.email.equals(u.getEmail()) && this.password.equals(u.getPassword()) && this.nome.equals(u.getNome()) 
                && this.nif == u.getNif() && this.morada.equals(u.getMorada()) && this.dataNascimento.equals(u.getDataNascimento());
    }
    
    
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Email:           " + this.email + ";\n;");
        sb.append("Password:        " + this.password + ";\n");
        sb.append("Nome:            " + this.nome + ";\n");
        sb.append("NIF:             " + this.nif + ";\n");
        sb.append("Morada:          " + this.morada+ ";\n");
        sb.append("Data Nascimento: " + this.dataNascimento + ";\n");
        return sb.toString();
    }
    
    
    public abstract Utilizador clone();
}