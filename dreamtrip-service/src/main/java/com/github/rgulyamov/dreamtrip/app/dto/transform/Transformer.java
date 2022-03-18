package com.github.rgulyamov.dreamtrip.app.dto.transform;

import com.github.rguliamov.dreamtrip.app.model.entity.base.AbstractEntity;
import com.github.rgulyamov.dreamtrip.app.dto.entity.base.BaseDTO;

/**
 * Represent transformation engine to convert business entities into DTO objects
 *
 * @author Guliamov Rustam
 */
public interface Transformer {
    /**
     * Entity -> DTO
     *
     * @param entity
     * @param clazz
     * @param <T>
     * @param <P>
     * @return
     */
    <T extends AbstractEntity, P extends BaseDTO<T>> P transform(T entity, Class<P> clazz);

    /**
     * DTO -> Entity
     *
     * @param dto
     * @param clazz
     * @param <T>
     * @param <P>
     * @return
     */
    <T extends AbstractEntity, P extends BaseDTO<T>> T unTransform(P dto, Class<T> clazz);
}
