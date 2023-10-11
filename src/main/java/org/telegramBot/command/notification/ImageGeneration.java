package org.telegramBot.command.notification;

import org.telegramBot.bot.DarkZeit;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ImageGeneration {

    private static final String sourceImagePath = DarkZeit.BOT_DATA.getString("word.path.background");
    private static final String destinationImagePath = DarkZeit.BOT_DATA.getString("word.path.image");

    public static void generate(String word, String translation, Integer indexImage) {
        try {
            BufferedImage image = ImageIO.read(new File(String.format(sourceImagePath, new Random().nextInt(2) + 1)));

            Graphics graphics = image.getGraphics();
            graphics.setFont(new Font("Verdana", Font.BOLD, 190));
            graphics.setColor(Color.WHITE);

            int imageCenterX = image.getWidth() / 2;
            int imageCenterY = image.getHeight() / 2;

            int wordWidth = graphics.getFontMetrics().stringWidth(word);
            graphics.drawString(word, imageCenterX - (wordWidth / 2), imageCenterY);

            int translationWidth = graphics.getFontMetrics().stringWidth(translation);
            graphics.setFont(new Font("Verdana", Font.ITALIC, 110));
            graphics.drawString(String.format("Перевод: %s", translation), imageCenterX - (translationWidth / 2), imageCenterY + 160);

            graphics.dispose();

            ImageIO.write(image, "jpg", new File(String.format(destinationImagePath, indexImage)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

