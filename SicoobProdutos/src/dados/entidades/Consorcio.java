package dados.entidades;

import exceptions.ValorInvalidoException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"contrato", "grupo", "cota"})})
public class Consorcio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConsorcio;
    @Column(unique = true)
    private String contrato;
    @Column(unique = true)
    private String grupo;
    @Column(unique = true)
    private String cota;
    private BigDecimal valorOrigem;
    @ManyToOne
    private Consorcio_ModalidadeProduto modalidadeProduto;
    @ManyToOne
    private TabelaConsorcio tabelaConsorcio;
    @ManyToOne
    private Funcionario funcionario;
    @ManyToOne
    private Cliente cliente;
    private double valorComissaoCooperativa;
    private double valorComissaoFuncionario;
    private LocalDate dataVenda;
    private LocalDate dataPgtoComissao;
    private String statusConsorcio;
    @ManyToOne
    private Produto produto;

    public Consorcio(Integer idConsorcio, String contrato, String grupo, String cota, BigDecimal valorOrigem,
            Consorcio_ModalidadeProduto modalidadeProduto, TabelaConsorcio tabelaConsorcio, Funcionario funcionario, Cliente cliente,
            double valorComissaoCooperativa, double valorComissaoFuncionario, LocalDate dataVenda,
            LocalDate dataPgtoComissao, String statusConsorcio, Produto produto) throws ValorInvalidoException {
        this.idConsorcio = idConsorcio;
        this.contrato = contrato;
        this.grupo = grupo;
        this.cota = cota;
        this.valorOrigem = valorOrigem;
        this.modalidadeProduto = modalidadeProduto;
        this.tabelaConsorcio = tabelaConsorcio;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.valorComissaoCooperativa = valorComissaoCooperativa;
        this.valorComissaoFuncionario = valorComissaoFuncionario;
        this.dataVenda = dataVenda;
        this.dataPgtoComissao = dataPgtoComissao;
        this.statusConsorcio = statusConsorcio;
        this.produto = produto;
    }

    public Consorcio(Integer idConsorcio, String contrato, String grupo, String cota, String valorOrigem,
            Consorcio_ModalidadeProduto modalidadeProduto, TabelaConsorcio tabelaConsorcio,
            Funcionario funcionario, Cliente cliente, double valorComissaoCooperativa,
            double valorComissaoFuncionario, LocalDate dataVenda, LocalDate dataPgtoComissao, String statusConsorcio, Produto produto)
            throws ParseException, ValorInvalidoException {
        this.idConsorcio = idConsorcio;
        this.contrato = contrato;
        this.grupo = grupo;
        this.cota = cota;
        setValorOrigem(valorOrigem);
        this.modalidadeProduto = modalidadeProduto;
        this.tabelaConsorcio = tabelaConsorcio;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.valorComissaoCooperativa = valorComissaoCooperativa;
        this.valorComissaoFuncionario = valorComissaoFuncionario;
        this.dataVenda = dataVenda;
        this.dataPgtoComissao = dataPgtoComissao;
        this.statusConsorcio = statusConsorcio;
        this.produto = produto;
    }

    public Consorcio() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.idConsorcio);
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
        final Consorcio other = (Consorcio) obj;
        return Objects.equals(this.idConsorcio, other.idConsorcio);
    }

    public Integer getIdConsorcio() {
        return idConsorcio;
    }

    public void setIdConsorcio(Integer idConsorcio) {
        this.idConsorcio = idConsorcio;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getCota() {
        return cota;
    }

    public String getGrupoCota() {
        return grupo + " - " + cota;
    }

    public void setCota(String cota) {
        this.cota = cota;
    }

    public BigDecimal getValorOrigem() {
        return valorOrigem;
    }

    public void setValorOrigem(BigDecimal valorOrigem) {
        this.valorOrigem = valorOrigem;
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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorComissaoCooperativa() {
        return valorComissaoCooperativa;
    }

    public void setValorComissaoCooperativa(double valorComissaoCooperativa) {
        this.valorComissaoCooperativa = valorComissaoCooperativa;
    }

    public double getValorComissaoFuncionario() {
        return valorComissaoFuncionario;
    }

    public void setValorComissaoFuncionario(double valorComissaoFuncionario) {
        this.valorComissaoFuncionario = valorComissaoFuncionario;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getDataVendaFormatado() {
        DateTimeFormatter formatador = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM);

        String formatado = dataVenda.format(formatador);
        return formatado;
    }

    public String getDataPgtoComissaoFormatado() {
        DateTimeFormatter formatador = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM);

        String formatado = dataPgtoComissao.format(formatador);
        return formatado;
    }

    public LocalDate getDataPgtoComissao() {
        return dataPgtoComissao;
    }

    public void setDataPgtoComissao(LocalDate dataPgtoComissao) {
        this.dataPgtoComissao = dataPgtoComissao;
    }

    public String getStatusConsorcio() {
        return statusConsorcio;
    }

    public void setStatusConsorcio(String statusConsorcio) {
        this.statusConsorcio = statusConsorcio;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getNomeFuncionario() {
        return funcionario.getNomeFuncionario();
    }

    public String getValorOrigemFormatado() {
        DecimalFormat formatador = new DecimalFormat("#0.00");

        return formatador.format(valorOrigem);

    }

    public void setValorOrigem(String s) throws ParseException {
        NumberFormat formatador = NumberFormat.getInstance();
        this.valorOrigem = new BigDecimal(formatador.parse(s).toString());
    }

    public String getNomeCliente() {
        return cliente.getNomeCliente();
    }

    public String getDocumento() {
        return cliente.getDocumento();
    }
}
