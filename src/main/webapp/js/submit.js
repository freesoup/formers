/**
 * 
 */
var questionNo = 1;

function appendNewQuestion() {
	var formContainer = document.getElementById('form-container');
	var br = document.createElement("br");
	
	var newDiv = document.createElement("div");
	newDiv.setAttribute("id", "question-" + questionNo);
	newDiv.innerHTML = "Question: <input type='text' name='question'><br /> \
	Name: <input type='text' name='name'><br /> \
	Type: <select \
		name='type' onChange='check(this)'> \
		<option value='text'>Text</option> \
		<option value='radio'>Radio</option> \
		<option value='checkbox'>Checkbox</option> \
		<option value='textarea'>TextArea</option> \
	</select><br /> \
	<div class='options' hidden disabled> \
		<button type='button' onclick='appendNewOption(this)'>Add new Option</button><br> \
	</div>";
	
	formContainer.appendChild(newDiv);
	
	formContainer.appendChild(br);

	questionNo++;
}

function appendNewOption(element) {
	var newOption = document.createElement("div");
	var questionnum = element.parentElement.parentElement.id.split("-")[1];
	newOption.setAttribute("id", "option");
	newOption.innerHTML = "Option: <input type='text' name='optionfield-" + questionnum + "'>";
	element.parentElement.appendChild(newOption);
}

function check(element) {
	if (element.value == "radio" || element.value == "checkbox") {
		element.parentElement.querySelector(".options").removeAttribute("disabled");
		element.parentElement.querySelector(".options").removeAttribute("hidden");
	} else {
		element.parentElement.querySelector(".options").setAttribute("disabled", true);
		element.parentElement.querySelector(".options").setAttribute("hidden", true);
	}
}
