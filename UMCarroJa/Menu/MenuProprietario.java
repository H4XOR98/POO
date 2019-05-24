package Menu;


/**
 * Escreva a descrição da classe MenuProprietario aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class MenuProprietario extends Menu
{
    /**
     * Constructor for objects of class Menu
     */
    public MenuProprietario(String[] opcoes) {
        super(opcoes);
    }
    
    public void executa() {
        do {
            showMenuProprietario();
            setOpcao(lerOpcao());
        } while (getOpcao() == -1);
    }
    
    /** Apresentar o menu */
    private void showMenuProprietario() {
        System.out.println("|***********************************|");
        System.out.println("|******* Area de Proprietario ******|");
        System.out.println("|***********************************|\n");
        super.showMenu();
    }
     
}
