package ru.itis.datregistration.listener;

import jakarta.servlet.*;
import ru.itis.datregistration.repositories.AccountsRepository;
import ru.itis.datregistration.repositories.UsersRepository;
import ru.itis.datregistration.repositories.impl.AccountsRepositoryImpl;
import ru.itis.datregistration.repositories.impl.UsersRepositoryImpl;
import ru.itis.datregistration.services.AccountService;
import ru.itis.datregistration.services.UserService;
import ru.itis.datregistration.services.impl.AccountServiceImpl;
import ru.itis.datregistration.services.impl.UserServiceImpl;

@jakarta.servlet.annotation.WebListener
public class WebListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is initialized(when the Web application is deployed). */
        AccountsRepository accountsRepository = new AccountsRepositoryImpl();
        UsersRepository usersRepository = new UsersRepositoryImpl();

        AccountService accountService = new AccountServiceImpl(accountsRepository, usersRepository);
        sce.getServletContext().setAttribute("account_service", accountService);

        UserService userService = new UserServiceImpl(usersRepository);
        sce.getServletContext().setAttribute("user_service", userService);

    }
}
