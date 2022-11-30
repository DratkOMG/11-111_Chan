package ru.itis.datbookshop.services.changer.impl;

import jakarta.servlet.http.HttpServletRequest;
import ru.itis.datbookshop.models.User;
import ru.itis.datbookshop.repositories.user.UsersRepository;
import ru.itis.datbookshop.services.changer.ChangeProfileService;
import ru.itis.datbookshop.services.user.impl.UserServiceImpl;

public class ChangeProfileServiceImpl extends UserServiceImpl implements ChangeProfileService {
    public ChangeProfileServiceImpl(UsersRepository usersRepository) {
        super(usersRepository);
    }

    @Override
    public void changeUsername(HttpServletRequest request) {
        String username = request.getParameter("username");
        User user = usersRepository.getProfileById((Long) request.getSession().getAttribute("root_id"));
        String email = user.getEmail();
        if (username.isEmpty() || username.isBlank()) {
            request.setAttribute("errorUsername", "Please fill username");
        } else if (username.length() < 3) {
            request.setAttribute("username", username);
            request.setAttribute("errorUsername", "Minimum username length - 3");
        } else if (!username.equals(usersRepository.getUsernameByEmail(email))) {
            if (usersRepository.getProfileByUsername(username) != null) {
                request.setAttribute("username", username);
                request.setAttribute("errorUsername", "Username used");
            } else {
                usersRepository.updateUsernameByEmail(username, email);
                request.setAttribute("niceUsername", "Username changed");
            }
        }
    }

    @Override
    public void changePhoneNumber(HttpServletRequest request) {
        String numPhoneStr = request.getParameter("phone_number");
        User user = usersRepository.getProfileById((Long) request.getSession().getAttribute("root_id"));
        String email = user.getEmail();
        try {
            long numberPhone = Long.parseLong(numPhoneStr);
            if (!(numPhoneStr.length() == 11)) {
                request.setAttribute("errorPhoneNumber", "Your number phone is not correct");
                request.setAttribute("number_phone", numberPhone);
            } else if (!numPhoneStr.equals(usersRepository.getNumberPhoneByEmail(email))) {
                usersRepository.updateNumberPhoneByEmail(numberPhone, email);
                request.setAttribute("nicePhoneNumber", "Number phone changed");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("number_phone", numPhoneStr);
            request.setAttribute("errorPhoneNumber", "Your number phone is not correct");
        }

    }

    @Override
    public void changeCity(HttpServletRequest request) {
        String city = request.getParameter("city");
        User user = usersRepository.getProfileById((Long) request.getSession().getAttribute("root_id"));
        String email = user.getEmail();

        if (!city.equals(usersRepository.getCityByEmail(email))) {
            usersRepository.updateCityByEmail(city, email);
            request.setAttribute("niceCity", "Done!");
        }
    }

    @Override
    public void changeSex(HttpServletRequest request) {
        String sex = request.getParameter("sex");
        User user = usersRepository.getProfileById((Long) request.getSession().getAttribute("root_id"));
        String email = user.getEmail();

        if (!sex.equals(usersRepository.getSexByEmail(email))) {
            usersRepository.updateSexByEmail(sex, email);
            request.setAttribute("niceSex", "Done");
        }
    }

    @Override
    public void changeAge(HttpServletRequest request) {
        String ageStr = request.getParameter("age");
        User user = usersRepository.getProfileById((Long) request.getSession().getAttribute("root_id"));
        String email = user.getEmail();

        try {
            int age = Integer.parseInt(ageStr);
            if (!(age > 6 && age < 120)) {
                request.setAttribute("errorAge", "Are you ok???");
            } else if (!ageStr.equals(usersRepository.getAgeByEmail(email))) {
                usersRepository.updateAgeByEmail(age, email);
                request.setAttribute("niceAge", "Niceee");
            }
        } catch (Exception e) {
            request.setAttribute("errorAge", "Whatttt???");
        }
    }
}
