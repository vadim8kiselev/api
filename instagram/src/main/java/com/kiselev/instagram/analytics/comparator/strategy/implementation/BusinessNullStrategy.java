package com.kiselev.instagram.analytics.comparator.strategy.implementation;

import com.kiselev.instagram.analytics.comparator.strategy.BusinessStrategy;

import java.util.Collections;
import java.util.List;

public class BusinessNullStrategy implements BusinessStrategy {

    @Override
    public List<String> execute(Object object, Object tcejbo) {
        if (object == null && tcejbo != null) {
            return Collections.singletonList(
                    String.format(
                            "Added a new value [%s]",
                            tcejbo
                    )
            );
        }

        if (object != null && tcejbo == null) {
            return Collections.singletonList(
                    String.format(
                            "Deleted a value [%s]",
                            object
                    )
            );
        }

        throw new RuntimeException("Something bad is happened, please check me out");
    }
}
