package ru.itis.datbookshop.services.changer;

import jakarta.servlet.http.HttpServletRequest;

public interface ChangeProfileService {
    void changeUsername(HttpServletRequest request);
    void changePhoneNumber(HttpServletRequest request);
    void changeCity(HttpServletRequest request);
    void changeSex(HttpServletRequest request);
    void changeAge(HttpServletRequest request);
}
