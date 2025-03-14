document.addEventListener("DOMContentLoaded", function () {
    loadEmployees();
});

// Function to add an employee
function addEmployee(event) {
    event.preventDefault();

    let empCode = document.getElementById("empCode").value.trim();
    let empName = document.getElementById("empName").value.trim();
    let empPosition = document.getElementById("empPosition").value.trim();
    let empSalary = document.getElementById("empSalary").value.trim();
    let empDate = document.getElementById("empDate").value.trim();
    let empStatus = document.getElementById("empStatus").value;

    if (!empCode || !empName || !empPosition || !empSalary || !empDate) {
        alert("Please fill all fields.");
        return;
    }

    let employees = JSON.parse(localStorage.getItem("employees")) || [];
    
    // Check for duplicate Employee Code
    if (employees.some(emp => emp.empCode === empCode)) {
        alert("Employee code already exists. Use a unique code.");
        return;
    }

    let newEmployee = { empCode, empName, empPosition, empSalary, empDate, empStatus };
    employees.push(newEmployee);
    localStorage.setItem("employees", JSON.stringify(employees));

    alert("Employee added successfully!");
    document.getElementById("addEmployeeForm").reset();
}

// Function to load employees into the view table
function loadEmployees() {
    let employees = JSON.parse(localStorage.getItem("employees")) || [];
    let tbody = document.querySelector("#employeeTable tbody");

    if (tbody) {
        tbody.innerHTML = "";
        employees.forEach(emp => {
            let row = `<tr>
                <td>${emp.empCode}</td>
                <td>${emp.empName}</td>
                <td>${emp.empPosition}</td>
                <td>${emp.empSalary}</td>
                <td>${emp.empDate}</td>
                <td>${emp.empStatus}</td>
            </tr>`;
            tbody.innerHTML += row;
        });
    }
}

// Function to delete an employee
function deleteEmployee() {
    let empCode = document.getElementById("deleteEmpCode").value.trim();
    let employees = JSON.parse(localStorage.getItem("employees")) || [];

    let index = employees.findIndex(emp => emp.empCode === empCode);

    if (index === -1) {
        alert("Employee not found.");
        return;
    }

    employees.splice(index, 1);
    localStorage.setItem("employees", JSON.stringify(employees));

    document.getElementById("deleteSuccessMessage").style.display = "block";
}

// Function to search an employee for updating
function searchEmployee() {
    let empCode = document.getElementById("searchEmpCode").value.trim();
    let employees = JSON.parse(localStorage.getItem("employees")) || [];
    let employee = employees.find(emp => emp.empCode === empCode);

    if (!employee) {
        alert("Employee not found.");
        return;
    }

    document.getElementById("updateEmpName").value = employee.empName;
    document.getElementById("updateEmpPosition").value = employee.empPosition;
    document.getElementById("updateEmpSalary").value = employee.empSalary;
    document.getElementById("updateEmpDate").value = employee.empDate;
    document.getElementById("updateEmpStatus").value = employee.empStatus;
    document.getElementById("updateSection").style.display = "block";
}

// Function to update an employee
function updateEmployee(event) {
    event.preventDefault();

    let empCode = document.getElementById("searchEmpCode").value.trim();
    let empName = document.getElementById("updateEmpName").value.trim();
    let empPosition = document.getElementById("updateEmpPosition").value.trim();
    let empSalary = document.getElementById("updateEmpSalary").value.trim();
    let empDate = document.getElementById("updateEmpDate").value.trim();
    let empStatus = document.getElementById("updateEmpStatus").value;

    let employees = JSON.parse(localStorage.getItem("employees")) || [];
    let index = employees.findIndex(emp => emp.empCode === empCode);

    if (index === -1) {
        alert("Employee not found.");
        return;
    }

    employees[index] = { empCode, empName, empPosition, empSalary, empDate, empStatus };
    localStorage.setItem("employees", JSON.stringify(employees));

    document.getElementById("updateSuccessMessage").style.display = "block";
}

// Function to return to the main menu
function returnToMenu() {
    window.location.href = "index.html";
}

// Prevent form double submission
function preventDoubleSubmission(form) {
    let submitButton = form.querySelector("button[type='submit']");
    if (submitButton.disabled) {
        return false;
    }
    submitButton.disabled = true;
    setTimeout(() => (submitButton.disabled = false), 2000); 
    return true;
}

// Attach event listeners
document.addEventListener("DOMContentLoaded", function () {
    let addForm = document.getElementById("addEmployeeForm");
    if (addForm) addForm.addEventListener("submit", addEmployee);

    let updateForm = document.getElementById("updateEmployeeForm");
    if (updateForm) updateForm.addEventListener("submit", updateEmployee);
});
