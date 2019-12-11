/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.cadastrovenda;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import dados.entidades.Cliente;
import dados.entidades.Consorcio;
import dados.entidades.Consorcio_ModalidadeProduto;
import dados.entidades.Funcionario;
import dados.entidades.TabelaConsorcio;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import servicos.ClienteServico;
import servicos.ConsorcioServico;
import servicos.Consorcio_ModalidadeProdutoServico;
import servicos.FuncionarioServico;
import servicos.TabelaConsorcioServico;
import util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class CadastroDeVendaController implements Initializable {

    private final ConsorcioServico consorcioServico = new ConsorcioServico();

    private ObservableList<Consorcio> dados
            = FXCollections.observableArrayList();
    private Consorcio consorcioSelecionado;
    @FXML
    private JFXTextField grupo;
    @FXML
    private JFXTextField cota;
    @FXML
    private JFXTextField contrato;
    @FXML
    private JFXRadioButton status;
    @FXML
    private ChoiceBox<Cliente> consorciado;
    @FXML
    private ChoiceBox<Consorcio_ModalidadeProduto> modalidade;
    @FXML
    private ChoiceBox<TabelaConsorcio> plano;
    @FXML
    private JFXDatePicker data;
    @FXML
    private JFXTextField valor;
    @FXML
    private ChoiceBox<Funcionario> funcionario;

    @FXML
    private TableView<Consorcio> tabelaConsorcio;
    @FXML
    private TableColumn colunaGrupoCota;
    @FXML
    private TableColumn colunaContrato;
    @FXML
    private TableColumn colunaDoc;
    @FXML
    private TableColumn colunaNome;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabAdc;
    @FXML
    private Tab tabPesq;
    @FXML
    private CheckBox checkBoxEditar;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listarCliente();
        listarModalidade();
        listarFunc();
        listarTabelaConsorcio();
        configurarTabela();
        listarConsorcio();
    }
    private final ClienteServico clienteServico = new ClienteServico();
    private final Consorcio_ModalidadeProdutoServico cmps = new Consorcio_ModalidadeProdutoServico();
    private final FuncionarioServico funcionarioServico = new FuncionarioServico();
    private final TabelaConsorcioServico tabelaConsorcioServico = new TabelaConsorcioServico();

    private void listarCliente() {
        List<Cliente> clientes = clienteServico.listar();
        consorciado.setItems(FXCollections.observableArrayList(clientes));
        consorciado.setTooltip(new Tooltip("Selecione o Consorciado"));
    }

    private void listarModalidade() {
        List<Consorcio_ModalidadeProduto> consorcio_ModalidadeProdutos = this.cmps.listar();
        modalidade.setItems(FXCollections.observableArrayList(consorcio_ModalidadeProdutos));
        modalidade.setTooltip(new Tooltip("Selecione a modalidade"));
    }

    private void listarFunc() {

        List<Funcionario> funcionarios = funcionarioServico.listar();
        funcionario.setItems(FXCollections.observableArrayList(funcionarios));
        funcionario.setTooltip(new Tooltip("Selecione o Angariador"));
    }

    private void listarTabelaConsorcio() {

        List<TabelaConsorcio> tabelaConsorcios = tabelaConsorcioServico.listar();
        plano.setItems(FXCollections.observableArrayList(tabelaConsorcios));
        plano.setTooltip(new Tooltip("Selecione a Tabela"));
    }

    private void limparDados() {
        @SuppressWarnings("LocalVariableHidesMemberVariable")
        String valor = "";
        checkBoxEditar.setSelected(false);
        consorciado.setValue(null);
        contrato.setText(valor);
        cota.setText(valor);
        data.setValue(null);
        funcionario.setValue(null);
        grupo.setText(valor);
        modalidade.setValue(null);
        plano.setValue(null);
        status.setSelected(true);
        this.valor.setText(null);

    }

    @FXML
    private void salvar(ActionEvent event) {
        if (checkBoxEditar.isSelected()) {
            Optional<ButtonType> btn
                    = AlertaUtil.mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                            "EDITAR");

            //Se o botão OK foi pressionado
            if (btn.get() == ButtonType.OK) {
                try {
                    consorcioSelecionado.setCliente(consorciado.getValue());
                    consorcioSelecionado.setContrato(contrato.getText());
                    consorcioSelecionado.setCota(cota.getText());
                    consorcioSelecionado.setDataVenda(data.getValue());
                    consorcioSelecionado.setFuncionario(funcionario.getValue());
                    consorcioSelecionado.setGrupo(grupo.getText());
                    consorcioSelecionado.setModalidadeProduto(modalidade.getValue());
                    consorcioSelecionado.setTabelaConsorcio(plano.getValue());
                    consorcioSelecionado.setValorOrigem(valor.getText());
                    if (status.isSelected()) {
                        consorcioSelecionado.setStatusConsorcio("ativo");
                    } else {
                        consorcioSelecionado.setStatusConsorcio("inativo");
                    }

                    consorcioServico.editar(consorcioSelecionado);

                    AlertaUtil.mensagemSucesso("Consorcio Editado com Sucesso!");

                } catch (ParseException ex) {
                    AlertaUtil.mensagemErro("Problema ao converter valor");
                }
            }
        } else {
            try {

                Consorcio c = new Consorcio();
                c.setCliente(consorciado.getValue());
                c.setContrato(contrato.getText());
                c.setCota(cota.getText());
                c.setDataVenda(data.getValue());
                c.setFuncionario(funcionario.getValue());
                c.setGrupo(grupo.getText());
                c.setModalidadeProduto(modalidade.getValue());
                c.setTabelaConsorcio(plano.getValue());
                c.setValorOrigem(valor.getText());
                if (status.isSelected()) {
                    c.setStatusConsorcio("ativo");
                } else {
                    c.setStatusConsorcio("inativo");
                }

                //mandar ator para camada de serviço
                consorcioServico.salvar(c);

                //Exibir Confirmação de Inclusão
                AlertaUtil.mensagemSucesso("Consorciado Incluído com Sucesso!");
            } catch (ParseException ex) {
                AlertaUtil.mensagemErro("Problema ao converter valor");
            }
        }
        selecionarTab(tabPesq);
        listarConsorcio();
        limparDados();
    }

    //Segunda Tab
    private void configurarTabela() {
        colunaGrupoCota.setCellValueFactory(new PropertyValueFactory("grupoCota"));
        colunaContrato.setCellValueFactory(new PropertyValueFactory("contrato"));
        colunaDoc.setCellValueFactory(new PropertyValueFactory("documento"));
        colunaNome.setCellValueFactory(new PropertyValueFactory("nomeCliente"));
    }

    private void listarConsorcio() {
        // ativarTab(tabPesq, true);
        //selecionarTab(tabPesq);
        dados.clear();
        List<Consorcio> consorcios = consorcioServico.listarTudo();
        dados = FXCollections.observableArrayList(consorcios);

        tabelaConsorcio.setItems(dados);

    }

    private void selecionarTab(Tab tab) {
        tabPane.getSelectionModel().select(tab);
    }

    @FXML
    private void editar(MouseEvent event) {

        consorcioSelecionado = tabelaConsorcio.getSelectionModel().getSelectedItem();

        if (consorcioSelecionado != null) {
            selecionarTab(tabAdc);
            checkBoxEditar.setSelected(true);
            contrato.setText(consorcioSelecionado.getContrato());
            grupo.setText(consorcioSelecionado.getGrupo());
            cota.setText(consorcioSelecionado.getCota());
            consorciado.setValue(consorcioSelecionado.getCliente());
            modalidade.setValue(consorcioSelecionado.getModalidadeProduto());
            plano.setValue(consorcioSelecionado.getTabelaConsorcio());
            valor.setText(consorcioSelecionado.getValorOrigemFormatado());
            data.setValue(consorcioSelecionado.getDataVenda());
            funcionario.setValue(consorcioSelecionado.getFuncionario());
            if (consorcioSelecionado.getStatusConsorcio() == "ativo") {
                status.setSelected(true);
            } else {
                status.setSelected(false);
            }

        } else {
            AlertaUtil.mensagemAtencao("É necessário escolher um registro!");
        }
    }

    @FXML
    private void excluir(MouseEvent event) {
        consorcioSelecionado = tabelaConsorcio.getSelectionModel().getSelectedItem();
        if (consorcioSelecionado != null) {
            Optional<ButtonType> btn = AlertaUtil.mensagemDeConfirmacao("Deseja mesmo excluir o registro?", "Excluir");

            if (btn.get() == ButtonType.OK) {

                consorcioServico.excluir(consorcioSelecionado);

                AlertaUtil.mensagemSucesso("Registro Excluído com Sucesso");
                listarConsorcio();
            }
        } else {
            AlertaUtil.mensagemAtencao("É necessário selecionar um registro válido");
        }
    }

}
