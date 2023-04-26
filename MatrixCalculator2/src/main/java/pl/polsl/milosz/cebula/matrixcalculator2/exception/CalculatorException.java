/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.milosz.cebula.matrixcalculator2.exception;

/**
 *
 * CalculatorException class, it is extension of Exception class
 * 
 * @author Miłosz Cebula
 * @version 1.0
 */
public class CalculatorException extends Exception{
    /**
     * Constructor that creates exception with given message
     * @param str message
     */
    public CalculatorException(String str){
        super(str);
    }
}
