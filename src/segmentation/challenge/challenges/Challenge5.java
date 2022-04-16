package segmentation.challenge.challenges;

import segmentation.challenge.bean.Image;
import segmentation.challenge.utils.ImageUtils;

import static segmentation.challenge.utils.ImageConstants.*;

import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.*;
import java.awt.Color;

import java.util.Optional;

/**
 * Classe para resolver o desafio 5.
 * 
 * Mendes, apaixonada por flores, pegou uma imagem na internet para decorar seu álbum de imagens em preto e branco. 
 * Solicitou que fosse retirado o fundo da imagem, permanecendo somente a flor.
 * Para isso, utiliza o algoritmo de threshold com o limiar de 150. 
 */
public final class Challenge5 implements Challenge {

    /** Limiar para o algoritmo de thresholding */
    private static final int THRESHOLDING = 150;

    /** Construtor do desafio 5 */
    public Challenge5() {}

    @Override
    public void solveChallenge() {
        Optional<Image> optImage = ImageUtils.getImageFromFile(CHALLENGE_5_PATH);
        if (optImage.isEmpty()) {
            return;
        }
        Image image = optImage.get();
        BufferedImage processed = new BufferedImage(image.getWidth(), image.getHeight(), TYPE_INT_RGB);
        int[][] matrix = image.getMatrix();
        for(int i=0; i < image.getWidth() - 1; i++) {
            for(int j=0; j < image.getHeight() - 1; j++) {
                Color color = new Color(matrix[i][j]);
                int blue = color.getBlue();
                if (blue > THRESHOLDING) {
                    processed.setRGB(i, j, Color.WHITE.getRGB());
                } else {
                    processed.setRGB(i, j, Color.BLACK.getRGB());
                }
            }
        }
        ImageUtils.writeImage(processed, PROCESSED_IMAGE_PATH); 
        ImageUtils.showProcessedImage();
    }
}
