package interfaces.interfaceImpl;

import base.Dir;
import base.Group;
import bean.Bullet;
import bean.Tank;
import interfaces.FireStrategy;

/**
 *
 * <strong>Description : 四方向发射子弹</strong><br>
 * <strong>Create on : 2021/3/17 10:36<br>
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
public class SuperFireStrategy implements FireStrategy {
    
    //private static final FireStrategy INSTANCE = new SuperFireStrategy();
    //
    //private SuperFireStrategy(){}
    //
    //public static FireStrategy getInstance(){
    //    return INSTANCE;
    //}
    
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.TANK_WIDTH / 2 - Bullet.BULLET_WIDTH / 2;
        int bY = tank.getY() + Tank.TANK_HEIGHT / 2 - Bullet.BULLET_HEIGHT / 2;
        for (Dir dir : Dir.values()) {
            new Bullet(bX, bY, dir, tank.getGroup(), tank.getGameModel());
        }
    }
}
