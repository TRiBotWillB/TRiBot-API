package scripts.api.antiban;

import org.tribot.api.Timing;
import org.tribot.api.util.abc.ABCProperties;
import org.tribot.api.util.abc.ABCUtil;
import org.tribot.api2007.Combat;

/**
 * Created by willb on 13/02/2016.
 */
public class Antiban {

    // Thanks to Starfox

    ABCUtil abcUtil;

    private int eatAt;
    private int runAt;

    public long lastUnderAttackTime;

    private boolean shouldHover;
    private boolean shouldOpenMenu;

    public Antiban(ABCUtil abcUtil) {
        this.abcUtil = abcUtil;

        eatAt = abcUtil.generateEatAtHP();
        runAt = abcUtil.generateRunActivation();
        lastUnderAttackTime = 0;
        shouldHover = false;
        shouldOpenMenu = abcUtil.shouldOpenMenu();
    }

    public void performTimedAntibanActions() {

        if (abcUtil.shouldCheckTabs())
            abcUtil.checkTabs();

        if (abcUtil.shouldCheckXP())
            abcUtil.checkXP();

        if (abcUtil.shouldExamineEntity())
            abcUtil.examineEntity();

        if (abcUtil.shouldMoveMouse())
            abcUtil.moveMouse();

        if (abcUtil.shouldPickupMouse())
            abcUtil.pickupMouse();

        if (abcUtil.shouldRightClick())
            abcUtil.rightClick();

        if (abcUtil.shouldRotateCamera())
            abcUtil.rotateCamera();

        if (abcUtil.shouldLeaveGame()) {
            abcUtil.leaveGame();
        }
    }

    public void sleepReactionTime(boolean hovering) {
        final int reaction_time = getReactionTime(hovering);

        try {
            getABCUtil().sleep(reaction_time);
        } catch (InterruptedException e) {

        }
    }

    public void generateTrackers(int estimated_wait, boolean underAttack, boolean waitingFixed) {
        final ABCProperties properties = getProperties();

        properties.setWaitingTime(estimated_wait);
        properties.setUnderAttack(underAttack);
        properties.setWaitingFixed(waitingFixed);

        getABCUtil().generateTrackers();
    }

    public int getReactionTime(boolean hovering) {
        resetShouldHover();
        resetShouldOpenMenu();

        ABCProperties properties = getProperties();

        properties.setWaitingTime(getWaitingTime());
        properties.setHovering(hovering || shouldHover);
        properties.setMenuOpen(shouldOpenMenu);
        properties.setUnderAttack(Combat.isUnderAttack() || (Timing.currentTimeMillis() - lastUnderAttackTime < 2000));
        properties.setWaitingFixed(false);

        return getABCUtil().generateReactionTime();
    }

    public void resetShouldHover() {
        shouldHover = getABCUtil().shouldHover();
    }

    public boolean shouldHover() {
        return shouldHover;
    }

    public void resetShouldOpenMenu() {
        shouldOpenMenu = getABCUtil().shouldOpenMenu() && getABCUtil().shouldHover();
    }

    public void resetEatAt() {
        eatAt = 0;
    }

    public void resetRunAt() {
        runAt = 0;
    }

    public int getNextRunAt() {
        if (runAt == 0)
            runAt = abcUtil.generateRunActivation();

        return runAt;
    }

    public int getNextEatAt() {
        if (eatAt == 0)
            eatAt = abcUtil.generateEatAtHP();

        return eatAt;
    }

    public int getWaitingTime() {
        return getProperties().getWaitingTime();
    }

    public ABCUtil getABCUtil() {
        return abcUtil;
    }

    public ABCProperties getProperties() {
        return getABCUtil().getProperties();
    }

}
