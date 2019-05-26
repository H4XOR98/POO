package MyLogic.Gestores;

import MyLogic.ClassesBase.*;
import MyLogic.Exceptions.*;

public class Teste{
    
    public static void main (String[] args){
        
        GestorNotificacoes gn = new GestorNotificacoes();
        Notificacao n1 = new Notificacao (13, "ola", "jdgdggdg");
        Notificacao n2 = new Notificacao (34, "ole", "bbbbbbbb");
        
        Utilizador u1 = new Proprietario ("hhhhhh", "fffff", "ruben", 13, "mmmmmmm");
        Utilizador u2 = new Proprietario ("hhhhhh", "fffff", "rubeninho", 34, "mmmmmmm");
        
        try{
            gn.adicionaUtilizador(u1);
            gn.adicionaUtilizador(u2);
        }
        catch (UtilizadorJaExisteException e){
            System.out.println("erro");
        }
        
        System.out.println(gn.toString());
        
        try{
            gn.apagaNotificacoes(13);
        }
        catch (UtilizadorNaoExisteException e){
            System.out.println("erro");
        }
        
        System.out.println(gn.toString());
        
    }
        
        
    
}
