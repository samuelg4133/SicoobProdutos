package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    
    private static EntityManagerFactory sicoob
            = Persistence.createEntityManagerFactory("sicoobprodutos");
    
    public static EntityManager getGerenciador(){
        return sicoob.createEntityManager();
    }
    
}
