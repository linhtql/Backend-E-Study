package com.estudy.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessionInfor {
	private Long id;
	private String name;
	private String content;
	private String attachment;
	private Date createAt;
}
