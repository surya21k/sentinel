import { useState, useEffect } from 'react';
import './App.css';

function App() {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch('http://localhost:8080/api/users')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Server responded with status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                setUsers(data);
                setLoading(false);
            })
            .catch(err => {
                setError(err.message);
                setLoading(false);
            });
    }, []);

    if (loading) return <p>Loading users...</p>;
    if (error) return <p>Error loading users: {error}</p>;

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
                        <th>Max Requests</th>
                        <th>Window (sec)</th>
                    </tr>
                    </thead>
                    <tbody>
                    {users.map(user => (
                        <tr key={user.id}>
                            <td>{user.id}</td>
                            <td>{user.username}</td>
                            <td>{user.apiKey}</td>
                            <td>{user.maxRequests}</td>
                            <td>{user.windowSeconds}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </main>
        </>
    );
}

export default App;