package ru.itis.datregistration.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import ru.itis.datregistration.repositories.AccountsRepository;
import ru.itis.datregistration.repositories.UsersRepository;
import ru.itis.datregistration.repositories.impl.AccountsRepositoryImpl;
import ru.itis.datregistration.exceptions.EmailValidatorException;
import ru.itis.datregistration.exceptions.PasswordValidatorException;
import ru.itis.datregistration.models.Account;
import ru.itis.datregistration.services.AccountService;
import ru.itis.datregistration.validation.EmailValidator;
import ru.itis.datregistration.validation.PasswordValidator;
import ru.itis.datregistration.validation.impl.EmailValidatorImpl;
import ru.itis.datregistration.validation.impl.PasswordByLengthValidatorImpl;

public class AccountServiceImpl implements AccountService {
    private final PasswordValidator passwordValidator = new PasswordByLengthValidatorImpl();
    private final EmailValidator emailValidator = new EmailValidatorImpl();

    private final AccountsRepository accountsRepository;
    private final UsersRepository usersRepository;

    public AccountServiceImpl(AccountsRepository accountsRepository, UsersRepository usersRepository) {
        this.accountsRepository = accountsRepository;
        this.usersRepository = usersRepository;
    }

    public boolean signIn(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AccountsRepository accountsRepositoryImpl = new AccountsRepositoryImpl();
        Account account = accountsRepositoryImpl.signIn(email, password);

        if (account == null) {
            request.setAttribute("email", email);
            request.setAttribute("message", "Wrong email or password");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void signUp(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String yearOfBirth = request.getParameter("year_of_birth");
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || yearOfBirth.isEmpty()) {
            request.setAttribute("messError", "Please fill in the data");
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("confirm_password", confirmPassword);
            request.setAttribute("year_of_birth", yearOfBirth);
            return;
        }

        if (!email.contains("@")) {
            request.setAttribute("messError", "email not correct");
            return;
        }
        if (!password.equals(confirmPassword)) {
            request.setAttribute("email", email);
            request.setAttribute("messError", "Confirm password not correct");
            return;
        }
        try {
            int age = Integer.parseInt(yearOfBirth);
            if (!(age > 1900 && age < 2023)) {
                request.setAttribute("email", email);
                request.setAttribute("password", password);
                request.setAttribute("confirm_password", confirmPassword);
                request.setAttribute("messError", "Year of birth not correct");
                return;
            }
        } catch (Exception e) {
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("confirm_password", confirmPassword);
            request.setAttribute("messError", "Year of birth not correct");
            return;
        }


        try {
            this.passwordValidator.validate(password);
            this.emailValidator.validate(email);
            Account account = Account.builder()
                    .email(email)
                    .password(password)
                    .yearOfBirth(Integer.parseInt(yearOfBirth))
                    .build();
            accountsRepository.signUp(account);
            usersRepository.createProfile(account);
            request.setAttribute("messSuccess", "SignUp Successful");
        } catch (PasswordValidatorException e) {
            request.setAttribute("email", email);
            request.setAttribute("messError", e.getMessage());
        } catch (EmailValidatorException e) {
            request.setAttribute("messError", e.getMessage());
        }
    }

    @Override
    public void changePassword(HttpServletRequest request) {
        String rootPassword = (String) request.getSession().getAttribute("root_password");
        String oldPassword = request.getParameter("old_password");
        String newPassword = request.getParameter("new_password");
        String confirmNewPassword = request.getParameter("confirm_new_password");
        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            request.setAttribute("mess", "Please fill in the data");
            request.setAttribute("old_password", oldPassword);
            request.setAttribute("new_password", newPassword);
            request.setAttribute("confirm_new_password", confirmNewPassword);
            return;
        }

        if (!newPassword.equals(confirmNewPassword)) {
            request.setAttribute("old_password", request.getParameter("old_password"));
            request.setAttribute("mess", "Confirm new password not correct");
            return;
        }

        if (!oldPassword.equals(rootPassword)) {
            request.setAttribute("mess", "Old password not correct");
            return;
        }

        if (oldPassword.equals(newPassword)) {
            request.setAttribute("mess", "The password is the same");
            return;
        }

        try {
            this.passwordValidator.validate(newPassword);
            Account account = Account.builder()
                    .email((String) request.getSession().getAttribute("root_email"))
                    .password(newPassword)
                    .build();
            request.getSession().setAttribute("root_password", newPassword);
            accountsRepository.changePassword(account);
            request.setAttribute("mess", "Change password successful");
        } catch (PasswordValidatorException e) {
            request.setAttribute("old_password", request.getParameter("old_password"));
            request.setAttribute("mess", e.getMessage());
        }

    }

    public static void main(String[] args) {
    }

}
