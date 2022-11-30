package ru.itis.datbookshop.services.user.impl;

import jakarta.servlet.http.HttpServletRequest;
import ru.itis.datbookshop.models.User;
import ru.itis.datbookshop.repositories.user.UsersRepository;
import ru.itis.datbookshop.services.changer.ChangeProfileService;
import ru.itis.datbookshop.services.changer.impl.ChangeProfileServiceImpl;
import ru.itis.datbookshop.services.user.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    protected final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> findAll() {
        return usersRepository.getAll();
    }

    @Override
    public List<User> findNext10User(int amount) {
        return usersRepository.getNext10User(amount);
    }

    @Override
    public User findUserById(long id) {
        return usersRepository.getProfileById(id);
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
    public void changeProfile(HttpServletRequest request) {
        ChangeProfileService changeProfileService = new ChangeProfileServiceImpl(usersRepository);

        changeProfileService.changeUsername(request);
        changeProfileService.changePhoneNumber(request);
        changeProfileService.changeCity(request);
        changeProfileService.changeSex(request);
        changeProfileService.changeAge(request);

    }

    @Override
    public void donate(int money, long id) {
        usersRepository.updateBalanceById(money, id);
    }


}
