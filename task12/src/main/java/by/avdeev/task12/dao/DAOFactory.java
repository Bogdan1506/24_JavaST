package by.avdeev.task12.dao;

public class DAOFactory {
    private static DAOFactory daoFactory = new DAOFactory();
    private final MatrixDAO matrixDAO = new MatrixDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory;
    }

    public MatrixDAO getMatrixDAO() {
        return matrixDAO;
    }
}
