/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.TextMind.event;

import com.TextMind.entity.User;
import java.util.ArrayList;

/**
 *
 * @author hoanl
 */
public interface EventMenuLeft {
    public void showFound(ArrayList<User> list);
    public void unShowBanned(String id);

}