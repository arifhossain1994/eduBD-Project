// createTableWithAllSchool.js

//This function inserts data from the database to a table dynamically. To see all data
const schoolTableBody = document.querySelector("#allSchoolsTable > tbody");
function loadAllSchools(){
    const request=new XMLHttpRequest();
    request.open("GET", "School/allSchool");
    request.setRequestHeader("Content-Type", "application/json");
    request.onreadystatechange = function() {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            console.log(this.responseText);
        }
    };
    request.onload=() =>{
        try{
            const json=JSON.parse(request.responseText);
            populateAllSchool(json);
        }catch (e){
            console.log("Could not load school data. \n");
        }
    };
    request.send();
}

function populateAllSchool(json) {
    //This is to clear existing data in the table.
    while (schoolTableBody.firstChild) {
        schoolTableBody.removeChild(schoolTableBody.firstChild);
    }
    // for each data create a <tr> and insert <td> for each item.
    json.forEach((row) => {
        const tr = document.createElement("tr");

        const tdID = document.createElement("td");
        let button3 = document.createElement("BUTTON");
        button3.setAttribute("id", "schoolNameEditable");
        button3.setAttribute("class", "btn btn-success");
        button3.setAttribute("type", "button");
        button3.onclick= function(){ document.getElementById('id02').style.display='block';};
        button3.setAttribute("value", row.id);
        button3.textContent = row.id;
        tdID.appendChild(button3);
        tr.appendChild(tdID);

        const tdSN = document.createElement("td");
        tdSN.textContent=row.schoolName;
        tr.appendChild(tdSN);

        const tdSE = document.createElement("td");
        tdSE.textContent = row.schoolEmail;
        tr.appendChild(tdSE);

        const tdSW = document.createElement("td");
        tdSW.textContent= row.schoolWebsite;
        tr.appendChild(tdSW);

        const tdSA = document.createElement("td");
        tdSA.textContent = row.schoolStreet;
        tr.appendChild(tdSA);

        const tdSP = document.createElement("td");
        tdSP.textContent = row.schoolPhone;
        tr.appendChild(tdSP);

        const tdStatus = document.createElement("td");
        tdStatus.textContent = row.status;
        tr.appendChild(tdStatus);

        const tdSC = document.createElement("td");
        tdSC.textContent = row.createdDate;
        tr.appendChild(tdSC);

        const tdSU = document.createElement("td");
        tdSU.textContent = row.updatedDate;
        tr.appendChild(tdSU);

        const tdModify = document.createElement("td");
        //tdModify.textContent="";

        let button = document.createElement("BUTTON");
        button.setAttribute("id", "allSchoolTableDisable");
        button.setAttribute("type", "button");
        button.setAttribute("value", row.schoolEmail);

        if (row.status === "DEACTIVE") {
            button.setAttribute("class", "btn btn-success");
            button.textContent = "Enable";
            tdModify.appendChild(button);
            tr.appendChild(tdModify);
        } else {
            button.setAttribute("class", "btn btn-danger");
            button.textContent = "Disable";
            tdModify.appendChild(button);
            tr.appendChild(tdModify);
        }

        schoolTableBody.appendChild(tr);
    });

    document.addEventListener("DOMContentLoaded", () => {
        loadAllSchools();
    });

}