package com.github.rgulyamov.dreamtrip.app.dto.transform.impl;

import com.github.rguliamov.dreamtrip.app.infra.util.CommonUtils;
import com.github.rguliamov.dreamtrip.app.infra.util.ReflectionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Verifies functionality of the {@link CachedFieldProvider} unit
 *
 * @author Guliamov Rustam
 */
class CachedFieldProviderTest { 
    CachedFieldProvider provider;

    @BeforeEach
    void setup() {
        provider = new CachedFieldProvider();
    }

    @Test
    void testGetFieldNamesSuccess() {
        List<String> fields = provider.getFieldNames(Source.class, Destination.class);
        assertEquals(fields.size(), 1);
        assertEquals(fields.get(0), "test");
    }

    @Test
    void testGetFieldNamesCacheSuccess() {
        List<String> fields1 = provider.getFieldNames(Source.class, Destination.class);
        List<String> fields2 = provider.getFieldNames(Source.class, Destination.class);
        assertEquals(fields1, fields2);
    }

    @Test
    void testGetFieldNamesAreCached() {
        List<String> fields1 = provider.getFieldNames(Source.class, Destination.class);
        MockedStatic<ReflectionUtil> mock = Mockito.mockStatic(ReflectionUtil.class);
        mock.when(() -> ReflectionUtil.findSimilarFields(Source.class, Destination.class)).thenReturn(Collections.emptyList());
        List<String> fields2 = provider.getFieldNames(Source.class, Destination.class);
        assertFalse(fields2.isEmpty());
        assertEquals(fields1, fields2);
        mock.close();
    }
}

class Source {
    int test;
}

class Destination {
    int test;
}