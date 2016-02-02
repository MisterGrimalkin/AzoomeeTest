package entity;

public class Employee {

    private Long id;
    private String firstname;
    private String lastname;
    private Department department;

    public Employee(Long id, String firstname, String lastname, Department department) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Department getDepartment() {
        return department;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}