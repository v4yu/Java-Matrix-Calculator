/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.milosz.cebula.matrixcalculator2.model;

import java.util.ArrayList;
import java.util.List;
import pl.polsl.milosz.cebula.matrixcalculator2.exception.CalculatorException;

/**
 * 
 * Class implements matrix operations and validation
 * @author Miłosz Cebula
 * @version 1.0
 */
public class Model {
    /**
     * Methods checks if matrix is square one
     * @param numberRows amount of rows in matrix
     * @param numberColums amount of columns in matrix
     * @throws CalculatorException if matrix is not square
     */
    public void matrixSquareCheck(int numberRows, int numberColums) throws CalculatorException{
        if(numberRows!= numberColums){
            throw new CalculatorException("Matrix is not the square one");
        }
    }
    
    /**
     * Methods checks if matrix size is valid
     * @param numberRows amount of rows in matrix
     * @param numberColums amount of columns in matrix
     * @throws CalculatorException if matrix size is invalid 
     */
    public void matrixSizeCheck(int numberRows, int numberColums) throws CalculatorException{
        if(numberRows <=0 || numberColums <= 0)
            throw new CalculatorException("Matrix size cannot be less than 1");
    }
   
    /**
     * Methods creates submatrix for the purpose of minor method
     * @param inputMatrix matrix from which new submatrix will be created
     * @param skipRow row number which will be ignored
     * @param skipCol column number which will be ignored
     * @param currentSize size of inputMatrix
     * @return new submatrix 
     */
    private  List<List<Double>> matrixGetCofactor( List<List<Double>> inputMatrix, int skipRow, int skipCol, int currentSize){
         int i = 0, j = 0;
         //int tempArray = new int[currentSize-1][currentSize-1];
          List<List<Double>> resultArray = new  ArrayList<>();
          List<Double> tempArray = new  ArrayList<>();
         
 
        for (int row = 0; row < currentSize; row++) {
            tempArray = new ArrayList<>();
            for (int col = 0; col < currentSize; col++) {

                if (row != skipRow && col != skipCol) {
                    //tempArray[i][j++] = inputMatrix[row][col];
                    tempArray.add(inputMatrix.get(row).get(col));
                    j++;
                    if (j == currentSize - 1) {
                        j = 0;
                        i++;
                        resultArray.add(tempArray);
                    }
                }
            }
        }
        return resultArray;
    }


     /**
     * Methods calculates matrix determinant
     * @param mat matrix for which determinant is calculated
     * @return matrix determinant
     */   
    public Double matrixDeterminant(List<List<Double>> mat)
 {       
        Double result = 0.0;
        int n = mat.size();

        if (n == 1)
            return mat.get(0).get(0);
 
        int sign = 1;

        for (int f = 0; f < n; f++) {
            List<List<Double>> temp1 = matrixGetCofactor(mat, 0, f, n );
            result += sign * mat.get(0).get(f)* matrixDeterminant(temp1);
 
            sign = -sign;
        }
        return result;
    }
    
     /**
     * Methods performs matrix transposition
     * @param mat matrix which will be transpositioned
     * @return tranpositioned matrix
     */ 
    public List<List<Double>> matrixTransposition( List<List<Double>> mat){
    List<List<Double>> result = new ArrayList<>();
    for(int i = 0 ; i < mat.size(); i++){
        result.add(new ArrayList<>());
        for (int j = 0 ; j < mat.get(0).size(); j++){
            result.get(i).add(mat.get(j).get(i));
        }
    }
    return result;
    };

}