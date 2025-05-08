document.getElementById('loginOngForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;

    fetch('/api/login/ong', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, senha })
    })
        .then(res => res.json())
        .then(data => {
            if (data.message === "Login bem-sucedido") {
                window.location.href = '/ong/dashboard.html'; // Redirecione conforme necessário
            } else {
                document.getElementById('errorMessage').textContent = data.message;
            }
        })
        .catch(err => {
            document.getElementById('errorMessage').textContent = 'Erro ao conectar com o servidor.';
        });
});