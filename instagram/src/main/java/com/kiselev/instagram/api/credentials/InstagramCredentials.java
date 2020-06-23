package com.kiselev.instagram.api.credentials;

import lombok.Builder;
import lombok.Data;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;

@Data
@Builder
public class InstagramCredentials {

    private String username;

    private String password;

    private HttpHost proxy;

    private CookieStore cookieStore;

    private CredentialsProvider credentialsProvider;
}
