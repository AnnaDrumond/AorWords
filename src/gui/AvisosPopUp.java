package gui;

import programa.EmpresaAorWords;

import javax.swing.*;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Implementa classe que permite mostrar ao utilizador as pop-ups de aviso e de informação
 */
public class AvisosPopUp {

    protected EmpresaAorWords empresaObjeto;
    protected TrocaDeIdioma trocaDeIdiomaObjeto;


    /**
     * Construtor.
     *
     * @param empresaObjeto       instância de EmpresaAorWords criada no método main.
     * @param trocaDeIdiomaObjeto instância de TrocaDeIdioma criada em FrameUnica.
     */
    public AvisosPopUp(EmpresaAorWords empresaObjeto, TrocaDeIdioma trocaDeIdiomaObjeto) {
        this.empresaObjeto = empresaObjeto;
        this.trocaDeIdiomaObjeto = trocaDeIdiomaObjeto;
    }


    /**
     * Implementa PopUp de aviso.
     *
     * @param mensagem   referência ao texto que será implementado na mensagem da PopUp.
     * @param nomeJanela referência ao texto que constará no nome da PopUp.
     */
    public void mensagemDialogo(int mensagem, int nomeJanela) {
        if (empresaObjeto.isIdiomaPortugues()) {

            JOptionPane.showMessageDialog(null,
                    trocaDeIdiomaObjeto.getIdiomasLista().get(mensagem)[1],
                    trocaDeIdiomaObjeto.getIdiomasLista().get(nomeJanela)[1],
                    JOptionPane.WARNING_MESSAGE);

        } else {

            JOptionPane.showMessageDialog(null,
                    trocaDeIdiomaObjeto.getIdiomasLista().get(mensagem)[2],
                    trocaDeIdiomaObjeto.getIdiomasLista().get(nomeJanela)[2],
                    JOptionPane.WARNING_MESSAGE);

        }
    }

    /**
     * Implementa PopUp de informação ao utilizador.
     *
     * @param mensagem referência ao texto que será implementado na mensagem da PopUp.
     */
    public void mensagemInformacao(int mensagem) {
        if (empresaObjeto.isIdiomaPortugues()) {
            JOptionPane.showMessageDialog(null, trocaDeIdiomaObjeto.getIdiomasLista().get(mensagem)[1]);
        } else {
            JOptionPane.showMessageDialog(null, trocaDeIdiomaObjeto.getIdiomasLista().get(mensagem)[2]);
        }
    }


    /**
     * Implementa PopUp de questão ao utilizador.
     *
     * @param fraseSelecionada   frase que vai ser anunciada.
     * @param quantExib          número de exibições contratadas para aquela frase na aplicação.
     * @param nomeApp            nome da aplicação que vai anunciar a frase.
     * @param valorUnitarioPExib valor por cada exibição da frase naquela aplicação.
     * @param custo              custo total do anúncio.
     * @return retorna resposta do utilizador, se sim, confirma que pretende estabelecer contrato, se não, não pretende estabelecer contrato.
     */
    public int popUpSimOuNao(String fraseSelecionada, String quantExib, String nomeApp, String valorUnitarioPExib, String custo) {
        int resultado;
        if (empresaObjeto.isIdiomaPortugues()) {
            resultado = JOptionPane.showConfirmDialog(null, trocaDeIdiomaObjeto.getIdiomasLista().get(41)[1] + ": " +
                            fraseSelecionada + "\n" + trocaDeIdiomaObjeto.getIdiomasLista().get(42)[1] + ": " + quantExib + "\n" + trocaDeIdiomaObjeto.getIdiomasLista().get(36)[1] +
                            ": " + nomeApp + "\n" + trocaDeIdiomaObjeto.getIdiomasLista().get(38)[1] + ": " + valorUnitarioPExib + "€\n"
                            + trocaDeIdiomaObjeto.getIdiomasLista().get(85)[1] + custo + "€\n" + trocaDeIdiomaObjeto.getIdiomasLista().get(93)[1],
                    trocaDeIdiomaObjeto.getIdiomasLista().get(94)[1], JOptionPane.YES_NO_OPTION);

        } else {
            resultado = JOptionPane.showConfirmDialog(null, trocaDeIdiomaObjeto.getIdiomasLista().get(41)[2] +
                    ": " + fraseSelecionada + "\n" + trocaDeIdiomaObjeto.getIdiomasLista().get(42)[2] + ": " + quantExib + "\n" +
                    trocaDeIdiomaObjeto.getIdiomasLista().get(36)[2] + ": " + nomeApp + "\n" + trocaDeIdiomaObjeto.getIdiomasLista().get(38)[2]
                    + ": " + valorUnitarioPExib + "€\n" + trocaDeIdiomaObjeto.getIdiomasLista().get(85)[2] + custo + "€\n" +
                    trocaDeIdiomaObjeto.getIdiomasLista().get(93)[2], trocaDeIdiomaObjeto.getIdiomasLista().get(94)[2], JOptionPane.YES_NO_OPTION);
        }
        return resultado;
    }


    /**
     * Implementa PopUp em que o utilizador digita um valor.
     *
     * @param mensagem referência ao texto que será implementado na mensagem da PopUp.
     * @return retorna o valor que o utilizador introduziu na caixa de texto da PopUp.
     */
    public String inputDialog(int mensagem) {
        String valor;
        if (empresaObjeto.isIdiomaPortugues()) {
            valor = JOptionPane.showInputDialog(null, trocaDeIdiomaObjeto.getIdiomasLista().get(mensagem)[1]);

        } else {
            valor = JOptionPane.showInputDialog(null, trocaDeIdiomaObjeto.getIdiomasLista().get(mensagem)[2]);
        }
        return valor;
    }


    /**
     * Implementa PopUp de questão ao utilizador.
     *
     * @param mensagem   referência ao texto que será implementado na mensagem da PopUp.
     * @param nomeJanela referência ao texto que constará no nome da PopUp.
     * @return retorna resposta do utilizador, se sim, confirma que pretende eliminar a conta, se não, não pretende eliminar a conta.
     */
    public int popUpSimOuNaoSimples(int mensagem, int nomeJanela) {
        int auxiliarPopUp;
        if (empresaObjeto.isIdiomaPortugues()) {
            auxiliarPopUp = JOptionPane.showConfirmDialog(null, trocaDeIdiomaObjeto.getIdiomasLista().get(mensagem)[1],
                    trocaDeIdiomaObjeto.getIdiomasLista().get(nomeJanela)[1], JOptionPane.YES_NO_OPTION);
        } else {
            auxiliarPopUp = JOptionPane.showConfirmDialog(null, trocaDeIdiomaObjeto.getIdiomasLista().get(mensagem)[2],
                    trocaDeIdiomaObjeto.getIdiomasLista().get(nomeJanela)[2], JOptionPane.YES_NO_OPTION);
        }
        return auxiliarPopUp;
    }
}
