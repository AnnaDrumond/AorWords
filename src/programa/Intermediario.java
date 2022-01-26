package programa;

import java.io.Serializable;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Extends Utilizador.
 * Permite criar um tipo de Utilizador, tendo o objeto a mesma informação que a classe Utilizador, todavia,
 * permite diferenciar o tipo de interface a que aquele utilizador concreto é exposto.
 * É instanciada na classe EcraAddUtilizador sempre o intermediário seleciona a opção 'Intermediário' da JComboBox
 * e clica em criar, ou é instanciado o fundador.
 */
public class Intermediario extends Utilizador implements Serializable {

    /**
     * Construtor.
     * Permite criar uma nova instância de Utilizador do tipo Intermediário.
     *
     * @param nome     corresponde ao nome do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador ou que foi recolhido através da leitura do ficheiro de configuração.
     * @param morada   corresponde à morada do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador ou que foi recolhido através da leitura do ficheiro de configuração.
     * @param email    corresponde ao e-mail do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador ou que foi recolhido através da leitura do ficheiro de configuração.
     * @param telefone corresponde ao número de telefone do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador ou que foi recolhido através da leitura do ficheiro de configuração.
     * @param password corresponde à password da conta do utilizador a ser criado que o intermediário introduziu no EcraAddUtilizador ou que foi recolhido através da leitura do ficheiro de configuração.
     */

    public Intermediario(String nome, String morada, String email, String telefone, String password) {
        super(nome, morada, email, telefone, password);
    }


}
