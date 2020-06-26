package com.kiselev.instagram.analytics.comparator.strategy.implementation;

import com.kiselev.instagram.analytics.comparator.strategy.BusinessStrategy;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BusinessTextStrategy implements BusinessStrategy {

    @Override
    public List<String> execute(Object object, Object tcejbo) {
        boolean equals = Objects.equals(object, tcejbo);
        if (!equals) {
            return Collections.singletonList(
                    String.format(
                            "Text value changed from [%s] to [%s]",
                            object,
                            tcejbo
                    )
            );
        }
        return null;
    }
}
