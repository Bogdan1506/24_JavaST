package by.avdeev.pizzeria.action;

import by.avdeev.pizzeria.dao.DAOException;
import by.avdeev.pizzeria.dao.Transaction;
import by.avdeev.pizzeria.dao.factory.TransactionFactory;
import by.avdeev.pizzeria.dao.factory.TransactionFactoryImpl;
import by.avdeev.pizzeria.entity.User;
import by.avdeev.pizzeria.service.ServiceException;
import by.avdeev.pizzeria.service.UserService;
import by.avdeev.pizzeria.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserShowAction extends Action {
    @Override
    public Forward exec(HttpServletRequest request, HttpServletResponse response) {
        try {
//            TransactionFactory transactionFactory = new TransactionFactoryImpl();
//            Transaction transaction = transactionFactory.createTransaction();
            UserService userService = factory.getUserService();
//            UserServiceImpl userService = (UserServiceImpl) factory.getUserService();
            userService.setTransaction(factory.getTransactionFactory().createTransaction());
//            userService.setTransaction(transaction);
            List<User> users;
            users = userService.findAll();
            request.setAttribute("users", users);
            request.getRequestDispatcher("user.jsp").forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (DAOException e) {
//            e.printStackTrace();
//        }
        return null;
    }
}
