package App;
import java.util.Scanner;
import java.util.*;
import MyLogic.ClassesBase.*;
import MyLogic.Gestores.*;
import MyLogic.Exceptions.*;
import java.lang.*; 
import Menu.*;
import java.io.*;
import java.util.Scanner;
/**
 * Escreva a descrição da classe App aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class VeiculoApp
{
    private GestorNotificacoes gestorNotificacoes;
    private GestorUtilizadores gestorUtilizadores;
    private GestorVeiculos gestorVeiculos;
    private GestorAlugueres gestorAlugueres;
    private Menu menuPrincipal;
    //, menuRegisto, menuCliente, menuProprietario;
    
    /*public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        this.gestorVeiculos = new GestorVeiculos();
        this.gestorUtilizadores = new GestorUtilizadores();
        this.gestorAlugueres = new GestorAlugueres();
        this.gestorNotificacoes = new GestorNotificacoes();
        Leitura l = new Leitura("./logsPOO_carregamentoInicial.txt");
        try{
            l.readFile(this.gestorUtilizadores, this.gestorVeiculos, this.gestorAlugueres, this.gestorNotificacoes);
        }
        catch(UtilizadorJaExisteException e){
            System.out.println(e.getMessage());
        }
        catch(VeiculoJaExisteException e){
            System.out.println(e.getMessage());
        }
        catch(AluguerJaExisteException e){
            System.out.println(e.getMessage());
        }
        catch(UtilizadorNaoExisteException e){
            System.out.println(e.getMessage());
        }
        catch(VeiculoNaoExisteException e){
            System.out.println(e.getMessage());
        }
        catch(AvaliacaoInvalidaException e){
            System.out.println(e.getMessage());
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }*/
    /**
     * O mÈtodo main cria a aplicaÁ„o e invoca o mÈtodo run()
     */
    public static void main(String[] args) {
        new VeiculoApp().run();
    }
    
    /**
     * Construtor.
     * 
     * Cria os menus e a camada de negÛcio.
     */
    
    private VeiculoApp() {
        String[] opcoes ={"Login",
                          "Registar"};
        
        String[] opcoesRegisto = {"",
                                  "",
                                  ""};
                                  
        String[] opcoesCliente = {"Alugar Veiculo",
                                  "Realizar Viagem",
                                  "Avalizar Proprietario"};
                                 
        String[] opcoesProprietario = {"Consoltar Caixa Notificacoes",
                                       "Confirmar Aluguer",
                                       "Registar Veiculo",
                                       "Alterar o preco por km de um Veiculo",
                                       "Abastecer Veiculo",
                                       "Registar custo viagem",
                                       "Avaliar Proprietario"};
        this.menuPrincipal = new Menu(opcoes);     
        //this.menuProprietario = new Menu(opcoesProprietario);
    }

    /**
     * Executa o menu principal e invoca o mÈtodo correspondente ‡ opÁ„o seleccionada.
     */
    private void run() {
        Input input = new Input();
        do {
            menuPrincipal.executa();
            switch (menuPrincipal.getOpcao()) {
                case 1: System.out.println("----------Registo----------\n");
                        System.out.println("Introduza o email:");
                        String email = input.lerString();
                        System.out.println("Introduza password:");
                        String password = input.lerString();
                        break;
                case 2: System.out.println("-----------Login-----------\n");
                        break;
                case 3: System.out.println("Vai ser direcionado para o menu das queries");
                        //runQueries();
                        break;
            }
        } while (menuPrincipal.getOpcao()!=0); // A opÁ„o 0 È usada para sair do menu. 
        System.out.println("Até breve!...");  
    }
}