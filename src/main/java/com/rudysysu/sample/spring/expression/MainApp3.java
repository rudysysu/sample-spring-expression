package com.rudysysu.sample.spring.expression;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class MainApp3 {

    public static void main(String[] args) {
        Inventor i1 = new Inventor("ant", new Date(), "cn");
        Inventor i2 = new Inventor("egg", new Date(), "en");
        Inventor i3 = new Inventor("grass", new Date(), "us");

        List<Inventor> list = new ArrayList<Inventor>();
        list.add(i1);
        list.add(i2);
        list.add(i3);

        SpelExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("list", list);
        
        @SuppressWarnings("unchecked")
        List<Inventor> value = (List<Inventor>) parser.parseExpression("#list.![name]").getValue(context);
        System.out.println(value);

    }

}
