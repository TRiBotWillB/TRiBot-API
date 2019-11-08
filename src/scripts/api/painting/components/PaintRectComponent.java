package scripts.api.painting.components;

import org.tribot.api2007.Skills;
import scripts.api.utility.trackers.SkillsTracker;

import java.awt.*;

public class PaintRectComponent extends PaintComponent {

    private int width;
    private int height;

    private float borderThickness;

    private Color backgroundColor;
    private Color borderColor;

    public static class Builder extends PaintComponent.Builder<Builder> {

        private int width;
        private int height;

        private float borderThickness;

        private Color backgroundColor;
        private Color borderColor;

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setBackgroundColor(Color backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder setBorderColor(Color borderColor) {
            this.borderColor = borderColor;
            return this;
        }

        public Builder setBorderThickness(float borderThickness) {
            this.borderThickness = borderThickness;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public PaintRectComponent build() {
            return new PaintRectComponent(this);
        }
    }

    private PaintRectComponent(Builder builder) {
        super(builder);

        this.width = builder.width;
        this.height = builder.height;
        this.backgroundColor = builder.backgroundColor;
        this.borderColor = builder.borderColor;
        this.borderThickness = builder.borderThickness;
    }


    @Override
    void drawComponent(Graphics g) {
        g.setColor(borderColor);
        ((Graphics2D) g).setStroke(new BasicStroke(borderThickness));
        g.drawRect(getX(), getY(), width, height);
        g.setColor(backgroundColor);
        g.fillRect(getX(), getY(), width, height);
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
