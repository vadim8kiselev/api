package com.kiselev.instagram.analytics.comparator.strategy.implementation;

import com.kiselev.instagram.analytics.comparator.strategy.BusinessStrategy;

public class BusinessSkipStrategy implements BusinessStrategy<Void> {

    @Override
    public Void execute(Object object, Object tcejbo) {
        return null;
    }
}
