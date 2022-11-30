package ru.itis.datbookshop.controllers.listener;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import ru.itis.datbookshop.dbcontext.DBContext;
import ru.itis.datbookshop.repositories.user.AccountsRepository;
import ru.itis.datbookshop.repositories.product.BooksRepository;
import ru.itis.datbookshop.repositories.product.CategoriesRepository;
import ru.itis.datbookshop.repositories.user.OrdersRepository;
import ru.itis.datbookshop.repositories.user.UsersRepository;
import ru.itis.datbookshop.repositories.user.impl.AccountsRepositoryImpl;
import ru.itis.datbookshop.repositories.product.impl.BooksRepositoryImpl;
import ru.itis.datbookshop.repositories.product.impl.CategoriesRepositoryImpl;
import ru.itis.datbookshop.repositories.user.impl.OrdersRepositoryImpl;
import ru.itis.datbookshop.repositories.user.impl.UsersRepositoryImpl;
import ru.itis.datbookshop.services.user.AccountService;
import ru.itis.datbookshop.services.product.BooksService;
import ru.itis.datbookshop.services.product.CategoriesService;
import ru.itis.datbookshop.services.user.OrderService;
import ru.itis.datbookshop.services.user.UserService;
import ru.itis.datbookshop.services.user.impl.AccountServiceImpl;
import ru.itis.datbookshop.services.product.impl.BooksServiceImpl;
import ru.itis.datbookshop.services.product.impl.CategoriesServiceImpl;
import ru.itis.datbookshop.services.user.impl.OrderServiceImpl;
import ru.itis.datbookshop.services.user.impl.UserServiceImpl;
import ru.itis.datbookshop.show.LeftShow;

@jakarta.servlet.annotation.WebListener
public class WebListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    private HikariDataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        DBContext dbContext = new DBContext();
        this.dataSource = dbContext.getDataSource();

        BooksRepository booksRepository = new BooksRepositoryImpl();
        BooksService booksService = new BooksServiceImpl(booksRepository);
        sce.getServletContext().setAttribute("book_service", booksService);

        CategoriesRepository categoriesRepository = new CategoriesRepositoryImpl();
        CategoriesService categoriesService = new CategoriesServiceImpl(categoriesRepository);
        sce.getServletContext().setAttribute("categories_service", categoriesService);

        LeftShow leftShow = new LeftShow();
        sce.getServletContext().setAttribute("left_show", leftShow);

        AccountsRepository accountsRepository = new AccountsRepositoryImpl();
        UsersRepository usersRepository = new UsersRepositoryImpl();

        AccountService accountService = new AccountServiceImpl(accountsRepository, usersRepository);
        sce.getServletContext().setAttribute("account_service", accountService);

        UserService userService = new UserServiceImpl(usersRepository);
        sce.getServletContext().setAttribute("user_service", userService);

        OrdersRepository ordersRepository = new OrdersRepositoryImpl();
        OrderService orderService = new OrderServiceImpl(ordersRepository);
        sce.getServletContext().setAttribute("order_service", orderService);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        this.dataSource.close();
    }
}
