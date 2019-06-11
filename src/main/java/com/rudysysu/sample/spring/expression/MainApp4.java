package com.rudysysu.sample.spring.expression;

import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.boot.SpringApplication;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class MainApp4 {

    public MainApp4() {
        {
            ExpressionParser EL_PARSER = new SpelExpressionParser();
            Expression expression = EL_PARSER.parseExpression("T(java.lang.Math).random()");
            Object metas = expression.getValue();
            System.out.println(metas);
        }
        {
            ExpressionParser EL_PARSER = new SpelExpressionParser();
            Expression expression = EL_PARSER.parseExpression("T(java.util.Date).newInstance()");
            Object metas = expression.getValue();
            System.out.println(metas);
        }
        {
            ExpressionParser EL_PARSER = new SpelExpressionParser();
            Expression expression = EL_PARSER.parseExpression("T(java.util.GregorianCalendar).newInstance()");
            Object metas = expression.getValue();
            System.out.println(metas);
        }
        {
            ExpressionParser EL_PARSER = new SpelExpressionParser();
            Expression expression = EL_PARSER.parseExpression("T(java.util.GregorianCalendar).newInstance().get(T(java.util.Calendar).DAY_OF_WEEK)");
            Object metas = expression.getValue();
            System.out.println(metas);
        }
        
        {
            ExpressionParser EL_PARSER = new SpelExpressionParser();
            Expression expression = EL_PARSER.parseExpression("{1, 2, 3, 4}");
            Object metas = expression.getValue();
            System.out.println(metas);
        }
        
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApp4.class, args);
    }
}