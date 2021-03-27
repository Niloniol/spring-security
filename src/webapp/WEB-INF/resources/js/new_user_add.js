const url = './api/users/';
const form = document.getElementById('new_user_form');
const username = document.getElementById('username');
const password = document.getElementById('password');
const email = document.getElementById('email');
const roles = document.getElementById('roles');

roles.addEventListener('click', () => {
    thatSelected();
})

function thatSelected() {
    let select1 = roles;
    let selectedArr = [];

    for (let i = 0; i < select1.length; i++) {
        if (select1.options[i].selected) {
            let selectedHsh = {};
            selectedHsh['id'] = +select1.options[i].value;
            selectedHsh['name'] = select1.options[i].id;
            selectedArr.push(selectedHsh);
        }
    }
    return selectedArr;
}

form.addEventListener('submit', (e) => {

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            username: username.value,
            password: password.value,
            email: email.value,
            roles: thatSelected(),
        })
    })
        .then(res => res.json())
        .then(data => {
            const dataArr = [];
            dataArr.push(data);

            renderUsers(dataArr);
        });

    username.value = '';
    password.value = '';
    email.value = '';
    roles.value = '';
})