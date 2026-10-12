$(document).ready(function(){

    $('.adicionar-filmes').validate({

        rules: {

            titulo: {
                required: true
            },

            genero: {
                required: true,
                minlength: 4
            },

            lancamento: {
                required: true
            },

            duracao: {
                required: true
            },

            'faixa-etaria': {
                required: true,
                maxlenght: 2
            }

        },

        messages: {

            titulo:{
                required: "Informe o titulo do filme!"
            },

            genero: {
                required: "Digite o genero do filme!",
                maxlenght: "Mínimo de 4 caracteres."
            },

            lancamento: {
                required: "Informe a data de lançamento!"
            },

            duracao: {
                required: "Informe a duração!"
            },

            'faixa-etaria': {
                required: "Informe a faixa-etária!",
                maxlenght: "Máximo de 2 caracteres."
            }

        },

        errorPlacement: function(error, element){
            if(element.attr('name') == "titulo"){
                error.appendTo('.erro-titulo')
            }
            if(element.attr('name') == "genero"){
                error.appendTo('.erro-genero')
            }
            if(element.attr('name') == "lancamento"){
                error.appendTo('.erro-lancamento')
            }
            if(element.attr('name') == "duracao"){
                error.appendTo('.erro-duracao')
            }
            if(element.attr('name') == "faixa-etaria"){
                error.appendTo('.erro-faixa-etaria')
            }
        }
    });
});