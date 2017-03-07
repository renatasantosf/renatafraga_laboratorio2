/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.fraga.renata.view;

import br.fraga.renata.DAO.impl_bd.VendaDAOBD;
import br.fraga.renata.dominio.Venda;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Renata-Samsung
 */
public class RelatorioUIController implements Initializable {

    @FXML
    private AnchorPane painelRelatorio;
    
    @FXML
    private ComboBox<String> cbBusca;
    
    @FXML
    private Button btBusca;
    
    @FXML
    private Button btGrafico;
    
    
    @FXML
    private ListView<String> listViewBusca;
    
    
    private VendaDAOBD vendaDAOBD;
    
    private List<String> listaVenda;
    private ObservableList<String> observableListVenda;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       vendaDAOBD = new VendaDAOBD();
                    
       cbBusca.setEditable(true);
       cbBusca.getItems().addAll("VENDAS POR FILME","VENDAS POR HORÁRIO",
               "VENDAS POR SALA", "VENDAS POR SESSÃO","FILME QUE ESTEVE EM MAIS SESSÕES");
    } 
    
       
    @FXML
    private void tratarBuscaDoCombo(ActionEvent event) {
        if(cbBusca.getValue().equals("VENDAS POR FILME")) {
            listaVenda = vendaDAOBD.vendasPorFilmes();
            observableListVenda = FXCollections.observableArrayList(listaVenda);
            listViewBusca.setItems(observableListVenda);
            
        } else {
            if(cbBusca.getValue().equals("VENDAS POR HORÁRIO")) {
                listaVenda = vendaDAOBD.vendasPorHorario();
                observableListVenda = FXCollections.observableArrayList(listaVenda);
                listViewBusca.setItems(observableListVenda);
            } else {
                if(cbBusca.getValue().equals("VENDAS POR SALA")) {
                    listaVenda = vendaDAOBD.vendasSalas();
                    observableListVenda = FXCollections.observableArrayList(listaVenda);
                    listViewBusca.setItems(observableListVenda);
                } else {
                    if(cbBusca.getValue().equals("VENDAS POR SESSÃO")) {
                        listaVenda = vendaDAOBD.vendasPorSessoes();
                        observableListVenda = FXCollections.observableArrayList(listaVenda);
                        listViewBusca.setItems(observableListVenda);
                        
                    } else {
                        if(cbBusca.getValue().equals("FILME QUE ESTEVE EM MAIS SESSÕES")) {
                            listViewBusca.getItems().add(vendaDAOBD.filmeMaisSessoes());                        
                        } 
                    }
                }
            }
    }
    
}


}

