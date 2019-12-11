/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.daos;

import dados.entidades.Consorcio_ModalidadeProduto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author Samuel
 */
public class Consorcio_ModalidadeProdutoDAO {
    
    public void salvar(Consorcio_ModalidadeProduto cmp){
    
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o cliente
        gerenciador.persist(cmp);

        //Commit
        gerenciador.getTransaction().commit();
        
    }
    
    
    
    public void editar(Consorcio_ModalidadeProduto cmp) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(cmp);
        
        //Commit na transação
        gerenciador.getTransaction().commit();

    }
    
    /**
     * Exclui o Consorcio_ModalidadeProduto cmp do BD
     * @param cmp
     */
    public void excluir(Consorcio_ModalidadeProduto cmp){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //selecionado na tela
        cmp = gerenciador.merge(cmp);

        //Mandar sincronizar as alterações 
        gerenciador.remove(cmp);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
        
    }

   public List<Consorcio_ModalidadeProduto> listar() {
        EntityManager gerenciador = JPAUtil.getGerenciador();
    
    TypedQuery consulta=gerenciador.createQuery
        ("Select cmp from Consorcio_ModalidadeProduto cmp", Consorcio_ModalidadeProduto.class);
    
    return consulta.getResultList();
    }
}
