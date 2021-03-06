package org.brunocvcunha.instagram4j.requests;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetStoryViewersResult;

@RequiredArgsConstructor
public class InstagramGetStoryViewersRequest2 extends InstagramGetRequest<InstagramGetStoryViewersResult> {

    private final String storyPk;

    private final String maxId;

    @Override
    public String getUrl() {
        String url = "media/" + storyPk
                + "/list_reel_media_viewer";

        if (StringUtils.isNotEmpty(maxId)) {
            url += "/?max_id=" + maxId;
        }
        return url;
    }

    @Override
    public InstagramGetStoryViewersResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramGetStoryViewersResult.class);
    }
}
