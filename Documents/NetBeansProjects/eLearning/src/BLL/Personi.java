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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Doruntina Osaj
 */
@Entity
@Table(name = "Personi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personi.findAll", query = "SELECT p FROM Personi p")
    , @NamedQuery(name = "Personi.findByPersoniID", query = "SELECT p FROM Personi p WHERE p.personiID = :personiID")
    , @NamedQuery(name = "Personi.findByEmri", query = "SELECT p FROM Personi p WHERE p.emri = :emri")
    , @NamedQuery(name = "Personi.findByMbiemri", query = "SELECT p FROM Personi p WHERE p.mbiemri = :mbiemri")})
public class Personi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PersoniID")
    private Integer personiID;
    @Column(name = "Emri")
    private String emri;
    @Column(name = "Mbiemri")
    private String mbiemri;

    public Personi() {
    }

    public Personi(Integer personiID) {
        this.personiID = personiID;
    }

    public Integer getPersoniID() {
        return personiID;
    }

    public void setPersoniID(Integer personiID) {
        this.personiID = personiID;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personiID != null ? personiID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personi)) {
            return false;
        }
        Personi other = (Personi) object;
        if ((this.personiID == null && other.personiID != null) || (this.personiID != null && !this.personiID.equals(other.personiID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.Personi[ personiID=" + personiID + " ]";
    }
    
}
