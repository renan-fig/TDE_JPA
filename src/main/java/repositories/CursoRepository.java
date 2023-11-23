package repositories;

import entities.Aluno;
import entities.Curso;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

public class CursoRepository {
    public void inserirCurso(EntityManagerFactory emf, Curso curso){

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try{

            transaction.begin();
            em.persist(curso);
            transaction.commit();

        } catch (Exception e){
            throw new RuntimeException("Erro ao inserir o curso: " + curso.getDescricao(), e);
        } finally {
            em.close();
        }
    }

    public void removerCurso(EntityManagerFactory emf, Curso curso){

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();
            em.remove(curso);
            transaction.commit();

        } catch (Exception e){
            throw new RuntimeException("Erro ao remover o curso: " + curso.getDescricao(), e);
        } finally {
            em.close();
        }

    }

    public Curso consultarCurso(EntityManagerFactory emf, Curso curso){

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Curso c1 = new Curso();

        try{

            transaction.begin();
            c1 = em.find(Curso.class, curso.getCodigo());
            transaction.commit();

            return c1;
        } catch (Exception e){
            throw new RuntimeException("Erro ao  consultar o curso: " + curso.getDescricao(), e);
        } finally {
            em.close();
        }
    }

    public void  consultaAlunosCurso(EntityManagerFactory emf, Curso cursoProcurado) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try{

            transaction.begin();
            String jpql = "SELECT a FROM Aluno a JOIN a.curso c WHERE c = :curso ";
            TypedQuery<Aluno> query = em.createQuery(jpql, Aluno.class);
            query.setParameter("curso", cursoProcurado);
            transaction.commit();

            List<Aluno> listaAlunos = query.getResultList();

            if(listaAlunos.isEmpty()){
                System.out.println("Nenhum aluno matriculado no curso!");
            }else{
                System.out.println("LISTA DE ALUNOS:");

                for (Aluno aluno : listaAlunos) {
                    System.out.println(aluno);
                }
            }

        } catch (Exception e){
            throw new RuntimeException("Erro ao consultar os alunos do curso: " + cursoProcurado.getDescricao(), e);
        } finally {
            em.close();
        }

    }

    public void consultarTodosCursos(EntityManagerFactory emf) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Query query = em.createQuery("select c from Curso c");
            List<Curso> resultList = query.getResultList();

            for(Curso x: resultList){
                System.out.println(x);
            }

            transaction.commit();

        } catch (Exception e){
            throw new RuntimeException("Erro ao  consultar o curso todos os cursos", e);
        } finally {
            em.close();
        }

    }

    public void matricularAluno(EntityManagerFactory emf, Aluno alunoProcurado, Curso cursoProcurado) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();

            Curso curso = em.createQuery("SELECT c FROM Curso c LEFT JOIN FETCH c.listaAlunos WHERE c = :curso", Curso.class)
                    .setParameter("curso", cursoProcurado)
                    .getSingleResult();

            curso.getListaAlunos().add(alunoProcurado);
            transaction.commit();

        } catch (Exception e){
            throw new RuntimeException("Erro ao matricular aluno no curso: " + cursoProcurado.getDescricao(), e);
        } finally {
            em.close();
        }
    }
}
