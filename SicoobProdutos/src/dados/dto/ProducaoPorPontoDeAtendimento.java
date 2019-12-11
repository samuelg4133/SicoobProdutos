/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.dto;

import dados.entidades.Funcionario;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author Samuel
 */
public class ProducaoPorPontoDeAtendimento {

    private Funcionario funcionario;
    private Long contador;
    private BigDecimal valorTotal;

    public ProducaoPorPontoDeAtendimento(Funcionario funcionario, Long contador, BigDecimal valorTotal) {
        this.funcionario = funcionario;
        this.contador = contador;
        this.valorTotal = valorTotal;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Long getContador() {
        return contador;
    }

    public void setContador(Long contador) {
        this.contador = contador;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public String getValorTotalFormatado() {
        DecimalFormat formatador = new DecimalFormat("#0.00");
        return formatador.format(valorTotal);
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getNomePA() {
        return funcionario.getNomePA();
    }

}
