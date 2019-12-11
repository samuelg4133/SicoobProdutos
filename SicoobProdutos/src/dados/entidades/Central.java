package dados.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity

public class Central implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCentral;
    private String numeroCentral;
    private String nomeCentral;
    private String statusCentral;

    public Central() {
    }

    public Central(String numeroCentral, String nomeCentral, String statusCentral) {
        this.numeroCentral = numeroCentral;
        this.nomeCentral = nomeCentral;
        this.statusCentral = statusCentral;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.idCentral);
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
        final Central other = (Central) obj;
        return Objects.equals(this.idCentral, other.idCentral);
    }

    public Integer getIdCentral() {
        return idCentral;
    }

    public void setIdCentral(Integer idCentral) {
        this.idCentral = idCentral;
    }

    public String getNumeroCentral() {
        return numeroCentral;
    }

    public void setNumeroCentral(String numeroCentral) {
        this.numeroCentral = numeroCentral;
    }

    public String getNomeCentral() {
        return nomeCentral;
    }

    public void setNomeCentral(String nomeCentral) {
        this.nomeCentral = nomeCentral;
    }

    public String getStatusCentral() {
        return statusCentral;
    }

    public void setStatusCentral(String statusCentral) {
        this.statusCentral = statusCentral;
    }
    
    
}
