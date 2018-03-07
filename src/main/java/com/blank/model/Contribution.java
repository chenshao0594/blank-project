package com.blank.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "contributions")
public class Contribution extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3108308666380679073L;

	private String name;
	
	private String fileName;
	
	private String brief;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] content;
	
	private String contentType;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "contribution")
	private Set<Comment> comments;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	
	
	
	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	protected Set<Comment> getCommentsInternal() {
		if (this.comments == null) {
			this.comments = new HashSet<Comment>();
		}
		return this.comments;
	}
	
	public void addComment(Comment comment) {
		getCommentsInternal().add(comment);
		comment.setContribution(this);
	}
	
	
}
