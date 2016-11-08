package cn.tomleung.tool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.tomleung.entity.User;

public class InputVerify {
	public boolean PasswordLengthVerify(User user){
		if(user.getPassword().length()<8)
			return false;
		else
			return true;
	}
	
	public boolean PasswordEqualVerify(User user){
		if(user.getPassword().equals(user.getPassword2()))
			return true;
		else
			return false;
	}
	
	public boolean AgeNumericVerify(User user){
		String age = user.getAge();
		for (int i = 0; i < age.length(); i++) {
			if (!Character.isDigit(age.charAt(i)))
				return false;
		}
		return true;
	}
	
	public boolean EmailVerify(User user){
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(user.getEmail());
		return matcher.matches();
	}
}
