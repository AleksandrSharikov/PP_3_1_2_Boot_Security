console.log("Ok");

 top.idEdit;

let URL = 'http://localhost:8080/admin/';


fetch(URL)
    .then(function (response) {
        return response.json();
    }).then(function (apiJsonData) {
    console.log(apiJsonData);
    tableCreate(apiJsonData);
})


function tableCreate(userSet) {
    const body = document.body,
        tbl = document.getElementById('html-data-table');
    tbl.style.width = '100px';
    tbl.style.border = '1px solid black';

    userSet.forEach(userDat => {
        console.log('+');
        const tr = tbl.insertRow();

        const td = tr.insertCell();
        td.appendChild(document.createTextNode(userDat.id));
        const td1 = tr.insertCell();
        td1.appendChild(document.createTextNode(userDat.name));
        const td2 = tr.insertCell();
        td2.appendChild(document.createTextNode(userDat.username));

        let btn = document.createElement('input');
        btn.type = "button";
        btn.className = "btn";
        btn.value = "Edit";
        btn.style.backgroundColor = "green";
        btn.style.color = "white";
        btn.onclick = function () {

            edit(userDat.id)
        };
        let td3 = tr.insertCell();
        td3.appendChild(btn);

        let btnD = document.createElement('input');
        btnD.type = "button";
        btnD.className = "btn";
        btnD.value = "Delet";
        btnD.style.backgroundColor = "red";
        btnD.style.color = "white";
        btnD.onclick = function () {
            delet(userDat.id)
        };
        let td4 = tr.insertCell();
        td4.appendChild(btnD);


        td.style.border =
            td1.style.border =
                td2.style.border =
                    td3.style.border =
                        td4.style.border
                            = '1px solid black';
        td.style.backgroundColor =
            td1.style.backgroundColor =
                td2.style.backgroundColor =
                    td3.style.backgroundColor =
                        td4.style.backgroundColor
                            = "white";


    })
    body.appendChild(tbl);
}

function edit(id) {
    idEdit = id;
    console.log(id);
}

function delet(id) {
    console.log('del');
    fetch(URL + id, {method: 'DELETE'});
}

function addUser() {
    console.log('add')
  //  let myWindow = window.open(URL + "userForm", "NewUser", "width=500,height=700,popup");


}

