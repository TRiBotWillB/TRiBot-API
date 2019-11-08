package scripts.api.painting.components;

import java.awt.*;
import java.util.ArrayList;

public class PaintListComponent extends PaintComponent {


    private ArrayList<String> list;

    public static class Builder extends PaintComponent.Builder<PaintListComponent.Builder> {

        private ArrayList<String> list = new ArrayList<>();

        public Builder addString(String s) {
            list.add(s);

            return self();
        }

        @Override
        protected PaintListComponent.Builder self() {
            return this;
        }

        @Override
        public PaintListComponent build() {
            return new PaintListComponent(this);
        }
    }

    private PaintListComponent(Builder builder) {
        super(builder);

        this.list = builder.list;

        PaintRectComponent bgRect = new PaintRectComponent.Builder().setBackgroundColor(Color.GREEN)
                .setWidth(200)
                .setHeight(200)
                .setBorderColor(Color.BLACK)
                .setBorderThickness(1.1F)
                .build();

        this.addChild(bgRect);

        int marginTop = 0;

        for (String s : list) {
            if (marginTop + 15 <= bgRect.getHeight())
                this.addChild(new PaintTextComponent.Builder(s)
                        .setMarginTop(marginTop)
                        .setFontColor(Color.WHITE)
                        .build());
            //Implement Text Height
            marginTop += 15;
        }
    }

    @Override
    void drawComponent(Graphics g) {
        //Draw each child, seperated by height, growth list box option

    }


    //Can add different components to list
    //Make a default getHeight

    @Override
    public void addChild(PaintComponent paintComponent) {
        super.addChild(paintComponent);
    }

    @Override
    public int getWidth() {
        return 200;
    }
}
