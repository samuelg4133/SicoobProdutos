/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dados.daos.TabelaConsorcioDAO;
import dados.entidades.Cliente;
import dados.entidades.TabelaConsorcio;
import java.util.List;

/**
 *
 * @author Samuel
 */
public class TabelaConsorcioServico {

    TabelaConsorcioDAO dao = new TabelaConsorcioDAO();

    public void salvar(TabelaConsorcio c) {

        dao.salvar(c);
    }

    //Serviço para editar as informações do Cliente
    public void editar(TabelaConsorcio c) {

        dao.editar(c);

    }

    /**
     * Recebe um cliente para passar para a DAO excluir no BD
     *
     * @param c
     */
    public void excluir(TabelaConsorcio c) {

        //Mandar para a DAO excluir
        dao.excluir(c);
    }

    public List<TabelaConsorcio> listar() {

        //Pedir ao DAO para listar e retornar
        return dao.listar();

    }

}
