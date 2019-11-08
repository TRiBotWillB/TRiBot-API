package scripts.api.painting.images;

import org.tribot.api2007.Skills;
import scripts.api.sprites.Sprites;

import java.awt.*;
import java.util.HashMap;


public class Images {

    private HashMap<Skills.SKILLS, SkillImage> skillImages = new HashMap<>();

    public enum SkillImage {
        COOKING("https://ih0.redbubble.net/image.470904693.3904/flat,1000x1000,075,f.u2.jpg");


        private String imageUrl;

        SkillImage(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Image getImage() {
            return Sprites.getSkillImage(this);
        }

        public String getImageUrl() {
            return this.imageUrl;
        }
    }

    public static Image getSkillImage(Skills.SKILLS skill) {
        for (SkillImage i : SkillImage.values()) {
            if (i.name().equals(skill.name()))
                return i.getImage();
        }
        return null;
    }
}
