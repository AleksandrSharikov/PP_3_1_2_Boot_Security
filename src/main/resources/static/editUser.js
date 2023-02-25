let URL = 'http://localhost:8080/admin/';

console.log('log');

let roles = [];
let user = {password:"",name:"",username:"",roles};
let name1;
let username;
let password;
let userCheck;
let adminCheck;
function newUserForm() {


    console.log('form');
    const body = document.body,
        tbl = document.createElement('table');
    tbl.style.width = '100px';
    tbl.style.border = '1px solid black';

    const tr = tbl.insertRow();
    const td = tr.insertCell();
    td.appendChild(document.createTextNode('Name'))
    const td1 = tr.insertCell();
    td1.appendChild(name1 = document.createElement("input"));

    const tr2 = tbl.insertRow();
    const td2 = tr2.insertCell();
    td2.appendChild(document.createTextNode('Username'))
    const td3 = tr2.insertCell();
    td3.appendChild(username = document.createElement("input"));

    const tr3 = tbl.insertRow();
    const td4 = tr3.insertCell();
    td4.appendChild(document.createTextNode('Password'))
    const td5 = tr3.insertCell();
    td5.appendChild(password = document.createElement("input"));


    const trCheck = tbl.insertRow();
    userCheck = document.createElement('input')
    userCheck.type = "checkbox";
    userCheck.checked = true;
    let tdUser = trCheck.insertCell();
    tdUser.appendChild(userCheck);

//    const trCheck = tbl.insertRow();
    adminCheck = document.createElement('input')
    adminCheck.type = "checkbox";
    let tdAdmin = trCheck.insertCell();
    tdAdmin.appendChild(adminCheck);

    const trB = tbl.insertRow();
    let btn = document.createElement('input');
    btn.type = "button";
    btn.className = "btn";
    btn.value = "Add";
    btn.style.backgroundColor = "red";
    btn.style.color = "white";
    btn.onclick = function () {
        newUser();

        // close();
    };
    let tdb = trB.insertCell();
    tdb.appendChild(btn);

    console.log(user);
    body.appendChild(tbl);
}
newUserForm();
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
    }).then(response=>console.log(response));


}