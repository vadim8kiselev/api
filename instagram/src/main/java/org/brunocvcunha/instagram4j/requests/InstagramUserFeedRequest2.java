package org.brunocvcunha.instagram4j.requests;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;

@AllArgsConstructor
@RequiredArgsConstructor
public class InstagramUserFeedRequest2 extends InstagramGetRequest<InstagramFeedResult> {

    private final Long userId;

    private final String maxId;

    private Long minTimestamp;

    private Long maxTimestamp;

    @Override
    public String getUrl() {
        return "feed/user/" + userId +
                "/?max_id=" + maxId +
                "&min_timestamp=" + minTimestamp +
                "&max_timestamp=" + maxTimestamp +
                "&rank_token=" + api.getRankToken() +
                "&ranked_content=true&";
    }

    @Override
    public InstagramFeedResult parseResult(int statusCode, String content) {
        return parseJson(statusCode, content, InstagramFeedResult.class);
    }
}