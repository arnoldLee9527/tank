package interfaces.interfaceImpl;

import base.PropertiesManager;
import interfaces.Collider;
import modle.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * <strong>Description : TODO</strong><br>
 * <strong>Create on : 2021/5/10 15:06<br>
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
public class ColliderChain implements Collider {

    private List<Collider> colliders = new LinkedList<>();


    public ColliderChain() {
        PropertiesManager propertiesManager = PropertiesManager.getInstance();
        String collideGameModel = (String) propertiesManager.getProperty("collideGameModel");
        String[] split = collideGameModel.split(",");
        for (String s : split) {
            try {
                this.add((Collider)Class.forName(s).getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void add(Collider collider) {
        colliders.add(collider);
    }
    
    
    @Override
    public boolean collideWith(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (!colliders.get(i).collideWith(o1, o2)){
                return false;
            }
        }
        return true;
    }
}
