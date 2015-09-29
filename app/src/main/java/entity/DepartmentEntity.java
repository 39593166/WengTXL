package entity;


/**
 * Created by Lenovo on 2015/9/18.
 */
public class DepartmentEntity {
    private int id;
    private String departmentName;
    private Integer pDepartmentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartmentEntity that = (DepartmentEntity) o;

        if (id != that.id) return false;
        if (departmentName != null ? !departmentName.equals(that.departmentName) : that.departmentName != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        return result;
    }

    public Integer getpDepartmentId() {
        return pDepartmentId;
    }

    public void setpDepartmentId(Integer pDepartmentId) {
        this.pDepartmentId = pDepartmentId;
    }
}
