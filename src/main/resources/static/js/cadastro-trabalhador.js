document.getElementById('perfilForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const ongId = localStorage.getItem("ongId");
    if (ongId) {
        document.getElementById("ongId").value = ongId;
    } else {
        alert("ID da ONG não encontrado. Faça login novamente.");
        window.location.href = "/login-ong.html";
    }

    // Validação da data de nascimento
    const dataNascimentoInput = document.getElementById('dataNascimento');
    if (!dataNascimentoInput.value) {
        alert('Data de nascimento é obrigatória!');
        return;
    }

    const formData = {
        nome: document.getElementById('nome').value,
        email: document.getElementById('email').value,
        cpf: document.getElementById('cpf').value.replace(/\D/g, ''),
        data_nascimento: dataNascimentoInput.value, // Garante o envio
        telefone: document.getElementById('telefone').value.replace(/\D/g, ''),
        endereco: document.getElementById('endereco').value,
        habilidades: document.getElementById('habilidades').value || null,
        idOng: parseInt(document.getElementById('ongId').value)
    };

    console.log('Dados enviados:', formData); // Para debug

    try {
        const response = await fetch('http://localhost:8080/ong/trabalhador', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(formData)
        });

        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.message || 'Erro no servidor');
        }

        const data = await response.json();
        alert(`Cadastro realizado com sucesso!`);
        window.location.href = "/ong/trabalhadores.html";
    } catch (error) {
        console.error('Erro detalhado:', error);
        alert(`Erro: ${error.message || 'Falha na conexão com o servidor'}`);
    }
});

// Máscaras para CPF e Telefone
document.getElementById('cpf').addEventListener('input', function(e) {
    let value = e.target.value.replace(/\D/g, '');
    if (value.length > 3) value = value.replace(/^(\d{3})/, '$1.');
    if (value.length > 7) value = value.replace(/^(\d{3})\.(\d{3})/, '$1.$2.');
    if (value.length > 11) value = value.replace(/^(\d{3})\.(\d{3})\.(\d{3})/, '$1.$2.$3-');
    e.target.value = value.substring(0, 14);
});

document.getElementById('telefone').addEventListener('input', function(e) {
    let value = e.target.value.replace(/\D/g, '');
    if (value.length > 0) value = value.replace(/^(\d{0,2})/, '($1');
    if (value.length > 3) value = value.replace(/^(\(\d{2})(\d)/, '$1) $2');
    if (value.length > 10) value = value.replace(/(\d{4})(\d)/, '$1-$2');
    e.target.value = value.substring(0, 15);
});