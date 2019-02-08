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
 * @author Doruntina
 */
@Entity
@Table(name = "Tools")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tools.findAll", query = "SELECT t FROM Tools t")
    , @NamedQuery(name = "Tools.findByToolID", query = "SELECT t FROM Tools t WHERE t.toolID = :toolID")
    , @NamedQuery(name = "Tools.findByEmri", query = "SELECT t FROM Tools t WHERE t.emri = :emri")})
public class Tools implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ToolID")
    private Integer toolID;
    @Column(name = "Emri")
    private String emri;

    public Tools() {
    }

    public Tools(Integer toolID) {
        this.toolID = toolID;
    }

    public Integer getToolID() {
        return toolID;
    }

    public void setToolID(Integer toolID) {
        this.toolID = toolID;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (toolID != null ? toolID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tools)) {
            return false;
        }
        Tools other = (Tools) object;
        if ((this.toolID == null && other.toolID != null) || (this.toolID != null && !this.toolID.equals(other.toolID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BLL.Tools[ toolID=" + toolID + " ]";
    }
    
}
