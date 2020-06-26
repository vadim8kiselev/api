package com.kiselev.instagram.analytics.comparator;

import com.kiselev.instagram.analytics.comparator.strategy.BusinessStrategy;
import com.kiselev.instagram.analytics.comparator.strategy.holder.BusinessStrategyHolder;
import com.kiselev.instagram.model.InstagramProfile;

import java.util.List;

public class InstagramComparator {

    public List<String> compareProfiles(InstagramProfile latestProfile,
                                        InstagramProfile actualProfile) {
        BusinessStrategy strategy = BusinessStrategyHolder.object();
        return strategy.execute(latestProfile, actualProfile);
    }
}
