package scripts.api.painting.components;

import java.awt.*;
import java.awt.image.ImageObserver;

public class PaintImageComponent extends PaintComponent {

    private Image image;
    private int width, height;

    public static class Builder extends PaintComponent.Builder<PaintImageComponent.Builder> {

        private Image image;
        private int width, height;

        public Builder(Image image) {
            super();

            this.image = image;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return self();
        }

        public Builder setHeight(int height) {
            this.height = height;
            return self();
        }

        @Override
        protected PaintImageComponent.Builder self() {
            return this;
        }

        @Override
        public PaintImageComponent build() {
            return new PaintImageComponent(this);
        }
    }

    private PaintImageComponent(Builder builder) {
        super(builder);

        this.width = builder.width;
        this.height = builder.height;

        this.image = builder.image;


        if (this.width == 0)
            this.width = image.getWidth(null);

        if (this.height == 0)
            this.height = image.getHeight(null);
    }

    @Override
    void drawComponent(Graphics g) {
        g.drawImage(image, getX(), getY(), width, height, null);
    }

    @Override
    public int getWidth() {
        return width;
    }
}
