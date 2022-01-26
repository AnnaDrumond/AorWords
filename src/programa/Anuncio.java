package programa;

import java.io.Serializable;
import java.time.LocalDate;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Classe que permite criar uma nova instância de Anuncio com as informações necessárias.
 * É instanciada no ecraAddAnuncio quando o utilizador confirma que pretende estabelecer contrato na popUp.
 */

public class Anuncio implements Serializable {

    protected double valorAnuncio;
    protected double receitaProgramador;
    protected double receitaEmpresa;
    protected double valorUnitario;
    protected LocalDate dataContrato;
    protected int numExibicoes;
    protected Publicitario anunciante;
    protected Aplicacao aplicacao;
    protected Frase fraseAnunciada;
    protected Programador anunciador;

    /**
     * Construtor.
     * Permite criar uma nova instância de Anuncio tendo em conta a informação.
     * Obtém a data atual e coloca dentro do atributo dataContrato.
     * Guarda o valor unitário naquele momento da aplicação que vai receber o anúncio em valorUnitario.
     *
     * @param numExibicoes   número de exibições desejado para aquela frase, introduzido pelo utilizador na classe EcraAddAnuncio.
     * @param valorAnuncio   custo total do anúncio a pagar pelo publicitário.
     * @param aplicacao      instância de Aplicacao, corresponde à aplicação que vai publicitar a frase.
     * @param fraseAnunciada instância de Frase, corresponde à frase que vai ser publicitada.
     * @param anunciante     instância de Publicitario, corresponde ao utilizador que vai publicitar a frase.
     * @param anunciador     instância de Programador, corresponde ao programador que vai publicitar a frase na sua aplicação.
     */

    public Anuncio(int numExibicoes, double valorAnuncio, Aplicacao aplicacao, Frase fraseAnunciada, Publicitario anunciante, Programador anunciador) {
        this.numExibicoes = numExibicoes;
        this.valorAnuncio = valorAnuncio;
        this.aplicacao = aplicacao;
        this.anunciante = anunciante;
        this.anunciador = anunciador;
        this.fraseAnunciada = fraseAnunciada;
        this.dataContrato = java.time.LocalDate.now();
        this.valorUnitario = aplicacao.getValorUnitarioPorExibicao();
    }


    /**
     * Getter do atributo valorAnuncio.
     *
     * @return retorna o custo total do anúncio.
     */
    public double getValorAnuncio() {
        return valorAnuncio;
    }


    /**
     * Getter do atributo receitaProgramador.
     *
     * @return retorna a receita que o programador obteve com o anúncio.
     */
    public double getReceitaProgramador() {
        return receitaProgramador;
    }


    /**
     * Setter do atributo receitaProgramador.
     *
     * @param receitaProgramador modifica a receita que o programador obteve com o anúncio.
     */
    public void setReceitaProgramador(double receitaProgramador) {
        this.receitaProgramador = receitaProgramador;
    }


    /**
     * Setter do atributo receitaEmpresa.
     *
     * @param receitaEmpresa modifica a receita que a empresa obteve com o anúncio.
     */
    public void setReceitaEmpresa(double receitaEmpresa) {
        this.receitaEmpresa = receitaEmpresa;
    }


    /**
     * Getter do atributo dataContrato.
     *
     * @return retorna a data em que o contrato foi estabelecido.
     */
    public LocalDate getDataContrato() {
        return dataContrato;
    }


    /**
     * Getter do atributo numExibicoes.
     *
     * @return retorna o número de vezes contratadas pelo publicitário para a frase ser publicitada.
     */
    public int getNumExibicoes() {
        return numExibicoes;
    }


    /**
     * Getter do atributo anunciante.
     *
     * @return retorna a instância de Publicitario que corresponde ao utilizador que criou o anúncio.
     */
    public Publicitario getAnunciante() {
        return anunciante;
    }


    /**
     * Getter do atributo aplicacao.
     *
     * @return retorna a instância de Aplicacao que corresponde à aplicação em que a frase vai ser publicitada.
     */
    public Aplicacao getAplicacao() {
        return aplicacao;
    }


    /**
     * Getter do atributo fraseAnunciada.
     *
     * @return retorna a instância de Frase que corresponde à frase que vai ser publicitada.
     */
    public Frase getFraseAnunciada() {
        return fraseAnunciada;
    }


    /**
     * Getter do atributo anunciador.
     *
     * @return retorna a instância de Programador que corresponde ao utilizador que praticar o anúncio na sua aplicação.
     */
    public Programador getAnunciador() {
        return anunciador;
    }


    /**
     * Getter do atributo valorUnitario.
     *
     * @return retorna o valor unitário, aquando do estabelecimento do contrato, para anunciar a frase uma vez naquela aplicação.
     */
    public double getValorUnitario() {
        return valorUnitario;
    }
}
