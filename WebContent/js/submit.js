/**
 * 
 */
var questionNo = 1;

function appendNewQuestion() {
	var formContainer = document.getElementById('form-container');
	var newDiv = document.createElement("div");
	newDiv.setAttribute("id", "question-" + questionNo);
	newDiv.innerHTML = "Question: <input type='text' name='question'><br /> Type: <select name='type'><option value='text'>Text</option><option value='radio'>Radio</option><option value='checkbox'>Checkbox</option><option value='textarea'>TextArea</option></select><br /> Name: <input type='text' name='name'><br />"
	
	formContainer.appendChild(newDiv);
}




