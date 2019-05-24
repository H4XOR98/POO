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
    private Menu menuPrincipal ,menuRegisto, menuCliente, menuProprietario;
    
    /*public static void main (String[] args){
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
        String[] opcoes ={"Registar",
                          "Login"};
        
        String[] opcoesRegisto = {"Registar como Cliente",
                                  "Registar como Proprietario"};
                                  
        String[] opcoesCliente = {"Consoltar Caixa Notificacoes",
                                  "Alugar Veiculo",
                                  "Realizar Viagem",
                                  "Avalizar Proprietario",
                                  "Avaliar Veiculo"};
                                 
        String[] opcoesProprietario = {"Consoltar Caixa Notificacoes",
                                       "Confirmar Aluguer",
                                       "Registar Veiculo",
                                       "Alterar o preco por km de um Veiculo",
                                       "Abastecer Veiculo",
                                       "Registar custo viagem",
                                       "Avaliar Proprietario"};
                                       
        this.gestorVeiculos = new GestorVeiculos();
        this.gestorUtilizadores = new GestorUtilizadores();
        this.gestorAlugueres = new GestorAlugueres();
        this.gestorNotificacoes = new GestorNotificacoes();  
        
        this.menuPrincipal = new MenuPrincipal(opcoes);
        this.menuRegisto = new MenuRegisto(opcoesRegisto);
        this.menuCliente = new MenuCliente(opcoesCliente);
        this.menuProprietario = new MenuProprietario(opcoesProprietario);
    }

    /**
     * Executa o menu principal e invoca o mÈtodo correspondente ‡ opÁ„o seleccionada.
     */
    private void run() {
        Input input = new Input();
        do {
            System.out.println("\f");
            menuPrincipal.executa();
            System.out.println("\f");
            switch (menuPrincipal.getOpcao()) {
                case 1: runRegisto();
                        break;
                case 2: System.out.println("|****************************|");
                        System.out.println("|******* Area de Login ******|");
                        System.out.println("|****************************|\n");
                        System.out.println("Introduza o email:");
                        String email = input.lerString();
                        System.out.println("Introduza password:");
                        String password = input.lerString();
                        System.out.println("/f");
                        Utilizador u = null;
                        try{
                            u = this.gestorUtilizadores.loginUtilizador(email,password);
                        }
                        catch(UtilizadorNaoExisteException e){
                            System.out.println(e.getMessage());
                        }
                        if(u != null && u.getClass().getSimpleName().equals("Cliente")){
                            Cliente c = (Cliente)u;
                            runCliente();
                        }
                        if(u != null && u.getClass().getSimpleName().equals("Proprietario")){
                            Proprietario p = (Proprietario)u;
                            runProprietario();
                        }
                        break;
                case 3: System.out.println("Vai ser direcionado para o menu das queries");
                        //runQueries();
                        break;
            }
        } while (menuPrincipal.getOpcao()!=0); // A opÁ„o 0 È usada para sair do menu. 
        System.out.println("\f");
        System.out.println("Até breve!...");  
    }
    
    
    private void runRegisto() {
        Input input = new Input();
        do {
            System.out.println("\f");
            menuRegisto.executa();
            switch (menuRegisto.getOpcao()) {
                case 1: System.out.println("\fIntroduza o seu email.");
                        String emailC = input.lerString();
                        System.out.println("\fIntroduza a sua password.");
                        String passwordC = input.lerString();
                        System.out.println("\fIntroduza o seu nome.");
                        String nomeC = input.lerString();
                        System.out.println("\fIntroduza o seu nif.");
                        int nifC = input.lerInt();
                        System.out.println("\fIntroduza a sua morada.");
                        String moradaC = input.lerString();
                
                        System.out.println("\fIntroduza a sua localizaçao i.e. (x,y):\n");
                        System.out.println("\t-Digite a coordenada 'x'.");
                        double xC = input.lerDouble();
                        System.out.println("\t-Digite a coordenada 'y'.");
                        double yC = input.lerDouble();
                        Ponto pontoC = new Ponto(xC,yC);
                        Utilizador c = new Cliente(emailC,passwordC,nomeC,nifC,moradaC,pontoC);
                        try {
                            this.gestorUtilizadores.insereUtilizador(c);
                        }
                        catch(UtilizadorJaExisteException e){
                             System.out.println(e.getMessage());
                        }
                        System.out.println("\fUtilizador registado!");
                        System.out.println("\n\n\n\nPara sair pressione enter!");
                        input.lerString();
                        break;
                case 2: System.out.println("\fIntroduza o seu email.");
                        String emailP = input.lerString();
                        System.out.println("\fIntroduza a sua password.");
                        String passwordP = input.lerString();
                        System.out.println("\fIntroduza o seu nome.");
                        String nomeP = input.lerString();
                        System.out.println("\fIntroduza o seu nif.");
                        int nifP = input.lerInt();
                        System.out.println("\fIntroduza a sua morada.");
                        String moradaP = input.lerString();
                
                        Utilizador p = new Proprietario(emailP,passwordP,nomeP,nifP,moradaP);
                        try {
                            this.gestorUtilizadores.insereUtilizador(p);
                        }
                        catch(UtilizadorJaExisteException e){
                             System.out.println(e.getMessage());
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                        System.out.println("\fUtilizador registado!");
                        System.out.println("\n\n\n\nPara sair pressione enter!");
                        input.lerString();
                        break;
            }
        } while (menuRegisto.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\f");
    }
    
    
    private void runCliente() {
        Input input = new Input();
        do {
            menuRegisto.executa();
            switch (menuRegisto.getOpcao()) {
                case 1: 
                        System.out.println("Utilizador registado!");
                        break;
                case 2:
                        System.out.println("Utilizador registado!");
            }
        } while (menuRegisto.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\f");
    }
    
    private void runProprietario() {
        Input input = new Input();
        do {
            menuRegisto.executa();
            switch (menuRegisto.getOpcao()) {
                case 1: 
                        System.out.println("Utilizador registado!");
                        break;
                case 2:
                        System.out.println("Utilizador registado!");
            }
        } while (menuRegisto.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\f");
    }
}