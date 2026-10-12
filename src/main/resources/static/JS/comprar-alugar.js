document.addEventListener("DOMContentLoaded", function () {
    const btnComprar = document.getElementById("btn-comprar");
    const btnAlugar = document.getElementById("btn-alugar");

    const tipoOperacao = document.getElementById("tipo-operacao");
    const campoDevolucao = document.getElementById("campo-devolucao");
    const dataDevolucao = document.getElementById("data-devolucao");

    btnComprar.addEventListener("click", function () {
        tipoOperacao.value = "COMPRA";

        dataDevolucao.disabled = true;
        dataDevolucao.value = "";

        campoDevolucao.classList.add("campo-desativado");
    });

    btnAlugar.addEventListener("click", function () {
        tipoOperacao.value = "ALUGUEL";

        dataDevolucao.disabled = false;

        campoDevolucao.classList.remove("campo-desativado");
    });
});