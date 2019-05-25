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
                menuProprietarioHistoricos, menuProprietarioVeiculos, menuConfirmacaoAluguer;
    private Listagem listagem;
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
                          "Login",
                          "TOP 10 Clientes"};
        
        String[] opcoesRegisto = {"Registar como Cliente",
                                  "Registar como Proprietario"};
                                  
        String[] opcoesTop10 = {"em numero de vezes",
                               "em quilometros"};
        
        // ------------------------------------------------------- 
                               
        String[] opcoesCliente = {"Consultar Caixa Notificacoes",
                                  "Aluguer",
                                  "Historicos"};
                                  
        String[] opcoesClienteAluguer = {"Alugar Veiculo",
                                         "Realizar Viagem"};
                                         
        String[] opcoesClienteHistoricos = {"Historico Aluguer",
                                            "Historico Aluguer(entre datas)"};
                                                            
        // ------------------------------------------------------- 
                                            
        String[] opcoesProprietario = {"Consultar Caixa Notificacoes",
                                       "Aluguer",
                                       "Historico",
                                       "Definicoes Veiculo"};
                                       
                                       
        String[] opcoesProprietarioAluguer = {"Confirmar Aluguer",
                                              "Registar custo viagem"};
       
        String[] opcoesConfirmacaoAluguer = {"Aceitar","Rejeitar"};                                      
                                              
        String[] opcoesProprietarioHistoricos = {"Historico Aluguer",
                                                 "Historico Aluguer(entre datas)",
                                                 "Total faturado por um Veiculo"};                               
                                       
        String[] opcoesProprietarioVeiculos = {"Registar Veiculo",
                                               "Alterar o preco por km de um Veiculo",
                                               "Abastecer Veiculo"};                                         
                                              
        
                                       
        this.gestorVeiculos = new GestorVeiculos();
        this.gestorUtilizadores = new GestorUtilizadores();
        this.gestorAlugueres = new GestorAlugueres();
        this.gestorNotificacoes = new GestorNotificacoes();  
        
        this.menuPrincipal = new MenuPrincipal(opcoes);
        this.menuRegisto = new MenuRegisto(opcoesRegisto);
        this.menuTop10 = new Menu(opcoesTop10);
        
        this.menuCliente = new MenuCliente(opcoesCliente);
        this.menuClienteAluguer = new MenuCliente(opcoesClienteAluguer);
        this.menuClienteHistoricos = new MenuCliente(opcoesClienteHistoricos);
        
        this.menuProprietario = new MenuProprietario(opcoesProprietario);
        this.menuProprietarioAluguer = new MenuProprietario(opcoesProprietarioAluguer);
        this.menuConfirmacaoAluguer = new Menu(opcoesConfirmacaoAluguer);
        this.menuProprietarioHistoricos = new MenuProprietario(opcoesProprietarioHistoricos);
        this.menuProprietarioVeiculos = new MenuProprietario(opcoesProprietarioVeiculos);
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
                        System.out.println("\f");
                        Utilizador u = null;
                        try{
                            u = this.gestorUtilizadores.loginUtilizador(email,password);
                        }
                        catch(UtilizadorNaoExisteException e){
                            System.out.println(e.getMessage());
                        }
                        if(u != null && u.getClass().getSimpleName().equals("Cliente")){
                           Cliente cliente = (Cliente)u;
                           System.out.println("Bem vindo Sr. " + cliente.getNome());
                           System.out.println("\n\n\n\nPara prosseguir presione Enter!");
                           input.lerString();
                           System.out.println("\f");
                           runCliente(cliente);
                        }
                        if(u != null && u.getClass().getSimpleName().equals("Proprietario")){
                            Proprietario proprietario = (Proprietario)u;
                            System.out.println("Bem vindo Sr. " + proprietario.getNome());
                            System.out.println("\n\n\n\nPara prosseguir presione Enter!");
                            input.lerString();
                            System.out.println("\f");
                            runProprietario(proprietario);
                        }
                        System.out.println("\n\n\n\nPara sair presione Enter!");
                        input.lerString();
                        break;
                case 3: runTop();
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
                        System.out.println("\f");
                        Ponto pontoC = new Ponto(xC,yC);
                        Utilizador c = new Cliente(emailC,passwordC,nomeC,nifC,moradaC,pontoC);
                        try {
                            this.gestorUtilizadores.insereUtilizador(c);
                            this.gestorNotificacoes.adicionaUtilizador(c);
                            System.out.println("Registo concluido com sucesso.");
                        }
                        catch(UtilizadorJaExisteException e){
                             System.out.println(e.getMessage());
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                        }
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
                        System.out.println("\f");
                
                        Utilizador p = new Proprietario(emailP,passwordP,nomeP,nifP,moradaP);
                        try {
                            this.gestorUtilizadores.insereUtilizador(p);
                            this.gestorNotificacoes.adicionaUtilizador(p);
                            System.out.println("Registo concluido com sucesso.");
                        }
                        catch(UtilizadorJaExisteException e){
                             System.out.println(e.getMessage());
                        }
                        catch(Exception e){
                            System.out.println(e.getMessage());
                        }       
                        System.out.println("\n\n\n\nPara sair pressione enter!");
                        input.lerString();
                        break;
            }
        } while (menuRegisto.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\f");
    }
    
    private void runTop(){
        Input input = new Input();
        do {
            menuTop10.executa();
            switch (menuTop10.getOpcao()) {
                case 1: System.out.println("TOP 10 Clientes Numero de Utilizacoes");
                        try{
                            this.listagem = new Listagem(this.gestorUtilizadores.procuraUtilizadores(this.gestorAlugueres.topDezClientesVezes()));
                        }
                        catch(UtilizadorNaoExisteException e){
                            System.out.println(e.getMessage());
                        }
                        catch(AluguerNaoExisteException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                case 2: System.out.println("TOP 10 Clientes Quilometros");
                        try{
                            this.listagem = new Listagem(this.gestorUtilizadores.procuraUtilizadores(this.gestorAlugueres.topDezClientesKms()));
                        }
                        catch(UtilizadorNaoExisteException e){
                            System.out.println(e.getMessage());
                        }
                        catch(AluguerNaoExisteException e){
                            System.out.println(e.getMessage());
                        }
                        break;
            }
        } while (menuRegisto.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\f");
        if(this.listagem != null){//Vazia ATENCAO
            System.out.println("Nao existem utilizadores registados!");
        }
        System.out.println("\n\n\n\nPara sair pressione enter!");
        input.lerString();
    }
    
    
    
    private void runCliente(Cliente cliente) {
        try{
            System.out.println("Tem " + this.gestorNotificacoes.quantasNotificacoes(cliente.getNif()) + " notificacoes.");
        }
        catch(UtilizadorNaoExisteException e){
            System.out.println(e.getMessage());
        }
        do {
            menuCliente.executa();
            switch (menuCliente.getOpcao()) {
                case 1: 
                       break; 
                case 2: runClienteAluguer(cliente);
                       break;
                case 3: runClienteHistoricos(cliente);
                       break;
            }
        } while (menuRegisto.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\f");
    }
    
    private void runClienteAluguer(Cliente cliente) {
        Input input = new Input();
        do {
            menuClienteAluguer.executa();
            switch (menuClienteAluguer.getOpcao()) {
                case 1: System.out.println("\fIntroduza a localizaçao do seu destino i.e. (x,y):\n");
                        System.out.println("\t-Digite a coordenada 'x'.");
                        double xC = input.lerDouble();
                        System.out.println("\t-Digite a coordenada 'y'.");
                        double yC = input.lerDouble();
                        Ponto destino = new Ponto(xC,yC);
                        System.out.println("\f");
                        try{
                            System.out.println("Tipo de veiculo pretendido:");
                            //this.listagem = new Listagem(Arrays.asList(TipoVeiculo.values()));
                            String str = input.lerString();
                            TipoVeiculo tipoVeiculo = TipoVeiculo.valueOf(str);
                            System.out.println("Tipo de combustivel pretendido:");
                            //this.listagem = new Listagem(Arrays.asList(TipoCombustivel.values()));
                            str = input.lerString();
                            TipoCombustivel tipoCombustivel = TipoCombustivel.valueOf(str);
                            System.out.println("Preferencia de Aluguer pretendido:");
                            //this.listagem = new Listagem(Arrays.asList(TipoCombustivel.values()));
                            str = input.lerString();
                            PreferenciaAluguer preferencia = PreferenciaAluguer.valueOf(str);
                            Aluguer a = new Aluguer(tipoVeiculo,cliente.getNif(),destino,tipoCombustivel,preferencia);
                            
                            List<String> veiculos = null;
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
                                    System.out.println("Introduza a distancia que esta disposto a percorrer!");
                                    double autonomia = input.lerDouble();
                                    veiculos = gestorVeiculos.veiculoAutonomia(a.getTipoVeiculo(), a.getTipoCombustivel(), autonomia);
                                    break;
                            }
                            this.gestorAlugueres.insereAluguer(a);
                            /*ATENCAO
                            if(veiculos != null){
                                this.listagem = new Listagem(veiculos);
                            }*/
                            if(veiculo == null){
                                System.out.println("Introduza a matricula do veiculo que pretende alugar!");
                                matricula = input.lerString();
                                veiculo = this.gestorVeiculos.getVeiculo(matricula);
                            }
                            System.out.println("O veiculo que quer alugar: \n" + veiculo.toString());
                        }
                        catch(IllegalArgumentException e){
                            System.out.println("Tipo invalido");
                        }
                        catch(AluguerJaExisteException e){
                            System.out.println(e.getMessage());
                        }
                        catch(VeiculoNaoExisteException e){
                            System.out.println(e.getMessage());
                        }
                       break;
                case 2: int id = input.lerInt();
                        try{
                            Aluguer a = this.gestorAlugueres.getAluguer(id);
                            Notificacao notificacao = a.efetuaViagem(cliente);
                            if(notificacao != null){
                                this.gestorNotificacoes.adicionaNotificacao(notificacao);
                            }
                            System.out.println("Insira uma nota para o Veiculo.");
                            double notaVeiculo = input.lerInt();
                            System.out.println("Insira uma nota para o Proprietario.");
                            double notaProprietario = input.lerInt();
                            Proprietario p = (Proprietario)this.gestorUtilizadores.getUtilizador(a.getVeiculo().getNif());
                            a.avaliacoesCliente(p, notaVeiculo, notaProprietario);
                            this.gestorAlugueres.insereAluguer(a);
                            this.gestorUtilizadores.atualizaUtilizador(p);
                        }
                        catch(AluguerNaoExisteException e){
                            System.out.println(e.getMessage());
                        }
                        catch(AluguerJaExisteException e){
                            System.out.println(e.getMessage());
                        }
                        catch(UtilizadorNaoExisteException e){
                            System.out.println(e.getMessage());
                        }
                        catch(AvaliacaoInvalidaException e){
                            System.out.println(e.getMessage());
                        }
                        break;     
            }
        } while (menuClienteAluguer.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\f");
    }
    
    private void runClienteHistoricos(Cliente cliente) {
        Input input = new Input();
        List<String> historico = null;
        do {
            menuClienteHistoricos.executa();
            switch (menuClienteHistoricos.getOpcao()) {
                case 1: historico = this.gestorAlugueres.historicoCliente(cliente.getNif());
                        break;
                case 2: try{
                            System.out.println("Introduza a data de Inicio.");
                            System.out.println("Introduza o ano;");
                            int ano = input.lerInt();
                            System.out.println("\fIntroduza o mes;");
                            int mes = input.lerInt();
                            System.out.println("\fIntroduza o dia;");
                            int dia = input.lerInt();
                            System.out.println("\fIntroduza a hora;");
                            int hora = input.lerInt();
                            System.out.println("\fIntroduza os minutos;");
                            int minutos = input.lerInt();
                            LocalDateTime inicio = LocalDateTime.of(ano, mes, dia, hora, minutos);
                            System.out.println("Introduza a data de Fim.");
                            System.out.println("Introduza o ano;");
                            ano = input.lerInt();
                            System.out.println("\fIntroduza o mes;");
                            mes = input.lerInt();
                            System.out.println("\fIntroduza o dia;");
                            dia = input.lerInt();
                            System.out.println("\fIntroduza a hora;");
                            hora = input.lerInt();
                            System.out.println("\fIntroduza os minutos;");
                            minutos = input.lerInt();
                            LocalDateTime fim = LocalDateTime.of(ano, mes, dia, hora, minutos);
                            historico = this.gestorAlugueres.historicoClienteEntreDatas(cliente.getNif(),inicio,fim);
                        }
                        catch(DateTimeException e){
                            System.out.println("UPS! Data Invalida!");
                        }
                        break;    
            }
        } while (menuClienteHistoricos.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        if(historico != null){
            //this.listagem = new Listagem(Arrays.asList(TipoCombustivel.values()));
        }
        System.out.println("\f");
    }
    
    
    private void runProprietario(Proprietario proprietario) {
        Input input = new Input();
        try{
            System.out.println("Tem " + this.gestorNotificacoes.quantasNotificacoes(proprietario.getNif()) + " notificacoes.");
        }
        catch(UtilizadorNaoExisteException e){
            System.out.println(e.getMessage());
        }
        do {
            menuProprietario.executa();
            switch (menuProprietario.getOpcao()) {
                case 1: 
                        break;
                case 2: runProprietarioAluguer();
                        break;
                case 3: runProprietarioHistoricos(proprietario);
                        break;
                case 4:
                        break;
            }
        } while (menuProprietario.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\f");
    }
    
    
    private void runProprietarioAluguer() {
        Input input = new Input();
        do {
            menuProprietarioAluguer.executa();
            System.out.println("Introduza o Id do aluguer.");
            int id = input.lerInt();
            try{
                Aluguer a = this.gestorAlugueres.getAluguer(id);
                switch (menuProprietarioAluguer.getOpcao()) {
                    case 1: runConfirmacaoAluguer(a);
                            //ATENCAO
                            //this.gestorAlugueres.atualizaAluguer(a);
                        break;
                    case 2: a.registaCusto();
                            System.out.println("Introduza nota do Cliente.");
                            int notaCliente = input.lerInt();
                            Cliente cliente = (Cliente)this.gestorUtilizadores.getUtilizador(a.getNif());
                            a.avaliacaoProprietario(cliente,notaCliente);
                            this.gestorUtilizadores.atualizaUtilizador(cliente);
                        break; 
                }
            }
            catch(AluguerNaoExisteException e){
                System.out.println(e.getMessage());
            }
            catch(AvaliacaoInvalidaException e){
                System.out.println(e.getMessage());
            }
            catch(UtilizadorNaoExisteException e){
                System.out.println(e.getMessage());
            }
        }while (menuProprietarioAluguer.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\f");
    }                       
       
   
    private void runConfirmacaoAluguer(Aluguer a) {
        do {
            System.out.println("Pretende confirmar o Aluguer?");
            menuConfirmacaoAluguer.executa();
            switch (menuConfirmacaoAluguer.getOpcao()) {
                case 1: a.aceitaAluguer();
                    break;
                case 2: a.rejeitaAluguer();
                    break;
            }
        }while (menuConfirmacaoAluguer.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\f");
    }
    
    private void runProprietarioHistoricos(Proprietario proprietario){
        Input input = new Input();
        List<String> historico = null;
        do {
            menuProprietarioHistoricos.executa();
            switch (menuProprietarioHistoricos.getOpcao()) {
                case 1: historico = this.gestorAlugueres.historicoProprietario(proprietario.getNif());
                        break;
                case 2: try{
                            System.out.println("Introduza a data de Inicio.");
                            System.out.println("Introduza o ano;");
                            int ano = input.lerInt();
                            System.out.println("\fIntroduza o mes;");
                            int mes = input.lerInt();
                            System.out.println("\fIntroduza o dia;");
                            int dia = input.lerInt();
                            System.out.println("\fIntroduza a hora;");
                            int hora = input.lerInt();
                            System.out.println("\fIntroduza os minutos;");
                            int minutos = input.lerInt();
                            LocalDateTime inicio = LocalDateTime.of(ano, mes, dia, hora, minutos);
                            System.out.println("Introduza a data de Fim.");
                            System.out.println("Introduza o ano;");
                            ano = input.lerInt();
                            System.out.println("\fIntroduza o mes;");
                            mes = input.lerInt();
                            System.out.println("\fIntroduza o dia;");
                            dia = input.lerInt();
                            System.out.println("\fIntroduza a hora;");
                            hora = input.lerInt();
                            System.out.println("\fIntroduza os minutos;");
                            minutos = input.lerInt();
                            LocalDateTime fim = LocalDateTime.of(ano, mes, dia, hora, minutos);
                            historico = this.gestorAlugueres.historicoProprietarioEntreDatas(proprietario.getNif(),inicio,fim);
                        }
                        catch(DateTimeException e){
                            System.out.println("UPS! Data Invalida!");
                        }
                        break;
                case 3: System.out.println("Os seus Veiculos:");
                        //this.listagem = new Listagem(this.gestorUtilizadores.)
                        System.out.println("Introduza a matricula do veiculo que pretende saber o total faturado.");
                        String matricula = input.lerString();
                        System.out.println("\fIntroduza a data de Inicio.");
                        System.out.println("Introduza o ano;");
                        int ano = input.lerInt();
                        System.out.println("\fIntroduza o mes;");
                        int mes = input.lerInt();
                        System.out.println("\fIntroduza o dia;");
                        int dia = input.lerInt();
                        System.out.println("\fIntroduza a hora;");
                        int hora = input.lerInt();
                        System.out.println("\fIntroduza os minutos;");
                        int minutos = input.lerInt();
                        LocalDateTime inicio = LocalDateTime.of(ano, mes, dia, hora, minutos);
                        System.out.println("Introduza a data de Fim.");
                        System.out.println("Introduza o ano;");
                        ano = input.lerInt();
                        System.out.println("\fIntroduza o mes;");
                        mes = input.lerInt();
                        System.out.println("\fIntroduza o dia;");
                        dia = input.lerInt();
                        System.out.println("\fIntroduza a hora;");
                        hora = input.lerInt();
                        System.out.println("\fIntroduza os minutos;");
                        minutos = input.lerInt();
                        LocalDateTime fim = LocalDateTime.of(ano, mes, dia, hora, minutos);
                        System.out.println("O veiculo com matricula " + matricula + " faturou " + this.gestorAlugueres.totalFaturadoVeiculo(matricula, inicio, fim) + 
                                "€ entre " + inicio.toString() + " e " + fim.toString() + ".");
                        break;
            }
        } while (menuClienteHistoricos.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        if(historico != null){
            //this.listagem = new Listagem(Arrays.asList(TipoCombustivel.values()));
        }
    }
                
     private void runConfirmacaoAluguer(Proprietario proprietario) {
        Input input = new Input();
        do {
            menuProprietarioVeiculos.executa();
            switch (menuProprietarioVeiculos.getOpcao()) {
                case 1: System.out.println("Tipos de veiculo disponiveis:");
                        //this.listagem = new Listagem(Arrays.asList(TipoVeiculo.values()));
                        String str = input.lerString();
                        TipoVeiculo tipoVeiculo = TipoVeiculo.valueOf(str);
                        System.out.println("\fTipos de combustivel disponiveis:");
                        //this.listagem = new Listagem(Arrays.asList(TipoCombustivel.values()));
                        str = input.lerString();
                        TipoCombustivel tipoCombustivel = TipoCombustivel.valueOf(str);
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
                        try{
                            this.gestorVeiculos.insereVeiculo(veiculo);
                            System.out.println("\fVeiculo registado com suceso.");
                        }
                        catch(VeiculoJaExisteException e){
                            System.out.println(e.getMessage());
                        }
                        System.out.println("\n\n\n\nPara sair pressione enter!");
                        input.lerString();
                        break;
                case 2: 
                        break;
                case 3:
                    
                        break;
            }
        }while (menuProprietarioVeiculos.getOpcao()!=0); // A op¡Ño 0 » usada para sair do menu.
        System.out.println("\f");
    }
                                       
        String[] opcoesProprietarioVeiculos = {"Registar Veiculo",
                                               "Alterar o preco por km de um Veiculo",
                                               "Abastecer Veiculo"};   
}