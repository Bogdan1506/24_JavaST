package by.avdeev.pizzeria.dao;

import by.avdeev.pizzeria.entity.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        Goods goods = new Goods();
        goods.setId(id);
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT name, description, price, picture FROM goods WHERE id=?")) {
            statement.setString(1, String.valueOf(id)); //todo
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                goods.setName(rs.getString("name"));
                goods.setDescription(rs.getString("description"));
                goods.setPrice(Double.parseDouble(rs.getString("price")));
                goods.setPicture(rs.getString("picture"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    @Override
    public boolean delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM goods WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Goods entity) {
        return false;
    }

    @Override
    public boolean create(Goods entity) {
        String name = entity.getName();
        String description = entity.getDescription();
        double price = entity.getPrice();
        String picture = entity.getPicture();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO goods (name, description, price, picture) VALUES (?,?,?,?)")) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setDouble(3, price);
            statement.setString(4, picture);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Goods entity) {
        int id = entity.getId();
        String name = entity.getName();
        String description = entity.getDescription();
        double price = entity.getPrice();
        String picture = entity.getPicture();
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE goods SET name=?, description=?, price=?, picture=? WHERE id=?");) {
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setDouble(3, price);
            statement.setString(4, picture);
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
