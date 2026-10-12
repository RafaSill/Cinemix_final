$(document).ready(function(){

    $('#inputTel').mask('(00) 00000-0000');

    $('.formulario-cadastro').validate({

        rules: {
            nome: {
                required: true
            },

              email: {
                required: true,
                email: true
            },

            senha: {
                required: true,
                minlength: 6,
                maxlength: 12
            },

            numero: {
                required: true,
                minlength: 12,
                maxlenght: 12,
            },

            cidade: {
                required: true,
                minlength: 16
            }
        },

        messages: {

            nome:{
                required: "informe seu nome!"
            },

            email: {
                required: "Informe seu email!",
                email: "email inválido."
            },

            senha: {
                required: "Informe sua Senha!",
                minlength: "Mínimo 6 caracteres."
            },

            numero: {
                required: "Informe seu Número!",
                minlength: "Informe um número válido.",
                maxlength: "Máximo de 12 caracteres."
            },

            cidade: {
                required: "Informe sua cidade!",
                minlength: "Informe uma cidade válida."
            }
        }, 

        errorPlacement: function(error, element){
            if(element.attr('name') == "nome"){
                error.appendTo(".erro-nome");
            }

            if(element.attr('name') == "email"){
                error.appendTo('.erro-email');
            }
            
            if(element.attr('name') == "senha"){
                error.appendTo('.erro-senha');
            }

             if(element.attr('name') == "numero"){
                error.appendTo('.erro-numero');
            }

            if(element.attr('name') == "cidade"){
                error.appendTo('.erro-cidade');
            }
        }

    });
});