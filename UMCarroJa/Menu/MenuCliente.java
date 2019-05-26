package Menu;


/**
 * Write a description of class MenuCliente here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
        System.out.println("|***********************************|");
        System.out.println("|********* Area de Cliente *********|");
        System.out.println("|***********************************|\n");
        super.showMenu();
    }
}
