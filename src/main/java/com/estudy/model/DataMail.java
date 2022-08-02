package com.estudy.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataMail {
    private String to;
    private String subject;
    private String content;
    private Map<String, Object> props;
} 
