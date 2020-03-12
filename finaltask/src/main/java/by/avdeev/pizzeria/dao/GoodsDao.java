package by.avdeev.pizzeria.dao;

import by.avdeev.pizzeria.entity.Goods;
import by.avdeev.pizzeria.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao extends AbstractDAO<Goods> {
    public GoodsDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Goods> findAll() throws DAOException {
        List<Goods> goods = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM goods";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String picture = rs.getString("picture");
                Goods tempGoods = new Goods(id, name, description, price, picture);
                goods.add(tempGoods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public Goods findEntityById(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(Goods entity) {
        return false;
    }

    @Override
    public boolean create(Goods entity) {
        return false;
    }

    @Override
    public Goods update(Goods entity) {
        return null;
    }
}
