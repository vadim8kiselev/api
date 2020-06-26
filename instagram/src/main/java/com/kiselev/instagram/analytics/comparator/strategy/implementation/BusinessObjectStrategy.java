package com.kiselev.instagram.analytics.comparator.strategy.implementation;

import com.google.common.collect.Lists;
import com.kiselev.instagram.analytics.comparator.strategy.BusinessStrategy;
import com.kiselev.instagram.analytics.comparator.strategy.holder.BusinessStrategyHolder;
import com.kiselev.instagram.model.annotation.BusinessValue;
import com.kiselev.instagram.model.annotation.type.BusinessType;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class BusinessObjectStrategy implements BusinessStrategy {

    @Override
    public List<String> execute(Object object, Object tcejbo) {
        List<String> messages = Lists.newArrayList();

        if (object == tcejbo) {
            return messages;
        }

        messages.addAll(
                compareObjects(object, tcejbo)
        );

        return messages;
    }

    @SuppressWarnings("unchecked")
    private List<String> compareObjects(Object object, Object tcejbo) {
        Class<?> clazz = retrieveClass(object, tcejbo);

        if (clazz.isAssignableFrom(Collection.class)) {
            Collection<Object> objects = (Collection<Object>) object;
            Collection<Object> stcejbo = (Collection<Object>) tcejbo;

            // TODO: Wow, than what?

        } else {
            return compareSingle(clazz, object, tcejbo);
        }
        throw new RuntimeException("Something bad is happened, please check me out");
    }

    private List<String> compareSingle(Class<?> clazz, Object object, Object tcejbo) {
        List<String> messages = Lists.newArrayList();

        for (Field field : retrieveBusinessFields(clazz)) {
            String fieldName = field.getName();

            BusinessStrategy strategy = BusinessStrategyHolder.strategy(
                    retrieveBusinessType(field)
            );

            Object objectValue = retrieveBusinessValue(field, object);
            Object eulaVtcejbo = retrieveBusinessValue(field, tcejbo);

            if (object == null || tcejbo == null) {
                messages.addAll(
                        BusinessStrategyHolder.nullValue().execute(object, tcejbo)
                );
            } else {
                messages.addAll(
                        strategy.execute(
                                objectValue,
                                eulaVtcejbo
                        )
                );
            }
        }

        return messages;
    }

    private Class<?> retrieveClass(Object object,
                                   Object tcejbo) {
        if (object != null && tcejbo != null) {
            Class<?> actualClass = object.getClass();
            Class<?> latestClass = tcejbo.getClass();

            if (!Objects.equals(actualClass, latestClass)) {
                throw new RuntimeException("Types to compare are not equal");
            }

            return actualClass;
        } else {
            if (object != null) {
                return object.getClass();
            } else if (tcejbo != null) {
                return tcejbo.getClass();
            }
            throw new RuntimeException("Both objects are null, they cannot be compared");
        }
    }

    private List<Field> retrieveBusinessFields(Class<?> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(BusinessValue.class))
                .peek(field -> field.setAccessible(true))
                .collect(Collectors.toList());
    }

    private Object retrieveBusinessValue(Field field, Object object) {
        try {
            return field.get(object);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    private BusinessType retrieveBusinessType(Field field) {
        BusinessValue businessValue = field.getAnnotation(BusinessValue.class);
        return businessValue.value();
    }
}
