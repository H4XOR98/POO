import java.time.LocalDateTime;
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
    private String nome;
    private String password;
    private String morada;
    private LocalDateTime dataNascimento;
    
    
    public Utilizador(){
        this.email = "n/a";
        this.nome = "n/a";
        this.password = "n/a";
        this.morada = "n/a";
        this.dataNascimento = LocalDateTime.now();
    }
    
    
    public Utilizador(String email, String nome, String password, String morada, LocalDateTime dataNascimento){
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.morada = morada;
        this.dataNascimento = dataNascimento;
    }
    
    public Utilizador(Utilizador utiliza){
        this.email = utiliza.getEmail();
        this.nome = utiliza.getNome();
        this.password = utiliza.getPassword();
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
     * Devolve o Nome.
     * @return nome.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Devolve a password.
     * @return password.
     */
    public String getPassword() {
        return this.password;
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
    public LocalDateTime getDataNascimento() {
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
     * Atualiza o nome.
     *
     * @param novo nome.
     */
    public void setNome(String nome) {
        this.nome = nome;
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
     * Atualiza a password.
     *
     * @param novo password.
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Atualiza a data de nascimento.
     *
     * @param nova dataNascimento.
     */
    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}