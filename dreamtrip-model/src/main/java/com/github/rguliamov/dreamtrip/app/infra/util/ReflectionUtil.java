package com.github.rguliamov.dreamtrip.app.infra.util;

import com.github.rguliamov.dreamtrip.app.infra.exception.ConfigurationException;
import com.github.rguliamov.dreamtrip.app.infra.util.annotation.Ignore;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
            //creation with protected constructor
            Constructor<T> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            T object = constructor.newInstance();

            return object;
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new ConfigurationException(e);
        }
    }

    /**
     * Returns all declared fields of the specified classes and all superclasses
     *
     * @param clazz
     * @return
     */
    public static List<Field> getFields(Class<?> clazz) {

        List<Field> fields = new ArrayList<>();
        while(clazz != null) {
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }

        return fields;
    }

    /**
     * Returns class field by its name. This method supports base classes as
     * well
     *
     * @param clazz
     * @param name
     * @return
     */
    public static Field getField(Class<?> clazz, String name) {
        while(clazz != null) {
            try {
                return clazz.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            }
        }

        throw new ConfigurationException("No field " + name + " in the class " + clazz);
    }

    /**
     * returns list of fields with identical names irregardles of their modifiers
     *
     * @param clazz1
     * @param clazz2
     * @return
     */
    public static List<String> findSimilarFields(Class<?> clazz1, Class<?> clazz2) {
        Objects.requireNonNull(clazz1, "clazz1 not be null");
        Objects.requireNonNull(clazz2, "clazz1 not be null");


        try {
            List<String> fieldsName1 = getFields(clazz1).stream()
                    .filter(f -> !f.isAnnotationPresent(Ignore.class))
                    .map(f -> f.getName())
                    .collect(Collectors.toList());

            List<String> fieldsName2 = getFields(clazz2).stream()
                    .filter(f -> !f.isAnnotationPresent(Ignore.class))
                    .filter(f -> !Modifier.isStatic(f.getModifiers()) && !Modifier.isFinal(f.getModifiers()))
                    .map(f -> f.getName())
                    .filter(name -> fieldsName1.contains(name))
                    .collect(Collectors.toList());

            return fieldsName2;
        } catch (SecurityException e) {
            throw new ConfigurationException(e);
        }
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
                Field srcField = getField(src.getClass(), fieldName);

                if(srcField == null)
                    continue;
                srcField.setAccessible(true);

                Field dstField = getField(dest.getClass(), fieldName);
                if(dstField == null)
                    continue;
                dstField.setAccessible(true);

                dstField.set(dest, srcField.get(src));
            }
        } catch (IllegalAccessException | SecurityException e) {
            throw new ConfigurationException(e);
        }
    }
}
