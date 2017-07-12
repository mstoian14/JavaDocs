package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Mihaela.Stoian on 7/12/2017.
 */
@Table(name = "departments")
public class Department {
    @Id(name = "department_id")
    private long id;
    @Column(name = "department_name")
    private String departmentName;
    @Column(name = "location")
    private Location location;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(!(obj instanceof Department)) {
            return false;
        }

        Department d = (Department) obj;
        if(this.id != d.id) {
            return false;
        }

        if(this.departmentName.equals(d.departmentName) == false) {
            return false;
        }

        if(d.location.getId() != this.location.getId()) {
            return false;
        }

        if(d.location.getCity().equals(this.location.getCity()) == false) {
            return false;
        }

        if(d.location.getPostalCode().equals(this.location.getPostalCode()) == false) {
            return false;
        }

        if(d.location.getStateProvince().equals(this.location.getStateProvince()) == false) {
            return false;
        }

        if(d.location.getStreetAddress().equals(this.location.getStreetAddress()) == false) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.id + " " + this.departmentName;
    }
}
