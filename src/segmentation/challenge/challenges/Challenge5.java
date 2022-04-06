package segmentation.challenge.challenges;

import segmentation.challenge.window.Processed;

import static segmentation.challenge.utils.ImageConstants.*;

/**
 * Classe para resolver o desafio 5.
 * 
 * Mendes, apaixonada por flores, pegou uma imagem na internet para decorar seu álbum de imagens em preto e branco. 
 * Solicitou que fosse retirado o fundo da imagem, permanecendo somente a flor.
 * Para isso, utiliza o algoritmo de threshold com o limiar de 150. 
 */
public final class Challenge5 implements Challenge {

    /** Construtor do desafio 5 */
    public Challenge5() {}

    @Override
    public void solveChallenge() {
        Processed p = new Processed(CHALLENGE_5_PATH);
        p.setVisible(true);
    }

}
