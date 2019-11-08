package scripts.api.painting.components;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api2007.Skills;
import scripts.api.painting.images.Images;
import scripts.api.utility.trackers.SkillsTracker;

import java.awt.*;

public class PaintSkillComponent extends PaintComponent {

    private SkillsTracker skillsTracker;
    private Skills.SKILLS skill;

    public static class Builder extends PaintComponent.Builder<PaintSkillComponent.Builder> {

        private SkillsTracker skillsTracker;
        private Skills.SKILLS skill;

        public Builder setSkillsTracker(SkillsTracker skillsTracker) {
            this.skillsTracker = skillsTracker;
            return self();
        }

        public Builder setSkill(Skills.SKILLS skill) {
            this.skill = skill;
            return self();
        }

        public Builder(SkillsTracker skillsTracker, Skills.SKILLS skill) {
            super();

            this.skill = skill;
            this.skillsTracker = skillsTracker;
        }

        @Override
        protected PaintSkillComponent.Builder self() {
            return this;
        }

        @Override
        public PaintSkillComponent build() {
            return new PaintSkillComponent(this);
        }
    }

    private PaintSkillComponent(PaintSkillComponent.Builder builder) {
        super(builder);

        this.skillsTracker = builder.skillsTracker;
        this.skill = builder.skill;


        //Background Rect
        this.addChild(new PaintRectComponent.Builder()
                .setWidth(520)
                .setHeight(20)
                .setBorderThickness(2.5F)
                .setBorderColor(Color.GREEN)
                .setBackgroundColor(new Color(Color.BLACK.getRed(), Color.BLACK.getGreen(), Color.BLACK.getBlue(), 150))
                .build());

        //Fill Rect
        double percentTillLevel = Skills.getPercentToNextLevel(skill);

        double width = (percentTillLevel / 100.0) * getWidth();

        this.addChild(new PaintRectComponent.Builder()
                .setWidth((int) width) //Set to % to Next Level
                .setHeight(20)
                .setBorderThickness(0)
                .setBorderColor(new Color(0, 0, 0, 0))
                .setBackgroundColor(new Color(Color.GREEN.getRed(), Color.GREEN.getGreen(), Color.GREEN.getBlue(), 150))
                .build());


        //Skill Text
        //        PaintTextComponent skillName = new PaintTextComponent.Builder(skill.toString()).setMarinLeft(5).setFontColor(Color.WHITE).build();

        PaintImageComponent skillImg = new PaintImageComponent.Builder(Images.getSkillImage(skill))
                .setMarginTop(2)
                .setMarinLeft(5)
                .setHeight(15).setWidth(15)
                .build();

        this.addChild(skillImg);

        //XP Gained Text
        PaintTextComponent xpGained = new PaintTextComponent.Builder("XP Gained: " + skillsTracker.getXPGained(skill))
                .setFontColor(Color.WHITE)
                .setMarinLeft(skillImg.getRealWidth() + 10)
                .build();
        this.addChild(xpGained);


        //XP Per Hour Text
        PaintTextComponent xpPerHour = new PaintTextComponent.Builder("XP Per Hour: " + (int) skillsTracker.getXPPerHour(skill))
                .setFontColor(Color.WHITE)
                .setMarinLeft(xpGained.getRealWidth() + 10)
                .build();

        this.addChild(xpPerHour);

        //TTL Text
        PaintTextComponent timeTillLevel = new PaintTextComponent.Builder("TTL: " + Timing.msToString(skillsTracker.getTimeToNextLevel(skill)))
                .setFontColor(Color.WHITE)
                .setMarinLeft(xpPerHour.getRealWidth() + 10)
                .build();

        this.addChild(timeTillLevel);
    }

    @Override
    void drawComponent(Graphics g) {

    }

    @Override
    public int getWidth() {
        return 520; // Change
    }
}
