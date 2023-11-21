package control;

import entities.Aluno;
import entities.Curso;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteConexao {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        EntityManager em = emf.createEntityManager();

        try {
            // Iniciar uma transação. É necessário para realizar operações de persistência, como salvar um objeto no banco de dados.
            ((EntityManager) em).getTransaction().begin();

            Curso novoCurso = new Curso();
            novoCurso.setDescricao("Sistemas de Informação");

            // Criação de uma nova instância de Livro.
            Aluno novoAluno = new Aluno();
            novoAluno.setNome("Renan Figueiredo");

            // Persiste o objeto Livro no banco de dados. Isso fará com que o JPA insira um novo registro na tabela correspondente.
            em.persist(novoAluno);

            // Commit da transação. Isso confirmará as operações de persistência realizadas durante a transação.
            em.getTransaction().commit();
        } catch (Exception e) {
            // Se houver alguma exceção durante a transação, faz o rollback para evitar um estado inconsistente no banco de dados.
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Fechamento do 'EntityManager'. É importante fechar para liberar os recursos que ele está consumindo.
            em.close();
        }

        // Fechamento da 'EntityManagerFactory'. Uma vez fechado, nenhum 'EntityManager' pode ser criado. Deve ser fechado ao final do programa para liberar recursos.
        emf.close();
    }
}
