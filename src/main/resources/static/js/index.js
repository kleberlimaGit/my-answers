let request = [];
const MOBILE_PAGER_MQ = window.matchMedia("(max-width: 640px)");

function isCompactPager() {
    return MOBILE_PAGER_MQ.matches;
}

/** Returns page indexes and "ellipsis" markers for compact mobile pagination. */
function getVisiblePageItems(total, current, compact) {
    if (total <= 0) return [];
    if (!compact || total <= 5) {
        return Array.from({ length: total }, (_, i) => i);
    }

    const items = [];
    const windowStart = Math.max(1, current - 1);
    const windowEnd = Math.min(total - 2, current + 1);

    items.push(0);

    if (windowStart > 1) {
        items.push("ellipsis");
    }

    for (let i = windowStart; i <= windowEnd; i++) {
        items.push(i);
    }

    if (windowEnd < total - 2) {
        items.push("ellipsis");
    }

    items.push(total - 1);
    return items;
}

function createNavigation(pageRequest) {
    const pageContent = pageRequest;
    const total = Math.max(0, Number(pageContent.totalPages) || 0);
    const current = Number(pageContent.number) || 0;
    const items = getVisiblePageItems(total, current, isCompactPager());

    let html = `
    <li class="${pageContent.first ? "page-item disabled" : "page-item"}">
        <button class="page-link" type="button" onclick="getNewPage(${current - 1})"
                aria-label="Previous page" ${pageContent.first ? 'aria-disabled="true" tabindex="-1"' : ""}>
            <span aria-hidden="true">&laquo;</span>
        </button>
    </li>
    `;

    for (const item of items) {
        if (item === "ellipsis") {
            html += `
            <li class="page-item disabled" aria-hidden="true">
                <span class="page-link pager-ellipsis">&hellip;</span>
            </li>
            `;
            continue;
        }

        const isActive = item === current;
        html += `
        <li class="page-item ${isActive ? "active" : ""}">
            <button class="page-link" type="button" onclick="getNewPage(${item})"
                    aria-label="Go to page ${item + 1}"
                    ${isActive ? 'aria-current="page"' : ""}>
                ${item + 1}
            </button>
        </li>
        `;
    }

    html += `
    <li class="${pageContent.last ? "page-item disabled" : "page-item"}">
        <button class="page-link" type="button" onclick="getNewPage(${current + 1})"
                aria-label="Next page" ${pageContent.last ? 'aria-disabled="true" tabindex="-1"' : ""}>
            <span aria-hidden="true">&raquo;</span>
        </button>
    </li>
    `;

    $("#nav-pages").empty().append(html);
}

function highlightCode(elementId, source) {
    const codeEl = document.getElementById(elementId);
    if (!codeEl) return;
    $(codeEl).text(source);
    if (window.Prism) {
        Prism.highlightElement(codeEl);
    }
}

function hasPageContent(pageRequest) {
    return Array.isArray(pageRequest?.content) && pageRequest.content.length > 0;
}

function createSnippet(pageRequest) {
    if (!hasPageContent(pageRequest)) {
        highlightCode("code", "");
        highlightCode("code-fullscreen", "");
        return;
    }
    const content = pageRequest.content[0];
    const decoded = decodeHtml(content.snippet);
    highlightCode("code", decoded);
    highlightCode("code-fullscreen", decoded);
}

function createShowExplanation(pageRequest) {
    if (!hasPageContent(pageRequest)) {
        $("#solutionExplanationContent").empty();
        return;
    }
    const html = normalizeAssetPaths(pageRequest.content[0].explanation);
    $("#solutionExplanationContent").empty().html(html);
}

function normalizeAssetPaths(html) {
    if (!html) return html;
    // DB content may still reference the old servlet context-path (/myanswers)
    return String(html).replaceAll("/myanswers/images/", "/images/");
}

function salaryRateTableHtml(isPt) {
    const salaryLabel = isPt ? "Salário" : "Salary";
    const percentLabel = isPt ? "Percentual de Reajuste" : "Readjustment Rate";
    const above = isPt ? "Acima de 2000.00" : "Above 2000.00";
    const rows = [
        ["0 - 400.00", "15%"],
        ["400.01 - 800.00", "12%"],
        ["800.01 - 1200.00", "10%"],
        ["1200.01 - 2000.00", "7%"],
        [above, "4%"],
    ];

    const body = rows
        .map(([salary, rate]) => `<tr><td>${salary}</td><td>${rate}</td></tr>`)
        .join("");

    return `
    <div class="rate-table-wrap" role="img" aria-label="Salary Percent">
      <table class="rate-table">
        <thead>
          <tr><th>${salaryLabel}</th><th>${percentLabel}</th></tr>
        </thead>
        <tbody>${body}</tbody>
      </table>
    </div>`;
}

