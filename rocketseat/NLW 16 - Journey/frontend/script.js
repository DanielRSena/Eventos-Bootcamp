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
    <div class="card-bg">

        ${input}

        <div>

            <svg class="active" width="18" height="18" viewBox="0 0 18 18" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M6.50008 8.99999L8.16675 10.6667L11.5001 7.33332M17.3334 8.99999C17.3334 13.6024 13.6025 17.3333 9.00008 17.3333C4.39771 17.3333 0.666748 13.6024 0.666748 8.99999C0.666748 4.39762 4.39771 0.666656 9.00008 0.666656C13.6025 0.666656 17.3334 4.39762 17.3334 8.99999Z" stroke="#BEF264" stroke-width="1.25" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>

            <svg class="inactive" width="20" height="20" viewBox="0 0 20 20" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M8.41664 1.81833C9.46249 1.61593 10.5374 1.61593 11.5833 1.81833M11.5833 18.1817C10.5374 18.3841 9.46249 18.3841 8.41664 18.1817M14.6741 3.10083C15.5587 3.70019 16.3197 4.46406 16.9158 5.35083M1.8183 11.5833C1.6159 10.5375 1.6159 9.46252 1.8183 8.41667M16.8991 14.6742C16.2998 15.5587 15.5359 16.3198 14.6491 16.9158M18.1816 8.41667C18.384 9.46252 18.384 10.5375 18.1816 11.5833M3.1008 5.32583C3.70016 4.44128 4.46403 3.68023 5.3508 3.08417M5.3258 16.8992C4.44124 16.2998 3.6802 15.5359 3.08414 14.6492" stroke="#A1A1AA" stroke-width="1.25" stroke-linecap="round" stroke-linejoin="round"/>
            </svg>

            <span>${atividade.nome}</span>
        </div>

        <time class="short">
            ${formatar.dia.semana.curto}. ${formatar.dia.numerico} <br> ${formatar.hora}h
        </time>
        <time class="full">
            ${formatar.dia.semana.longo}, ${formatar.dia.numerico} de ${formatar.mes} às ${formatar.hora}h
        </time>
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

    for (let hora of horas)
        horasSelecao += `<option value="${hora}">${hora}</option>`;

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