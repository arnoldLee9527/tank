package abstractFactory;

import base.Dir;
import base.Group;
import bean.Tank;
import windows.TankFrame;

/**
 *
 * <strong>Description : TODO</strong><br>
 * <strong>Create on : 2021/3/31 11:03<br>
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
public abstract class GameFactory {
    
    public abstract BaseTank createTanks(int x, int y, Dir dir, Group group, TankFrame tf);
    
    public abstract BaseBullet createBullets(int x, int y, Dir dir, Group group,  TankFrame tf);
    
    public abstract BaseExplosion createExplosions(int x, int y, TankFrame tf);
}
