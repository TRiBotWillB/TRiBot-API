package scripts.api.painting.components;

import org.tribot.api.General;

import java.awt.*;
import java.util.ArrayList;

public abstract class PaintComponent {

    private int x;
    private int y;
    private int marinTop;
    private int marginLeft;

    private ArrayList<PaintComponent> childComponents;

    abstract static class Builder<T extends Builder<T>> {
        private int x;
        private int y;
        private int marginTop;
        private int marinLeft;

        private ArrayList<PaintComponent> childComponents;

        protected Builder() {
            this.childComponents = new ArrayList<>();

        }

        public T setX(int x) {
            this.x = x;

            return self();
        }

        public T setY(int y) {
            this.y = y;

            return self();
        }

        public T setMarginTop(int marginTop) {
            this.marginTop = marginTop;
            return self();
        }

        public T setMarinLeft(int marinLeft) {
            this.marinLeft = marinLeft;
            return self();
        }

        public T addChild(PaintComponent paintComponent) {
            childComponents.add(paintComponent);
            return self();
        }

        protected abstract T self();

        public abstract PaintComponent build();
    }

    PaintComponent(Builder<?> builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.marinTop = builder.marginTop;
        this.marginLeft = builder.marinLeft;

        this.childComponents = new ArrayList<>();

        for (PaintComponent p : builder.childComponents) {
            addChild(p);
        }
    }

    public void draw(Graphics g) {
        drawComponent(g);

        for (PaintComponent paintComponent : childComponents) {
            paintComponent.drawComponent(g);
        }
    }

    abstract void drawComponent(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getMarinTop() {
        return marinTop;
    }

    public void setMarinTop(int marinTop) {
        this.marinTop = marinTop;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public abstract int getWidth();


    public int getRealWidth() {
        return getWidth() + getMarginLeft();
    }

    public ArrayList<PaintComponent> getChildComponents() {
        return this.childComponents;
    }

    public void addChild(PaintComponent paintComponent) {
        //Check bounds and move if out of bounds
        paintComponent.setX(getX() + paintComponent.getMarginLeft());
        paintComponent.setY(getY() + paintComponent.getMarinTop());

        childComponents.add(paintComponent);
    }

    //public abstract boolean inBounds(int x, int y);
}
