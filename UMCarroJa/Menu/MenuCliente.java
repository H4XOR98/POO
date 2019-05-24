package Menu;


/**
 * Escreva a descrição da classe MenuCliente aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class MenuCliente extends Menu
{
    /**
     * Constructor for objects of class Menu
     */
    public MenuCliente(String[] opcoes) {
        super(opcoes);
    }
    
    public void executa() {
        do {
            showMenuCliente();
            setOpcao(lerOpcao());
        } while (getOpcao() == -1);
    }
    
    /** Apresentar o menu */
    private void showMenuCliente() {
        System.out.println("|**********************************|");
        System.out.println("|********* Areá do Cliente ********|");
        System.out.println("|**********************************|\n");
        super.showMenu();
        
    }
}
