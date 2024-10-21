package fit.iuh.daoImpl;

import java.util.List;

import fit.iuh.dao.DienThoaiDAO;
import fit.iuh.models.DienThoai;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DienThoaiDAOImpl implements DienThoaiDAO{
	
	private EntityManager entityManager;
	public DienThoaiDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<DienThoai> findAll() {
		try {
			return entityManager.createQuery("FROM DienThoai").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DienThoai getById(int id) {
		try {
			DienThoai dienThoai = entityManager.find(DienThoai.class, id);
			return dienThoai;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DienThoai addDienThoai(DienThoai dt) {
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			entityManager.persist(dt);
			transaction.commit();
			return dt;
		} catch (Exception e) {
			e.printStackTrace();
			
			if(transaction!=null && transaction.isActive()) {
				transaction.rollback();
			}
		}
		
		return null;
	}

	@Override
	public boolean remove(int maDT) {
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();
			DienThoai dienThoai = entityManager.find(DienThoai.class, maDT);
			if (dienThoai != null) {
				entityManager.remove(entityManager.contains(dienThoai) ? dienThoai : entityManager.merge(dienThoai));
			}
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if(transaction!=null && transaction.isActive()) {
				transaction.rollback();
			}
		}
		return false;
	}


	@Override
	public List<DienThoai> findByName(String tenDT) {
		try {
	        String query = "FROM DienThoai WHERE tenDT LIKE :tenDT";
	        return entityManager.createQuery(query, DienThoai.class)
	                            .setParameter("tenDT", "%" + tenDT + "%")  // Sử dụng wildcard để tìm kiếm theo từ khóa
	                            .getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
