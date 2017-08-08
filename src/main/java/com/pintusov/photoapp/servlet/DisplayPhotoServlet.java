package com.pintusov.photoapp.servlet;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project: photo_app
 * Created by: USER
 * Date: 08.08.17.
 */

@WebServlet(name = "DisplayPhotoServlet", urlPatterns = "/DisplayPhotoServlet")
public class DisplayPhotoServlet extends HttpServlet {
	@Inject
	PhotoAlbum photoAlbum;
	@Inject
	EditPhoto uploadBean;

	DisplayPhotoServlet () {}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String indexString = req.getParameter("photoid");
		byte[] photoData;
		if (indexString != null){
			long out = new Long (indexString.trim()).longValue();
			Photo photo = this.photoAlbum.getPhoto(out);
			photoData = photo.getData();
		} else {
			photoData = this.uploadBean.getPhotoData();
		}

		resp.setContentType("image/jpeg");
		try (ServletOutputStream sos = resp.getOutputStream()) {
			for (int i =0; i < photoData.length; i++) {
				sos.write(photoData[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
