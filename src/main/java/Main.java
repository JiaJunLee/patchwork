import com.sourcetech.patchwork.hibernate.HibernateFactory;
import com.sourcetech.patchwork.hibernate.model.UserEntity;
import org.hibernate.*;

/**
 * Created by 李佳骏 on 2017/4/4.
 */
public class Main {


    public static void main(String[] args){




        for(int i=0;i<10000;i++){
            new Thread(){
                @Override
                public void run() {
                    HibernateFactory hibernateFactory = HibernateFactory.getInstance();
                    SessionFactory sessionFactory = hibernateFactory.getSessionFactory();
                    Session session = sessionFactory.getCurrentSession();
                    Transaction transaction = session.beginTransaction();

                    UserEntity userEntity = new UserEntity();
                    userEntity.setUsername("AA");
                    userEntity.setPassword("BB");
                    session.save(userEntity);

                    transaction.commit();
                }
            }.start();
        }

    }

}