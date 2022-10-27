package ru.itis.datregistration.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import ru.itis.datregistration.repositories.UsersRepository;
import ru.itis.datregistration.models.User;
import ru.itis.datregistration.services.UserService;

public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User findByEmail(String email) {
        return usersRepository.getProfileByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return usersRepository.getProfileByUsername(username);
    }

    @Override
    public User findByNumberPhone(long numberPhone) {
        return usersRepository.getProfileByNumberPhone(numberPhone);
    }

    @Override
    public void changeUsername(HttpServletRequest request) {
        String username = request.getParameter("user_name");
        String email = (String) request.getSession().getAttribute("root_email");
        if (username.isEmpty()) {
            request.setAttribute("messError1", "Please fill in the data");
        } else if (username.length() < 3) {
            request.setAttribute("username", username);
            request.setAttribute("messError1", "Minimum username length - 3");
        } else if (usersRepository.getProfileByUsername(username) != null) {
            request.setAttribute("username", username);
            request.setAttribute("messError1", "Username used");
        } else {
            usersRepository.updateUsername(username, email);
            request.setAttribute("messSuccess1", "Username changed");
        }
    }

    @Override
    public void changeNumberPhone(HttpServletRequest request) {
        String numPhoneStr = request.getParameter("phone_number");
        String email = (String) request.getSession().getAttribute("root_email");
        if (numPhoneStr.isEmpty()) {
            request.setAttribute("messError2", "Please fill in the data");
        } else {
            try {
                long numberPhone = Long.parseLong(numPhoneStr);
                if (!(numPhoneStr.length() == 11)) {
                    request.setAttribute("messError2", "Your number phone is not correct");
                    request.setAttribute("number_phone", numberPhone);
                } else {
                    usersRepository.updateNumberPhone(numberPhone, email);
                    request.setAttribute("messSuccess2", "Number phone changed");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("number_phone", numPhoneStr);
                request.setAttribute("messError2", "Your number phone is not correct");
            }
        }

    }
}
