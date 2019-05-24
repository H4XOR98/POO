package Menu;

import java.util.*;
/**
 * Escreva a descrição da classe Listagem aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Listagem
{
    private List<?> lista;
    private int elementosPorPagina;
    private int paginaAtual;
    private int numPaginas;
    private int op;
    
    
    /**
     * Constructor for objects of class Menu
     */
    public Listagem(List<?> opcoes) {
        this.lista = opcoes;
        this.elementosPorPagina = 3;
        this.paginaAtual = 0;
        this.numPaginas = this.lista.size() / this.elementosPorPagina;
    }

    /**
     * MÈtodo para apresentar o menu e ler uma opÁ„o.
     * 
     */
    public void executa() {
        do {
            showListagem();
            this.op = lerOpcao();
            if(this.op == 1 && this.paginaAtual > 0){
                this.paginaAtual--;
            }
            if(this.op == 2 && this.paginaAtual < this.numPaginas){
                this.paginaAtual++;
            }
            System.out.println("\f");
        } while (this.op == -1);
    }
    
    /** Apresentar o menu */
    public void showListagem() {
        int num = this.elementosPorPagina*this.paginaAtual;
        if(this.op == 1){
            for (int i= num; i < num - 3 && i > 0 ; i++) {
                System.out.print(i+1);
                System.out.print(" - ");
                System.out.println(this.lista.get(i));
            }
        }
        if(this.op == 2){
            for (int i= num; i < num + 3 && i < this.lista.size() ; i++) {
                System.out.print(i+1);
                System.out.print(" - ");
                System.out.println(this.lista.get(i));
            }
        }
        
        System.out.println("0 - Sair");
    }
    
    
    /** Ler uma opÁ„o v·lida */
    public int lerOpcao() {
        int op; 
        Scanner is = new Scanner(System.in);
        
        System.out.println("Pagina : " + (this.paginaAtual + 1) + " de " + this.numPaginas);
        System.out.println("*Navegar:*");
        System.out.println("1 - Esquerda");
        System.out.println("2 - Direita");
        System.out.print("Opçao: ");
        try {
            op = is.nextInt();
        }
        catch (InputMismatchException e) { // Nao foi inscrito um int
            op = -1;
        }
        if (op!=1 || op != 2) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }
        return op;
    }
    
    /**
     * MÈtodo para obter a ˙ltima opÁ„o lida
     */
    public int getOpcao() {
        return this.op;
    }
}
