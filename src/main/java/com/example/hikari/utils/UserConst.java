package com.example.hikari.utils;

public interface UserConst {

    String TBL_COL_1 = "id";
    String TBL_COL_2 = "username";
    String GET_ALL_USERS_QUERY = "SELECT `id`, `username` FROM `user`";
    String GET_USER_BY_ID_QUERY = "SELECT `id`, `username` FROM `user` WHERE `id` = ?";
}
