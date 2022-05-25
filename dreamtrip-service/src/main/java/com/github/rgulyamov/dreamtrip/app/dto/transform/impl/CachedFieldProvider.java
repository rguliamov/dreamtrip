package com.github.rgulyamov.dreamtrip.app.dto.transform.impl;

import com.github.rguliamov.dreamtrip.app.infra.util.ReflectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
/**
 * This class cached field names for each transformation pair
 *
 * @author Guliamov Rustam
 */
public class CachedFieldProvider extends FieldProvider {
    /**
     * Mapping between transformation pair(class names) and field list
     */
    private final Map<String, List<String>> cache;

    public CachedFieldProvider() {
        cache = new HashMap<>();
    }

    @Override
    public List<String> getFieldNames(Class<?> clazz1, Class<?> clazz2) {
        String key1 = clazz2.getSimpleName() + clazz1.getSimpleName();
        String key2 = clazz1.getSimpleName() + clazz2.getSimpleName();

        List<String> fields = cache.get(key1);
        if(fields == null)
            fields = cache.get(key2);

        if(fields == null) {
            fields = ReflectionUtil.findSimilarFields(clazz2, clazz1);
            cache.put(key1, fields);
        }

        return fields;
    }
}
