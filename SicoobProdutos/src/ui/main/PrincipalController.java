/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dados.dto.ContagemFuncionario;
import dados.dto.ContagemStatusConsorcio;
import dados.dto.ProducaoPorPontoDeAtendimento;
import dados.dto.ValorTotalFuncionario;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import servicos.ConsorcioServico;
import servicos.FuncionarioServico;

/**
 *
 * @author Samuel
 */
public class PrincipalController implements Initializable {

    @FXML
    private Label labelValorTotal;
    @FXML
    private Label labelVendasAtivas;
    @FXML
    private Label labelVendasInativas;
    //TABELA RANKING DE VENDAS POR FUNCIONÁRIOS POR QUANTIDADE
    @FXML
    private TableView<ContagemFuncionario> dash_tabela_FuncRankingQtd;
    @FXML
    private TableColumn dash_colunaFunc_FuncRankingQtd;
    @FXML
    private TableColumn dash_colunaQtd_FuncRankingQtd;
    /*TextField e ImageView responsáveis por fazerem comando de pesquisa
    na tabela Raking de Venda Funcionario por Quantidade*/
    @FXML
    private JFXTextField dash_textFieldPesquisar_FuncRankingQtd;

    //TABELA RANKING DE VENDAS POR FUNCIONÁRIOS POR VALOR
    @FXML
    private TableView<ValorTotalFuncionario> dash_tabela_FuncRankingValor;
    @FXML
    private TableColumn dash_colunaFunc_FuncRankingValor;
    @FXML
    private TableColumn dash_colunaQtd_FuncRankingValor;
    /*TextField e ImageView responsáveis por fazerem comando de pesquisa
    na tabela Raking de Venda Funcionario por Valor Total*/
    @FXML
    private JFXTextField dash_textFieldPesquisar_FuncRankingValorTotal;

    //TABELA PRODUÇÃO POR PONTO DE ATENDIMENTO
    @FXML
    private TableView<ProducaoPorPontoDeAtendimento> dash_tabela_ProducaoPorPontoDeAtendimento;
    @FXML
    private TableColumn dash_colunaUnidade_ProducaoPorPontoDeAtendimento;
    @FXML
    private TableColumn dash_colunaQuantidade_ProducaoPorPontoDeAtendimento;
    @FXML
    private TableColumn dash_colunaTotal_ProducaoPorPontoDeAtendimento;

    @FXML
    private JFXButton buttonGestao;
    @FXML
    private JFXButton buttonDashboard;
    @FXML
    private Pane paneGestao;
    @FXML
    private Pane paneDashboard;

    private ConsorcioServico consorcioServico = new ConsorcioServico();

    private FuncionarioServico funcionarioServico = new FuncionarioServico();

    private ObservableList<ContagemFuncionario> dadosContagemFuncionario
            = FXCollections.observableArrayList();

    private ObservableList<ValorTotalFuncionario> dadosTotalFuncionario
            = FXCollections.observableArrayList();

    private ObservableList<ProducaoPorPontoDeAtendimento> dadosProducaoPorPontoDeAtendimento
            = FXCollections.observableArrayList();

    private ObservableList<ContagemStatusConsorcio> dadosContagemStatusConsorcio = FXCollections.observableArrayList();
    @FXML
    private Pane paneConsorciados;
    @FXML
    private Pane gestao_paneOpcoesConsorciados;
    @FXML
    private ImageView gestao_imageAdd;
    @FXML
    private ImageView gestao_imageEditar;

//metodo de inicio
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        gestao_paneOpcoesConsorciados.setVisible(false);
        configurarDash_tabela_FuncRankingQtd();
        listarDash_tabela_FuncRankingQtd();

        configurarDash_tabela_FuncRankingValor();
        listarDash_tabela_FuncRankingValor();

