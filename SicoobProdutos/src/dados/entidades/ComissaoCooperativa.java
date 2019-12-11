package dados.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ComissaoCooperativa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComissaoCooperativa;
    private double valorComissao;
    private Integer anoComissao;
    @ManyToOne
    private Consorcio_ModalidadeProduto modalidadeProduto;
    @ManyToOne
    private TabelaConsorcio tabelaConsorcio;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.idComissaoCooperativa);
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
        final ComissaoCooperativa other = (ComissaoCooperativa) obj;
        return Objects.equals(this.idComissaoCooperativa, other.idComissaoCooperativa);
    }

    public Integer getIdComissaoCooperativa() {
        return idComissaoCooperativa;
    }

    public void setIdComissaoCooperativa(Integer idComissaoCooperativa) {
        this.idComissaoCooperativa = idComissaoCooperativa;
    }

    public double getValorComissao() {
        return valorComissao;
    }

    public void setValorComissao(double valorComissao) {
        this.valorComissao = valorComissao;
    }

    public Integer getAnoComissao() {
        return anoComissao;
    }

    public void setAnoComissao(Integer anoComissao) {
        this.anoComissao = anoComissao;
    }

    public Consorcio_ModalidadeProduto getModalidadeProduto() {
        return modalidadeProduto;
    }

    public void setModalidadeProduto(Consorcio_ModalidadeProduto modalidadeProduto) {
        this.modalidadeProduto = modalidadeProduto;
    }

    public TabelaConsorcio getTabelaConsorcio() {
        return tabelaConsorcio;
    }

    public void setTabelaConsorcio(TabelaConsorcio tabelaConsorcio) {
        this.tabelaConsorcio = tabelaConsorcio;
    }

}
