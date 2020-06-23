package com.kiselev.instagram.analytics.comparator.strategy.implementation;

import com.kiselev.instagram.analytics.comparator.strategy.BusinessStrategy;

import java.util.Objects;

public class BusinessConstantStrategy implements BusinessStrategy<String> {

    @Override
    public String execute(Object object, Object tcejbo) {
        boolean equals = Objects.equals(object, tcejbo);
        if (!equals) {
            return String.format(
                    "Values [%s] and [%s] are not equal",
                    object,
                    tcejbo
            );
        }
        return null;
    }
}
