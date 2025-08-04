// Загрузка заметок по категории
function loadNotes(category) {
    fetch(`/ui/stash/by-category?category=${encodeURIComponent(category)}`)
        .then(response => response.json())
        .then(items => {
            const container = document.getElementById('notes-container');
            if (items.length === 0) {
                container.innerHTML = '<em>Нет заметок в этой категории.</em>';
                return;
            }

            let html = '';
            items.forEach(item => {
                const created = new Date(item.created).toLocaleString('ru-RU');
                html += `
                    <div class="accordion-item mb-2">
                        <h2 class="accordion-header">
                            <button class="accordion-button collapsed d-flex justify-content-between align-items-center"
                                    type="button" data-bs-toggle="collapse"
                                    data-bs-target="#collapse-${item.id}">
                                <span class="fw-normal">${item.title}</span>
                            </button>
                        </h2>
                        <div id="collapse-${item.id}" class="accordion-collapse collapse">
                            <div class="accordion-body">
                                <small class="note-date">${created}</small>
                                <pre class="note-body">${item.body}</pre>
                                ${item.tag ? `<div class="note-tags"><small class="note-tag-label">${item.tag}</small></div>` : ''}
                                <div class="d-flex gap-2 mt-2">
                                    <button type="button" class="btn btn-subtle btn-sm"
                                            data-bs-toggle="modal"
                                            data-bs-target="#updateModal"
                                            data-id="${item.id}"
                                            data-title="${escapeHtml(item.title)}"
                                            data-body="${escapeHtml(item.body)}"
                                            data-tag="${item.tag || ''}"
                                            data-category="${item.category}">
                                        Редактировать
                                    </button>
                                    <button type="button" class="btn btn-subtle btn-sm"
                                            data-bs-toggle="modal"
                                            data-bs-target="#confirmDeleteModal"
                                            data-id="${item.id}">
                                        Удалить
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                `;
            });
            container.innerHTML = html;
        })
        .catch(err => {
            console.error('Ошибка загрузки заметок:', err);
            document.getElementById('notes-container').innerHTML = '<em>Ошибка загрузки.</em>';
        });
}

// Экранирование HTML для безопасного вставления
function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

// Обработчики кликов по категориям
document.addEventListener('DOMContentLoaded', function () {
    const buttons = document.querySelectorAll('.category-btn');
    buttons.forEach(btn => {
        btn.addEventListener('click', function () {
            const category = this.getAttribute('data-category');
            // Сброс стилей
            buttons.forEach(b => b.classList.remove('fw-bold'));
            // Выделение активной
            this.classList.add('fw-bold');
            // Загрузка
            loadNotes(category);
        });
    });

    // Загрузка Inbox при старте
    const inboxButton = document.querySelector('.category-btn[data-category="Inbox"]');
    inboxButton.classList.add('fw-bold');
    loadNotes('Inbox');
});

// Модальное окно: удаление
document.getElementById('confirmDeleteModal').addEventListener('show.bs.modal', event => {
    const button = event.relatedTarget;
    const id = button.getAttribute('data-id');
    document.getElementById('deleteForm').action = `/ui/stash/delete/${id}`;
});

// Модальное окно: редактирование
document.getElementById('updateModal').addEventListener('show.bs.modal', event => {
    const button = event.relatedTarget;
    document.getElementById('updateTitle').value = button.getAttribute('data-title');
    document.getElementById('updateBody').value = button.getAttribute('data-body');
    document.getElementById('updateTag').value = button.getAttribute('data-tag');
    document.getElementById('updateForm').action = `/ui/stash/update/${button.getAttribute('data-id')}`;
    document.getElementById('updateCategory').value = button.getAttribute('data-category') || 'Inbox';
});