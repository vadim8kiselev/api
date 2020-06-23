package com.kiselev.instagram.analytics.comparator;

import com.kiselev.instagram.analytics.comparator.strategy.BusinessStrategy;
import com.kiselev.instagram.analytics.comparator.strategy.holder.BusinessStrategyHolder;
import com.kiselev.instagram.model.InstagramProfile;

import java.util.Map;

public class InstagramComparator {

    public Map<String, Object> compareProfiles(InstagramProfile latestProfile,
                                               InstagramProfile actualProfile) {
        BusinessStrategy<Map<String, Object>> strategy = BusinessStrategyHolder.object();
        return strategy.execute(latestProfile, actualProfile);
    }
}
