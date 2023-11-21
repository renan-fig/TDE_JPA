package control;

import entities.Aluno;
import entities.Curso;
import repositories.AlunoRepository;
import repositories.CursoRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        boolean flag = false;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
        Scanner scanner = new Scanner(System.in);

        System.out.println("SISTEMA DE GESTÃO UNILSALLE");


        do{
            System.out.println("\nEscolha uma opção: ");
            System.out.println("1 - Cadastrar curso \n2 - Cadastrar aluno\n3 - Adicionar aluno a curso existente\n4 - Exibir dados do aluno\n5 - Exibir alunos de um curso\n6 - Sair" );
            Integer opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1:
                    Curso curso = new Curso();

                    System.out.print("Insira o nome do curso: ");
                    curso.setDescricao(scanner.nextLine());
                    System.out.print("Insira a carga horária do curso: ");
                    curso.setCargaHoraria(scanner.nextInt());

                    CursoRepository cr = new CursoRepository();
                    cr.inserirCurso(emf, curso);

                    break;

                case 2:
                    Aluno aluno = new Aluno();

                    System.out.println("Insira o nome do aluno: ");
                    aluno.setNome(scanner.nextLine());

                    AlunoRepository ar = new AlunoRepository();
                    ar.inserirAluno(emf,aluno);

                    break;

                case 3:
                    Aluno alunoProcurado = new Aluno();
                    Curso cursoProcurado = new Curso();

                    AlunoRepository arMat = new AlunoRepository();
                    CursoRepository crMat = new CursoRepository();

                    System.out.println("LISTA DOS ALUNOS:");
                    arMat.consultarTodosAlunos(emf);
                    System.out.println("Insira a matrícula do aluno que deseja matricular: ");
                    alunoProcurado.setMatricula(scanner.nextLong());
                    alunoProcurado = arMat.consultarAluno(emf, alunoProcurado);

                    System.out.println("LISTA DOS CURSOS:");
                    crMat.consultarTodosCursos(emf);
                    System.out.println("Insira o código do curso: ");
                    cursoProcurado.setCodigo(scanner.nextLong());
                    cursoProcurado = crMat.consultarCurso(emf, cursoProcurado);

                    crMat.matricularAluno(emf, alunoProcurado, cursoProcurado);

                    break;

                case 4:
                    Aluno alunoConsulta = new Aluno();

                    System.out.println("Insira a matrícula do aluno que deseja procurar: ");
                    alunoConsulta.setMatricula(scanner.nextLong());

                    AlunoRepository arP = new AlunoRepository();
                    alunoConsulta = arP.consultarAluno(emf, alunoConsulta);

                    System.out.println(alunoConsulta);
                    break;

                case 5:
                    cursoProcurado = new Curso();

                    System.out.println("Insira o código do curso que deseja procurar: ");
                    cursoProcurado.setCodigo(scanner.nextLong());

                    CursoRepository crP = new CursoRepository();
                    crP.consultaAlunosCurso(emf, cursoProcurado);

                    break;
                case 6:

                    System.out.println("Programa finalizado!");

                    flag = true;
                    break;
            }
        }while(!flag);

        scanner.close();
    }
}

