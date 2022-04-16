package segmentation.challenge.challenges;

import segmentation.challenge.bean.Image;
import segmentation.challenge.utils.ImageUtils;

import static segmentation.challenge.utils.ImageConstants.*;

import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import java.awt.Color;

import java.util.Optional;

/**
 * Classe para resolver o desafio 4.
 * 
 * Claudiomiro quer dar a sua namorada de presente uma caricatura sua, onde apareçam só os contornos da imagem. 
 * Utilize a imagem de entrada e aplique o detector de bordas de Sobel de forma que a foto fique como a demonstrada abaixo.
 */
public final class Challenge4 implements Challenge {

    /** Matriz auxiliar para armazenar os valores RGB */
    private static final int[][] pixelMatrix = new int[3][3];                                            

    /** Construtor do desafio 4 */
    public Challenge4() {}

    @Override
    public void solveChallenge() {
        Optional<Image> optImage = ImageUtils.getImageFromFile(CHALLENGE_4_PATH);
        if (optImage.isEmpty()) {
            return;
        }
        Image image = optImage.get();
        BufferedImage processed = new BufferedImage(image.getWidth(), image.getHeight(), TYPE_INT_RGB);
        int[][] matrix = image.getMatrix();
        for(int i=1; i < image.getWidth() - 1; i++){
            for(int j=1; j < image.getHeight() - 1;j++){
                pixelMatrix[0][0] = new Color(matrix[i-1][j-1]).getRed();
                pixelMatrix[0][1] = new Color(matrix[i-1][j]).getRed();
                pixelMatrix[0][2] = new Color(matrix[i-1][j+1]).getRed();
                pixelMatrix[1][0] = new Color(matrix[i][j-1]).getRed();
                pixelMatrix[1][2] = new Color(matrix[i][j+1]).getRed();
                pixelMatrix[2][0] = new Color(matrix[i+1][j-1]).getRed();
                pixelMatrix[2][1] = new Color(matrix[i+1][j]).getRed();
                pixelMatrix[2][2] = new Color(matrix[i+1][j+1]).getRed();
                int edge = (int) convolution(pixelMatrix);
                processed.setRGB(i, j, new Color((edge<<16 | edge<<8 | edge)).getRGB());
            }
        }
        ImageUtils.writeImage(processed, PROCESSED_IMAGE_PATH); 
        ImageUtils.showProcessedImage();
    }

    private double convolution(int[][] pixelMatrix){
        int gy=(pixelMatrix[0][0]*-1)+(pixelMatrix[0][1]*-2)+(pixelMatrix[0][2]*-1)+(pixelMatrix[2][0])+(pixelMatrix[2][1]*2)+(pixelMatrix[2][2]*1);
        int gx=(pixelMatrix[0][0])+(pixelMatrix[0][2]*-1)+(pixelMatrix[1][0]*2)+(pixelMatrix[1][2]*-2)+(pixelMatrix[2][0])+(pixelMatrix[2][2]*-1);
        return Math.sqrt(Math.pow(gy,2)+Math.pow(gx,2));
    }

}
