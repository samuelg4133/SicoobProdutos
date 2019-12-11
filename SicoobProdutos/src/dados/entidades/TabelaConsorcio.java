package dados.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TabelaConsorcio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTabela;
    private String descricaoTabela;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.idTabela);
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
        final TabelaConsorcio other = (TabelaConsorcio) obj;
        return Objects.equals(this.idTabela, other.idTabela);
    }

    public Integer getIdTabela() {
        return idTabela;
    }

    public void setIdTabela(Integer idTabela) {
        this.idTabela = idTabela;
    }

    public String getDescricaoTabela() {
        return descricaoTabela;
    }

    public void setDescricaoTabela(String descricaoTabela) {
        this.descricaoTabela = descricaoTabela;
    }

    @Override
    public String toString() {
        return descricaoTabela;
    }

}
