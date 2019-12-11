package dados.daos;


import dados.entidades.Funcao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import util.JPAUtil;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Samuel
 */
public class FuncaoDAO {

    public List<Funcao> listar() {
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();

        //Criando a consulta ao BD
        TypedQuery consulta = gerenciador.createQuery(
                "Select f from Funcao f order by f.nomeFuncao", Funcao.class);

        return consulta.getResultList();
    }

}
