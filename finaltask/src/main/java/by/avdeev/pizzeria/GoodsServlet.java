package by.avdeev.pizzeria;

import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.GoodsDao;
import by.avdeev.pizzeria.entity.Goods;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoodsServlet {
    private final GoodsDao goodsDao;

    public GoodsServlet(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    public void getGoodsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Goods> goods = null;
        try {
            goods = goodsDao.findAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("goods", goods);  //todo
        req.getRequestDispatcher("goods.jsp").forward(req, resp);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        goodsDao.delete(id);
        getGoodsList(req, resp);
    }

    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int goodsId = Integer.parseInt(id);
        Goods goods = goodsDao.findEntityById(goodsId);
        goods.setName(req.getParameter("name"));
        goods.setDescription(req.getParameter("description"));
        goods.setPrice(Double.parseDouble(req.getParameter("price")));
        goods.setPicture(req.getParameter("picture"));
        System.out.println(goods);
        goodsDao.update(goods);
        getGoodsList(req, resp);
    }

    public void create(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        String picture = req.getParameter("picture");
        Goods goods = new Goods(name, description, price, picture);
        goodsDao.create(goods);
        getGoodsList(req, resp);
    }
}
