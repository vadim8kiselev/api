package com.kiselev.instagram.api.client;

import com.kiselev.instagram.api.credentials.InstagramCredentials;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.*;
import org.brunocvcunha.instagram4j.requests.payload.*;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class InstagramClient {

    private static final String OK = "ok";

    private static final String CLOSE = "close";

    private static final String SELECT_VERIFY_METHOD = "select_verify_method";

    private static final String CHALLENGE = "challenge";
    
    private static final String CHALLENGE_RESET = "challenge/reset";

    private static final String CHALLENGE_REQUIRED = "checkpoint_challenge_required";

    private final Instagram4j instagram;

    private final String username;

    public InstagramClient(InstagramCredentials credentials) {
        this.username = credentials.getUsername();

        this.instagram = Instagram4j.builder()
                .username(credentials.getUsername())
                .password(credentials.getPassword())
                .proxy(credentials.getProxy())
                .cookieStore(credentials.getCookieStore())
                .credentialsProvider(credentials.getCredentialsProvider())
                .build();

        instagram.setup();
    }

    public String whoAmI() {
        return username;
    }

    public void authenticate() {
        InstagramLoginResult loginResult = login();

        if (loginResult.getTwo_factor_info() != null) {
            loginResult = authenticateWithTwoFactors(loginResult);
        }

        if (loginResult.getChallenge() != null) {
            loginResult = authenticateWithChallenge(loginResult);
        }

        if (Objects.equals(loginResult.getStatus(), OK)) {
            System.out.println("Login success");
        } else {
            System.out.println("Login failed");
            throw new RuntimeException("Login failed");
        }
    }

    private InstagramLoginResult login() {
        try {
            return instagram.login();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private InstagramLoginResult login(String verificationCode) {
        try {
            return instagram.login(verificationCode);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private InstagramLoginResult authenticateWithTwoFactors(InstagramLoginResult instagramLoginResult) {
        String twoFactorVerificationCode = readFromKeyboard("Input two factors security code: ");
        String twoFactorIdentifier = instagramLoginResult.getTwo_factor_info().getTwo_factor_identifier();

        return login(twoFactorVerificationCode);
    }
    
    private InstagramLoginResult authenticateWithChallenge(InstagramLoginResult loginResult) {
        String challengeUrl = loginResult.getChallenge().getApi_path().substring(1);

        String resetChallengeUrl = challengeUrl.replace(CHALLENGE, CHALLENGE_RESET);
        InstagramGetChallengeResult getChallengeResult = request(
                new InstagramResetChallengeRequest(resetChallengeUrl)
        );

        if (Objects.equals(getChallengeResult.getAction(), CLOSE)) {
            getChallengeResult = request(
                    new InstagramGetChallengeRequest(challengeUrl)
            );
        }

        if (Objects.equals(getChallengeResult.getStep_name(), SELECT_VERIFY_METHOD)) {
            InstagramSelectVerifyMethodResult postChallengeResult = request(
                    new InstagramSelectVerifyMethodRequest(
                            challengeUrl, 
                            getChallengeResult.getStep_data().getChoice()
                    )
            );
            
            String securityCode = readFromKeyboard("Input challenge security code: ");
            
            return request(
                    new InstagramSendSecurityCodeRequest(challengeUrl, securityCode)
            );
        }

        throw new RuntimeException("Login failed");
    }
    
    private String readFromKeyboard(String message) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(message);
        return scanner.nextLine();
    }

    public <T extends StatusResult> T request(InstagramRequest<T> request) {
        try {
            return instagram.sendRequest(request);
        } catch (IOException exception) {
            System.err.println("Exception occurred while sending a request: " + exception.getMessage());
            // throw new RuntimeException(exception);
            return null;
        }
    }
}
