/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author lib
 */
public class evaluation_function
{
    
   
    public static String  infix_to_post(String input)
    {
       int len=input.length();
       Stack st1=new Stack();
       String postfix="";
       for(int i=0;i<len;i++)
       {   
          char c=input.charAt(i);
          char ch;
          switch(c)
          {
             case ')':
                 if(!st1.empty())
                 {
                   do
                   {
                     ch=(char)st1.pop();
                     if(ch=='+' || ch=='-' || ch=='*' || ch=='/' || ch=='%')
                     postfix=postfix+ch;
                   }while(ch!='('&&!st1.empty());
                 }// discard left parenth
                  break;
            case '+':
            case '-':
            case '*':
            case '/':
            case '%':
            case '(':
            case '$':
                  if(!st1.empty())
                  {
                    ch=(char)st1.peek();
                   if(precedence_of_isp(ch)>=precedence_of_icp(c))
                   {
                      postfix=postfix+st1.pop();
                   }
                   st1.push(c);
                  }
                  else
                   st1.push(c);
                break;
            default:
                postfix=postfix+c;
         }
        
    }
    while(!st1.empty())
    {
        postfix=postfix+st1.pop();
    }
    return postfix;
  }
    
  public static int precedence_of_isp(char c1)
  { 
     int p=0;
     if(c1=='$')
         p=6;
     if(c1=='/' || c1=='*' || c1=='%')
         p=3;
     if(c1=='+' || c1=='-')
        p=2;
     if(c1=='(')
         p=0;
     if(c1==')')
         p=4;
     return p;
  }
  public static int precedence_of_icp(char c1)
  { 
     int p=0;
     if(c1=='$')
        p=6;
     if(c1=='/' || c1=='*' || c1=='%')
         p=3;
     if(c1=='+' || c1=='-')
        p=2;
     if(c1=='(')
         p=5;
     if(c1==')')
         p=4;
     return p;
  }
  public static boolean check_perenth(String input)
  {
      int len=input.length();
      Stack st1=new Stack();
      char c,ch;
      for(int i=0;i<len;i++)
      {
          c=input.charAt(i);
          switch(c)
          {
              case ')':
                  do
                  {
                     ch=(char)st1.pop();
                  }while(ch!='(' && !st1.empty());
                  break;
              case '(':
                 st1.push(c);
          }
      }
      return st1.empty(); 
    }
  
     public static float evaluate(String input)
     {
        int len=input.length();
        Stack st1=new Stack();
        float op1,op2;
        char c;
        for(int i=0;i<len;i++)
        {
           c=(char)input.charAt(i);
           if(c=='+' || c=='-'|| c=='*'|| c=='/'||c=='%')
           {
                 if(!st1.empty())
                 {
                     op1=(float)st1.pop();
                     op2=(float)st1.pop();
                     if(c=='+')
                         st1.push(op1+op2);
                     if(c=='-')
                         st1.push(op2-op1);
                     if(c=='*')
                         st1.push(op1*op2);
                     if(c=='/')
                         st1.push(op2/op1);
                     if(c=='%')
                         st1.push(op2%op1);
                    
                }
            }
            else
              st1.push(c-'0');
      }
      return (float)st1.pop();
    }
    
     public static String symbol_input(String input)
     {
         int len=input.length();
         Stack st1=new Stack();
         Stack st2=new Stack();
         String symbol="";
         Queue q1;
        Object c,peek;
         int k=1;
         for(int i=0;i<len;i++)
         {
             c=input.charAt(i);
             System.out.println(c);
             if((char)c-'0'>=0 || (char)c-'0'<=9|| (char)c=='.')
             {
                if((char)c-'0'>=0 || (char)c-'0'<=9 )
                {
                    if(!st1.empty())
                    {
                       peek=st1.peek();
                      if((char)peek=='+'|| (char)peek=='-'||(char)peek=='*'||(char)peek=='/'||(char)peek=='%')
                       st1.push(c);
                      else
                      st1.push((char)st1.pop()*10+(char)c-'0');
                    }
                    else
                      st1.push(c);    
                }
                else
                {
                    while(true)
                    {
                      c=input.charAt(++i);
                      if((char)c=='+'|| (char)c=='-'||(char)c=='*'||(char)c=='/'||(char)c=='%')
                      { i--;
                          break;
                      }
                      int p=(10)^(k++);
                      if(!st1.empty())
                         st1.push((float)st1.pop()+((char)c-'0')/p);
                      else
                         st1.push(((char)c-'0')/p);
                    }
                }
            }
            else
            {     
                k=1;
                st1.push(c);
            } 
        }     
        do
        {
            System.out.println(st1.peek());
            st2.push(st1.pop());
        }while(!st1.empty());
        char s='a';
      do
      {
         char obj=(char)st2.peek();
        if(obj=='+'||obj=='-'||obj=='*'||obj=='/'||obj=='%')
             symbol+=st2.pop();
        else
           symbol+=s++;
      }while(!st2.empty());
     return symbol;      
     }
}
