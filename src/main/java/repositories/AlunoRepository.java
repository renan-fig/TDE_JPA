package repositories;

import entities.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

public class AlunoRepository {

    // Criação de uma 'EntityManagerFactory'. Este é um passo caro, normalmente feito apenas uma vez por aplicação.
    // "exemplo-jpa" é o nome da unidade de persistência definida no arquivo "persistence.xml".
    public void inserirAluno(EntityManagerFactory emf, Aluno aluno){

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();
            em.persist(aluno);
            transaction.commit();

        } catch (Exception e){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao inserir o aluno: " + aluno.getNome(), e);
        } finally {
            em.close();
        }
    }

    public void removerAluno(EntityManagerFactory emf, Aluno aluno){

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try{

            transaction.begin();
            em.remove(aluno);
            transaction.commit();

        } catch (Exception e){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao remover o aluno: " + aluno.getNome(), e);
        } finally {
            em.close();
        }
    }

    public Aluno consultarAluno(EntityManagerFactory emf, Aluno aluno){

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Aluno aluno1 = new Aluno();

        try{

            transaction.begin();
            aluno1 = em.find(Aluno.class, aluno.getMatricula());
            transaction.commit();

            return aluno1;

        } catch (Exception e){
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao consultar o aluno: " + aluno.getNome(), e);
        } finally {
            em.close();
        }
    }

    public void consultarTodosAlunos(EntityManagerFactory emf){

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();
            Query query = em.createQuery("select a from Aluno a");
            List<Aluno> resultList = query.getResultList();

            for(Aluno aluno: resultList){
                System.out.println(aluno);
            }

            transaction.commit();

        } catch (Exception e){
            throw new RuntimeException("Erro ao consultar todos os alunos", e);
        } finally {
            em.close();
        }
    }

}
