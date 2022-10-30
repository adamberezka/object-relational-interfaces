/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;



/**
 *
 * @author LabHiber
 */
// <joined-subclass extends="model.Person" lazy="false" name="model.Employee" table="EMPLOYEES">
//<key foreign-key="FK_EMP_PER">
//      <column name="EMP_ID"/>
//    </key>
@Entity 
@PrimaryKeyJoinColumn(name="EMP_ID",foreignKey = @javax.persistence.ForeignKey(name="FK_EMP_PER"))
@Table(name="EMPLOYEES")
public class Employee extends Person implements Serializable{
// <many-to-one cascade="save-update" class="model.Address"
// column="ADR_ID" foreign-key="FK_EMP_ADR"
//   name="address" unique="false"/>
 @ManyToOne
 @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
 @JoinColumn(name="ADR_ID", foreignKey = @javax.persistence.ForeignKey(name = "FK_EMP_ADR"))

 private Address address;
 private String job;
//  <many-to-one cascade="save-update" class="model.Departement"
//  column="DEPT_ID" foreign-key="FK_EMP_DEP" name="department" unique="false"/>
 @ManyToOne
 @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
 @JoinColumn(name="DEPT_ID",foreignKey = @javax.persistence.ForeignKey(name = "FK_EMP_DEP"))
 
 private Department department;
 
//  <set cascade="save-update" lazy="true" name="children">
//      <key foreign-key="FK_EMP_CHILDREN">
//        <column name="EMP_ID"/>
//      </key>
//      <one-to-many class="model.Child"/>
//    </set>
 @OneToMany
 @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
 @JoinColumn(name="EMP_ID",foreignKey = @javax.persistence.ForeignKey(name = "FK_EMP_CHILDREN"))
 private Set<Child> children = new HashSet<>(3);

//  <set cascade="save-update" lazy="true" name="projects" table="EMP_PROJ">
//      <key foreign-key="FK_PROJ_EMP">
//        <column name="EMP_ID"/>
//      </key>
//      <many-to-many class="model.Project" column="PROJ_ID"/>
//    </set>
 @ManyToMany
 @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
 @JoinTable(name="EMP_PROJ",
    joinColumns=@JoinColumn(name="EMP_ID"),
    inverseJoinColumns=@JoinColumn(name="PROJ_ID"),
    foreignKey = @javax.persistence.ForeignKey(name="FK_EMP_PROJ"), 
    inverseForeignKey = @javax.persistence.ForeignKey(name="FK_PROJ_EMP"))
 private Set<Project> projects = new HashSet<>(3);

    public Employee() {
    }

    public Employee(String imie, String nazwisko, LocalDate dataUr) {
       super(imie, nazwisko, dataUr);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Child> getChildren() {
        return children;
    }

    public void setChildren(Set<Child> children) {
        this.children = children;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
@Override
    public String toString() {
      return (super.getName() + " "
              +super.getSurname() + "  " + super.getBirthDayS()
              +" stanowisko: "+ job);
    }
}
