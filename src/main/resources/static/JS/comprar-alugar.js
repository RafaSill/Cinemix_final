
document.addEventListener("DOMContentLoaded", function () {
    const btnComprar = document.getElementById("btn-comprar");
    const btnAlugar = document.getElementById("btn-alugar");

    const form = document.getElementById("form-pagamento");
    const tipoOperacao = document.getElementById("tipo-operacao");
    const campoDevolucao = document.getElementById("campo-devolucao");
    const dataInicio = document.getElementById("data-compra");
    const dataDevolucao = document.getElementById("data-devolucao");

    function selecionarOperacao(operacao) {
        tipoOperacao.value = operacao;

        const alugando = operacao === "ALUGUEL";

        dataInicio.required = alugando;
        dataDevolucao.required = alugando;

        dataInicio.disabled = !alugando;
        dataDevolucao.disabled = !alugando;

        if (!alugando) {
            dataInicio.value = "";
            dataDevolucao.value = "";
        }

        campoDevolucao.classList.toggle(
            "campo-desativado", !alugando
        );
    }

    btnComprar.addEventListener("click", function () {
        selecionarOperacao("COMPRA");
    });

    btnAlugar.addEventListener("click", function () {
        selecionarOperacao("ALUGUEL");
    });

    form.addEventListener("submit", function (event) {
        if (!tipoOperacao.value) {
            event.preventDefault();
            alert("Selecione COMPRAR ou ALUGAR.");
            return;
        }

        if (tipoOperacao.value === "ALUGUEL") {
            if (!dataInicio.value || !dataDevolucao.value) {
                event.preventDefault();
                alert("Informe as datas de aluguel e devolução.");
                return;
            }

            if (dataDevolucao.value < dataInicio.value) {
                event.preventDefault();
                alert("A devolução não pode ser anterior ao aluguel.");
            }
        }
    });
});
