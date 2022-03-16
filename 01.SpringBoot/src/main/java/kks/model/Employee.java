package kks.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employees")
public class Employee implements Serializable {
    
    @Id // Το όρίζω σαν Primary Key.
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @Column(name="first_name") // to MySQL
    private String firstname;  // to React
    
    @Column(name="last_name") // to MySQL    
    private String lastname;  // to React
    
    @Column(name="email_id") // to MySQL
    private String emailId;  // to React

    
    public Employee() {
        
    }

    public Employee(String firstname, String lastname, String emailId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailId = emailId;
    }    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    
    
}
