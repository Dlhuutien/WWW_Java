package fit.iuh.daoImpl;

import java.util.List;

import fit.iuh.dao.NhaCungCapDAO;
import fit.iuh.models.NhaCungCap;
import jakarta.persistence.EntityManager;

public class NhaCungCapDAOImpl implements NhaCungCapDAO{

	private EntityManager entityManager;
	public NhaCungCapDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public List<NhaCungCap> findAll() {
		try {
			return entityManager.createQuery("FROM NhaCungCap").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
