package scripts.api.sprites;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import org.tribot.api.General;
import org.tribot.util.Util;
import scripts.api.painting.images.Images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

public class Sprites {

    private static String ITEM_IMAGE_DIRECTORY = Util.getWorkingDirectory() + "\\Sprites\\";

    public static Image getItemSpriteFX(int id) {
        String fileName = id + ".png", filePath = ITEM_IMAGE_DIRECTORY + fileName;
        try {

            File directory = new File(ITEM_IMAGE_DIRECTORY);
            if (!directory.exists()) {
                General.println("Directory Made: " + ITEM_IMAGE_DIRECTORY);
                directory.mkdir();
            }

            File imageFile = new File(filePath);
            if (imageFile.exists()) {
                return SwingFXUtils.toFXImage(ImageIO.read(imageFile), null);
            }

            URL url = new URL("http://cdn.rsbuddy.com/items/" + id + ".png");
            BufferedImage image = ImageIO.read(url);
            ImageIO.write(image, "png", imageFile);
            return SwingFXUtils.toFXImage(image, null);
        } catch (Exception e) {
            System.out.println("Could not grab icon for item " + id);
        }
        return null;
    }

    public static String getItemSpriteUrl(int id) {
        return "http://cdn.rsbuddy.com/items/" + id + ".png";
    }

    public static java.awt.Image getSkillImage(Images.SkillImage skillImage) {
        String fileName = skillImage.name() + ".png", filePath = ITEM_IMAGE_DIRECTORY + fileName;
        try {

            File directory = new File(ITEM_IMAGE_DIRECTORY);
            if (!directory.exists()) {
                General.println("Directory Made: " + ITEM_IMAGE_DIRECTORY);
                directory.mkdir();
            }

            File imageFile = new File(filePath);
            if (imageFile.exists()) {
                return ImageIO.read(imageFile);
            }

            URL url = new URL(skillImage.getImageUrl());
            BufferedImage image = ImageIO.read(url);
            ImageIO.write(image, "png", imageFile);
            return image;
        } catch (Exception e) {
            System.out.println("Could not grab icon for item " + skillImage.name());
        }
        return null;
    }

}