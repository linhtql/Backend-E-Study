package com.estudy.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationCommentInfo {
	private int totalPages;
	private int totalElements;
	private int currentPage;
	private List<CommentInfo> content;
}
