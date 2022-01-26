package programa;

import java.io.Serializable;
import java.util.ArrayList;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Extends Utilizador.
 * Permite criar um tipo de Utilizador, tendo o objeto a mesma informação que a classe Utilizador, todavia,
 * permite diferenciar o tipo de interface a que aquele utilizador concreto é exposto.
 * É instanciada na classe EcraAddUtilizador sempre o intermediário seleciona a opção 'Programador' da JComboBox
 * e clica em criar.
 */
public class Programador extends Utilizador implements Serializable {

    protected ArrayList<Aplicacao> listaAplicacoes;


    /**
     * Construtor.
     * Permite criar uma nova instância de Utilizador do tipo Programador.
     * Inicializa o Arraylist listaAplicacoes.
     *
     * @param nome     corresponde ao nome do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador.
     * @param morada   corresponde à morada do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador.
     * @param email    corresponde ao e-mail do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador.
     * @param telefone corresponde ao número de telefone do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador.
     * @param password corresponde à password da conta do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador.
     */
    public Programador(String nome, String morada, String email, String telefone, String password) {
        super(nome, morada, email, telefone, password);
        this.listaAplicacoes = new ArrayList<>(10);
    }


    /**
     * Getter do atributo Arraylist listaAplicacoes.
     *
     * @return retorna a lista de aplicações daquele programador.
     */
    public ArrayList<Aplicacao> getListaAplicacoes() {
        return listaAplicacoes;
    }


    /**
     * Adiciona uma nova aplicação à listaAplicacoes daquele programador.
     *
     * @param aplicacao corresponde à aplicação criada no EcraAddApplicacao sempre o Intermediário/Programador clica no botão 'adicionar'.
     */
    public void adicionarApps(Aplicacao aplicacao) {
        if (aplicacao != null) {
            listaAplicacoes.add(aplicacao);
        }
    }
}