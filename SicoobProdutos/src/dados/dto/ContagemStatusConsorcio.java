/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.dto;

import dados.entidades.Consorcio;

/**
 *
 * @author Samuel
 */
public class ContagemStatusConsorcio {

    public Consorcio consorcio;
    public Long contador;

    public ContagemStatusConsorcio(Consorcio consorcio,Long contador) {
        this.consorcio = consorcio;
        this.contador = contador;
    }

    public Long getContador() {
        return contador;
    }

    public void setContador(Long contador) {
        this.contador = contador;
    }

    public Consorcio getConsorcio() {
        return consorcio;
    }

    public void setConsorcio(Consorcio consorcio) {
        this.consorcio = consorcio;
    }

    public String getStatus() {

        return consorcio.getStatusConsorcio();
    }
}
