/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dados.daos.ClienteDAO;
import dados.entidades.Cliente;
import java.util.List;

/**
 *
 * @author Samuelg4133_00
 */
public class ClienteServico {

    private ClienteDAO dao = new ClienteDAO();

    //Serviço para salvar cliente
    public void salvar(Cliente c) {

        dao.salvar(c);
    }

    //Serviço para editar as informações do Cliente
    public void editar(Cliente c) {

        dao.editar(c);

    }

    /**
     * Recebe um cliente para passar para a DAO excluir no BD
     *
     * @param c
     */
    public void excluir(Cliente c) {

        //Mandar para a DAO excluir
        dao.excluir(c);
    }

    public List<Cliente> listar() {

        //Pedir ao DAO para listar e retornar
        return dao.listar();

    }

    public List<Cliente> buscarPeloDocumento(String documento) {
        return dao.buscarPeloDocumento(documento);
    }

    public List<Cliente> buscarPeloNome(String nome) {
        return dao.buscarPeloNome(nome);
    }
}
