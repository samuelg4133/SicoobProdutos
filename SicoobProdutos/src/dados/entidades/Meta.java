package dados.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Meta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMeta;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Funcionario funcionario;
    private BigDecimal valorMeta;
    private BigDecimal valorBatido;
    private LocalDate dataInicioMeta;
    private LocalDate dataFinalMeta;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idMeta);
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
        final Meta other = (Meta) obj;
        if (!Objects.equals(this.idMeta, other.idMeta)) {
            return false;
        }
        return true;
    }

    public BigDecimal getValorBatido() {
        return valorBatido;
    }

    public void setValorBatido(BigDecimal valorBatido) {
        this.valorBatido = valorBatido;
    }

    public Integer getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(Integer idMeta) {
        this.idMeta = idMeta;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public BigDecimal getValorMeta() {
        return valorMeta;
    }

    public void setValorMeta(BigDecimal valorMeta) {
        this.valorMeta = valorMeta;
    }

    public LocalDate getDataInicioMeta() {
        return dataInicioMeta;
    }

    public void setDataInicioMeta(LocalDate dataInicioMeta) {
        this.dataInicioMeta = dataInicioMeta;
    }

    public LocalDate getDataFinalMeta() {
        return dataFinalMeta;
    }

    public void setDataFinalMeta(LocalDate dataFinalMeta) {
        this.dataFinalMeta = dataFinalMeta;
    }
}
