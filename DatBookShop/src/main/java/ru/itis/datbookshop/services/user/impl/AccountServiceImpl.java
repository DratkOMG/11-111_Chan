package ru.itis.datbookshop.services.user.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.datbookshop.exceptions.EmailValidatorException;
import ru.itis.datbookshop.exceptions.PasswordValidatorException;
import ru.itis.datbookshop.models.Account;
import ru.itis.datbookshop.models.User;
import ru.itis.datbookshop.repositories.user.AccountsRepository;
import ru.itis.datbookshop.repositories.user.UsersRepository;
import ru.itis.datbookshop.repositories.user.impl.AccountsRepositoryImpl;
import ru.itis.datbookshop.services.user.AccountService;
import ru.itis.datbookshop.validation.EmailValidator;
import ru.itis.datbookshop.validation.PasswordValidator;
import ru.itis.datbookshop.validation.impl.EmailValidatorImpl;
import ru.itis.datbookshop.validation.impl.PasswordByLengthValidatorImpl;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    private final PasswordValidator passwordValidator = new PasswordByLengthValidatorImpl();
    private final EmailValidator emailValidator = new EmailValidatorImpl();

    private final AccountsRepository accountsRepository;
    private final UsersRepository usersRepository;

    public AccountServiceImpl(AccountsRepository accountsRepository, UsersRepository usersRepository) {
        this.accountsRepository = accountsRepository;
        this.usersRepository = usersRepository;
    }

    public boolean signIn(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AccountsRepository accountsRepositoryImpl = new AccountsRepositoryImpl();
        Account account = accountsRepositoryImpl.signIn(email, password);

        if (account == null) {
            request.setAttribute("email", email);
            request.setAttribute("messError", "Wrong email or password");
            return false;
        } else {
            request.setAttribute("ad", account.getIsAdmin());
            request.setAttribute("sell", account.getIsSeller());
            String remember = request.getParameter("remember");
            try {
                if (remember.equals("on")) {
                    Cookie acc = new Cookie("acc", email);
                    Cookie pass = new Cookie("pass", password);
                    Cookie rem = new Cookie("remember", remember);
                    response.addCookie(acc);
                    response.addCookie(pass);
                    response.addCookie(rem);
                }
            } catch (NullPointerException ignored) {
                Cookie[] cookies = request.getCookies();
                for (Cookie cookie: cookies) {
                    if (cookie.getName().equals("acc") || cookie.getName().equals("pass") || cookie.getName().equals("remember")) {
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
            return true;
        }
    }

    @Override
    public void signUp(HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        if (!password.equals(confirmPassword)) {
            request.setAttribute("email", email);
            request.setAttribute("username", username);
            request.setAttribute("messError", "Confirm password not correct");
            return;
        }

        try {
            this.passwordValidator.validate(password);
            this.emailValidator.validate(email);
            Account account = Account.builder()
                    .email(email)
                    .password(password)
                    .build();
            accountsRepository.signUp(account);
            usersRepository.createProfile(account, request.getParameter("username"));
            request.setAttribute("messSuccess", "SignUp Successful");
        } catch (PasswordValidatorException e) {
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("messError", e.getMessage());
        } catch (EmailValidatorException e) {
            request.setAttribute("messError", e.getMessage());
        }
    }

    @Override
    public void changePassword(HttpServletRequest request) {
        User user = usersRepository.getProfileById((Long) request.getSession().getAttribute("root_id"));
        String email = user.getEmail();
        String rootPassword = accountsRepository.getPassword(email);
        String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("new_password");
        String confirmNewPassword = request.getParameter("confirm_new_password");
        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            request.setAttribute("errorChange", "Please fill in the data");
            request.setAttribute("old_password", oldPassword);
            request.setAttribute("new_password", newPassword);
            request.setAttribute("confirm_new_password", confirmNewPassword);
            return;
        }

        if (!newPassword.equals(confirmNewPassword)) {
            request.setAttribute("old_password", request.getParameter("old_password"));
            request.setAttribute("errorChange", "Confirm new password not correct");
            return;
        }

        if (!oldPassword.equals(rootPassword)) {
            request.setAttribute("errorChange", "Old password not correct");
            return;
        }

        if (oldPassword.equals(newPassword)) {
            request.setAttribute("errorChange", "The password is the same");
            return;
        }

        try {
            this.passwordValidator.validate(newPassword);
            Account account = Account.builder()
                    .email(email)
                    .password(newPassword)
                    .build();
            accountsRepository.changePassword(account);
            request.setAttribute("niceChange", "Change password successful");
        } catch (PasswordValidatorException e) {
            request.setAttribute("errorChange", e.getMessage());
        }

    }

    @Override
    public List<Account> findAll() {
        return accountsRepository.getAll();
    }

    @Override
    public List<Account> findNext10(int amount) {
        return accountsRepository.getNext10Account(amount);
    }

    @Override
    public Account findAccountById(long id) {
        return accountsRepository.getAccountById(id);
    }

    @Override
    public Account findAccountByEmail(String email) {
        return accountsRepository.getAccountByEmail(email);
    }

    @Override
    public void updateSeller(long eId, String seller) {
        String s = "0";

        try {
            if (seller.equals("on")) {
                s = "1";
            }
        } catch (NullPointerException ignored) {
        }

        accountsRepository.updateSeller(eId, s);
    }

    @Override
    public void deleteById(long eId) {
        accountsRepository.deleteAccById(eId);
    }


}


