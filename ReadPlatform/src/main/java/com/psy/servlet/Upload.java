package com.psy.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Upload
 */
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		//为解析类提供配置信息
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//创建解析类的实例
		ServletFileUpload sfu = new ServletFileUpload();
		//开始解析,限制每个上传文件的最大长度 
		sfu.setFileSizeMax(1024*400);
		//每个表单域中数据会封装到一个对应的FileItem对象上
		try {
			List<FileItem> items = sfu.parseRequest(request);
			//区分表单域
			for(int i = 0;i<items.size();i++){
				FileItem item = items.get(i);
				//isFormField判断FileItem对象里面封装的数据是一个普通文本表单字段，还是一个文件表单字段
				if(!item.isFormField()){
					ServletContext sctx = getServletContext();
					//获得存放文件的物理路径
					//upload下的某个文件夹，得到当前在线的用户，找到对应的文件夹
					String path = sctx.getRealPath("/upload");
					System.out.println(path);
					//得到字段name属性的值
					String fileName = item.getFieldName();
					//得到file字段的文件名全路径名，如果不是file字段，为null 
				    String nameAll = item.getName();
				    System.out.println(fileName);
				    //该方法在
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
