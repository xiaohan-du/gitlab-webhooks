package com.example.gitlabwebhooks.api.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class CommitsJson {
    private String id;
    private String message;
    private String title;
    private String timestamp;
    private String url;
    private ArrayList<String> added;
    private ArrayList<String> modified;
    private ArrayList<String> removed;
}
