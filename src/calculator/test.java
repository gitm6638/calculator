/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import helper.evaluation_function;

/**
 *
 * @author lib
 */
public class test
{
    public static void main(String args[])
    {
        String input="100.55*100.20+100";
        System.out.println(evaluation_function.symbol_input(input));
//        String postfix=evaluation_function.infix_to_post(input);
//        System.out.println(postfix);
//        float result=evaluation_function.evaluate(postfix);
//        System.out.println(result);
//        
    }
    
}
