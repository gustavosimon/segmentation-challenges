package segmentation.challenge.challenges;

import static segmentation.challenge.utils.ImageConstants.*;

import java.util.Optional;

import segmentation.challenge.bean.Image;
import segmentation.challenge.utils.ImageUtils;

import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.*;
import java.awt.Color;

/**
 * Classe para resolver o desafio 6.
 * 
 * Ruídos são encontrados normalmente em imagens. 
 * O programador Marinalvo recebeu uma imagem com ruído, e ele precisa remover o fundo desta imagem. 
 * Abaixo estão a imagem original e imagem de saída. 
 * Utilizar como filtro de suavização o de média e o limiar como 60.
 */
public final class Challenge6 implements Challenge {

    /** Máscara para aplicar filtro de média */
    private static final int[][] mask = {{ 1,  1,  1 },
                                         { 1,  1,  1 },
                                         { 1,  1,  1 }};    
    /** Limiar para o algoritmo de thresholding */
    private static final int THRESHOLDING = 60;

    /** Construtor do desafio 6 */
    public Challenge6() {}

    @Override
    public void solveChallenge() {
        Optional<Image> optImage = ImageUtils.getImageFromFile(CHALLENGE_6_PATH);
        if (optImage.isEmpty()) {
            return;
        }
        Image image = optImage.get();
        BufferedImage processed = new BufferedImage(image.getWidth(), image.getHeight(), TYPE_INT_RGB);
        int[][] matrix = image.getMatrix();
        int[][] matrixAux = matrix;
        //
        // Aplica filtro de média para suavizar a imagem
        // 
        double redSum = 0;
        double greenSum = 0;
        double blueSum = 0;
        int newRed = 0;
        int newGreen = 0;
        int newBlue = 0;
        for (int y = 1; y < image.getHeight() - 1; y++) {
            for (int x = 1; x < image.getWidth() - 1; x++) {
                redSum = 0;
                greenSum = 0;
                blueSum =  0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Color color = new Color(matrix[x + (i - 1)][y + (j - 1)]);
                        redSum += color.getRed() * mask[i][j];
                        greenSum += color.getGreen() * mask[i][j];
                        blueSum += color.getBlue() * mask[i][j];
                    }
                }
                newRed = (int) redSum / 9;
                newGreen = (int) greenSum / 9;
                newBlue = (int) blueSum / 9;
                matrixAux[x][y] = new Color(newRed, newGreen, newBlue).getRGB();
            }
        }
        //
        // Aplica limiar na imagem
        // 
        for(int i=0; i < image.getWidth() - 1; i++) {
            for(int j=0; j < image.getHeight() - 1; j++) {
                Color color = new Color(matrixAux[i][j]);
                int red    = color.getRed();
                int green  = color.getGreen();
                int blue   = color.getBlue();
                if ((red + green + blue) / 3 > THRESHOLDING) {
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
