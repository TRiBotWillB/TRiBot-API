package scripts.api.projectiles;

import org.tribot.api.General;
import org.tribot.api2007.Game;
import org.tribot.api2007.Login;
import org.tribot.api2007.Projectiles;
import org.tribot.api2007.types.RSProjectile;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by willb on 26/07/2018.
 */
public class ProjectileObserver extends Thread {

    private ArrayList<ProjectileListener> listeners;
    private boolean running;

    private ArrayList<RSProjectile> projectileCache;

    public ProjectileObserver() {
        this.listeners = new ArrayList<>();
        this.running = true;
        this.projectileCache = new ArrayList<>();
    }

    @Override
    public void run() {
        while (Login.getLoginState() != Login.STATE.INGAME) {
            General.sleep(500);
        }

        while (running) {
            RSProjectile[] projectiles = Projectiles.getAll();
            for (RSProjectile p : projectiles) {
                if (!projectileCache.contains(p)) {
                    projectileMovedTrigger(p);
                    projectileCache.add(p);
                }
            }

            Iterator<RSProjectile> it = projectileCache.iterator();

            while (it.hasNext()) {
                RSProjectile next = it.next();

                if (Game.getLoopCycle() > next.getEndCycle()) {
                    it.remove();
                }
            }

            General.sleep(50);
        }
    }

    public void addListener(ProjectileListener listener) {
        this.listeners.add(listener);
        General.println("Added listener");
    }

    public void projectileMovedTrigger(RSProjectile projectile) {
        for (ProjectileListener l : listeners) {
            l.projectileMoved(projectile);
        }
    }
}
