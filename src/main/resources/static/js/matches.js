// Substitua com o ID da vaga real ao integrar
const idVaga = 1;

fetch(`http://localhost:8080/ongs/match/vaga/${idVaga}`)
    .then(response => {
        if (!response.ok) {
            throw new Error("Erro ao buscar os trabalhadores.");
        }
        return response.json();
    })
    .then(trabalhadores => {
        const container = document.getElementById("lista-trabalhadores");

        if (trabalhadores.length === 0) {
            container.innerHTML = "<p>Nenhum trabalhador recomendado encontrado.</p>";
            return;
        }

        trabalhadores.forEach(trab => {
            const div = document.createElement("div");
            div.className = "trabalhador-card";
            div.innerHTML = `
                <h2>${trab.nome}</h2>
                <p><strong>Email:</strong> ${trab.email}</p>
                <p><strong>Telefone:</strong> ${trab.telefone}</p>
                <p><strong>Endereço:</strong> ${trab.endereco}</p>
                <p><strong>Habilidades:</strong> ${trab.habilidades}</p>
            `;
            container.appendChild(div);
        });
    })
    .catch(error => {
        console.error(error);
        document.getElementById("lista-trabalhadores").innerHTML = "<p>Erro ao carregar os dados.</p>";
    });
