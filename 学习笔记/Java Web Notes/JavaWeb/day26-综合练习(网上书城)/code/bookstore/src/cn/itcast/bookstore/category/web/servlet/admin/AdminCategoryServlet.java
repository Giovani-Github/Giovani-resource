package cn.itcast.bookstore.category.web.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminCategoryServlet extends BaseServlet {
	//依赖service层
	private CategoryService cateService = new CategoryService(); 
	
	//修改分类名称
	public String edit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.封装表单数据
		 * 2.调用service的方法，修改分类名称
		 * 3.调用findAll方法
		 * */
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		cateService.edit(category);
		return findAll(request, response);
	}
	
	//修改分类之前的加载
	public String editPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.得到cid
		 * 2.使用cid调用service的方法，得到category
		 * 3.保存到request中
		 * 4.转发到adminjsps/admin/category/mod.jsp
		 * */
		String cid = request.getParameter("cid");
		Category category = cateService.load(cid);
		request.setAttribute("category", category);
		return "f:/adminjsps/admin/category/mod.jsp";
	}
	
	//删除分类
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.获取cid
		 * 2.使用cid调用service方法
		 * 	抛出异常：保存异常信息到request，转发到adminjsps/msg.jsp
		 *	没有异常：调用findAll方法
		 * */
		String cid = request.getParameter("cid");
		try {
			cateService.delete(cid);
			return findAll(request, response);
		} catch(CategoryException e) {
			request.setAttribute("msg", e.getMessage());
			return "f:/adminjsps/msg.jsp";
		}
	}
	
	//添加分类
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.封装表单信息
		 * 2.补全uuid
		 * 3.调用service方法，完成添加
		 * 4.调用findAll方法
		 * */
		Category category = CommonUtils.toBean(request.getParameterMap(), Category.class);
		category.setCid(CommonUtils.uuid());
		cateService.add(category);
		return findAll(request, response);
	}
	
	//查看所有分类
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 1.调用service的findAll方法，得到所有分类List<Cartgory>
		 * 2.保存到request
		 * 3.转发到adminjsps/admin/crtegory/list.jsp
		 * */
		request.setAttribute("categoryList", cateService.findAll());
		return "f:/adminjsps/admin/category/list.jsp";
	}
}
