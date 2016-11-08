package cn.tomleung.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.tomleung.dao.DAOFactory;
import cn.tomleung.dao.GoodDAO;
import cn.tomleung.entity.Good;

/**
 * Servlet implementation class GoodUpdateServlet
 */
@WebServlet("/GoodUpdateServlet")
public class GoodUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(true);
		Good good = (Good) session.getAttribute("good");
		GoodDAO goodDAO = DAOFactory.getGoodDAOInstance();
		String savePath = "/gpic";
		File saveDir = new File(savePath);
		if (!saveDir.exists()) {
			saveDir.mkdir();
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("UTF-8");
		sfu.setFileSizeMax(1024 * 1024 * 2);

		try {
			List<FileItem> items = sfu.parseRequest(request);
			for (FileItem item : items) {
				String fieldName = item.getFieldName();
				String value = item.getString();
				if (item.isFormField()) {
					value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
					switch (fieldName) {
					case "gname":
						good.setGname(value);
						break;
					case "gprice":
						good.setGprice(Double.parseDouble(value));
						break;
					default:
						break;
					}
				} else if (value == null || value.equals("")) {
					goodDAO.update(good);
				} else {
					String fileName = item.getName();
					int pos = fileName.lastIndexOf("\\");
					fileName = fileName.substring(pos + 1);
					if (!(fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith(".jpeg"))) {
						request.setAttribute("fail", "文件格式不正确");
						request.getRequestDispatcher("goodupdate.jsp").forward(request, response);
						return;
					}
					int pos2 = fileName.lastIndexOf(".");
					fileName = good.getGname() + fileName.substring(pos2);
					File file = new File(savePath, fileName);
					item.write(file);
					good.setGpic(savePath + "/" + fileName);
					goodDAO.update(good);
				}
			}
		} catch (FileSizeLimitExceededException e) {
			request.setAttribute("fail", "文件体积太大");
			request.getRequestDispatcher("goodupdate.jsp").forward(request, response);
			return;
		} catch (FileUploadException e) {
			request.setAttribute("fail", "文件上传失败");
			request.getRequestDispatcher("goodupdate.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			request.setAttribute("fail", "未知错误");
			request.getRequestDispatcher("goodupdate.jsp").forward(request, response);
			return;
		}
		response.sendRedirect("ShowAllServlet?flag=1");
		return;
	}

}
