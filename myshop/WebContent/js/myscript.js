function validUsr() {
	var username = document.getElementById("username").value;
	var mdiv = document.getElementById("usr");
	if (username == "") {
		mdiv.innerHTML = "<font color='red'>用户名不能为空！</font>";
		return false;
	}
	$.ajax({
		type : "POST",
		url : "NameValidateServlet",
		data : "username=" + username,
		success : function(data) {
			if (data == "true") {
				mdiv.innerHTML = "<font color='green'>恭喜你！该用户名可用</font>";
				return true;
			} else {
				mdiv.innerHTML = "<font color='red'>抱歉！该用户名已被占用</font>";
				return false;
			}
		}
	});
}

function validPwd() {
	var mdiv = document.getElementById("pwd1");
	var pwd = document.getElementById("password").value
	if (pwd.length < 8 && pwd != "") {
		mdiv.innerHTML = "<font color='red'>密码必须大于八位数！</font>";
		return false;
	} else if (pwd.length == 0) {
		mdiv.innerHTML = "";
		return true;
	} else {
		mdiv.innerHTML = "";
		return true;
	}
}

function validPwd2() {
	var mdiv = document.getElementById("pwd2");
	var pwd = document.getElementById("password").value
	var pwd2 = document.getElementById("password2").value
	if (pwd != pwd2) {
		mdiv.innerHTML = "<font color='red'>两次密码输入不一致！</font>";
		return false;
	} else {
		mdiv.innerHTML = "";
		return true;
	}
}

function validPwd3() {
	var mdiv = document.getElementById("pwd2");
	var pwd = document.getElementById("password2").value
	if (pwd.length < 8 && pwd != "") {
		mdiv.innerHTML = "<font color='red'>密码必须大于八位数！</font>";
		return false;
	} else {
		mdiv.innerHTML = "";
		return true;
	}
}

function validPwd4() {
	var mdiv = document.getElementById("pwd3");
	var pwd = document.getElementById("password2").value
	var pwd2 = document.getElementById("password3").value
	if (pwd != pwd2) {
		mdiv.innerHTML = "<font color='red'>两次密码输入不一致！</font>";
		return false;
	} else {
		mdiv.innerHTML = "";
		return true;
	}
}

function validEmail() {
	var mdiv = document.getElementById("eml");
	var email = document.getElementById("email").value
	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
	if (!reg.test(email)) {
		mdiv.innerHTML = "<font color='red'>邮箱格式不正确！</font>";
		return false;
	} else {
		mdiv.innerHTML = "";
		return true;
	}
}

$(function() {
	$(".dropdown").mouseover(function() {
		$(this).addClass("open");
	});

	$(".dropdown").mouseleave(function() {
		$(this).removeClass("open");
	})
})

function deleteConfirm() {
	var msg = "您真的确定吗？";
	if (confirm(msg) == true) {
		return true;
	} else {
		return false;
	}
}
function gotoSelectedPage() {
	var x = document.getElementById("navigatorForm");
	// alert("Original action: " + x.action)
	x.submit();
}

// $('#goodsubmit').click(function() {
// var description = $("#fake_gdes").val();
// $("#gdes").val(description);
// $("goodform").submit();
// });
