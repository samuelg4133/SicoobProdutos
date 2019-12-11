package dados.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity

public class Agencia implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAgencia;
    private String numeroAgencia;
    private String razaoSocialAgencia;
    private String nomeFantasia;
    @ManyToOne
    private Central central;
    
    private String statusAgencia;

    public Agencia() {
    }

    public Agencia(String numeroAgencia, String razaoSocialAgencia, String nomeFantasia, Central central, String statusAgencia) {
        this.numeroAgencia = numeroAgencia;
        this.razaoSocialAgencia = razaoSocialAgencia;
        this.nomeFantasia = nomeFantasia;
        this.central = central;
        this.statusAgencia = statusAgencia;
    }
    
    

    public Integer getIdAgencia() {
        return idAgencia;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.idAgencia);
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
        final Agencia other = (Agencia) obj;
        return Objects.equals(this.idAgencia, other.idAgencia);
    }

    public void setIdAgencia(Integer idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getNumeroAgencia() {
        return numeroAgencia;
    }

    public void setNumeroAgencia(String numeroAgencia) {
        this.numeroAgencia = numeroAgencia;
    }

    public String getRazaoSocialAgencia() {
        return razaoSocialAgencia;
    }

    public void setRazaoSocialAgencia(String razaoSocialAgencia) {
        this.razaoSocialAgencia = razaoSocialAgencia;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public Central getCentral() {
        return central;
    }

    public void setCentral(Central central) {
        this.central = central;
    }

    public String getStatusAgencia() {
        return statusAgencia;
    }

    public void setStatusAgencia(String statusAgencia) {
        this.statusAgencia = statusAgencia;
    }
    
    
}
