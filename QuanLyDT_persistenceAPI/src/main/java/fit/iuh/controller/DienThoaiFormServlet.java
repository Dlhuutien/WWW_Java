package fit.iuh.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import fit.iuh.dao.DienThoaiDAO;
import fit.iuh.dao.NhaCungCapDAO;
import fit.iuh.daoImpl.DienThoaiDAOImpl;
import fit.iuh.daoImpl.NhaCungCapDAOImpl;
import fit.iuh.models.DienThoai;
import fit.iuh.models.NhaCungCap;
import fit.iuh.ultis.EntityManagerFactoryUtil;

/**
 * Servlet implementation class DienThoaiFormServlet
 */
@WebServlet(urlPatterns = { "/dienthoaiform", "/dienthoaiform*" })
public class DienThoaiFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EntityManagerFactoryUtil entityManagerFactory;
	private DienThoaiDAO dienThoaiDAO;
	private NhaCungCapDAO nhaCungCapDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DienThoaiFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
   	public void init(ServletConfig config) throws ServletException {
   		// TODO Auto-generated method stub
   		super.init(config);
   		this.entityManagerFactory = new EntityManagerFactoryUtil();
   		this.dienThoaiDAO = new DienThoaiDAOImpl(this.entityManagerFactory.getEnManager());
   		this.nhaCungCapDAO = new NhaCungCapDAOImpl(this.entityManagerFactory.getEnManager());
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
		List<NhaCungCap> nhaCungCapList = nhaCungCapDAO.findAll();
        request.setAttribute("nhaCungCapList", nhaCungCapList);
        request.getRequestDispatcher("views/DienThoaiForm.jsp").forward(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		themDienThoai(request, response);
	}
	
	private void themDienThoai(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tenDT = request.getParameter("tenDT");
		String namSanXuat = request.getParameter("namSanXuat");
		String cauHinh = request.getParameter("cauHinh");
		String maNCC = request.getParameter("maNCC");
		NhaCungCap nhaCungCap = new NhaCungCap(Integer.parseInt(maNCC));
		String hinhAnh = request.getParameter("hinhAnh");

		DienThoai dienThoai = new DienThoai();
		dienThoai.setTenDT(tenDT);
		dienThoai.setNamSanXuat(Integer.parseInt(namSanXuat));
		dienThoai.setCauHinh(cauHinh);
		dienThoai.setNhaCungCap(nhaCungCap);
		
		dienThoai.setHinhAnh(hinhAnh);

		dienThoaiDAO.addDienThoai(dienThoai);

		response.sendRedirect(request.getContextPath() + "/list");
	}

}
