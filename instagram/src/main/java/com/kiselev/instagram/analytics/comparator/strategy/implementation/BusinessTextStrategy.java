package com.kiselev.instagram.analytics.comparator.strategy.implementation;

import com.kiselev.instagram.analytics.comparator.strategy.BusinessStrategy;

import java.util.Objects;

public class BusinessTextStrategy implements BusinessStrategy<String> {

    @Override
    public String execute(Object object, Object tcejbo) {
        boolean equals = Objects.equals(object, tcejbo);
        if (!equals) {
            return String.format(
                    "Text value changed from [%s] to [%s]",
                    object,
                    tcejbo
            );
        }
        return null;
    }
}
