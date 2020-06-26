package com.kiselev.instagram.analytics.comparator.strategy.implementation;

import com.kiselev.instagram.analytics.comparator.strategy.BusinessStrategy;

import java.util.Collections;
import java.util.List;

public class BusinessSkipStrategy implements BusinessStrategy {

    @Override
    public List<String> execute(Object object, Object tcejbo) {
        return Collections.emptyList();
    }
}
