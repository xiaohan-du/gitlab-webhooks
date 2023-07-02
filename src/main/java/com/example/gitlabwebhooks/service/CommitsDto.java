package com.example.gitlabwebhooks.service;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayList;

@Value
@AllArgsConstructor
public class CommitsDto {
    private String id;
    private String message;
    private String title;
    private String timestamp;
    private String url;
    private ArrayList<String> added;
    private ArrayList<String> modified;
    private ArrayList<String> removed;
}
