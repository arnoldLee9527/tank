package interfaces.interfaceImpl;

import bean.Bullet;
import bean.Tank;
import interfaces.Collider;
import modle.GameObject;

/**
 * <strong>Description : TODO</strong><br>
 * <strong>Create on : 2021/4/20 15:23<br>
 * </strong>
 * <p>
 *
 * @author LiXuyang <br>
 * @version <strong> </strong><br>
 * <br>
 * <strong>修改历史:</strong><br>
 * 修改人 | 修改日期 | 修改描述<br>
 * -------------------------------------------<br>
 * <br>
 * <br>
 * @email arnoldlee9527@163.com<br>
 */
public class TankAndTankCollide implements Collider {

    @Override
    public boolean collideWith(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.getGroup() == t2.getGroup() && t1.rectangle.intersects(t2.rectangle)) {
                t1.back();
                t2.back();
            } else {
                return (t2).collideWith(t1);
            }
        }
        return true;
    }
}
