/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SoftDevLab16;

/**
 *
 * @author Zsolti
 */
public interface IPayable {
    double lowTaxRate = 23;
    double highTaxRate = 42;
    double vatRate = 23.5;
    
    double getPaymentAmount();
}
