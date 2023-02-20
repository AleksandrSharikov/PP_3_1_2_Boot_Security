function newUserForm() {
    const body = document.body,
        tbl = bocument.createElement('table');
    tbl.style.width = '100px';
    tbl.style.border = '1px solid black';
    const tr = tbl.insertRow();
    const td = tr.insertCell();
    td.appendChild(document.createTextNode('Name'))
    const td1 = tr.insertCell();
    td1.appendChild(document.createElement("input",userDat.name));
    const td2 = tr.insertCell();
    td2.appendChild(document.createTextNode('Username'))
    const td3 = tr.insertCell();
    td3.appendChild(document.createElement("input",userDat.usermane));

    console.log(userDat);

}
newUserForm();