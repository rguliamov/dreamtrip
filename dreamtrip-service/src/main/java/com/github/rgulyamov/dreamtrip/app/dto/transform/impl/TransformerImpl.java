package com.github.rgulyamov.dreamtrip.app.dto.transform.impl;

import com.github.rguliamov.dreamtrip.app.infra.util.ReflectionUtil;
import com.github.rguliamov.dreamtrip.app.model.entity.base.AbstractEntity;
import com.github.rgulyamov.dreamtrip.app.dto.entity.base.BaseDTO;
import com.github.rgulyamov.dreamtrip.app.dto.transform.Transformer;

import java.util.List;
import java.util.Objects;

/**
 * Default implementation engine.
 *
 * @author Guliamov Rustam
 */
public class TransformerImpl implements Transformer {
    @Override
    public <T extends AbstractEntity, P extends BaseDTO<T>> P transform(T entity, Class<P> clazz) {
        Objects.requireNonNull(entity, "source object is not initialised");
        Objects.requireNonNull(clazz, "class isn't defined for transformation");

        P dto = ReflectionUtil.createInstance(clazz);
        List<String> familiarFields = ReflectionUtil.findSimilarFields(entity.getClass(), clazz);
        //System.out.println(familiarFields);
        ReflectionUtil.copyFields(dto, entity, familiarFields);
        dto.transform(entity);

        return dto;
    }

    @Override
    public <T extends AbstractEntity, P extends BaseDTO<T>> T unTransform(P dto, Class<T> clazz) {
        Objects.requireNonNull(dto, "source object is not initialised");
        Objects.requireNonNull(clazz, "class isn't defined for transformation");

        T entity = ReflectionUtil.createInstance(clazz);
        List<String> familiarFields = ReflectionUtil.findSimilarFields(dto.getClass(), clazz);
        ReflectionUtil.copyFields(dto, entity, familiarFields);
        dto.unTransform(entity);

        return entity;
    }
}
