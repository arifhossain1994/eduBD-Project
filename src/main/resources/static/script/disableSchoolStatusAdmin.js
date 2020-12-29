// disableSchoolStatusAdmin.js

//This function disables the status of a school.
$(document).ready( function(){
    let readButton= document.getElementById("allSchoolTableDisable");
    $(readButton).click(function(e){
        e.preventDefault();

        let buttonValue=readButton.getAttribute("value");
        let xhr2 = new XMLHttpRequest(); // to send the data as JSON to the controller.
        xhr2.open("PUT", "School/disable_"+buttonValue,true);
        //  xhr2.setRequestHeader("Content-Type", "application/json");
        xhr2.onreadystatechange = function() { // Call a function when the state changes.
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                // Request finished. Do processing here.
                console.log("SUCCESS DISABLE");
                window.location.reload();
                window.location.reload();
            }
            else {
                $('#statusChangeButtonAction').html(xhr2.responseText);
            }
        }
        xhr2.send(buttonValue);

    });
});





//This function enables the status of a school.
$(document).ready( function(){
    let readButton2= document.getElementById("allSchoolTableEnable");
    $(readButton2).click(function(e2){
        e2.preventDefault();

        let buttonValue2=readButton2.getAttribute("value");
        let stringValue2=buttonValue2.toString();
        console.log(stringValue2);

        let xhr3 = new XMLHttpRequest(); // to send the data as JSON to the controller.
        xhr3.open("PUT", "School/disable_"+buttonValue2,true);
        // xhr3.setRequestHeader("Content-Type", "application/json");
        xhr3.onreadystatechange = function() { // Call a function when the state changes.
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                // Request finished. Do processing here.
                console.log("SUCCESS ENABLED");
                window.location.reload();
            }
            else {
                console.log(xhr3.responseText);
                $('#statusChangeButtonAction').html(xhr3.responseText);
            }
        }
        xhr3.send(buttonValue2);
    });
});


