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
public class ValorTotalFuncionario {

    private BigDecimal valorTotal;
    private Funcionario funcionario;

    public ValorTotalFuncionario(BigDecimal valorTotal, Funcionario funcionario) {
        this.valorTotal = valorTotal;
        this.funcionario = funcionario;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
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

    public String getNomeFuncionario() {
        return funcionario.getNomeFuncionario();
    }
}
