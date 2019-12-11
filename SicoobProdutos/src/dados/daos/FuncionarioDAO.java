/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.daos;

import dados.dto.ProducaoPorPontoDeAtendimento;
import dados.entidades.Funcionario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/**
 *
 * @author Samuel
 */
public class FuncionarioDAO {

    public void salvar(Funcionario f) {

        EntityManager gerenciador = JPAUtil.getGerenciador();

        gerenciador.getTransaction().begin();

        gerenciador.persist(f);

        gerenciador.getTransaction().commit();

    }

    public void editar(Funcionario f) {

        EntityManager gerenciador = JPAUtil.getGerenciador();

        gerenciador.getTransaction().begin();

        gerenciador.merge(f);

        gerenciador.getTransaction().commit();
    }

    public void excluir(Funcionario f) {
//Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Iniciar a transação
        gerenciador.getTransaction().begin();

        //Para excluir tem que dar o merge primeiro para 
        //selecionado na tela
        f = gerenciador.merge(f);

        //Mandar sincronizar as alterações 
        gerenciador.remove(f);

        //Commit na transação
        gerenciador.getTransaction().commit();

    }

    public List<Funcionario> listar() {

        EntityManager gerenciador = JPAUtil.getGerenciador();

        TypedQuery consulta = gerenciador
                .createQuery("Select f from Funcionario f "
                        + "where f.statusFuncionario='ativo' "
                        + "order by f.nomeFuncionario",
                        Funcionario.class);

        return consulta.getResultList();

    }

    public List<Funcionario> buscarPeloNome(String nome) {

        EntityManager gerenciador = JPAUtil.getGerenciador();

        TypedQuery<Funcionario> consulta = gerenciador.createQuery("Select f from Funcionario f "
                + "where f.nomeFuncionario like :nome "
                + "ORDER BY f.nomeFuncionario",
                Funcionario.class);

        consulta.setParameter("nome", "%" + nome + "%");

        return consulta.getResultList();
    }

    public List<ProducaoPorPontoDeAtendimento> listarProducaoPorPontoDeAtendimento() {
        EntityManager gerenciador = JPAUtil.getGerenciador();

        String sql = "SELECT new dados.dto.ProducaoPorPontoDeAtendimento"
                + "(f, count(*) as quantidade, sum(c.valorOrigem) as total) "
                + "from Consorcio c "
                + "join c.funcionario f group by f.pa order by total desc";
        TypedQuery consulta = gerenciador.createQuery(sql, ProducaoPorPontoDeAtendimento.class);
        
        return consulta.getResultList();
    }
}
