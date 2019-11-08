package scripts.api.painting.components;

import org.tribot.api.General;

import java.awt.*;

public class PaintTextComponent extends PaintComponent {

    private Color fontColor;
    private Font font;

    private String text;


    public static class Builder extends PaintComponent.Builder<Builder> {

        private Color fontColor;
        private Font font = new Font("Helvetica", Font.PLAIN, 12);

        private String text;

        public Builder setFontColor(Color fontColor) {
            this.fontColor = fontColor;
            return this;
        }

        public Builder setFont(Font font) {
            this.font = font;
            return this;
        }

        public Builder(String text) {
            super();
            this.text = text;
        }

        @Override
        protected Builder self() {
            return this;
        }

        @Override
        public PaintTextComponent build() {
            return new PaintTextComponent(this);
        }
    }

    public PaintTextComponent(Builder builder) {
        super(builder);

        this.fontColor = builder.fontColor;
        this.font = builder.font;
        this.text = builder.text;
    }

    @Override
    void drawComponent(Graphics g) {
        g.setFont(font);
        g.setColor(fontColor);

        g.drawString(text, getX() + getMarginLeft(), getY() + getMarinTop() + g.getFontMetrics().getHeight());
    }

    @Override
    public int getWidth() {
        Canvas c = new Canvas();
        return c.getFontMetrics(font).stringWidth(text) / 2;
    }
}
