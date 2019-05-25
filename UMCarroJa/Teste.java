import java.util.Scanner;
import java.util.*;
import App.*;
import MyLogic.ClassesBase.*;
import MyLogic.Gestores.*;
import MyLogic.Exceptions.*;
import java.lang.*;
import java.io.*;

public class Teste{
    
    public static void main (String[] args){
        
        Scanner sc = new Scanner(System.in);
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        GestorUtilizadores gestorUtilizadores = new GestorUtilizadores();
        GestorAlugueres gestorAlugueres = new GestorAlugueres();
        GestorNotificacoes gestorNotificacoes = new GestorNotificacoes();
        Leitura l = new Leitura("./logsPOO_carregamentoInicial.txt");
        
        try{
            l.readFile(gestorUtilizadores, gestorVeiculos, gestorAlugueres, gestorNotificacoes);
            
            gestorVeiculos.saveStatus();
            System.out.println("GestorVeiculos Save Done");
            
            gestorUtilizadores.saveStatus();
            System.out.println("GestorUtilizadores Save Done");
            
            gestorAlugueres.saveStatus();
            System.out.println("GestorAlugueres Save Done");
            
            gestorNotificacoes.saveStatus();
            System.out.println("GestorNotificacoes Save Done");
            
            GestorVeiculos g = new GestorVeiculos();
            g = g.loadStatus();
            System.out.println(g.toString());
        }
        catch(UtilizadorJaExisteException e){
            System.out.println("O utilizador com nif " + e.getMessage() + " já existe no sistema!");
        }
        catch(VeiculoJaExisteException e){
            System.out.println("O veículo com matrícula " + e.getMessage() + " já existe no sistema!");
        }
        catch(AluguerJaExisteException e){
            System.out.println(e.getMessage());
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
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
       
       
        /*
        // Aluguer
        
        Ponto destino = new Ponto (0,0);
        Aluguer a1 = new Aluguer (TipoVeiculo.Carro,256709718, destino, TipoCombustivel.Gasolina, PreferenciaAluguer.MaisPertoBarato);
        List<Veiculo> veiculos = new ArrayList<>();
        Veiculo veiculo = new Veiculo();
           
        String escolhaVeiculo;
        
        try{
           Utilizador util = gestorUtilizadores.getUtilizador(a1.getNif());
           Cliente cli = (Cliente) util;
           switch(a1.getPreferencia()){
            case MaisBarato:
                veiculos = gestorVeiculos.veiculoMaisBarato(a1.getTipoVeiculo(), a1.getTipoCombustivel(), destino);
                for (Veiculo v : veiculos) System.out.println(v.toString());
                break;
                
            case MaisPerto:
                veiculos = gestorVeiculos.veiculoMaisPerto(a1.getTipoVeiculo(), a1.getTipoCombustivel(), cli.getLocalizacao(), destino);
                for (Veiculo v : veiculos) System.out.println(v.toString());
                break;
                
            case MaisPertoBarato:
                System.out.println("Introduza a distancia que esta disposto a percorrer!");
                double distanciaMax = sc.nextDouble();
                veiculos = gestorVeiculos.veiculoMaisPertoBarato(a1.getTipoVeiculo(), a1.getTipoCombustivel(), cli.getLocalizacao(), 
                                                                 destino, distanciaMax);
                for (Veiculo v : veiculos) System.out.println(v.toString());
                break;
                
            case Especifico:
                System.out.println("Introduza a matricula do veiculo que pretende alugar!");
                String matricula = sc.nextLine();
                veiculo = gestorVeiculos.veiculoEspecifico(matricula, destino);
                System.out.println(veiculo.toString());
                break;
                
            case Autonomia:
            default:
                System.out.println("Introduza a distancia que esta disposto a percorrer!");
                double autonomia = sc.nextDouble();
                veiculos = gestorVeiculos.veiculoAutonomia(a1.getTipoVeiculo(), a1.getTipoCombustivel(), autonomia);
                for (Veiculo v : veiculos) System.out.println(v.toString());
                break;
           }
           sc.nextLine();
           System.out.println("Indique a matricula do veiculo que pretende alugar.");
           String matricula = sc.nextLine();
           veiculo = gestorVeiculos.getVeiculo(matricula);
           a1.setVeiculo(veiculo);
           gestorAlugueres.insereAluguer(a1);
           Notificacao not = a1.pedidoAluguer(cli);
           gestorNotificacoes.adicionaNotificacao(not);
           System.out.println(a1.toString());
           a1.pedidoAluguer(cli);
           Proprietario pr = (Proprietario)gestorUtilizadores.getUtilizador(veiculo.getNif());
           pr.confirmaAluguer(a1);
           a1.efetuaViagem(cli);
           System.out.println("\n\n\n\n\n\n\n\n\n" + a1.toString());
           a1.registaCusto();
           System.out.println("\n\n\n\n\n\n\n\n\n" + a1.toString());
        }
        
        catch(UtilizadorNaoExisteException e){
            System.out.println(e.getMessage());
        }
        catch(VeiculoNaoExisteException e){
            System.out.println(e.getMessage());
        }
        catch(AluguerJaExisteException e){
            System.out.println(e.getMessage());
        }
        catch(AluguerNaoExisteException e){
            System.out.println(e.getMessage());
        }
        */
    }
}
