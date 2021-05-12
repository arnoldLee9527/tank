package interfaces;

import modle.GameObject;

/**
 *
 * <strong>Description : TODO</strong><br>
 * <strong>Create on : 2021/4/20 15:22<br>
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
public interface Collider {

    boolean collideWith(GameObject o1, GameObject o2);
    
}
