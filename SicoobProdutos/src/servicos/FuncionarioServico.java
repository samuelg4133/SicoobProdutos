/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dados.daos.FuncionarioDAO;
import dados.dto.ProducaoPorPontoDeAtendimento;
import dados.entidades.Funcionario;
import java.util.List;

/**
 *
 * @author Samuel
 */
public class FuncionarioServico {
    
    private FuncionarioDAO dao = new FuncionarioDAO();

    //Serviço para salvar cliente
    public void salvar(Funcionario f) {
        
        dao.salvar(f);
    }

    //Serviço para editar as informações do Cliente
    public void editar(Funcionario f) {
        
        dao.editar(f);
        
    }

    /**
     * Recebe um cliente para passar para a DAO excluir no BD
     *
     * @param f
     */
    public void excluir(Funcionario f) {

        //Mandar para a DAO excluir
        dao.excluir(f);
    }
    
    public List<Funcionario> listar() {

        //Pedir ao DAO para listar e retornar
        return dao.listar();
        
    }
    
    public List<Funcionario> buscarPeloNome(String nome) {
        return dao.buscarPeloNome(nome);
    }
    
    public List<ProducaoPorPontoDeAtendimento> listarProducaoPorPontoDeAtendimento(){
    
    return dao.listarProducaoPorPontoDeAtendimento();
    }
}
