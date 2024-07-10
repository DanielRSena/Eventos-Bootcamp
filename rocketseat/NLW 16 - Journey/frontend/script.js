//let muda as informações da variável
//const não muda as informações da variável

const formatador = (data) => {
    return {
        dia: {
            numerico: dayjs(data).format('DD'),
            semana: {
                curto: dayjs(data).format('ddd'),
                longo : dayjs(data).format('dddd')
            }
        },
        mes : dayjs(data).format('MMMM'), 
        hora : dayjs(data).format('HH:mm')
    }
}

let atividades = [ 
    
    {
    nome: "Passar para o Mucujê",
    data:  new Date("2023-12-08 20:00"),
    finalizada: false
    },

    {
    nome: "Colar na CCXP",
    data:  new Date("2024-12-15 11:00"),
    finalizada: true
    },

    {
    nome: "Brisar no Anime Friends",
    data:  new Date("2024-07-21 13:00"),
    finalizada: false
    }
];

const atualizarAtividades = () => {
    const section = document.querySelector('section');
    if(atividades.length == 0) {
        section.innerHTML = '<p>Nenhuma atividade cadastrada</p>';
        return;
    }

    section.innerHTML = '';

    for (let atividade of atividades)
        section.innerHTML += criarItemDaAtividade(atividade);
}


//arrow function
const criarItemDaAtividade = (atividade) => {

    let input = `
    <input onchange="concluirAtividade(event)" value="${atividade.data}" type="checkbox" 
    `;

    if(atividade.finalizada) input += 'checked';

    input += '>';

    const formatar = formatador(atividade.data);

    return `
        <div>
            ${input}
            <span>${atividade.nome}</span>
            <time>${formatar.dia.semana.longo}, ${formatar.dia.numerico} de ${formatar.mes} às ${formatar.hora}h </time>
        </div>
    `;
}

const section = document.querySelector('section');
for (let atividade of atividades) {
    section.innerHTML += criarItemDaAtividade(atividade);
}

const salvarAtividade = (event) => {
    event.preventDefault();
    const dadosFormulario = new FormData(event.target);

    const nome = dadosFormulario.get('atividade');
    const dia = dadosFormulario.get('dia');
    const hora = dadosFormulario.get('hora');
    const data  = `${dia} ${hora}`;

    const novaAtividade = {
        nome,
        data,
        finalizada: false
    };

    const atividadeExiste = atividades.find(atividade => {
        return atividade.data == novaAtividade.data;
    });

    if(atividadeExiste)
        return alert('Dia/Hora não disponível');

    atividades = [novaAtividade, ...atividades];
    atualizarAtividades();
}

const criarDiasSelecao = () => {
    const dias = ['2024-01-01', '2024-01-02', '2024-01-03', '2024-01-04', '2024-01-05', '2024-01-06', '2024-01-07'];

    let diasSelecao = "";

    for (let dia of dias) {
        const formatar = formatador(dia);
        const diaFormatado = `${formatar.dia.numerico} de ${formatar.mes}`;

        diasSelecao += `<option value="${dia}">${diaFormatado}</option>`;
    }

    document.querySelector('select[name = "dia"]').innerHTML = diasSelecao;
}
criarDiasSelecao();

const criarHorasSelecao = () => {
    let horas = ['10:00', '11:00', '12:00', '13:00', '14:00', '15:00', '16:00', '17:00', '18:00', '19:00', '20:00'];

    let horasSelecao = "";

    for (let hora of horas) {
        horasSelecao += `<option value="${hora}">${hora}</option>`;
    }

    document.querySelector('select[name = "hora"]').innerHTML = horasSelecao;
}
criarHorasSelecao();

const concluirAtividade = (event) => {
    const input = event.target;
    const dataDesteInput = input.value;

    const atividade = atividades.find((atividade) => {
        return atividade.data == dataDesteInput;
    });

    if(!atividade) return;

    atividade.finalizada = !atividade.finalizada;

    atualizarAtividades();
}