package App;


import java.util.*;
import MyLogic.ClassesBase.*;
import MyLogic.Gestores.*;
import MyLogic.Exceptions.*;
import java.lang.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Leitura{
    
    // Variávies de Instância
    
    private String file;
    
    // Construtores
    
    public Leitura (String file){
        this.file = file;
    }
    
    public Leitura (Leitura leitura){
        this.file = leitura.getFile();
    }
    
    // Gets
    
    public String getFile(){
        return this.file;
    }
    
    // Sets
    
    public void setFile (String newFile){
        this.file = newFile;
    }
    
    // Clone
    
    public Leitura clone(){
        return new Leitura(this);
    }
    
    // Equals
    
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if((o == null) || (o.getClass() != this.getClass())){
            return false;
        }

        Leitura leitura = (Leitura)o;

        return this.file.equals(leitura.getFile());
    }
    
    // Ler do ficheiro para os gestores
    
    public void readFile (GestorUtilizadores gestorUtilizadores, GestorVeiculos gestorVeiculos, GestorAlugueres gestorAlugueres, 
    GestorNotificacoes gestorNotificacoes) throws GestorVazioException, UtilizadorJaExisteException, VeiculoJaExisteException, AluguerJaExisteException, 
    UtilizadorNaoExisteException, VeiculoNaoExisteException, AvaliacaoInvalidaException, IOException, FileNotFoundException, 
    AluguerNaoExisteException, VeiculoNaoEncontradoException {
               
        BufferedReader br = new BufferedReader(new FileReader(this.file));
        
        for (String line; (line = br.readLine()) != null; ) {
            
            String[] parts = line.split(":");
            String comando = parts[0];
            String componentes;
            String[] comp;
                
            switch (parts[0]){
                    
                case "NovoProp":
                    componentes = parts[1];
                    comp = componentes.split(",");
                    Utilizador p = new Proprietario (comp[2], comp[1], comp[0], Integer.parseInt(comp[1]), comp[3]);
                    gestorUtilizadores.insereUtilizador(p);
                    gestorNotificacoes.adicionaUtilizador(p);
                    break;
                        
                case "NovoCliente":
                    componentes = parts[1];
                    comp = componentes.split(",");
                    Ponto pc = new Ponto (Double.parseDouble(comp[4]), Double.parseDouble(comp[5]));
                    Utilizador c = new Cliente (comp[2], comp[1], comp[0], Integer.parseInt(comp[1]), comp[3], pc);
                    gestorUtilizadores.insereUtilizador(c);
                    gestorNotificacoes.adicionaUtilizador(c);
                    break;
                    
                case "NovoCarro":
                    componentes = parts[1];
                    comp = componentes.split(",");
                    TipoVeiculo tv1 = TipoVeiculo.valueOf("Carro");
                    TipoCombustivel tc1 = TipoCombustivel.valueOf(comp[0]);
                    Ponto pv = new Ponto (Double.parseDouble(comp[6]), Double.parseDouble(comp[7]));
                    Veiculo v = new Veiculo (tv1, tc1, comp[1], comp[2], Integer.parseInt(comp[3]), Integer.parseInt(comp[4]), 
                                                 Double.parseDouble(comp[5]), Double.parseDouble(comp[6]), Double.parseDouble(comp[7]), pv);
                    gestorVeiculos.insereVeiculo(v);
                    break;
                        
                case "Aluguer":
                    componentes = parts[1];
                    comp = componentes.split(",");
                    TipoVeiculo tv2 = TipoVeiculo.valueOf("Carro");
                    TipoCombustivel tc2 = TipoCombustivel.valueOf(comp[3]);
                    PreferenciaAluguer pf = PreferenciaAluguer.valueOf(comp[4]);
                    Ponto destino = new Ponto (Double.parseDouble(comp[1]), Double.parseDouble(comp[2]));
                    Aluguer a = new Aluguer (tv2, Integer.parseInt(comp[0]), destino, tc2, pf);
                    
                    String matricula = "";
                    Utilizador ua = gestorUtilizadores.getUtilizador(Integer.parseInt(comp[0]));
                    Cliente ca = (Cliente)ua;
                    Ponto pca = ca.getLocalizacao();
                    
                    if (pf.equals(PreferenciaAluguer.MaisPerto)){
                        matricula = gestorVeiculos.veiculoMaisPertoFile(tv2, tc2, pca, destino).get(0).getMatricula();
                    }
                    if (pf.equals(PreferenciaAluguer.MaisBarato)){
                        matricula = gestorVeiculos.veiculoMaisBaratoFile(tv2, tc2, destino).get(0).getMatricula();
                    }
                    
                    a.setVeiculo(gestorVeiculos.getVeiculo(matricula));
                    a.setEstadoAluguer(EstadoAluguer.Aceite);
                    a.efetuaViagemFile(ca);
                    a.setCusto(a.getVeiculo().getPreco() * a.getDistancia());
                    gestorAlugueres.insereAluguer(a);
                    break;
                        
                case "Classificar":
                    componentes = parts[1];
                    comp = componentes.split(",");
                    if (comp[0].indexOf("-") != -1){
                        Veiculo veiculo = gestorVeiculos.getVeiculo(comp[0]);
                        veiculo.novaAvaliacao(Double.parseDouble(comp[1]));
                        gestorVeiculos.atualizaVeiculo(veiculo);
                    }
                    else {
                        if (gestorUtilizadores.getUtilizador(Integer.parseInt(comp[0])).getClass().getSimpleName().equals("Proprietario")){
                            Utilizador u1 = gestorUtilizadores.getUtilizador(Integer.parseInt(comp[0]));
                            Proprietario pr = (Proprietario) u1; 
                            pr.novaAvaliacao(Double.parseDouble(comp[1]));
                            gestorUtilizadores.atualizaUtilizador(pr);
                        }
                        else {
                            Utilizador u2 = gestorUtilizadores.getUtilizador(Integer.parseInt(comp[0]));
                            Cliente cl = (Cliente) u2;
                            cl.novaAvaliacao(Double.parseDouble(comp[1]));
                            gestorUtilizadores.atualizaUtilizador(cl);
                        }
                    }
                    break;
                    
                default:
                    break;
            }
        }
    }
    
}
