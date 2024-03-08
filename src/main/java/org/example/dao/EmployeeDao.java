package org.example.dao;

import org.example.entities.Departement;
import org.example.entities.Employee;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

public class EmployeeDao {
    public static List<Employee> getEmployeeByDepartementName(String departementName) {
        Session session = HibernateUtil.getSession();
        List<Employee> employees = null;

        try {
            session.beginTransaction();

            // Retrieve the department by its name
            String hql = "SELECT d FROM Departement d WHERE d.nom = :departementName";
            Departement departement = (Departement) session.createQuery(hql)
                    .setParameter("departementName", departementName)
                    .uniqueResult();

            if (departement != null) {
                // Retrieve all employees for the given department
                hql = "FROM Employee e WHERE e.departement = :departement";
                employees = session.createNativeQuery(hql)
                        .setParameter("departement", departement)
                        .list();
            }
            session.getTransaction().commit();
            return employees;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            // Close the session
            if (session != null) {
                session.close();
            }
        }
    }

    public static Departement getDepartementByEmployeeId(long employeeId) {
        Session session = HibernateUtil.getSession();
        Departement departement = null;

        try {
            session.beginTransaction();

            // Retrieve the employee by ID
            Employee employee = session.get(Employee.class, employeeId);

            if (employee != null) {
                // Retrieve all departments for the given employee
                departement = employee.getDepartement();
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            // Close the session
            if (session != null) {
                session.close();
            }
        }
        return departement;
    }

    public static void addEmployeeToDepartement(long employeeId, long departementId) {
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();

            // Retrieve the employee and department by their IDs
            Employee employee = session.get(Employee.class, employeeId);
            Departement departement = session.get(Departement.class, departementId);

            if (employee != null && departement != null) {
                // Set the department for the employee
                employee.setDepartement(departement);
                // Save or update the employee (depending on your use case)
                session.saveOrUpdate(employee);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Close the session
            if (session != null) {
                session.close();
            }
        }
    }

    public static void deleteEmployeeById(long employeeId) {
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();

            // Retrieve the employee by ID
            Employee employee = session.get(Employee.class, employeeId);

            if (employee != null) {
                // Delete the employee
                session.delete(employee);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Close the session
            if (session != null) {
                session.close();
            }
        }
    }

    public static void deleteDepartementById(long departementId) {
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();

            // Retrieve the department by ID
            Departement departement = session.get(Departement.class, departementId);

            if (departement != null) {
                // Delete the department
                session.delete(departement);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Close the session
            if (session != null) {
                session.close();
            }
        }
    }

}
