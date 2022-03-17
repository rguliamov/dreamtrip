package com.github.rguliamov.dreamtrip.app.infra.util;

import com.github.rguliamov.dreamtrip.app.infra.util.exception.ConfigurationException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Creates an instance of the specified class using default constructor.
 * This method throws uncheck exception if creation fails.
 *
 * @author Guliamov Rustam
 */
public class ReflectionUtil {
    private ReflectionUtil() {
    }

    public static <T> T createInstance(Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor();
            T object = constructor.newInstance();

            return object;
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new ConfigurationException(e);
        }
    }

    /**
     * returns list of fields with identical names irregardles of their modifiers
     *
     * @param clazz1
     * @param clazz2
     * @return
     */
    public static List<String> findSimilarFields(Class<?> clazz1, Class<?> clazz2) {
        List<String> fieldList = new ArrayList<>();

        try {
            Field[] fields1 = clazz1.getDeclaredFields();
            Field[] fields2 = clazz2.getDeclaredFields();

            for(Field field1: fields1) {
                for(Field field2: fields2) {
                    if(field1.getName().equals(field2.getName())) {
                        fieldList.add(field1.getName());
                        break;
                    }
                }
            }
        } catch (SecurityException e) {
            throw new ConfigurationException(e);
        }

        return fieldList;
    }

    /**
     * Copy specified fields values from source to destination objects
     *
     * @param dest
     * @param src
     * @param fieldsList
     */
    public static void copyFields(Object dest, Object src, List<String> fieldsList) {
        try {
            for(String fieldName: fieldsList) {
                Field srcField = src.getClass().getField(fieldName);

                if(srcField == null)
                    continue;
                srcField.setAccessible(true);

                Field dstField = dest.getClass().getField(fieldName);
                if(dstField == null)
                    continue;
                dstField.setAccessible(true);

                dstField.set(dest, srcField.get(src));
            }
        } catch (NoSuchFieldException | IllegalAccessException | SecurityException e) {
            throw new ConfigurationException(e);
        }
    }
}
