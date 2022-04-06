package segmentation.challenge.challenges;

import segmentation.challenge.window.Processed;

import static segmentation.challenge.utils.ImageConstants.*;

/**
 * Classe para resolver o desafio 6.
 * 
 * Ruídos são encontrados normalmente em imagens. 
 * O programador Marinalvo recebeu uma imagem com ruído, e ele precisa remover o fundo desta imagem. 
 * Abaixo estão a imagem original e imagem de saída. Utilizar como filtro de suavização o de média e o limiar como 60.
 */
public final class Challenge6 implements Challenge {

    /** Construtor do desafio 6 */
    public Challenge6() {}

    @Override
    public void solveChallenge() {
        Processed p = new Processed(CHALLENGE_6_PATH);
        p.setVisible(true);
    }

}
