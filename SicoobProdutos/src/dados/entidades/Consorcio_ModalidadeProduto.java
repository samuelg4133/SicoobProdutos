package dados.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Consorcio_ModalidadeProduto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduto;
    private String nomeProduto;
    private String siglaProduto;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.idProduto);
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
        final Consorcio_ModalidadeProduto other = (Consorcio_ModalidadeProduto) obj;
        if (!Objects.equals(this.idProduto, other.idProduto)) {
            return false;
        }
        return true;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getSiglaProduto() {
        return siglaProduto;
    }

    public void setSiglaProduto(String siglaProduto) {
        this.siglaProduto = siglaProduto;
    }

    @Override
    public String toString() {
        return siglaProduto + " - "+ nomeProduto ;
    }

}
