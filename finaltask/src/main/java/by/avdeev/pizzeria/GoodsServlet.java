package by.avdeev.pizzeria;

import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.GoodsDao;
import by.avdeev.pizzeria.entity.Goods;
import by.avdeev.pizzeria.entity.User;

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
}
