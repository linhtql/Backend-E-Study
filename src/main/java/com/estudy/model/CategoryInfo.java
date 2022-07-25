package com.estudy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryInfo {
	private Long id;
	private String name;
	private String avatar;
	private String description;
}
