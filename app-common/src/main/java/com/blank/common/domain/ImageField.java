package com.blank.common.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ImageField {
	@Column(name = "url_name")
	private String url;
	@Column(name = "image_name")
	private String name;
	@Column(name = "image_content")
	private byte[] content;
	@Column(name = "image_size")
	private long size;
	@Column(name = "image_type")
	private String type;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

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

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
