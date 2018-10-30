package br.com.modulo.configuracao.instituicao.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.envers.Audited;

@Entity
@Audited
@Table(name = "ins_intituicao")
public class Instituicao implements Serializable {

    private static final long serialVersionUID = -7735396802585172567L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ins_id", nullable = false)
    private Integer id;

    @Column(name = "ins_nome_fantasia", scale = 100, nullable = false)
    private String nomeFantasia;

    @Column(name = "ins_endereco", scale = 50, nullable = false)
    private String endereco;

    @Column(name = "ins_telefone", scale = 20, nullable = false)
    private String telefone;

    @Column(name = "ins_email", scale = 30, nullable = false)
    private String email;

//    @OneToMany(mappedBy = "cliente", orphanRemoval = true, cascade = CascadeType.ALL)
//    private List<ContatoRealizado> contatoRealizadoList = new ArrayList<>();

    public static Instituicao criarInstancia() {
        Instituicao cliente = new Instituicao();
        return criarInstancia(cliente);
    }

    public static Instituicao criarInstancia(Instituicao cliente) {
        return cliente;
    }

//    public void adicionar(ContatoRealizado contatoRealizado) {
//        contatoRealizado.setCliente(this);
//        contatoRealizadoList.add(contatoRealizado);
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public List<ContatoRealizado> getContatoRealizadoList() {
//        return contatoRealizadoList;
//    }
//
//    public void setContatoRealizadoList(List<ContatoRealizado> contatoRealizadoList) {
//        this.contatoRealizadoList = contatoRealizadoList;
//    }

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
        final Instituicao other = (Instituicao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
