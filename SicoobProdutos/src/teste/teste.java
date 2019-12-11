package teste;

import dados.entidades.Agencia;
import javax.persistence.*;
import util.JPAUtil;

public class teste {
    
    public static void main(String[] args) {
        
        //Criando um objeto ator
        //Ator a1 = new Ator();
        //a1.setNome("David Gonçalves");
        
        //Ator a2 = new Ator();
        //a2.setNome("Petrônio Augusto");
        
        //Pegando o gerenciador de acesso ao BD
        EntityManager gerenciador = JPAUtil.getGerenciador();
        
        //Iniciar a transação
        Agencia a1=gerenciador.find(Agencia.class, 1);
        
        //Finalizo a transação
        System.out.println("Nome:"+a1.getNomeFantasia());
        
        //Fechar o gerenciador
        gerenciador.close();
        

    }
    
}
