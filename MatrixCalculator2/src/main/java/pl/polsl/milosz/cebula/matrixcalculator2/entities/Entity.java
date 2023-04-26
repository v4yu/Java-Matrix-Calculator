/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.milosz.cebula.matrixcalculator2.entities;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Miłosz Cebula
 */
@javax.persistence.Entity
public class Entity implements Serializable {
    
    public String mode;
    public String key;
    public String[][] matrix;
    public String[][] matrixTransposed;
    public String matrixDeterminant;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setMode(String mode)
    {
        this.mode = mode;
    }
    public String getMode(String mode)
    {
        return mode;
    }
    public void setKey(String key)
    {
        this.key = key;
    }
    public String getKey(String key)
    {
        return key;
    }
    public void setMatrix(String[][] matrix)
    {
        this.matrix = matrix;
    }
    public String[][] getMatrix(String[][] matrix)
    {
        return matrix;
    }
    public void setMatrixTransposed(String[][] matrixTransposed)
    {
        this.matrixTransposed = matrixTransposed;
    }
    public String[][] getMatrixTransposed(String[][] matrixTransposed)
    {
        return matrixTransposed;
    }
    
     public void setMatrixDeterminant(String matrixDeterminant)
    {
        this.matrixDeterminant = matrixDeterminant;
    }
    public String getMatrixDeterminant(String matrixDeterminant)
    {
        return matrixDeterminant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Entity)) {
            return false;
        }
        Entity other = (Entity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.polsl.milosz.cebula.matrixcalculator2.model.Entity[ id=" + id + " ]";
    }
    
}
