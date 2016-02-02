package webservice;

import dao.EmployeeDao;
import entity.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class EmployeeService {

    private EmployeeDao dao;

    @RequestMapping(value="/employee", method= GET)
    public Employee getEmployee(@RequestParam(value="id",required=true) long id) {
        return dao.getEmployee(id);
    }

}
