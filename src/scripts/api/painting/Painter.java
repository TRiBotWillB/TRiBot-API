package scripts.api.painting;

import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Skills;
import org.tribot.api2007.types.RSInterface;
import scripts.api.painting.components.PaintComponent;
import scripts.api.painting.components.PaintSkillComponent;
import scripts.api.utility.trackers.SkillsTracker;

import java.awt.*;

/**
 * Created by willb on 26/08/2018.
 */
public class Painter {

    private PaintingInfo paintingInfo;

    public Painter(PaintingInfo paintingInfo) {
        this.paintingInfo = paintingInfo;
    }

    public void paint(Graphics g) {
        /*int newStartY = startY - ((paintingInfo.getPaintInfo().length - 1) * 20);

        String[] paintInfo = paintingInfo.getPaintInfo();

        for (int i = 0; i < paintInfo.length; i++) {
            g.drawString(paintInfo[i], startX, newStartY + (i * 20));
        }*/

        for (PaintComponent p : paintingInfo.getPaintComponents()) {
            p.draw(g);
        }

    }
}
