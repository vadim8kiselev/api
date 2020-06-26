package com.kiselev.instagram.analytics.comparator.strategy.holder;

import com.google.common.collect.Maps;
import com.kiselev.instagram.analytics.comparator.strategy.BusinessStrategy;
import com.kiselev.instagram.analytics.comparator.strategy.implementation.*;
import com.kiselev.instagram.model.annotation.type.BusinessType;

import java.util.Map;

public class BusinessStrategyHolder {

    private static final Map<BusinessType, BusinessStrategy> STRATEGIES =
            Maps.newHashMap();

    static {
        STRATEGIES.put(BusinessType.NULL, new BusinessNullStrategy());
        STRATEGIES.put(BusinessType.SKIP, new BusinessSkipStrategy());
        STRATEGIES.put(BusinessType.CONSTANT, new BusinessConstantStrategy());
        STRATEGIES.put(BusinessType.TEXT, new BusinessTextStrategy());
        STRATEGIES.put(BusinessType.NUMBER, new BusinessNumberStrategy());
        STRATEGIES.put(BusinessType.OBJECT, new BusinessObjectStrategy());
    }

    public static BusinessStrategy strategy(BusinessType businessType) {
        return STRATEGIES.get(businessType);
    }

    public static BusinessStrategy nullValue() {
        return STRATEGIES.get(BusinessType.NULL);
    }

    public static BusinessStrategy skip() {
        return STRATEGIES.get(BusinessType.SKIP);
    }

    public static BusinessStrategy constant() {
        return STRATEGIES.get(BusinessType.CONSTANT);
    }

    public static BusinessStrategy text() {
        return STRATEGIES.get(BusinessType.TEXT);
    }

    public static BusinessStrategy number() {
        return STRATEGIES.get(BusinessType.NUMBER);
    }

    public static BusinessStrategy object() {
        return STRATEGIES.get(BusinessType.OBJECT);
    }
}
