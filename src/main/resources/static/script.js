

const body = document.body,
tbl = document.getElementById('html-data-table');

let URL = 'http://localhost:8080/admin/';

let roles = [];
let user = {id:"",password:"",name:"",username:"",roles};
let name1;
let username;
let password;
let userCheck;
let adminCheck;



// main page call
function mainPage() {
    fetch(URL)
        .then(function (response) {
            return response.json();
        }).then(function (apiJsonData) {
        console.log(apiJsonData);
        tableCreate(apiJsonData);
    })
}

// main table  create function

function tableCreate(userSet) {


    tbl.style.width = '100px';
    tbl.style.border = '1px solid black';


    const tr = tbl.insertRow();

    let btn = document.createElement('input');
    btn.type = "button";
    btn.className = "btn";
    btn.value = "Add";
    btn.style.backgroundColor = "orange";
    btn.style.color = "white";
    btn.onclick = function () {
        newUserForm();
    };
    let td = tr.insertCell();
    td.appendChild(btn);

    userSet.forEach(userDat => {
        addUserRow(userDat);

    })
    body.appendChild(tbl);
}



function delet(id) {
    console.log('del');
    fetch(URL + id, {method: 'DELETE'});
}



function addUserRow(userDat,row) {
    if(row==null)
        row=tbl.rows.length - 1;
    const tr = tbl.insertRow(row);

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
        newUserForm(userDat);
        tr.remove();
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
        delet(userDat.id);
        tr.remove();
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
}






function newUserForm(userIn) {


    console.log('form');
    const body = document.body,
        form = document.createElement('table');
    form.style.width = '100px';
    form.style.border = '1px solid black';

    const tr = form.insertRow();
    const td = tr.insertCell();
    td.appendChild(document.createTextNode('Name'))
    const td1 = tr.insertCell();
    td1.appendChild(name1 = document.createElement("input"));

    const tr2 = form.insertRow();
    const td2 = tr2.insertCell();
    td2.appendChild(document.createTextNode('Username'))
    const td3 = tr2.insertCell();
    td3.appendChild(username = document.createElement("input"));


    const tr3 = form.insertRow();
    const td4 = tr3.insertCell();
    td4.appendChild(document.createTextNode('Password'))
    const td5 = tr3.insertCell();
    td5.appendChild(password = document.createElement("input"));




    const trCheck = form.insertRow();
    userCheck = document.createElement('input')
    userCheck.type = "checkbox";
    userCheck.checked = true;
    let tdUser = trCheck.insertCell();
    tdUser.appendChild(userCheck);

//    const trCheck = form.insertRow();
    adminCheck = document.createElement('input')
    adminCheck.type = "checkbox";
    let tdAdmin = trCheck.insertCell();
    tdAdmin.appendChild(adminCheck);

    const trB = form.insertRow();
    let btn = document.createElement('input');
    btn.type = "button";
    btn.className = "btn";
    btn.value = "Add";
    btn.style.backgroundColor = "red";
    btn.style.color = "white";
    btn.onclick = function () {
        newUser();
        form.parentNode.removeChild(form);

    };


    if(userIn != null) {
        username.value = userIn.username;
        password.value = userIn.password;
        name1.value = userIn.name;

        userCheck.checked = userIn.roles.some(e => e.name === "ROLE_USER");
        adminCheck.checked = userIn.roles.some( e => e.name === "ROLE_ADMIN");

        btn.value = "Save";
        btn.style.backgroundColor = "green";
        btn.onclick = function() {
            let userToEdit = userIn;
            userToEdit.username = username.value;
            userToEdit.name = name1.value;
            userToEdit.password = password.value;

            if(userCheck.checked)
                roles.push({"id":1,"name": "ROLE_USER"});
            if(adminCheck.checked)
                roles.push({"id":2,"name": "ROLE_ADMIN"});
            userToEdit.roles = roles;
            form.parentNode.removeChild(form);
            editUser(userToEdit);


        }
    }

    let tdb = trB.insertCell();
    tdb.appendChild(btn);
    body.appendChild(form);
}

function newUser() {
    user.name = name1.value;
    user.username = username.value;
    user.password = password.value;
    if(userCheck.checked)
        roles.push({"id":1,"name": "ROLE_USER"});
    if(adminCheck.checked)
        roles.push({"id":2,"name": "ROLE_ADMIN"});
    user.roles = roles;
    console.log(user);

    fetch(URL, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(function (response1){
        return response1.json();
    }).then(function (data){
        addUserRow(data);
    });
}


function editUser(user){
    let  id=user.id;
    fetch(URL + id, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    }).then(function (response1){
        return response1.json();
    }).then(function (data){
        addUserRow(data);
    });
}



