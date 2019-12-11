/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.editarconsorciado;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dados.entidades.Cliente;
import dados.entidades.PontoDeAtendimento;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import servicos.ClienteServico;
import servicos.PontoDeAtendimentoServico;
import util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author IFNMG
 */
public class EditarConsorciadoController implements Initializable {

    @FXML
    private ChoiceBox gestao_EditarConsorciado_choiceBox;
    @FXML
    private TableView<Cliente> tableViewConsorciadoPesquisado;
    @FXML
    private TableColumn tableColumnNome;
    @FXML
    private TableColumn tableColumnDoc;
    @FXML
    private TextField textFieldPesquisa;

    private final String nomeC = "Nome/Razão Social";
    private final String docC = "CPF/CNPJ";

    /**
     * Objeto que irá selecionar o cliente da qual será editado
     */
    public static Cliente clienteSelecionado;
    @FXML
    private JFXTextField textFieldNome;
    @FXML
    private JFXTextField textFieldDoc;
    @FXML
    private ChoiceBox<PontoDeAtendimento> checkBoxPA;
    @FXML
    private JFXRadioButton radioButtonStatus;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabPesq;
    @FXML
    private Tab tabEdit;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        gestao_EditarConsorciado_choiceBox.setValue(nomeC);
        gestao_EditarConsorciado_choiceBox.setItems(FXCollections.observableArrayList(nomeC, docC));
        configurarTabela();
        listChoiceBoxPA();
    }

    @FXML
    private void listarConsorciados(MouseEvent event) {

        dados.clear();

        if (gestao_EditarConsorciado_choiceBox.getValue() == nomeC) {
            String nome = textFieldPesquisa.getText();
            List<Cliente> clientes = servico.buscarPeloNome(nome);

            dados = FXCollections.observableArrayList(clientes);

            tableViewConsorciadoPesquisado.setItems(dados);
        } else {
            String documento = textFieldPesquisa.getText();
            List<Cliente> clientes = servico.buscarPeloDocumento(documento);

            dados = FXCollections.observableArrayList(clientes);

            tableViewConsorciadoPesquisado.setItems(dados);
        }
    }

    private void configurarTabela() {

        tableColumnNome.setCellValueFactory(
                new PropertyValueFactory("nomeCliente"));

        tableColumnDoc.setCellValueFactory(
                new PropertyValueFactory("documento"));

    }//configurarTabela

    private ObservableList<Cliente> dados
            = FXCollections.observableArrayList();

    private ClienteServico servico = new ClienteServico();

    @FXML
    private void excluir(MouseEvent event) {
        clienteSelecionado = tableViewConsorciadoPesquisado.getSelectionModel().getSelectedItem();
        if (clienteSelecionado != null) {
            Optional<ButtonType> btn = AlertaUtil.mensagemDeConfirmacao("Deseja mesmo excluir o registro?", "Excluir");

            if (btn.get() == ButtonType.OK) {

                servico.excluir(clienteSelecionado);

                AlertaUtil.mensagemSucesso("Registro Excluído com Sucesso");
                listarConsorciados(event);
            }
        } else {
            AlertaUtil.mensagemAtencao("É necessário selecionar um registro válido");
        }
    }

    private Cliente c;

    @FXML
    private void editar(MouseEvent event) {
        c = tableViewConsorciadoPesquisado.getSelectionModel().getSelectedItem();

        if (c != null) {
            selecionarTab(tabEdit);
            textFieldNome.setText(c.getNomeCliente());
            textFieldDoc.setText(c.getDocumento());
            checkBoxPA.setValue(c.getPa());
            if (c.getStatusCliente() != "ativo") {
                radioButtonStatus.setSelected(false);
            } else {
                radioButtonStatus.setSelected(true);
            }

        } else {
            AlertaUtil.mensagemAtencao("É necessário escolher uma cota!");
        }
    }

    private void selecionarTab(Tab tab) {
        tabPane.getSelectionModel().select(tab);
    }
    private final PontoDeAtendimentoServico pontoDeAtendimentoServico = new PontoDeAtendimentoServico();

    private void listChoiceBoxPA() {
        List<PontoDeAtendimento> pa = pontoDeAtendimentoServico.listarAtivos();
        checkBoxPA.setItems(FXCollections.observableArrayList(pa));
        checkBoxPA.setTooltip(new Tooltip("Selecione o Ponto de Atendimento"));
    }

    @FXML
    private void salvar(ActionEvent event) {
        Optional<ButtonType> btn
                = AlertaUtil.mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                        "EDITAR");

        //Se o botão OK foi pressionado
        if (btn.get() == ButtonType.OK) {
            c.setNomeCliente(textFieldNome.getText());
            c.setDocumento(textFieldDoc.getText());
            c.setPa(checkBoxPA.getValue());
            if (radioButtonStatus.isSelected()) {
                c.setStatusCliente("ativo");
            } else {
                c.setStatusCliente("inativo");
            }

            servico.editar(c);

            AlertaUtil.mensagemSucesso("Consorciado Editado com Sucesso!");

        }

    }

}
