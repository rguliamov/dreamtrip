package com.github.rgulyamov.dreamtrip.app.dto.entity.base;

import com.github.rguliamov.dreamtrip.app.model.entity.base.AbstractEntity;

/**
 * Base class for all DTO classes
 *
 * @author Guliamov Rustam
 */
public abstract class BaseDTO<T extends AbstractEntity> {
    /**
     * Unique entity identifier
     */
    private long id;

    /**
     * Should be overridden in the derived classes if additional transformation
     * logic DOMAIN MODEL -> DTO is needed.
     * Overridden method should call super.transform()
     *
     * @param t
     */
    public void transform(T t) {
        id = t.getId();
    }

    /**
     * Should be overridden in the derived classes
     * if additional transformation logic DTO -> domain model is needed
     *
     * @param t
     * @return
     */
    public T unTransform(T t) {
        t.setId(id);
        return t;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
