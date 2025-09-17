package com.java.author;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.StringJoiner;


@Component("customKeyGenerator")
public class CustomKeyGenerator implements KeyGenerator
{

    @Override
    public Object generate(Object target, Method method, Object... params)
    {
        StringBuilder key = new StringBuilder();

        // Class name
        key.append(target.getClass().getSimpleName());

        key.append(":");

        // Method name
        key.append(method.getName());

        key.append(":");

        // Parameters
        if (params.length == 0)
        {
            key.append("no-params");
        }
        else
        {
            StringJoiner paramJoiner = new StringJoiner(",");
            for (Object param : params)
            {
                paramJoiner.add(param == null ? "null" : param.toString());
            }
            key.append(paramJoiner.toString());
        }

        return key.toString();
    }
}