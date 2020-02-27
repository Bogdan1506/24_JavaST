package by.avdeev.task12.dao;

public class DAOFactory {
    private static DAOFactory factory;
    private final MatrixDAO matrixDAO = new MatrixDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (factory == null) {
            synchronized (DAOFactory.class) {
                if (factory == null) {
                    factory = new DAOFactory();
                }
            }
        }
        return factory;
    }

    public MatrixDAO getMatrixDAO() {
        return matrixDAO;
    }
}
