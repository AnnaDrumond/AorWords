package programa;

import java.io.Serializable;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Classe premite criar uma nova instância de Frase com a informação introduzida pelo utilizador na classe EcraAddFrase.
 * Classe é instanciada sempre que o utilizador (Publicitário ou Intermediário) clica no botão 'adicionar' na classe EcraAddFrase.
 */
public class Frase implements Serializable {

    protected String frase;


    /**
     * Construtor.
     * Permite criar uma nova instância de Frase.
     *
     * @param frase corresponde à frase que o utilizador introduziu na classe EcraAddFrase.
     */
    public Frase(String frase) {
        this.frase = frase;
    }


    /**
     * Getter do atributo frase.
     *
     * @return retorna a frase introduzida pelo utilizador.
     */
    public String getFrase() {
        return frase;
    }


    /**
     * Setter do atributo frase.
     *
     * @param frase modifica a frase, permitindo que esta seja atualizada.
     */
    public void setFrase(String frase) {
        this.frase = frase;
    }
}
