import axios from 'axios';

const API_URL = 'http://localhost:8081/api';

const testConnection = () => axios.get(`${API_URL}/test`);

export default { testConnection };
