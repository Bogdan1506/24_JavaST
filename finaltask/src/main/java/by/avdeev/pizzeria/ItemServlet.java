package by.avdeev.pizzeria;

import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.GoodsDao;
import by.avdeev.pizzeria.dao.ItemDAO;
import by.avdeev.pizzeria.entity.Dough;
import by.avdeev.pizzeria.entity.Goods;
import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.entity.Size;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ItemServlet {
    private final ItemDAO itemDAO;

    public ItemServlet(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public void getItemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> items = null;
        try {
            items = itemDAO.findAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("items", items);  //todo
        req.getRequestDispatcher("items.jsp").forward(req, resp);
    }

    public void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        itemDAO.delete(id);
        getItemList(req, resp);
    }

/*    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        int goodsId = Integer.parseInt(id);
        Goods goods = itemDAO.findEntityById(goodsId);
        goods.setName(req.getParameter("name"));
        goods.setDescription(req.getParameter("description"));
        goods.setPrice(Double.parseDouble(req.getParameter("price")));
        goods.setPicture(req.getParameter("picture"));
        System.out.println(goods);
        itemDAO.update(goods);
        getGoodsList(req, resp);
    }*/

    public void createItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int goodsId = Integer.parseInt(req.getParameter("id"));
        Dough doughId = Dough.valueOf(req.getParameter("dough").toUpperCase());
        Size sizeId = Size.valueOf(req.getParameter("size").toUpperCase());
        Item item = new Item(goodsId, doughId, sizeId);
        itemDAO.create(item);
        getItemList(req, resp);
    }
}