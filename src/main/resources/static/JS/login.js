$(document).ready(function(){
    $('#form-login').validate({

        rules: {
            email: {
                required: true,
                email: true
            },

            senha: {
                required: true,
                minlength: 6,
                maxlength: 12
            }
        },

        messages: {
            email: {
                required: "Informe seu email!",
                email: "email inválido."
            },

            senha: {
                required: "Informe sua Senha!",
                minlength: "Mínimo 6 caracteres.",
                maxlength: "Máximo de 12 caracteres."
            }
        },

        errorPlacement: function(error, element){
            if(element.attr('name') == "email"){
                error.appendTo('.erro-email');
            }

            if(element.attr('name') == "senha"){
                error.appendTo('.erro-senha');
            }
        }
   
    });
});