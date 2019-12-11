/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.daos;

import dados.entidades.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author Samuelg4133_00
 */
public class ClienteDAO {
    
    public void salvar(Cliente c){
    
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o cliente
        gerenciador.persist(c);

        //Commit
        gerenciador.getTransaction().commit();
        
    }
    
    
    
    public void editar(Cliente c) {

        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar sincronizar as alterações 
        gerenciador.merge(c);
        
        //Commit na transação
        gerenciador.getTransaction().commit();

    }
    
    /**
     * Exclui o cliente do BD
     * @param c
     */
    public void excluir(Cliente c){
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        gerenciador.getTransaction().begin();
        
        //Para excluir tem que dar o merge primeiro para 
        //selecionado na tela
        c = gerenciador.merge(c);

        //Mandar sincronizar as alterações 
        gerenciador.remove(c);
        
        //Commit na transação
        gerenciador.getTransaction().commit();
        
    }

   public List<Cliente> listar() {
        EntityManager gerenciador = JPAUtil.getGerenciador();
    
    TypedQuery consulta=gerenciador.createQuery
        ("Select c from Cliente c order by c.nomeCliente", Cliente.class);
    
    return consulta.getResultList();
    }
   public List<Cliente> buscarPeloNome(String nome) {

        EntityManager gerenciador = JPAUtil.getGerenciador();

        TypedQuery<Cliente> consulta = gerenciador.createQuery("Select c from Cliente c "
                + "where c.nomeCliente like :nome "
                + "ORDER BY c.nomeCliente",
                Cliente.class);

        consulta.setParameter("nome", "%" + nome + "%");

        return consulta.getResultList();
    }
   
   public List<Cliente> buscarPeloDocumento(String documento) {

        EntityManager gerenciador = JPAUtil.getGerenciador();

        TypedQuery<Cliente> consulta = gerenciador.createQuery("Select c from Cliente c "
                + "where c.documento = :documento "
                + "ORDER BY c.nomeCliente",
                Cliente.class);

        consulta.setParameter("documento",documento);

        return consulta.getResultList();
    }
   
}
