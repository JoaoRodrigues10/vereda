document.querySelectorAll('.nav-menu a').forEach(link => {
    link.addEventListener('click', function (e) {
        e.preventDefault();
        const page = this.getAttribute('data-page');

        fetch(page)
            .then(res => res.text())
            .then(html => {
                document.getElementById('conteudo').innerHTML = html;
            })
            .catch(() => {
                document.getElementById('conteudo').innerHTML = '<p>Erro ao carregar a página.</p>';
            });

        // Atualiza a aba ativa
        document.querySelectorAll('.nav-menu a').forEach(a => a.classList.remove('active'));
        this.classList.add('active');
    });

    // Carregar dashboard automaticamente ao iniciar
    window.addEventListener('DOMContentLoaded', () => {
        document.querySelector('.nav-menu a[data-page="/ong/dashboard-ong.html"]').click();
    });



});

document.addEventListener('DOMContentLoaded', function() {
    // Função para carregar a contagem de trabalhadores
    async function carregarContagemTrabalhadores() {
        try {
            const idOng = localStorage.getItem('ongId');
            const response = await fetch(`/ong/trabalhador/count/${idOng}`);

            if (!response.ok) {
                throw new Error('Erro ao carregar dados');
            }
            const data = await response.json();
            document.getElementById('contadorTrabalhadores').textContent = data.count;
        } catch (error) {
            console.error('Erro:', error);
            document.getElementById('contadorTrabalhadores').textContent = 'Erro ao carregar';
        }
    }

    async function carregarContagemMatches() {
        try {
            const idOng = localStorage.getItem('ongId');

            if (!idOng) {
                console.warn('ID da ONG não encontrado no localStorage.');
                document.getElementById('contadorMatches').textContent = 'N/A';
                return;
            }

            const response = await fetch(`/ongs/match/count/${idOng}`); // Corrigido aqui

            if (!response.ok) {
                throw new Error('Erro ao carregar dados dos matches');
            }

            const data = await response.json();
            document.getElementById('contadorMatches').textContent = data.count;
        } catch (error) {
            console.error('Erro ao buscar matches:', error);
            document.getElementById('contadorMatches').textContent = 'Erro';
        }
    }

    carregarContagemMatches();
    setInterval(carregarContagemMatches, 30000); // Atualiza a cada 30s se quiser

    // Carrega os dados quando a página é aberta
    carregarContagemTrabalhadores();

    // Atualiza a cada 30 segundos (opcional)
    setInterval(carregarContagemTrabalhadores, 30000);
});

const socket = new WebSocket(`ws://${window.location.host}/ong/trabalhador/updates`);

socket.onmessage = function(event) {
    const data = JSON.parse(event.data);
    if (data.type === 'count_update') {
        document.getElementById('contadorTrabalhadores').textContent = data.count;
    }
};

