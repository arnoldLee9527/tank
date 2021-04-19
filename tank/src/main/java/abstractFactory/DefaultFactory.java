package abstractFactory;

import base.Dir;
import base.Group;
import bean.Bullet;
import bean.Explosion;
import bean.Tank;
import windows.TankFrame;

/**
 *
 * <strong>Description : TODO</strong><br>
 * <strong>Create on : 2021/3/31 14:06<br>
 * </strong>
 * <p>
 * 
 * @author LiXuyang <br>
 * @email arnoldlee9527@163.com<br>
 * @version <strong> </strong><br>
 *          <br>
 *          <strong>修改历史:</strong><br>
 *          修改人 | 修改日期 | 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */ 
public class DefaultFactory extends GameFactory{
    @Override
    public BaseTank createTanks(int x, int y, Dir dir, Group group, TankFrame tf) {
        //return new Tank(x, y, dir, group, tf);
        return null;
    }

    @Override
    public BaseBullet createBullets(int x, int y, Dir dir, Group group, TankFrame tf) {
        return new Bullet(x, y, dir, group, tf);
    }

    @Override
    public BaseExplosion createExplosions(int x, int y, TankFrame tf) {
        return new Explosion(x, y);
    }
}
