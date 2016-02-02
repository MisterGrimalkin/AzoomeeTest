package dao;

import entity.Department;
import entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao extends BaseDao {

    public void truncate() {
        // !!!
        executeUpdate("TRUNCATE TABLE azoomee.department");
        executeUpdate("TRUNCATE TABLE azoomee.employee");
    }

    public Department createDepartment(String name) {
        Department department = new Department(getNextDepartmentId(), name);
        executeUpdate("INSERT INTO azoomee.department(department_id, name) VALUES(" + department.getId() + ",'" + department.getName() + "');");
        return department;
    }

    public Employee createEmployee(String firstname, String lastname, Department department) {
        Employee employee = new Employee(getNextEmployeeId(), firstname, lastname, department);
        executeUpdate("INSERT INTO azoomee.employee(employee_id, firstname, lastname, department_id) VALUES(" +
                employee.getId() + ", '" + employee.getFirstname() + "','" + employee.getLastname() + "'," + department.getId() + ");"
        );
        return employee;
    }

    public List<Department> getDepartments() {
        List<Department> result = new ArrayList<>();
        ResultSet rs = executeSelect("SELECT department_id, name FROM azoomee.department;");
        try {
            while (rs.next()) {
                result.add(new Department(rs.getLong(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Department getDepartment(long id) {
        ResultSet rs = executeSelect("SELECT department_id, name FROM azoomee.department WHERE department_id = " + id + ";");
        try {
            while (rs.next()) {
                return new Department(rs.getLong(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> getEmployees() {
        List<Employee> result = new ArrayList<>();
        ResultSet rs = executeSelect("SELECT employee_id, firstname, lastname, department_id FROM azoomee.employee;");
        try {
            while (rs.next()) {
                Department department = getDepartment(rs.getLong(4));
                result.add(new Employee(rs.getLong(1), rs.getString(2), rs.getString(3), department));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Employee getEmployee(long id) {
        ResultSet rs = executeSelect("SELECT employee_id, firstname, lastname, department_id FROM azoomee.employee WHERE employee_id = " + id + ";");
        try {
            while (rs.next()) {
                Department department = getDepartment(rs.getLong(4));
                return new Employee(rs.getLong(1), rs.getString(2), rs.getString(3), department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateDepartment(Department department) {
        // TODO
    }

    public void updateEmployee(Employee employee) {
        // TODO
    }

    public void deleteDepartment(long id) {
        executeUpdate("DELETE FROM azoomee.department WHERE department_id=" + id);
    }

    public void deleteEmployee(long id) {
        executeUpdate("DELETE FROM azoomee.employee WHERE employee_id=" + id);
    }

    private long getNextDepartmentId() {
        return getMaxValue("azoomee.department", "department_id") + 1;
    }

    private long getNextEmployeeId() {
        return getMaxValue("azoomee.employee", "employee_id") + 1;
    }


}
