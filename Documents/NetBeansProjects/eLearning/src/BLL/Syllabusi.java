/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Doruntina
 */
@Entity
@Table(name = "Syllabusi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Syllabusi.findAll", query = "SELECT s FROM Syllabusi s")
    , @NamedQuery(name = "Syllabusi.findBySyllabusID", query = "SELECT s FROM Syllabusi s WHERE s.syllabusID = :syllabusID")
    , @NamedQuery(name = "Syllabusi.findByPermbajtja", query = "SELECT s FROM Syllabusi s WHERE s.permbajtja = :permbajtja")})
public class Syllabusi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SyllabusID")
    private Integer syllabusID;
    @Column(name = "Permbajtja")
    private String permbajtja;
    @OneToMany(mappedBy = "syllabusiID")
    private Collection<KursiSyllabusi> kursiSyllabusiCollection;

    public Syllabusi() {
    }

    public Syllabusi(Integer syllabusID) {
        this.syllabusID = syllabusID;
    }

    public Integer getSyllabusID() {
        return syllabusID;
    }

    public void setSyllabusID(Integer syllabusID) {
        this.syllabusID = syllabusID;
    }

    public String getPermbajtja() {
        return permbajtja;
    }

    public void setPermbajtja(String permbajtja) {
        this.permbajtja = permbajtja;
    }

    @XmlTransient
    public Collection<KursiSyllabusi> getKursiSyllabusiCollection() {
        return kursiSyllabusiCollection;
    }

    public void setKursiSyllabusiCollection(Collection<KursiSyllabusi> kursiSyllabusiCollection) {
        this.kursiSyllabusiCollection = kursiSyllabusiCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (syllabusID != null ? syllabusID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Syllabusi)) {
            return false;
        }
        Syllabusi other = (Syllabusi) object;
        if ((this.syllabusID == null && other.syllabusID != null) || (this.syllabusID != null && !this.syllabusID.equals(other.syllabusID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.Syllabusi[ syllabusID=" + syllabusID + " ]";
    }
    
}
