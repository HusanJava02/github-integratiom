package com.example.githubintegration.util;


public class Utils {

    public static String replaceURLParameters(String url, String username, String repoName) {
        return url
                .replace("{USER_NAME}", username)
                .replace("{REPO_NAME}", repoName);
    }

    public static String replaceURLParameters(String url, String username) {
        return url
                .replace("{USER_NAME}", username);
    }
}
