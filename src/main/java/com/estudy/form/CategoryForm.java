package com.estudy.form;

import com.estudy.entities.Role;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryForm {
	private String name;
	private MultipartFile avatar;
	private String description;
}
