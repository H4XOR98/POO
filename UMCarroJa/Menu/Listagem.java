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
        this.elementosPorPagina = 2;
        this.paginaAtual = 0;
        this.numPaginas = this.lista.size() / this.elementosPorPagina;
        this.op = 2;
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
        } while (this.op == -1);
    }
    
    /** Apresentar o menu */
    public void showListagem() {
        int num = this.elementosPorPagina * this.paginaAtual;
        System.out.println("-------OPCOES-------");
        for(int i = num ; i < this.elementosPorPagina && i < this.lista.size() ; i++){
            System.out.println(this.lista.get(i));
        }
        System.out.println("\nPagina : " + (this.paginaAtual + 1) + " de " + (this.numPaginas+1));
        /*if(this.paginaAtual == 0){
            System.out.println("********Navegar********");
            System.out.println("*     2 - Próximo     *");
            System.out.println("*     0 - Sair        *");
            System.out.println("***********************");
        }else if(this.paginaAtual == this.numPaginas){
            System.out.println("********Navegar********");
            System.out.println("*     1 - Anterior    *");
            System.out.println("*     0 - Sair        *");
            System.out.println("***********************");
        }else{*/
            System.out.println("********Navegar********");
            System.out.println("*     1 - Anterior    *");
            System.out.println("*     2 - Próximo     *");
            System.out.println("*     0 - Sair        *");
            System.out.println("***********************");
        //}
    }
    
    
    /** Ler uma opÁ„o v·lida */
    public int lerOpcao() {
        int op; 
        Scanner is = new Scanner(System.in);
        
        System.out.print("Opçao: ");
        try {
            op = is.nextInt();
        }
        catch (InputMismatchException e) { // Nao foi inscrito um int
            op = -1;
        }
        if (op < 0 || op > 2) {
            System.out.println("Opção Inválida!!!");
            op = -1;
        }else if(op == 1 && this.paginaAtual == 0){
            System.out.println("Já se encontra no inicio.");
            op = -1;
        } else
        if(op == 2 && this.paginaAtual == this.numPaginas){
            System.out.println("Já se encontra no final.");
            op = -1;
        }
        System.out.println("\n\n\n\nPara sair pressione enter!");
        is.nextLine();
        System.out.println("\f");
        return op;
    }
    
    /**
     * MÈtodo para obter a ˙ltima opÁ„o lida
     */
    public int getOpcao() {
        return this.op;
    }
}
