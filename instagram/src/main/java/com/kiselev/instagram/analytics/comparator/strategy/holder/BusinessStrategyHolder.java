package com.kiselev.instagram.analytics.comparator.strategy.holder;

import com.google.common.collect.Maps;
import com.kiselev.instagram.analytics.comparator.strategy.BusinessStrategy;
import com.kiselev.instagram.analytics.comparator.strategy.implementation.*;
import com.kiselev.instagram.model.annotation.type.BusinessType;

import java.util.Map;

public class BusinessStrategyHolder {

    private static final Map<BusinessType, BusinessStrategy<?>> STRATEGIES =
            Maps.newHashMap();

    static {
        STRATEGIES.put(BusinessType.NULL, new BusinessNullStrategy());
        STRATEGIES.put(BusinessType.SKIP, new BusinessSkipStrategy());
        STRATEGIES.put(BusinessType.CONSTANT, new BusinessConstantStrategy());
        STRATEGIES.put(BusinessType.TEXT, new BusinessTextStrategy());
        STRATEGIES.put(BusinessType.NUMBER, new BusinessNumberStrategy());
        STRATEGIES.put(BusinessType.OBJECT, new BusinessObjectStrategy());
    }

    public static BusinessStrategy<?> strategy(BusinessType businessType) {
        return STRATEGIES.get(businessType);
    }

    @SuppressWarnings("unchecked")
    public static BusinessStrategy<String> nullValue() {
        return (BusinessStrategy<String>) STRATEGIES.get(BusinessType.NULL);
    }

    @SuppressWarnings("unchecked")
    public static BusinessStrategy<Void> skip() {
        return (BusinessStrategy<Void>) STRATEGIES.get(BusinessType.SKIP);
    }

    @SuppressWarnings("unchecked")
    public static BusinessStrategy<String> constant() {
        return (BusinessStrategy<String>) STRATEGIES.get(BusinessType.CONSTANT);
    }

    @SuppressWarnings("unchecked")
    public static BusinessStrategy<String> text() {
        return (BusinessStrategy<String>) STRATEGIES.get(BusinessType.TEXT);
    }

    @SuppressWarnings("unchecked")
    public static BusinessStrategy<String> number() {
        return (BusinessStrategy<String>) STRATEGIES.get(BusinessType.NUMBER);
    }

    @SuppressWarnings("unchecked")
    public static BusinessStrategy<Map<String, Object>> object() {
        return (BusinessStrategy<Map<String, Object>>) STRATEGIES.get(BusinessType.OBJECT);
    }
}
