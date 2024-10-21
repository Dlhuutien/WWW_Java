package fit.iuh.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import fit.iuh.dao.DienThoaiDAO;
import fit.iuh.daoImpl.DienThoaiDAOImpl;
import fit.iuh.models.DienThoai;
import fit.iuh.ultis.EntityManagerFactoryUtil;

/**
 * Servlet implementation class DanhSachDienThoaiNCCServlet
 */
@WebServlet(urlPatterns = { "/list", "/searchDienThoai" })
public class DanhSachDienThoaiNCCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EntityManagerFactoryUtil entityManagerFactory;
	private DienThoaiDAO dienThoaiDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DanhSachDienThoaiNCCServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		this.entityManagerFactory = new EntityManagerFactoryUtil();
		this.dienThoaiDAO = new DienThoaiDAOImpl(this.entityManagerFactory.getEnManager());
	}
    
    @Override
	public void destroy() {
		// TODO Auto-generated method stub
		this.entityManagerFactory.close();
		super.destroy();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action = request.getServletPath();
	        if ("/searchDienThoai".equals(action)) {
	            searchDienThoai(request, response);
	        } else {
	            request.setAttribute("dienThoai", dienThoaiDAO.findAll());
	            request.getRequestDispatcher("views/DanhSachDienThoaiNCC.jsp").forward(request, response);
	        }		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void searchDienThoai(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String tenDT = request.getParameter("tenDT");

	    List<DienThoai> searchResults = dienThoaiDAO.findByName(tenDT);

	    request.setAttribute("dienThoai", searchResults);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("views/DanhSachDienThoaiNCC.jsp");
	    dispatcher.forward(request, response);
	}

}
