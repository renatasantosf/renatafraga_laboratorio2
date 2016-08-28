/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import model.Medicamento;
import repositorio.RepositorioMedicamentos;
import util.Console;
import view.menu.MedicamentoMenu;

/**
 *
 * @author lhries
 */
public class MedicamentoUI {
    private RepositorioMedicamentos listaMedicamentos;

    public MedicamentoUI(RepositorioMedicamentos lista) {
        this.listaMedicamentos = lista;
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(MedicamentoMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção:");
            switch (opcao) {
                case MedicamentoMenu.OP_CADASTRAR:
                    cadastrarMedicamentos();
                    break;
                case MedicamentoMenu.OP_LISTAR:
                    mostrarMedicamentos();
                    break;
                case MedicamentoMenu.OP_VOLTAR:
                    System.out.println("Retornando ao menu principal..");
                    break;
                default:
                    System.out.println("Opção inválida..");

            }
        } while (opcao != MedicamentoMenu.OP_VOLTAR);
    }

    private void cadastrarMedicamentos() {
        String nome = Console.scanString("Nome: ");
        if (listaMedicamentos.buscarMedicamentoPorNome(nome)!=null) {
            System.out.println("Medicamento já existente no cadastro");
        } else {
            
            String descricao = Console.scanString("Descrição: ");
            listaMedicamentos.addMedicamentos(new Medicamento(nome, descricao));
            System.out.println("Medicamento " + nome + " cadastrado com sucesso!");
        }
    }

    public void mostrarMedicamentos() {
        System.out.println("-----------------------------\n");
        System.out.println(String.format("%-10s", "CÓDIGO") + "\t"
                + String.format("%-20s", "|NOME") + "\t"
                + String.format("%-20s", "|DESCRIÇÃO"));
        for (Medicamento medicamento : listaMedicamentos.getListaMedicamentos()) {
            System.out.println(String.format("%-10s", medicamento.getCodigo()) + "\t"
                    + String.format("%-20s", "|" + medicamento.getNome()) + "\t"
                    + String.format("%-20s", "|" + medicamento.getDescricao()));
        }

    }    
}
