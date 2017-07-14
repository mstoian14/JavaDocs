package ro.teamnet.zth.api.em;

import org.junit.Assert;
import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Location;
import ro.teamnet.zth.autowired.A;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mihaela.Stoian on 7/14/2017.
 */
public class EntityManagerImplTest {

    @Test
    public void testFindById() {
        Long idDepartment = new Long(40);
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department department = entityManager.findById(Department.class, idDepartment);
        Assert.assertEquals(idDepartment, department.getId());
        String departmentName = "Human Resources";
        Long idLocation = new Long(2400);
        Assert.assertEquals(departmentName, department.getDepartmentName());
        Assert.assertEquals(idLocation, department.getLocation());
    }

    @Test
    public void testGetNextIdValue() {
        Long idNextValue = new Long(272);
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Long id = entityManager.getNextIdVal("departments", "department_id");
        Assert.assertEquals(idNextValue, id);
    }

    @Test
    public void testUpdate() throws IllegalAccessException, NoSuchFieldException, SQLException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department department = new Department();
        department.setDepartmentName("Departament 2x");
        department.setId(new Long(271));
        department.setLocation(new Long(1700));
        Department department1 = entityManager.update(department);
        Assert.assertEquals(department.getDepartmentName(), department1.getDepartmentName());

    }

    @Test
    public void testDelete() throws IllegalAccessException, NoSuchFieldException, SQLException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department department = new Department();
        department.setId(new Long(271));
        department.setLocation(new Long(1700));
        department.setDepartmentName("Department 2x");
        entityManager.delete(department);
       // Department d = entityManager.findById(Department.class, department.getId());
        Assert.assertTrue(entityManager.findById(Department.class, department.getId()) == null);

    }

    @Test
    public void testFindByParams() throws NoSuchFieldException, IllegalAccessException {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Location location = new Location();
        location.setId(1000L);
        Map<String, Object> map = new HashMap<>();
        map.put("city", "Roma");
        map.put("location_id", 1000);
        entityManager.findByParams(location.getClass(), map);
    }
}
