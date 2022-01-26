package programa;

import java.io.Serializable;
import java.time.LocalDate;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Classe que permite criar uma nova instância de MovimentoFinanceiro com a informação do valor da transação e o tipo de movimento realizado.
 * Classe é instanciada sempre que existe alguma transação, permitindo registar os pormenores dos trocas monetárias
 * entre os utilizadores ou o levantamento e/ou depósito na conta.
 */

public class MovimentoFinanceiro implements Serializable {

    protected LocalDate data;
    protected TipoDeMovimento tipoMovimento;
    protected double valor;


    /**
     * Construtor.
     * Permite criar uma nova instância de MovimentoFinanceiro.
     * Obtém a data atual e coloca dentro do atirbuto data.
     *
     * @param tipoMovimento corresponde a um dos tipos contido dentro do enumerador TipoDeMovimento.
     * @param valor         corresponde ao valor que vai ser transacionado.
     */
    public MovimentoFinanceiro(TipoDeMovimento tipoMovimento, double valor) {
        this.data = java.time.LocalDate.now();
        this.tipoMovimento = tipoMovimento;
        this.valor = valor;
    }


    /**
     * Getter do atributo data.
     *
     * @return retorna a data em que o movimento financeiro foi realizado.
     */
    public LocalDate getData() {
        return data;
    }


    /**
     * Getter do atributo tipoMovimento.
     *
     * @return retorna o tipo de movimento financeiro realizado, que se encontra estipulado no enumerador TipoDeMovimento.
     */
    public TipoDeMovimento getTipoMovimento() {
        return tipoMovimento;
    }


    /**
     * Getter do atributo valor.
     *
     * @return retorna o valor que foi transacionado aquando do movimento financeiro.
     */
    public double getValor() {
        return valor;
    }
}
