// Simulated data — later this comes from a real API call to your Java backend
const users = [
    { id: 1, username: "ashwin", apiKey: "abc123", isActive: true, requestsUsed: 2, maxRequests: 4 },
    { id: 2, username: "priya", apiKey: "xyz789", isActive: false, requestsUsed: 0, maxRequests: 2 },
    { id: 3, username: "surya", apiKey: "def456", isActive: true, requestsUsed: 3, maxRequests: 5 }
];

function renderUsers(userList) {
    const tbody = document.getElementById("userTableBody");
    tbody.innerHTML = ""; // clear existing rows before re-rendering

    userList.forEach(user => {
        const statusClass = user.isActive ? "active" : "inactive";
        const statusText = user.isActive ? "Active" : "Inactive";

        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.apiKey}</td>
            <td><span class="badge ${statusClass}">${statusText}</span></td>
            <td>${user.requestsUsed} / ${user.maxRequests}</td>
        `;
        tbody.appendChild(row);
    });
}

renderUsers(users);