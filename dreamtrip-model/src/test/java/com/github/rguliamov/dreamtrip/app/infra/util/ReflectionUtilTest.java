package com.github.rguliamov.dreamtrip.app.infra.util;

import com.github.rguliamov.dreamtrip.app.infra.exception.ConfigurationException;
import com.github.rguliamov.dreamtrip.app.infra.util.helperClasses.A;
import com.github.rguliamov.dreamtrip.app.infra.util.helperClasses.B;
import com.github.rguliamov.dreamtrip.app.infra.util.helperClasses.C;
import com.github.rguliamov.dreamtrip.app.model.entity.base.AbstractEntity;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.City;
import com.github.rguliamov.dreamtrip.app.model.entity.geography.Coordinate;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * contatins unit-tests to check functionality of {@link ReflectionUtil} class
 *
 * @author Guliamov Rustam
 */
class ReflectionUtilTest {
    @Test
    public void testCreateInstanceSuccess() {
        A aInstance = ReflectionUtil.createInstance(A.class);
        assertTrue(aInstance != null);
        assertEquals(aInstance.getId(), 1);
    }

    @Test
    public void testCreateInstanceWithNullClassFailure() {
        assertThrows(NullPointerException.class, () ->
                ReflectionUtil.createInstance(null));
    }

    @Test
    public void testCreateInstanceWithClassWithoutDefaultConstructorFailure() {
        assertThrows(ConfigurationException.class, () ->
                ReflectionUtil.createInstance(C.class));
    }

    @Test
    public void testFindSimilarFieldsSuccess() {
        List<String> fieldsList = ReflectionUtil.findSimilarFields(A.class, B.class);
        assertEquals(fieldsList.size(), 3);
        assertTrue(fieldsList.contains("age"));
        assertTrue(fieldsList.contains("name"));
        assertTrue(fieldsList.contains("id"));
    }

    @Test
    public void testFindSimilarFieldsWithNullFailure() {
        assertThrows(NullPointerException.class,
                () -> ReflectionUtil.findSimilarFields(null, A.class));
    }

    @Test
    public void testFindSimilarFieldsReturnsEmptyList() {
        List<String> fieldsList = ReflectionUtil.findSimilarFields(A.class, C.class);

        assertTrue(fieldsList.isEmpty());
    }

    @Test
    public void testCopyFieldsSuccess() {
        A a = new A(1, "Rustam", 10, 1L);
        List<String> familiarFields = ReflectionUtil.findSimilarFields(A.class, B.class);
        B b = new B();
        ReflectionUtil.copyFields(b, a, familiarFields);

        assertEquals(b.getAge(), a.getAge());
        assertEquals(b.getName(), a.getName());
        assertEquals(b.getId(), a.getId());
    }

    @Test
    public void testCopyFieldsWithNullParameter1Failure() {
        A a = null;
        List<String> familiarFields = ReflectionUtil.findSimilarFields(A.class, B.class);
        B b = new B();
        assertThrows(NullPointerException.class, () ->
                ReflectionUtil.copyFields(b, a, familiarFields));
    }

    @Test
    public void testCopyFieldsWithNullParameter2Failure() {
        A a = new A(1, "Rustam", 10, 1L);
        List<String> familiarFields = ReflectionUtil.findSimilarFields(A.class, B.class);
        B b = null;
        assertThrows(NullPointerException.class, () ->
                ReflectionUtil.copyFields(b, a, familiarFields));
    }

    @Test
    public void testCopyFieldsWithNullParameter3Failure() {
        A a = new A(1, "Rustam", 10, 1L);
        List<String> familiarFields = null;
        B b = new B();
        assertThrows(NullPointerException.class, () ->
                ReflectionUtil.copyFields(b, a, familiarFields));
    }

    @Test
    public void testGetFieldsSuccess() {
        List<String> sumFields = convert(ReflectionUtil.getFields(City.class));

        List<String> cityFields = convert(Arrays.asList(City.class.getDeclaredFields()));
        List<String> abstractEntityFields = convert(Arrays.asList(AbstractEntity.class.getDeclaredFields()));

        System.out.println(sumFields);
        System.out.println(cityFields);
        System.out.println(abstractEntityFields);

        boolean flag = true;
        assertTrue(cityFields.stream().allMatch(t -> sumFields.contains(t)) && abstractEntityFields.stream().allMatch(t -> sumFields.contains(t)));


    }

    @Test
    void testFindFieldSuccess() {
        Field field = ReflectionUtil.getField(Coordinate.class, "x");
        assertEquals(field.getName(), "x");
    }

    @Test
    void testFindFieldNotFoundThrowsConfigurationException() {
        assertThrows(ConfigurationException.class, () -> ReflectionUtil.getField(Coordinate.class, "name"));
    }

    private static List<String> convert(List<Field> fields) {
        return fields.stream().map(f -> f.getName()).collect(Collectors.toList());
    }
}