        configurarDash_tabela_ProducaoPorPontoDeAtendimento();
        listarDash_tabela_ProducaoPorPontoDeAtendimento();
    }

    private void configurarDash_tabela_FuncRankingQtd() {

        dash_colunaFunc_FuncRankingQtd.setCellValueFactory(
                new PropertyValueFactory("nomeFuncionario"));
        dash_colunaQtd_FuncRankingQtd.setCellValueFactory(
                new PropertyValueFactory("contador"));

    }//configurarTabela

    private void listarDash_tabela_FuncRankingQtd() {
        //Limpando quaisquer dados anteriores
        dadosContagemFuncionario.clear();

        //Solicitando a camada de servico a lista de atores
        List<ContagemFuncionario> contagemFuncionario = consorcioServico.listar();

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dadosContagemFuncionario = FXCollections.observableArrayList(contagemFuncionario);

        //Jogando os dados na tabela
        dash_tabela_FuncRankingQtd.setItems(dadosContagemFuncionario);

    }

    private void configurarDash_tabela_FuncRankingValor() {

        dash_colunaFunc_FuncRankingValor.setCellValueFactory(
                new PropertyValueFactory("nomeFuncionario"));
        dash_colunaQtd_FuncRankingValor.setCellValueFactory(
                new PropertyValueFactory("valorTotalFormatado"));

    }//configurarTabela

    private void listarDash_tabela_FuncRankingValor() {
        //Limpando quaisquer dados anteriores
        dadosTotalFuncionario.clear();

        //Solicitando a camada de servico a lista de atores
        List<ValorTotalFuncionario> valorFuncionario = consorcioServico.listarValorTotal();

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dadosTotalFuncionario = FXCollections.observableArrayList(valorFuncionario);

        //Jogando os dados na tabela
        dash_tabela_FuncRankingValor.setItems(dadosTotalFuncionario);

    }

    @FXML
    private void abrirPane(ActionEvent event) {
        if (event.getSource() == buttonDashboard) {
            paneDashboard.toFront();
            listarDash_tabela_FuncRankingQtd();
            listarDash_tabela_FuncRankingValor();
            listarDash_tabela_ProducaoPorPontoDeAtendimento();
        } else if (event.getSource() == buttonGestao) {
            paneGestao.toFront();
        }
    }

    @FXML
    private void dash_Pesquisar_FuncRankingQtd(MouseEvent event) {
        dadosContagemFuncionario.clear();

        String nome = dash_textFieldPesquisar_FuncRankingQtd.getText();

        List<ContagemFuncionario> contagemFuncionario = consorcioServico.buscarFuncionarioQuantidade(nome);

        dadosContagemFuncionario = FXCollections.observableArrayList(contagemFuncionario);

        //Jogando os dados na tabela
        dash_tabela_FuncRankingQtd.setItems(dadosContagemFuncionario);

    }

    @FXML
    private void dash_Pesquisar_FuncRankingValorTotal(MouseEvent event) {
        dadosTotalFuncionario.clear();

        String nome = dash_textFieldPesquisar_FuncRankingValorTotal.getText();

        List<ValorTotalFuncionario> totalFuncionario = consorcioServico.buscarFuncionarioValorTotal(nome);

        dadosTotalFuncionario = FXCollections.observableArrayList(totalFuncionario);

        dash_tabela_FuncRankingValor.setItems(dadosTotalFuncionario);

    }

    private void configurarDash_tabela_ProducaoPorPontoDeAtendimento() {

        dash_colunaUnidade_ProducaoPorPontoDeAtendimento.setCellValueFactory(
                new PropertyValueFactory("nomePA"));

        dash_colunaQuantidade_ProducaoPorPontoDeAtendimento.setCellValueFactory(
                new PropertyValueFactory("contador"));

        dash_colunaTotal_ProducaoPorPontoDeAtendimento.setCellValueFactory(
                new PropertyValueFactory("valorTotalFormatado"));

    }//configurarTabela

    private void listarDash_tabela_ProducaoPorPontoDeAtendimento() {
        //Limpando quaisquer dados anteriores
        dadosProducaoPorPontoDeAtendimento.clear();

        //Solicitando a camada de servico a lista de atores
        List<ProducaoPorPontoDeAtendimento> producaoPorPontoDeAtendimentos = funcionarioServico.listarProducaoPorPontoDeAtendimento();

        //Transformar a lista de atores no formato que a tabela
        //do JavaFX aceita
        dadosProducaoPorPontoDeAtendimento = FXCollections.observableArrayList(producaoPorPontoDeAtendimentos);

        //Jogando os dados na tabela
        dash_tabela_ProducaoPorPontoDeAtendimento.setItems(dadosProducaoPorPontoDeAtendimento);

    }

    @FXML
    private void abrirJanelaConsorciados(MouseEvent event) throws IOException {

        //Código para abrir uma nova Janela
        //Ler o FXML que representa a nova janela
        //(adicionar o throws)
        Parent root = FXMLLoader.load(getClass()
                .getResource("/ui/cadastroconsorciado/cadastroConsorciado.fxml"));
        //Criando a cena
        Scene scene = new Scene(root);
        //Criando a janela (STAGE) 
        Stage stage = new Stage(StageStyle.UTILITY);
        //Titulo na janela
        stage.setTitle("Cadastro de Consorciado");
        //Adicionando a cena na janela
        stage.setScene(scene);

        //Para impedir que a janela seja redimensionada
        //isso faz a janela ficar igual como está no 
        //Secene Builder
        stage.setResizable(false);

        //Configurando o MODALITY
        //Diz respeito ao comportamento das janelas anteriores
        //quando essa for mostrada
        //Para bloquear interação com as janelas anteriores
        stage.initModality(Modality.APPLICATION_MODAL);

        //Mostrando a nova janela
        stage.show();

    }

    @FXML
    private void mostrarOpcoes(MouseEvent event) {
        if (gestao_paneOpcoesConsorciados.isVisible()) {
            gestao_paneOpcoesConsorciados.setVisible(false);
        } else {
            gestao_paneOpcoesConsorciados.setVisible(true);
        }

    }

    @FXML
    private void abrirJanelaEditarConsorciados(MouseEvent event) throws IOException {   //Código para abrir uma nova Janela
        //Ler o FXML que representa a nova janela
        //(adicionar o throws)
        Parent root = FXMLLoader.load(getClass()
                .getResource("/ui/editarconsorciado/EditarConsorciado.fxml"));
        //Criando a cena
        Scene scene = new Scene(root);
        //Criando a janela (STAGE) 
        Stage stage = new Stage(StageStyle.UTILITY);
        //Titulo na janela
        stage.setTitle("Pesquisar Consorciado");
        //Adicionando a cena na janela
        stage.setScene(scene);

        //Para impedir que a janela seja redimensionada
        //isso faz a janela ficar igual como está no 
        //Secene Builder
        stage.setResizable(false);

        //Configurando o MODALITY
        //Diz respeito ao comportamento das janelas anteriores
        //quando essa for mostrada
        //Para bloquear interação com as janelas anteriores
        stage.initModality(Modality.APPLICATION_MODAL);

        //Mostrando a nova janela
        stage.show();
    }

    @FXML
    private void abrirJanelaVendas(MouseEvent event) throws IOException {
        //(adicionar o throws)
        Parent root = FXMLLoader.load(getClass()
                .getResource("/ui/cadastrovenda/cadastroDeVenda.fxml"));
        //Criando a cena
        Scene scene = new Scene(root);
        //Criando a janela (STAGE) 
        Stage stage = new Stage(StageStyle.UTILITY);
        //Titulo na janela
        stage.setTitle("Cadastro de Vendas");
        //Adicionando a cena na janela
        stage.setScene(scene);

        //Para impedir que a janela seja redimensionada
        //isso faz a janela ficar igual como está no 
        //Secene Builder
        stage.setResizable(false);

        //Configurando o MODALITY
        //Diz respeito ao comportamento das janelas anteriores
        //quando essa for mostrada
        //Para bloquear interação com as janelas anteriores
        stage.initModality(Modality.APPLICATION_MODAL);

        //Mostrando a nova janela
        stage.show();
    }

    @FXML
    private void abrirJanelaListaPA(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass()
                .getResource("/ui/listapa/listaPA.fxml"));
        //Criando a cena
        Scene scene = new Scene(root);
        //Criando a janela (STAGE) 
        Stage stage = new Stage(StageStyle.UTILITY);
        //Titulo na janela
        stage.setTitle("Pontos de Atendimento");
        //Adicionando a cena na janela
        stage.setScene(scene);

        //Para impedir que a janela seja redimensionada
        //isso faz a janela ficar igual como está no 
        //Secene Builder
        stage.setResizable(false);

        //Configurando o MODALITY
        //Diz respeito ao comportamento das janelas anteriores
        //quando essa for mostrada
        //Para bloquear interação com as janelas anteriores
        stage.initModality(Modality.APPLICATION_MODAL);

        //Mostrando a nova janela
        stage.show();
    }

    @FXML
    private void abrirJanelaFuncionario(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass()
                .getResource("/ui/funcionario/Funcionario.fxml"));
        //Criando a cena
        Scene scene = new Scene(root);
        //Criando a janela (STAGE) 
        Stage stage = new Stage(StageStyle.UTILITY);
        //Titulo na janela
        stage.setTitle("Funcionários");
        //Adicionando a cena na janela
        stage.setScene(scene);

        //Para impedir que a janela seja redimensionada
        //isso faz a janela ficar igual como está no 
        //Secene Builder
        stage.setResizable(false);

        //Configurando o MODALITY
        //Diz respeito ao comportamento das janelas anteriores
        //quando essa for mostrada
        //Para bloquear interação com as janelas anteriores
        stage.initModality(Modality.APPLICATION_MODAL);

        //Mostrando a nova janela
        stage.show();
    }

}
