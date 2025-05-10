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