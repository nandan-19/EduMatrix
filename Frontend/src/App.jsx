import React, { useEffect, useState } from 'react';
import ApiService from './services/ApiService';

const App = () => {
    const [message, setMessage] = useState('');

    useEffect(() => {
        // Call the backend API when the component loads
        ApiService.testConnection()
            .then((response) => {
                setMessage(response.data); // Set the backend response to the state
            })
            .catch((error) => {
                console.error('Error connecting to backend:', error);
                setMessage('Failed to connect to backend.');
            });
    }, []);

    return (
        <div>
            <h1>Frontend-Backend Connection Test</h1>
            <p>{message}</p> {/* Display the message */}
        </div>
    );
};

export default App;
