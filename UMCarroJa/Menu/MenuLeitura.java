package Menu;


/**
 * Escreva a descrição da classe MenuLeitura aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class MenuLeitura extends Menu
{
    /**
     * Constructor for objects of class Menu
     */
    public MenuLeitura(String[] opcoes) {
        super(opcoes);
    }
    
    public void executa() {
        do {
            showMenuLeitura();
            setOpcao(lerOpcao());
        } while (getOpcao() == -1);
    }
    
    /** Apresentar o menu */
    private void showMenuLeitura() {
        System.out.println("|**********************|");
        System.out.println("|******* LEITURA ******|");
        System.out.println("|**********************|\n");
        super.showMenu();
    }
}
