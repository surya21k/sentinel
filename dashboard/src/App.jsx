import './App.css';

const users = [
  { id: 1, username: "ashwin", apiKey: "abc123", isActive: true, requestsUsed: 2, maxRequests: 4 },
  { id: 2, username: "priya", apiKey: "xyz789", isActive: false, requestsUsed: 0, maxRequests: 2 },
  { id: 3, username: "surya", apiKey: "def456", isActive: true, requestsUsed: 3, maxRequests: 5 }
];

function App() {
  return (
      <>
        <header className="navbar">
          <h1>Sentinel Dashboard</h1>
        </header>

        <main className="container">
          <h2>Registered Users</h2>
          <table className="user-table">
            <thead>
            <tr>
              <th>ID</th>
              <th>Username</th>
              <th>API Key</th>
              <th>Status</th>
              <th>Requests Used</th>
            </tr>
            </thead>
            <tbody>
            {users.map(user => (
                <tr key={user.id}>
                  <td>{user.id}</td>
                  <td>{user.username}</td>
                  <td>{user.apiKey}</td>
                  <td>
                  <span className={`badge ${user.isActive ? "active" : "inactive"}`}>
                    {user.isActive ? "Active" : "Inactive"}
                  </span>
                  </td>
                  <td>{user.requestsUsed} / {user.maxRequests}</td>
                </tr>
            ))}
            </tbody>
          </table>
        </main>
      </>
  );
}

export default App;