package interfaces.interfaceImpl;

import bean.Bullet;
import bean.Tank;
import bean.Wall;
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
public class WallAndBulletCollide implements Collider {
    
    @Override
    public boolean collideWith(GameObject o1, GameObject o2) {
        if (o1 instanceof Wall && o2 instanceof Bullet) {
            return ((Wall) o1).collideWith((Bullet) o2);
        }else if (o1 instanceof Bullet && o2 instanceof Wall) {
            this.collideWith(o2, o1);
        }
        return true;
    }
}
