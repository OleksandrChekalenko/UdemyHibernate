package main;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().
                configure().
                addAnnotatedClass(Employee.class).
                buildSessionFactory();
        Session session = factory.getCurrentSession();
        Employee employee = new Employee("Oleg", "Sidorenko", "IT", 900);

        try {
            session.beginTransaction();
            session.save(employee);
//            session.getTransaction().commit();

            int empId = employee.getId();
//            session = factory.getCurrentSession();
//            session.beginTransaction();
            Employee returnedEmp = session.get(Employee.class, empId);
            session.getTransaction().commit();

            System.out.println("Done!");
            System.out.println(returnedEmp);
        } finally {
            factory.close();
        }
    }
}
