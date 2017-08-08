package com.pintusov.photoapp.servlet;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Project: EETestApp
 * Created by: USER
 * Date: 31.07.17.
 */


public class PhotoTag extends SimpleTagSupport {
	private int index;
	private int width;
	private int height;

	public void setIndex(int index) {
		this.index = index;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
		out.println("<a href='photo.jsp?photo="+index+"'>");
		out.println("<img = src='photo.jsp?photo="+index+"' alt='photo'" +
				"height='"+this.height+"' width='"+this.width+"'>");
		out.println("</a>");
	}
}
