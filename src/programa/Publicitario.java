package programa;

import java.io.Serializable;
import java.util.ArrayList;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Extends Utilizador.
 * Permite criar um tipo de Utilizador, tendo o objeto a mesma informação que a classe Utilizador, todavia,
 * permite diferenciar o tipo de interface a que aquele utilizador concreto é exposto.
 * É instanciada na classe EcraAddUtilizador sempre o intermediário seleciona a opção 'Publicitário' da JComboBox
 * e clica em criar.
 */
public class Publicitario extends Utilizador implements Serializable {
    private static final long serialVersionUID = 1L;

    protected ArrayList<Frase> listaFrases;


    /**
     * Construtor.
     * Permite criar uma nova instância de Utilizador do tipo Publicitario.
     * Inicializa o Arraylist listaFrases.
     *
     * @param nome     corresponde ao nome do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador.
     * @param morada   corresponde à morada do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador.
     * @param email    corresponde ao e-mail do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador.
     * @param telefone corresponde ao número de telefone do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador.
     * @param password corresponde à password da conta do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador.
     */
    public Publicitario(String nome, String morada, String email, String telefone, String password) {
        super(nome, morada, email, telefone, password);
        this.listaFrases = new ArrayList<>(10);
    }


    /**
     * Getter do atributo Arraylist listaFrases.
     *
     * @return retorna a lista de frases daquele publicitário.
     */
    public ArrayList<Frase> getListaFrases() {
        return listaFrases;
    }


    /**
     * Adiciona uma nova frase à listaFrases daquele publicitário.
     *
     * @param frase corresponde à frase criada na classe EcraAddFrase sempre que o Publicitário/Intermediário clica no botão 'adicionar'.
     */
    public void adicionarFrases(Frase frase) {
        if (frase != null) {
            listaFrases.add(frase);
        }
    }
}
