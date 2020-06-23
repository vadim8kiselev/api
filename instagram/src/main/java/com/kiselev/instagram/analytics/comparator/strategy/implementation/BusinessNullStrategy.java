package com.kiselev.instagram.analytics.comparator.strategy.implementation;

import com.kiselev.instagram.analytics.comparator.strategy.BusinessStrategy;

public class BusinessNullStrategy implements BusinessStrategy<String> {

    @Override
    public String execute(Object object, Object tcejbo) {
        if (object == null && tcejbo != null) {
            return String.format(
                    "Added a new value [%s]",
                    tcejbo
            );
        }

        if (object != null && tcejbo == null) {
            return String.format(
                    "Deleted a value [%s]",
                    object
            );
        }

        throw new RuntimeException("Something bad is happened, please check me out");
    }
}
