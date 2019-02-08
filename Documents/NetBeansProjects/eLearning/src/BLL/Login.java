/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Doruntina
 */
@Entity
@Table(name = "Login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Login.findAll", query = "SELECT l FROM Login l")
    , @NamedQuery(name = "Login.findByLoginID", query = "SELECT l FROM Login l WHERE l.loginID = :loginID")
    , @NamedQuery(name = "Login.findByUsername", query = "SELECT l FROM Login l WHERE l.username = :username")
    , @NamedQuery(name = "Login.findByPassword", query = "SELECT l FROM Login l WHERE l.password = :password")})
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(generator = "InvSeq")
    @SequenceGenerator(name = "InvSeq", sequenceName = "INV_SEQ", allocationSize = 1)
    @Column(name = "LoginID")
    private Integer loginID;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "StudentID", referencedColumnName = "StudentID")
    @ManyToOne
    private Studenti studentID;

    public Login() {
    }

    public Login(Integer loginID) {
        this.loginID = loginID;
    }

    public Integer getLoginID() {
        return loginID;
    }

    public void setLoginID(Integer loginID) {
        this.loginID = loginID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Studenti getStudentID() {
        return studentID;
    }

    public void setStudentID(Studenti studentID) {
        this.studentID = studentID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loginID != null ? loginID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Login)) {
            return false;
        }
        Login other = (Login) object;
        if ((this.loginID == null && other.loginID != null) || (this.loginID != null && !this.loginID.equals(other.loginID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.Login[ loginID=" + loginID + " ]";
    }
    
}
