import { useState, useEffect } from 'react';
import './App.css';

function App() {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const [formData, setFormData] = useState({
        username: '', email: '', apiKey: '', maxRequests: '', windowSeconds: ''
    });

    const fetchUsers = () => {
        setLoading(true);
        fetch('http://localhost:8080/api/users')
            .then(response => {
                if (!response.ok) throw new Error('Server responded with status: ' + response.status);
                return response.json();
            })
            .then(data => { setUsers(data); setLoading(false); })
            .catch(err => { setError(err.message); setLoading(false); });
    };

    useEffect(() => { fetchUsers(); }, []);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        fetch('http://localhost:8080/api/users', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                ...formData,
                maxRequests: Number(formData.maxRequests),
                windowSeconds: Number(formData.windowSeconds)
            })
        })
            .then(response => response.json())
            .then(() => {
                setFormData({ username: '', email: '', apiKey: '', maxRequests: '', windowSeconds: '' });
                fetchUsers();
            })
            .catch(err => setError(err.message));
    };

    const handleDelete = (id) => {
        fetch(`http://localhost:8080/api/users/${id}`, { method: 'DELETE' })
            .then(() => fetchUsers())
            .catch(err => setError(err.message));
    };

    if (loading) return <p>Loading users...</p>;
    if (error) return <p>Error: {error}</p>;

    return (
        <>
            <header className="navbar">
                <h1>Sentinel Dashboard</h1>
            </header>

            <main className="container">
                <h2>Add New User</h2>
                <form onSubmit={handleSubmit} style={{ marginBottom: '24px' }}>
                    <input name="username" placeholder="Username" value={formData.username} onChange={handleChange} required />
                    <input name="email" placeholder="Email" value={formData.email} onChange={handleChange} required />
                    <input name="apiKey" placeholder="API Key" value={formData.apiKey} onChange={handleChange} required />
                    <input name="maxRequests" type="number" placeholder="Max Requests" value={formData.maxRequests} onChange={handleChange} required />
                    <input name="windowSeconds" type="number" placeholder="Window (sec)" value={formData.windowSeconds} onChange={handleChange} required />
                    <button type="submit">Add User</button>
                </form>

                <h2>Registered Users</h2>
                <table className="user-table">
                    <thead>
                    <tr>
                        <th>ID</th><th>Username</th><th>API Key</th><th>Max Requests</th><th>Window (sec)</th><th>Action</th>
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
                            <td><button onClick={() => handleDelete(user.id)}>Delete</button></td>
                        </tr>
                    ))}
                    </tbody>
                </table>
            </main>
        </>
    );
}

export default App;