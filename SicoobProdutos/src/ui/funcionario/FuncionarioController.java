/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.funcionario;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import dados.daos.FuncaoDAO;
import dados.daos.SetorDAO;
import dados.entidades.Funcao;
import dados.entidades.Funcionario;
import dados.entidades.PontoDeAtendimento;
import dados.entidades.Setor;
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
import servicos.FuncionarioServico;
import servicos.PontoDeAtendimentoServico;
import util.AlertaUtil;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class FuncionarioController implements Initializable {

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabPesq;
    @FXML
    private TextField textFieldPesquisa;
    @FXML
    private TableColumn<Funcionario, String> tableColumnNome;
    @FXML
    private Tab tabEdit;
    @FXML
    private JFXTextField textFieldNome;
    @FXML
    private JFXTextField textFieldDoc;
    @FXML
    private JFXRadioButton radioButtonStatus;
    @FXML
    private ChoiceBox<PontoDeAtendimento> choiceBoxPA;
    @FXML
    private ChoiceBox<Funcao> choiceBoxFuncao;
    @FXML
    private ChoiceBox<Setor> choiceBoxSetor;
    @FXML
    private TableView<Funcionario> tabelaFuncionario;
    @FXML
    private TableColumn<Funcionario, String> tableColumnCPF;
    @FXML
    private JFXToggleButton toggleButton;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabela();
        listarSetor();
        listarFuncao();
        listarPontosDeAtendimento();
    }

    private final PontoDeAtendimentoServico PAservico = new PontoDeAtendimentoServico();

    private void listarPontosDeAtendimento() {

        List<PontoDeAtendimento> pa = PAservico.listarAtivos();
        choiceBoxPA.setItems(FXCollections.observableArrayList(pa));
        choiceBoxPA.setTooltip(new Tooltip("Selecione o Ponto de Atendimento"));
    }
    private final FuncaoDAO funcaoDAO = new FuncaoDAO();

    private void listarFuncao() {
        List<Funcao> funcoes = funcaoDAO.listar();
        choiceBoxFuncao.setItems(FXCollections.observableArrayList(funcoes));
        choiceBoxFuncao.setTooltip(new Tooltip("Selecione a Função"));
    }

    private final SetorDAO setorDAO = new SetorDAO();

    private void listarSetor() {
        List<Setor> setores = setorDAO.listar();
        choiceBoxSetor.setItems(FXCollections.observableArrayList(setores));
        choiceBoxSetor.setTooltip(new Tooltip("Selecione o Setor"));
    }
    private ObservableList<Funcionario> dados
            = FXCollections.observableArrayList();

    private final FuncionarioServico funcionarioServico = new FuncionarioServico();

    @FXML
    private void listarFuncionarios(MouseEvent event) {

        dados.clear();
        String nome = textFieldPesquisa.getText();
        List<Funcionario> funcionarios = funcionarioServico.buscarPeloNome(nome);

        dados = FXCollections.observableArrayList(funcionarios);

        tabelaFuncionario.setItems(dados);
    }

    private void configurarTabela() {

        tableColumnNome.setCellValueFactory(
                new PropertyValueFactory("nomeFuncionario"));

        tableColumnCPF.setCellValueFactory(
                new PropertyValueFactory("cpf"));

    }

    @FXML
    private void excluir(MouseEvent event) {
        funcionario = tabelaFuncionario.getSelectionModel().getSelectedItem();
        if (funcionario != null) {
            Optional<ButtonType> btn = AlertaUtil.mensagemDeConfirmacao("Deseja mesmo excluir o registro?", "Excluir");

            if (btn.get() == ButtonType.OK) {

                funcionarioServico.excluir(funcionario);

                AlertaUtil.mensagemSucesso("Registro Excluído com Sucesso");
                selecionarTab(tabEdit);
            }
        } else {
            AlertaUtil.mensagemAtencao("É necessário selecionar um registro válido");
        }
    }

    private Funcionario funcionario;

    @FXML
    private void editar(MouseEvent event) {
        funcionario = tabelaFuncionario.getSelectionModel().getSelectedItem();

        if (funcionario != null) {
            selecionarTab(tabEdit);
            toggleButton.setSelected(true);
            textFieldDoc.setEditable(false);
            textFieldDoc.setText(funcionario.getCpf());
            textFieldNome.setText(funcionario.getNomeFuncionario());
            choiceBoxFuncao.setValue(funcionario.getFuncao());
            choiceBoxSetor.setValue(funcionario.getSetor());
            choiceBoxPA.setValue(funcionario.getPa());
            if (funcionario.getStatusFuncionario() != "ativo") {
                radioButtonStatus.setSelected(false);
            } else {
                radioButtonStatus.setSelected(true);
            }

        } else {
            AlertaUtil.mensagemAtencao("É necessário escolher uma cota!");
        }
    }

    @FXML
    private void salvar(ActionEvent event) {

        if (toggleButton.isSelected()) {
            Optional<ButtonType> btn
                    = AlertaUtil.mensagemDeConfirmacao("Deseja mesmo salvar as alterações?",
                            "EDITAR");

            //Se o botão OK foi pressionado
            if (btn.get() == ButtonType.OK) {
                funcionario.setNomeFuncionario(textFieldNome.getText());
                funcionario.setCpf(textFieldDoc.getText());
                funcionario.setPa(choiceBoxPA.getValue());
                funcionario.setSetor(choiceBoxSetor.getValue());
                funcionario.setFuncao(choiceBoxFuncao.getValue());
                if (radioButtonStatus.isSelected()) {

                    funcionario.setStatusFuncionario("ativo");
                } else {
                    funcionario.setStatusFuncionario("inativo");
                }

                funcionarioServico.editar(funcionario);
                AlertaUtil.mensagemSucesso("Funcionário Editado com Sucesso!");
            }
        } else {
            Funcionario f = new Funcionario();
            f.setNomeFuncionario(textFieldNome.getText());
            f.setCpf(textFieldDoc.getText());
            f.setPa(choiceBoxPA.getValue());
            f.setSetor(choiceBoxSetor.getValue());
            f.setFuncao(choiceBoxFuncao.getValue());
            if (radioButtonStatus.isSelected()) {

                f.setStatusFuncionario("ativo");
            } else {
                f.setStatusFuncionario("inativo");
            }
            funcionarioServico.salvar(f);
            AlertaUtil.mensagemSucesso("Funcionário Incluído com Sucesso!");
        }
        
        limparDados();
    }

    String valor = "";

    private void limparDados() {
        toggleButton.setSelected(false);
        textFieldDoc.setEditable(true);
        textFieldNome.setText(valor);
        textFieldDoc.setText(valor);
        choiceBoxPA.setValue(null);
        choiceBoxFuncao.setValue(null);
        choiceBoxSetor.setValue(null);
        radioButtonStatus.setSelected(true);
    }

    private void selecionarTab(Tab tab) {
        tabPane.getSelectionModel().select(tab);
    }
}
