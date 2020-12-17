package main;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().
                configure().
                addAnnotatedClass(Employee.class).
                buildSessionFactory();
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

//            List<Employee> emps = session.createQuery("from Employee").getResultList();
            List<Employee> emps = session.createQuery("from Employee where firstName = 'Kal' " +
                    "AND salary>2500").getResultList();


            for (Employee e :
                    emps) {
                System.out.println(e);
            }

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            factory.close();
        }
    }
}
