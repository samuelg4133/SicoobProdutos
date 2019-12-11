/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dados.daos.ConsorcioDAO;
import dados.dto.ContagemFuncionario;
import dados.dto.ContagemStatusConsorcio;
import dados.dto.ValorTotalFuncionario;
import dados.entidades.Consorcio;
import java.util.List;

/**
 *
 * @author IFNMG
 */
public class ConsorcioServico {

    private ConsorcioDAO dao = new ConsorcioDAO();

    //Serviço para salvar cliente
    public void salvar(Consorcio c) {

        dao.salvar(c);
    }

    //Serviço para editar as informações do Cliente
    public void editar(Consorcio c) {

        dao.editar(c);

    }

    /**
     * Recebe um cliente para passar para a DAO excluir no BD
     *
     * @param c
     */
    public void excluir(Consorcio c) {

        //Mandar para a DAO excluir
        dao.excluir(c);
    }

    public List<ContagemFuncionario> listar() {

        //Pedir ao DAO para listar e retornar
        return dao.listar();

    }

    public List<ValorTotalFuncionario> listarValorTotal() {

        //Pedir ao DAO para listar e retornar
        return dao.listarValorTotal();

    }

    public List<ContagemFuncionario> buscarFuncionarioQuantidade(String nomeFuncionario) {
        return dao.buscarFuncionarioQuantidade(nomeFuncionario);
    }

    public List<ValorTotalFuncionario> buscarFuncionarioValorTotal(String nomeFuncionario) {
        return dao.buscarFuncionarioValorTotal(nomeFuncionario);
    }

    public List<ContagemStatusConsorcio> listarStatus() {
        return dao.listarStatus();
    }

    public List<Consorcio> buscarPeloContrato(String contrato) {
        return dao.buscarPeloContrato(contrato);
    }

    public List<Consorcio> listarTudo() {
        return dao.listarTudo();

    }

}
