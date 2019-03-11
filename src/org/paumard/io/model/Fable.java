package org.paumard.io.model;

public class Fable {
	private String title;
	private String text;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void addText(String text) {
		if (this.text == null) {
			this.text = text;
		} else {
			this.text += "\n" + text;
		}
	}

	@Override
	public String toString() {
		return "Fable [title=" + title + ", text=" + text + "]";
	}
}
