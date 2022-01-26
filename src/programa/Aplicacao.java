package programa;

import java.io.Serializable;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Classe que permite criar uma nova instância de Aplicacao com as informações necessárias.
 * É instanciada no ecraAddAplicacao quando o utilizador clica no botão 'adicionar' da mesma classe.
 */
public class Aplicacao implements Serializable {

    protected String nome;
    protected String descricao;
    protected double valorUnitarioPorExibicao;


    /**
     * Construtor.
     * Permite criar uma nova instância de Aplicacao tendo em conta a informação.
     *
     * @param nome                     nome que a aplicação criada possui.
     * @param descricao                breve texto a apresentar e descrever de que se trata aquela aplicação.
     * @param valorUnitarioPorExibicao custo por cada exibição.
     */
    public Aplicacao(String nome, String descricao, double valorUnitarioPorExibicao) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorUnitarioPorExibicao = valorUnitarioPorExibicao;
    }


    /**
     * Getter do atributo nome.
     *
     * @return retorna o nome escolhido para aquela aplicação.
     */
    public String getNome() {
        return nome;
    }


    /**
     * Setter do atributo nome.
     *
     * @param nome modifica o nome da aplicação.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }


    /**
     * Getter do atributo descricao.
     *
     * @return retorna o texto de apresentação e descrição daquela aplicação.
     */
    public String getDescricao() {
        return descricao;
    }


    /**
     * Setter do atributo descricao.
     *
     * @param descricao modifica o texto de descrição daquela aplicação.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    /**
     * Getter do atributo valorUnitarioPorExibicao.
     *
     * @return retorna o valor por cada publicitação efetuada naquela aplicação.
     */
    public double getValorUnitarioPorExibicao() {
        return valorUnitarioPorExibicao;
    }


    /**
     * Setter do atributo valorUnitarioPorExibicao.
     *
     * @param valorUnitarioPorExibicao modifica o valor por para publicitação efetuada naquela aplicação.
     */
    public void setValorUnitarioPorExibicao(double valorUnitarioPorExibicao) {
        this.valorUnitarioPorExibicao = valorUnitarioPorExibicao;
    }
}