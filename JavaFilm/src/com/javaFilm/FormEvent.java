package com.javaFilm;

import java.util.EventObject;

public class FormEvent extends EventObject {
	private boolean isActive;
	
	public FormEvent(Object source) {
		super(source);
	}
	
	public FormEvent(Object source,boolean isActive) {
		super(source);
		this.isActive = isActive;
		
	}

	protected boolean isActive() {
		return isActive;
	}

	protected void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
