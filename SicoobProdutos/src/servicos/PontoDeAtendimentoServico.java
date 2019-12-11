/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import dados.daos.PontoDeAtendimentoDAO;
import dados.entidades.PontoDeAtendimento;
import java.util.List;

/**
 *
 * @author IFNMG
 */
public class PontoDeAtendimentoServico {
    private PontoDeAtendimentoDAO dao = new PontoDeAtendimentoDAO();
    
    public List<PontoDeAtendimento> listarAtivos(){
        
        //Qualquer regra de negócio (se aplicável)
        
        //Pedir a DAO para listar e retornar
        return dao.listarAtivos();
        
    }
    
    public List<PontoDeAtendimento> listar(){
    return dao.listar();
    }
}
