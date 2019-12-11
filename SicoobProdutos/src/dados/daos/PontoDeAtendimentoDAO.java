/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.daos;

import dados.entidades.PontoDeAtendimento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author IFNMG
 */
public class PontoDeAtendimentoDAO {
    public List<PontoDeAtendimento> listarAtivos() {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select p from PontoDeAtendimento p where p.statusPA='ativo'", PontoDeAtendimento.class);

        //Retornar a lista de atores
        return consulta.getResultList();

    }
    
    public List<PontoDeAtendimento> listar(){
    //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select p from PontoDeAtendimento p", PontoDeAtendimento.class);

        //Retornar a lista de atores
        return consulta.getResultList();
    }
    
}
