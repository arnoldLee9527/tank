package interfaces.interfaceImpl;

import bean.Bullet;
import bean.Tank;
import interfaces.FireStrategy;

/**
 *
 * <strong>Description : 默认开火模式</strong><br>
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
public class DefaultFireStrategy implements FireStrategy {
    
    //private static final FireStrategy INSTANCE = new DefaultFireStrategy();
    //
    //private DefaultFireStrategy(){}
    //
    //public static FireStrategy getInstance(){
    //    return INSTANCE;
    //}
    //
    @Override
    public void fire(Tank tank) {
        int bX = tank.getX() + Tank.TANK_WIDTH / 2 - Bullet.BULLET_WIDTH / 2;
        int bY = tank.getY() + Tank.TANK_HEIGHT / 2 - Bullet.BULLET_HEIGHT / 2;
        new Bullet(bX, bY, tank.getDir(), tank.getGroup());
    }
}
