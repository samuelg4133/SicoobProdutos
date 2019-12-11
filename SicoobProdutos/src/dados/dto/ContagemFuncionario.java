/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.dto;

import dados.entidades.Funcionario;

/**
 *
 * @author IFNMG
 */
public class ContagemFuncionario {

    private Long contador;
    private Funcionario funcionario;

    public ContagemFuncionario(Long contador, Funcionario funcionario) {
        this.contador = contador;
        this.funcionario = funcionario;
    }

    public Long getContador() {
        return contador;
    }

    public void setContador(Long contador) {
        this.contador = contador;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getNomeFuncionario() {
        return funcionario.getNomeFuncionario();
    }

}
