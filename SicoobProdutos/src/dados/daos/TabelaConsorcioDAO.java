/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.daos;

import dados.entidades.Cliente;
import dados.entidades.TabelaConsorcio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author Samuel
 */
public class TabelaConsorcioDAO {
     public void salvar(TabelaConsorcio tc){
    
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o cliente
        gerenciador.persist(tc);

        //Commit
        gerenciador.getTransaction().commit();
        
    }
    
    
    
    public void editar(TabelaConsorcio tc) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(tc);
        
        //Commit na transação
        gerenciador.getTransaction().commit();

    }
    
    /**
     * Exclui o cliente do BD
     * @param tc
     */
    public void excluir(TabelaConsorcio tc){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //selecionado na tela
        tc = gerenciador.merge(tc);

        //Mandar sincronizar as alterações 
        gerenciador.remove(tc);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
        
    }

   public List<TabelaConsorcio> listar() {
        EntityManager gerenciador = JPAUtil.getGerenciador();
    
    TypedQuery consulta=gerenciador.createQuery
        ("Select tc from TabelaConsorcio tc", TabelaConsorcio.class);
    
    return consulta.getResultList();
    }
}
