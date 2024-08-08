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
        
        // 检查帳號是否已被註冊
        if (user.getAccount() != null && userRepository.findByAccount(user.getAccount()).isPresent()) {
            errorMsgs.put("account", "ACCOUNT_ALREADY_REGISTERED");
            validatorMsg.add("帳號已被註冊！");
            System.out.println("account_already_registered");
        }

        // 驗證email格式
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (user.getEmail() == null || user.getEmail().trim().isEmpty() || !pattern.matcher(user.getEmail()).matches()) {
            errorMsgs.put("email", invalidEmail);
            validatorMsg.add("信箱格式錯誤！");
            System.out.println("email_error");
        }

        // 验证密码长度和复杂度
        if (user.getPassword() == null || user.getPassword().trim().isEmpty() || !isValidPassword(user.getPassword())) {
            errorMsgs.put("password", passwordTooWeak);
            validatorMsg.add("密碼長度至少8碼，且必須包含大小寫英文、數字");
            System.out.println("password_error");
        }

        // 验证确认密码是否匹配
        if (user.getPassword() != null && !user.getPassword().equals(user.getConfirmPassword())) {
            errorMsgs.put("confirmPassword", passwordMismatch);
            validatorMsg.add("確認密碼不符！");
            System.out.println("confirmPassword_error");
        }

        // 检查邮箱是否已被注册
        if (user.getEmail() != null && userRepository.findByEmail(user.getEmail()).isPresent()) {
            errorMsgs.put("email", "EMAIL_ALREADY_REGISTERED");
            validatorMsg.add("該信箱已被註冊！");
            System.out.println("email_already_registered");
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

