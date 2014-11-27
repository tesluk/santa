package tab.os.tools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by andrey.tesluk on 27.11.2014.
 */
public class DBSession {

    private static SessionFactory factory;

    private static SessionFactory getFactory() {
        if (factory == null) {
            Configuration configuration = new Configuration();
            configuration.configure();

            ServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            factory = configuration.buildSessionFactory(registry);
        }
        return factory;
    }

    public static Session getSession(){
        return getFactory().openSession();
    }

}
