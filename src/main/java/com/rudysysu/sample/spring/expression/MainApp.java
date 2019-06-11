package com.rudysysu.sample.spring.expression;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class MainApp {
    public class Resource {
        public String id;
        public String val;

        public Resource(String id, String val) {
            this.id = id;
            this.val = val;
        }

        @Override
        public String toString() {
            return "Resource [id=" + id + ", val=" + val + "]";
        }
    }

    public class SmartObject {
        public String id;
        public String instanceId;
        public List<Resource> resources = new ArrayList<>();

        public SmartObject(String id, String instanceId) {
            this.id = id;
            this.instanceId = instanceId;
            resources.add(new Resource(id + "_" + "r1", "rv1"));
            resources.add(new Resource(id + "_" + "r2", "rv2"));
            resources.add(new Resource(id + "_" + "r3", "rv2"));
        }

        @Override
        public String toString() {
            return "SmartObject [id=" + id + ", resources=" + resources + "]";
        }
    }

    public class Event {
        public List<SmartObject> smartObjects = new ArrayList<>();

        public Event() {
            smartObjects.add(new SmartObject("s1", "i1"));
            smartObjects.add(new SmartObject("s2", "i2"));
            smartObjects.add(new SmartObject("s3", "i3"));
        }
    }

    public MainApp() {
        ExpressionParser EL_PARSER = new SpelExpressionParser();

        int i = 3;
        switch (i) {
        case 1: {
            Expression expression = EL_PARSER.parseExpression("This is #{event.meta['id']}",
                    new TemplateParserContext());
            Object metas = expression.getValue(new Event());
            System.out.println(metas);
            break;
        }
        case 2: {
            Expression expression = EL_PARSER.parseExpression("smartObjects.?[id == 's2' && instanceId == 'i2']");
            Object metas = expression.getValue(new Event());
            System.out.println(metas);
            break;
        }
        case 3: {
            Expression expression = EL_PARSER.parseExpression("smartObjects.?[id=='s2']");
            Object metas = expression.getValue(new Event());
            System.out.println(metas);
            
            expression = EL_PARSER.parseExpression("smartObjects.?[id=='s2'].![resources]");
            metas = expression.getValue(new Event());
            System.out.println(metas);
            
            expression = EL_PARSER.parseExpression("smartObjects.?[id=='s2'].![resources].?[id=='1234']");
            metas = expression.getValue(new Event());
            System.out.println(metas);
            break;
        }
        case 4: {
            Expression expression = EL_PARSER.parseExpression("smartObjects[0].id");
            Object metas = expression.getValue(new Event());
            System.out.println(metas);
            
            expression = EL_PARSER.parseExpression("smartObjects.![id]");
            metas = expression.getValue(new Event());
            System.out.println(metas);
            break;
        }
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }
}