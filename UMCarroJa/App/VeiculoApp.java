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
import java.time.*;
import java.io.IOException;
import java.lang.ClassNotFoundException;

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
    private Menu menuPrincipal ,menuRegisto, menuCliente, menuProprietario, menuTop10, menuClienteAluguer, menuClienteHistoricos, menuProprietarioAluguer, 
                menuProprietarioHistoricos, menuProprietarioVeiculos, menuConfirmacaoAluguer, menuNotificacoes, menuLeitura, menuAceitacao,
                menuProprietarioAlteraDados, menuClienteAlteraDados;
    private Listagem listagem;
   
    
    /**
     * O mÈtodo main cria a aplicaÁ„o e invoca o mÈtodo run()
     */
    public static void main(String[] args) {
        
        VeiculoApp vApp = new VeiculoApp();

        try{
            vApp.gestorVeiculos = vApp.gestorVeiculos.loadStatus();
            vApp.gestorAlugueres = vApp.gestorAlugueres.loadStatus();
            vApp.gestorNotificacoes = vApp.gestorNotificacoes.loadStatus();
            vApp.gestorUtilizadores = vApp.gestorUtilizadores.loadStatus();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        
        vApp.run(); 
        try{
            vApp.gestorVeiculos.saveStatus();
            vApp.gestorAlugueres.saveStatus();
            vApp.gestorNotificacoes.saveStatus();
            vApp.gestorUtilizadores.saveStatus();
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Construtor.
     * 
     * Cria os menus e a camada de negÛcio.
     */
    
    private VeiculoApp() {
        String[] opcoesLeitura = {"Sim","Não"};
        
        String[] opcoes ={"Carregar Estado",
                          "Registar",
                          "Login",
                          "TOP 10 Clientes",
                          "Esqueceu-se da password?"};
        
        String[] opcoesRegisto = {"Registar como Cliente",
                                  "Registar como Proprietario"};
                                  
        String[] opcoesTop10 = {"em numero de vezes",
                               "em quilometros"};
        
        // ------------------------------------------------------- 
                               
        String[] opcoesCliente = {"Consultar Caixa Notificacoes",
                                  "Aluguer",
                                  "Historicos",
                                  "Alterar dados pessoais",
                                  "Meu Perfil"};
                                  
        String[] opcoesClienteAluguer = {"Alugar Veiculo",
                                         "Realizar Viagem"};
                                         
        String[] opcoesClienteHistoricos = {"Historico Aluguer",
                                            "Historico Aluguer(entre datas)"};
                                            
        String[] opcoesClienteAlteraDados = {"Alterar email",
                                             "Alterar password",
                                             "Alterar nome",
                                             "Alterar morada",
                                             "Alterar localizacao"};
                                                            
        // ------------------------------------------------------- 
                                            
        String[] opcoesProprietario = {"Consultar Caixa Notificacoes",
                                       "Aluguer",
                                       "Historico",
                                       "Definicoes Veiculo",
                                       "Alterar dados pessoais",
                                       "Meu Perfil"};
                                       
                                       
        String[] opcoesProprietarioAluguer = {"Confirmar Aluguer",
                                              "Terminar Aluguer"};
       
        String[] opcoesConfirmacaoAluguer = {"Aceitar","Rejeitar"};                                      
                                              
        String[] opcoesProprietarioHistoricos = {"Historico Aluguer",
                                                 "Historico Aluguer(entre datas)",
                                                 "Total faturado por um Veiculo"};                               
                                       
        String[] opcoesProprietarioVeiculos = {"Registar Veiculo",
                                               "Alterar o preco por km de um Veiculo",
                                               "Abastecer Veiculo",
                                               "Os meus veiculos"};
                                               
        String[] opcoesProprietarioAlteraDados = {"Alterar email",
                                                  "Alterar password",
                                                  "Alterar nome",
                                                  "Alterar Morada"};
                                              
        // ------------------------------------------------------- 
                                               
        String[] opcoesNotificacoes = {"Consultar Notificacoes",
                                       "Apagar todas as Notificacoes"};
        
        
                                       
        this.gestorVeiculos = new GestorVeiculos();
        this.gestorUtilizadores = new GestorUtilizadores();
        this.gestorAlugueres = new GestorAlugueres();
        this.gestorNotificacoes = new GestorNotificacoes();  
        
        this.menuLeitura = new MenuLeitura(opcoesLeitura);
        
        this.menuPrincipal = new MenuPrincipal(opcoes);
        this.menuRegisto = new MenuRegisto(opcoesRegisto);
        this.menuTop10 = new Menu(opcoesTop10);
        
        this.menuCliente = new MenuCliente(opcoesCliente);
        this.menuClienteAluguer = new MenuCliente(opcoesClienteAluguer);
        this.menuClienteHistoricos = new MenuCliente(opcoesClienteHistoricos);
        this.menuClienteAlteraDados = new MenuCliente(opcoesClienteAlteraDados);
        
        this.menuProprietario = new MenuProprietario(opcoesProprietario);
        this.menuProprietarioAluguer = new MenuProprietario(opcoesProprietarioAluguer);
        this.menuConfirmacaoAluguer = new Menu(opcoesConfirmacaoAluguer);
        this.menuProprietarioHistoricos = new MenuProprietario(opcoesProprietarioHistoricos);
        this.menuProprietarioVeiculos = new MenuProprietario(opcoesProprietarioVeiculos);
        this.menuProprietarioAlteraDados = new MenuProprietario(opcoesProprietarioAlteraDados);
        
        this.menuNotificacoes = new MenuNotificacoes(opcoesNotificacoes);
        
        this.menuAceitacao = new MenuNotificacoes(opcoesLeitura);
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
                case 1: runLeitura();
                        break;
                case 2: runRegisto();
                        break;
                case 3: System.out.println("|****************************|");
                        System.out.println("|******* Area de Login ******|");
                        System.out.println("|****************************|\n");
                        System.out.println("Introduza o email:");
                        String email = input.lerString();
                        System.out.println("Introduza password:");
                        String password = input.lerString();
                        System.out.println("\f");
                        Utilizador u = null;
                        try{
                            u = this.gestorUtilizadores.loginUtilizador(email,password);
                        }
                        catch(UtilizadorNaoRegistadoException e){
                            System.out.println("Utilizador não registado.");
                        }
                        if(u != null && u.getClass().getSimpleName().equals("Cliente")){
                           Cliente cliente = (Cliente)u;
                           System.out.println("Bem vindo Sr.(a) " + cliente.getNome());
                           System.out.println("\n\nPara prosseguir pressione 'Enter'!");
                           input.lerString();
                           System.out.println("\f");
                           runCliente(cliente);
                        }
                        if(u != null && u.getClass().getSimpleName().equals("Proprietario")){
                            Proprietario proprietario = (Proprietario)u;
                            System.out.println("Bem vindo Sr.(a) " + proprietario.getNome());
                            System.out.println("\n\nPara prosseguir pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\f");
                            runProprietario(proprietario);
                        }
                        System.out.println("\n\nPara retroceder pressione 'Enter'!");
                        input.lerString();
                        break;
                case 4: runTop();
                        break;
                case 5: System.out.println("Introduza o seu email.");
                        email = input.lerString();
                        if(this.gestorUtilizadores.emailRegistado(email)){
                            System.out.println("Enviamos-lhe um email para que recuperar a palavra passe.");
                        }else{
                            System.out.println("Este email não existe.");
                        }   
                        System.out.println("\n\nPara voltar ao menu principal pressione 'Enter'!");
                        input.lerString();
                        System.out.println("\f");
                        break;
            }
        } while (menuPrincipal.getOpcao()!=0); // A opÁ„o 0 È usada para sair do menu. 
        System.out.println("\f");
        System.out.println("Até breve!...");  
    }
    
    private void runLeitura(){
        Input input = new Input();
        do{
            System.out.println("\f");
            menuLeitura.executa();
            switch (menuLeitura.getOpcao()) {
                case 1: Leitura l = new Leitura("./logsPOO_carregamentoInicial.txt");
                        try{
                            l.readFile(this.gestorUtilizadores,this.gestorVeiculos,this.gestorAlugueres, this.gestorNotificacoes);
                            System.out.println("Carregamento do ficheiro efetuado com sucesso.");
                        }
                        catch(UtilizadorJaExisteException e){
                            System.out.println("Os dados do ficheiro já se encontram carregados.");
                        }
                        catch(VeiculoJaExisteException e){
                            System.out.println("Os dados do ficheiro já se encontram carregados.");
                        }   
                        catch(AluguerJaExisteException e){
                            System.out.println("Os dados do ficheiro já se encontram carregados.");
                        }
                        catch(UtilizadorNaoExisteException e){
                            System.out.println("O utilizador com nif " + e.getMessage() + " não existe no sistema!");
                        }   
                        catch(VeiculoNaoExisteException e){
                            System.out.println("O veículo com matrícula " + e.getMessage() + " não existe no sistema!");
                        }
                        catch(AvaliacaoInvalidaException e){
                            System.out.println("Avaliação errada!");
                        }
                        catch(GestorVazioException e){
                            System.out.println("O gestor está vazio");
                        }
                        catch(AluguerNaoExisteException e){
                            System.out.println("O aluguer não existe no sistema.");
                        }
                        catch(VeiculoNaoEncontradoException e){
                            System.out.println("Nenhum veículo encontrado");
                        }
                        catch(IOException e){
                            System.out.println(e.getMessage());
                        }
                    break;
                case 2: System.out.println("A iniciar sem carregamento de ficheiro.");
                    break;
            }
            menuLeitura.setOpcao(0);
        }while(menuLeitura.getOpcao() != 0);
        System.out.println("\n\nPara voltar ao menu principal pressione 'Enter'!");
        input.lerString();
        System.out.println("\f");
    }
    
    
    private void runRegisto() {
        Input input = new Input();
        do {
            System.out.println("\f");
            menuRegisto.executa();
            switch (menuRegisto.getOpcao()) {
                case 1: System.out.println("Passo 1 de 6 do registo de Cliente.");
                        System.out.println("Introduza o seu email.");
                        String emailC = input.lerString();
                        
                        System.out.println("\n\nPara prosseguir pressione 'Enter'!\n\tPasso 2 de 6.");
                        input.lerString();
                        System.out.println("\fPasso 2 de 6 do registo de Cliente.");
                        System.out.println("Introduza a sua password.");
                        String passwordC = input.lerString();
                        
                        System.out.println("\n\nPara prosseguir pressione 'Enter'!\n\tPasso 3 de 6.");
                        input.lerString();
                        System.out.println("\fPasso 3 de 6 do registo de Cliente.");
                        System.out.println("Introduza o seu nome.");
                        String nomeC = input.lerString();
                        
                        System.out.println("\n\nPara prosseguir pressione 'Enter'!\n\tPasso 4 de 6.");
                        input.lerString(); 
                        System.out.println("\fPasso 4 de 6 do registo de Cliente.");
                        System.out.println("Introduza o seu nif.");
                        int nifC = input.lerInt();
                        
                        System.out.println("\n\nPara prosseguir pressione 'Enter'!\n\tPasso 5 de 6.");
                        input.lerString();
                        System.out.println("\fPasso 5 de 6 do registo de Cliente.");
                        System.out.println("Introduza a sua morada.");
                        String moradaC = input.lerString();
                        
                        System.out.println("\n\nPara prosseguir pressione 'Enter'!\n\tPasso 6 de 6.");
                        input.lerString();
                        System.out.println("\fPasso 5 de 6 do registo de Cliente.");
                        System.out.println("Introduza a sua localizaçao i.e. (x,y):\n");
                        System.out.println("*Digite a coordenada 'x'.");
                        double xC = input.lerDouble();
                        System.out.println("*Digite a coordenada 'y'.");
                        double yC = input.lerDouble();
                        System.out.println("\f");
                        Ponto pontoC = new Ponto(xC,yC);
                        Utilizador c = new Cliente(emailC,passwordC,nomeC,nifC,moradaC,pontoC);
                        System.out.println("\f");
                        try {
                            this.gestorUtilizadores.insereUtilizador(c);
                            this.gestorNotificacoes.adicionaUtilizador(c);
                            System.out.println("Registo concluido com sucesso.");
                        }
                        catch(UtilizadorJaExisteException e){
                             System.out.println("O utilizador com nif " + e.getMessage() + " já existe no sistema.");
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                        }
                        System.out.println("\n\nPara voltar ao menu principal pressione 'Enter'!");
                        input.lerString();
                        break;
                case 2: System.out.println("Passo 1 de 5 do registo de Proprietario.");
                        System.out.println("Introduza o seu email.");
                        String emailP = input.lerString();
                        
                        System.out.println("\n\nPara prosseguir pressione 'Enter'!\n\tPasso 2 de 5.");
                        input.lerString();
                        System.out.println("\fPasso 2 de 5 do registo de Proprietario.");
                        System.out.println("Introduza a sua password.");
                        String passwordP = input.lerString();
                        
                        System.out.println("\n\nPara prosseguir pressione 'Enter'!\n\tPasso 3 de 5.");
                        input.lerString();
                        System.out.println("\fPasso 3 de 5 do registo de Proprietario.");
                        System.out.println("Introduza o seu nome.");
                        String nomeP = input.lerString();
                        
                        System.out.println("\n\nPara prosseguir pressione 'Enter'!\n\tPasso 4 de 5.");
                        input.lerString();
                        System.out.println("\fPasso 4 de 5 do registo de Proprietario."); 
                        System.out.println("Introduza o seu nif.");
                        int nifP = input.lerInt();
                        
                        System.out.println("\n\nPara prosseguir pressione 'Enter'!\n\tPasso 5 de 5.");
                        input.lerString();
                        System.out.println("\fPasso 5 de 5 do registo de Proprietario.");
                        System.out.println("Introduza a sua morada.");
                        String moradaP = input.lerString();
                        
                        System.out.println("\f");
                        Utilizador p = new Proprietario(emailP,passwordP,nomeP,nifP,moradaP);
                        try {
                            this.gestorUtilizadores.insereUtilizador(p);
                            this.gestorNotificacoes.adicionaUtilizador(p);
                            System.out.println("Registo concluido com sucesso.");
                        }
                        catch(UtilizadorJaExisteException e){
                             System.out.println("O utilizador com nif " + e.getMessage() + " já existe no sistema.");
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                        }       
                        System.out.println("\n\nPara voltar ao menu principal pressione 'Enter'!");
                        input.lerString();
                        break;     
             }
             menuRegisto.setOpcao(0);
        } while (menuRegisto.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\f");
    }
    
    private void runTop(){
        Input input = new Input();
        do {
            menuTop10.executa();
            switch (menuTop10.getOpcao()) {
                
                case 1: try{              
                            String str = "---------------------------------------------------------\n--------- TOP 10 Clientes Numero de Utilizacoes ---------\n---------------------------------------------------------";
                            this.listagem = new Listagem(this.gestorUtilizadores.procuraUtilizadores(this.gestorAlugueres.topDezClientesVezes()),str);
                            this.listagem.executa();
                        }
                        catch(UtilizadorNaoExisteException e){
                            System.out.println("O utilizador com nif " + e.getMessage() + " não existe no sistema.");
                        }   
                        catch(UtilizadoresNaoExistemException e){
                            System.out.println("Os Utilizadores não existem no sistema.");
                        }
                        catch(AlugueresNaoExistemException e){
                            System.out.println("Os Alugures não existem no sistema.");
                        }
                        break;
                                              
                case 2: try{
                            String str = "-----------------------------------------------\n--------- TOP 10 Clientes Quilometros ---------\n-----------------------------------------------";
                            this.listagem = new Listagem(this.gestorUtilizadores.procuraUtilizadores(this.gestorAlugueres.topDezClientesKms()),str);
                            this.listagem.executa();
                        }
                        catch(UtilizadorNaoExisteException e){
                            System.out.println("O utilizador com nif " + e.getMessage() + " não existe no sistema.");
                        }
                        catch(UtilizadoresNaoExistemException e){
                            System.out.println("Os Utilizadores não existem no sistema.");
                        }
                        catch(AlugueresNaoExistemException e){
                            System.out.println("Os Alugueres não existem no sistema.");
                        }
                        break;
            }
            System.out.println("\n\nPara retroceder pressione 'Enter'!");
            input.lerString();
            System.out.println("\f");
        } while (menuTop10.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\n\nPara voltar ao menu principal pressione 'Enter'!");
        input.lerString();
        System.out.println("\f");
    }
    
    
    
    private void runCliente(Cliente cliente) {
        Input input = new Input();
        try{
            System.out.println("Tem " + this.gestorNotificacoes.quantasNotificacoes(cliente.getNif()) + " notificações.");
        }
        catch(UtilizadorNaoExisteException e){
            System.out.println("O utilizador com nif " + e.getMessage() + " não existe no sistema.");
        }
        do {
            menuCliente.executa();
            switch (menuCliente.getOpcao()) {
                case 1: runMenuNotificacoes(cliente);
                       break; 
                case 2: runClienteAluguer(cliente);
                       break;
                case 3: runClienteHistoricos(cliente);
                       break;
                case 4: runClienteAlteraDados(cliente);
                       break;
                case 5: System.out.println("\fO meu Perfil");
                        System.out.println(cliente.toString());
            }
            System.out.println("\n\nPara voltar ao menu de cliente pressione 'Enter'!");
            input.lerString();
            System.out.println("\f");
        } while (menuCliente.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\n\nPara voltar ao menu principal pressione 'Enter'!");
        input.lerString();
        System.out.println("\f");
    }
    
    private void runClienteAluguer(Cliente cliente) {
        System.out.println("\f");
        Input input = new Input();
        do {
            menuClienteAluguer.executa();
            switch (menuClienteAluguer.getOpcao()) {
                case 1: System.out.println("Passo 1 de 4 do registo de Veiculo.");
                        System.out.println("Introduza a localizaçao do seu destino i.e. (x,y):\n");
                        System.out.println("Digite a coordenada 'x'.");
                        double xC = input.lerDouble();
                        System.out.println("Digite a coordenada 'y'.");
                        double yC = input.lerDouble();
                        Ponto destino = new Ponto(xC,yC);
                        System.out.println("\n\nPara prosseguir pressione 'Enter'!\n\tPasso 2 de 4.");
                        input.lerString();
                        try{
                            System.out.println("\fPasso 2 de 4 do registo de Veiculo.");
                            String titulo = "--------------------------------------------\n------- Tipos de veiculos do sistema -------\n--------------------------------------------";
                            this.listagem = new Listagem(Arrays.asList(TipoVeiculo.values()),titulo);
                            this.listagem.executa();
                            System.out.println("\n\nIntroduza o tipo de veiculo pretendido:");
                            String str = input.lerString();
                            TipoVeiculo tipoVeiculo = TipoVeiculo.valueOf(str);
                            System.out.println("\n\nPara prosseguir pressione 'Enter'!\n\tPasso 3 de 4.");
                            input.lerString();
                            System.out.println("\fPasso 3 de 4 do registo de Veiculo.");         
                            titulo = "------------------------------------------------\n------- Tipos de combustivel do sistema. -------\n------------------------------------------------";
                            this.listagem = new Listagem(Arrays.asList(TipoCombustivel.values()),titulo);
                            this.listagem.executa();
                            System.out.println("Tipo de combustivel pretendido:");
                            str = input.lerString();
                            TipoCombustivel tipoCombustivel = TipoCombustivel.valueOf(str);
                            System.out.println("\n\nPara prosseguir pressione 'Enter'!\n\tPasso 4 de 4.");
                            input.lerString();
                            System.out.println("\fPasso 4 de 4 do registo de Veiculo.");
                            titulo = "-------------------------------------------------\n------- Preferencia de Aluguer do sistema -------\n-------------------------------------------------";
                            this.listagem = new Listagem(Arrays.asList(PreferenciaAluguer.values()),titulo);
                            this.listagem.executa();
                            System.out.println("\nPreferencia de Aluguer pretendido:");
                            str = input.lerString();
                            PreferenciaAluguer preferencia = PreferenciaAluguer.valueOf(str);
                            
                            Aluguer a = new Aluguer(tipoVeiculo,cliente.getNif(),destino,tipoCombustivel,preferencia);
                            System.out.println("\f");
                            List<Veiculo> veiculos = null;
                            Veiculo veiculo = null;
                            String matricula = "";
                            switch(a.getPreferencia()){
                                case MaisBarato:
                                    veiculos = gestorVeiculos.veiculoMaisBarato(a.getTipoVeiculo(), a.getTipoCombustivel(), destino);
                                    break;
                
                                case MaisPerto:
                                    veiculos = gestorVeiculos.veiculoMaisPerto(a.getTipoVeiculo(), a.getTipoCombustivel(), cliente.getLocalizacao(), destino);
                                    break;
                
                                case MaisPertoBarato:
                                    System.out.println("Introduza a distancia que esta disposto a percorrer!");
                                    double distanciaMax = input.lerDouble();
                                    veiculos = gestorVeiculos.veiculoMaisPertoBarato(a.getTipoVeiculo(), a.getTipoCombustivel(), cliente.getLocalizacao(), 
                                                                 destino, distanciaMax);
                                    break;
                
                                case Especifico:
                                    System.out.println("Introduza a matricula do veiculo que pretende alugar!");
                                    matricula = input.lerString();
                                    veiculo = gestorVeiculos.veiculoEspecifico(matricula, destino);
                                    break;
                
                                case Autonomia:
                                    default:
                                    System.out.println("Introduza a autonomia pretendida!");
                                    double autonomia = input.lerDouble();
                                    veiculos = gestorVeiculos.veiculoAutonomia(a.getTipoVeiculo(), a.getTipoCombustivel(), autonomia);
                                    break;
                            }
                            if(veiculos != null){
                                List<String> resultado = new ArrayList<>();
                                veiculos.forEach(v -> resultado.add(v.toString()));
                                titulo = "----------------------------------\n------ Veiculos disponiveis ------\n----------------------------------";
                                this.listagem = new Listagem(resultado,titulo);
                                this.listagem.executa();
                            }
                            if(veiculo == null){
                                System.out.println("\fIntroduza a matricula do veiculo que pretende alugar!");
                                matricula = input.lerString();
                                veiculo = this.gestorVeiculos.getVeiculo(matricula);
                                System.out.println("\n\nPara prosseguir pressione 'Enter'!");
                                input.lerString();
                            }
                            a.setEstadoAluguer(EstadoAluguer.Espera);
                            a.setVeiculo(veiculo);
                            Notificacao n = a.pedidoAluguer(cliente);
                            this.gestorAlugueres.insereAluguer(a);
                            this.gestorNotificacoes.adicionaNotificacao(n);
                            System.out.println("\fO veiculo que pretende alugar: \n\n" + veiculo.toString());
                        }
                        catch(GestorVazioException e){
                            System.out.println("O gestor está vazio.");
                        }
                        catch(IllegalArgumentException e){
                            System.out.println("Tipo inválido");
                        }
                        catch(VeiculoNaoEncontradoException e){
                            System.out.println("Nenhum veículo disponível" + e.getMessage());
                        }
                        catch(VeiculoNaoExisteException e){
                            System.out.println("O veículo com matrícula " + e.getMessage() + " não existe no sistema.");
                        }
                        catch(AluguerJaExisteException e){
                            System.out.println("O aluguer já existe.");
                        }
                       break;
                case 2: System.out.println("Introduza o id do aluguer que pretende.");
                        int id = input.lerInt();
                        System.out.println("\n\nPara prosseguir pressione 'Enter'!");
                        input.lerString();
                        try{
                            Aluguer a = this.gestorAlugueres.getAluguer(id);
                            Notificacao notificacao = a.efetuaViagem(cliente);
                            if(notificacao != null){
                                this.gestorNotificacoes.adicionaNotificacao(notificacao);
                            }
                            notificacao = a.terminaViagemCliente(cliente);
                            this.gestorNotificacoes.adicionaNotificacao(notificacao);
                            System.out.println("\fInsira uma nota para o Veiculo.");
                            double notaVeiculo = input.lerInt();
                            System.out.println("\n\nPara prosseguir pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fInsira uma nota para o Proprietario.");
                            double notaProprietario = input.lerInt();
                            System.out.println("\n\nPara prosseguir pressione 'Enter'!");
                            input.lerString();
                            Proprietario p = (Proprietario)this.gestorUtilizadores.getUtilizador(a.getVeiculo().getNif());
                            a.avaliacoesCliente(p, notaVeiculo, notaProprietario);
                            this.gestorUtilizadores.atualizaUtilizador(cliente);
                            this.gestorAlugueres.atualizaAluguer(a);
                            this.gestorUtilizadores.atualizaUtilizador(p);
                            this.gestorVeiculos.atualizaVeiculo(a.getVeiculo());
                        }
                        catch(AluguerNaoExisteException e){
                            System.out.println("O aluguer não existe no sistema.");
                        }
                        catch(UtilizadorNaoExisteException e){
                            System.out.println("O utilizador com nif " + e.getMessage() + " não existe no sistema.");
                        }
                        catch(AvaliacaoInvalidaException e){
                            System.out.println("Avaliação inválida.");
                        }
                        break;     
            }
            System.out.println("\n\nPara retroceder pressione 'Enter'!");
            input.lerString();
            System.out.println("\f");
        } while (menuClienteAluguer.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\n\nPara voltar ao menu principal pressione 'Enter'!");
        input.lerString();
        System.out.println("\f");
    }
    
    private void runClienteHistoricos(Cliente cliente) {
        Input input = new Input();
        List<String> historico = null;
        do {
            System.out.println("\f");
            menuClienteHistoricos.executa();
            switch (menuClienteHistoricos.getOpcao()) {
                case 1: historico = this.gestorAlugueres.historicoCliente(cliente.getNif());
                        menuClienteHistoricos.setOpcao(0);
                        break;
                        
                case 2: try{
                            System.out.println("Introduza a data de Inicio.");
                            System.out.println("Introduza o ano.");
                            int ano = input.lerInt();
                            System.out.println("\n\nPara introduzir o mes pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza o mes.");
                            int mes = input.lerInt();
                            System.out.println("\n\nPara introduzir o dia pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza o dia.");
                            int dia = input.lerInt();
                            System.out.println("\n\nPara introduzir o hora pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza a hora.");
                            int hora = input.lerInt();
                            System.out.println("\n\nPara introduzir os minutos pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza os minutos.");
                            int minutos = input.lerInt();
                            LocalDateTime inicio = LocalDateTime.of(ano, mes, dia, hora, minutos);
                            System.out.println("Introduza a data de Fim.");
                            System.out.println("Introduza o ano;");
                            ano = input.lerInt();
                            System.out.println("\n\nPara introduzir o mes pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza o mes;");
                            mes = input.lerInt();
                            System.out.println("\n\nPara introduzir o dia pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza o dia;");
                            dia = input.lerInt();
                            System.out.println("\n\nPara introduzir o hora pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza a hora;");
                            hora = input.lerInt();
                            System.out.println("\n\nPara introduzir o minutos pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza os minutos;");
                            minutos = input.lerInt();
                            LocalDateTime fim = LocalDateTime.of(ano, mes, dia, hora, minutos);
                            historico = this.gestorAlugueres.historicoClienteEntreDatas(cliente.getNif(),inicio,fim);
                        }
                        catch(DateTimeException e){
                            System.out.println("Data inválida.");
                        }
                        break;    
            }
        } while (menuClienteHistoricos.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        if(historico != null){
            String titulo = "       -----------------------------       \n---------------- Historico ----------------\n       -----------------------------\n";
            this.listagem = new Listagem(historico,titulo);
            this.listagem.executa();
        }
        System.out.println("\n\nPara voltar ao menu de cliente pressione 'Enter'!");
        input.lerString();
        System.out.println("\f");
    }
    
    
    private void runProprietario(Proprietario proprietario) {
        Input input = new Input();
        System.out.println("\f");
        try{
            System.out.println("Tem " + this.gestorNotificacoes.quantasNotificacoes(proprietario.getNif()) + " notificacoes.");
        }
        catch(UtilizadorNaoExisteException e){
            System.out.println("O utilizador com nif " + e.getMessage() + " não existe no sistema.");
        }
        do {
            menuProprietario.executa();
            switch (menuProprietario.getOpcao()) {
                case 1: runMenuNotificacoes(proprietario);
                        break;
                case 2: runProprietarioAluguer();
                        break;
                case 3: runProprietarioHistoricos(proprietario);
                        break;
                case 4: runProprietarioVeiculos(proprietario);
                        break;
                case 5: runProprietarioAlteraDados(proprietario);
                        break;
                case 6: System.out.println("\fO meu Perfil");
                        System.out.println(proprietario.toString());
            }
            System.out.println("\n\nPara voltar ao menu de proprietario pressione 'Enter'!");
            input.lerString();
            System.out.println("\f");
        } while (menuProprietario.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\n\nPara voltar ao menu principal pressione 'Enter'!");
        input.lerString();
        System.out.println("\f");
    }
    
    
    private void runProprietarioAluguer() {
        Input input = new Input();
        System.out.println("\f");
        do {
            menuProprietarioAluguer.executa();
            try{
                switch (menuProprietarioAluguer.getOpcao()) {
                    case 1: System.out.println("Introduza o Id do aluguer.");
                            int id = input.lerInt();
                            Aluguer a = this.gestorAlugueres.getAluguer(id);
                            runConfirmacaoAluguer(a);
                            this.gestorAlugueres.atualizaAluguer(a);
                        break;
                    case 2: System.out.println("Introduza o Id do aluguer.");
                            id = input.lerInt();
                            a = this.gestorAlugueres.getAluguer(id);
                            a.registaCusto();
                            System.out.println("Introduza nota do Cliente.");
                            int notaCliente = input.lerInt();
                            Cliente cliente = (Cliente)this.gestorUtilizadores.getUtilizador(a.getNif());
                            a.avaliacaoProprietario(cliente,notaCliente);
                            this.gestorUtilizadores.atualizaUtilizador(cliente);
                            this.gestorAlugueres.atualizaAluguer(a);
                        break; 
                }
            }
            catch(AluguerNaoExisteException e){
                System.out.println("O aluguer não existe no sistema.");
            }
            catch(AvaliacaoInvalidaException e){
                System.out.println("Avaliação inválida.");
            }
            catch(UtilizadorNaoExisteException e){
                System.out.println("O utilizador com nif " + e.getMessage() + " não existe no sistema.");
            }
            menuProprietarioAluguer.setOpcao(0);
        }while (menuProprietarioAluguer.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\n\nPara voltar ao menu principal pressione 'Enter'!");
        input.lerString();
        System.out.println("\f");
    }                       
       
   
    private void runConfirmacaoAluguer(Aluguer a) {
        System.out.println("\f");
        Input input = new Input();
        System.out.println("Pretende Aceitar/Rejeitar?\n\n");
        System.out.println(a.toString());
        System.out.println("\n\n");
        do {
            menuConfirmacaoAluguer.executa();
            switch (menuConfirmacaoAluguer.getOpcao()) {
                case 1: try{
                       
                            Notificacao n = a.aceitaAluguer();
                            System.out.println("\nO aluguer com id " + a.getId() + " foi aceite com sucesso.");
                            this.gestorNotificacoes.adicionaNotificacao(n);
                            this.gestorAlugueres.atualizaAluguer(a);
                            menuConfirmacaoAluguer.setOpcao(0);
                        }
                        catch(AluguerNaoEmEsperaException e){
                                System.out.println("Este aluguer não se encontra em espera.");
                        }
                    break;
                case 2: try{
                            this.gestorAlugueres.removeAluguer(a);
                            Notificacao n = a.rejeitaAluguer();
                            System.out.println("\nO aluguer com id " + a.getId() + " foi rejeitado.");
                            menuConfirmacaoAluguer.setOpcao(0);
                        }
                        catch(AluguerNaoEmEsperaException e){
                                System.out.println("Este aluguer não se encontra em espera.");
                        }
                    break;
            }
            System.out.println("\n\nPara retroceder pressione 'Enter'!");
            input.lerString();
            System.out.println("\f");
        }while (menuConfirmacaoAluguer.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\n\nPara retroceder pressione 'Enter'");
        input.lerString();
        System.out.println("\f");
    }
    
    private void runProprietarioHistoricos(Proprietario proprietario){
        Input input = new Input();
        List<String> historico = null;
        do {
            System.out.println("\f");
            menuProprietarioHistoricos.executa();
            switch (menuProprietarioHistoricos.getOpcao()) {
                case 1: historico = this.gestorAlugueres.historicoProprietario(proprietario.getNif());
                        break;
                case 2: try{
                            System.out.println("Introduza a data de Inicio.");
                            System.out.println("Introduza o ano.");
                            int ano = input.lerInt();
                            System.out.println("\n\nPara introduzir o mes pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza o mes.");
                            int mes = input.lerInt();
                            System.out.println("\n\nPara introduzir o dia pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza o dia.");
                            int dia = input.lerInt();
                            System.out.println("\n\nPara introduzir o hora pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza a hora.");
                            int hora = input.lerInt();
                            System.out.println("\n\nPara introduzir os minutos pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza os minutos.");
                            int minutos = input.lerInt();
                            LocalDateTime inicio = LocalDateTime.of(ano, mes, dia, hora, minutos);
                            System.out.println("Introduza a data de Fim.");
                            System.out.println("Introduza o ano;");
                            ano = input.lerInt();
                            System.out.println("\n\nPara introduzir o mes pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza o mes;");
                            mes = input.lerInt();
                            System.out.println("\n\nPara introduzir o dia pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza o dia;");
                            dia = input.lerInt();
                            System.out.println("\n\nPara introduzir o hora pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza a hora;");
                            hora = input.lerInt();
                            System.out.println("\n\nPara introduzir o minutos pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza os minutos;");
                            minutos = input.lerInt();
                            LocalDateTime fim = LocalDateTime.of(ano, mes, dia, hora, minutos);
                            historico = this.gestorAlugueres.historicoProprietarioEntreDatas(proprietario.getNif(),inicio,fim);
                        }
                        catch(DateTimeException e){
                            System.out.println("Data inválida.");
                        }
                        break;
                case 3: try{                 
                            String titulo = "      -----------------------     \n-------- Os seus Veiculos: -------\n      -----------------------     \n";
                            this.listagem = new Listagem(this.gestorVeiculos.redacaoVeiculosProprietario(proprietario.getNif()),titulo);
                            System.out.println("Introduza a matricula do veiculo que pretende saber o total faturado.");
                            String matricula = input.lerString();
                            System.out.println("\nIntroduza a data de Inicio.");
                            System.out.println("Introduza o ano.");
                            int ano = input.lerInt();
                            System.out.println("\n\nPara introduzir o mes pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza o mes.");
                            int mes = input.lerInt();
                            System.out.println("\n\nPara introduzir o dia pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza o dia.");
                            int dia = input.lerInt();
                            System.out.println("\n\nPara introduzir o hora pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza a hora.");
                            int hora = input.lerInt();
                            System.out.println("\n\nPara introduzir os minutos pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza os minutos.");
                            int minutos = input.lerInt();
                            LocalDateTime inicio = LocalDateTime.of(ano, mes, dia, hora, minutos);
                            System.out.println("\fIntroduza a data de Fim.");
                            System.out.println("Introduza o ano;");
                            ano = input.lerInt();
                            System.out.println("\n\nPara introduzir o mes pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza o mes;");
                            mes = input.lerInt();
                            System.out.println("\n\nPara introduzir o dia pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza o dia;");
                            dia = input.lerInt();
                            System.out.println("\n\nPara introduzir o hora pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza a hora;");
                            hora = input.lerInt();
                            System.out.println("\n\nPara introduzir o minutos pressione 'Enter'!");
                            input.lerString();
                            System.out.println("\fIntroduza os minutos;");
                            minutos = input.lerInt();
                            LocalDateTime fim = LocalDateTime.of(ano, mes, dia, hora, minutos);
                            System.out.println("\fO veiculo com matricula " + matricula + " faturou " + this.gestorAlugueres.totalFaturadoVeiculo(matricula, inicio, fim) + 
                                "€ entre " + inicio.toString() + " e " + fim.toString() + ".");
                        }
                        catch(GestorVazioException e){
                            System.out.println("O gestor está vazio.");
                        }
                        break;
            }
        } while (menuClienteHistoricos.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        if(historico != null){
            String titulo = "       -----------------------------       \n---------------- Historico ----------------\n       -----------------------------\n";
            this.listagem = new Listagem(historico,titulo);
            this.listagem.executa();
        }
        System.out.println("\n\nPara voltar ao menu de proprietário pressione 'Enter'!");
        input.lerString();
        System.out.println("\f");
    }
                
    private void runProprietarioVeiculos(Proprietario proprietario) {
        Input input = new Input();
        do {
            System.out.println("\f");
            menuProprietarioVeiculos.executa();
            switch (menuProprietarioVeiculos.getOpcao()) {
                case 1: try{
                            String titulo = "--------------------------------------------\n------- Tipos de veiculos do sistema -------\n--------------------------------------------";
                            this.listagem = new Listagem(Arrays.asList(TipoVeiculo.values()),titulo);
                            this.listagem.executa();
                            System.out.println("\nIntroduza o tipo de veiculo pretendido:");
                            String str = input.lerString();
                            TipoVeiculo tipoVeiculo = TipoVeiculo.valueOf(str);
                            System.out.println("\n\n\n\nPara sair pressione enter!");
                            input.lerString();
                            titulo = "------------------------------------------------\n------- Tipos de combustivel do sistema. -------\n------------------------------------------------";
                            this.listagem = new Listagem(Arrays.asList(TipoCombustivel.values()),titulo);
                            this.listagem.executa();
                            System.out.println("Tipo de combustivel pretendido:");
                            str = input.lerString();
                            TipoCombustivel tipoCombustivel = TipoCombustivel.valueOf(str);
                            System.out.println("\n\n\n\nPara sair pressione enter!");
                            input.lerString();
                            System.out.println("\fIntroduza a marca.");
                            String marca = input.lerString();
                            System.out.println("\fIntroduza a matricula.");
                            String matricula = input.lerString();
                            System.out.println("\fIntroduza a velocidade media.");
                            int velocidadeMedia = input.lerInt();
                            System.out.println("\fIntroduza o preco por quilometro.");
                            double preco = input.lerDouble();
                            System.out.println("\fIntroduza o consumo medio por quilometro.");
                            double consumo = input.lerDouble();
                            System.out.println("\fIntroduza a autonomia maxima.");
                            double autonomiaMax = input.lerDouble();
                            System.out.println("\fIntroduza a localizaçao do seu veiculo i.e. (x,y):\n");
                            System.out.println("\t-Digite a coordenada 'x'.");
                            double x = input.lerDouble();
                            System.out.println("\t-Digite a coordenada 'y'.");
                            double y = input.lerDouble();
                            Ponto localizacao = new Ponto(x,y);
                            Veiculo veiculo = new Veiculo (tipoVeiculo,tipoCombustivel,marca,matricula,proprietario.getNif(),velocidadeMedia, preco, consumo,
                            autonomiaMax, localizacao);
                        
                            this.gestorVeiculos.insereVeiculo(veiculo);
                            System.out.println("\fVeiculo registado com suceso.");
                        }
                        catch(VeiculoJaExisteException e){
                            System.out.println("\f" + "O veículo com matrícula " + e.getMessage() + " já existe no sistema.");
                        }
                        catch(IllegalArgumentException e){
                            System.out.println("Tipo inválido.");
                        }
                        System.out.println("\n\nPara retroceder pressione 'Enter'");
                        input.lerString();
                        System.out.println("\f");
                        break;
                case 2: try{
                            System.out.println("\fIntroduza a matricula do veiculo que pretende alterar o preco por km.");
                            String matricula = input.lerString();
                            Veiculo v = this.gestorVeiculos.getVeiculo(matricula);
                            System.out.println("\n\nPara prosseguir pressione 'Enter'.");
                            input.lerString();
                            System.out.println("\f");
                            System.out.println("Introduza o novo preco por km que pretende.");
                            double preco = input.lerDouble();
                            proprietario.alteraPrecoVeiculo(v,preco);
                            this.gestorVeiculos.atualizaVeiculo(v);
                            System.out.println("Novo preço introduzido com sucesso.");
                        }
                        catch(GestorVazioException e){
                            System.out.println("O gestor está vazio.");
                        }
                        catch(VeiculoNaoExisteException e){
                            System.out.println("O veículo com matrícula " + e.getMessage() + " não existe no sistema.");
                        }
                        catch(VeiculoNaoPertenceException e){
                            System.out.println("O veículo com matrícula " + e.getMessage() + " não lhe pertence.");
                        }
                        System.out.println("\n\nPara retroceder pressione 'Enter'");
                        input.lerString();
                        System.out.println("\f");
                        break;
                case 3: try{
                            System.out.println("\f");
                            System.out.println("Introduza a matricula do veiculo que pretende abastecer.");
                            String matricula = input.lerString();
                            Veiculo v = this.gestorVeiculos.getVeiculo(matricula);
                            proprietario.abasteceVeiculo(v);
                            this.gestorVeiculos.atualizaVeiculo(v);
                            System.out.println("Veiculo abastecido com suceso.");
                        }
                        catch(GestorVazioException e){
                            System.out.println("O gestor está vazio.");
                        }
                        catch(VeiculoNaoExisteException e){
                            System.out.println("O veículo com matrícula " + e.getMessage() + " não existe no sistema.");
                        }
                        catch(VeiculoNaoPertenceException e){
                            System.out.println("O veículo com matrícula " + e.getMessage() + " não lhe pertence.");
                        }
                        System.out.println("\n\nPara retroceder pressione 'Enter'");
                        input.lerString();
                        System.out.println("\f");
                        break;
                case 4: try{
                            String titulo = "********************************\n******* Os seus veiculos *******\n********************************";
                            List<String> resultado = this.gestorVeiculos.redacaoVeiculosProprietario(proprietario.getNif());
                            this.listagem = new Listagem(resultado,titulo);
                            this.listagem.executa();
                        }
                        catch(GestorVazioException e){
                            System.out.println("O gestor está vazio.");
                        }
                        break;
            }
            System.out.println("\n\nPara retroceder pressione 'Enter'!");
            input.lerString();
            System.out.println("\f");
        }while (menuProprietarioVeiculos.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\n\nPara retroceder pressione 'Enter'");
        input.lerString();
        System.out.println("\f");
    }
    
    private void runMenuNotificacoes(Utilizador u){
        System.out.println("\f");
        Input input = new Input();
        try{
            System.out.println("Tem " + this.gestorNotificacoes.quantasNotificacoes(u.getNif()) + " notificações.");
        }
        catch(UtilizadorNaoExisteException e){
            System.out.println("O utilizador com nif " + e.getMessage() + " não existe no sistema.");
        }
        do {
            menuNotificacoes.executa();
            switch (menuNotificacoes.getOpcao()) {
                case 1: try{
                            List<Notificacao> notificacoes = this.gestorNotificacoes.getNotificacoes(u.getNif());
                            List<String> resultado = new ArrayList<>();
                            notificacoes.forEach(n -> resultado.add(n.toString()));
                                            
                            String titulo = "------------------------------------\n------ Caixa de Notificacoes ------\n------------------------------------";
                            this.listagem = new Listagem(resultado,titulo);
                            this.listagem.executa();
                        }
                        catch(UtilizadorNaoExisteException e){
                            System.out.println("Utilizador não existe");
                        }
                        catch(CaixaNotificacoesVaziaException e){
                            System.out.println("Nao existem notificacoes");
                        }
                    break;
                case 2: System.out.println("\fPretende limpar caixa de notificacoes?\n\n");
                        menuAceitacao.executa();
                        switch(menuAceitacao.getOpcao()){
                            case 1: System.out.println("Confirme a sua decisão.");
                                    System.out.println("Introduza a sua password.");
                                    String password = input.lerString();
                                    if(password.equals(u.getPassword())){
                                        try{
                                            this.gestorNotificacoes.apagaNotificacoes(u.getNif());
                                            System.out.println("\fCaixa de notificacoes limpa com sucesso.");
                                        }
                                        catch(UtilizadorNaoExisteException e){
                                            System.out.println("O utilizador com nif " + e.getMessage() + " não existe no sistema.");
                                        }
                                    }else{
                                        System.out.println("\fPassword incorreta.\nTente novamente mais tarde.");
                                    }
                        }
                    break;
            }
            System.out.println("\n\nPara retroceder pressione 'Enter'");
            input.lerString();
        }while (menuNotificacoes.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\n\nPara retroceder pressione 'Enter'");
        input.lerString();
        System.out.println("\f");
    }
    
    
    
    private void runProprietarioAlteraDados(Proprietario proprietario) {
        Input input = new Input();
        System.out.println("\f");
        do {
            menuProprietarioAlteraDados.executa();
            switch (menuProprietarioAlteraDados.getOpcao()) {
                case 1: System.out.println("Introduza o novo email.");
                        String email = input.lerString();
                        System.out.println("Confirme a sua decisão.");
                        System.out.println("Introduza a sua password.");
                        String password = input.lerString();
                        if(password.equals(proprietario.getPassword())){
                            proprietario.setEmail(email);
                            this.gestorUtilizadores.atualizaUtilizador(proprietario);
                            System.out.println("\fEmail atualizado com sucesso.");
                        }else{
                            System.out.println("\fPassword incorreta.\nTente novamente mais tarde.");
                        }
                        break;
                case 2: System.out.println("Introduza a nova password.");
                        String passwordNova = input.lerString();
                        System.out.println("Confirme a sua decisão.");
                        System.out.println("Introduza a sua password antiga.");
                        password = input.lerString();
                        if(password.equals(proprietario.getPassword())){
                            proprietario.setPassword(passwordNova);
                            this.gestorUtilizadores.atualizaUtilizador(proprietario);
                            System.out.println("\fPassword atualizada com sucesso.");
                        }else{
                            System.out.println("\fPassword incorreta.\nTente novamente mais tarde.");
                        }
                        break;
                case 3: System.out.println("Introduza o novo nome.");
                        String nome = input.lerString();
                        System.out.println("Confirme a sua decisão.");
                        System.out.println("Introduza a sua password.");
                        password = input.lerString();
                        if(password.equals(proprietario.getPassword())){
                            proprietario.setNome(nome);
                            this.gestorUtilizadores.atualizaUtilizador(proprietario);
                            System.out.println("\fNome atualizado com sucesso.");
                        }else{
                            System.out.println("\fPassword incorreta.\nTente novamente mais tarde.");
                        }
                        break;
                case 4: System.out.println("Introduza a nova morada.");
                        String morada = input.lerString();
                        System.out.println("Confirme a sua decisão.");
                        System.out.println("Introduza a sua password.");
                        password = input.lerString();
                        if(password.equals(proprietario.getPassword())){
                           proprietario.setMorada(morada);
                           this.gestorUtilizadores.atualizaUtilizador(proprietario);
                           System.out.println("\fMorada atualizada com sucesso.");
                        }else{
                            System.out.println("\fPassword incorreta.\nTente novamente mais tarde.");
                        }
                        break;
            }
            System.out.println("\n\nPara retroceder pressione 'Enter'!");
            input.lerString();
            System.out.println("\f");
        } while (menuProprietarioAlteraDados.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\n\nPara voltar ao menu de proprietario pressione 'Enter'!");
        input.lerString();
        System.out.println("\f");
    }
    
    
    private void runClienteAlteraDados(Cliente cliente) {
        Input input = new Input();
        System.out.println("\f");
        do {
            menuClienteAlteraDados.executa();
            switch (menuClienteAlteraDados.getOpcao()) {
                case 1: System.out.println("Introduza o novo email.");
                        String email = input.lerString();
                        System.out.println("Confirme a sua decisão.");
                        System.out.println("Introduza a sua password.");
                        String password = input.lerString();
                        if(password.equals(cliente.getPassword())){
                            cliente.setEmail(email);
                            this.gestorUtilizadores.atualizaUtilizador(cliente);
                            System.out.println("\fEmail atualizado com sucesso.");
                        }else{
                            System.out.println("\fPassword incorreta.\nTente novamente mais tarde.");
                        }
                        break;
                case 2: System.out.println("Introduza a nova password.");
                        String passwordNova = input.lerString();
                        System.out.println("Confirme a sua decisão.");
                        System.out.println("Introduza a sua password antiga.");
                        password = input.lerString();
                        if(password.equals(cliente.getPassword())){
                            cliente.setPassword(passwordNova);
                            this.gestorUtilizadores.atualizaUtilizador(cliente);
                            System.out.println("\fPassword atualizada com sucesso.");
                        }else{
                            System.out.println("\fPassword incorreta.\nTente novamente mais tarde.");
                        }
                        break;
                case 3: System.out.println("Introduza o novo nome.");
                        String nome = input.lerString();
                        System.out.println("Confirme a sua decisão.");
                        System.out.println("Introduza a sua password.");
                        password = input.lerString();
                        if(password.equals(cliente.getPassword())){
                            cliente.setNome(nome);
                            this.gestorUtilizadores.atualizaUtilizador(cliente);
                            System.out.println("\fNome atualizado com sucesso.");
                        }else{
                            System.out.println("\fPassword incorreta.\nTente novamente mais tarde.");
                        }
                        break;
                case 4: System.out.println("Introduza a nova morada.");
                        String morada = input.lerString();
                        System.out.println("Confirme a sua decisão.");
                        System.out.println("Introduza a sua password.");
                        password = input.lerString();
                        if(password.equals(cliente.getPassword())){
                           cliente.setMorada(morada);
                           this.gestorUtilizadores.atualizaUtilizador(cliente);
                           System.out.println("\fMorada atualizada com sucesso.");
                        }else{
                            System.out.println("\fPassword incorreta.\nTente novamente mais tarde.");
                        }
                        break;
                case 5: System.out.println("Introduza a nova localizaçao i.e. (x,y):\n");
                        System.out.println("*Digite a coordenada 'x'.");
                        double xC = input.lerDouble();
                        System.out.println("*Digite a coordenada 'y'.");
                        double yC = input.lerDouble();
                        Ponto novoPonto = new Ponto(xC,yC);
                        System.out.println("Confirme a sua decisão.");
                        System.out.println("Introduza a sua password.");
                        password = input.lerString();
                        if(password.equals(cliente.getPassword())){
                           cliente.setLocalizacao(novoPonto);
                           this.gestorUtilizadores.atualizaUtilizador(cliente);
                           System.out.println("\fLocalizacao atualizada com sucesso.");
                        }else{
                            System.out.println("\fPassword incorreta.\nTente novamente mais tarde.");
                        }
            }
            System.out.println("\n\nPara retroceder pressione 'Enter'!");
            input.lerString();
            System.out.println("\f");
        } while (menuClienteAlteraDados.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\n\nPara voltar ao menu de cliente pressione 'Enter'!");
        input.lerString();
        System.out.println("\f");
    }
}