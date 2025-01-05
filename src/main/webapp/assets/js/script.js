function displayRows(page, rowsPerPage, rows) {
    const start = page * rowsPerPage;
    const end = start + rowsPerPage;
    rows.forEach((row, index) => {
        row.style.display = (index >= start && index < end) ? '' : 'none';
    });
}

function createPagination(rowsPerPage, rows, pagination, totalPages) {
    const maxPagesToShow = 10;
    let currentPage = 0;

    function updatePagination() {
        pagination.innerHTML = '';

        const startPage = Math.max(0, currentPage - Math.floor(maxPagesToShow / 2));
        const endPage = Math.min(totalPages, startPage + maxPagesToShow);

        if (currentPage > 0) {
            const prevLi = document.createElement('li');
            prevLi.className = 'page-item';
            const prevA = document.createElement('a');
            prevA.className = 'page-link';
            prevA.href = '#';
            prevA.textContent = '<<';
            prevA.addEventListener('click', function(e) {
                e.preventDefault();
                currentPage--;
                updatePagination();
                displayRows(currentPage, rowsPerPage, rows);
            });
            prevLi.appendChild(prevA);
            pagination.appendChild(prevLi);
        }

        for (let i = startPage; i < endPage; i++) {
            const li = document.createElement('li');
            li.className = 'page-item';
            const a = document.createElement('a');
            a.className = 'page-link';
            a.href = '#';
            a.textContent = i + 1;
            if (i === currentPage) {
                li.classList.add('active', 'bg-dark');
                a.classList.add('bg-dark');
            }
            a.addEventListener('click', function(e) {
                e.preventDefault();
                currentPage = i;
                updatePagination();
                displayRows(currentPage, rowsPerPage, rows);
            });
            li.appendChild(a);
            pagination.appendChild(li);
        }

        if (currentPage < totalPages - 1) {
            const nextLi = document.createElement('li');
            nextLi.className = 'page-item';
            const nextA = document.createElement('a');
            nextA.className = 'page-link';
            nextA.href = '#';
            nextA.textContent = '>>';
            nextA.addEventListener('click', function(e) {
                e.preventDefault();
                currentPage++;
                updatePagination();
                displayRows(currentPage, rowsPerPage, rows);
            });
            nextLi.appendChild(nextA);
            pagination.appendChild(nextLi);
        }
    }

    displayRows(0, rowsPerPage, rows);
    updatePagination();
}