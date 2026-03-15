package com.arnau.registerformarnau;

public class RegisteredUsersManagement {

    private static String[] usersRegistered = new String[10];
    private static String[] pwdRegistered = new String[10];
    private static int userCount = 0;

    public boolean addUser(String user, String pass) {
        if (userCount >= usersRegistered.length) {
            return false;
        }

        usersRegistered[userCount] = user;
        pwdRegistered[userCount] = pass;
        userCount++;

        return true;
    }

    public static boolean checkLogin(String user, String pass){

        for (int i = 0; i < usersRegistered.length; i++) {
            if (usersRegistered[i].equals(user) && pwdRegistered[i].equals(pass)) {
                return true;
            }
        }

        return false;
    }
}
