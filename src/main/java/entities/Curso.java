package entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Integer cargaHoraria;

    @ManyToMany
    @JoinTable(
            name="aluno_curso",
            joinColumns = @JoinColumn(name = "matricula"),
            inverseJoinColumns = @JoinColumn(name = "codigo")
    )
    private Set<Aluno> listaAlunos = new HashSet<>();

    public Curso(Long codigo, String descricao, Integer cargaHoraria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.cargaHoraria = cargaHoraria;
    }

    public Curso(){

    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Set<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public void setListaAlunos(Set<Aluno> listaAlunos) {
        this.listaAlunos = listaAlunos;
    }

    @Override
    public String toString() {
        return "Curso: \n" +
                "Codigo=" + codigo +
                "\nDescricao = '" + descricao + '\'' +
                "\nCarga Horaria = " + cargaHoraria + "\n";
    }
}