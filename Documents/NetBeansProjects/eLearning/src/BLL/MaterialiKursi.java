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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Doruntina
 */
@Entity
@Table(name = "MaterialiKursi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaterialiKursi.findAll", query = "SELECT m FROM MaterialiKursi m")
    , @NamedQuery(name = "MaterialiKursi.findByMaterialiKursiID", query = "SELECT m FROM MaterialiKursi m WHERE m.materialiKursiID = :materialiKursiID")})
public class MaterialiKursi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MaterialiKursiID")
    private Integer materialiKursiID;
    @JoinColumn(name = "KursiID", referencedColumnName = "KursID")
    @ManyToOne
    private Kursi kursiID;
    @JoinColumn(name = "MaterialiID", referencedColumnName = "MaterialID")
    @ManyToOne
    private Materiali materialiID;

    public MaterialiKursi() {
    }

    public MaterialiKursi(Integer materialiKursiID) {
        this.materialiKursiID = materialiKursiID;
    }

    public Integer getMaterialiKursiID() {
        return materialiKursiID;
    }

    public void setMaterialiKursiID(Integer materialiKursiID) {
        this.materialiKursiID = materialiKursiID;
    }

    public Kursi getKursiID() {
        return kursiID;
    }

    public void setKursiID(Kursi kursiID) {
        this.kursiID = kursiID;
    }

    public Materiali getMaterialiID() {
        return materialiID;
    }

    public void setMaterialiID(Materiali materialiID) {
        this.materialiID = materialiID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (materialiKursiID != null ? materialiKursiID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialiKursi)) {
            return false;
        }
        MaterialiKursi other = (MaterialiKursi) object;
        if ((this.materialiKursiID == null && other.materialiKursiID != null) || (this.materialiKursiID != null && !this.materialiKursiID.equals(other.materialiKursiID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.MaterialiKursi[ materialiKursiID=" + materialiKursiID + " ]";
    }
    
}
