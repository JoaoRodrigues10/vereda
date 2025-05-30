const empresaId = localStorage.getItem("empresaId");

function verCandidatos(idVaga) {
    const container = document.getElementById(`candidatos-container-${idVaga}`);

    if (container.style.display === 'block') {
        container.style.display = 'none';
        container.innerHTML = '';
        return;
    }

    fetch(`http://localhost:8080/api/candidaturas/vaga/${idVaga}`)
        .then(res => res.json())
        .then(candidatos => {
            if (candidatos.length === 0) {
                container.innerHTML = '<p>Sem candidatos ainda.</p>';
            } else {
                container.innerHTML = candidatos.map(c => `
                        <div class="candidato-card">
                            <div>
                                <strong>${c.trabalhador.nome}</strong> - Status: ${c.status}
                            </div>
                            <div>
                                <button class="btn-aprovar" onclick="atualizarStatus(${c.idCandidatura}, 'ACEITA', ${idVaga})">Aprovar</button>
                                <button class="btn-recusar" onclick="atualizarStatus(${c.idCandidatura}, 'RECUSADA', ${idVaga})">Recusar</button>
                            </div>
                        </div>
                    `).join('');
            }
            container.style.display = 'block';
        })
        .catch(err => alert('Erro ao buscar candidatos: ' + err));
}

function atualizarStatus(idCandidatura, novoStatus, idVaga) {
    fetch(`http://localhost:8080/api/candidaturas/${idCandidatura}/status?status=${novoStatus}`, {
        method: 'PUT'
    })
        .then(res => {
            if (!res.ok) throw new Error('Erro ao atualizar status');
            alert(`Candidato ${novoStatus.toLowerCase()}`);
            verCandidatos(idVaga);
        })
        .catch(err => alert(err));
}

fetch(`http://localhost:8080/vagas/empresa/${empresaId}`)
    .then(response => {
        if (!response.ok) throw new Error("Erro ao buscar vagas");
        return response.json();
    })
    .then(vagas => {
        const container = document.getElementById("vaga-container");

        if (vagas.length === 0) {
            container.innerHTML = "<p>Nenhuma vaga cadastrada.</p>";
            return;
        }

        vagas.forEach(vaga => {
            const card = document.createElement("div");
            card.className = "vaga-card";

            card.innerHTML = `
                    <h2>${vaga.titulo}</h2>
                    <p><strong>Local:</strong> ${vaga.local}</p>
                    <p><strong>Status:</strong>
                        <span class="${vaga.ativa ? 'status-ativa' : 'status-inativa'}">
                            ${vaga.ativa ? 'Ativa' : 'Desativada'}
                        </span>
                    </p>
                    <p><strong>Descrição:</strong> ${vaga.descricao}</p>
                    <button onclick="verCandidatos(${vaga.idVaga})">Ver candidatos</button>
                    <div id="candidatos-container-${vaga.idVaga}" class="candidatos-container" style="display:none;"></div>
                `;

            container.appendChild(card);
        });
    })
    .catch(error => {
        const container = document.getElementById("vaga-container");
        container.innerHTML = `<p style="color:red;">${error.message}</p>`;
    });