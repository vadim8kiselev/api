package com.kiselev.instagram;

import com.kiselev.instagram.analytics.InstagramAnalytic;
import com.kiselev.instagram.api.InstagramAPI;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Instagram {

    private final InstagramAPI api;

    private final InstagramAnalytic analytic;

    public InstagramAPI api() {
        return api;
    }

    public InstagramAnalytic analytic() {
        return analytic;
    }
}
