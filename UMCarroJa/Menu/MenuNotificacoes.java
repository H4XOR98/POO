package Menu;


/**
 * Escreva a descrição da classe MenuNotificacoes aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class MenuNotificacoes extends Menu
{
    /**
     * Constructor for objects of class Menu
     */
    public MenuNotificacoes(String[] opcoes) {
        super(opcoes);
    }
    
    public void executa() {
        do {
            showMenuNotificacoes();
            setOpcao(lerOpcao());
        } while (getOpcao() == -1);
    }
    
    /** Apresentar o menu */
    private void showMenuNotificacoes() {
        System.out.println("|****************************************|");
        System.out.println("|********** Area de Notificacoes ********|");
        System.out.println("|****************************************|\n");
        super.showMenu();
    }
}
