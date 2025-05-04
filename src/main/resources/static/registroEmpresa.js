// Evento de submit do formulário
    document.getElementById('registroEmpresaForm').addEventListener('submit', function(e) {
    e.preventDefault();
    console.log("Formulário submetido"); // Log de debug

    if (validarFormulario()) {
    const empresa = {
    nome: document.getElementById('nome').value,
    email: document.getElementById('email').value,
    senha: document.getElementById('senha').value,
    telefone: document.getElementById('telefone').value,
    endereco: document.getElementById('endereco').value,
    setor: document.getElementById('setor').value,
    cnpj: document.getElementById('cnpj').value
};

    console.log("Dados a serem enviados:", empresa); // Log dos dados
    enviarDados(empresa);
}
});

    // Função para validar campos - Adicione validação para os novos campos
    function validarFormulario() {
    const nome = document.getElementById('nome').value.trim();
    const email = document.getElementById('email').value.trim();
    const senha = document.getElementById('senha').value;
    const confirmarSenha = document.getElementById('confirmarSenha').value;
    const telefone = document.getElementById('telefone').value.trim();
    const endereco = document.getElementById('endereco').value.trim();
    const setor = document.getElementById('setor').value.trim();
    const cnpj = document.getElementById('cnpj').value.trim();

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

    // Validação Telefone
    if (!telefone) {
    document.getElementById('telefoneError').style.display = 'block';
    isValid = false;
}

    // Validação Endereço
    if (!endereco) {
    document.getElementById('enderecoError').style.display = 'block';
    isValid = false;
}

    // Validação Setor
    if (!setor) {
    document.getElementById('setorError').style.display = 'block';
    isValid = false;
}

    // Validação CNPJ
    if (!cnpj) {
    document.getElementById('cnpjError').style.display = 'block';
    isValid = false;
}

    return isValid;
}

    async function enviarDados(empresa) {
    try {
    const response = await fetch('http://localhost:8080/api/registro/empresa', {
    method: 'POST',
    headers: {
    'Content-Type': 'application/json'
},
    body: JSON.stringify(empresa)
});

    if (response.ok) {
    // Feedback visual
    showFeedback('✅ Cadastro realizado com sucesso! Redirecionando para login...', 'success');

    setTimeout(() => {
    window.location.href = '/login'; // Confirme este caminho
}, 2000);
} else {
    const error = await response.json();
    showFeedback(`❌ ${error.message || 'Erro no cadastro'}`, 'error');
}
} catch (error) {
    showFeedback('❌ Erro de conexão com o servidor', 'error');
    console.error('Erro:', error);
}
}

    // Função auxiliar para mostrar feedback
    function showFeedback(message, type) {
    const feedback = document.getElementById('feedbackMessage');
    feedback.innerText = message;
    feedback.className = `feedback-message ${type}`;
    feedback.style.display = 'block';
}

    function mascaraCNPJ(input) {
    // Remove tudo o que não é dígito
    let value = input.value.replace(/\D/g, "");

    // Aplica a máscara
    if (value.length <= 12) {
    value = value.replace(/(\d{2})(\d)/, "$1.$2");
    value = value.replace(/(\d{3})(\d)/, "$1.$2");
    value = value.replace(/(\d{3})(\d)/, "$1/$2");
} else {
    value = value.replace(/^(\d{2})(\d)/, "$1.$2");
    value = value.replace(/^(\d{2})\.(\d{3})(\d)/, "$1.$2.$3");
    value = value.replace(/\.(\d{3})(\d)/, ".$1/$2");
    value = value.replace(/(\d{4})(\d)/, "$1-$2");
}

    // Atualiza o valor do campo
    input.value = value.substring(0, 18);
}

