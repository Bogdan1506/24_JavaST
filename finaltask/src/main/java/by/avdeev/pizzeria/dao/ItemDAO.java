//package by.avdeev.pizzeria.dao;
//
//import by.avdeev.pizzeria.entity.Dough;
//import by.avdeev.pizzeria.entity.Goods;
//import by.avdeev.pizzeria.entity.Item;
//import by.avdeev.pizzeria.entity.Size;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ItemDAO extends AbstractDAO<Item> {
//    public ItemDAO(Connection connection) {
//        super(connection);
//    }
//
//    @Override
//    public List<Item> findAll() throws DAOException {
//        List<Item> items = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            String sql = "SELECT item.id, item.goods_id, goods.name, goods.picture, goods.price, dough.name, size.name " +
//                    "FROM item JOIN dough ON dough.id = item.dough_id JOIN size ON size_id = size.id JOIN goods ON goods.id = item.goods_id";
//            ResultSet rs = statement.executeQuery(sql);
//            while (rs.next()) {
//                int id = rs.getInt("item.id");
//                int goodsId = rs.getInt("item.goods_id");
//                String goodsName = rs.getString("goods.name");
//                String goodsPicture = rs.getString("goods.picture");
//                double goodsPrice = rs.getDouble("goods.price");
//                Dough dough = Dough.valueOf(rs.getString("dough.name").toUpperCase());
//                Size size = Size.valueOf(rs.getString("size.name").toUpperCase());
//                Goods goods = new Goods();
//                goods.setId(goodsId);
//                goods.setName(goodsName);
//                goods.setPicture(goodsPicture);
//                goods.setPrice(goodsPrice);
//                Item item = new Item(id, goods, dough, size);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return items;
//    }
//
//    @Override
//    public Item findEntityById(int id) {
//        Item item = new Item();
//        item.setId(id);
//        try (PreparedStatement statement = connection.prepareStatement("SELECT item.id, item.goods_id, dough.name, size.name FROM item" +
//                " JOIN dough ON dough.id = item.dough_id JOIN size ON size_id = size.id WHERE item.id=?")) {
//            statement.setString(1, String.valueOf(id));
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                item.setId(rs.getInt("goods.id"));
//                item.setDough(Dough.valueOf(rs.getString("dough.name").toUpperCase()));
//                item.setSize(Size.valueOf(rs.getString("size.name").toUpperCase()));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return item;
//    }
//
//    @Override
//    public boolean delete(int id) {
//        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM item WHERE id=?")) {
//            statement.setInt(1, id);
//            statement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public boolean delete(Item entity) {
//        return false;
//    }
//
//    @Override
//    public boolean create(Item entity) {
//        int goodsId = entity.getGoods().getId();
//        int doughId = entity.getDough().getDoughId();
//        int sizeId = entity.getSize().getSizeId();
//        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO item (goods_id, dough_id, size_id) VALUES (?,?,?)")) {
//            statement.setInt(1, goodsId);
//            statement.setInt(2, doughId);
//            statement.setInt(3, sizeId);
//            statement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    @Override
//    public void update(Item entity) {  //todo remove unnecessary method
//        int id = entity.getId();
//        int doughId = entity.getDough().getDoughId();
//        int sizeId = entity.getSize().getSizeId();
//        try (PreparedStatement statement = connection.prepareStatement("UPDATE item SET goods_id=?, dough_id=?, size_id=? WHERE id=?")) {
//            statement.setInt(2, doughId);
//            statement.setInt(3, sizeId);
//            statement.setInt(4, id);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
