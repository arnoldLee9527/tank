package interfaces.interfaceImpl;

import bean.Bullet;
import bean.Tank;
import interfaces.Collider;
import modle.GameObject;

/**
 *
 * <strong>Description : TODO</strong><br>
 * <strong>Create on : 2021/4/20 15:23<br>
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
public class TankAndBulletCollide implements Collider {
    
    @Override
    public boolean collideWith(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Bullet) {
            return ((Bullet) o2).collideWith((Tank) o1);
        }else if (o2 instanceof Tank && o1 instanceof Bullet) {
            this.collideWith(o2, o1);
        }
        return true;
    }
}
