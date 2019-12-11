
package dados.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PontoDeAtendimento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idPA;
private String numeroPA;
private String nomePA;
private String statusPA;

    @ManyToOne
    private Cidade cidade;
  @ManyToOne
    private Agencia agencia;

    public PontoDeAtendimento() {
    }

    public PontoDeAtendimento(String numeroPA, String nomePA, String statusPA, Cidade cidade, Agencia agencia) {
        this.numeroPA = numeroPA;
        this.nomePA = nomePA;
        this.statusPA = statusPA;
        this.cidade = cidade;
        this.agencia = agencia;
    }
  
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.idPA);
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
        final PontoDeAtendimento other = (PontoDeAtendimento) obj;
        return Objects.equals(this.idPA, other.idPA);
    }

    public Integer getIdPA() {
        return idPA;
    }

    public void setIdPA(Integer idPA) {
        this.idPA = idPA;
    }

    public String getNumeroPA() {
        return numeroPA;
    }

    public void setNumeroPA(String numeroPA) {
        this.numeroPA = numeroPA;
    }

    public String getNomePA() {
        return nomePA;
    }

    public void setNomePA(String nomePA) {
        this.nomePA = nomePA;
    }

    public String getStatusPA() {
        return statusPA;
    }

    public void setStatusPA(String statusPA) {
        this.statusPA = statusPA;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    @Override
    public String toString() {
        return numeroPA + " " + nomePA;
    }

}
