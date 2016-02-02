package dao;

import entity.Department;
import entity.Employee;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EmployeeDaoTest {

    EmployeeDao dao;

    private static final String DEPT_1 = "Creative";
    private static final String DEPT_2 = "Accounts";

    private static final String[] EMP_1 = { "David", "Bowie" };
    private static final String[] EMP_2 = { "Alan", "Rickman" };

    @Test
    public void doTest() {

        given_employee_dao();

        then_department_count_is(0);

            long deptId1 =
        when_create_department(DEPT_1);
        then_department_count_is(1);

            long deptId2 =
        when_create_department(DEPT_2);
        then_department_count_is(2);

        then_department_is_called(deptId1, DEPT_1);
        then_department_is_called(deptId2, DEPT_2);

        then_employee_count_is(0);

            long emp1Id =
        when_create_employee(EMP_1[0], EMP_1[1], deptId1);
        then_employee_count_is(1);

            long emp2Id =
        when_create_employee(EMP_2[0], EMP_2[1], deptId2);
        then_employee_count_is(2);

        then_employee_is(emp1Id, EMP_1[0], EMP_1[1], DEPT_1);
        then_employee_is(emp2Id, EMP_2[0], EMP_2[1], DEPT_2);

        // test updates

        when_delete_employee(emp1Id);
        then_employee_count_is(1);

        when_delete_department(deptId1);
        then_department_count_is(1);

    }

    void when_delete_employee(long id) {
        dao.deleteEmployee(id);
    }

    void when_delete_department(long id) {
        dao.deleteDepartment(id);
    }

    void then_employee_is(long id, String firstname, String lastname, String departmentName) {
        Employee employee = dao.getEmployee(id);
        assertNotNull(employee);
        assertEquals(firstname, employee.getFirstname());
        assertEquals(lastname, employee.getLastname());
        assertNotNull(employee.getDepartment());
        assertEquals(departmentName, employee.getDepartment().getName());
    }

    void given_employee_dao() {
        dao = new EmployeeDao();
        dao.truncate();     // TODO reconfigure
    }

    long when_create_department(String name) {
        return dao.createDepartment(name).getId();
    }

    long when_create_employee(String firstname, String lastname, long departmentId) {
        return dao.createEmployee(firstname, lastname, dao.getDepartment(departmentId)).getId();
    }

    void then_department_count_is(int count) {
        assertEquals(count, dao.getDepartments().size());
    }

    void then_department_is_called(long id, String name) {
        Department dept = dao.getDepartment(id);
        assertNotNull(dept);
        assertEquals(name, dept.getName());
    }

    void then_employee_count_is(int count) {
        assertEquals(count, dao.getEmployees().size());
    }

}
