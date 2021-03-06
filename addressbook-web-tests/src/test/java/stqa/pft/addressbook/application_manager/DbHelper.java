package stqa.pft.addressbook.application_manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import stqa.pft.addressbook.model.GroupData;
import stqa.pft.addressbook.model.Groups;
import stqa.pft.addressbook.model.UserData;
import stqa.pft.addressbook.model.Users;

import java.util.List;

public class DbHelper {

    private SessionFactory sessionFactory;

    public DbHelper(){
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Groups groups(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Users users(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery( "from UserData where deprecated = '0000-00-00 00:00:00'" ).list();
        session.getTransaction().commit();
        session.close();
    return new Users(result);
    }

    public Groups userInGroups(UserData user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<UserData> result = session.createQuery(
                "from UserData where id = " + user.getId() ).list();
        session.getTransaction().commit();
        session.close();
        return result.iterator().next().getGroups();
    }
}
