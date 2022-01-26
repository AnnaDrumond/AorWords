package gui;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Classe desenvolvida com o objetivo de validar os dados inseridos pelo utilizador ativo em várias circunstâncias do uso da aplicação.
 */
public class ValidadorGeral {


    /**
     * Valida o email introduzido por um intermediário quando está a criar um novo utilizador.
     * O e-mail introduzido deve ser constituído por letras e/ou números, o @, seguido de letras e/ou números.
     *
     * @param email corresponde ao email introduzido pelo intermediário e a ser associado a um novo utilizador.
     * @return retorna uma variável boolean, em que se possuir o valor 'true' significa que o e-mail introduzido preenche os requisitos para ser considerado um e-mail válido.
     */
    public boolean validarEmail(String email) {
        boolean validacaoOk = true;
        if (email.matches("^(.+)@(.+)$")) {
        } else {
            validacaoOk = false;
        }
        return validacaoOk;
    }


    /**
     * Valida o nome introduzido por um intermediário quando está a criar um novo utilizador.
     * O nome introduzido deve ser constituído por duas palavras, somente com letras, podendo estas palavras possuír traços.
     *
     * @param nome corresponde ao nome intrdduzido pelo intermediário e a ser associado a um novo utilizador.
     * @return retorna uma variável boolean, em que se possuir o valor 'true' significa que o nome introduzido preenche os requisitos para ser considerado um nome válido.
     */
    public boolean validarNome(String nome) {
        boolean validacaoOk = true;
        String[] vetorAuxNome = nome.split(" ");

        if (vetorAuxNome.length == 2 && vetorAuxNome[0].matches("(?i)[a-z]([- ',.a-z]{0,23}[a-z])?") && vetorAuxNome[1].matches("(?i)[a-z]([- ',.a-z]{0,23}[a-z])?")) {
        } else {
            validacaoOk = false;
        }
        return validacaoOk;
    }


    /**
     * Valida o número de telefone introduzido por um intermediário quando está a criar um novo utilizador.
     * O número de telefone introduzido deve ser constituído por 9 dígitos entre 0 e 9.
     *
     * @param telefone corresponde ao número de telefone introduzido pelo intermediário e a ser associado a um novo utilizador.
     * @return retorna uma variável boolean, em que se possuir um valor 'true' significa que o número de telefone introduzido preenche os requisitos para ser considerado um número de telefone válido.
     */
    public boolean validarTelefone(String telefone) {
        boolean validacaoOk = true;
        if (telefone.matches("^[0-9]{9}$")) {
        } else {
            validacaoOk = false;
        }
        return validacaoOk;
    }


    /**
     * Valida o valor unitário cobrado por cada exibição de frases publicitárias introduzido pelo programador ou
     * ou pelo intermediário aquando da criação de uma nova aplicação.
     * * o valor a ser levantado ou depositado na conta corrente correspondente.
     * O valor unitário pode ser um número real ou um número inteiro (para isso, chama o método validarNumExibicoes.
     *
     * @param valor corresponde ao valor unitário introduzido pelo programador ou pelo intermediário e a ser associado a uma nova aplicação ou ao valor a ser depositado ou levantado da conta corrente.
     * @return retorna uma variável boolean, em que se possuir um valor 'true' significa que o valor unitário introduzido preenche os requisitos para ser considerado um valor válido.
     */
    public boolean validarValorUnitario(String valor) {
        boolean validacaoOk = true;

        if (valor.matches("^(?:0|[1-9][0-9]*)\\.[0-9]+$")) {
        } else {
            validacaoOk = validarNumExibicoes(valor);
        }
        return validacaoOk;
    }


    /**
     * Valida o número de vezes que o publicitário prentende que a sua frase seja exibida. Este valor pode ser introduzido pelo programador ou por um intermediário aquando da criação de um novo anúncio.
     * O número de exibições deve ser um número inteiro.
     *
     * @param numExibicoes corresponde ao número de exibições pretendido para aquela frase introduzido por um publicitário ou por um intermediário e a ser associado a um anúncio.
     * @return retorna uma variável boolean, em que se possuir um valor 'true' significa que o número de exibições introduzido preenche os requisitos para ser considerado um valor válido.
     */
    public boolean validarNumExibicoes(String numExibicoes) {
        boolean validacaoOk;

        try {
            Integer.parseInt(numExibicoes);
            validacaoOk = true;
        } catch (Exception e) {
            validacaoOk = false;
        }
        return validacaoOk;
    }
}
