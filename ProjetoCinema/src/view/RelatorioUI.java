
package view;

import repositorio.RepositorioFilme;
import repositorio.RepositorioSala;
import repositorio.RepositorioSessao;
import repositorio.RepositorioVenda;
import util.Console;
import util.DateUtil;
import view.menu.RelatorioMenu;

/**
 *
 * @author Diego Pinto e Renata Fraga
 */
public class RelatorioUI {
    private RepositorioVenda listaVendas;
    private RepositorioSessao listaSessoes;
    private RepositorioFilme listaFilmes;
    private RepositorioSala listaSalas;
    
    
    public RelatorioUI(RepositorioVenda rv, RepositorioSessao rse,
            RepositorioFilme rf, RepositorioSala rs) {
        this.listaVendas = rv;
        this.listaSessoes = rse;
        this.listaFilmes = rf;
        this.listaSalas = rs;
    }
    
      public void iniciar() {
        int opcao = 0;
        while (opcao!= RelatorioMenu.SAIR) {
            System.out.println(RelatorioMenu.getOpcoes());
            opcao = Console.scanInt("Digite a opcao que deseja: ");

            switch(opcao) {
                case RelatorioMenu.OPVENDASPORFILME:
                     vendasPorFilme();
                    break;
                case RelatorioMenu.OPVENDASPORHORARIO:
                     vendasPorHorario();
                    break;
                case RelatorioMenu.OPVENDASPORSALA:
                     vendasPorSala();
                    break;
                case RelatorioMenu.OPFILMEMAISVENDIDO:
                    filmeMaisVendido();
                    break;
                case RelatorioMenu.OPSALAMAISUTILIZADA:
                    System.out.println(salaMaisUtilizada());
                    break;
                case RelatorioMenu.SAIR:
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
      
    private void vendasPorFilme() {
        int cont_ing;
        System.out.println(String.format("%-40s", "FILME") + "\t"
                + String.format("%-10s","INGRESSOS VENDIDOS"));
        for(int i=0;i<listaFilmes.getFilmes().size();i++) {
            cont_ing = 0;
            for(int j=0;j<listaSessoes.getSessoes().size();j++){
                if(listaFilmes.getFilmes().get(i).getTitulo().equals
                    (listaSessoes.getSessoes().get(j).getFilme().getTitulo())) {
                        cont_ing += 50 - listaSessoes.getSessoes().get(j).getQuantidade();
                  
                }
            }
            System.out.println(String.format("%-40s",listaFilmes.getFilmes().get(i).getTitulo() ) + "\t"
                + String.format("%-10s",cont_ing));
        }
    }
    
    private void vendasPorHorario() {
        int cont_ing;
        System.out.println(String.format("%-40s", "HORARIO") + "\t"
                + String.format("%-10s","INGRESSOS VENDIDOS"));
        for(int i=0;i<listaSessoes.getSessoes().size();i++) {
            cont_ing = 0;
            for(int j=0;j<listaVendas.getVendas().size();j++){
                if(listaSessoes.getSessoes().get(i).getHorario().equals
                    (listaVendas.getVendas().get(j).getSessao().getHorario())) {
                        cont_ing++;
                  
                }
            }
            System.out.println(String.format("%-40s",DateUtil.dateHourToString(listaSessoes.getSessoes().get(i).getHorario())) + "\t"
                + String.format("%-10s",cont_ing));
        }
    }
    
    private void vendasPorSala() {
        int vet[] = new int[listaSalas.getSalas().size()];
        System.out.println(String.format("%-20s", "SALA") + "\t"
                + String.format("%-10s","INGRESSOS VENDIDOS"));
         for(int i=0;i<listaSalas.getSalas().size();i++) {
            for(int j=0;j<listaVendas.getVendas().size();j++){
                if(listaSalas.getSalas().get(i).getNumero()==
                    listaVendas.getVendas().get(j).getSessao().getSala().getNumero()) {
                        vet[i]++;
                  
                }
            }
            System.out.println(String.format("%-20s",listaSalas.getSalas().get(i).getNumero()) + "\t"
                + String.format("%-10s",vet[i]));
        }
    }
    
    private void filmeMaisVendido() {
        int menor = 0, indice = 0;
        if(listaVendas.getVendas().isEmpty()) {
            System.out.println("Não há vendas efetuadas.");
        } else {
           
            for(int i=0;i<listaVendas.getVendas().size();i++) {
                if(i==0) {
                    
                        menor = listaVendas.getVendas().get(i).getSessao().getQuantidade();
                        indice = i;
                    
                } else {
                    if(listaVendas.getVendas().get(i).getSessao().getQuantidade()<listaVendas.getVendas().get(i-1).getSessao().getQuantidade()) {
                        menor = listaVendas.getVendas().get(i).getSessao().getQuantidade();
                        indice = i;
                    }
                }
                
            }
        }
        System.out.println(listaVendas.buscarVenda(indice));
    }
    
    private String salaMaisUtilizada() {
        //contabilizar a quantidade de sessões por sala
        int vet[] = new int[listaSalas.getSalas().size()];
        for(int x = 0; x < listaSalas.getSalas().size(); x++) {
            for(int y=0;y<listaSessoes.getSessoes().size();y++) {
                if(listaSalas.getSalas().get(x).getNumero()==
                   listaSessoes.getSessoes().get(y).getSala().getNumero()) {
                      vet[x]++;   
                }
            }   
        }
        //retornar a sala mais utilizada
        int maior = 0, indice=0;
        for(int i = 0; i < vet.length; i++) {
              if(vet[i]>0) {
                  maior = vet[i];
                  indice = i;
              }
              if(vet[i]>maior) {
                  maior = vet[i];
                  indice = i;
              }          
        }
        return "Sala mais lotada: "+listaSalas.getSalas().get(indice).getNumero()+" com "+maior+" sessoes";
    
    }

     
     
}
