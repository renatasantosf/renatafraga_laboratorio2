/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Consulta;
import model.Medicamento;
import model.ItemReceituario;
import repositorio.RepositorioMedicamentos;
import util.Console;
import view.menu.ReceituarioMenu;

/**
 *
 * @author lhries
 */
public class ReceituarioUI {

    private Consulta consulta;
    private RepositorioMedicamentos listaMedicamentos;

    public ReceituarioUI(Consulta consulta,RepositorioMedicamentos listaMedicamentos) {
        this.consulta = consulta;
        this.listaMedicamentos = listaMedicamentos;
    }

    public void executar() {
        System.out.println("Medicamentos: ");
        int opcao = 0;
        do {
            System.out.println(ReceituarioMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case ReceituarioMenu.OP_ADICIONAR:
                    adicionarReceita();
                    break;

                case ReceituarioMenu.OP_LISTAR:
                    mostrarReceitas();
                    break;

                case ReceituarioMenu.OP_FINALIZAR:
                    System.out.println("Finalizando operações com receituario na consulta..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != ReceituarioMenu.OP_FINALIZAR);
    }

    private void adicionarReceita() {
        System.out.println("Relacione o medicamento abaixo para a consulta: ");
        new MedicamentoUI(listaMedicamentos).mostrarMedicamentos();
        int codigo = Console.scanInt("Escolha o código do medicamento: ");
        Medicamento medicamento = listaMedicamentos.buscarMedicamento(codigo);
        if(medicamento == null || consulta.temMedicamentoNoReceituario(medicamento))
        {
            System.out.println("Medicamento inválido! Repita a operação!");            
        }
        else
        {
            String posologia = Console.scanString("Posologia:");
            consulta.adicionarReceita(new ItemReceituario(medicamento, posologia));
        }
    }


    public void mostrarReceitas() {
        int indice = 1;
        System.out.println(String.format("%-6s", "INDICE") + "\t"
                + String.format("%-20s", "|MEDICAMENTO") + "\t"
                + String.format("%-10s", "|POSOLOGIA"));
        for (ItemReceituario receita : consulta.getReceituario()) {
            System.out.println(String.format("%-6s", (indice++) + "\t"
                    + String.format("%-20s", "|" + receita.getMedicamento().getNome()
                            +"("+receita.getMedicamento().getCodigo()+")") + "\t"
                    + String.format("%-10s", "|" + receita.getPosologia())));
        }
    }

}
