package Menu;

import App.Input;
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
    private String titulo;
    private int elementosPorPagina;
    private int paginaAtual;
    private int numPaginas;
    private int op;
    
    
    /**
     * Constructor for objects of class Menu
     */
    public Listagem(List<?> opcoes,String titulo) {
        this.lista = opcoes;
        this.titulo = titulo;
        this.elementosPorPagina = 3;
        this.paginaAtual = 0;
        this.numPaginas = (int) Math.ceil(((double)this.lista.size()) / ((double)this.elementosPorPagina));
        this.op = 2;
    }
    
    /**
     * MÈtodo para apresentar o menu e ler uma opÁ„o.
     * 
     */
    public void executa() {
        Input input = new Input(); 
        do {
            System.out.println("\n\nPara prosseguir pressione enter!");
            input.lerString();
            System.out.println("\f");
            showListagem();
            this.op = lerOpcao();
            if(this.op == 1 && this.paginaAtual > 0){
                this.paginaAtual--;
                this.op = -1;
            }
            if(this.op == 2 && this.paginaAtual < this.numPaginas){
                this.paginaAtual++;
                this.op = -1;
            }
        } while (this.op == -1);
    }
    
    /** Apresentar o menu */
    public void showListagem() {
        int num = this.elementosPorPagina * this.paginaAtual;
        if(this.lista.isEmpty()){
            System.out.println("VAZIO");
        }
        System.out.println(titulo + "\n");
        for(int i = num ; i < this.elementosPorPagina + num && i < this.lista.size() ; i++){
            System.out.println(this.lista.get(i));
        }
        System.out.println("\nPagina : " + (this.paginaAtual + 1) + " de " + this.numPaginas + ".");
        if(this.numPaginas == 1){
            System.out.println("********Navegar********");
            System.out.println("*     0 - Sair        *");
            System.out.println("***********************");
        }else if(this.paginaAtual == 0){
            System.out.println("********Navegar********");
            System.out.println("*     2 - Próximo     *");
            System.out.println("*     0 - Sair        *");
            System.out.println("***********************");
        }else if(this.paginaAtual+1 == this.numPaginas){
            System.out.println("********Navegar********");
            System.out.println("*     1 - Anterior    *");
            System.out.println("*     0 - Sair        *");
            System.out.println("***********************");
        }else{
            System.out.println("********Navegar********");
            System.out.println("*     1 - Anterior    *");
            System.out.println("*     2 - Próximo     *");
            System.out.println("*     0 - Sair        *");
            System.out.println("***********************");
        }
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
        if(op == 2 && this.paginaAtual+1 == this.numPaginas){
            System.out.println("Já se encontra no final.");
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
