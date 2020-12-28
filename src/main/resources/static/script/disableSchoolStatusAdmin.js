// disableSchoolStatusAdmin.js

//This function disables the status of a school.
$(document).ready( function(){
    $("#allSchoolTableDisable").click(function(e){
        e.preventDefault();
        let readButton= document.getElementById("allSchoolTableDisable");
        let buttonValue=readButton.getAttribute("value");
        let stringValue=buttonValue.toString();
        console.log(stringValue);

        let xhr2 = new XMLHttpRequest(); // to send the data as JSON to the controller.
        xhr2.open("PUT", "School/disable_"+buttonValue,true);
        xhr2.setRequestHeader("Content-Type", "application/json");
        xhr2.onreadystatechange = function() { // Call a function when the state changes.
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                // Request finished. Do processing here.
                console.log("SUCCESS DISABLE");
                window.location.reload();
            }
            else {
                console.log(xhr2.responseText);
                $('#disableButtonAction').html(xhr2.responseText);
            }
        }
        xhr2.send(buttonValue);
    });
});