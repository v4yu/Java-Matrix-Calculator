/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.milosz.cebula.matrixcalculator2.model;

/**
 *
 * @author Miłosz Cebula
 */
public class Jdbc {
    public String mode;
    public String key;
    public String[][] matrix;
    public String[][] matrixTransposed;
    public String matrixDeterminant;
    
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
    
}
