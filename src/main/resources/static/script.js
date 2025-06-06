document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.getElementById('loginForm');
    const entryForm = document.getElementById('entryForm');
    const generateBtn = document.getElementById('generate');
    const entriesBody = document.getElementById('entriesBody');
    const searchInput = document.getElementById('searchInput');
    const app = document.getElementById('app');
    const entryModal = document.getElementById('entryModal');
    const modalClose = document.getElementById('modalClose');
    const addEntryBtn = document.getElementById('addEntryBtn');
    const modalTitle = document.getElementById('modalTitle');

    let allEntries = [];
    let editingId = null;

    function isValidURL(string) {
        try {
            new URL(string.startsWith("http") ? string : "https://" + string);
            return true;
        } catch (_) {
            return false;
        }
    }

    function copyToClipboard(text) {
        navigator.clipboard.writeText(text).then(() => {
            showToast("In den Zwischenspeicher kopiert!");
        }).catch(err => {
            console.error("Fehler beim Kopieren: ", err);
        });
    }

    function showToast(message) {
        const toast = document.getElementById('toast');
        toast.textContent = message;
        toast.style.display = 'block';
        toast.classList.add('show');

        setTimeout(() => {
            toast.classList.remove('show');
            toast.style.display = 'none';
        }, 2000);
    }

    loginForm.addEventListener('submit', e => {
        e.preventDefault();
        const master = document.getElementById('master').value;
        fetch('/api/auth/login', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({masterPassword: master})
        }).then(res => {
            if (res.ok) {
                loginForm.style.display = 'none';
                app.style.display = 'block';
                loadEntries();
            } else {
                alert("Falsches Master-Passwort");
            }
        });
    });

    function renderEntries(entries) {
        entriesBody.innerHTML = '';
        entries.forEach(entry => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td><a href="${entry.website}" target="_blank">${entry.website}</a></td>
                <td style="cursor:pointer;" title="Klicken zum Kopieren" onclick="copyToClipboard('${entry.username}')">${entry.username}</td>
                <td>
                    <span id="pw-${entry.id}" style="cursor:pointer;" title="Klicken zum Kopieren" onclick="copyToClipboard('${entry.password}')">********</span>
                    <button onclick="togglePassword('${entry.id}', '${entry.password}')"><i class="fas fa-eye"></i></button>
                </td>
                <td>
                    <button onclick="editEntry(${entry.id}, '${entry.website}', '${entry.username}', '${entry.password}')"><i class="fas fa-edit"></i></button>
                    <button onclick="deleteEntry(${entry.id})"><i class="fas fa-trash-alt"></i></button>
                </td>
            `;
            entriesBody.appendChild(row);
        });
    }

    function loadEntries() {
        fetch('/api/passwords')
            .then(response => response.json())
            .then(data => {
                allEntries = data;
                renderEntries(allEntries);
            });
    }

    generateBtn.addEventListener('click', () => {
        fetch('/api/passwords/generate')
            .then(response => response.text())
            .then(password => {
                document.getElementById('password').value = password;
            });
    });

    entryForm.addEventListener('submit', event => {
        event.preventDefault();
        const website = document.getElementById('website').value;
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        if (!isValidURL(website)) {
            alert("Bitte eine gültige URL eingeben (z. B. example.com oder https://example.com)");
            return;
        }

        const payload = { website, username, password };
        const method = editingId ? 'PUT' : 'POST';
        const url = editingId ? '/api/passwords/' + editingId : '/api/passwords';

        fetch(url, {
            method: method,
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(payload)
        }).then(() => {
            entryForm.reset();
            editingId = null;
            entryModal.style.display = 'none';
            loadEntries();
        });
    });

    window.togglePassword = (id, actualPassword) => {
        const el = document.getElementById('pw-' + id);
        el.innerText = el.innerText === '********' ? actualPassword : '********';
    };

    window.editEntry = (id, website, username, password) => {
        modalTitle.innerText = "Eintrag bearbeiten";
        document.getElementById('website').value = website;
        document.getElementById('username').value = username;
        document.getElementById('password').value = password;
        editingId = id;
        entryModal.style.display = 'block';
    };

    window.deleteEntry = (id) => {
        if (confirm("Eintrag wirklich löschen?")) {
            fetch('/api/passwords/' + id, { method: 'DELETE' })
                .then(() => loadEntries());
        }
    };

    window.sortEntries = (key) => {
        let asc = true;
        if (window.lastSortKey === key) {
            asc = !window.lastSortAsc;
        }
        window.lastSortKey = key;
        window.lastSortAsc = asc;

        allEntries.sort((a, b) => {
            const valA = a[key].toLowerCase();
            const valB = b[key].toLowerCase();
            return asc ? valA.localeCompare(valB) : valB.localeCompare(valA);
        });
        renderEntries(filterEntries(searchInput.value));
    };

    function filterEntries(query) {
        return allEntries.filter(entry =>
            entry.website.toLowerCase().includes(query.toLowerCase()) ||
            entry.username.toLowerCase().includes(query.toLowerCase())
        );
    }

    searchInput.addEventListener('input', () => {
        renderEntries(filterEntries(searchInput.value));
    });

    modalClose.onclick = () => {
        entryModal.style.display = 'none';
        entryForm.reset();
        editingId = null;
        modalTitle.innerText = "Eintrag hinzufügen";
    };

    addEntryBtn.onclick = () => {
        modalTitle.innerText = "Eintrag hinzufügen";
        entryForm.reset();
        editingId = null;
        entryModal.style.display = 'block';
    };

    window.onclick = (event) => {
        if (event.target === entryModal) {
            entryModal.style.display = 'none';
            entryForm.reset();
        }
    };

    // Für Zugriff durch HTML inline onclick
    window.copyToClipboard = copyToClipboard;
});
document.getElementById('logoutBtn')?.addEventListener('click', () => {
    // App verstecken
    document.getElementById('app').style.display = 'none';
    // Login-Form wieder anzeigen
    document.getElementById('loginForm').style.display = 'block';
    // Master-Passwort-Feld leeren
    document.getElementById('master').value = '';
});
