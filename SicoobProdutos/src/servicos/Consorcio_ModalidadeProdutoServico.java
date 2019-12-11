/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dados.daos.Consorcio_ModalidadeProdutoDAO;
import dados.entidades.Consorcio_ModalidadeProduto;
import java.util.List;

/**
 *
 * @author Samuel
 */
public class Consorcio_ModalidadeProdutoServico {

    private Consorcio_ModalidadeProdutoDAO dao = new Consorcio_ModalidadeProdutoDAO();
     public void salvar(Consorcio_ModalidadeProduto cmp) {

        dao.salvar(cmp);
    }

    //Serviço para editar as informações do Cliente
    public void editar(Consorcio_ModalidadeProduto cmp) {

        dao.editar(cmp);

    }

    /**
     * Recebe um cliente para passar para a DAO excluir no BD
     *
     * @param cmp
     */
    public void excluir(Consorcio_ModalidadeProduto cmp) {

        //Mandar para a DAO excluir
        dao.excluir(cmp);
    }

    public List<Consorcio_ModalidadeProduto> listar() {

        //Pedir ao DAO para listar e retornar
        return dao.listar();

    }
}
