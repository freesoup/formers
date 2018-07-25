window.onload = function() {
	var passwordInput = document.getElementById("password");

	passwordInput.oncopy = function(event) {
		event.preventDefault();
	};

	passwordInput.oncut = function(event) {
		event.preventDefault();
	};

	passwordInput.ondragstart = function(event) {
		event.preventDefault();
	};

	passwordInput.addEventListener("contextmenu", function(event) {
		event.preventDefault();
	});
}