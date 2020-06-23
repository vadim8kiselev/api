package com.kiselev.instagram.utils;

import com.kiselev.instagram.model.InstagramPhoto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class InstagramUtils {

    public static <T> Stream<T> safeStream(Collection<T> collection) {
        return collection == null
                ? Stream.empty()
                : collection.stream();
    }

    public static String dateAndTime(Long timestamp) {
        return LocalDateTime
                .ofInstant(Instant.ofEpochMilli(timestamp * 1000), ZoneId.systemDefault())
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static InstagramPhoto selectMainPhoto(List<InstagramPhoto> photos) {
        return photos.stream()
                .max((first, second) -> {
                    int firstScale = first.getHeight() * first.getWidth();
                    int secondScale = second.getHeight() * second.getWidth();
                    return Integer.compare(firstScale, secondScale);
                })
                .orElse(null);
    }
}
