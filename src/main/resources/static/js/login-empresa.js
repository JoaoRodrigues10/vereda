document.getElementById('loginEmpresaForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    fetch('/api/login/empresa', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, senha }),
        credentials: 'same-origin' // ⬅️ ISSO MANTÉM A SESSÃO
    })
        .then(res => res.json())
        .then(data => {
            if (data.message === "Login bem-sucedido") {
                window.location.href = '/homeEmpresa'; // Redirecione conforme necessário
            } else {
                document.getElementById('errorMessage').textContent = data.message;
            }
        })
        .catch(err => {
            document.getElementById('errorMessage').textContent = 'Erro ao conectar com o servidor.';
        });
});