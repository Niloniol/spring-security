const userTable = document.getElementById('user_table');
const api = './api/users/';

const parseUsers = (users) => {

    let html = "";

    users.forEach((user) => {
        html += "<tr id=" + user.id + ">";
        html += "<td>" + user.id + "</td>";
        html += "<td>" + user.username + "</td>";
        html += "<td>" + user.email + "</td>";
        html += "<td>";
        user.roles.forEach((role) => {
            html += role.role + " ";
        })
        html += "</td>";

        html += '<td><a href="#" onclick="modalWindowProp('+ "\'" +user.username+ "\'" +')" class="btn btn-primary" >Edit</a></td>';
        html += '<td><a href="#" onclick="deleteUser('+ "\'" +user.username+ "\'" +')"  class="btn btn-danger">Delete</a></td>';
        html += "</tr>";
    });

    userTable.innerHTML = html;
}

function refresh(){
    fetch(api)
        .then(async response => {
            try{
                const data = await response.json();
                console.log("Users list refreshed");
                parseUsers(data);
                $('#modalEdit').on('hidden.bs.modal', function (e) {
                    parent.postMessage($("#main-content").height()+50, "*");
                });
                parent.postMessage($("#main-content").height()+50, "*");
            } catch (error) {
                alert('Error in json response: ' + error);
            }
        })
} //setInterval('refresh()', 20000);

function deleteUser(username){

    const url = api + username;

    fetch(url, {
        method: 'DELETE'
    }).then( async responce => {
        try {
            refresh();
            const data = responce.json();
        } catch (error){
            alert('Error from user delete: ' + error);
        }
    });
}