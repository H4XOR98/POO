
import java.util.*;
import ClassesBase.*;
import Gestores.*;
import Exceptions.*;

public class Teste{
    
    public static void main (String[] args){
        
        /*
         * Utilizadores
         */
        Utilizador pr1 = new Proprietario("rubnitos@hotmail.com","Sete7Sete","Ruben",145573473,"Travessa do Gatao");
        Utilizador pr2 = new Proprietario("jorgemanel@gmail.com","joaquim2","Jorge",859204758,"Vale dos fazeres");
        Utilizador pr3 = new Proprietario("pedritonene@outlook.com","souavariado","Pedro",97261940,"Avenida dos casados");
        Utilizador pr4 = new Proprietario("avosinha@hotmail.com","Bolinhos1","Avosinha",947163950,"Rua das parafusas");
        Utilizador pr5 = new Proprietario("numeiro@hotmail.com","youtuber12","Nuno", 948275930,"Avenida da Liberdade");
        Utilizador pr6 = new Proprietario("carlitos@hotmail.com","naoMeAmo","Carlos",948173940,"Rua dos mares");
        
        Ponto pc1 = new Ponto (4,4);
        Utilizador c1 = new Cliente ("goncalo.iosx@gmail.com", "pilinha", "Gonçalo Almeida", 256709718, "Vila do Conde", pc1);
        Ponto pc2 = new Ponto (84.42827, 34.340637);
        Utilizador c2 = new Cliente ("564497795@gmail.com", "564497795", "Maria Francisca Tavares Martins Fernandes", 564497795, "Seixal", pc2);
        Ponto pc3 = new Ponto (-15.797783, 90.88934);
        Utilizador c3 = new Cliente ("476759518@gmail.com", "476759518", "Emanuel de Jesus Pedrosa Viana", 476759518, "Celorico de Basto", pc3);
        
        //-------------
        /*
         * Veiculos
         */
        TipoVeiculo tv1 = TipoVeiculo.Carro;
        TipoCombustivel tb1 = TipoCombustivel.Gasolina;
        Ponto p1 = new Ponto (20,20);
        Veiculo v1 = new Veiculo (tv1, tb1, "BMW", "28-AB-47", 145573473, 120, 2.0, 1.5, 700.0, p1);
        
        TipoVeiculo tv2 = TipoVeiculo.Carro;
        TipoCombustivel tb2 = TipoCombustivel.Eletrico;
        Ponto p2 = new Ponto (30,30);
        Veiculo v2 = new Veiculo (tv2, tb2, "Testa", "11-AB-22", 859204758, 140, 1.5, 2.0, 550.0, p2);
        
        TipoVeiculo tv3 = TipoVeiculo.Carro;
        TipoCombustivel tb3 = TipoCombustivel.Hibrido;
        Ponto p3 = new Ponto (25,25);
        Veiculo v3 = new Veiculo (tv3, tb3, "Nissan", "35-FS-75", 97261940, 110, 2.5, 1.0,650.0, p3);
        //-------------
        
        /*
         * Gestores
         */
        GestorVeiculos gestorVeiculos = new GestorVeiculos();
        GestorUtilizadores gestorUtilizadores = new GestorUtilizadores();
        try{
            gestorUtilizadores.insereUtilizador(pr1);
            gestorUtilizadores.insereUtilizador(pr2);
            gestorUtilizadores.insereUtilizador(pr3);
            gestorUtilizadores.insereUtilizador(pr4);
            gestorUtilizadores.insereUtilizador(pr5);
            gestorUtilizadores.insereUtilizador(pr6);
            gestorUtilizadores.insereUtilizador(c1);
            gestorUtilizadores.insereUtilizador(c2);
            gestorUtilizadores.insereUtilizador(c3);
        }
        catch (UtilizadorJaExisteException e){
            System.out.println(e.getMessage());
        }
        
        
        
        try{
            gestorVeiculos.insereVeiculo(v1);
            gestorVeiculos.insereVeiculo(v2);
            gestorVeiculos.insereVeiculo(v3);
        }
        
        catch (UtilizadorNaoExisteException e){
            System.out.println(e.getMessage());
        }
        catch
        
        
        //-------------
        
        /*
         * Aluguer
         */
        Aluguer a1 = new Aluguer(256709718,pc1,TipoCombustivel.Gasolina,PreferenciaAluguer.MaisBarato);
        
        
        System.out.println(gestorVeiculos.toString());
        
        
    }
}
