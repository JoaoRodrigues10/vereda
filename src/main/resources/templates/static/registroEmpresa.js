
    function validarFormulario() {
    const nome = document.getElementById('nome').value.trim();
    const email = document.getElementById('email').value.trim();
    const senha = document.getElementById('senha').value;
    const confirmarSenha = document.getElementById('confirmarSenha').value;

    let isValid = true;

    // Limpa mensagens anteriores
    document.querySelectorAll('.error-message').forEach(el => el.style.display = 'none');

    // Validação Nome
    if (!nome) {
    document.getElementById('nomeError').style.display = 'block';
    isValid = false;
}

    // Validação Email
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(email)) {
    document.getElementById('emailError').style.display = 'block';
    isValid = false;
}

    // Validação Senha
    if (senha.length < 6) {
    document.getElementById('senhaError').style.display = 'block';
    isValid = false;
}

    // Validação Confirmação de Senha
    if (senha !== confirmarSenha) {
    document.getElementById('confirmarSenhaError').style.display = 'block';
    isValid = false;
}

    return isValid;
}

    // Função para enviar dados via fetch
    async function enviarDados(usuario) {
    try {
    const response = await fetch('http://localhost:8080/api/registro/empresa', {
    method: 'POST',
    headers: {
    'Content-Type': 'application/json'
},
    body: JSON.stringify(usuario)
});

    const data = await response.json();

    if (response.ok) {
    document.getElementById('feedbackMessage').innerText = 'Cadastro realizado com sucesso!';
    document.getElementById('feedbackMessage').style.color = 'green';
    document.getElementById('feedbackMessage').style.display = 'block';

    // Redireciona após 2 segundos (opcional)
    setTimeout(() => {
    window.location.href = '/login.html'; // Substitua pela página de login
}, 2000);

} else {
    document.getElementById('feedbackMessage').innerText = data.message || 'Erro ao cadastrar empresa.';
    document.getElementById('feedbackMessage').style.color = 'red';
    document.getElementById('feedbackMessage').style.display = 'block';
}
} catch (error) {
    console.error('Erro:', error);
    document.getElementById('feedbackMessage').innerText = 'Erro ao conectar com o servidor.';
    document.getElementById('feedbackMessage').style.color = 'red';
    document.getElementById('feedbackMessage').style.display = 'block';
}
}

    // Evento de submit do formulário
    document.getElementById('registroEmpresaForm').addEventListener('submit', function (e) {
    e.preventDefault(); // Impede o envio padrão

    if (validarFormulario()) {
    const usuario = {
    nome: document.getElementById('nome').value,
    email: document.getElementById('email').value,
    senha: document.getElementById('senha').value
    // O campo "papel" é definido no backend como "EMPRESA"
};

    enviarDados(usuario);
}
});
