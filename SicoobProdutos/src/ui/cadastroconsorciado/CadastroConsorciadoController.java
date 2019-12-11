/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastroconsorciado;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dados.entidades.Cliente;
import dados.entidades.PontoDeAtendimento;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import servicos.ClienteServico;
import servicos.PontoDeAtendimentoServico;
import util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class CadastroConsorciadoController implements Initializable {

    @FXML
    private JFXRadioButton radioButtonStatus;
    @FXML
    private ChoiceBox<PontoDeAtendimento> choiceBoxPA;
    @FXML
    private JFXTextField textFieldNome;
    @FXML
    private JFXTextField textFieldDoc;
    @FXML
    private JFXButton buttonSave;

    //Atributo para representar o serviço
    private final PontoDeAtendimentoServico PAservico = new PontoDeAtendimentoServico();

    private final ClienteServico consorciadoServico = new ClienteServico();


    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Marca o radioButtonStatus
        radioButtonStatus.setSelected(true);
        //checkBoxEdition.setSelected(true);
        //Lista os PAs ativos
        listChoiceBoxPA();

    }

    /**
     * Responsável por carregar a lista de Pontos de Atendimentos no Choice Box
     */
    private void listChoiceBoxPA() {
        List<PontoDeAtendimento> pa = PAservico.listarAtivos();
        choiceBoxPA.setItems(FXCollections.observableArrayList(pa));
        choiceBoxPA.setTooltip(new Tooltip("Selecione o Ponto de Atendimento"));
    }

    private void clearField() {
        textFieldNome.setText("");
        textFieldDoc.setText("");
        choiceBoxPA.setValue(null);
        radioButtonStatus.setSelected(true);
        
    }

    @FXML
    private void toSave(ActionEvent event) {

            //Inserir Valores
            //Criando o objeto cliente
            Cliente c = new Cliente();

            //Inserir Nome
            c.setNomeCliente(textFieldNome.getText());
            //Inserir CPF/CNPJ
            c.setDocumento(textFieldDoc.getText());
            //Inserir o Ponto de Atendimento se não estiver vazio
      
               c.setPa(choiceBoxPA.getValue());
          
            //Inserir o Status
            if (radioButtonStatus.isSelected()) {
                c.setStatusCliente("ativo");
            } else {
                c.setStatusCliente("inativo");
            }

            //mandar ator para camada de serviço
            consorciadoServico.salvar(c);

            //Exibir Confirmação de Inclusão
            AlertaUtil.mensagemSucesso("Consorciado Incluído com Sucesso!");
        

        //limpar as entradas anteriores
        clearField();
        
        
    }
}

