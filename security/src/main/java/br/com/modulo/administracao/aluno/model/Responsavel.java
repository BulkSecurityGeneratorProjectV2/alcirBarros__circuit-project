package br.com.modulo.administracao.aluno.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tbresponsaveis")
public class Responsavel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tbresponsaveis_id", nullable = false)
    private Integer id;

    @Column(name = "nome", scale = 100, nullable = false)
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tbpessoas_id", unique = true, referencedColumnName = "tbpessoas_id")
    private Pessoa pessoa;

    @Transient
//    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "tbusuarios_id", unique = true, referencedColumnName = "tbusuarios_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResponsavelAluno> responsavelAlunoList = new ArrayList<>();

    public static Responsavel criarInstancia() {
        Responsavel responsavel = new Responsavel();
        return responsavel;
    }

    public static Responsavel criarInstancia(Responsavel responsavel) {
        responsavel = Optional.ofNullable(responsavel).orElse(criarInstancia());
        return responsavel;
    }

    public static Responsavel criarInstancia(Pessoa pessoa) {
        pessoa = Optional.ofNullable(pessoa).orElse(Pessoa.criarInstancia(pessoa));
        Responsavel responsavel = criarInstancia();
        responsavel.setPessoa(pessoa);
        return responsavel;
    }

    public boolean contens(Aluno aluno) {
        Predicate<ResponsavelAluno> predicate = new Predicate<ResponsavelAluno>() {
            @Override
            public boolean test(ResponsavelAluno responsavelAluno) {
                return responsavelAluno.getAluno().getPessoa().getNome().equals(aluno.getPessoa().getNome());
            }
        };
        return responsavelAlunoList.stream().filter(predicate).findAny().isPresent();
    }

    public void adicionarAluno(Aluno aluno) {
        try {
            if (!contens(aluno)) {
                ResponsavelAluno responsavelAluno = new ResponsavelAluno();
                responsavelAluno.setResponsavel(this);
                responsavelAluno.setAluno(aluno);
                responsavelAlunoList.add(responsavelAluno);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String responsavelList(List<ResponsavelAluno> responsavelAlunoList) {
        List<String> stringList = new ArrayList<>();
        for (ResponsavelAluno responsavelAluno : responsavelAlunoList) {
            stringList.add(responsavelAluno.getDisplay());
        }
        return stringList.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ResponsavelAluno> getResponsavelAlunoList() {
        return responsavelAlunoList;
    }

    public void setResponsavelAlunoList(List<ResponsavelAluno> responsavelAlunoList) {
        this.responsavelAlunoList = responsavelAlunoList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Responsavel other = (Responsavel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public String getDisplay() {
        return Responsavel.responsavelList(responsavelAlunoList);
    }
}
