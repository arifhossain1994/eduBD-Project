// addNewSchoolAdminPage.js
// This function helps to send data for the Add new school HTML form and adds it to the data base.
$(document).ready( function() {
    $("#submit").click(function(e) {
        e.preventDefault();
        let schEml=$("#schoolEmail").val();
        let y=$("#schoolEmail-repeat").val();
        if(schEml!==y) { //if the user types in different emails in the boxes.
            $('#feedback').html("Email doesn't match. Enter again.");
            return false;
        }

        let schNum=$("#schoolPhone").val();
        if(isNaN(schNum)){
            $('#feedback').html("Phone number does not have proper format.");
            return false;
        }

        let school={};
        school.schoolName = $("#schoolName").val();
        school.schoolEmail= schEml;
        school.schoolPhone = schNum
        school.status= null;
        school.schoolStreet= null;
        school.schoolHouse= null;
        school.schoolZip= null;
        school.schoolCity= null;
        school.schoolState= null;
        school.schoolCountry= null;
        school.image= null;
        school.createBy= null;
        school.updatedBy= null;
        school.createdDate= null;
        school.updatedDate= null;
        school.id= null;

        let s2=JSON.stringify(school);
        console.log(s2);

        let xhr = new XMLHttpRequest(); // to send the data as JSON to the controller.
        xhr.open("POST", "School",true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function() { // Call a function when the state changes.
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                // Request finished. Do processing here.
                $('#feedback').html("Success");
                alert("Record inserted successfully");
                document.getElementById('createSchool').reset();
                window.location.reload();
            }
            else {
                console.log(xhr.responseText);
                $('#feedback').html(xhr.responseText);
            }
        }
        xhr.send(s2);
    });
});