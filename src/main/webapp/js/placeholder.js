/**USERNAME */
var username = document.getElementById("username");

username.value = "Nom";

username.onfocus = function() {
	if (username.value == "Nom") {
		username.value = "";
	}
}
username.onblur = function() {
	if (username.value == "") {
		username.value = "Nom";
	}
}
/**Telefon */
var telefon = document.getElementById("telefon");

telefon.value = "Telèfon";

telefon.onfocus = function() {
	if (telefon.value == "Telèfon") {
		telefon.value = "";
	}
}
telefon.onblur = function() {
	if (telefon.value == "") {
		telefon.value = "Telèfon";
	}
}
/**Telefon */
var email = document.getElementById("email");

email.value = "Mail";

email.onfocus = function() {
	if (email.value == "Mail") {
		email.value = "";
	}
}
email.onblur = function() {
	if (email.value == "") {
		email.value = "Mail";
	}
}

/**password */
var password = document.getElementById("password");

password.value = "Contrassenya";

password.onfocus = function() {
	if (password.value == "Contrassenya") {
		password.value = "";
	}
}
password.onblur = function() {
	if (password.value == "") {
		password.value = "Contrassenya";
	}
}
/**REpassword */
var confirmPassword = document.getElementById("confirmPassword");

confirmPassword.value = "Confirma la contrassenya";

confirmPassword.onfocus = function() {
	if (confirmPassword.value == "Confirma la contrassenya") {
		confirmPassword.value = "";
	}
}
confirmPassword.onblur = function() {
	if (confirmPassword.value == "") {
		confirmPassword.value = "Confirma la contrassenya";
	}
}


