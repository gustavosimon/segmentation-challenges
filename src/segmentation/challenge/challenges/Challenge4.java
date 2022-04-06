package segmentation.challenge.challenges;

import segmentation.challenge.window.Processed;

import static segmentation.challenge.utils.ImageConstants.*;

/**
 * Classe para resolver o desafio 4.
 * 
 * Claudiomiro quer dar a sua namorada de presente uma caricatura sua, onde apareçam só os contornos da imagem. 
 * Utilize a imagem de entrada e aplique o detector de bordas de Sobel de forma que a foto fique como a demonstrada abaixo.
 */
public final class Challenge4 implements Challenge {

    /** Construtor do desafio 4 */
    public Challenge4() {}

    @Override
    public void solveChallenge() {
        Processed p = new Processed(CHALLENGE_4_PATH);
        p.setVisible(true);
    }

}
