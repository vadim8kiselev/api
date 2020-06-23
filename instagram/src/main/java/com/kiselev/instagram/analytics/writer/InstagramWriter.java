package com.kiselev.instagram.analytics.writer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiselev.instagram.model.InstagramProfile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InstagramWriter {

    private final ObjectMapper MAPPER = new ObjectMapper();

    public InstagramWriter() {
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public String writeToFile(InstagramProfile profile) {
        String json = write(profile);

        try {
            String localDate = LocalDateTime
                    .now()
                    .format(
                            DateTimeFormatter.ofPattern("dd_MM_yyyy")
                    );

            String localDateTime = LocalDateTime
                    .now()
                    .format(
                            DateTimeFormatter.ofPattern("H_m_s")
                    );

            String fileName = String.format(
                    "%s/%s_%s.json",
                    localDate,
                    profile.getUsername(),
                    localDateTime
            );

            FileUtils.writeStringToFile(
                    new File(fileName),
                    json,
                    Charset.defaultCharset()
            );
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        return json;
    }

    public String write(InstagramProfile profile) {
        try {
            return MAPPER
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(profile);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }
}
