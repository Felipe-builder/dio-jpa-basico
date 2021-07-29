package test;

import classes.Aluno;
import classes.Estado;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ExecutionPart2 {
    public static void main(String[] args) {

        // OBS: Esse código deve executar SEM ERROS com a implementação JPA que foi definida no "persistence.xml"
        // Se estiver somente com o JPA baixado, o codigo NÃO IRÁ funcionar.

        // O ideal é que nessa parte (Parte 2) o código EXECUTE SEM ERROS, pois aqui terá uma implementação JPA sendo utilizada.

        // 1 - Passos iniciais para criar um gerenciador de entidades com o banco de dados especificado no arquivo "persistence.xml"
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("part2-DIO");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // 2.1 - Criar instâncias para serem adicionadas no banco de dados
        Estado estadoParaAdicionar1 = new Estado("Rio de Janeiro", "RJ");
        Estado estadoParaAdicionar2 = new Estado("São Paulo", "SP");
        Aluno alunoParaAdicionar1 = new Aluno("Daniel", 29, estadoParaAdicionar1);
        Aluno alunoParaAdicionar2 = new Aluno("Maria", 23, estadoParaAdicionar1);
        Aluno alunoParaAdicionar3 = new Aluno("Isac", 52, estadoParaAdicionar2);

        // 2.2 - Iniciar uma transação para adicionar as instância no banco de dados
        entityManager.getTransaction().begin();

        entityManager.persist(estadoParaAdicionar1);
        entityManager.persist(estadoParaAdicionar2);
        entityManager.persist(alunoParaAdicionar1);
        entityManager.persist(alunoParaAdicionar2);
        entityManager.persist(alunoParaAdicionar3);

        entityManager.getTransaction().commit();

        // 3 - Resgatar instâncias no banco de dados
        Estado estadoEncontrado = entityManager.find(Estado.class, 1);
        Aluno alunoEncontrado = entityManager.find(Aluno.class, 1);

        System.out.println(estadoEncontrado);
        System.out.println(alunoEncontrado);

        // 4 - Alterar uma entidade
        entityManager.getTransaction().begin();

        alunoEncontrado.setNome("Karen");
        alunoEncontrado.setIdade(28);

        entityManager.getTransaction().commit();

        // 5 - Remover uma entidade
//        entityManager.getTransaction().begin();
//
//        entityManager.remove(alunoEncontrado);
//
//        entityManager.getTransaction().commit();

        // 6 - Encerrar o gerenciador de entidades e encerrar a fabrica de gerenciadores de entidade.
        entityManager.close();
        entityManagerFactory.close();

    }
}
