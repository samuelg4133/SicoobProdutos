/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.daos;

import dados.dto.ContagemFuncionario;
import dados.dto.ContagemStatusConsorcio;
import dados.dto.ValorTotalFuncionario;
import dados.entidades.Consorcio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author IFNMG
 */
public class ConsorcioDAO {

    public void salvar(Consorcio c) {

        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Mandar persistir o cliente
        gerenciador.persist(c);

        //Commit
        gerenciador.getTransaction().commit();

    }

    public void editar(Consorcio c) {

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
     *
     * @param c
     */
    public void excluir(Consorcio c) {

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

    public List<ContagemStatusConsorcio> listarStatus() {
        EntityManager gerenciador = JPAUtil.getGerenciador();
        String sql = "SELECT new dados.dto.ContagemStatusConsorcio(c, count(*) as qtd) "
                + "from Consorcio c "
                + "group by c.statusConsorcio order by qtd desc";
        TypedQuery consulta = gerenciador.createQuery(sql,
                ContagemStatusConsorcio.class);

        return consulta.getResultList();
    }

    public List<ContagemFuncionario> listar() {
        EntityManager gerenciador = JPAUtil.getGerenciador();

        TypedQuery consulta = gerenciador.createQuery("SELECT new dados.dto.ContagemFuncionario(count(*) as qtd,f) "
                + "from Consorcio c "
                + "join c.funcionario f group by f.nomeFuncionario order by qtd desc",
                ContagemFuncionario.class);

        return consulta.getResultList();
    }

    public List<Consorcio> listarTudo() {
        EntityManager gerenciador = JPAUtil.getGerenciador();
        String sql = "Select c from Consorcio c order by c.contrato";
        TypedQuery consulta = gerenciador.createQuery(sql, Consorcio.class);

        return consulta.getResultList();

    }

    public List<ValorTotalFuncionario> listarValorTotal() {
        EntityManager gerenciador = JPAUtil.getGerenciador();
        String sql = "SELECT new dados.dto.ValorTotalFuncionario(sum(c.valorOrigem) as total,f) "
                + "from Consorcio c "
                + "join c.funcionario f group by f.nomeFuncionario order by total desc";
        TypedQuery consulta = gerenciador.createQuery(sql, ValorTotalFuncionario.class);

        return consulta.getResultList();
    }

    public List<ContagemFuncionario> buscarFuncionarioQuantidade(String nomeFuncionario) {
        EntityManager gerenciador = JPAUtil.getGerenciador();
        String sql = "SELECT new dados.dto.ContagemFuncionario(count(*) as quantidade,f) "
                + "from Consorcio c join c.funcionario f "
                + "where f.nomeFuncionario like :nomeFuncionario "
                + "group by f.nomeFuncionario order by quantidade desc";

        TypedQuery<ContagemFuncionario> consulta = gerenciador.createQuery(sql, ContagemFuncionario.class);

        consulta.setParameter("nomeFuncionario", "%" + nomeFuncionario + "%");

        return consulta.getResultList();
    }

    public List<ValorTotalFuncionario> buscarFuncionarioValorTotal(String nomeFuncionario) {
        EntityManager gerenciador = JPAUtil.getGerenciador();
        String sql = "SELECT new dados.dto.ValorTotalFuncionario(sum(c.valorOrigem) as total,f) "
                + "from Consorcio c join c.funcionario f "
                + "where f.nomeFuncionario like :nomeFuncionario "
                + "group by f.nomeFuncionario order by total desc";

        TypedQuery<ValorTotalFuncionario> consulta = gerenciador.createQuery(sql, ValorTotalFuncionario.class);

        consulta.setParameter("nomeFuncionario", "%" + nomeFuncionario + "%");

        return consulta.getResultList();
    }

    public List<Consorcio> buscarPeloContrato(String contrato) {

        EntityManager gerenciador = JPAUtil.getGerenciador();

        TypedQuery<Consorcio> consulta = gerenciador.createQuery("Select c from Consorcio c "
                + "where c.contrato = :contrato "
                + "ORDER BY c.contrato",
                Consorcio.class);

        consulta.setParameter("contrato", "%" + contrato + "%");

        return consulta.getResultList();
    }

}
