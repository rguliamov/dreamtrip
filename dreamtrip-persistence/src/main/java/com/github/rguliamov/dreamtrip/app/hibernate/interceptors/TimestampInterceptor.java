package com.github.rguliamov.dreamtrip.app.hibernate.interceptors;

import com.github.rguliamov.dreamtrip.app.model.entity.base.AbstractEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.time.LocalDateTime;

/**
 * Initializes mandatory timestamp fields for the entities
 *
 * @author Guliamov Rustam
 */
public class TimestampInterceptor extends EmptyInterceptor {
    private static final long serialVersionUID = 6825201844366406253L;

    @Override
    public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        int idx = ArrayUtils.indexOf(propertyNames, AbstractEntity.FIELD_CREATED_AT);
        if(idx >= 0) {
            state[idx] = LocalDateTime.now();
            return true;
        }

        return false;
    }
}
