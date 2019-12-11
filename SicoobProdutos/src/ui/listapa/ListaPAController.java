/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.listapa;

import dados.entidades.Cliente;
import dados.entidades.PontoDeAtendimento;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import servicos.PontoDeAtendimentoServico;

/**
 * FXML Controller class
 *
 * @author Samuel
 */
public class ListaPAController implements Initializable {

    @FXML
    private TableView<PontoDeAtendimento> tabelaPA;
    @FXML
    private TableColumn colunaNumero;
    @FXML
    private TableColumn colunaNome;
    @FXML
    private TableColumn colunaStatus;

    PontoDeAtendimentoServico servico = new PontoDeAtendimentoServico();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        configurarTabela();
        listar();
    }

    private void configurarTabela() {

        colunaNumero.setCellValueFactory(
                new PropertyValueFactory("numeroPA"));

        colunaNome.setCellValueFactory(
                new PropertyValueFactory("nomePA"));

        colunaStatus.setCellValueFactory(new PropertyValueFactory("statusPA"));
    }//configurarTabela

    private ObservableList<PontoDeAtendimento> dados
            = FXCollections.observableArrayList();
    private void listar() {

        List<PontoDeAtendimento> pontoDeAtendimentos = servico.listar();

        dados = FXCollections.observableArrayList(pontoDeAtendimentos);

        tabelaPA.setItems(dados);
    }
}
