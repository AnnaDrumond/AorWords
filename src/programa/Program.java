package programa;

import gui.FrameUnica;

//Anna Helena Drumond - 2021151911
//Joana Valente - 2021152666

/**
 * Classe principal.
 * Instancia um objeto do tipo EmpresaAorWords e um objeto do tipo FrameUnica.
 * Permite inicar a leitura dos ficheiros bem como a interface gráfica.
 */

public class Program {

    public static void main(String[] args) {

        EmpresaAorWords empresaObjeto = new EmpresaAorWords(args);
        FrameUnica frameUnica = new FrameUnica(empresaObjeto, empresaObjeto.getFicheiroDeTexto());
    }
}
