document.getElementById("vaga-form").addEventListener("submit", function (e) {
    e.preventDefault();

    const vaga = {
        titulo: document.getElementById("titulo").value,
        descricao: document.getElementById("descricao").value,
        local: document.getElementById("local").value,
        empresaId: parseInt(document.getElementById("empresaId").value),
        ativa: true
    };

    fetch("http://localhost:8080/vagas", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(vaga)
    })
        .then(response => {
            if (response.ok) {
                document.getElementById("mensagem").textContent = "Vaga cadastrada com sucesso!";
                document.getElementById("vaga-form").reset();
            } else {
                return response.text().then(text => { throw new Error(text); });
            }
        })
        .catch(error => {
            document.getElementById("mensagem").textContent = "Erro ao cadastrar vaga: " + error.message;
            document.getElementById("mensagem").style.color = "red";
        });
});
