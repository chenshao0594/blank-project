package com.blank.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6736864301576053524L;

	private String content;

	@ManyToOne
	@JoinColumn(name = "contribution_id")
	private Contribution contribution;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Contribution getContribution() {
		return contribution;
	}

	public void setContribution(Contribution contribution) {
		this.contribution = contribution;
	}

}