function enhanceQuestionMedia(root) {
    const $root = $(root);
    const isPt = String(lang || "").toLowerCase().startsWith("pt");

    $root.find('img[src*="salary-percent"], img[alt="Salary Percent"]').each(function () {
        const $img = $(this);
        const $wrap = $img.parent("div.text-center");
        ($wrap.length ? $wrap : $img).replaceWith(salaryRateTableHtml(isPt));
    });

    $root.find("img").each(function () {
        const src = $(this).attr("src") || "";
        if (src.startsWith("/myanswers/images/")) {
            $(this).attr("src", src.replace("/myanswers/images/", "/images/"));
        }
    });
}

function createTextQuestion(pageRequest) {
    if (!hasPageContent(pageRequest)) {
        $("#editor").empty();
        return;
    }
    const html = normalizeAssetPaths(pageRequest.content[0].question);
    $("#editor").empty().html(html);
    enhanceQuestionMedia("#editor");
}

function updateProblemMeta(pageRequest) {
    const number = pageRequest?.content?.[0]?.questionNumber;
    const label = number != null ? `#${number}` : "#—";
    $("#problem-number, #modal-problem-number").text(label);
}

function resetOutput() {
    const $output = $("#question-content");
    $output.empty().removeClass("is-result is-warn").addClass("is-empty");
}

function setOutput(message, type) {
    const $output = $("#question-content");
    $output.removeClass("is-empty is-result is-warn");
    if (type === "warn") {
        $output.addClass("is-warn");
    } else if (type === "result") {
        $output.addClass("is-result");
    }
    $output.html(message);
}

function decodeHtml(html) {
    const txt = document.createElement("textarea");
    txt.innerHTML = html;
    return txt.value;
}

function renderPage(pageRequest) {
    createNavigation(pageRequest);
    createSnippet(pageRequest);
    createShowExplanation(pageRequest);
    createTextQuestion(pageRequest);
    updateProblemMeta(pageRequest);
    resetOutput();
    request = [];
}

function pageStorageKey() {
    return `answers.page.${lang || "en"}`;
}

function cleanAnswersUrl() {
    const url = new URL(window.location.href);
    url.searchParams.delete("page");
    return url;
}

function readStoredPage() {
    const value = Number(sessionStorage.getItem(pageStorageKey()));
    return Number.isFinite(value) && value >= 0 ? value : 0;
}

function saveStoredPage(pageNumber) {
    sessionStorage.setItem(pageStorageKey(), String(Math.max(0, pageNumber)));
}

function syncPageHistory(pageNumber, { replace = false } = {}) {
    const state = { page: pageNumber };
    const url = cleanAnswersUrl();
    if (replace) {
        history.replaceState(state, "", url);
    } else {
        history.pushState(state, "", url);
    }
}

$(document).ready(async function () {
    // Prefer explicit ?page= once (shared/old links), then keep the URL clean.
    const urlPage = Number(new URLSearchParams(window.location.search).get("page"));
    const hasUrlPage = Number.isFinite(urlPage) && urlPage >= 0 && new URLSearchParams(window.location.search).has("page");
    const rememberedPage = hasUrlPage ? urlPage : readStoredPage();
    const initialPage = Number(page?.number) || 0;

    renderPage(page);
    saveStoredPage(initialPage);
    syncPageHistory(initialPage, { replace: true });

    if (rememberedPage !== initialPage) {
        await getNewPage(rememberedPage, { skipHistory: true, replaceHistory: true });
    } else {
        saveStoredPage(initialPage);
        syncPageHistory(initialPage, { replace: true });
    }

    const onPagerModeChange = () => {
        if (page) createNavigation(page);
    };
    if (typeof MOBILE_PAGER_MQ.addEventListener === "function") {
        MOBILE_PAGER_MQ.addEventListener("change", onPagerModeChange);
    } else if (typeof MOBILE_PAGER_MQ.addListener === "function") {
        MOBILE_PAGER_MQ.addListener(onPagerModeChange);
    }

    window.addEventListener("popstate", (event) => {
        const pageNumber = event.state?.page ?? readStoredPage();
        getNewPage(pageNumber, { skipHistory: true });
    });
});

