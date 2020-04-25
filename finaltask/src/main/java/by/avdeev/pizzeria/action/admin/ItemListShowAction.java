package by.avdeev.pizzeria.action.admin;

import by.avdeev.pizzeria.entity.Item;
import by.avdeev.pizzeria.service.ItemService;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.validator.IncorrectFormDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ItemListShowAction extends AdminAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public ForwardObject exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, IncorrectFormDataException {
        String pageNum = request.getParameter("page");
        int page = 1;
        if (pageNum != null) {
            page = Integer.parseInt(pageNum);
        }
        ItemService itemService = factory.getItemService();
        int pageSize = 20;
        List<Item> items = itemService.findAll((page - 1) * pageSize, page * pageSize);
        logger.debug("items={}", items);
        request.setAttribute("items", items);
        logger.debug("pageNum={}", pageNum);
        request.setAttribute("page", page);
        return null;
    }
}
