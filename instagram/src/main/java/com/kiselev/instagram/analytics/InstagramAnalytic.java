package com.kiselev.instagram.analytics;

import com.kiselev.instagram.analytics.comparator.InstagramComparator;
import com.kiselev.instagram.analytics.reader.InstagramReader;
import com.kiselev.instagram.analytics.writer.InstagramWriter;
import com.kiselev.instagram.model.InstagramProfile;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class InstagramAnalytic {

    private final InstagramReader reader;

    private final InstagramWriter writer;

    private final InstagramComparator comparator;

    public InstagramProfile read(String username) {
        return reader.read(username);
    }

    public String write(InstagramProfile profile) {
        return writer.write(profile);
    }

    public String writeToFile(InstagramProfile profile) {
        return writer.writeToFile(profile);
    }

    public Map<String, Object> compare(InstagramProfile latestProfile,
                                       InstagramProfile actualProfile) {
        return comparator.compareProfiles(latestProfile, actualProfile);
    }
}