$("#btn-execute").on("click", function () {
    if (!hasPageContent(page)) {
        return;
    }

    const inputSize = page.content[0].inputSize;
    const value = $("#input-execute").val();

    if (value === "") {
        setOutput(inputEmpty, "warn");
        return;
    }

    request.push(value);
    $("#input-execute").val("");

    if (inputSize === request.length) {
        axios
            .post(`/${lang}/answers`, {
                request,
                questionNumber: page.content[0].questionNumber,
            })
            .then((res) => {
                let response = res.data;
                const isError = String(response).includes("Err");
                response = isError ? "⚠ " + response : response;
                setOutput(response, isError ? "warn" : "result");
                request = [];
            })
            .catch(() => {
                setOutput("⚠ Unexpected error while executing.", "warn");
                request = [];
            });
    } else {
        setOutput(inputSizeError, "warn");
    }
});

$("#input-execute").on("keydown", function (e) {
    if (e.key === "Enter") {
        e.preventDefault();
        $("#btn-execute").click();
    }
});

async function getNewPage(pageNumber, { skipHistory = false, replaceHistory = false } = {}) {
    const targetPage = Math.max(0, Number(pageNumber) || 0);
    if (!skipHistory && Number(page?.number) === targetPage) {
        saveStoredPage(targetPage);
        syncPageHistory(targetPage, { replace: true });
        return;
    }

    const workspace = document.getElementById("workspace");
    if (workspace) {
        workspace.classList.remove("is-switching");
        void workspace.offsetWidth;
        workspace.classList.add("is-switching");
    }

    await axios.get(`/${lang}/answers/page?page=${targetPage}`).then((res) => {
        page = res.data;
        const resolvedPage = Number(page.number) || targetPage;
        renderPage(page);
        saveStoredPage(resolvedPage);
        if (!skipHistory) {
            syncPageHistory(resolvedPage, { replace: replaceHistory });
        } else if (replaceHistory) {
            syncPageHistory(resolvedPage, { replace: true });
        }
        const collapse = document.getElementById("solutionExplanation");
        if (collapse && collapse.classList.contains("show")) {
            bootstrap.Collapse.getOrCreateInstance(collapse).hide();
        }
    });
}

function focusModalControl(modalSelector, controlSelector) {
    const modalEl = document.querySelector(modalSelector);
    if (!modalEl) return;
    const focusTarget = () => {
        const control = modalEl.querySelector(controlSelector);
        if (control) control.focus();
    };
    modalEl.addEventListener("shown.bs.modal", focusTarget, { once: true });
}

$("#brand-home").on("click", function (e) {
    e.preventDefault();
    saveStoredPage(0);
    try {
        sessionStorage.setItem("answers.page.en", "0");
        sessionStorage.setItem("answers.page.pt", "0");
    } catch (_) {}

    if (Number(page?.number) === 0) {
        syncPageHistory(0, { replace: true });
        window.scrollTo({ top: 0, behavior: "smooth" });
        return;
    }

    getNewPage(0, { replaceHistory: true }).then(() => {
        window.scrollTo({ top: 0, behavior: "smooth" });
    });
});

$("#btn-fullscreen-solution").on("click", function () {
    const source = $("#code").text();
    highlightCode("code-fullscreen", source);
    $("#modal-problem-number").text($("#problem-number").text());
    const modal = bootstrap.Modal.getOrCreateInstance("#modalSolutionFullscreen");
    focusModalControl("#modalSolutionFullscreen", "#btn-close-solution-modal");
    modal.show();
});

const solutionModalEl = document.getElementById("modalSolutionFullscreen");
if (solutionModalEl) {
    solutionModalEl.addEventListener("hidden.bs.modal", () => {
        const trigger = document.getElementById("btn-fullscreen-solution");
        if (trigger) trigger.focus();
    });
}

$("#about_me").on("click", function (e) {
    e.preventDefault();
    const aboutMelink = $(this).attr("href");
    const modalEdit = bootstrap.Modal.getOrCreateInstance("#modalAboutMe");
    $("#modalAboutMe .modal-content").load(aboutMelink, function () {
        focusModalControl("#modalAboutMe", ".about-close");
        modalEdit.show();
    });
});

const aboutModalEl = document.getElementById("modalAboutMe");
if (aboutModalEl) {
    aboutModalEl.addEventListener("hidden.bs.modal", () => {
        const trigger = document.getElementById("about_me");
        if (trigger) trigger.focus();
    });
}
