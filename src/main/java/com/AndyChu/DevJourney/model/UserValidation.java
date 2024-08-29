package com.AndyChu.DevJourney.model;

import java.util.*;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.AndyChu.DevJourney.util.ValidationResult;

public class UserValidation {
	
	private static final Logger logger = LoggerFactory.getLogger(UserValidation.class);
	
    public static ValidationResult validateUser(User user, UserRepository userRepository) {
    	
        Map<String, String> errorMsgs = new HashMap<>();
        List<String> validatorMsg = new ArrayList<>();
        
        // 檢查帳號是不是空值
        if (user.getAccount() == null || user.getAccount().trim().isEmpty()) {
            errorMsgs.put("account", "ACCOUNT_CANNOT_BE_EMPTY");
            validatorMsg.add("帳號不能為空！");
            logger.warn("account_cannot_be_empty");
        } else {
            // 檢查帳號是否已被註冊
            if (userRepository.findByAccount(user.getAccount()).isPresent()) {
                errorMsgs.put("account", "ACCOUNT_ALREADY_REGISTERED");
                validatorMsg.add("帳號已被註冊！");
                logger.warn("account_already_registered");
            }else {
            	logger.info("account_verification_passed");
            }
        }

        // 檢查密碼是不是空值
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            errorMsgs.put("password", "PASSWORD_CANNOT_BE_EMPTY");
            validatorMsg.add("密碼不能為空！");
            logger.warn("password_cannot_be_empty");
        } else {
            // 檢查密碼複雜度
            if (!isValidPassword(user.getPassword())) {
                errorMsgs.put("password", "PASSWORD_TOO_WEAK");
                validatorMsg.add("密碼長度至少8碼，且必須包含大小寫英文、數字");
                logger.warn("password_too_weak");
            }else {
            	// 驗證密碼與確認密碼
                if (user.getPassword() != null && !user.getPassword().equals(user.getConfirmPassword())) {
                    errorMsgs.put("confirmPassword", "PASSWORD_MISMATCH");
                    validatorMsg.add("確認密碼不符！");
                    logger.warn("password_mismatch");
                }else {
                	logger.info("password_verification_passed");
                }
            }
        }

        // Email是不是空值
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            errorMsgs.put("email", "EMAIL_CANNOT_BE_EMPTY");
            validatorMsg.add("信箱不能為空！");
            logger.warn("email_cannot_be_empty");
        } else {
            // 驗證 Email 格式
            String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (!pattern.matcher(user.getEmail()).matches()) {
                errorMsgs.put("email", "INVALID_EMAIL_FORMAT");
                validatorMsg.add("信箱格式錯誤！");
                logger.warn("invalid_email_format");
            } else {
            	// 檢查信箱是否重複
                if (user.getEmail() != null && userRepository.findByEmail(user.getEmail()).isPresent()) {
                    errorMsgs.put("email", "EMAIL_ALREADY_REGISTERED");
                    validatorMsg.add("該信箱已被註冊！");
                    logger.warn("nickname_cannot_be_empty");
                } else {
                	logger.info("email_verification_passed");
                }
            }
        }
        
        if (user.getNickname() == null || user.getNickname().trim().isEmpty()) {
        	errorMsgs.put("nickname", "NICKNAME_CANNOT_EMPTY");
        	validatorMsg.add("暱稱不能為空！");
        	logger.warn("nickname_cannot_empty");
        } else {
        	logger.info("nickname_verification_passed");
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

