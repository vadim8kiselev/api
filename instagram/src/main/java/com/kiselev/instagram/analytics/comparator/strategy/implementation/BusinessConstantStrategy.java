package com.kiselev.instagram.analytics.comparator.strategy.implementation;

import com.kiselev.instagram.analytics.comparator.strategy.BusinessStrategy;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BusinessConstantStrategy implements BusinessStrategy{

    @Override
    public List<String> execute(Object object, Object tcejbo) {
        boolean equals = Objects.equals(object, tcejbo);
        if (!equals) {
            return Collections.singletonList(
                    String.format(
                            "Values [%s] and [%s] are not equal",
                            object,
                            tcejbo
                    )
            );
        }
        return null;
    }
}
