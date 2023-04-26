/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.milosz.cebula;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import pl.polsl.milosz.cebula.matrixcalculator2.model.*;
import pl.polsl.milosz.cebula.matrixcalculator2.exception.*;

/**
 * Class implements test for matrix calculator
 * @author Miłosz Cebula
 * @version 1.0
 */
public class CalculatorTest{
    /** Model of matrix calculator used during tests */
    private Model testModel;
    
    /**
     * Method prepares test model before each test
     */
    @BeforeEach
    public void setUp(){
        testModel = new Model();
    }
    
    /**
     * Unit tests for SquareCheck method
     */  
    @Test
    public void testSquareCheck() {
        try{
        testModel.matrixSquareCheck(1,2);
        fail("An exception should be thrown when matrix is not square");
        }catch(CalculatorException ex){
            
        }
        
        try{
            testModel.matrixSquareCheck(2, 2);
        }catch(CalculatorException ex){
            fail("There should no exception for those values");
        }
    }
    
        public void testSizeCheck() {
        try{
        testModel.matrixSizeCheck(-1,2);
        fail("An exception should be thrown when matrix size is less than zero");
        }catch(CalculatorException ex){
            
        }
        
        try{
            testModel.matrixSizeCheck(2, 2);
        }catch(CalculatorException ex){
            fail("There should no exception for those values");
        }
    }
            
    /**
     * Parameterized tests for SquareCheck method
     * @param row number of rows
     * @param col number of columns
     * @param bo bool that tell if there should be excpetion raised
     */  
    @ParameterizedTest
    @CsvSource({"1,2,1","3,4,1","2,2,0"})
    public void testSquareCheckParam(int row,int col,boolean bo){
        try{
            testModel.matrixSquareCheck(row,col);
            if(bo)
                fail("exception should be thrown");
        }catch(CalculatorException ex){
            
        }
    }

    /**
     * Unit tests for MatrixDeterminant method 
     */  
    @Test
    public void testMatrixDeterminant(){
        List<List<Double>> temp= new ArrayList<>();
        List<Double> temp1 = new ArrayList<>();
        
        temp1.add(1.0);
        temp1.add(3.0);
        temp1.add(5.0);
        temp.add(temp1);
        
        temp1 = new ArrayList<>();
        temp1.add(2.0);
        temp1.add(21.0);
        temp1.add(1.0);
        temp.add(temp1);
        
        temp1 = new ArrayList<>();
        temp1.add(10.0);
        temp1.add(15.0);
        temp1.add(1.0);
        temp.add(temp1);

        assertEquals(testModel.matrixDeterminant(temp),-870,"result is not correct");
    }
    
     /**
     * Unit tests for MatrixTransposition method 
     */     
    @Test
    public void testMatrixTransposition(){
        List<List<Double>> temp= new ArrayList<>();
        List<Double> temp1 = new ArrayList<>();
        
        temp1.add(1.0);
        temp1.add(2.0);
        temp1.add(5.0);
        temp.add(temp1);
        
        temp1 = new ArrayList<>();
        temp1.add(8.0);
        temp1.add(7.0);
        temp1.add(6.0);
        temp.add(temp1);
        
        temp1 = new ArrayList<>();
        temp1.add(5.0);
        temp1.add(4.0);
        temp1.add(3.0);
        temp.add(temp1);

        
         List<List<Double>> expected = new ArrayList<>();
         
         temp1 = new ArrayList<>();
         temp1.add(1.0);
         temp1.add(8.0);
         temp1.add(5.0);
         expected.add(temp1);
         
        temp1 = new ArrayList<>();
         temp1.add(2.0);
         temp1.add(7.0);
         temp1.add(4.0);
         expected.add(temp1); 
         
        temp1 = new ArrayList<>();
         temp1.add(5.0);
         temp1.add(6.0);
         temp1.add(3.0);
         expected.add(temp1);
         
         
        assertEquals(testModel.matrixTransposition(temp),expected,"result is not correct");
    }
}
