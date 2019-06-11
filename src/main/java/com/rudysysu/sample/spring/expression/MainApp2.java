package com.rudysysu.sample.spring.expression;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.alibaba.fastjson.JSON;

public class MainApp2 {

    public static class Resource {
        public String resourceTypeKey;
        public String timestamp;
        public String value;
        public String url;

        @Override
        public String toString() {
            return "Resource [resourceTypeKey=" + resourceTypeKey + ", timestamp=" + timestamp + ", value=" + value
                    + ", url=" + url + "]";
        }
    }

    public static class SmartObject {
        public String instanceId;
        public String smartObjectTypeKey;
        public List<Resource> resources;

        @Override
        public String toString() {
            return "SmartObject [instanceId=" + instanceId + ", smartObjectTypeKey=" + smartObjectTypeKey
                    + ", resources=" + resources + "]";
        }
    }

    public static class Event {
        public List<SmartObject> smartObjects;
        public String name;

        @Override
        public String toString() {
            return "Event [smartObjects=" + smartObjects + ", name=" + name + "]";
        }
    }

    public MainApp2() throws FileNotFoundException, IOException {
        StringBuffer sb = new StringBuffer();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(MainApp2.class.getClassLoader().getResourceAsStream("measure.json")))) {
            while (br.ready()) {
                sb.append(br.readLine());
            }
        }
        String json = sb.toString();

        Event event = JSON.parseObject(json, Event.class);
        //System.out.println(event);

        ExpressionParser EL_PARSER = new SpelExpressionParser();

        Expression expression = EL_PARSER.parseExpression("smartObjects");

        // StandardEvaluationContext context = new
        // StandardEvaluationContext(jsonObject);

        List<SmartObject> smartObjects = (List<SmartObject>)expression.getValue(event);

        System.out.println(smartObjects);
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApp2.class, args);
    }
}