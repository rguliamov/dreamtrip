package com.github.rgulyamov.dreamtrip.app.dto.transform.impl;

import com.github.rguliamov.dreamtrip.app.infra.util.ReflectionUtil;

import java.util.List;

/**
 * Base functionality of the field preparation
 *
 * @author Guliamov Rustam
 */
public class FieldProvider {
    public List<String> getFieldNames(Class<?> clazz1, Class<?> clazz2) {
        return ReflectionUtil.findSimilarFields(clazz1, clazz2);
    }
}
