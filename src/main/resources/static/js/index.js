let request = [];
function createNavigation(pageRequest) {
    let pageContent = pageRequest;
    const total = Math.max(0, Number(pageContent.totalPages) || 0);
    const current = Number(pageContent.number) || 0;

    let html = `
    <li class="${pageContent.first ? 'page-item disabled' : 'page-item'}">
        <button class="page-link bg-transparent" type="button" onclick="getNewPage(${current - 1})" aria-label="Previous">
            <span aria-hidden="true">&laquo;</span>
        </button>
    </li>
    `;

    for (let page = 0; page < total; page++) {
        html += `
        <li class="page-item ${page === current ? 'active' : ''}">
            <button class="page-link bg-transparent text-light" type="button" onclick="getNewPage(${page})" aria-label="Next">
                ${current + 1}
            </button>
        </li>
        `;
    }

    html += `
    <li class="${pageContent.last ? 'page-item disabled' : 'page-item'}">
        <button class="page-link bg-transparent" type="button" onclick="getNewPage(${current + 1})" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
        </button>
    `
    $("#nav-pages").empty().append(html);
}

function createSnippet(pageRequest) {
    let content = pageRequest.content[0];
    const decoded = decodeHtml(content.snippet);
    $("#code").text(decoded);
    Prism.highlightElement(document.getElementById("code"));
}

function createTextQuestion(pageRequest) {
    const html = pageRequest.content[0].question;
    quill.setText("");
    quill.clipboard.dangerouslyPasteHTML(html);
    quill.enable(false);
}

function decodeHtml(html) {
    const txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}

$(document).ready(function () {
    createNavigation(page);
    createSnippet(page);
    createTextQuestion(page);
})


$("#btn-execute").on("click", function () {
    let inputSize = page.content[0].inputSize;
    request.push($("#input-execute").val())
    if(inputSize === request.length) {
        axios.post(`/myanswers/${lang}/answers`, {
            request,
            questionNumber: page.content[0].questionNumber
        }).then((res) => {
            let response = res.data;
            response = response.includes("Err") ? "&#9888; " + response : response;
            $("#question-content").empty().html(response);
        })
    }else{
        $("#question-content").empty().html(inputSizeError);
    }


})
