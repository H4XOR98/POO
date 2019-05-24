package Menu;


/**
 * Escreva a descrição da classe MenuRegisto aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class MenuRegisto extends Menu
{
    /**
     * Constructor for objects of class Menu
     */
    public MenuRegisto(String[] opcoes) {
        super(opcoes);
    }
    
    public void executa() {
        do {
            showMenuRegisto();
            setOpcao(lerOpcao());
        } while (getOpcao() == -1);
    }
    
    /** Apresentar o menu */
    private void showMenuRegisto() {
        System.out.println("|***********************************|");
        System.out.println("|************* Registo *************|");
        System.out.println("|***********************************|\n");
        super.showMenu();
    }
}
