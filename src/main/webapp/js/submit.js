/**
 * 
 */
var questionNo = 1;

function appendNewQuestion() {
	var formContainer = document.getElementById('questions-container');

	var newDiv = document.createElement("div");
	newDiv.setAttribute("id", "question-" + questionNo);
	newDiv.innerHTML = "<h2>Question " + ++questionNo + "</h2> \
					<label>Question</label><br> \
					<input type='text' name='question' required><br> \
					<label>Type</label><br> \
					<select name='type' onChange='check(this)'> \
						<option value='text'>Text</option> \
						<option value='radio'>Radio</option> \
						<option value='checkbox'>Checkbox</option> \
						<option value='textarea'>TextArea</option> \
					</select> \
					<hr class='break'>";

	formContainer.appendChild(newDiv);
}

function appendNewOption(element) {
	var newOption = document.createElement("div");
	var questionnum = element.parentElement.parentElement.id.split("-")[1];
	newOption.setAttribute("id", "option");
	newOption.innerHTML = "<label>Option:</label> <input type='text' name='optionfield-"
			+ questionnum + "' required> <button class='inline' type='button' onclick='deleteParentOption(this)'>Delete</button>";

	var optionDiv = element.parentNode;
	optionDiv.insertBefore(newOption, optionDiv.getElementsByClassName("add-option")[0]);
}

function check(element) {
	var questionElement = element.parentNode;
	if (element.value == "radio" || element.value == "checkbox") {
		if (!questionElement.getElementsByClassName("options-div")[0]) {
			var newOptionsDiv = document.createElement("div");
			newOptionsDiv.innerHTML = "<button class='add-option' type='button' onclick='appendNewOption(this)'>Add new Option</button>";
			newOptionsDiv.setAttribute("class","options-div")
			questionElement.insertBefore(newOptionsDiv, questionElement.getElementsByClassName("break")[0]);
		}
	} else {
		if (questionElement.getElementsByClassName("options-div")[0]) {
			var optionsDiv = questionElement.getElementsByClassName("options-div")[0];
			optionsDiv.parentNode.removeChild(optionsDiv);	
		}
	}
}

function deletePreviousQuestion() {
	questionNo--;
	var questionToBedeleted = document.getElementById("question-" + questionNo);
	questionToBedeleted.parentNode.removeChild(questionToBedeleted);
}

function deleteParentOption(element) {
	var questionToBedeleted = element.parentNode;
	questionToBedeleted.parentNode.removeChild(questionToBedeleted);
}

window.onload = function() {
	document.getElementById('createdate').valueAsDate = new Date();

	var minExpiry = new Date();
	minExpiry.setDate(minExpiry.getDate() + 1);

	var newExpiry = minExpiry.toISOString().split('T')[0];
	document.getElementById('expirydate').setAttribute('min', newExpiry);
}

function setMinMax() {
	var createDate = document.getElementById('createdate').valueAsDate;
	if (createDate != null) {
		createDate.setDate(createDate.getDate() + 1);
		var newMin = createDate.toISOString().split('T')[0];
		document.getElementById('expirydate').setAttribute('min', newMin);
	} else {
		document.getElementById('expirydate').removeAttribute('min');
	}
	
	var expiryDate = document.getElementById('expirydate').valueAsDate;
	if (expiryDate != null) {
		expiryDate.setDate(expiryDate.getDate() - 1);
		var newMax = expiryDate.toISOString().split('T')[0];
		document.getElementById('createdate').setAttribute('max', newMax);
	} else {
		document.getElementById('createdate').removeAttribute('max');
	}
}
