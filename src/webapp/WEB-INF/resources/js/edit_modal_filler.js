/*$(document).delegate('.edit-button_', 'click', function (event){
    //event.preventDefault();

});*/

function modalWindowProp(username){

    const url = './api/users/' + username;

    fetch(url)
        .then(async response => {
            try {
                const user = await response.json();
                console.log(user);
                $('.form-edit #id').val(user.id);
                $('.form-edit #username').val(user.username);
                $('.form-edit #email').val(user.email);
                $('.form-edit #password').val(user.password);
            } catch (error){
                alert('Error in modal json: ' + error);
            }
        })

    $('#modalEdit').modal('show');
}

