package com.AndyChu.DevJourney.model;

import java.util.*;
import java.util.regex.Pattern;

import com.AndyChu.DevJourney.util.ValidationResult;

public class UserValidation {
    public static ValidationResult validateUser(User user, UserRepository userRepository) {
        Map<String, String> errorMsgs = new HashMap<>();
        List<String> validatorMsg = new ArrayList<>();

        String invalidEmail = "INVALID_EMAIL";
        String passwordTooWeak = "PASSWORD_TOO_WEAK";
        String passwordMismatch = "PASSWORD_MISMATCH";
        
        // 檢查帳號是不是空值
        if (user.getAccount() == null || user.getAccount().trim().isEmpty()) {
            errorMsgs.put("account", "ACCOUNT_CANNOT_BE_EMPTY");
            validatorMsg.add("帳號不能為空！");
            System.out.println("account_cannot_be_empty");
        } else {
            // 檢查帳號是否已被註冊
            if (userRepository.findByAccount(user.getAccount()).isPresent()) {
                errorMsgs.put("account", "ACCOUNT_ALREADY_REGISTERED");
                validatorMsg.add("帳號已被註冊！");
                System.out.println("account_already_registered");
            }
        }

        // Email是不是空值
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            errorMsgs.put("email", "EMAIL_CANNOT_BE_EMPTY");
            validatorMsg.add("信箱不能為空！");
            System.out.println("email_cannot_be_empty");
        } else {
            // 驗證 Email 格式
            String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (!pattern.matcher(user.getEmail()).matches()) {
                errorMsgs.put("email", "INVALID_EMAIL_FORMAT");
                validatorMsg.add("信箱格式錯誤！");
                System.out.println("email_error");
            } else {
            	// 檢查信箱是否重複
                if (user.getEmail() != null && userRepository.findByEmail(user.getEmail()).isPresent()) {
                    errorMsgs.put("email", "EMAIL_ALREADY_REGISTERED");
                    validatorMsg.add("該信箱已被註冊！");
                    System.out.println("email_already_registered");
                }
            }
        }

        // 檢查密碼是不是空值
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            errorMsgs.put("password", "PASSWORD_CANNOT_BE_EMPTY");
            validatorMsg.add("密碼不能為空！");
            System.out.println("password_cannot_be_empty");
        } else {
            // 檢查密碼複雜度
            if (!isValidPassword(user.getPassword())) {
                errorMsgs.put("password", "PASSWORD_TOO_WEAK");
                validatorMsg.add("密碼長度至少8碼，且必須包含大小寫英文、數字");
                System.out.println("password_error");
            }
        }


        // 驗證密碼與確認密碼
        if (user.getPassword() != null && !user.getPassword().equals(user.getConfirmPassword())) {
            errorMsgs.put("confirmPassword", passwordMismatch);
            validatorMsg.add("確認密碼不符！");
            System.out.println("confirmPassword_error");
        }

        return new ValidationResult(errorMsgs, validatorMsg);
    }

    private static boolean isValidPassword(String password) {
    	//密碼必須包含大小寫字母、數字，且最少為8碼
        return password.length() >= 8 &&
        		password.matches(".*[A-Z].*") &&
        		password.matches(".*[a-z].*") &&
        		password.matches(".*\\d.*");
    }
}

