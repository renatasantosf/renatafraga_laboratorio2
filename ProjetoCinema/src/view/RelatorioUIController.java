/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.impl_bd.VendaDAOBD;
import dominio.Venda;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
    
    private List<Venda> listaVenda;
    private ObservableList<Venda> observableListVenda;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       vendaDAOBD = new VendaDAOBD();
       listaVenda = vendaDAOBD.listar();
       
       
       
       cbBusca.setEditable(true);
       cbBusca.getItems().addAll("VENDAS POR FILME","VENDAS POR HORÁRIO",
               "VENDAS POR SALA", "VENDAS POR SESSÃO","FILME QUE ESTEVE EM MAIS SESSÕES",
               "SALA MAIS UTILIZADA");
    } 
    
    @FXML
    private void tratarBuscaDoCombo(ActionEvent event) {
        if(cbBusca.getValue().equals("VENDAS POR FILME")) {
            listViewBusca.getItems().add(vendaDAOBD.vendasPorFilme());
        }
        
    }
    
}
