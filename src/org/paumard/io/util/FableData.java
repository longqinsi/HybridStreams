package org.paumard.io.util;

import org.paumard.io.model.Fable;

public class FableData {

	private Fable fable;
	private int offset, length;
	
	public FableData(Fable fable, int offset, int length) {
		this.fable = fable;
		this.offset = offset;
		this.length = length;
	}
	
	public Fable getFable() {
		return fable;
	}

	public void setFable(Fable fable) {
		this.fable = fable;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
}
