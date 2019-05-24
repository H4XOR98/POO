package Menu;


/**
 * Escreva a descrição da classe MenuPrincipal aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class MenuPrincipal extends Menu
{
    /**
     * Constructor for objects of class Menu
     */
    public MenuPrincipal(String[] opcoes) {
        super(opcoes);
    }
    
    public void executa() {
        do {
            showMenuPrincipal();
            setOpcao(lerOpcao());
        } while (getOpcao() == -1);
    }
    
    /** Apresentar o menu */
    private void showMenuPrincipal() {
        System.out.println("|*************************************|");
        System.out.println("|************* UMCarroJá *************|");
        System.out.println("|*************************************|\n");
        super.showMenu();
    }
}
