package com.github.rguliamov.dreamtrip.app.model.infra.util;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

/**
 * @author Guliamov Rustam
 */
public class CommonUtils {
    private CommonUtils() {
    }

    /**
     * returns not-null unmodifiable copy of the source set
     *
     * @param source
     * @param <T>
     * @return
     */
    public static <T> Set<T> getSafeSet(Set<T> source) {
        return Collections.unmodifiableSet(Optional.ofNullable(source).orElse(Collections.emptySet()));
    }
}
