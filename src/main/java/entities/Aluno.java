package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "aluno")
public class Aluno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;

    @Column(nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "listaAlunos")
    public Set<Curso> curso = new HashSet<>();

    public Aluno(String nome, Long matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public Aluno(){
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public Set<Curso> getCurso() {
        return curso;
    }

    public void setCurso(Set<Curso> curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Aluno: \n" +
                "Matricula = " + matricula +
                "\nNome = '" + nome + '\'';
    }
}
